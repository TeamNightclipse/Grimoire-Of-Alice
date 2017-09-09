/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item.crafting;

import arekkuusu.grimoireofalice.common.block.ModBlocks;
import arekkuusu.grimoireofalice.common.core.handler.ConfigHandler;
import arekkuusu.grimoireofalice.common.item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class VanillaCrafting {

	public static void booksAndStrings() {
		//Items
		shaped().grid("SSS", "SAS", "SSS")
				.where('S').mapsTo("paper")
				.where('A').mapsTo(Items.CLAY_BALL)
				.outputs(new ItemStack(ModItems.solidified_paper, 1)).build();

		//Blocks
		shaped().grid("SSS", "SSS", "SSS")
				.where('S').mapsTo(Items.SUGAR)
				.outputs(ModBlocks.sugar_block).build();

		shaped().grid("ASA", "IWI", "ASA")
				.where('W').mapsTo("logWood")
				.where('I').mapsTo("nuggetIron")
				.where('S').mapsTo(Items.STICK)
				.where('A').mapsTo(ModItems.paste)
				.outputs(new ItemStack(ModBlocks.rope_block, 8)).build();

		shaped().grid(" S ", " A ", "AAA")
				.where('A').mapsTo("paper")
				.where('S').mapsTo(ModItems.solidified_paper)
				.outputs(new ItemStack(ModBlocks.paper_block, 8)).build();

		shaped().grid("AAA", "ASA", "AAA")
				.where('A').mapsTo("stone")
				.where('S').mapsTo(new ItemStack(Blocks.DIRT, 1, 1))
				.outputs(ModBlocks.compact_stone).build();

		shaped().grid("RLR", "PGP")
				.where('G').mapsTo(ModBlocks.compact_stone)
				.where('L').mapsTo("logWood")
				.where('R').mapsTo(ModBlocks.rope_block)
				.where('P').mapsTo(ModBlocks.paper_block)
				.outputs(ModBlocks.pillar_altar).build();

		shaped().grid("FFF", "FIF", "FFF")
				.where('F').mapsTo(Items.FEATHER)
				.where('I').mapsTo("dyeBlack")
				.outputs(new ItemStack(ModItems.black_feather, 2)).build();

		shaped().grid("PBP", "WCW", "WDW")
				.where('P').mapsTo("paper")
				.where('B').mapsTo(Items.BOOK)
				.where('W').mapsTo("plankWood")
				.where('D').mapsTo(ModBlocks.compact_stone)
				.where('C').mapsTo(Blocks.CRAFTING_TABLE)
				.outputs(ModBlocks.crafting_altar).build();

		shaped().grid("N N", "PLP", "N N")
				.where('N').mapsTo("nuggetIron")
				.where('P').mapsTo(Items.PAPER)
				.where('L').mapsTo(Items.LEATHER)
				.outputs(ModItems.hardened_leather).build();

		shaped().grid("NNN", "NNN", "NNN")
				.where('N').mapsTo(ModItems.iron_nugget)
				.outputs(Items.IRON_INGOT).build();

		shapeless()
				.add(ModBlocks.sugar_block)
				.outputs(new ItemStack(Items.SUGAR, 9)).build();

		shapeless()
				.add(Items.IRON_INGOT)
				.outputs(new ItemStack(ModItems.iron_nugget, 9)).build();

		shapeless()
				.add(ModItems.solidified_paper)
				.add(Items.STRING)
				.add(Items.WATER_BUCKET)
				.add(Items.CLAY_BALL)
				.outputs(new ItemStack(ModItems.paste, 2)).build();

		shapeless()
				.add(Items.STICK)
				.add(Items.BOWL)
				.outputs(ModItems.mortar_n_pestle).build();

		if(ConfigHandler.grimoireOfAlice.crafting.mask) {
			shapeless()
					.add(ModItems.paste)
					.add(ModItems.solidified_paper)
					.add(ModItems.solidified_paper)
					.outputs(ModItems.mask).build();
		}

		if(ConfigHandler.grimoireOfAlice.crafting.hihiirokane) {
			shapeless()
					.add(ModItems.impure_rock)
					.add(Blocks.COAL_BLOCK)
					.outputs(ModItems.hihiirokane).build();
		}

		if(ConfigHandler.grimoireOfAlice.crafting.hihiirokaneBlock) {
			shaped().grid("HHH", "HHH", "HHH")
					.where('H').mapsTo(ModItems.hihiirokane)
					.outputs(ModBlocks.hihiirokane_block).build();
		}

		if(ConfigHandler.grimoireOfAlice.crafting.shimenawaRope) {
			shaped().grid("RRR", "PPP")
					.where('R').mapsTo(ModBlocks.rope_block)
					.where('P').mapsTo(ModBlocks.paper_block)
					.outputs(ModItems.shimenawa_rope).build();
		}

		if(ConfigHandler.grimoireOfAlice.crafting.popsicleStick) {
			shaped().grid("  S", "SS ", "SS ")
					.where('S').mapsTo("stickWood")
					.outputs(ModItems.popsicle_stick).build();
		}

		if(ConfigHandler.grimoireOfAlice.crafting.waterMelonBlade) {
			shaped().grid("  M", " M ", "MS ")
					.where('M').mapsTo(Items.MELON)
					.where('S').mapsTo("stickWood")
					.outputs(ModItems.watermelon_blade).build();
		}

		if(ConfigHandler.grimoireOfAlice.crafting.hakurouken) {
			shaped().grid(" I ", "S  ")
					.where('S').mapsTo(Items.STICK)
					.where('I').mapsTo(Items.IRON_INGOT)
					.outputs(ModItems.hakurouken).build();
		}

		if(ConfigHandler.grimoireOfAlice.crafting.tenshiHat) {
			shaped().grid("F F", "CHC")
					.where('F').mapsTo(ModItems.heavenly_peach)
					.where('H').mapsTo(Items.LEATHER_HELMET)
					.where('C').mapsTo(new ItemStack(Blocks.CARPET, 1, 15))
					.outputs(ModItems.tenshi_hat).build();
		}

		if(ConfigHandler.grimoireOfAlice.crafting.talisman) {
			shaped().grid("G  ", " P ", "  G")
					.where('G').mapsTo(Items.GLOWSTONE_DUST)
					.where('P').mapsTo(ModItems.solidified_paper)
					.outputs(ModItems.spiritual_strike_talisman).build();
		}

		if(ConfigHandler.grimoireOfAlice.crafting.waterMelonSword) {
			shapeless()
					.add(ModItems.popsicle_stick)
					.add(Blocks.MELON_BLOCK)
					.add(Blocks.ICE)
					.outputs(ModItems.watermelon_sword).build();
		}

		if(ConfigHandler.grimoireOfAlice.crafting.cattailPlant) {
			shaped().grid("AE ", " AS", " SA")
					.where('A').mapsTo(Blocks.VINE)
					.where('E').mapsTo(Items.SLIME_BALL)
					.where('S').mapsTo("stickWood")
					.outputs(ModItems.cattail_plant).build();
		}

		if(ConfigHandler.grimoireOfAlice.crafting.ghastlySendOffLantern) {
			shaped().grid("EAE", "ASA", "EAE")
					.where('A').mapsTo(Blocks.GLASS_PANE)
					.where('E').mapsTo(ModItems.solidified_paper)
					.where('S').mapsTo(Blocks.TORCH)
					.outputs(new ItemStack(ModItems.send_off_lantern, 4)).build();
		}

		if(ConfigHandler.grimoireOfAlice.crafting.patchyBook) {
			shapeless()
					.add(Items.WRITABLE_BOOK)
					.add("stickWood")
					.add(Items.FEATHER)
					.outputs(ModItems.patchy_book).build();
		}

		if(ConfigHandler.grimoireOfAlice.food.grilledLamprey) {
			shapeless()
					.add(Items.COOKED_FISH)
					.add(Items.COOKED_FISH)
					.add(Items.COOKED_FISH)
					.add(Items.COOKED_FISH)
					.add(Items.COOKED_FISH)
					.add("stickWood")
					.outputs(ModItems.grilled_lamprey).build();
		}

		if(ConfigHandler.grimoireOfAlice.crafting.syringe) {
			shapeless()
					.add(Items.GLASS_BOTTLE)
					.add(new ItemStack(ModItems.shroom_powder, 1, OreDictionary.WILDCARD_VALUE))
					.outputs(ModItems.syringe).build();
		}

		if(ConfigHandler.grimoireOfAlice.crafting.simpleUfo) {
			shaped().grid("GGG", "GIG", "B B")
					.where('I').mapsTo(Blocks.SLIME_BLOCK)
					.where('B').mapsTo(ModItems.paste)
					.where('G').mapsTo("blockGlassGreen")
					.outputs(ModItems.ufo_green).build();

			shaped().grid("GGG", "GIG", "B B")
					.where('I').mapsTo(Blocks.SLIME_BLOCK)
					.where('B').mapsTo(ModItems.paste)
					.where('G').mapsTo("blockGlassRed")
					.outputs(ModItems.ufo_red).build();

			shaped().grid("GGG", "GIG", "B B")
					.where('I').mapsTo(Blocks.SLIME_BLOCK)
					.where('B').mapsTo(ModItems.paste)
					.where('G').mapsTo("blockGlassBlue")
					.outputs(ModItems.ufo_blue).build();
		}

		if(ConfigHandler.grimoireOfAlice.food.shroomPowder) {
			for(int i = 0; i < 16; i++)
				shapeless()
						.add(new ItemStack(ModBlocks.shroom, 1, i))
						.add(new ItemStack(ModItems.mortar_n_pestle, 1, OreDictionary.WILDCARD_VALUE))
						.outputs(new ItemStack(ModItems.shroom_powder, 1, 15 - i)).build();
		}

		shapeless()
				.add("treeLeaves")
				.outputs(ModItems.leaf_item).build();

		GameRegistry.addSmelting(Items.BLAZE_ROD, new ItemStack(ModItems.tamahagane_steel), 1);

		GameRegistry.addSmelting(ModBlocks.impure_stone, new ItemStack(ModItems.impure_rock), 1);
	}

	public static void masks() {
		if(ConfigHandler.grimoireOfAlice.crafting.kokoroMask) {
			shapeless()
					.add(ModItems.fox_mask)
					.add(ModItems.fuku_no_kami_mask)
					.add(ModItems.hannya_mask)
					.add(ModItems.hyottoko_mask)
					.add(ModItems.koomote_mask)
					.add(ModItems.mask_of_hope)
					.add(ModItems.monkey_mask)
					.add(ModItems.raiden_mask)
					.add(ModItems.uba_mask)
					.outputs(ModItems.kokoro_masks).build();

			mask(Items.SUGAR, ModItems.fox_mask);
			mask(Items.BLAZE_POWDER, ModItems.fuku_no_kami_mask);
			mask(Items.POISONOUS_POTATO, ModItems.hannya_mask);
			mask(Items.GHAST_TEAR, ModItems.hyottoko_mask);
			mask(Items.ROTTEN_FLESH, ModItems.koomote_mask);
			mask(Items.FERMENTED_SPIDER_EYE, ModItems.mask_of_hope);
			mask(Items.MUSHROOM_STEW, ModItems.monkey_mask);
			rawMask().where('R').mapsTo("slimeball").outputs(ModItems.raiden_mask).build();
			mask(Items.NETHER_STAR, ModItems.uba_mask);
		}
	}

	private static ShapedRecipe shaped() {
		return new ShapedRecipe();
	}

	private static ShapelessRecipe shapeless() {
		return new ShapelessRecipe();
	}

	private static ShapedRecipe rawMask() {
		return new ShapedRecipe().grid("IAR", "SGS", " S ")
				.where('S').mapsTo(ModItems.paste)
				.where('G').mapsTo(ModItems.mask)
				.where('A').mapsTo(ModItems.nether_shard)
				.where('I').mapsTo(new ItemStack(Items.POTIONITEM, 1, 16));
	}

	private static void mask(Item item, Item output) {
		rawMask().where('R').mapsTo(item).outputs(item).build();
	}
}
