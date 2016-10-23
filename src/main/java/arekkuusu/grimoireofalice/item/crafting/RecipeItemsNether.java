package arekkuusu.grimoireofalice.item.crafting;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class RecipeItemsNether extends RecipeItems {

	RecipeItemsNether(ItemStack result, Object... inputs) {
		super(result, inputs);
	}

	@Override
	public boolean checkRecipe(List<ItemStack> usedItems, World world) {
		return world.provider.doesWaterVaporize() && super.checkRecipe(usedItems, world);
	}
}
