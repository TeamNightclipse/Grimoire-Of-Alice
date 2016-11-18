package arekkuusu.grimoireofalice.jei;

import java.util.List;

import arekkuusu.grimoireofalice.lib.LibJEI;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class AltarRecipeItemsCategory extends BlankRecipeCategory<AltarRecipeItemsWrapper> {

	private final IGuiHelper guiHelper;

	private static final int oLvl1 = 0;
	private static final int oLvl2 = 36 + 4;
	private static final int oLvl3 = 72 + 8;

	private static final ResourceLocation furnace = new ResourceLocation("minecraft", "textures/gui/container/furnace.png");
	private final IDrawableStatic outputSlot;

	public static final int[][] OUTER_PILLARS = {
			{oLvl1, oLvl1},
			{oLvl1, oLvl2},
			{oLvl1, oLvl3},
			{oLvl2, oLvl1},
			{oLvl2, oLvl3},
			{oLvl3, oLvl1},
			{oLvl3, oLvl2},
			{oLvl3, oLvl3}
	};

	private static final int iLvl1 = 18;
	private static final int iLvl2 = 36 + 4;
	private static final int iLvl3 = 54 + 8;

	private static final int[][] INNER_PILLARS = {
			{iLvl1, iLvl1},
			{iLvl1, iLvl2},
			{iLvl1, iLvl3},
			{iLvl2, iLvl1},
			{iLvl2, iLvl3},
			{iLvl3, iLvl1},
			{iLvl3, iLvl2},
			{iLvl3, iLvl3}
	};

	public AltarRecipeItemsCategory(IGuiHelper guiHelper) {
		this.guiHelper = guiHelper;
		outputSlot = guiHelper.createDrawable(furnace, 111, 30, 26, 26);
	}


	@Override
	public String getUid() {
		return LibJEI.ALTER_CATEGORY_UID;
	}

	@Override
	public String getTitle() {
		return I18n.format("grimoire.jei.alter.title");
	}

	@Override
	public IDrawable getBackground() {
		return guiHelper.createBlankDrawable(100, 80); //TODO
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, AltarRecipeItemsWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup stackLayout = recipeLayout.getItemStacks();

		stackLayout.init(0, false, 40, 40);
		stackLayout.set(0, ingredients.getOutputs(ItemStack.class).get(0));

		List<List<ItemStack>> inputs = ingredients.getInputs(ItemStack.class);
		int size = inputs.size();

		for(int i = 0; i < 8; i++) {
			stackLayout.init(i + 1, true, INNER_PILLARS[i][0], INNER_PILLARS[i][1]);
			if(size > i) {
				stackLayout.set(i + 1, inputs.get(i));
			}

			stackLayout.init(i + 9, true, OUTER_PILLARS[i][0], OUTER_PILLARS[i][1]);
			if(size > i + 8) {
				stackLayout.set(i + 9, inputs.get(i + 8));
			}
		}
	}

	@Override
	public void drawExtras(Minecraft minecraft) {
		IDrawableStatic slot = guiHelper.getSlotDrawable();
		outputSlot.draw(minecraft, 36, 36);

		for(int i = 0; i < 8; i++) {
			slot.draw(minecraft, INNER_PILLARS[i][0], INNER_PILLARS[i][1]);
			slot.draw(minecraft, OUTER_PILLARS[i][0], OUTER_PILLARS[i][1]);
		}
	}
}
