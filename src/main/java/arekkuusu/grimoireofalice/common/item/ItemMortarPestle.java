/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.minecraft.item.ItemStack;

public class ItemMortarPestle extends ItemBase {

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
		if(dmg == stack.getMaxDamage()) {
			return new ItemStack(stack.getItem(), 0, stack.getMaxDamage());
		}
		ItemStack itemStack = stack.copy();
		itemStack.setItemDamage(dmg + 1);
		return itemStack;
	}
}
