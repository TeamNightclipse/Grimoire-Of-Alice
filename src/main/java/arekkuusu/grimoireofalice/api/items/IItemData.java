package arekkuusu.grimoireofalice.api.items;

import net.minecraft.item.ItemStack;

/**
 * Interface used to access Data in an Items NBT that implements it
 */
public interface IItemData {
	/**
	 * Sets the amount
	 *
	 * @param itemStack ItemStack to modify
	 * @param amount    Amount to set to NBT
	 */
	void setData(ItemStack itemStack, int amount);

	/**
	 * Return the amount stored in the NBT
	 *
	 * @param itemStack ItemStack to access
	 * @return
	 */
	int getData(ItemStack itemStack);
}
