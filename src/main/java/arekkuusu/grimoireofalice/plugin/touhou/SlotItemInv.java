package arekkuusu.grimoireofalice.plugin.touhou;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;

public class SlotItemInv extends Slot {
	
	public SlotItemInv(IInventory inv, int index, int xPos, int yPos) {
		super(inv, index, xPos, yPos);
	}
	
	@Override
	public boolean isItemValid(ItemStack itemstack) {
		return !(itemstack.getItem() instanceof ItemSpellCardPouch) && (itemstack.getItem() instanceof ItemArrow);
	}
}
