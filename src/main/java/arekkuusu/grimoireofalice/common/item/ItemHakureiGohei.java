/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.api.items.GoheiMode;
import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import arekkuusu.grimoireofalice.common.GrimoireOfAlice;
import arekkuusu.grimoireofalice.common.entity.EntityBarrier;
import arekkuusu.grimoireofalice.common.entity.EntityHakureiOrb;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import static arekkuusu.grimoireofalice.api.items.GoheiMode.*;

public class ItemHakureiGohei extends ItemGohei<GoheiMode> {

	public ItemHakureiGohei() {
		super(LibItemName.HAKUREI_GOHEI, GoheiMode.values());
		setMaxDamage(120);
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
		if (entityIn instanceof EntityPlayer && getType(stack) == PASSIVE) {
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
			}
			player.fallDistance = 0.0F;
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if (playerIn.isSneaking()) {
			increaseType(itemStackIn);
			if (worldIn.isRemote) {
				String modeName = getType(itemStackIn).toString() + ".name";
				ITextComponent text = new TextComponentTranslation("grimoire.tooltip.hakurei_gohei_mode_header.name");
				text.appendSibling(new TextComponentTranslation("grimoire.tooltip.hakurei_gohei_mode_" + modeName));

				GrimoireOfAlice.proxy.displayRecordText(text);
			}
		}
		else {
			playerIn.setActiveHand(hand);

			GoheiMode mode = getType(itemStackIn);
			if (mode == YING_YANG_ORB) {
				playerIn.playSound(GrimoireSoundEvents.POWER_UP, 0.1F, itemRand.nextFloat() * 0.1F + 0.8F);
				if (!worldIn.isRemote) {
					EntityHakureiOrb orb = new EntityHakureiOrb(worldIn, playerIn);
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
		if (getType(stack) == AURA_MANIPULATION) {
			List<EntityLivingBase> list = playerIn.world.getEntitiesWithinAABB(EntityLivingBase.class,
					playerIn.getEntityBoundingBox().expandXyz(4.0D), entity -> entity != playerIn);
			for(EntityLivingBase mob : list) {
				Vec3d playerPos = playerIn.getPositionVector();
				Vec3d mobPos = mob.getPositionVector();
				double ratio = playerPos.distanceTo(mobPos) / 4;
				double scaling = 1 - ratio;
				Vec3d motion = playerPos.subtract(mobPos).scale(scaling);
				mob.motionX = -motion.xCoord * 1.2;
				mob.motionY = -motion.yCoord * 1.2;
				mob.motionZ = -motion.zCoord * 1.2;
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
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if (entityLiving instanceof EntityPlayer && !entityLiving.isSneaking()) {
			EntityPlayer playerIn = (EntityPlayer) entityLiving;
			if (getType(stack) == PASSIVE) {
				Vec3d look = playerIn.getLookVec();
				float distance = 5F;
				double dx = playerIn.posX + look.xCoord * distance;
				double dy = playerIn.posY + 1 + look.yCoord * distance;
				double dz = playerIn.posZ + look.zCoord * distance;
				if (isSafe(worldIn, dx, dy, dz)) {
					if (playerIn instanceof EntityPlayerMP) {
						((EntityPlayerMP) playerIn).setPositionAndUpdate(dx, dy, dz);
					}
					stack.damageItem(1, playerIn);
				}
			}
			EnumHand hand = playerIn.getHeldItemMainhand() == stack ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
			playerIn.swingArm(hand);
			playerIn.getCooldownTracker().setCooldown(this, 10);
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (getType(stack) == OFFENSIVE) {
			if (target.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) {
				attacker.world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, target.posX, target.posY + 1, target.posZ, 0.0D, 0.0D, 0.0D);
				if(!target.world.isRemote) {
					target.attackEntityFrom(DamageSource.causeThornsDamage(attacker), 20);
				}
			}
			else if (target.getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD) {
				attacker.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, target.posX, target.posY + 1, target.posZ, 0.0D, 0.0D, 0.0D);
				if(!target.world.isRemote) {
					target.attackEntityFrom(DamageSource.causeThornsDamage(attacker), 10);
				}
			}
			else if (target instanceof EntityPlayer) {
				attacker.world.spawnParticle(EnumParticleTypes.HEART, target.posX, target.posY + 1, target.posZ, 0.0D, 0.0D, 0.0D);
				if(!target.world.isRemote) {
					target.attackEntityFrom(DamageSource.magic, 5);
				}
			}
			else {
				target.attackEntityFrom(DamageSource.causeThornsDamage(attacker), 3);
			}
		}
		else {
			stack.damageItem(1, attacker);
		}
		return true;
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
		return I18n.format("item.hakureigohei.name") + " :"
				+ I18n.format("grimoire.tooltip.hakurei_gohei_mode_" + getType(stack).toString() + ".name");
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
