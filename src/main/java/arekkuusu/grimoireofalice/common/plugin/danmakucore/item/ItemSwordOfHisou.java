package arekkuusu.grimoireofalice.common.plugin.danmakucore.item;

import java.util.List;
import java.util.Optional;

import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import arekkuusu.grimoireofalice.client.fx.ParticleFX;
import arekkuusu.grimoireofalice.common.GrimoireOfAlice;
import arekkuusu.grimoireofalice.common.core.handler.ConfigHandler;
import arekkuusu.grimoireofalice.common.entity.EntityMagicCircle;
import arekkuusu.grimoireofalice.common.item.ItemSwordOwner;
import arekkuusu.grimoireofalice.common.item.ModItems;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import arekkuusu.grimoireofalice.common.plugin.danmakucore.LibGOAShotData;
import net.katsstuff.danmakucore.data.Vector3;
import net.katsstuff.danmakucore.entity.danmaku.DanmakuTemplate;
import net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters;
import net.katsstuff.danmakucore.helper.DanmakuCreationHelper;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.katsstuff.danmakucore.lib.LibColor;
import net.katsstuff.danmakucore.lib.data.LibShotData;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSwordOfHisou extends ItemSwordOwner implements IOwnedBy {

	public ItemSwordOfHisou(ToolMaterial material) {
		super(material, LibItemName.HISOU);
		setNoRepair();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.hisou_sword_header.name"));
		if(GuiScreen.isShiftKeyDown()) {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.hisou_sword_description_top.name"));
		}
		else {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.hisou_sword_shift.name"));
		}
		super.addInformation(stack, player, list, p_77624_4_);
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLivingBase, ItemStack itemStackIn) {
		if(!entityLivingBase.world.isRemote && entityLivingBase instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entityLivingBase;
			if(player.getCooldownTracker().hasCooldown(this)) {
				Optional<Entity> lookedAt = Vector3.getEntityLookedAt(player, entity -> entity != player && entity instanceof EntityLivingBase, 35);

				if(lookedAt.isPresent()) {
					EntityLivingBase entity = (EntityLivingBase)lookedAt.get();
					Vec3d look = player.getLookVec();

					entity.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 50, 0));
					entity.attackEntityFrom(DamageSource.magic, 10);
					entity.motionX -= look.xCoord * 0.5;
					entity.motionY -= look.yCoord * 0.5;
					entity.motionZ -= look.zCoord * 0.5;
				}
			}
		}
		return super.onEntitySwing(entityLivingBase, itemStackIn);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase livingBase, int count) {
		if (count > 260 && count % 5 == 0) {
			List<EntityMob> list = livingBase.world.getEntitiesWithinAABB(EntityMob.class, livingBase.getEntityBoundingBox().expandXyz(20));
			if (!list.isEmpty()) {
				for (EntityLivingBase entityMob : list) {
					GrimoireOfAlice.proxy.sparkleFX(ParticleFX.RED_MIST, livingBase, entityMob.posX, entityMob.posY, entityMob.posZ, 0, 0, 0);
				}
			}
		}
		if (count < 260 && count > 220 && count % 2 == 0) {
			livingBase.playSound(GrimoireSoundEvents.WAVE, 0.2F, 1F);
			World world = livingBase.world;
			if (!world.isRemote && livingBase instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) livingBase;
				if (isOwner(stack, player)) {
					DanmakuTemplate danmaku = DanmakuTemplate.builder()
							.setUser(player)
							.setShot(LibGOAShotData.SUN.setColor(LibColor.COLOR_SATURATED_RED))
							.build();

					DanmakuCreationHelper.createRandomRingShot(danmaku, 10 + itemRand.nextInt(5), 5, 0.5D);
				}
			}
		}
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		int timeUsed = getMaxItemUseDuration(stack) - timeLeft;
		entityLiving.playSound(GrimoireSoundEvents.WAVE, 0.2F, 1F);
		if (!worldIn.isRemote) {
			if (entityLiving instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) entityLiving;
				if (isOwner(stack, player)) {
					if (timeUsed < 20 && timeUsed > 5) {
						List<EntityMob> list = worldIn.getEntitiesWithinAABB(EntityMob.class, player.getEntityBoundingBox().expandXyz(20));
						if (!list.isEmpty()) {
							int count = (int)(list.stream().mapToDouble(EntityLivingBase::getHealth).sum() * 2);
							EntityMagicCircle circle = new EntityMagicCircle(worldIn, player, EntityMagicCircle.EnumTextures.RED_NORMAL, count);
							worldIn.spawnEntityInWorld(circle);
							player.getCooldownTracker().setCooldown(this, count);
						}
					}
				}
			}
		}
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(player.isSneaking()) {
			worldIn.playSound(player, hitX, hitY, hitZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1F, 1F);
			for (int t = 0; t < 5; t++) {
				for (int u = 0; u < 10; u++) {
					spawnGround(player, itemRand.nextDouble(), itemRand.nextDouble());
					spawnGround(player, -itemRand.nextDouble(), -itemRand.nextDouble());
					spawnGround(player, itemRand.nextDouble(), -itemRand.nextDouble());
					spawnGround(player, -itemRand.nextDouble(), itemRand.nextDouble());
				}
			}

			if(!worldIn.isRemote) {
				List<EntityMob> list = worldIn.getEntitiesWithinAABB(EntityMob.class, player.getEntityBoundingBox().expandXyz(10.0D));
				for (EntityMob mob : list) {
					mob.attackEntityFrom(DamageSource.drown, 1);
					Vec3d playerPos = player.getPositionVector();
					Vec3d mobPos = mob.getPositionVector();
					double ratio = playerPos.distanceTo(mobPos) / 4;
					double scaling = 1 - ratio;
					Vec3d motion = playerPos.subtract(mobPos).scale(scaling);
					mob.motionX = -motion.xCoord * 2;
					mob.motionY = .3F;
					mob.motionZ = -motion.zCoord * 2;
				}
			}
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}

	private void spawnGround(EntityPlayer player, double xVelocity, double zVelocity) {
		player.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, player.posX, player.posY, player.posZ, xVelocity, 0, zVelocity);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
		if(ConfigHandler.grimoireOfAlice.food.heavelyPeach && itemRand.nextBoolean()) {
			if (pos.getY() > 100 && state.getMaterial() == Material.LEAVES) {
				stack.damageItem(1, entityLiving);
				if (!worldIn.isRemote) {
					EntityItem entityItem = new EntityItem(entityLiving.world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
							new ItemStack(ModItems.HEAVENLY_PEACH));

					entityLiving.world.spawnEntityInWorld(entityItem);
				}
				return true;
			}
		}
		return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		return true;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 300;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}

	@Override
	public EnumTouhouCharacters character(ItemStack stack) {
		return EnumTouhouCharacters.TENSHI_HINANAWI;
	}
}
