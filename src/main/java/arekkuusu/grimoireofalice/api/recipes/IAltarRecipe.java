package arekkuusu.grimoireofalice.api.recipes;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Interface for Altar Recipes
 */
public interface IAltarRecipe {

	/**
	 * Checks the inputs match the recipe
	 *
	 * @param usedItems List of inputs
	 * @param world     The world
	 * @return If the inputs match the recipe
	 */
	boolean checkRecipe(List<ItemStack> usedItems, World world);

	/**
	 * Returns the output of the recipe
	 *
	 * @return An Itemstack
	 */
	ItemStack getResult();
}
