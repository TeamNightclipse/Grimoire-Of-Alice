package arekkuusu.grimoireofalice.plugin.danmakucore.item;

import arekkuusu.grimoireofalice.entity.EntityMagicCircle;
import arekkuusu.grimoireofalice.handler.EnumTextures;
import arekkuusu.grimoireofalice.item.ItemSwordOwner;
import arekkuusu.grimoireofalice.item.ModItems;
import arekkuusu.grimoireofalice.lib.LibItemName;
import net.katsstuff.danmakucore.data.Vector3;
import net.katsstuff.danmakucore.entity.danmaku.DanmakuBuilder;
import net.katsstuff.danmakucore.helper.DanmakuCreationHelper;
import net.katsstuff.danmakucore.lib.LibColor;
import net.katsstuff.danmakucore.lib.data.LibShotData;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Optional;

public class ItemSwordOfHisou extends ItemSwordOwner {

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
		list.add(TextFormatting.GOLD + "Sword of Scarlet Perceptions");
		if(GuiScreen.isShiftKeyDown()) {
			list.add(TextFormatting.GRAY + "It has the ability to identifying one's spirit,");
			list.add(TextFormatting.GRAY + "no matter the circumstances.");
			list.add(TextFormatting.ITALIC + "It takes the form of a golden Chinese jian.");
		} else {
			list.add(TextFormatting.ITALIC + "SHIFT for details");
		}
		super.addInformation(stack, player, list, p_77624_4_);
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLivingBase, ItemStack itemStackIn) {
		if(!entityLivingBase.worldObj.isRemote && entityLivingBase instanceof  EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entityLivingBase;
			if(player.getCooldownTracker().hasCooldown(this)) {
				Optional<Entity> lookedAt = Vector3.getEntityLookedAt(player, entity -> entity != player && entity instanceof EntityLivingBase);

				if (lookedAt.isPresent()) {
					EntityLivingBase entity = (EntityLivingBase)lookedAt.get();
					Vec3d look = player.getLookVec();

					entity.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 50, 0));
					entity.attackEntityFrom(DamageSource.lightningBolt, 2);
					entity.motionX -= look.xCoord * 0.5;
					entity.motionY -= look.yCoord * 0.5;
					entity.motionZ -= look.zCoord * 0.5;
					itemStackIn.damageItem(1, player);
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

	@SuppressWarnings("ConstantConditions")
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		int timeUsed = getMaxItemUseDuration(stack) - timeLeft;
		if(!worldIn.isRemote) {
			if (entityLiving instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) entityLiving;
				if (isOwner(stack, player)) {
					if (timeUsed < 20 && timeUsed > 5) {
						List<EntityMob> list = worldIn.getEntitiesWithinAABB(EntityMob.class, player.getEntityBoundingBox().expandXyz(20));
						if (!list.isEmpty()) {
							int count = 0;
							for (EntityMob mob : list) {
								count += mob.getHealth();
							}
							EntityMagicCircle circle = new EntityMagicCircle(worldIn, player, EnumTextures.RED_NORMAL, count);
							worldIn.spawnEntityInWorld(circle);
							player.getCooldownTracker().setCooldown(this, count);
						}
					} else if (timeUsed >= 20  && player.isSneaking()) {
						for (int i = 0; i < 4; i++) {
							DanmakuBuilder danmaku = DanmakuBuilder.builder()
									.setUser(player)
									.setShot(LibShotData.SHOT_MEDIUM.setColor(LibColor.COLOR_SATURATED_RED).setDelay(i * 3))
									.build();

							DanmakuCreationHelper.createRandomRingShot(danmaku, 25 + itemRand.nextInt(5), 5, 0.5D);
						}
					}
				}
			}
		}
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
		stack.damageItem(1, entityLiving);
		if(state.getMaterial() == Material.LEAVES ) {
			EntityItem entityItem = new EntityItem(entityLiving.worldObj, pos.getX() + 0.5 , pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(
					ModItems.HEAVENLY_PEACH));
			if (!worldIn.isRemote) {
				entityLiving.worldObj.spawnEntityInWorld(entityItem);
			}
			return true;
		}
		return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BLOCK;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 100;
	}

	@Override
	public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2) {
		return false;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
