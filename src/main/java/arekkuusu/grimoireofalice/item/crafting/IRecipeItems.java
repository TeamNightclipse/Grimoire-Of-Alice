package arekkuusu.grimoireofalice.item.crafting;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IRecipeItems {

	boolean checkRecipe(List<ItemStack> usedItems, World world);

	ItemStack getResult();
}
