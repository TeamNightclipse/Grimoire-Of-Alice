package arekkuusu.grimoireofalice.item.crafting;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class RecipeItemsRain extends RecipeItems {

	RecipeItemsRain(ItemStack result, Object... inputs) {
		super(result, inputs);
	}

	@Override
	public boolean checkRecipe(List<ItemStack> usedItems, World world) {
		return (world.isThundering() || world.isRaining()) && super.checkRecipe(usedItems, world);
	}
}
