package arekkuusu.grimoireofalice.plugin.touhou;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

public class InventoryPouch implements IItemHandlerModifiable {

    private final IItemHandlerModifiable pouchInv;
    public ItemStack pouch;

    public InventoryPouch(ItemStack pouch) {
        this.pouch = pouch;
        this.pouchInv = (IItemHandlerModifiable) pouch.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
    }

    @Override
    public void setStackInSlot(int slot, ItemStack stack) {
        pouchInv.setStackInSlot(slot, stack);
    }

    @Override
    public int getSlots() {
        return pouchInv.getSlots();
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return pouchInv.getStackInSlot(slot);
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        return pouchInv.insertItem(slot, stack, simulate);
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return pouchInv.extractItem(slot, amount, simulate);
    }
}
