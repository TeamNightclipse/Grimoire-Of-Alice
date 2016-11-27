package arekkuusu.grimoireofalice.api.items;

import net.minecraft.item.ItemStack;

public interface IItemData {
	void setData(ItemStack itemStack, int amount);
	int getData(ItemStack itemStack);
}
