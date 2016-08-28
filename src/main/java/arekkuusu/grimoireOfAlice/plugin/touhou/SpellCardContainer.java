package arekkuusu.grimoireOfAlice.plugin.touhou;

import arekkuusu.grimoireOfAlice.item.Item3rdEye;
import arekkuusu.grimoireOfAlice.item.masks.ItemMask;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SpellCardContainer extends Container{

	public final InventoryPouch inventory;
	
	private static final int INV_START = InventoryPouch.INV_SIZE, INV_END = INV_START+26, HOTBAR_START = INV_END+1, HOTBAR_END = HOTBAR_START+8;

	public SpellCardContainer(EntityPlayer par1Player, InventoryPlayer inventoryPlayer, InventoryPouch inventoryItem) {
		this.inventory = inventoryItem;

		int i;
		
		this.addSlotToContainer(new SlotItemInv(this.inventory, 6, 10, 30));
		this.addSlotToContainer(new SlotItemInv(this.inventory, 6, 30, 30));
		this.addSlotToContainer(new SlotItemInv(this.inventory, 6, 50, 30));
		this.addSlotToContainer(new SlotItemInv(this.inventory, 6, 70, 30));
		this.addSlotToContainer(new SlotItemInv(this.inventory, 6, 90, 30));
		this.addSlotToContainer(new SlotItemInv(this.inventory, 6, 110, 30));
		this.addSlotToContainer(new SlotItemInv(this.inventory, 6, 130, 30));
		this.addSlotToContainer(new SlotItemInv(this.inventory, 6, 150, 30));

		for (i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		for (i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return inventory.isUseableByPlayer(entityplayer);
	}

	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int index) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index < INV_START) {
				if (itemstack1.getItem() instanceof Item3rdEye) {
					if (!this.mergeItemStack(itemstack1, INV_START, HOTBAR_END+1, true)) {
						return null;
					}
				}
				slot.onSlotChange(itemstack1, itemstack);
			}
			else {
				if (itemstack1.getItem() instanceof Item3rdEye) {
					if (!this.mergeItemStack(itemstack1, 0, InventoryPouch.INV_SIZE, false)) {
						return null;
					}
				}
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			}
			else {
				slot.onSlotChanged();
			}

			slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
		}

		return itemstack;
	}
	
	@Override
	public ItemStack slotClick(int slot, int button, int flag, EntityPlayer player) {
		if (slot >= 0 && getSlot(slot) != null && getSlot(slot).getStack() == player.getHeldItem()) {
			return null;
		}
		return super.slotClick(slot, button, flag, player);
	}
	
	@Override
	protected boolean mergeItemStack(ItemStack stack, int start, int end, boolean backwards) {
		boolean flag1 = false;
		int k = (backwards ? end - 1 : start);
		Slot slot;
		ItemStack itemstack1;

		if (stack.isStackable()) {
			while (stack.stackSize > 0 && (!backwards && k < end || backwards && k >= start)) {
				slot = (Slot) inventorySlots.get(k);
				itemstack1 = slot.getStack();

				if (!slot.isItemValid(stack)) {
					k += (backwards ? -1 : 1);
					continue;
				}

				if (itemstack1 != null && itemstack1.getItem() == stack.getItem() && (!stack.getHasSubtypes() || stack.getItemDamage() == itemstack1.getItemDamage()) && ItemStack.areItemStackTagsEqual(stack, itemstack1)) {
					int l = itemstack1.stackSize + stack.stackSize;

					if (l <= stack.getMaxStackSize() && l <= slot.getSlotStackLimit()) {
						stack.stackSize = 0;
						itemstack1.stackSize = l;
						inventory.markDirty();
						flag1 = true;
					}
					else if (itemstack1.stackSize < stack.getMaxStackSize() && l < slot.getSlotStackLimit()) {
						stack.stackSize -= stack.getMaxStackSize() - itemstack1.stackSize;
						itemstack1.stackSize = stack.getMaxStackSize();
						flag1 = true;
					}
				}

				k += (backwards ? -1 : 1);
			}
		}
		if (stack.stackSize > 0) {
			k = (backwards ? end - 1 : start);
			while (!backwards && k < end || backwards && k >= start) {
				slot = (Slot) inventorySlots.get(k);
				itemstack1 = slot.getStack();

				if (!slot.isItemValid(stack)) {
					k += (backwards ? -1 : 1);
					continue;
				}

				if (itemstack1 == null) {
					int l = stack.stackSize;
					if (l <= slot.getSlotStackLimit()) {
						slot.putStack(stack.copy());
						stack.stackSize = 0;
						inventory.markDirty();
						flag1 = true;
						break;
					}
					else {
						putStackInSlot(k, new ItemStack(stack.getItem(), slot.getSlotStackLimit(), stack.getItemDamage()));
						stack.stackSize -= slot.getSlotStackLimit();
						inventory.markDirty();
						flag1 = true;
					}
				}

				k += (backwards ? -1 : 1);
			}
		}

		return flag1;
	}

}
