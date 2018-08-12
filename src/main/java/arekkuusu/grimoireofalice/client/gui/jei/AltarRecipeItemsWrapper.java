package arekkuusu.grimoireofalice.client.gui.jei;

import arekkuusu.grimoireofalice.api.recipes.AltarRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

public class AltarRecipeItemsWrapper implements IRecipeWrapper {

	private final AltarRecipe recipe;
	private final IStackHelper stackHelper;

	public AltarRecipeItemsWrapper(AltarRecipe recipe, IStackHelper stackHelper) {
		this.recipe = recipe;
		this.stackHelper = stackHelper;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setOutput(ItemStack.class, recipe.getResult());
		ingredients.setInputLists(ItemStack.class, stackHelper.expandRecipeItemStackInputs(recipe.getNeededItems()));
	}
}
