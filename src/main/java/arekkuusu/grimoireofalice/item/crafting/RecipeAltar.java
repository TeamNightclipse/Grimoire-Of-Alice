package arekkuusu.grimoireofalice.item.crafting;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public final class RecipeAltar {

	public static final List<IRecipeItems> recipes = new ArrayList<>();

	public static IRecipeItems registerRecipe(IRecipeItems recipe) {
		recipes.add(recipe);
		return recipe;
	}

	public static IRecipeItems registerRecipe(ItemStack result, Object... inputs){
		return registerRecipe(new RecipeItems(result, inputs));
	}

	public static IRecipeItems registerRecipeNether(ItemStack result, Object... inputs){
		return registerRecipe(new RecipeItemsNether(result, inputs));
	}

	public static IRecipeItems registerRecipeMoonPhase(int moonPhase, ItemStack result, Object... inputs){
		return registerRecipe(new RecipeItemsMoonPhase(moonPhase, result, inputs));
	}

	public static IRecipeItems registerRecipeRain(ItemStack result, Object... inputs){
		return registerRecipe(new RecipeItemsRain(result, inputs));
	}
}
