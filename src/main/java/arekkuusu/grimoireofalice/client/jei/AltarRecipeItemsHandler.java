package arekkuusu.grimoireofalice.client.jei;

import arekkuusu.grimoireofalice.api.recipes.AltarRecipe;
import arekkuusu.grimoireofalice.common.lib.LibJEI;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IStackHelper;

public class AltarRecipeItemsHandler implements IRecipeHandler<AltarRecipe> {

	private final IStackHelper stackHelper;

	public AltarRecipeItemsHandler(IStackHelper stackHelper) {
		this.stackHelper = stackHelper;
	}

	@Override
	public Class<AltarRecipe> getRecipeClass() {
		return AltarRecipe.class;
	}

	@Override
	public String getRecipeCategoryUid() {
		return LibJEI.ALTAR_CATEGORY_UID;
	}

	@Override
	public String getRecipeCategoryUid(AltarRecipe recipe) {
		return LibJEI.ALTAR_CATEGORY_UID;
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(AltarRecipe recipe) {
		return new AltarRecipeItemsWrapper(recipe, stackHelper);
	}

	@Override
	public boolean isRecipeValid(AltarRecipe recipe) {
		for(Object obj : recipe.getNeededItems()) {
			if(stackHelper.toItemStackList(obj).isEmpty()) {
				return false;
			}
		}

		return true;
	}
}
