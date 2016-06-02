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

		//@formatter:off
		shaped().grid("ISG", "SAS", "GSI")
				.where('S').mapsTo(Items.string)
				.where('G').mapsTo("dustGlowstone")
				.where('I').mapsTo(Items.gunpowder)
				.where('A').mapsTo(Items.firework_charge)
				.outputs(new ItemStack(GOAItem.itemVolatileString, 16)).build();

		shaped().grid("HHH", "STS", "STS")
				.where('S').mapsTo(GOAItem.itemVolatileString)
				.where('H').mapsTo(GOABlock.blockRope)
				.where('T').mapsTo(GOAItem.itemSoldifiedPaper)
				.outputs(GOAItem.itemShimenawaRope).build();

		shaped().grid("STS", "TAT", "STS")
				.where('S').mapsTo(Items.paper)
				.where('A').mapsTo(Items.coal)
				.where('T').mapsTo("stickWood")
				.outputs(new ItemStack(GOAItem.itemSoldifiedPaper, 4)).build();

		shaped().grid("SSS", "SA ", "SA ")
				.where('S').mapsTo(GOAItem.itemGloriousNipponSteel)
				.where('A').mapsTo("stickWood")
				.mirrored(true)
				.outputs(GOAItem.itemMomijisScimitarSword).build();

		shaped().grid("SSG", " A ", " A ")
				.where('G').mapsTo(GOAItem.itemGloriousNipponSteel)
				.where('A').mapsTo("stickWood")
				.where('S').mapsTo("ingotBrick")
				.outputs(GOAItem.itemMochiHammer).build();

		shaped().grid("SSS", "SSS", "SSS")
				.where('S').mapsTo(Items.sugar)
				.outputs(GOABlock.blockSugar).build();

		shaped().grid("ACA", "CSC", "ACA")
				.where('S').mapsTo("logWood")
				.where('A').mapsTo(GOAItem.itemVolatileString)
				.where('C').mapsTo(Items.coal)
				.outputs(new ItemStack(GOABlock.blockRope, 8)).build();

		shaped().grid("AAA", "ASA", "AAA")
				.where('A').mapsTo(Items.paper)
				.where('S').mapsTo(GOAItem.itemSoldifiedPaper)
				.outputs(new ItemStack(GOABlock.blockPaper, 8)).build();

		shaped().grid("AAA", "ASA", "AAA")
				.where('A').mapsTo("stone")
				.where('S').mapsTo(GOAItem.itemVolatileString)
				.outputs(GOABlock.blockCompactStone).build();

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

		shapeless()
				.add(GOABlock.blockSugar)
				.outputs(new ItemStack(Items.sugar, 9)).build();

		shapeless()
				.add(GOABlock.blockShroom)
				.outputs(new ItemStack(GOAItem.itemShroomSlice, 1, 0)).build();

		shapeless()
				.add(GOAItem.itemShroomSlice)
				.add(Items.fermented_spider_eye)
				.add(Items.poisonous_potato)
				.outputs(new ItemStack(GOAItem.itemShroomSlice, 1, 1)).build();

		shapeless()
				.add(Items.leather)
				.add(Items.book)
				.add(GOAItem.itemVolatileString)
				.add(Items.feather)
				.outputs(GOAItem.itemYoukaiBook).build();

		//@formatter:on

		GameRegistry.addSmelting(Items.iron_sword, new ItemStack(GOAItem.itemGloriousNipponSteel), 0);
	}

	public static void masks() {

		//@formatter:off

		shapeless()
				.add(GOAItem.itemFoxMask)
				.add(GOAItem.itemFukuNoKamiMask)
				.add(GOAItem.itemHannyaMask)
				.add(GOAItem.itemHyottokoMask)
				.add(GOAItem.itemKoomoteMask)
				.add(GOAItem.itemMaskOfHope)
				.add(GOAItem.itemMonkeyMask)
				.add(GOAItem.itemRaidenMask)
				.add(GOAItem.itemUbaMask)
				.outputs(GOAItem.itemKokorosMasks).build();

		shaped().grid("SSS", "GAG", "SSS")
				.where('S').mapsTo(GOAItem.itemSoldifiedPaper)
				.where('G').mapsTo(GOAItem.itemVolatileString)
				.where('A').mapsTo(Items.clay_ball)
				.outputs(GOAItem.itemMask).build();

		mask().where('R').mapsTo(Items.sugar).outputs(GOAItem.itemFoxMask).build();
		mask().where('R').mapsTo(Items.blaze_powder).outputs(GOAItem.itemFukuNoKamiMask).build();
		mask().where('R').mapsTo(Items.poisonous_potato).outputs(GOAItem.itemHannyaMask).build();
		mask().where('R').mapsTo(Items.ghast_tear).outputs(GOAItem.itemHyottokoMask).build();
		mask().where('R').mapsTo(Items.rotten_flesh).outputs(GOAItem.itemKoomoteMask).build();
		mask().where('R').mapsTo(Items.fermented_spider_eye).outputs(GOAItem.itemMaskOfHope).build();
		mask().where('R').mapsTo(Items.mushroom_stew).outputs(GOAItem.itemMonkeyMask).build();
		mask().where('R').mapsTo("slimeball").outputs(GOAItem.itemRaidenMask).build();
		mask().where('R').mapsTo(Items.nether_wart).outputs(GOAItem.itemUbaMask).build();

		//@formatter:on
	}

	private static void addOreDictRecipe(ItemStack output, Object... recipe) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, recipe));
	}

	private static void addShapelessOreDictRecipe(ItemStack output, Object... recipe) {
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(output, recipe));
	}

	private static ShapedRecipe shaped() {
		return new ShapedRecipe();
	}

	private static ShapelessRecipe shapeless() {
		return new ShapelessRecipe();
	}

	private static ShapedRecipe mask() {
		return new ShapedRecipe().grid("IAR", "SGS", " S ")
				.where('S').mapsTo(GOAItem.itemVolatileString)
				.where('G').mapsTo(GOAItem.itemMask)
				.where('A').mapsTo(Items.nether_star)
				.where('I').mapsTo(new ItemStack(Items.potionitem, 1, 16));
	}
}
