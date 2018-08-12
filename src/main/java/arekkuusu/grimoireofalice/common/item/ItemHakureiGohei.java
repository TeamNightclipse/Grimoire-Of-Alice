/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.api.items.GoheiMode;
import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import arekkuusu.grimoireofalice.common.Alice;
import arekkuusu.grimoireofalice.common.entity.EntityBarrier;
import arekkuusu.grimoireofalice.common.entity.EntityHakureiOrb;
import arekkuusu.grimoireofalice.common.lib.LibName;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import static arekkuusu.grimoireofalice.api.items.GoheiMode.*;

public class ItemHakureiGohei extends ItemGohei<GoheiMode> {

	public ItemHakureiGohei() {
		super(LibName.HAKUREI_GOHEI, GoheiMode.values());
		setMaxDamage(120);
		setMaxStackSize(1);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@Override
	public boolean onDroppedByPlayer(ItemStack stack, EntityPlayer player) {
		return false;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entityIn, int itemSlot, boolean isSelected) {
		if(entityIn instanceof EntityPlayer && getType(stack) == PASSIVE) {
			EntityPlayer player = (EntityPlayer) entityIn;
			if(!player.isSneaking()) {
				Vec3d vec = player.getLookVec();
				if(player.motionX < 0.5 && player.motionX > -0.5) {
					player.motionX = 0.5 * vec.x;
				}
				if(player.motionY < 0.5 && player.motionY > -0.5) {
					player.motionY = 0.5 * vec.y;
				}
				if(player.motionZ < 0.5 && player.motionZ > -0.5) {
					player.motionZ = 0.5 * vec.z;
				}
			} else {
				player.motionY = 0;
			}
			player.fallDistance = 0.0F;
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if(player.isSneaking()) {
			increaseType(stack);
			if(world.isRemote) {
				String modeName = getType(stack).toString() + ".name";
				ITextComponent text = new TextComponentTranslation("grimoire.tooltip.hakurei_gohei_mode_header.name");
				text.appendSibling(new TextComponentTranslation("grimoire.tooltip.hakurei_gohei_mode_" + modeName));

				Alice.proxy.displayRecordText(text);
			}
		} else {
			player.setActiveHand(hand);

			GoheiMode mode = getType(stack);
			if(mode == YING_YANG_ORB) {
				player.playSound(GrimoireSoundEvents.POWER_UP, 0.1F, itemRand.nextFloat() * 0.1F + 0.8F);
				if(!world.isRemote) {
					EntityHakureiOrb orb = new EntityHakureiOrb(world, player);
					world.spawnEntity(orb);
				}
			} else if((mode == BARRIER_EXPLODE || mode == BARRIER_MOTION) && !world.isRemote) {
				EntityBarrier barrier = new EntityBarrier(world, player, mode.getType());
				world.spawnEntity(barrier);
			}
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int ticks) {
		if(getType(stack) == AURA_MANIPULATION) {
			List<EntityLivingBase> list = player.world.getEntitiesWithinAABB(EntityLivingBase.class,
					player.getEntityBoundingBox().grow(4.0D), entity -> !player.equals(entity));
			for(EntityLivingBase mob : list) {
				Vec3d playerPos = player.getPositionVector();
				Vec3d mobPos = mob.getPositionVector();
				double ratio = playerPos.distanceTo(mobPos) / 4;
				double scaling = 1 - ratio;
				Vec3d motion = playerPos.subtract(mobPos).scale(scaling);
				mob.motionX = -motion.x * 1.2;
				mob.motionY = -motion.y * 1.2;
				mob.motionZ = -motion.z * 1.2;
				mob.fallDistance = 0.0F;
				if(ticks % 4 == 0) {
					mob.world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, mob.posX + (itemRand.nextDouble() - 0.5D) * mob.width,
							mob.posY + itemRand.nextDouble() * mob.height - 0.25D, mob.posZ + (itemRand.nextDouble() - 0.5D) * mob.width,
							(itemRand.nextFloat() - 0.5D) * 0.2, -itemRand.nextFloat(), (itemRand.nextFloat() - 0.5D) * 0.2);
				}
			}
		}
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer && !entityLiving.isSneaking()) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			if(getType(stack) == PASSIVE) {
				Vec3d look = player.getLookVec();
				float distance = 5F;
				double dx = player.posX + look.x * distance;
				double dy = player.posY + 1 + look.y * distance;
				double dz = player.posZ + look.z * distance;
				if(isSafe(world, dx, dy, dz)) {
					if(player instanceof EntityPlayerMP) {
						((EntityPlayerMP) player).setPositionAndUpdate(dx, dy, dz);
					}
					stack.damageItem(1, player);
				}
			}
			EnumHand hand = player.getHeldItemMainhand() == stack ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
			player.swingArm(hand);
			player.getCooldownTracker().setCooldown(this, 10);
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if(getType(stack) == OFFENSIVE) {
			switch(target.getCreatureAttribute()) {
				case UNDEAD:
					attacker.world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, target.posX, target.posY + 1, target.posZ, 0.0D, 0.0D, 0.0D);
					if(!target.world.isRemote) {
						target.attackEntityFrom(DamageSource.causeThornsDamage(attacker), 20);
					}
					break;
				case ARTHROPOD:
					attacker.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, target.posX, target.posY + 1, target.posZ, 0.0D, 0.0D, 0.0D);
					if(!target.world.isRemote) {
						target.attackEntityFrom(DamageSource.causeThornsDamage(attacker), 10);
					}
					break;
				default:
					attacker.world.spawnParticle(EnumParticleTypes.HEART, target.posX, target.posY + 1, target.posZ, 0.0D, 0.0D, 0.0D);
					if(!target.world.isRemote) {
						target.attackEntityFrom(DamageSource.MAGIC, 5);
					}
			}
		} else {
			stack.damageItem(1, attacker);
		}
		return true;
	}

	private static boolean isSafe(World world, double x, double y, double z) {
		if(y < 0) {
			return false;
		}
		BlockPos pos = new BlockPos(x, y, z);
		IBlockState state = world.getBlockState(pos);
		return state.getBlock().isAir(state, world, pos) || !state.isSideSolid(world, pos, EnumFacing.UP);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack) {
		return I18n.format("item.hakurei_gohei.name") + " :"
				+ I18n.format("grimoire.tooltip.hakurei_gohei_mode_" + getType(stack).toString() + ".name");
	}

	@Override
	protected GoheiMode getType(ItemStack itemStack) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		return nbt == null ? GoheiMode.OFFENSIVE : GoheiMode.fromType(nbt.getByte("GoheiMode"));
	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemstack) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 500;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
