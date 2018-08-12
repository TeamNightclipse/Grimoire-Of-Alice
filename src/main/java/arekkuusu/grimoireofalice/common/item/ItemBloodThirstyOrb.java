/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.lib.LibName;
import net.katsstuff.teamnightclipse.mirror.data.Vector3;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemBloodThirstyOrb extends ItemBase  {

	public ItemBloodThirstyOrb() {
		super(LibName.BLOOD_THIRSTY_ORB);
		setMaxStackSize(1);
		setMaxDamage(5);
		setNoRepair();
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		player.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) { //Recycled code...
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			if(player.isSneaking()) {
				moveToClosestPlayer(world, player);
			}
			else {
				moveToMob(player);
			}
			stack.damageItem(1, player);
			player.attackEntityFrom(DamageSource.GENERIC, 1);
		}
	}

	private static void moveToClosestPlayer(World world, EntityPlayer player) {
		EntityPlayer closest = world.getClosestPlayerToEntity(player, 30D);
		if(closest != null) {
			if(player instanceof EntityPlayerMP) {
				player.setPositionAndUpdate(closest.posX, closest.posY, closest.posZ);
			}
			player.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1F, itemRand.nextFloat() * 0.4F + 0.8F);
		}
	}

	private static void moveToMob(EntityPlayer player) {
		Vector3.getEntityLookedAt(player, foundEntity -> !player.equals(foundEntity), 32D).ifPresent(entity -> {
			player.setPosition(entity.posX, entity.posY, entity.posZ);
			player.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1F, itemRand.nextFloat() * 0.4F + 0.8F);
		});
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
