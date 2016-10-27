/*
 * This class was created by <Katrix>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.plugin.danmakucore.item;

import arekkuusu.grimoireofalice.item.ItemMod;
import arekkuusu.grimoireofalice.plugin.danmakucore.LibGOAShotData;
import net.katsstuff.danmakucore.entity.danmaku.DanmakuBuilder;
import net.katsstuff.danmakucore.entity.danmaku.EntityDanmaku;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ItemInstrument extends ItemMod {

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
		if(player instanceof EntityPlayer) {
			if(!player.worldObj.isRemote) {
				EntityDanmaku danmaku = DanmakuBuilder.builder()
						.setUser(player)
						.setMovementData(1.5D)
						.setShot(LibGOAShotData.NOTE)
						.build().asEntity();
				player.worldObj.spawnEntityInWorld(danmaku);
			}
		}
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		EntityPlayer playerIn = (EntityPlayer)entityLiving;
		if(!playerIn.capabilities.isCreativeMode) {
			int hurr = (getMaxItemUseDuration(stack) - timeLeft) / 2;
			stack.damageItem(hurr, playerIn);
			playerIn.getCooldownTracker().setCooldown(this, 50);
		}
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
	public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2) {
		return false;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
