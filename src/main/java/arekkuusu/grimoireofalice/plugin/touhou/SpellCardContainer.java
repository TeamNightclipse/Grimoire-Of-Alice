package arekkuusu.grimoireofalice.plugin.touhou;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SpellCardContainer extends Container {

	private final ItemStack stack;

    public SpellCardContainer (InventoryPlayer playerInv, ItemStack pouchStack) {
		IItemHandler pouchInv = pouchStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		stack = pouchStack;

        addSlotToContainer(new SlotItemHandler(pouchInv, 0, 10, 30));
        addSlotToContainer(new SlotItemHandler(pouchInv, 1, 30, 30));
        addSlotToContainer(new SlotItemHandler(pouchInv, 2, 50, 30));
        addSlotToContainer(new SlotItemHandler(pouchInv, 3, 70, 30));
        addSlotToContainer(new SlotItemHandler(pouchInv, 4, 90, 30));
        addSlotToContainer(new SlotItemHandler(pouchInv, 5, 110, 30));
        addSlotToContainer(new SlotItemHandler(pouchInv, 6, 130, 30));
        addSlotToContainer(new SlotItemHandler(pouchInv, 7, 150, 30));

        int i;
        int j;

        for(i = 0; i < 3; ++i)
            for(j = 0; j < 9; ++j)
                addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));

        for(i = 0; i < 9; ++i) {
            if(playerInv.getStackInSlot(i) == pouchStack)
                addSlotToContainer(new SlotLocked(playerInv, i, 8 + i * 18, 142));
            else addSlotToContainer(new Slot(playerInv, i, 8 + i * 18, 142));
        }
    }

    private class SlotLocked extends Slot {
        SlotLocked(IInventory inv, int index, int xPos, int yPos) {
            super(inv, index, xPos, yPos);
        }

        @Override
        public boolean canTakeStack(EntityPlayer player) {
            return false;
        }

        @Override
        public boolean isItemValid(@Nullable ItemStack stack) {
            return false;
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return playerIn.getHeldItemMainhand() == stack
                || playerIn.getHeldItemOffhand() == stack;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        ItemStack itemstack = null;
        Slot slot = inventorySlots.get(slotIndex);

        if(slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if(slotIndex < 8) {
                if(!mergeItemStack(itemstack1, 8, 52, true))
                    return null;
            } else {
                int i = itemstack.getItemDamage();
                if(i < 8) {
                    Slot slot1 = inventorySlots.get(i);
                    if(slot1.isItemValid(itemstack) && !mergeItemStack(itemstack1, i, i + 1, true))
                        return null;
                }
            }

            if(itemstack1.stackSize == 0)
                slot.putStack(null);
            else slot.onSlotChanged();

            if(itemstack1.stackSize == itemstack.stackSize)
                return null;

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }
}
