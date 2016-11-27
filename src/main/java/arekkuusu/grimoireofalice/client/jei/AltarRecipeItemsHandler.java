package arekkuusu.grimoireofalice.client.jei;

import arekkuusu.grimoireofalice.api.recipes.RecipeItems;
import arekkuusu.grimoireofalice.common.lib.LibJEI;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IStackHelper;

public class AltarRecipeItemsHandler implements IRecipeHandler<RecipeItems> {

	private final IStackHelper stackHelper;

	public AltarRecipeItemsHandler(IStackHelper stackHelper) {
		this.stackHelper = stackHelper;
	}

	@Override
	public Class<RecipeItems> getRecipeClass() {
		return RecipeItems.class;
	}

	@Override
	public String getRecipeCategoryUid() {
		return LibJEI.ALTER_CATEGORY_UID;
	}

	@Override
	public String getRecipeCategoryUid(RecipeItems recipe) {
		return LibJEI.ALTER_CATEGORY_UID;
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(RecipeItems recipe) {
		return new AltarRecipeItemsWrapper(recipe, stackHelper);
	}

	@Override
	public boolean isRecipeValid(RecipeItems recipe) {
		for(Object obj : recipe.getNeededItems()) {
			if(stackHelper.toItemStackList(obj).isEmpty()) {
				return false;
			}
		}

		return true;
	}
}
