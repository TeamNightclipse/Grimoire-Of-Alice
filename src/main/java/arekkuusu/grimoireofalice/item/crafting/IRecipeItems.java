package arekkuusu.grimoireofalice.item.crafting;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public interface IRecipeItems {
	boolean checkRecipe(List<ItemStack> usedItems, World world);
	ItemStack getResult();
}
