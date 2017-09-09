package arekkuusu.grimoireofalice.api;

import arekkuusu.grimoireofalice.api.recipes.*;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;

import java.util.*;

public final class AliceAPI {

	private static final Map<Item, Achievement> ALTAR_ACHIEVEMENT_MAP = new HashMap<>();
	private static final List<Achievement> VANILLA_ACHIEVEMENT_LIST = new ArrayList<>();
	private static final List<IAltarRecipe> ALTAR_RECIPES = new ArrayList<>();
	private static final List<ItemStack> FLY_ITEMS = new ArrayList<>();
	private static final List<ItemStack> FLY_ARMOR = new ArrayList<>();

	public static Map<Item, Achievement> getAltarAchievements() {
		return ImmutableMap.copyOf(ALTAR_ACHIEVEMENT_MAP);
	}

	public static List<Achievement> getVanillaAchievements() {
		return ImmutableList.copyOf(VANILLA_ACHIEVEMENT_LIST);
	}

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

	public static void addAltarAchievement(Item item, Achievement achievement) {
		ALTAR_ACHIEVEMENT_MAP.put(item, achievement.registerStat());
	}

	public static void addVanillaAchievement(Achievement achievement) {
		VANILLA_ACHIEVEMENT_LIST.add(achievement.registerStat());
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
