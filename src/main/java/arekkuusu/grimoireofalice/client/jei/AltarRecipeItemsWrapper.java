package arekkuusu.grimoireofalice.client.jei;

import arekkuusu.grimoireofalice.api.recipes.AltarRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

public class AltarRecipeItemsWrapper extends BlankRecipeWrapper {

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
