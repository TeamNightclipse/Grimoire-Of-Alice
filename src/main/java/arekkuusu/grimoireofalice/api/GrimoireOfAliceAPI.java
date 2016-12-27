package arekkuusu.grimoireofalice.api;

import arekkuusu.grimoireofalice.api.recipes.*;
import com.google.common.collect.ImmutableList;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public final class GrimoireOfAliceAPI {

	private static final List<IRecipeItems> recipes = new ArrayList<>();

	//Don't touch them or I will honk you, strange person in the internet!
	private static final List<ItemStack> flyItems = new ArrayList<>();
	private static final List<ItemStack> flyArmor = new ArrayList<>();

	public static List<IRecipeItems> getRecipes() {
		return ImmutableList.copyOf(recipes);
	}

	public static List<ItemStack> getFlyItems() { return flyItems;}
	public static List<ItemStack> getFlyArmor() { return flyArmor;}

	/**
	 * Adds an IRecipeItems to the ArrayList.
	 *
	 * @param recipe The Recipe
	 * @return The Registered IRecipeItems
	 */
	public static IRecipeItems registerRecipe(IRecipeItems recipe) {
		recipes.add(recipe);
		return recipe;
	}

	/**
	 * Registers a Recipe to the Crafting Altar.
	 *
	 * @param result The ItemStack Output of the recipe
	 * @param inputs The Inputs of the recipe, no longer than 16. Can be an Item, ItemStack, or an OreDictionary name.
	 * @return The Registered Recipe
	 */
	public static IRecipeItems registerRecipe(ItemStack result, Object... inputs) {
		return registerRecipe(new RecipeItems(result, inputs));
	}

	/**
	 * Registers a Recipe that can only be crafted in a given Dimension id.
	 *
	 * @param result The ItemStack Output of the recipe
	 * @param inputs The Inputs of the recipe, no longer than 16. Can be an Item, ItemStack, or an OreDictionary name.
	 * @return The Registered Recipe
	 */
	public static IRecipeItems registerRecipeDimension(int dimId, ItemStack result, Object... inputs) {
		return registerRecipe(new RecipeItemsDimension(dimId, result, inputs));
	}

	/**
	 * Registers a Recipe that can only be crafted during a given Moon Phase at Night.
	 * Available Moon Phases are:
	 * - Full Moon : 1
	 * - Waning Gibbous : 2
	 * - Last Quarter : 3
	 * - Waning Crescent : 4
	 * - New Moon : 5
	 * - Waxing Crescent : 6
	 * - First Quarter : 7
	 * - Waxing Gibbous : 8
	 *
	 * @param result The ItemStack Output of the recipe
	 * @param inputs The Inputs of the recipe, no longer than 16. Can be an Item, ItemStack, or an OreDictionary name.
	 * @return The Registered Recipe
	 */
	public static IRecipeItems registerRecipeMoonPhase(int moonPhase, ItemStack result, Object... inputs) {
		return registerRecipe(new RecipeItemsMoonPhase(moonPhase, result, inputs));
	}

	/**
	 * Registers a Recipe that can only be crafted when Raining.
	 *
	 * @param result The ItemStack Output of the recipe
	 * @param inputs The Inputs of the recipe, no longer than 16. Can be an Item, ItemStack, or an OreDictionary name.
	 * @return The Registered Recipe
	 */
	public static IRecipeItems registerRecipeRain(ItemStack result, Object... inputs) {
		return registerRecipe(new RecipeItemsRain(result, inputs));
	}

	/**
	 * Registers a Recipe that can only be crafted during a Thunder Storm.
	 *
	 * @param result The ItemStack Output of the recipe
	 * @param inputs The Inputs of the recipe, no longer than 16. Can be an Item, ItemStack, or an OreDictionary name.
	 * @return The Registered Recipe
	 */
	public static IRecipeItems registerRecipeThunder(ItemStack result, Object... inputs) {
		return registerRecipe(new RecipeItemsThunder(result, inputs));
	}

	/**
	 * Register an Item that will allow the player to fly.
	 *
	 * @param item The ItemStack of the Item
	 */
	public static void registerFlyItem(ItemStack item){
		flyItems.add(item);
	}

	/**
	 * Registers an Armor that will allow the player to fly.
	 *
	 * @param item The ItemStack of the Item
	 */
	public static void registerFlyArmor(ItemStack item){
		flyArmor.add(item);
	}
}
