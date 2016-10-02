package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.entity.EntityMagicCircle;
import arekkuusu.grimoireofalice.handler.EnumTextures;
import arekkuusu.grimoireofalice.lib.LibItemName;
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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.UsernameCache;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.UUID;

public class ItemSwordOfHisou extends ItemSwordOwner {

	ItemSwordOfHisou(ToolMaterial material) {
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

	@SuppressWarnings("ConstantConditions")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Sword of Scarlet Perceptions");
		if(GuiScreen.isShiftKeyDown()) {
			list.add(TextFormatting.GRAY + "It has the ability to identifying one's spirit,");
			list.add(TextFormatting.GRAY + "no matter the circumstances.");
		} else {
			list.add(TextFormatting.ITALIC + "SHIFT for details");
		}
		list.add(TextFormatting.ITALIC + "It takes the form of a golden Chinese jian.");
		super.addInformation(stack, player, list, p_77624_4_);
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase playerIn, ItemStack itemStackIn) {
		if(playerIn instanceof  EntityPlayer) {
			EntityPlayer player = (EntityPlayer)playerIn;
			if(player.getCooldownTracker().hasCooldown(this)) {
				double range = 30.0D;
				Vec3d look = player.getLookVec();
				Vec3d vec3d = new Vec3d(player.posX, player.posY + player.getEyeHeight(), player.posZ);
				Vec3d vec3d1 = new Vec3d(player.posX + look.xCoord * range, player.posY + look.yCoord * range, player.posZ + look.zCoord * range);
				RayTraceResult movingObjectPosition = player.worldObj.rayTraceBlocks(vec3d, vec3d1, false, true, true);
				if (movingObjectPosition != null) {
					vec3d1 = new Vec3d(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
				}
				EntityLivingBase entity = null;
				List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().addCoord(look.xCoord * range, look.yCoord * range, look.zCoord * range).expand(1.0D, 1.0D, 1.0D));
				double d = 0.0D;
				for (Entity entity1 : list) {
					if (entity1 instanceof EntityLivingBase) {
						AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().expandXyz(0.3F);
						RayTraceResult movingObjectPosition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
						if (movingObjectPosition1 != null) {
							double d1 = vec3d.distanceTo(movingObjectPosition1.hitVec);
							if (d1 < d || d == 0.0D) {
								entity = (EntityLivingBase) entity1;
								d = d1;
							}
						}
					}
				}
				if (entity != null && !player.worldObj.isRemote) {
					entity.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 50, 0));
					entity.attackEntityFrom(DamageSource.lightningBolt, 2);
					entity.motionX -= look.xCoord * 0.5;
					entity.motionY -= look.yCoord * 0.5;
					entity.motionZ -= look.zCoord * 0.5;
					itemStackIn.damageItem(1, player);
				}
			}
		}
		return super.onEntitySwing(playerIn, itemStackIn);
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
				if (!stack.hasTagCompound()) return;
				if (isOwner(stack, player)) {
					if (timeUsed < 50 && timeUsed > 10) {
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
					} else if (timeUsed >= 50  && player.isSneaking()) {
						//TODO: Make this Item shoot a bunch of red danmaku
					}
				}
			}
		}
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
		stack.damageItem(1, entityLiving);
		if(state.getMaterial() == Material.LEAVES ) {
			EntityItem entityItem = new EntityItem(entityLiving.worldObj, pos.getX() + 0.5 , pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(ModItems.heavenlyPeach));
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
