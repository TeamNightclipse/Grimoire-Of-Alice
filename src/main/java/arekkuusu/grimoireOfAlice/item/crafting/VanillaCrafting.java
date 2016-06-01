/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.item.crafting;

import arekkuusu.grimoireOfAlice.block.GOABlock;
import arekkuusu.grimoireOfAlice.item.GOAItem;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class VanillaCrafting {

	public static void booksAndStrings() {

		addOreDictRecipe(new ItemStack(GOAItem.itemVolatileString, 16), "ISG", "SAS", "GSI", 'S', Items.string, 'G', "dustGlowstone", 'I', Items.gunpowder, 'A', Items.firework_charge);
		addOreDictRecipe(new ItemStack(GOAItem.itemShimenawaRope), "HHH", "STS", "STS", 'S', GOAItem.itemVolatileString, 'H', GOABlock.blockRope, 'T', GOAItem.itemSoldifiedPaper);
		addOreDictRecipe(new ItemStack(GOAItem.itemSoldifiedPaper, 4), "STS", "TAT", "STS", 'S', Items.paper, 'A', Items.coal, 'T', "stickWood");
		addOreDictRecipe(new ItemStack(GOAItem.itemMomijisScimitarSword), "SSS", "SA ", "SA ", 'S', GOAItem.itemGloriousNipponSteel, 'A', "stickWood");
		addOreDictRecipe(new ItemStack(GOAItem.itemMochiHammer), "SSG", " A ", " A ", 'G', GOAItem.itemGloriousNipponSteel, 'A', "stickWood", 'S', "ingotBrick");
		addOreDictRecipe(new ItemStack(GOABlock.blockSugar), "SSS", "SSS", "SSS", 'S', Items.sugar);
		addOreDictRecipe(new ItemStack(GOABlock.blockRope, 8), "ACA", "CSC", "ACA", 'S', "logWood", 'A', GOAItem.itemVolatileString, 'C', Items.coal);
		addOreDictRecipe(new ItemStack(GOABlock.blockPaper, 8), "AAA", "ASA", "AAA", 'A', Items.paper, 'S', GOAItem.itemSoldifiedPaper);
		addOreDictRecipe(new ItemStack(GOABlock.blockCompactStone), "AAA", "ASA", "AAA", 'A', "stone", 'S', GOAItem.itemVolatileString);
		addOreDictRecipe(new ItemStack(GOABlock.blockHolyStone), "AOA", "OSO", "AOA", 'A', GOABlock.blockCompactStone, 'S', GOAItem.itemShimenawaRope, 'O', GOABlock.blockHyperconcentratedMagic);
		addOreDictRecipe(new ItemStack(GOABlock.blockOnbashira), "AHA", "HSH", "AHA", 'A', "logWood", 'H', GOAItem.itemVolatileString, 'S', GOABlock.blockRope);
		addOreDictRecipe(new ItemStack(GOABlock.blockHyperconcentratedMagic), "AHA", "HSH", "AHA", 'A', GOAItem.itemVolatileString, 'H', GOABlock.blockCompactStone, 'S', GOABlock.blockShroom);
		addOreDictRecipe(new ItemStack(GOAItem.itemLaevatein), "AAA", " SA", " G ", 'G', Items.coal, 'A', GOAItem.itemGloriousNipponSteel, 'S', Items.blaze_rod);
		addOreDictRecipe(new ItemStack(GOAItem.itemEllyScythe), "HAA", " SA", "GAA", 'G', Items.bone, 'A', "dyeRed", 'S', Items.speckled_melon, 'H', "ingotGold");
		addOreDictRecipe(new ItemStack(GOAItem.itemMikoStick), "SNS", "SNS", "SNS", 'S', GOAItem.itemSoldifiedPaper, 'N', "logWood");
		addOreDictRecipe(new ItemStack(GOAItem.itemNazrinStick), "SSS", "HSH", "SH ", 'S', Items.flint, 'H', GOAItem.itemSoldifiedPaper);
		addOreDictRecipe(new ItemStack(GOAItem.itemCrestOfYggdrasill), "STS", "HOH", " U ", 'U', GOABlock.blockHyperconcentratedMagic, 'H', GOAItem.itemSoldifiedPaper, 'S', GOABlock.blockPaper, 'T', GOABlock.blockHolyStone, 'O', Items.nether_star);
		addOreDictRecipe(new ItemStack(GOAItem.itemAmenonuhoko), "STS", "HUG", "DUD", 'U', GOABlock.blockHyperconcentratedMagic, 'H', "blockEmerald", 'S', GOABlock.blockCompactStone, 'T', Blocks.beacon, 'G', Blocks.diamond_block, 'D', Items.nether_star);
		addOreDictRecipe(new ItemStack(GOAItem.itemPrimordialShield), "SQS", "QTQ", "OUO", 'Q', GOABlock.blockHyperconcentratedMagic, 'S', Items.nether_star, 'U', GOABlock.blockCompactStone, 'T', Blocks.beacon, 'O', GOABlock.blockHolyStone);

		addShapelessOreDictRecipe(new ItemStack(Items.sugar, 9), new ItemStack(GOABlock.blockSugar));
		addShapelessOreDictRecipe(new ItemStack(GOAItem.itemShroomSlice, 1, 0), GOABlock.blockShroom);
		addShapelessOreDictRecipe(new ItemStack(GOAItem.itemShroomSlice, 1, 1), GOAItem.itemShroomSlice, Items.fermented_spider_eye, Items.poisonous_potato);
		addShapelessOreDictRecipe(new ItemStack(GOAItem.itemYoukaiBook), Items.leather, Items.book, GOAItem.itemVolatileString, Items.feather);

		GameRegistry.addSmelting(Items.iron_sword, new ItemStack(GOAItem.itemGloriousNipponSteel), 0);
	}

	public static void masks() {

		addShapelessOreDictRecipe(new ItemStack(GOAItem.itemKokorosMasks, 1), GOAItem.itemFoxMask, GOAItem.itemFukuNoKamiMask, GOAItem.itemHannyaMask, GOAItem.itemHyottokoMask, GOAItem.itemKoomoteMask, GOAItem.itemMaskOfHope, GOAItem.itemMonkeyMask, GOAItem.itemRaidenMask, GOAItem.itemUbaMask);

		addOreDictRecipe(new ItemStack(GOAItem.itemMask, 1), "SSS", "GAG", "SSS", 'S', GOAItem.itemSoldifiedPaper, 'G', GOAItem.itemVolatileString, 'A', Items.clay_ball);
		addOreDictRecipe(new ItemStack(GOAItem.itemFoxMask, 1), "IAR", "SGS", " S ", 'S', GOAItem.itemVolatileString, 'G', GOAItem.itemMask, 'A', Items.nether_star, 'I', new ItemStack(Items.potionitem, 1, 16), 'R', Items.sugar);
		addOreDictRecipe(new ItemStack(GOAItem.itemFukuNoKamiMask), "IAR", "SGS", " S ", 'S', GOAItem.itemVolatileString, 'G', GOAItem.itemMask, 'A', Items.nether_star, 'I', new ItemStack(Items.potionitem, 1, 16), 'R', Items.blaze_powder);
		addOreDictRecipe(new ItemStack(GOAItem.itemHannyaMask), "IAR", "SGS", " S ", 'S', GOAItem.itemVolatileString, 'G', GOAItem.itemMask, 'A', Items.nether_star, 'I', new ItemStack(Items.potionitem, 1, 16), 'R', Items.poisonous_potato);
		addOreDictRecipe(new ItemStack(GOAItem.itemHyottokoMask), "IAR", "SGS", " S ", 'S', GOAItem.itemVolatileString, 'G', GOAItem.itemMask, 'A', Items.nether_star, 'I', new ItemStack(Items.potionitem, 1, 16), 'R', Items.ghast_tear);
		addOreDictRecipe(new ItemStack(GOAItem.itemKoomoteMask), "IAR", "SGS", " S ", 'S', GOAItem.itemVolatileString, 'G', GOAItem.itemMask, 'A', Items.nether_star, 'I', new ItemStack(Items.potionitem, 1, 16), 'R', Items.rotten_flesh);
		addOreDictRecipe(new ItemStack(GOAItem.itemMaskOfHope), "IAR", "SGS", " S ", 'S', GOAItem.itemVolatileString, 'G', GOAItem.itemMask, 'A', Items.nether_star, 'I', new ItemStack(Items.potionitem, 1, 16), 'R', Items.fermented_spider_eye);
		addOreDictRecipe(new ItemStack(GOAItem.itemMonkeyMask), "IAR", "SGS", " S ", 'S', GOAItem.itemVolatileString, 'G', GOAItem.itemMask, 'A', Items.nether_star, 'I', new ItemStack(Items.potionitem, 1, 16), 'R', Items.mushroom_stew);
		addOreDictRecipe(new ItemStack(GOAItem.itemRaidenMask), "IAR", "SGS", " S ", 'S', GOAItem.itemVolatileString, 'G', GOAItem.itemMask, 'A', Items.nether_star, 'I', new ItemStack(Items.potionitem, 1, 16), 'R', "slimeball");
		addOreDictRecipe(new ItemStack(GOAItem.itemUbaMask), "IAR", "SGS", " S ", 'S', GOAItem.itemVolatileString, 'G', GOAItem.itemMask, 'A', Items.nether_star, 'I', new ItemStack(Items.potionitem, 1, 16), 'R', Items.nether_wart);
	}

	private static void addOreDictRecipe(ItemStack output, Object... recipe) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, recipe));
	}

	private static void addShapelessOreDictRecipe(ItemStack output, Object... recipe) {
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(output, recipe));
	}
}
