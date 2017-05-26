package arekkuusu.grimoireofalice.api.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class AltarRecipeDimension extends AltarRecipe {

	private final int dimId;

	public AltarRecipeDimension(int dimId, ItemStack result, Object... inputs) {
		super(result, inputs);
		this.dimId = dimId;
	}

	@Override
	public boolean checkRecipe(List<ItemStack> usedItems, World world) {
		return world.provider.getDimensionType().getId() == dimId && super.checkRecipe(usedItems, world);
	}
}
