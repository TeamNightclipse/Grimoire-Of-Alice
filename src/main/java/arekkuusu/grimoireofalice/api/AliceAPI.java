package arekkuusu.grimoireofalice.api;

import arekkuusu.grimoireofalice.api.recipes.*;
import com.google.common.collect.ImmutableList;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class AliceAPI {

	private static final List<IAltarRecipe> ALTAR_RECIPES = new ArrayList<>();
	private static final List<ItemStack> FLY_ITEMS = new ArrayList<>();
	private static final List<ItemStack> FLY_ARMOR = new ArrayList<>();

	public static List<IAltarRecipe> getAltarRecipes() {
		return ImmutableList.copyOf(ALTAR_RECIPES);
	}

	public static Optional<IAltarRecipe> findAltarRecipeMatch(List<ItemStack> stacks, World world) {
		return ALTAR_RECIPES.stream().filter(recipe -> recipe.checkRecipe(stacks, world)).findAny();
	}

	public static List<ItemStack> getFlyingItems() {
		return ImmutableList.copyOf(FLY_ITEMS);
	}

	public static List<ItemStack> getFlyingArmor() {
		return ImmutableList.copyOf(FLY_ARMOR);
	}

	/**
	 * Adds an IAltarRecipe to the ArrayList.
	 *
	 * @param recipe The Recipe
	 */
	public static void registerAltarRecipe(IAltarRecipe recipe) {
		ALTAR_RECIPES.add(recipe);
	}

	/**
	 * Registers a Recipe to the Crafting Altar.
	 *
	 * @param result The ItemStack Output of the recipe
	 * @param inputs The Inputs of the recipe, no longer than 16. Can be an Item, ItemStack, or an OreDictionary name.
	 */
	public static void registerAltarRecipe(ItemStack result, Object... inputs) {
		registerAltarRecipe(new AltarRecipe(result, inputs));
	}

	/**
	 * Registers a Recipe that can only be crafted in a given Dimension id.
	 *
	 * @param result The ItemStack Output of the recipe
	 * @param inputs The Inputs of the recipe, no longer than 16. Can be an Item, ItemStack, or an OreDictionary name.
	 */
	public static void registerAltarRecipeDimension(DimensionType dim, ItemStack result, Object... inputs) {
		registerAltarRecipe(new AltarRecipeDimension(dim, result, inputs));
	}

	/**
	 * Registers a Recipe that can only be crafted during a given Moon Phase at Night.
	 *
	 * @param result The ItemStack Output of the recipe
	 * @param inputs The Inputs of the recipe, no longer than 16. Can be an Item, ItemStack, or an OreDictionary name.
	 */
	public static void registerAltarRecipeMoonPhase(MoonPhase phase, ItemStack result, Object... inputs) {
		registerAltarRecipe(new AltarRecipeMoonPhase(phase, result, inputs));
	}

	/**
	 * Registers a Recipe that can only be crafted when Raining.
	 *
	 * @param result The ItemStack Output of the recipe
	 * @param inputs The Inputs of the recipe, no longer than 16. Can be an Item, ItemStack, or an OreDictionary name.
	 */
	public static void registerAltarRecipeRain(ItemStack result, Object... inputs) {
		registerAltarRecipe(new AltarRecipeRain(result, inputs));
	}

	/**
	 * Registers a Recipe that can only be crafted during a Thunder Storm.
	 *
	 * @param result The ItemStack Output of the recipe
	 * @param inputs The Inputs of the recipe, no longer than 16. Can be an Item, ItemStack, or an OreDictionary name.
	 */
	public static void registerAltarRecipeThunder(ItemStack result, Object... inputs) {
		registerAltarRecipe(new AltarRecipeThunder(result, inputs));
	}

	/**
	 * Register an Item that will allow the player to fly.
	 *
	 * @param item The ItemStack of the Item
	 */
	public static void registerFlyingItem(ItemStack item) {
		FLY_ITEMS.add(item);
	}

	/**
	 * Registers an Armor that will allow the player to fly.
	 *
	 * @param item The ItemStack of the Item
	 */
	public static void registerFlyingArmor(ItemStack item) {
		FLY_ARMOR.add(item);
	}
}
