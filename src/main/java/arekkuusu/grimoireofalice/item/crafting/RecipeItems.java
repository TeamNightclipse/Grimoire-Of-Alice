package arekkuusu.grimoireofalice.item.crafting;

import com.google.common.collect.ImmutableList;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class RecipeItems {

	private final ItemStack result;
	private final ImmutableList<ItemStack> neededItems;

	RecipeItems(ItemStack result, ItemStack... inputs) {
		this.result = result;
		ImmutableList.Builder<ItemStack> inputsToSet = ImmutableList.builder();
		for (ItemStack obj : inputs) {
			inputsToSet.add(obj);
		}

		this.neededItems = inputsToSet.build();
	}

	public boolean checkRecipe(ArrayList<ItemStack> usedItems) {
		ArrayList<ItemStack> toCompare = new ArrayList<>(neededItems);
		if (toCompare.size() != usedItems.size()) return false;
		for (int i = 0; i < usedItems.size(); i++) {
			if (!areEqual(toCompare.get(i), usedItems.get(i))) return false;
		}
		return true;
	}

	private boolean areEqual(ItemStack one, ItemStack two){
		return one.getItem() == two.getItem() && one.getItemDamage() == two.getItemDamage();
	}

	public ItemStack getResult() {
		return result;
	}
}
