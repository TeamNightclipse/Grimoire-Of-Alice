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
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class VanillaCrafting {

	public static void booksAndStrings() {

		//Items
		shapeless()
				.add(Items.STRING)
				.add(Items.STRING)
				.add(Items.GUNPOWDER)
				.outputs(new ItemStack(ModItems.VOLATILE_STRING, 1)).build();

		shaped().grid("SSS", "SAS", "SSS")
				.where('S').mapsTo("paper")
				.where('A').mapsTo(Items.CLAY_BALL)
				.outputs(new ItemStack(ModItems.SOLDIFIED_PAPER, 1)).build();

		//Blocks
		shaped().grid("SSS", "SSS", "SSS")
				.where('S').mapsTo(Items.SUGAR)
				.outputs(ModBlocks.SUGAR_BLOCK).build();

		shaped().grid("AEA", "ESE", "AEA")
				.where('S').mapsTo("logWood")
				.where('E').mapsTo(Items.CLAY_BALL)
				.where('A').mapsTo(ModItems.VOLATILE_STRING)
				.outputs(new ItemStack(ModBlocks.ROPE_BLOCK, 8)).build();

		shaped().grid("AAA", "ASA", "AAA")
				.where('A').mapsTo("paper")
				.where('S').mapsTo(ModItems.SOLDIFIED_PAPER)
				.outputs(new ItemStack(ModBlocks.PAPER_BLOCK, 8)).build();

		shaped().grid("AAA", "ASA", "AAA")
				.where('A').mapsTo("stone")
				.where('S').mapsTo(ModItems.VOLATILE_STRING)
				.outputs(ModBlocks.COMPACT_STONE).build();

		shaped().grid("LLL", "LGL")
				.where('G').mapsTo(ModBlocks.COMPACT_STONE)
				.where('L').mapsTo("plankWood")
				.outputs(ModBlocks.PILLAR_ALTAR).build();

		shaped().grid("PBP", "WCW", "WDW")
				.where('P').mapsTo("paper")
				.where('B').mapsTo(Items.BOOK)
				.where('W').mapsTo("plankWood")
				.where('D').mapsTo(ModBlocks.COMPACT_STONE)
				.where('C').mapsTo(Blocks.CRAFTING_TABLE)
				.outputs(ModBlocks.ALTAR).build();

		shapeless()
				.add(Items.STICK)
				.add(Items.BOWL)
				.outputs(ModItems.MORTAR_AND_PESTLE).build();

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
					.outputs(ModItems.PATCHY_BOOK);
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

		if (ConfigHandler.grimoireOfAlice.crafting.needle) {
			shapeless()
					.add(Items.IRON_INGOT)
					.add(Items.FEATHER)
					.outputs(ModItems.NEEDLE).build();
		}

		if (ConfigHandler.grimoireOfAlice.crafting.syringe) {
			shapeless()
					.add(Items.GLASS_BOTTLE)
					.add(new ItemStack(ModItems.SHROOM_POWDER, 1, OreDictionary.WILDCARD_VALUE))
					.outputs(ModItems.SYRINGE).build();
		}

		if (ConfigHandler.grimoireOfAlice.crafting.ufo) {
			shaped().grid("GGG", "GIG", "BBB")
					.where('I').mapsTo(Items.PRISMARINE_CRYSTALS)
					.where('B').mapsTo(Items.CLAY_BALL)
					.where('G').mapsTo("blockGlassGreen")
					.outputs(ModItems.UFO_GREEN).build();

			shaped().grid("GGG", "GIG", "BBB")
					.where('I')
					.mapsTo(Items.PRISMARINE_CRYSTALS)
					.where('B').mapsTo(Items.CLAY_BALL)
					.where('G').mapsTo("blockGlassRed")
					.outputs(ModItems.UFO_RED).build();

			shaped().grid("GGG", "GIG", "BBB")
					.where('I').mapsTo(Items.PRISMARINE_CRYSTALS)
					.where('B').mapsTo(Items.CLAY_BALL)
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

		GameRegistry.addSmelting(Blocks.QUARTZ_BLOCK, new ItemStack(ModItems.TAMAHAGANE_STEEL), 0);

		GameRegistry.addSmelting(ModBlocks.IMPURE_STONE, new ItemStack(ModItems.IMPURE_ROCK), 1);
	}

	public static void masks() {
		if (ConfigHandler.grimoireOfAlice.crafting.masks.vanillaMaskRecipes) {
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
				.where('S').mapsTo(ModItems.VOLATILE_STRING)
				.where('G').mapsTo(ModItems.MASK)
				.where('A').mapsTo(Items.NETHER_STAR)
				.where('I').mapsTo(new ItemStack(Items.POTIONITEM, 1, 16));
	}
}