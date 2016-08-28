package arekkuusu.grimoireOfAlice.plugin.touhou;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
//import thKaguyaMod.item.ItemTHSpellCard;

public class SlotItemInv extends Slot {
	
	public SlotItemInv(IInventory inv, int index, int xPos, int yPos) {
		super(inv, index, xPos, yPos);
	}
	
	@Override
	public boolean isItemValid(ItemStack itemstack) {
		return !(itemstack.getItem() instanceof ItemSpellCardPouch);
	}
}
