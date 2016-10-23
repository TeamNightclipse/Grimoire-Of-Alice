package arekkuusu.grimoireofalice.item.crafting;

import com.google.common.collect.ImmutableList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class RecipeItems implements IRecipeItems {

	private final ItemStack result;
	private final ImmutableList<Object> neededItems;

	RecipeItems(ItemStack result, Object... inputs) {
		this.result = result;
		ImmutableList.Builder<Object> inputsToSet = ImmutableList.builder();
		for (Object obj : inputs) {
			inputsToSet.add(obj);
		}

		this.neededItems = inputsToSet.build();
	}

	public boolean checkRecipe(List<ItemStack> usedItems, World world) {
		List<Object> toCompare = new ArrayList<>(neededItems);
		if (toCompare.size() != usedItems.size()) return false;
		for (ItemStack stack : usedItems) {
			int index = -1;
			for (int j = 0; j < toCompare.size(); j++) {
				Object obj = toCompare.get(j);
				if (obj instanceof Item
						&& areEqual(stack, (Item)obj)) {
					index = j;
					break;
				} else if (obj instanceof ItemStack
						&& areEqual(stack, (ItemStack)obj)) {
					index = j;
					break;
				}
			}
			if (index != -1) {
				toCompare.remove(index);
			} else return false;
		}
		return toCompare.isEmpty();
	}

	private boolean areEqual(ItemStack one, Item two){
		return one.getItem() == two;
	}

	private boolean areEqual(ItemStack one, ItemStack two){
		return one.getItem() == two.getItem() && one.getItemDamage() == two.getItemDamage();
	}

	public ItemStack getResult() {
		return result;
	}

	public ArrayList<Object> getNeededItems() {
		return new ArrayList<>(neededItems);
	}
}
