package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import arekkuusu.grimoireofalice.common.Alice;
import arekkuusu.grimoireofalice.common.core.handler.ConfigHandler;
import arekkuusu.grimoireofalice.common.core.helper.MiscHelper;
import arekkuusu.grimoireofalice.common.entity.EntityMagicCircle;
import arekkuusu.grimoireofalice.common.lib.LibName;
import arekkuusu.grimoireofalice.compat.danmakucore.LibGOAShotData;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.DanmakuTemplate;
import net.katsstuff.teamnightclipse.danmakucore.lib.LibColor;
import net.katsstuff.teamnightclipse.danmakucore.scalastuff.DanmakuCreationHelper;
import net.katsstuff.teamnightclipse.mirror.data.Vector3;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;

public class ItemSwordOfHisou extends ItemSwordOwner {

	public ItemSwordOfHisou(ToolMaterial material) {
		super(material, LibName.HISOU_SWORD);
		setNoRepair();
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLivingBase, ItemStack stack) {
		if(!entityLivingBase.world.isRemote && entityLivingBase instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLivingBase;
			if(player.getCooldownTracker().hasCooldown(this)) {
				Optional<Entity> lookedAt = Vector3.getEntityLookedAt(player, entity -> !player.equals(entity) && entity instanceof EntityLivingBase, 35);
				if(lookedAt.isPresent()) {
					EntityLivingBase entity = (EntityLivingBase) lookedAt.get();
					Vec3d look = player.getLookVec();
					entity.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 50, 0));
					entity.attackEntityFrom(DamageSource.MAGIC, 10);
					entity.motionX -= look.x * 0.5;
					entity.motionY -= look.y * 0.5;
					entity.motionZ -= look.z * 0.5;
				}
			}
		}
		return super.onEntitySwing(entityLivingBase, stack);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		player.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase living, int count) {
		if(count > 260 && count % 5 == 0) {
			List<EntityMob> list = living.world.getEntitiesWithinAABB(EntityMob.class, living.getEntityBoundingBox().grow(20));
			if(!list.isEmpty()) {
				for(EntityLivingBase entityMob : list) {
					Alice.proxy.spawnRedMist(living.world, living, Vector3.fromEntityCenter(entityMob), Vector3.Zero());
				}
			}
		}
		if(count < 260 && count > 220 && count % 2 == 0) {
			living.playSound(GrimoireSoundEvents.WAVE, 0.2F, 1F);
			World world = living.world;
			if(!world.isRemote && living instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) living;
				if(isOwner(stack, player)) {
					DanmakuTemplate danmaku = DanmakuTemplate.builder()
							.setUser(player)
							.setShot(LibGOAShotData.SUN.setDamage(5).setSize(1.5F))
							.build();
					DanmakuCreationHelper.createRandomRingShot(danmaku, 2 + itemRand.nextInt(3), 5, 0.5D);
				}
			}
		}
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
		int timeUsed = getMaxItemUseDuration(stack) - timeLeft;
		entityLiving.playSound(GrimoireSoundEvents.WAVE, 0.2F, 1F);
		if(!world.isRemote && entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			if(isOwner(stack, player) && timeUsed < 20 && timeUsed > 5) {
				List<EntityMob> list = world.getEntitiesWithinAABB(EntityMob.class, player.getEntityBoundingBox().grow(20));
				if(!list.isEmpty()) {
					int count = (int) (list.stream().mapToDouble(EntityLivingBase::getHealth).sum() * 2);
					EntityMagicCircle circle = new EntityMagicCircle(world, player, EntityMagicCircle.EnumTextures.RED_NORMAL, count);
					world.spawnEntity(circle);
					player.getCooldownTracker().setCooldown(this, count);
				}
			}
		}
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(player.isSneaking()) {
			world.playSound(player, hitX, hitY, hitZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1F, 1F);
			for(int t = 0; t < 5; t++) {
				for(int u = 0; u < 10; u++) {
					spawnGround(player, itemRand.nextDouble(), itemRand.nextDouble());
					spawnGround(player, -itemRand.nextDouble(), -itemRand.nextDouble());
					spawnGround(player, itemRand.nextDouble(), -itemRand.nextDouble());
					spawnGround(player, -itemRand.nextDouble(), itemRand.nextDouble());
				}
			}
			if(!world.isRemote) {
				List<EntityMob> list = world.getEntitiesWithinAABB(EntityMob.class, player.getEntityBoundingBox().grow(10.0D));
				for(EntityMob mob : list) {
					mob.attackEntityFrom(DamageSource.DROWN, 1);
					MiscHelper.pushEntity(player, mob);
				}
			}
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}

	private static void spawnGround(EntityPlayer player, double xVelocity, double zVelocity) {
		player.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, player.posX, player.posY, player.posZ, xVelocity, 0, zVelocity);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
		if(ConfigHandler.grimoireOfAlice.food.heavelyPeach && itemRand.nextBoolean() && pos.getY() > 100 && state.getMaterial() == Material.LEAVES) {
			stack.damageItem(1, entityLiving);
			if(!world.isRemote) {
				EntityItem entityItem = new EntityItem(entityLiving.world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(ModItems.HEAVENLY_PEACH));
				entityLiving.world.spawnEntity(entityItem);
			}
			return true;
		}
		return super.onBlockDestroyed(stack, world, state, pos, entityLiving);
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
}
