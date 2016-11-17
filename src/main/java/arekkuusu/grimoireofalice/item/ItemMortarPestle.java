package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.item.ItemStack;

public class ItemMortarPestle extends ItemMod {

	public ItemMortarPestle() {
		super(LibItemName.MORTAR_AND_PESTLE);
		setMaxDamage(50);
		setMaxStackSize(1);
		setContainerItem(this);
		setNoRepair();
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean hasContainerItem() {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		int dmg = stack.getItemDamage();
		if (dmg == stack.getMaxDamage()) {
			return new ItemStack(stack.getItem(), 0, stack.getMaxDamage());
		}
		ItemStack itemStack = stack.copy();
		itemStack.setItemDamage(dmg + 1);
		return itemStack;
	}
}
