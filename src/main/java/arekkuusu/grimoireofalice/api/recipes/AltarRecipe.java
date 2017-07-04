package arekkuusu.grimoireofalice.api.recipes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import static net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE;

public class AltarRecipe implements IAltarRecipe {

	private final ItemStack result;
	private final ImmutableList<Object> neededItems;

	public AltarRecipe(ItemStack result, Object... inputs) {
		this.result = result;

		List<Object> stackedList = Arrays.stream(inputs).map(obj -> {
			if(obj instanceof Item) {
				return new ItemStack((Item) obj);
			}
			else if(obj instanceof Block) {
				return new ItemStack((Block) obj);
			}
			else {
				return obj;
			}
		}).collect(Collectors.toList());

		neededItems = ImmutableList.copyOf(stackedList);
	}

	public ImmutableList<Object> getNeededItems() {
		return neededItems;
	}

	@Override
	public boolean checkRecipe(List<ItemStack> usedItems, World world) {
		List<Object> toCompare = new ArrayList<>(neededItems);
		if(toCompare.size() != usedItems.size()) {
			return false;
		}

		for(ItemStack stack : usedItems) {
			int index = -1;
			for(int j = 0; j < toCompare.size(); j++) {
				Object obj = toCompare.get(j);
				if(obj instanceof ItemStack && ItemStack.areItemStacksEqual(stack, (ItemStack) obj)) {
					index = j;
					break;
				}
				else if(obj instanceof String && containsMatch(OreDictionary.getOres((String) obj), stack)) {
					index = j;
					break;
				}
			}
			if(index != -1) {
				toCompare.remove(index);
			}
			else {
				return false;
			}
		}
		return toCompare.isEmpty();
	}

	private static boolean containsMatch(List<ItemStack> inputs, ItemStack target) {
		for(ItemStack input : inputs) {
			if(itemMatches(target, input)) {
				return true;
			}
		}
		return false;
	}

	private static boolean itemMatches(ItemStack target, ItemStack input) {
		return !input.isEmpty() && !target.isEmpty() && target.getItem() == input.getItem() && (target.getItemDamage() == input.getItemDamage()
				|| input.getItemDamage() == WILDCARD_VALUE);
	}

	@Override
	public ItemStack getResult() {
		return result;
	}
}