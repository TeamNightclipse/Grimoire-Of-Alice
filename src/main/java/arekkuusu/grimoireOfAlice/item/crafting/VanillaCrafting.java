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
import arekkuusu.grimoireOfAlice.tmp.CleanupDone;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

@CleanupDone
public class VanillaCrafting {

	public static void booksAndStrings() {

		//@formatter:off
		shaped().grid("ISG", "SAS", "GSI")
				.where('S').mapsTo(Items.string)
				.where('G').mapsTo("dustGlowstone")
				.where('I').mapsTo(Items.gunpowder)
				.where('A').mapsTo(Items.firework_charge)
				.outputs(new ItemStack(GOAItem.volatileString, 16)).build();

		shaped().grid("HHH", "STS", "STS")
				.where('S').mapsTo(GOAItem.volatileString)
				.where('H').mapsTo(GOABlock.ropeBlock)
				.where('T').mapsTo(GOAItem.soldifiedPaper)
				.outputs(GOAItem.shimenawaRope).build();

		shaped().grid("STS", "TAT", "STS")
				.where('S').mapsTo(Items.paper)
				.where('A').mapsTo(Items.coal)
				.where('T').mapsTo("stickWood")
				.outputs(new ItemStack(GOAItem.soldifiedPaper, 4)).build();

		shaped().grid("SSS", "SA ", "SA ")
				.where('S').mapsTo(GOAItem.gloriousNipponSteel)
				.where('A').mapsTo("stickWood")
				.mirrored(true)
				.outputs(GOAItem.momijisScimitarSword).build();

		shaped().grid("SSG", " A ", " A ")
				.where('G').mapsTo(GOAItem.gloriousNipponSteel)
				.where('A').mapsTo("stickWood")
				.where('S').mapsTo("ingotBrick")
				.outputs(GOAItem.mochiHammer).build();

		shaped().grid("SSS", "SSS", "SSS")
				.where('S').mapsTo(Items.sugar)
				.outputs(GOABlock.sugarBlock).build();

		shaped().grid("ACA", "CSC", "ACA")
				.where('S').mapsTo("logWood")
				.where('A').mapsTo(GOAItem.volatileString)
				.where('C').mapsTo(Items.coal)
				.outputs(new ItemStack(GOABlock.ropeBlock, 8)).build();

		shaped().grid("AAA", "ASA", "AAA")
				.where('A').mapsTo(Items.paper)
				.where('S').mapsTo(GOAItem.soldifiedPaper)
				.outputs(new ItemStack(GOABlock.paperBlock, 8)).build();

		shaped().grid("AAA", "ASA", "AAA")
				.where('A').mapsTo("stone")
				.where('S').mapsTo(GOAItem.volatileString)
				.outputs(GOABlock.compactStone).build();

		shaped().grid("AOA", "OSO", "AOA")
				.where('A').mapsTo(GOABlock.compactStone)
				.where('S').mapsTo(GOAItem.shimenawaRope)
				.where('O').mapsTo(GOABlock.hyperconcentratedMagic)
				.outputs(GOABlock.holyStone).build();

		shaped().grid("AHA", "HSH", "AHA")
				.where('A').mapsTo("logWood")
				.where('H').mapsTo(GOAItem.volatileString)
				.where('S').mapsTo(GOABlock.ropeBlock)
				.outputs(GOABlock.onbashira).build();

		shaped().grid("AHA", "HSH", "AHA")
				.where('A').mapsTo(GOAItem.volatileString)
				.where('H').mapsTo(GOABlock.compactStone)
				.where('S').mapsTo(GOABlock.shroom)
				.outputs(GOABlock.hyperconcentratedMagic).build();

		shaped().grid("AAA", " SA", " G ")
				.where('G').mapsTo(Items.coal)
				.where('A').mapsTo(GOAItem.gloriousNipponSteel)
				.where('S').mapsTo(Items.blaze_rod)
				.mirrored(true)
				.outputs(GOAItem.laevatein).build();

		shaped().grid("HAA", " SA", "GAA")
				.where('G').mapsTo(Items.bone)
				.where('A').mapsTo("dyeRed")
				.where('S').mapsTo(Items.speckled_melon)
				.where('H').mapsTo("ingotGold")
				.outputs(GOAItem.ellyScythe).build();

		shaped().grid("SNS", "SNS", "SNS")
				.where('S').mapsTo(GOAItem.soldifiedPaper)
				.where('N').mapsTo("logWood")
				.outputs(GOAItem.mikoStick).build();

		shaped().grid("SSS", "HSH", "SH ")
				.where('S').mapsTo(Items.flint)
				.where('H').mapsTo(GOAItem.soldifiedPaper)
				.outputs(GOAItem.nazrinStick).build();

		shaped().grid("STS", "HOH", " U ")
				.where('U').mapsTo(GOABlock.hyperconcentratedMagic)
				.where('H').mapsTo(GOAItem.soldifiedPaper)
				.where('S').mapsTo(GOABlock.paperBlock)
				.where('T').mapsTo(GOABlock.holyStone)
				.where('O').mapsTo(Items.nether_star)
				.outputs(GOAItem.crestOfYggdrasill).build();

		shaped().grid("STS", "HUG", "DUD")
				.where('U').mapsTo(GOABlock.hyperconcentratedMagic)
				.where('H').mapsTo("blockEmerald")
				.where('S').mapsTo(GOABlock.compactStone)
				.where('T').mapsTo(Blocks.beacon)
				.where('G').mapsTo(Items.diamond)
				.where('D').mapsTo(Items.nether_star)
				.outputs(GOAItem.amenonuhoko).build();

		shaped().grid("SQS", "QTQ", "OUO")
				.where('Q').mapsTo(GOABlock.hyperconcentratedMagic)
				.where('S').mapsTo(Items.nether_star)
				.where('U').mapsTo(GOABlock.compactStone)
				.where('T').mapsTo(Blocks.beacon)
				.where('O').mapsTo(GOABlock.holyStone)
				.outputs(GOAItem.primordialShield).build();

		shapeless()
				.add(GOABlock.sugarBlock)
				.outputs(new ItemStack(Items.sugar, 9)).build();

		shapeless()
				.add(GOABlock.shroom)
				.outputs(new ItemStack(GOAItem.shroomSlice, 1, 0)).build();

		shapeless()
				.add(GOAItem.shroomSlice)
				.add(Items.fermented_spider_eye)
				.add(Items.poisonous_potato)
				.outputs(new ItemStack(GOAItem.shroomSlice, 1, 1)).build();

		shapeless()
				.add(Items.leather)
				.add(Items.book)
				.add(GOAItem.volatileString)
				.add(Items.feather)
				.outputs(GOAItem.youkaiBook).build();

		//@formatter:on

		GameRegistry.addSmelting(Items.iron_sword, new ItemStack(GOAItem.gloriousNipponSteel), 0);
	}

	public static void masks() {

		//@formatter:off

		shapeless()
				.add(GOAItem.foxMask)
				.add(GOAItem.fukuNoKamiMask)
				.add(GOAItem.hannyaMask)
				.add(GOAItem.hyottokoMask)
				.add(GOAItem.koomoteMask)
				.add(GOAItem.maskOfHope)
				.add(GOAItem.nonkeyMask)
				.add(GOAItem.raidenMask)
				.add(GOAItem.ubaMask)
				.outputs(GOAItem.kokorosMasks).build();

		shaped().grid("SSS", "GAG", "SSS")
				.where('S').mapsTo(GOAItem.soldifiedPaper)
				.where('G').mapsTo(GOAItem.volatileString)
				.where('A').mapsTo(Items.clay_ball)
				.outputs(GOAItem.mask).build();

		mask().where('R').mapsTo(Items.sugar).outputs(GOAItem.foxMask).build();
		mask().where('R').mapsTo(Items.blaze_powder).outputs(GOAItem.fukuNoKamiMask).build();
		mask().where('R').mapsTo(Items.poisonous_potato).outputs(GOAItem.hannyaMask).build();
		mask().where('R').mapsTo(Items.ghast_tear).outputs(GOAItem.hyottokoMask).build();
		mask().where('R').mapsTo(Items.rotten_flesh).outputs(GOAItem.koomoteMask).build();
		mask().where('R').mapsTo(Items.fermented_spider_eye).outputs(GOAItem.maskOfHope).build();
		mask().where('R').mapsTo(Items.mushroom_stew).outputs(GOAItem.nonkeyMask).build();
		mask().where('R').mapsTo("slimeball").outputs(GOAItem.raidenMask).build();
		mask().where('R').mapsTo(Items.nether_wart).outputs(GOAItem.ubaMask).build();

		//@formatter:on
	}

	private static ShapedRecipe shaped() {
		return new ShapedRecipe();
	}

	private static ShapelessRecipe shapeless() {
		return new ShapelessRecipe();
	}

	private static ShapedRecipe mask() {
		return new ShapedRecipe().grid("IAR", "SGS", " S ")
				.where('S').mapsTo(GOAItem.volatileString)
				.where('G').mapsTo(GOAItem.mask)
				.where('A').mapsTo(Items.nether_star)
				.where('I').mapsTo(new ItemStack(Items.potionitem, 1, 16));
	}
}
