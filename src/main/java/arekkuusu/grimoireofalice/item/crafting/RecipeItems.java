package arekkuusu.grimoireofalice.item.crafting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RecipeItems implements IRecipeItems {

	private final ItemStack result;
	private final ImmutableList<Object> neededItems;

	RecipeItems(ItemStack result, Object... inputs) {
		this.result = result;

		List<Object> stackedList = Arrays.stream(inputs).map(obj -> {
			if(obj instanceof Item) return new ItemStack((Item)obj);
			else if(obj instanceof Block) return new ItemStack((Block)obj);
			else return obj;
		}).collect(Collectors.toList());

		neededItems = ImmutableList.copyOf(stackedList);
	}

	@Override
	public boolean checkRecipe(List<ItemStack> usedItems, World world) {
		List<Object> toCompare = new ArrayList<>(neededItems);
		if(toCompare.size() != usedItems.size()) return false;

		for(ItemStack stack : usedItems) {
			int index = -1;
			for(int j = 0; j < toCompare.size(); j++) {
				Object obj = toCompare.get(j);
				if(obj instanceof ItemStack && ItemStack.areItemStacksEqual(stack, (ItemStack)obj)) {
					index = j;
					break;
				}
				else if(obj instanceof String) {

				}
			}
			if(index != -1) {
				toCompare.remove(index);
			}
			else return false;
		}
		return toCompare.isEmpty();
	}

	@Override
	public ItemStack getResult() {
		return result;
	}
}
