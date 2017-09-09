package arekkuusu.grimoireofalice.api.recipes;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AltarRecipeMoonPhase extends AltarRecipe {

	private final MoonPhase phase;

	public AltarRecipeMoonPhase(MoonPhase phase, ItemStack result, Object... inputs) {
		super(result, inputs);
		this.phase = phase;
	}

	@Override
	public boolean checkRecipe(List<ItemStack> usedItems, World world) {
		return !world.isDaytime() && phase.ordinal() == world.provider.getMoonPhase(world.getWorldTime()) && super.checkRecipe(usedItems, world);
	}
}
