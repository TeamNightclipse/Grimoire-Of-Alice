package arekkuusu.grimoireofalice.item.crafting;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public final class RecipeAltar {

	public static final List<RecipeItems> recipes = new ArrayList<>();

	public static RecipeItems registerRecipe(ItemStack result, ItemStack... inputs){
		RecipeItems recipe = new RecipeItems(result, inputs);
		recipes.add(recipe);
		return recipe;
	}
}
