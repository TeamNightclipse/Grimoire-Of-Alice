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
import arekkuusu.grimoireofalice.common.entity.EntityCameraSquare;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class ItemCamera extends ItemBase {

	public ItemCamera(String id) {
		super(id);
		setMaxDamage(180);
		setMaxStackSize(1);
		addPropertyOverride(new ResourceLocation("takingPhoto"),
				(stack, world, entity) -> entity != null && entity.isHandActive() && stack.isItemEqual(entity.getActiveItemStack()) ? 1F : 0F);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if(!world.isRemote) {
			EntityCameraSquare camera = new EntityCameraSquare(world, player, getSize(), getAngle());
			camera.setPosition();
			world.spawnEntity(camera);
		}
		world.playSound(player, player.getPosition(), GrimoireSoundEvents.CAMERA_BEEP, SoundCategory.PLAYERS, 1F, 1F);
		player.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			if(!player.capabilities.isCreativeMode) {
				stack.damageItem(1, player);
			}
			world.playSound(player, player.getPosition(), GrimoireSoundEvents.CAMERA_SHOOT, SoundCategory.PLAYERS, 1F, 1F);
			player.getCooldownTracker().setCooldown(this, 100);
		}
	}

	public abstract int getSize();

	public abstract float getAngle();

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 7000;
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return false;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.NONE;
	}
}
