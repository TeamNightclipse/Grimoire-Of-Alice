/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import java.util.List;

import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import arekkuusu.grimoireofalice.common.entity.EntityBarrier;
import arekkuusu.grimoireofalice.common.entity.EntityHakureiOrb;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static arekkuusu.grimoireofalice.common.item.ItemHakureiGohei.Mode.*;

public class ItemHakureiGohei extends ItemMod {

	public ItemHakureiGohei() {
		super(LibItemName.HAKUREI_GOHEI);
		setMaxStackSize(1);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.hakurei_gohei_header.name"));
	}

	@Override
	public boolean onDroppedByPlayer(ItemStack stack, EntityPlayer player) {
		return false;
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof EntityPlayer && isSelected && Mode.fromType(getMode(stack)) == PASSIVE) {
			EntityPlayer player = (EntityPlayer) entityIn;
			if (!player.isSneaking()) {
				Vec3d vec = player.getLookVec();
				if (player.motionX < 0.5 && player.motionX > -0.5) {
					player.motionX = 0.5 * vec.xCoord;
				}
				if (player.motionY < 0.5 && player.motionY > -0.5) {
					player.motionY = 0.5 * vec.yCoord;
				}
				if (player.motionZ < 0.5 && player.motionZ > -0.5) {
					player.motionZ = 0.5 * vec.zCoord;
				}
			}
			else {
				player.motionY = 0;
				player.setAir(0);
			}
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);

		Mode mode = fromType(getMode(itemStackIn));
		if (!playerIn.isSneaking()) {
			if (mode == YING_YANG_ORB) {
				EntityHakureiOrb orb = new EntityHakureiOrb(worldIn, playerIn);
				worldIn.playSound(playerIn, playerIn.getPosition(), GrimoireSoundEvents.POWER_UP, SoundCategory.HOSTILE, 0.1F,
						itemRand.nextFloat() * 0.1F + 0.8F);
				if (!worldIn.isRemote) {
					worldIn.spawnEntityInWorld(orb);
				}
			}
			else if ((mode == BARRIER_EXPLODE || mode == BARRIER_MOTION) && !worldIn.isRemote) {
				EntityBarrier barrier = new EntityBarrier(worldIn, playerIn, mode.getType());
				worldIn.spawnEntityInWorld(barrier);
			}
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase playerIn, int ticks) {
		if (fromType(getMode(stack)) == AURA_MANIPULATION) {
			List<EntityMob> list = playerIn.worldObj.getEntitiesWithinAABB(EntityMob.class, playerIn.getEntityBoundingBox().expandXyz(4.0D));
			for(EntityMob mob : list) {
				Vec3d playerPos = playerIn.getPositionVector();
				Vec3d mobPos = mob.getPositionVector();
				double ratio = playerPos.distanceTo(mobPos) / 4;
				double scaling = 1 - ratio;
				Vec3d motion = playerPos.subtract(mobPos).scale(scaling);
				mob.motionX = -motion.xCoord * 1.2;
				mob.motionY = -motion.yCoord * 1.2;
				mob.motionZ = -motion.zCoord * 1.2;
			}
		}
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer playerIn = (EntityPlayer) entityLiving;
			if (playerIn.isSneaking()) {
				byte mode = (byte) MathHelper.clamp_int(getMode(stack) + 1, 0, 5);
				setMode(stack, getMode(stack) != 5 ? mode : 0);
			}
			else {
				if (fromType(getMode(stack)) == PASSIVE) {
					Vec3d look = playerIn.getLookVec();
					float distance = 5F;
					double dx = playerIn.posX + look.xCoord * distance;
					double dy = playerIn.posY + 1 + look.yCoord * distance;
					double dz = playerIn.posZ + look.zCoord * distance;
					if (isSafe(worldIn, dx, dy, dz)) {
						playerIn.setPosition(dx, dy, dz);
					}
				}
				playerIn.swingArm(EnumHand.MAIN_HAND);
				playerIn.getCooldownTracker().setCooldown(this, 10);
			}
			stack.damageItem(1, playerIn);
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (fromType(getMode(stack)) == OFFENSIVE) {
			if (target.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) {
				attacker.worldObj.spawnParticle(EnumParticleTypes.CRIT_MAGIC, target.posX, target.posY + 1, target.posZ, 0.0D, 0.0D, 0.0D);
				target.attackEntityFrom(DamageSource.causeThornsDamage(attacker), 20);
			}
			else if (target.getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD) {
				attacker.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, target.posX, target.posY + 1, target.posZ, 0.0D, 0.0D, 0.0D);
				target.attackEntityFrom(DamageSource.causeThornsDamage(attacker), 10);
			}
			else if (target instanceof EntityPlayer) {
				attacker.worldObj.spawnParticle(EnumParticleTypes.HEART, target.posX, target.posY + 1, target.posZ, 0.0D, 0.0D, 0.0D);
				target.attackEntityFrom(DamageSource.magic, 5);
			}
			else {
				target.attackEntityFrom(DamageSource.causeThornsDamage(attacker), 3);
			}
		}
		return true;
	}

	private void setMode(ItemStack itemStack, byte mode) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		if (nbt == null) {
			nbt = new NBTTagCompound();
			itemStack.setTagCompound(nbt);
		}
		nbt.setByte("GoheiMode", mode);
	}

	private byte getMode(ItemStack itemStack) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		return nbt == null ? 5 : nbt.getByte("GoheiMode");
	}

	private boolean isSafe(World world, double x, double y, double z) {
		if (y < 0) return false;
		BlockPos pos = new BlockPos(x, y, z);
		IBlockState state = world.getBlockState(pos);
		return state.getBlock().isAir(state, world, pos) || !state.isSideSolid(world, pos, EnumFacing.UP);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack) {
		return I18n.format("item.hakureigohei.name") + " : "
				+ I18n.format("grimoire.tooltip.hakurei_gohei_mode_" + fromType(getMode(stack)).toString() + ".name");
	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemstack) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 72000;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}

	public enum Mode {
		PASSIVE("passive", (byte) 0),
		AURA_MANIPULATION("aura_manipulation", (byte) 1),
		YING_YANG_ORB("hakurei_yin_yang_orbs", (byte) 2),
		BARRIER_EXPLODE("exploding_barrier", (byte) 3),
		BARRIER_MOTION("motion_barrier", (byte) 4),
		OFFENSIVE("offensive", (byte) 5);

		private final String name;
		private final byte type;

		Mode(String name, byte type) {
			this.name = name;
			this.type = type;
		}

		public static Mode fromType(byte type) {
			switch (type) {
				case 0:
					return PASSIVE;
				case 1:
					return AURA_MANIPULATION;
				case 2:
					return YING_YANG_ORB;
				case 3:
					return BARRIER_EXPLODE;
				case 4:
					return BARRIER_MOTION;
				default:
					return OFFENSIVE;
			}
		}

		public byte getType() {
			return type;
		}

		@Override
		public String toString() {
			return name;
		}
	}
}
