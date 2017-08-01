/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.plugin.danmakucore.item;

import java.util.List;

import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import arekkuusu.grimoireofalice.common.plugin.danmakucore.entity.EntityCameraSquare;
import arekkuusu.grimoireofalice.common.item.ItemMod;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ItemCamera extends ItemMod {

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
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean advanced) {
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.tengu_camera_header.name"));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if(!world.isRemote) {
			int size = getSize();

			EntityCameraSquare camera = new EntityCameraSquare(world, player, size);
			Vec3d look = player.getLookVec();
			double distance = size + 4D;
			double dx = player.posX + look.x * distance;
			double dy = player.posY + 2 + look.y * distance;
			double dz = player.posZ + look.z * distance;
			camera.setPosition(dx, dy, dz);
			world.spawnEntity(camera);
		}
		world.playSound(player, player.getPosition(), GrimoireSoundEvents.CAMERA_BEEP, SoundCategory.PLAYERS, 1F, 1F);
		player.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		player.motionX = 0;
		player.motionY = 0;
		player.motionZ = 0;
		if(player instanceof EntityPlayerMP) {
			EntityPlayerMP playerMP = (EntityPlayerMP) player;
			playerMP.setPositionAndUpdate(playerMP.prevPosX, playerMP.posY, playerMP.prevPosZ);
		}
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
