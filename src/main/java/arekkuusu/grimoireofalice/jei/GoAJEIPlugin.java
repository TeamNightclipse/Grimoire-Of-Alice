package arekkuusu.grimoireofalice.jei;

import arekkuusu.grimoireofalice.block.ModBlocks;
import arekkuusu.grimoireofalice.item.ModItems;
import arekkuusu.grimoireofalice.item.crafting.RecipeAltar;
import arekkuusu.grimoireofalice.lib.LibJEI;
import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class GoAJEIPlugin extends BlankModPlugin {

	@Override
	public void register(IModRegistry registry) {
		registry.addRecipeCategories(new AltarRecipeItemsCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeHandlers(new AltarRecipeItemsHandler(registry.getJeiHelpers().getStackHelper()));
		registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.ALTAR), LibJEI.ALTER_CATEGORY_UID);
		registry.addRecipes(RecipeAltar.getRecipes());

		addDescriptions(registry);
	}

	private void addDescriptions(IModRegistry registry) {
		registry.addDescription(new ItemStack(ModItems.NEEDLE), "grimoire.jei.descriptions.needle");
	}
}
