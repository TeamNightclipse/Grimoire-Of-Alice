/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import arekkuusu.grimoireofalice.common.Alice;
import arekkuusu.grimoireofalice.common.lib.LibName;
import net.katsstuff.teamnightclipse.mirror.data.Vector3;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class ItemShinmyoumaruNeedle extends ItemBaseSword {

	public ItemShinmyoumaruNeedle(ToolMaterial material) {
		super(material, LibName.SHINMYOUMARU_NEEDLE);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		player.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entityIn, int itemSlot, boolean isSelected) {
		if(entityIn instanceof EntityPlayer) {
			EntityPlayer player = ((EntityPlayer) entityIn);
			if(isSelected && player.getCooldownTracker().hasCooldown(this) && !wasShifting(stack) && player.ticksExisted % 2 == 0) {
				EnumHand hand = player.getHeldItemMainhand() == stack ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
				player.swingArm(hand);

				Vec3d vec = player.getLookVec();
				float distance = 4F + itemRand.nextInt(3);
				double dx = player.posX + vec.x * distance;
				double dy = player.posY + 2.5 + vec.y * distance;
				double dz = player.posZ + vec.z * distance;
				Alice.proxy.spawnNeedleSwing(world, Vector3.apply(dx, dy, dz), Vector3.apply(itemRand.nextFloat(), 0F, 0F), 20, 0.5F + itemRand.nextFloat());
				world.playSound(player, player.getPosition(), GrimoireSoundEvents.NEEDLE_SWEEP, SoundCategory.PLAYERS, 1F, 1F);
				if(!world.isRemote) {
					List<EntityLivingBase> list = player.world.getEntitiesWithinAABB(EntityLivingBase.class,
							player.getEntityBoundingBox().offset(vec.x * 2, 0, vec.z * 2).grow(4D), entity -> !player.equals(entity));
					list.forEach(entity -> entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 6));
				}
			}
		}
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer player = ((EntityPlayer) entityLiving);
			int timeUsed = getMaxItemUseDuration(stack) - timeLeft;
			if(timeUsed > 50) {
				timeUsed = 50;
			}
			setShifting(stack, player.isSneaking());
			player.getCooldownTracker().setCooldown(this, timeUsed);
		}
		stack.damageItem(1, entityLiving);
	}

	private static void setShifting(ItemStack itemStack, boolean does) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		if(nbt == null) {
			nbt = new NBTTagCompound();
			itemStack.setTagCompound(nbt);
		}
		nbt.setBoolean("NeedleMode", does);
	}

	private static boolean wasShifting(ItemStack itemStack) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		return nbt != null && nbt.getBoolean("NeedleMode");
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 500;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
