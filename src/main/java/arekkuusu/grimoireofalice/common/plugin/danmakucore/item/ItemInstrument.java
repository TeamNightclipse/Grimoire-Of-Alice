/*
 * This class was created by <Katrix>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.plugin.danmakucore.item;

import arekkuusu.grimoireofalice.common.item.ItemMod;
import arekkuusu.grimoireofalice.common.plugin.danmakucore.LibGOAShotData;
import net.katsstuff.danmakucore.entity.danmaku.DanmakuBuilder;
import net.katsstuff.danmakucore.entity.danmaku.EntityDanmaku;
import net.katsstuff.danmakucore.helper.DanmakuCreationHelper;
import net.katsstuff.danmakucore.lib.LibColor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class ItemInstrument extends ItemMod {

	private static final int[] COLORS = {
			LibColor.COLOR_SATURATED_GREEN,
			LibColor.COLOR_SATURATED_YELLOW,
			LibColor.COLOR_SATURATED_RED,
			LibColor.COLOR_SATURATED_BLUE,
			LibColor.COLOR_SATURATED_CYAN
	};

	public ItemInstrument(String id) {
		super(id);
		setMaxDamage(500);
		setMaxStackSize(1);
		addPropertyOverride(new ResourceLocation("playing"),
				(stack, world, entity) -> entity != null && entity.isHandActive() && entity.getActiveItemStack() == stack ? 1F : 0F);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		if (player instanceof EntityPlayer && count % 2 == 0) {
			player.worldObj.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_NOTE_HARP, SoundCategory.RECORDS, 0.5F, 1F);
			if (!player.worldObj.isRemote) {
				int color = COLORS[itemRand.nextInt(COLORS.length)];

				DanmakuBuilder danmaku = DanmakuBuilder.builder()
						.setUser(player)
						.setMovementData(getVelocity())
						.setShot(LibGOAShotData.NOTE.setColor(color))
						.build();
				DanmakuCreationHelper.createRandomRingShot(danmaku, 1, getSize(), getDistance());
			}
		}
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		EntityPlayer playerIn = (EntityPlayer) entityLiving;
		if (!playerIn.capabilities.isCreativeMode) {
			int hurr = (getMaxItemUseDuration(stack) - timeLeft) / 2;
			stack.damageItem(hurr, playerIn);
			playerIn.getCooldownTracker().setCooldown(this, 50);
		}
	}

	public double getVelocity() {
		return 0.1D;
	}

	public float getSize() {
		return 1F;
	}

	public double getDistance() {
		return 1D;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.NONE;
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
