package arekkuusu.grimoireofalice.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class HatContainer extends Container {

	private final ItemStack stack;

	public HatContainer(InventoryPlayer playerInv, ItemStack hatStack) {
		IItemHandler hatStackCapability = hatStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		stack = hatStack;

		for(int i = 0; i < 3; i++) {
			addSlotToContainer(new SlotItemHandler(hatStackCapability, i, 60 + i * 20, 30));
		}

		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 9; ++j) {
				addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for(int i = 0; i < 9; ++i) {
			if(playerInv.getStackInSlot(i) == hatStack) {
				addSlotToContainer(new SlotLocked(playerInv, i, 8 + i * 18, 142));
			}
			else {
				addSlotToContainer(new Slot(playerInv, i, 8 + i * 18, 142));
			}
		}
	}

	private static class SlotLocked extends Slot {

		SlotLocked(IInventory inv, int index, int xPos, int yPos) {
			super(inv, index, xPos, yPos);
		}

		@Override
		public boolean canTakeStack(EntityPlayer player) {
			return false;
		}

		@Override
		public boolean isItemValid(ItemStack stack) {
			return false;
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return player.getHeldItemMainhand() == stack || player.getHeldItemOffhand() == stack;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(slotIndex);

		if(slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if(slotIndex < 3) {
				if(!mergeItemStack(itemstack1, 3, 38, true)) {
					return ItemStack.EMPTY;
				}
				else {
					slot.onSlotChange(itemstack1, itemstack);
				}
			}
			else {
				if(!mergeItemStack(itemstack1, 0, 3, false)) {
					return ItemStack.EMPTY;
				}
				slot.onSlotChange(itemstack1, itemstack);
			}

			if(itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			}
			else {
				slot.onSlotChanged();
			}

			if(itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(player, itemstack1);
		}

		return itemstack;
	}
}