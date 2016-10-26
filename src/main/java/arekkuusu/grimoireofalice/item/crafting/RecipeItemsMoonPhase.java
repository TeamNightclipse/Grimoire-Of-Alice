package arekkuusu.grimoireofalice.item.crafting;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class RecipeItemsMoonPhase extends RecipeItems {

	private final int moonPhase;

	RecipeItemsMoonPhase(int moonPhase, ItemStack result, Object... inputs) {
		super(result, inputs);
		this.moonPhase = moonPhase;
	}

	@Override
	public boolean checkRecipe(List<ItemStack> usedItems, World world) {
		return !world.isDaytime() && moonPhase == world.provider.getMoonPhase(world.getWorldTime()) && super.checkRecipe(usedItems, world);
	}
}
