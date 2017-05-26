package arekkuusu.grimoireofalice.api.recipes;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AltarRecipeMoonPhase extends AltarRecipe {

	private final int moonPhase;

	public AltarRecipeMoonPhase(int moonPhase, ItemStack result, Object... inputs) {
		super(result, inputs);
		this.moonPhase = moonPhase;
	}

	@Override
	public boolean checkRecipe(List<ItemStack> usedItems, World world) {
		return !world.isDaytime() && moonPhase == world.provider.getMoonPhase(world.getWorldTime()) && super.checkRecipe(usedItems, world);
	}
}
