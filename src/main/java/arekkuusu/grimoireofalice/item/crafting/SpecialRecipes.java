package arekkuusu.grimoireofalice.item.crafting;

import arekkuusu.grimoireofalice.block.ModBlocks;
import arekkuusu.grimoireofalice.handler.ConfigHandler;
import arekkuusu.grimoireofalice.item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;

public final class SpecialRecipes {
	//RecipeItems
	public static RecipeItems MASK;
	public static RecipeItems HIHIIROKANE;
	public static RecipeItems FOX_MASK;
	public static RecipeItems FUKU_NO_KAMI_MASK;
	public static RecipeItems HANNYA_MASK;
	public static RecipeItems HYOTTOKO_MASK;
	public static RecipeItems KOOMOTE_MASK;
	public static RecipeItems MASK_OF_HOPE;
	public static RecipeItems MONKEY_MASK;
	public static RecipeItems RAIDEN_MASK;
	public static RecipeItems UBA_MASK;
	public static RecipeItems KOKOROS_MASKS;
	//RecipeItemsNether
	public static RecipeItemsNether THIRD_EYE;
	//RecipeItemsMoonPhase
	public static RecipeItemsMoonPhase JEWELED_HOURAI;
	//RecipeItemsRain
	public static RecipeItems HOLYSTONE;
	public static RecipeItems ONBASHIRA;

	public static void init() { //How should this be ordered?
		//RecipeItems
		MASK = RecipeAltar.registerRecipe(new ItemStack(ModItems.MASK), ModItems.VOLATILE_STRING, ModItems.SOLDIFIED_PAPER, ModItems.SOLDIFIED_PAPER, ModItems.SOLDIFIED_PAPER);
		HIHIIROKANE = RecipeAltar.registerRecipe(new ItemStack(ModItems.HIHIIROKANE), new ItemStack(Blocks.COAL_BLOCK), ModItems.IMPURE_ROCK, new ItemStack(Blocks.COAL_BLOCK), ModItems.IMPURE_ROCK, new ItemStack(Blocks.COAL_BLOCK), ModItems.IMPURE_ROCK, new ItemStack(Blocks.COAL_BLOCK), ModItems.IMPURE_ROCK);

		if (ConfigHandler.maskAltarRecipes) {
			ItemStack potion = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.AWKWARD);
			KOKOROS_MASKS = RecipeAltar.registerRecipe(new ItemStack(ModItems.KOKOROS_MASKS), potion, ModItems.MASK, ModItems.FOX_MASK, ModItems.FUKU_NO_KAMI_MASK, ModItems.HANNYA_MASK, ModItems.HYOTTOKO_MASK, ModItems.KOOMOTE_MASK, ModItems.MASK_OF_HOPE, ModItems.MONKEY_MASK, ModItems.RAIDEN_MASK,ModItems.UBA_MASK);

			FOX_MASK = RecipeAltar.registerRecipe(new ItemStack(ModItems.FOX_MASK), ModItems.MASK, Items.NETHER_STAR, ModItems.VOLATILE_STRING, potion, Items.SUGAR, new ItemStack(Items.DYE, 1, 0));
			FUKU_NO_KAMI_MASK = RecipeAltar.registerRecipe(new ItemStack(ModItems.FUKU_NO_KAMI_MASK), ModItems.MASK, Items.NETHER_STAR, potion, Items.BLAZE_POWDER, new ItemStack(Items.DYE, 1, 14));
			HANNYA_MASK = RecipeAltar.registerRecipe(new ItemStack(ModItems.HANNYA_MASK), ModItems.MASK, Items.NETHER_STAR, ModItems.VOLATILE_STRING, potion, Items.POISONOUS_POTATO, new ItemStack(Items.DYE, 1, 1));
			HYOTTOKO_MASK = RecipeAltar.registerRecipe(new ItemStack(ModItems.HYOTTOKO_MASK), ModItems.MASK, Items.NETHER_STAR, ModItems.VOLATILE_STRING, potion, Items.GHAST_TEAR, new ItemStack(Items.DYE, 1, 9));
			KOOMOTE_MASK = RecipeAltar.registerRecipe(new ItemStack(ModItems.KOOMOTE_MASK), ModItems.MASK, Items.NETHER_STAR, ModItems.VOLATILE_STRING, potion, Items.ROTTEN_FLESH, new ItemStack(Items.DYE, 1, 0));
			MASK_OF_HOPE = RecipeAltar.registerRecipe(new ItemStack(ModItems.MASK_OF_HOPE), ModItems.MASK, Items.NETHER_STAR, ModItems.VOLATILE_STRING, potion, Items.FERMENTED_SPIDER_EYE, new ItemStack(Items.DYE, 1, 8));
			MONKEY_MASK = RecipeAltar.registerRecipe(new ItemStack(ModItems.MONKEY_MASK), ModItems.MASK, Items.NETHER_STAR, ModItems.VOLATILE_STRING, potion, Items.MUSHROOM_STEW, new ItemStack(Items.DYE, 1, 7));
			RAIDEN_MASK = RecipeAltar.registerRecipe(new ItemStack(ModItems.RAIDEN_MASK), ModItems.MASK, Items.NETHER_STAR, ModItems.VOLATILE_STRING, potion,Items.SLIME_BALL, new ItemStack(Items.DYE, 1, 9));
			UBA_MASK = RecipeAltar.registerRecipe(new ItemStack(ModItems.UBA_MASK), ModItems.MASK, Items.NETHER_STAR, ModItems.VOLATILE_STRING, potion, Items.BEETROOT_SOUP, new ItemStack(Items.DYE, 1, 9));
		}
		//RecipeItemsNether
		THIRD_EYE = RecipeAltar.registerRecipeNether(new ItemStack(ModItems.THIRD_EYE), Items.ENDER_EYE, Items.ENDER_PEARL, Items.ENDER_PEARL, Items.ENDER_PEARL, Items.ENDER_PEARL,Items.ENDER_PEARL);
		//RecipeItemsMoonPhase
		//RecipeItemsRain
		ONBASHIRA = RecipeAltar.registerRecipeRain(new ItemStack(ModBlocks.ONBASHIRA), new ItemStack(ModBlocks.PILLAR_ALTAR), new ItemStack(ModBlocks.HOLY_STONE), new ItemStack(ModBlocks.PILLAR_ALTAR), new ItemStack(ModBlocks.HOLY_STONE), new ItemStack(ModBlocks.PILLAR_ALTAR), new ItemStack(ModBlocks.HOLY_STONE), new ItemStack(ModBlocks.PILLAR_ALTAR), new ItemStack(ModBlocks.HOLY_STONE));
	}
}
