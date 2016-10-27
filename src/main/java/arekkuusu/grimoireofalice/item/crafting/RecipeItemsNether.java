package arekkuusu.grimoireofalice.item.crafting;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;

public class RecipeItemsNether extends RecipeItems {

	RecipeItemsNether(ItemStack result, Object... inputs) {
		super(result, inputs);
	}

	@Override
	public boolean checkRecipe(List<ItemStack> usedItems, World world) {
		return world.provider.getDimensionType() == DimensionType.NETHER && super.checkRecipe(usedItems, world);
	}
}
