package arekkuusu.grimoireofalice.plugin.touhou;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SpellCardContainer extends Container{

	public final InventoryPouch inventory;
	
	private static final int INV_START = InventoryPouch.INV_SIZE, INV_END = INV_START+26, HOTBAR_START = INV_END+1, HOTBAR_END = HOTBAR_START+8;

	public SpellCardContainer(EntityPlayer par1Player, InventoryPlayer inventoryPlayer, InventoryPouch inventoryItem) {
		this.inventory = inventoryItem;

		int i;
		
		this.addSlotToContainer(new SlotItemInv(this.inventory, 0, 10, 30));
		this.addSlotToContainer(new SlotItemInv(this.inventory, 1, 30, 30));
		this.addSlotToContainer(new SlotItemInv(this.inventory, 2, 50, 30));
		this.addSlotToContainer(new SlotItemInv(this.inventory, 3, 70, 30));
		this.addSlotToContainer(new SlotItemInv(this.inventory, 4, 90, 30));
		this.addSlotToContainer(new SlotItemInv(this.inventory, 5, 110, 30));
		this.addSlotToContainer(new SlotItemInv(this.inventory, 6, 130, 30));
		this.addSlotToContainer(new SlotItemInv(this.inventory, 7, 150, 30));

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

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int index) {
		ItemStack itemstack = null;
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
				if (index < INV_START) {
					if (!this.mergeItemStack(itemstack1, INV_START, HOTBAR_END+1, true)) {
						return null;
					}
					slot.onSlotChange(itemstack1, itemstack);
				}
				else {
					if (!this.mergeItemStack(itemstack1, 0, InventoryPouch.INV_SIZE, false)) {
						return null;
					}
					slot.onSlotChange(itemstack1, itemstack);
				}

				if (itemstack1.stackSize == 0) {
					slot.putStack(null);
				}
				else {
					slot.onSlotChanged();
				}

				slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
		}

		return itemstack;
	}
	
	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
		ItemStack heldItem = player.getHeldItemMainhand();
		if(heldItem == null) heldItem = player.getHeldItemOffhand();

		if(heldItem == null) return null;
        if (slotId >= 0 && getSlot(slotId).getStack() == heldItem) {
			return null;
        }
        return super.slotClick(slotId, dragType, clickTypeIn, player);
	}
	
	@Override
	protected boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection) {
        boolean flag = false;
        int i = startIndex;

        if (reverseDirection) {
            i = endIndex - 1;
        }

        if (stack.isStackable())  {
            while (stack.stackSize > 0 && (!reverseDirection && i < endIndex || reverseDirection && i >= startIndex)) {
                Slot slot = this.inventorySlots.get(i);
                ItemStack itemstack = slot.getStack();

                if (itemstack != null && areItemStacksEqual(stack, itemstack)) {
                    int j = itemstack.stackSize + stack.stackSize;

                    if (j <= stack.getMaxStackSize()) {
                        stack.stackSize = 0;
                        itemstack.stackSize = j;
                        slot.onSlotChanged();
                        flag = true;
                    }
                    else if (itemstack.stackSize < stack.getMaxStackSize()) {
                        stack.stackSize -= stack.getMaxStackSize() - itemstack.stackSize;
                        itemstack.stackSize = stack.getMaxStackSize();
                        slot.onSlotChanged();
                        flag = true;
                    }
                }

                if (reverseDirection) {
                    --i;
                }
                else {
                    ++i;
                }
            }
        }

        if (stack.stackSize > 0) {
            if (reverseDirection) {
                i = endIndex - 1;
            }
            else  {
                i = startIndex;
            }

            while (!reverseDirection && i < endIndex || reverseDirection && i >= startIndex) {
                Slot slot1 = this.inventorySlots.get(i);
                ItemStack itemstack1 = slot1.getStack();

                if (itemstack1 == null && slot1.isItemValid(stack))  {
                    slot1.putStack(stack.copy());
                    slot1.onSlotChanged();
                    stack.stackSize = 0;
                    flag = true;
                    break;
                }

                if (reverseDirection) {
                    --i;
                }
                else {
                    ++i;
                }
            }
        }
        System.out.println(flag);
        return flag;
	}

    private static boolean areItemStacksEqual(ItemStack stackA, ItemStack stackB)  {
        return stackB.getItem() == stackA.getItem() && (!stackA.getHasSubtypes() || stackA.getMetadata() == stackB.getMetadata()) && ItemStack.areItemStackTagsEqual(stackA, stackB);
    }

}
