package arekkuusu.grimoireofalice.api.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;

import java.util.List;

public class AltarRecipeDimension extends AltarRecipe {

	private final DimensionType dim;

	public AltarRecipeDimension(DimensionType dim, ItemStack result, Object... inputs) {
		super(result, inputs);
		this.dim = dim;
	}

	@Override
	public boolean checkRecipe(List<ItemStack> usedItems, World world) {
		return world.provider.getDimensionType().getId() == dim.getId() && super.checkRecipe(usedItems, world);
	}
}
