/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item.crafting;

import arekkuusu.grimoireofalice.common.GrimoireOfAlice;
import arekkuusu.grimoireofalice.common.block.ModBlocks;
import arekkuusu.grimoireofalice.common.core.handler.ConfigHandler;
import arekkuusu.grimoireofalice.common.item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class VanillaCrafting {

	public static void booksAndStrings() {
		//Items
		shaped().grid("SSS", "SAS", "SSS")
				.where('S').mapsTo("paper")
				.where('A').mapsTo(Items.CLAY_BALL)
				.outputs(new ItemStack(ModItems.SOLDIFIED_PAPER, 1)).build();

		//Blocks
		shaped().grid("SSS", "SSS", "SSS")
				.where('S').mapsTo(Items.SUGAR)
				.outputs(ModBlocks.SUGAR_BLOCK).build();

		shaped().grid("ASA", "IWI", "ASA")
				.where('W').mapsTo("logWood")
				.where('I').mapsTo("nuggetIron")
				.where('S').mapsTo(Items.STICK)
				.where('A').mapsTo(ModItems.PASTE)
				.outputs(new ItemStack(ModBlocks.ROPE_BLOCK, 8)).build();

		shaped().grid(" S ", " A ", "AAA")
				.where('A').mapsTo("paper")
				.where('S').mapsTo(ModItems.SOLDIFIED_PAPER)
				.outputs(new ItemStack(ModBlocks.PAPER_BLOCK, 8)).build();

		shaped().grid("AAA", "ASA", "AAA")
				.where('A').mapsTo("stone")
				.where('S').mapsTo(new ItemStack(Blocks.DIRT, 1, 1))
				.outputs(ModBlocks.COMPACT_STONE).build();

		shaped().grid("RLR", "PGP")
				.where('G').mapsTo(ModBlocks.COMPACT_STONE)
				.where('L').mapsTo("logWood")
				.where('R').mapsTo(ModBlocks.ROPE_BLOCK)
				.where('P').mapsTo(ModBlocks.PAPER_BLOCK)
				.outputs(ModBlocks.PILLAR_ALTAR).build();

		shaped().grid("FFF","FIF","FFF")
				.where('F').mapsTo(Items.FEATHER)
				.where('I').mapsTo(new ItemStack(Items.DYE, 1, 0))
				.outputs(new ItemStack(ModItems.BLACK_FEATHER, 2)).build();

		shaped().grid("PBP", "WCW", "WDW")
				.where('P').mapsTo("paper")
				.where('B').mapsTo(Items.BOOK)
				.where('W').mapsTo("plankWood")
				.where('D').mapsTo(ModBlocks.COMPACT_STONE)
				.where('C').mapsTo(Blocks.CRAFTING_TABLE)
				.outputs(ModBlocks.ALTAR).build();

		shaped().grid("N N","PLP","N N")
				.where('N').mapsTo("nuggetIron")
				.where('P').mapsTo(Items.PAPER)
				.where('L').mapsTo(Items.LEATHER)
				.outputs(ModItems.HARDENED_LEATHER).build();

		shaped().grid("NNN","NNN","NNN")
				.where('N').mapsTo(ModItems.IRON_NUGGET)
				.outputs(Items.IRON_INGOT).build();

		shapeless()
				.add(ModBlocks.SUGAR_BLOCK)
				.outputs(new ItemStack(Items.SUGAR, 9)).build();

		shapeless()
				.add(Items.IRON_INGOT)
				.outputs(new ItemStack(ModItems.IRON_NUGGET, 9)).build();

		shapeless()
				.add(ModItems.SOLDIFIED_PAPER)
				.add(Items.STRING)
				.add(Items.WATER_BUCKET)
				.add(Items.CLAY_BALL)
				.outputs(new ItemStack(ModItems.PASTE, 2)).build();

		shapeless()
				.add(Items.STICK)
				.add(Items.BOWL)
				.outputs(ModItems.MORTAR_AND_PESTLE).build();

		if(ConfigHandler.grimoireOfAlice.crafting.mask) {
			shapeless()
				.add(ModItems.PASTE)
				.add(ModItems.SOLDIFIED_PAPER)
				.add(ModItems.SOLDIFIED_PAPER)
				.outputs(ModItems.MASK).build();
		}

		if(ConfigHandler.grimoireOfAlice.crafting.hihiirokane) {
			shapeless()
					.add(ModItems.IMPURE_ROCK)
					.add(Blocks.COAL_BLOCK)
					.outputs(ModItems.HIHIIROKANE).build();
		}

		if (ConfigHandler.grimoireOfAlice.crafting.hihiirokaneBlock) {
			shaped().grid("HHH","HHH","HHH")
					.where('H').mapsTo(ModItems.HIHIIROKANE)
					.outputs(ModBlocks.HIHIIROKANE_BLOCK).build();
		}

		if (ConfigHandler.grimoireOfAlice.crafting.shimenawaRope) {
			shaped().grid("RRR","PPP")
					.where('R').mapsTo(ModBlocks.ROPE_BLOCK)
					.where('P').mapsTo(ModBlocks.PAPER_BLOCK)
					.outputs(ModItems.SHIMENAWA_ROPE).build();
		}

			if (ConfigHandler.grimoireOfAlice.crafting.popsicleStick) {
			shaped().grid("  S", "SS ", "SS ")
					.where('S').mapsTo("stickWood")
					.outputs(ModItems.POPSICLE_STICK).build();
		}

		if (ConfigHandler.grimoireOfAlice.crafting.waterMelonBlade) {
			shaped().grid("  M", " M ", "MS ")
					.where('M').mapsTo(Items.MELON)
					.where('S').mapsTo("stickWood")
					.outputs(ModItems.WATERMELON_BLADE).build();
		}

		if (ConfigHandler.grimoireOfAlice.crafting.waterMelonSword) {
			shapeless()
					.add(ModItems.POPSICLE_STICK)
					.add(Blocks.MELON_BLOCK)
					.add(Blocks.ICE)
					.outputs(ModItems.WATERMELON_SWORD).build();
		}

		if (ConfigHandler.grimoireOfAlice.crafting.cattailPlant) {
			shaped().grid("AE ", " AS", " SA")
					.where('A').mapsTo(Blocks.VINE)
					.where('E').mapsTo(Items.SLIME_BALL)
					.where('S').mapsTo("stickWood")
					.outputs(ModItems.CATTAIL_PLANT).build();
		}

		if (ConfigHandler.grimoireOfAlice.crafting.ghastlySendOffLantern) {
			shaped().grid("EAE", "ASA", "EAE")
					.where('A').mapsTo(Blocks.GLASS_PANE)
					.where('E').mapsTo(ModItems.SOLDIFIED_PAPER)
					.where('S').mapsTo(Blocks.TORCH)
					.outputs(new ItemStack(ModItems.GHASTLY_SEND_OFF_LANTERN, 4)).build();
		}

		if (ConfigHandler.grimoireOfAlice.crafting.patchyBook) {
			shapeless()
					.add(Items.WRITABLE_BOOK)
					.add("stickWood")
					.add(Items.FEATHER)
					.outputs(ModItems.PATCHY_BOOK).build();
		}

		if (ConfigHandler.grimoireOfAlice.food.grilledLamprey) {
			shapeless()
					.add(Items.COOKED_FISH)
					.add(Items.COOKED_FISH)
					.add(Items.COOKED_FISH)
					.add(Items.COOKED_FISH)
					.add(Items.COOKED_FISH)
					.add("stickWood")
					.outputs(ModItems.GRILLED_LAMPREY).build();
		}

		if (ConfigHandler.grimoireOfAlice.crafting.syringe) {
			shapeless()
					.add(Items.GLASS_BOTTLE)
					.add(new ItemStack(ModItems.SHROOM_POWDER, 1, OreDictionary.WILDCARD_VALUE))
					.outputs(ModItems.SYRINGE).build();
		}

		if (ConfigHandler.grimoireOfAlice.crafting.simpleUfo) {
			shaped().grid("GGG", "GIG", "B B")
					.where('I').mapsTo(Blocks.SLIME_BLOCK)
					.where('B').mapsTo(ModItems.PASTE)
					.where('G').mapsTo("blockGlassGreen")
					.outputs(ModItems.UFO_GREEN).build();

			shaped().grid("GGG", "GIG", "B B")
					.where('I').mapsTo(Blocks.SLIME_BLOCK)
					.where('B').mapsTo(ModItems.PASTE)
					.where('G').mapsTo("blockGlassRed")
					.outputs(ModItems.UFO_RED).build();

			shaped().grid("GGG", "GIG", "B B")
					.where('I').mapsTo(Blocks.SLIME_BLOCK)
					.where('B').mapsTo(ModItems.PASTE)
					.where('G').mapsTo("blockGlassBlue")
					.outputs(ModItems.UFO_BLUE).build();
		}

		if (ConfigHandler.grimoireOfAlice.food.shroomPowder) {
			for (int i = 0; i < 16; i++)
				shapeless()
						.add(new ItemStack(ModBlocks.SHROOM, 1, i))
						.add(new ItemStack(ModItems.MORTAR_AND_PESTLE, 1, OreDictionary.WILDCARD_VALUE))
						.outputs(new ItemStack(ModItems.SHROOM_POWDER, 1, 15 - i)).build();
		}

		if(GrimoireOfAlice.danmakuCoreInstalled){
			shapeless()
					.add("treeLeaves")
					.outputs(ModItems.LEAF).build();
		}

		GameRegistry.addSmelting(Items.QUARTZ, new ItemStack(ModItems.TAMAHAGANE_STEEL), 1);

		GameRegistry.addSmelting(ModBlocks.IMPURE_STONE, new ItemStack(ModItems.IMPURE_ROCK), 1);
	}

	public static void masks() {
		if (ConfigHandler.grimoireOfAlice.crafting.kokoroMask) {
			shapeless()
					.add(ModItems.FOX_MASK)
					.add(ModItems.FUKU_NO_KAMI_MASK)
					.add(ModItems.HANNYA_MASK)
					.add(ModItems.HYOTTOKO_MASK)
					.add(ModItems.KOOMOTE_MASK)
					.add(ModItems.MASK_OF_HOPE)
					.add(ModItems.MONKEY_MASK)
					.add(ModItems.RAIDEN_MASK)
					.add(ModItems.UBA_MASK)
					.outputs(ModItems.KOKOROS_MASKS).build();

			mask().where('R').mapsTo(Items.SUGAR).outputs(ModItems.FOX_MASK).build();
			mask().where('R').mapsTo(Items.BLAZE_POWDER).outputs(ModItems.FUKU_NO_KAMI_MASK).build();
			mask().where('R').mapsTo(Items.POISONOUS_POTATO).outputs(ModItems.HANNYA_MASK).build();
			mask().where('R').mapsTo(Items.GHAST_TEAR).outputs(ModItems.HYOTTOKO_MASK).build();
			mask().where('R').mapsTo(Items.ROTTEN_FLESH).outputs(ModItems.KOOMOTE_MASK).build();
			mask().where('R').mapsTo(Items.FERMENTED_SPIDER_EYE).outputs(ModItems.MASK_OF_HOPE).build();
			mask().where('R').mapsTo(Items.MUSHROOM_STEW).outputs(ModItems.MONKEY_MASK).build();
			mask().where('R').mapsTo("slimeball").outputs(ModItems.RAIDEN_MASK).build();
			mask().where('R').mapsTo(Items.NETHER_STAR).outputs(ModItems.UBA_MASK).build();
		}
	}

	private static ShapedRecipe shaped() {
		return new ShapedRecipe();
	}

	private static ShapelessRecipe shapeless() {
		return new ShapelessRecipe();
	}

	private static ShapedRecipe mask() {
		return new ShapedRecipe().grid("IAR", "SGS", " S ")
				.where('S').mapsTo(ModItems.PASTE)
				.where('G').mapsTo(ModItems.MASK)
				.where('A').mapsTo(ModItems.NETHER_SHARD)
				.where('I').mapsTo(new ItemStack(Items.POTIONITEM, 1, 16));
	}
}
