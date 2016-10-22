package arekkuusu.grimoireofalice.item.crafting;

import arekkuusu.grimoireofalice.item.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public final class SpecialRecipes {

	public static RecipeItems mask;
	public static RecipeItems bloodThirstyOrb;

	public static void init(){
		mask = RecipeAltar.registerRecipe(new ItemStack(ModItems.MASK), new ItemStack(ModItems.VOLATILE_STRING), new ItemStack(ModItems.SOLDIFIED_PAPER), new ItemStack(ModItems.SOLDIFIED_PAPER), new ItemStack(ModItems.SOLDIFIED_PAPER));
		bloodThirstyOrb = RecipeAltar.registerRecipe(new ItemStack(ModItems.BLOOD_ORB), new ItemStack(Items.ENDER_EYE), new ItemStack(Items.ENDER_PEARL), new ItemStack(Items.ENDER_PEARL), new ItemStack(Items.ENDER_PEARL), new ItemStack(Items.ENDER_PEARL), new ItemStack(Items.ENDER_PEARL));
	}
}
