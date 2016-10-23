package arekkuusu.grimoireofalice.item.crafting;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public final class RecipeAltar {

	public static final List<IRecipeItems> recipes = new ArrayList<>();

	public static RecipeItems registerRecipe(ItemStack result, Object... inputs){
		RecipeItems recipe = new RecipeItems(result, inputs);
		recipes.add(recipe);
		return recipe;
	}

	public static RecipeItemsNether registerRecipeNether(ItemStack result, Object... inputs){
		RecipeItemsNether recipe = new RecipeItemsNether(result, inputs);
		recipes.add(recipe);
		return recipe;
	}

	public static RecipeItemsMoonPhase registerRecipeMoonPhase(int moonPhase, ItemStack result, Object... inputs){
		RecipeItemsMoonPhase recipe = new RecipeItemsMoonPhase(moonPhase, result, inputs);
		recipes.add(recipe);
		return recipe;
	}

	public static RecipeItemsRain registerRecipeRain(ItemStack result, Object... inputs){
		RecipeItemsRain recipe = new RecipeItemsRain(result, inputs);
		recipes.add(recipe);
		return recipe;
	}
}
