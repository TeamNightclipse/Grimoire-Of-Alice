package arekkuusu.grimoireofalice.common.item.crafting;

import arekkuusu.grimoireofalice.api.recipes.AltarRecipe;
import arekkuusu.grimoireofalice.common.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

/**
 * This class was created by Arekkuusu on 23/05/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
public class RecipeHakurei extends AltarRecipe {

	public RecipeHakurei() {
		super(new ItemStack(ModItems.HAKUREI_GOHEI), "stickWood", "stickWood", ModItems.SHIMENAWA_ROPE, ModItems.SHIMENAWA_ROPE, ModItems.SHIMENAWA_ROPE, ModItems.SHIMENAWA_ROPE, ModItems.SHIMENAWA_ROPE, ModItems.SHIMENAWA_ROPE);
	}

	@Override
	public boolean checkRecipe(List<ItemStack> usedItems, World world) {
		return world.isDaytime() && 0 == world.provider.getMoonPhase(world.getWorldTime()) && super.checkRecipe(usedItems, world);
	}
}
