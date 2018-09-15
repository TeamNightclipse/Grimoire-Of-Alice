package arekkuusu.grimoireofalice.client.gui.jei;

import arekkuusu.grimoireofalice.common.lib.LibGui;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class AltarRecipeItemsCategory implements IRecipeCategory<AltarRecipeItemsWrapper> {

	private final IGuiHelper guiHelper;

	private static final int O_LVL_1 = 0;
	private static final int O_LVL_2 = 36 + 4;
	private static final int O_LVL_3 = 72 + 8;

	private static final ResourceLocation furnace = new ResourceLocation("minecraft", "textures/gui/container/furnace.png");
	private final IDrawableStatic outputSlot;

	private static final int[][] OUTER_PILLARS = {
			{O_LVL_1, O_LVL_1},
			{O_LVL_1, O_LVL_2},
			{O_LVL_1, O_LVL_3},
			{O_LVL_2, O_LVL_1},
			{O_LVL_2, O_LVL_3},
			{O_LVL_3, O_LVL_1},
			{O_LVL_3, O_LVL_2},
			{O_LVL_3, O_LVL_3}
	};

	private static final int I_LVL_1 = 18;
	private static final int I_LVL_2 = 36 + 4;
	private static final int I_LVL_3 = 54 + 8;

	private static final int[][] INNER_PILLARS = {
			{I_LVL_1, I_LVL_1},
			{I_LVL_1, I_LVL_2},
			{I_LVL_1, I_LVL_3},
			{I_LVL_2, I_LVL_1},
			{I_LVL_2, I_LVL_3},
			{I_LVL_3, I_LVL_1},
			{I_LVL_3, I_LVL_2},
			{I_LVL_3, I_LVL_3}
	};

	public AltarRecipeItemsCategory(IGuiHelper guiHelper) {
		this.guiHelper = guiHelper;
		outputSlot = guiHelper.createDrawable(furnace, 111, 30, 26, 26);
	}

	@Override
	public String getUid() {
		return LibGui.ALTAR_CATEGORY_UID;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getTitle() {
		return I18n.format("grimoire.jei.altar.title");
	}

	@Override
	public String getModName() {
		return LibMod.MOD_NAME;
	}

	@Override
	public IDrawable getBackground() {
		return guiHelper.createBlankDrawable(100, 100);
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
