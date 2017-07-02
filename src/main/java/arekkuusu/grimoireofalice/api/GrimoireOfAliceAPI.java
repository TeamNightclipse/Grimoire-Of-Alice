package arekkuusu.grimoireofalice.api;

import arekkuusu.grimoireofalice.api.recipes.*;
import com.google.common.collect.ImmutableList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class GrimoireOfAliceAPI {

    private static final Map<Item, Achievement> achievementAltarMap = new HashMap<>();
    private static final List<Achievement> achievementList = new ArrayList<>();

    private static final List<IAltarRecipe> recipes = new ArrayList<>();

	//#HONK!
	private static final List<ItemStack> flyItems = new ArrayList<>();
	private static final List<ItemStack> flyArmor = new ArrayList<>();

    public static Map<Item, Achievement> getAchievementAltarMap() {
        return achievementAltarMap;
    }

    public static List<Achievement> getAchievementList() {
        return achievementList;
    }

    public static List<IAltarRecipe> getRecipes() {
		return ImmutableList.copyOf(recipes);
	}

	public static List<ItemStack> getFlyItems() {
		return flyItems;
	}

	public static List<ItemStack> getFlyArmor() {
		return flyArmor;
	}

	public static void registerAltarAchievement(Item item, Achievement achievement) {
        achievementAltarMap.put(item, achievement.registerStat());
    }

    public static void registerAchievement(Achievement achievement) {
        achievementList.add(achievement.registerStat());
    }

	/**
	 * Adds an IAltarRecipe to the ArrayList.
	 *
	 * @param recipe The Recipe
	 * @return The Registered IAltarRecipe
	 */
	public static IAltarRecipe registerRecipe(IAltarRecipe recipe) {
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
	public static IAltarRecipe registerRecipe(ItemStack result, Object... inputs) {
		return registerRecipe(new AltarRecipe(result, inputs));
	}

	/**
	 * Registers a Recipe that can only be crafted in a given Dimension id.
	 *
	 * @param result The ItemStack Output of the recipe
	 * @param inputs The Inputs of the recipe, no longer than 16. Can be an Item, ItemStack, or an OreDictionary name.
	 * @return The Registered Recipe
	 */
	public static IAltarRecipe registerRecipeDimension(int dimId, ItemStack result, Object... inputs) {
		return registerRecipe(new AltarRecipeDimension(dimId, result, inputs));
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
	public static IAltarRecipe registerRecipeMoonPhase(int moonPhase, ItemStack result, Object... inputs) {
		return registerRecipe(new AltarRecipeMoonPhase(moonPhase, result, inputs));
	}

	/**
	 * Registers a Recipe that can only be crafted when Raining.
	 *
	 * @param result The ItemStack Output of the recipe
	 * @param inputs The Inputs of the recipe, no longer than 16. Can be an Item, ItemStack, or an OreDictionary name.
	 * @return The Registered Recipe
	 */
	public static IAltarRecipe registerRecipeRain(ItemStack result, Object... inputs) {
		return registerRecipe(new AltarRecipeRain(result, inputs));
	}

	/**
	 * Registers a Recipe that can only be crafted during a Thunder Storm.
	 *
	 * @param result The ItemStack Output of the recipe
	 * @param inputs The Inputs of the recipe, no longer than 16. Can be an Item, ItemStack, or an OreDictionary name.
	 * @return The Registered Recipe
	 */
	public static IAltarRecipe registerRecipeThunder(ItemStack result, Object... inputs) {
		return registerRecipe(new AltarRecipeThunder(result, inputs));
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
