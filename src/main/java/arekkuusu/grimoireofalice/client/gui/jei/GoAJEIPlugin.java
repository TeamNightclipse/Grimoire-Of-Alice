package arekkuusu.grimoireofalice.client.gui.jei;

import arekkuusu.grimoireofalice.api.AliceAPI;
import arekkuusu.grimoireofalice.api.recipes.AltarRecipe;
import arekkuusu.grimoireofalice.common.block.ModBlocks;
import arekkuusu.grimoireofalice.common.lib.LibGui;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class GoAJEIPlugin implements IModPlugin {
	private static final String LINE = "--------------------------";

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new AltarRecipeItemsCategory(registry.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void register(IModRegistry registry) {
		registry.handleRecipes(AltarRecipe.class, recipe -> new AltarRecipeItemsWrapper(recipe, registry.getJeiHelpers().getStackHelper()), LibGui.ALTAR_CATEGORY_UID);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.CRAFTING_ALTAR), LibGui.ALTAR_CATEGORY_UID);
		registry.addRecipes(AliceAPI.getAltarRecipes(), LibGui.ALTAR_CATEGORY_UID);

		addDescriptions(registry);
	}

	private static void addDescriptions(IModRegistry registry) {

	}

	private static void addDescription(IModRegistry registry, ItemStack stack, String... keys) {
		registry.addIngredientInfo(stack, ItemStack.class, keys);
	}
}
