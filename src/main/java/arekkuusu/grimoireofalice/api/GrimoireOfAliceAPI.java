package arekkuusu.grimoireofalice.api;

import java.util.ArrayList;
import java.util.List;

import arekkuusu.grimoireofalice.api.recipes.*;
import com.google.common.collect.ImmutableList;

import net.minecraft.item.ItemStack;

public final class GrimoireOfAliceAPI {

	private static final List<IRecipeItems> recipes = new ArrayList<>();

	public static List<IRecipeItems> getRecipes() {
		return ImmutableList.copyOf(recipes);
	}

	public static IRecipeItems registerRecipe(IRecipeItems recipe) {
		recipes.add(recipe);
		return recipe;
	}

	public static IRecipeItems registerRecipe(ItemStack result, Object... inputs) {
		return registerRecipe(new RecipeItems(result, inputs));
	}

	public static IRecipeItems registerRecipeNether(ItemStack result, Object... inputs) {
		return registerRecipe(new RecipeItemsNether(result, inputs));
	}

	public static IRecipeItems registerRecipeEnd(ItemStack result, Object... inputs) {
		return registerRecipe(new RecipeItemsEnd(result, inputs));
	}

	public static IRecipeItems registerRecipeMoonPhase(int moonPhase, ItemStack result, Object... inputs) {
		return registerRecipe(new RecipeItemsMoonPhase(moonPhase, result, inputs));
	}

	public static IRecipeItems registerRecipeRain(ItemStack result, Object... inputs) {
		return registerRecipe(new RecipeItemsRain(result, inputs));
	}

	public static IRecipeItems registerRecipeThunder(ItemStack result, Object... inputs) {
		return registerRecipe(new RecipeItemsThunder(result, inputs));
	}
}
