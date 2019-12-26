/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.lib.LibName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ItemGhastlySendOffLantern extends ItemBase {

	private static final String TAG = "timer";

	public ItemGhastlySendOffLantern() {
		super(LibName.SEND_OFF_LANTERN);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return getTimer(stack) <= 0 ? EnumRarity.RARE : EnumRarity.COMMON;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entityIn, int itemSlot, boolean isSelected) {
		int timer = getTimer(stack);
		if(timer > 0) {
			setTimer(stack, timer - 1);
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if(getTimer(stack) > 0) {
			return new ActionResult<>(EnumActionResult.FAIL, stack);
		}
		player.getCooldownTracker().setCooldown(this, 50);
		setAllIventory(player, 600);
		if(!player.capabilities.isCreativeMode) {
			stack.shrink(1);
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@SuppressWarnings("ConstantConditions")
	private void setAllIventory(EntityPlayer player, int time) {
		if(player.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)) {
			IItemHandler capability = player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

			for(int i = 0; i < capability.getSlots(); i++) {
				ItemStack stack = capability.getStackInSlot(i);
				if(!stack.isEmpty() && stack.getItem() == this) {
					setTimer(stack, time);
				}
			}
		}
	}

	private static void setTimer(ItemStack stack, int time) {
		getNBT(stack).setInteger(TAG, time);
	}

	private static int getTimer(ItemStack stack) {
		return getNBT(stack).getInteger(TAG);
	}

	private static NBTTagCompound getNBT(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		if(tag == null) {
			tag = new NBTTagCompound();
			stack.setTagCompound(tag);
		}
		return tag;
	}
}
