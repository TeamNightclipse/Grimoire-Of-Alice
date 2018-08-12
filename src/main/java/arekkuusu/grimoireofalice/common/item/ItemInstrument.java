/*
 * This class was created by <Katrix>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import net.katsstuff.teamnightclipse.danmakucore.danmaku.DanmakuTemplate;
import net.katsstuff.teamnightclipse.danmakucore.javastuff.DanmakuCreationHelper;
import net.katsstuff.teamnightclipse.danmakucore.lib.LibColor;
import net.katsstuff.teamnightclipse.danmakucore.lib.data.LibShotData;
import net.katsstuff.teamnightclipse.mirror.data.Quat;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;

public abstract class ItemInstrument extends ItemBase {

	private static final int[] COLORS = {
			LibColor.COLOR_SATURATED_GREEN,
			LibColor.COLOR_SATURATED_YELLOW,
			LibColor.COLOR_SATURATED_RED,
			LibColor.COLOR_SATURATED_BLUE,
			LibColor.COLOR_SATURATED_CYAN
	};

	public ItemInstrument(String id) {
		super(id);
		setMaxStackSize(1);
		addPropertyOverride(new ResourceLocation("playing"),
				(stack, world, entity) -> entity != null && entity.isHandActive() && entity.getActiveItemStack() == stack ? 1F : 0F);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		player.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		if(player instanceof EntityPlayer && count % 2 == 0) {
			player.world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_NOTE_HARP, SoundCategory.RECORDS, 0.5F, 1F);
			if(!player.world.isRemote) {
				int color = COLORS[itemRand.nextInt(COLORS.length)];
				DanmakuTemplate danmaku = DanmakuTemplate.builder()
						.setUser(player)
						.setMovementData(getVelocity())
						.setShot(LibShotData.SHOT_NOTE1.setMainColor(color))
						.build();
				DanmakuCreationHelper.createRandomRingShot(Quat.orientationOf(player), danmaku, 1, getSize(), getDistance());
			}
		}
		if(count % 10 == 0) {
			stack.damageItem(1, player);
		}
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
		EntityPlayer player = (EntityPlayer) entityLiving;
		if(!player.capabilities.isCreativeMode) {
			player.getCooldownTracker().setCooldown(this, 15);
		}
	}

	public abstract double getVelocity();

	public abstract float getSize();

	public abstract double getDistance();

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
