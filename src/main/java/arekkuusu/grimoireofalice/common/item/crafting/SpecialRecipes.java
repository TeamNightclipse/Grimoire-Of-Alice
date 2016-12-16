/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item.crafting;

import arekkuusu.grimoireofalice.api.GrimoireOfAliceAPI;
import arekkuusu.grimoireofalice.api.recipes.IRecipeItems;
import arekkuusu.grimoireofalice.common.GrimoireOfAlice;
import arekkuusu.grimoireofalice.common.block.ModBlocks;
import arekkuusu.grimoireofalice.common.core.handler.ConfigHandler;
import arekkuusu.grimoireofalice.common.item.ItemNazrinStick;
import arekkuusu.grimoireofalice.common.item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.oredict.OreDictionary;

public final class SpecialRecipes {

	//Masks
	public static IRecipeItems MASK;
	public static IRecipeItems FOX_MASK;
	public static IRecipeItems FUKU_NO_KAMI_MASK;
	public static IRecipeItems HANNYA_MASK;
	public static IRecipeItems HYOTTOKO_MASK;
	public static IRecipeItems KOOMOTE_MASK;
	public static IRecipeItems MASK_OF_HOPE;
	public static IRecipeItems MONKEY_MASK;
	public static IRecipeItems RAIDEN_MASK;
	public static IRecipeItems UBA_MASK;
	public static IRecipeItems KOKOROS_MASKS;

	//General
	public static IRecipeItems THIRD_EYE;
	public static IRecipeItems AMENONUHOKO;
	public static IRecipeItems BLOOD_THIRSTY_ORB;
	public static IRecipeItems BUDAH_BOUL;
	public static IRecipeItems CURSED_DECOY_DOLL;
	public static IRecipeItems DEATH_SCYTHE;
	public static IRecipeItems DRAGON_JEWEL;
	public static IRecipeItems FAKE_MIRACLE_MALLET;
	public static IRecipeItems FIRE_ROBE;
	public static IRecipeItems KANAKO_SHIMENAWA;
	public static IRecipeItems GAP_FOLDING_UMBRELLA;
	public static IRecipeItems GHOST_DIPPER;
	public static IRecipeItems GRIMOIRE_BOOK;
	public static IRecipeItems HAKUREI_GOHEI;
	public static IRecipeItems HIHIIROKANE;
	public static IRecipeItems IBARAKI_BOX_EMPTY;
	public static IRecipeItems ICHIRIN_AURA;
	public static IRecipeItems ICHIRIN_RING;
	public static IRecipeItems KAPPA_HAT;
	public static IRecipeItems MAPLE_LEAF_SHIELD;
	public static IRecipeItems MARISA_HAT;
	public static IRecipeItems MICO_CAPE;
	public static IRecipeItems MIKO_STICK;
	public static IRecipeItems MOCHI_HAMMER;
	public static IRecipeItems MOMIJIS_SCIMITAR_SWORD;
	public static IRecipeItems NAZRIN_PENDULUM;
	public static IRecipeItems NAZRIN_STICK_ONE;
	public static IRecipeItems NAZRIN_STICK_TWO;
	public static IRecipeItems NEEDLE;
	public static IRecipeItems NIMBLE_FABRIC;
	public static IRecipeItems NUE_TRIDENT;
	public static IRecipeItems IBUKI_GOURD;
	public static IRecipeItems ROD_OF_REMORSE;
	public static IRecipeItems RUMIA_SWORD;
	public static IRecipeItems SACRED_TOYOSATOMIMI_SWORD;
	public static IRecipeItems SANAE_GOHEI;
	public static IRecipeItems SARIEL_WAND;
	public static IRecipeItems SHICHI_SEIKEN;
	public static IRecipeItems SHIMENAWA_ROPE;
	public static IRecipeItems KANAKO_ONBASHIRA;
	public static IRecipeItems ORIN_SKULL;
	public static IRecipeItems STOP_WATCH;
	public static IRecipeItems SUBSTITUTE_JIZO;
	public static IRecipeItems SUWAKO_HAT;
	public static IRecipeItems SWALLOW_COWRIE_SHELL;
	public static IRecipeItems ROUKANKEN;
	public static IRecipeItems AYA_CAMERA;
	public static IRecipeItems HATATE_CAMERA;
	public static IRecipeItems UFOS;
	public static IRecipeItems WALL_PASSING_CHISEL;
	public static IRecipeItems YOUKAI_BOOK;
	public static IRecipeItems ULTRAMARINE_ORB_ELIXIR;
	public static IRecipeItems HOURAI_ELIXIR;
	public static IRecipeItems KAPPA_NOSTRUM;
	public static IRecipeItems SHINMYOUMARU_HAT;
	public static IRecipeItems YUUGI_SAKE;
	public static IRecipeItems TOYOSATOMIMI_HAT;
	public static IRecipeItems UTSUHO_WINGS;
	public static IRecipeItems NETHER_SHARD;

	//DanmakuCore
	public static IRecipeItems ELLY_SCYTHE;
	public static IRecipeItems JEWELED_HOURAI;
	public static IRecipeItems LEVATEIN;
	public static IRecipeItems MIRACLE_MALLET;
	public static IRecipeItems PIANO;
	public static IRecipeItems SHOU_LAMP;
	public static IRecipeItems SPELL_CARD_POUCH;
	public static IRecipeItems SWORD_OF_HISOU;
	public static IRecipeItems TENGU_FAN;
	public static IRecipeItems TRUMPET;
	public static IRecipeItems VIOLIN;

	//Bocks
	public static IRecipeItems HOLYSTONE;
	public static IRecipeItems HOLYKEYSTONE;
	public static IRecipeItems HIHIIROKANE_BLOCK;
	public static IRecipeItems ONBASHIRA;
	public static IRecipeItems END_CRYSTAL;

	private SpecialRecipes() {
	}

	public static void init() {
		//General
		MASK = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.MASK)
				, ModItems.PASTE, ModItems.SOLDIFIED_PAPER, ModItems.SOLDIFIED_PAPER);

		HIHIIROKANE = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.HIHIIROKANE)
				, Items.COAL, ModItems.IMPURE_ROCK, ModItems.IMPURE_ROCK, Items.COAL, ModItems.IMPURE_ROCK, Items.COAL, Items.COAL, ModItems.IMPURE_ROCK);

		if (ConfigHandler.grimoireOfAlice.crafting.masks.altarMaskRecipes) {
			ItemStack potion = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.AWKWARD);

			KOKOROS_MASKS = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.KOKOROS_MASKS), potion, ModItems.MASK, Items.NETHER_STAR, ModItems.FOX_MASK,
					ModItems.FUKU_NO_KAMI_MASK, ModItems.HANNYA_MASK, ModItems.HYOTTOKO_MASK, ModItems.KOOMOTE_MASK, ModItems.MASK_OF_HOPE,
					ModItems.MONKEY_MASK, ModItems.RAIDEN_MASK, ModItems.UBA_MASK);

			FOX_MASK = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.FOX_MASK), ModItems.MASK, ModItems.NETHER_SHARD,
					ModItems.HARDENED_LEATHER, potion, Items.SUGAR, new ItemStack(Items.DYE, 1, 0));

			FUKU_NO_KAMI_MASK = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.FUKU_NO_KAMI_MASK), ModItems.MASK, ModItems.NETHER_SHARD,
					ModItems.HARDENED_LEATHER, potion, Items.BLAZE_POWDER, new ItemStack(Items.DYE, 1, 14));

			HANNYA_MASK = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.HANNYA_MASK), ModItems.MASK, ModItems.NETHER_SHARD,
					ModItems.HARDENED_LEATHER, potion, Items.POISONOUS_POTATO, new ItemStack(Items.DYE, 1, 1));

			HYOTTOKO_MASK = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.HYOTTOKO_MASK), ModItems.MASK, ModItems.NETHER_SHARD,
					ModItems.HARDENED_LEATHER, potion, Items.GHAST_TEAR, new ItemStack(Items.DYE, 1, 9));

			KOOMOTE_MASK = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.KOOMOTE_MASK), ModItems.MASK, ModItems.NETHER_SHARD,
					ModItems.HARDENED_LEATHER, potion, Items.ROTTEN_FLESH, new ItemStack(Items.DYE, 1, 0));

			MASK_OF_HOPE = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.MASK_OF_HOPE), ModItems.MASK, ModItems.NETHER_SHARD,
					ModItems.HARDENED_LEATHER, potion, Items.FERMENTED_SPIDER_EYE, new ItemStack(Items.DYE, 1, 8));

			MONKEY_MASK = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.MONKEY_MASK), ModItems.MASK, ModItems.NETHER_SHARD,
					ModItems.HARDENED_LEATHER, potion, Items.MUSHROOM_STEW, new ItemStack(Items.DYE, 1, 7));

			RAIDEN_MASK = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.RAIDEN_MASK), ModItems.MASK, ModItems.NETHER_SHARD,
					ModItems.HARDENED_LEATHER, potion, Items.SLIME_BALL, new ItemStack(Items.DYE, 1, 9));

			UBA_MASK = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.UBA_MASK), ModItems.MASK, ModItems.NETHER_SHARD,
					ModItems.HARDENED_LEATHER, potion, Items.BEETROOT_SOUP, new ItemStack(Items.DYE, 1, 9));
		}

		if (ConfigHandler.grimoireOfAlice.crafting.thirdEye) {
			THIRD_EYE = GrimoireOfAliceAPI.registerRecipeNether(new ItemStack(ModItems.THIRD_EYE)
					, Blocks.OBSIDIAN, Items.ENDER_EYE, Items.ENDER_EYE, Items.ENDER_PEARL, Items.ENDER_PEARL);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.amenonuhoko) {
			AMENONUHOKO = GrimoireOfAliceAPI.registerRecipeRain(new ItemStack(ModItems.AMENONUHOKO)
					, Items.END_CRYSTAL, Items.END_CRYSTAL, Items.END_CRYSTAL, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModBlocks.COMPACT_STONE, Items.NETHER_STAR, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.bloodThirstyOrb) {
			BLOOD_THIRSTY_ORB = GrimoireOfAliceAPI.registerRecipeNether(new ItemStack(ModItems.BLOOD_ORB)
					, Items.ENDER_EYE, Items.NETHER_WART, Items.NETHER_WART, Items.NETHER_WART, Items.NETHER_WART);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.budahBoul) {
			BUDAH_BOUL = GrimoireOfAliceAPI.registerRecipeMoonPhase(5, new ItemStack(ModItems.BUDAH_BOUL)
					, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.STONE, Blocks.CLAY, Blocks.STONE, Items.NETHER_STAR);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.cursedDecoyDoll) {
			CURSED_DECOY_DOLL = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.CURSED_DECOY_DOLL)
					, Items.BEEF, Items.BEEF, Items.BEEF, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER, Blocks.PUMPKIN);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.deathScythe) {
			DEATH_SCYTHE = GrimoireOfAliceAPI.registerRecipeNether(new ItemStack(ModItems.DEATH_SCYTHE)
					, Blocks.SOUL_SAND, ModItems.HIHIIROKANE, "stickWood", ModItems.HIHIIROKANE, new ItemStack(Items.SKULL, 1, 1), Blocks.SOUL_SAND, ModItems.HIHIIROKANE, "stickWood");
		}

		if (ConfigHandler.grimoireOfAlice.crafting.dragonJewel) {
			DRAGON_JEWEL = GrimoireOfAliceAPI.registerRecipeMoonPhase(4, new ItemStack(ModItems.DRAGON_JEWEL)
					, Blocks.DRAGON_EGG, Blocks.GOLD_BLOCK, "nuggetGold", "nuggetGold", "nuggetGold", "nuggetGold", "nuggetGold", "nuggetGold", ModItems.NETHER_SHARD, ModItems.NETHER_SHARD, ModItems.NETHER_SHARD, ModItems.NETHER_SHARD, ModItems.NETHER_SHARD, ModItems.NETHER_SHARD, ModItems.NETHER_SHARD, ModItems.NETHER_SHARD);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.fakeMiracleMallet) {
			FAKE_MIRACLE_MALLET = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.FAKE_MIRACLE_MALLET)
					, ModItems.PASTE, ModItems.PASTE, ModItems.PASTE, ModItems.PASTE, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, ModItems.IMPURE_ROCK);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.kakanoShimenawa) {
			KANAKO_SHIMENAWA = GrimoireOfAliceAPI.registerRecipeRain(new ItemStack(ModItems.KANAKO_SHIMENAWA)
					, ModBlocks.ROPE_BLOCK, ModBlocks.ROPE_BLOCK, ModBlocks.ROPE_BLOCK, ModBlocks.ROPE_BLOCK, ModBlocks.ROPE_BLOCK, ModBlocks.ROPE_BLOCK, ModBlocks.ROPE_BLOCK, ModBlocks.ROPE_BLOCK, ModBlocks.ROPE_BLOCK, ModBlocks.ROPE_BLOCK, ModBlocks.ROPE_BLOCK, Items.NETHER_STAR, ModBlocks.PAPER_BLOCK, ModBlocks.PAPER_BLOCK, ModBlocks.PAPER_BLOCK, ModBlocks.PAPER_BLOCK);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.fireRobe) {
			FIRE_ROBE = GrimoireOfAliceAPI.registerRecipeMoonPhase(3, new ItemStack(ModItems.FIRE_ROBE)
					, Items.CHAINMAIL_CHESTPLATE, Items.LEATHER_CHESTPLATE, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_POWDER, Items.BLAZE_POWDER, Items.RABBIT_HIDE, Items.RABBIT_HIDE, ModItems.HARDENED_LEATHER, Items.RABBIT_HIDE, ModItems.HARDENED_LEATHER, Items.RABBIT_HIDE, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.gapFoldingUmbrella) {
			GAP_FOLDING_UMBRELLA = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.FOLDING_UMBRELLA)
					, ModItems.PASTE, new ItemStack(Blocks.WOOL, 1, 10), ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER, "stickWood");
		}

		if (ConfigHandler.grimoireOfAlice.crafting.ghostDipper) {
			GHOST_DIPPER = GrimoireOfAliceAPI.registerRecipeRain(new ItemStack(ModItems.GHOST_DIPPER)
					, "stickWood", "stickWood", Items.BOWL, Items.BOWL, Items.WATER_BUCKET, Items.WATER_BUCKET, Items.BUCKET, Items.BUCKET);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.grimoireBook) {
			ItemStack item = new ItemStack(ModItems.SHROOM_POWDER, 1, OreDictionary.WILDCARD_VALUE);
			GRIMOIRE_BOOK = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.GRIMOIRE_BOOK)
					, Items.WRITABLE_BOOK, ModItems.SOLDIFIED_PAPER, ModItems.SOLDIFIED_PAPER, ModItems.SOLDIFIED_PAPER, item, item, item, item, item, item);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.hakureiGohei) {
			HAKUREI_GOHEI = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.HAKUREI_GOHEI)
					, "stickWood", "stickWood", ModItems.SHIMENAWA_ROPE, ModItems.SHIMENAWA_ROPE, ModItems.SHIMENAWA_ROPE, ModItems.SHIMENAWA_ROPE, ModItems.SHIMENAWA_ROPE, ModItems.SHIMENAWA_ROPE);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.ibarakiBox) {
			IBARAKI_BOX_EMPTY = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.IBARAKI_BOX_EMPTY)
					, "plankWood", "plankWood", "plankWood", "plankWood", "plankWood", "plankWood", "plankWood", Items.GLASS_BOTTLE);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.ichirinUnzan) {
			ICHIRIN_AURA = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.ICHIRIN_UNZAN)
					, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.ichirinRing) {
			ICHIRIN_RING = GrimoireOfAliceAPI.registerRecipeThunder(new ItemStack(ModItems.ICHIRIN_RING)
					, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.kappaHat) {
			KAPPA_HAT = GrimoireOfAliceAPI.registerRecipeRain(new ItemStack(ModItems.KAPPA_HAT)
					, new ItemStack(Blocks.WOOL, 1, 5), new ItemStack(Blocks.WOOL, 1, 5), new ItemStack(Blocks.WOOL, 1, 5), new ItemStack(Blocks.WOOL, 1, 5), Items.SLIME_BALL, Blocks.WATERLILY, Blocks.WATERLILY, Blocks.WATERLILY);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.mapleLeafShield) {
			MAPLE_LEAF_SHIELD = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.MAPLE_LEAF_SHIELD)
					, ModItems.TAMAHAGANE_STEEL, ModItems.TAMAHAGANE_STEEL, ModItems.TAMAHAGANE_STEEL, ModItems.TAMAHAGANE_STEEL, "treeLeaves", "treeLeaves", "treeLeaves", "treeLeaves");
		}

		if (ConfigHandler.grimoireOfAlice.crafting.marisaHat) {
			MARISA_HAT = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.MARISA_HAT)
					, new ItemStack(Blocks.WOOL, 1, 7), new ItemStack(Blocks.WOOL, 1, 7), new ItemStack(Blocks.WOOL, 1, 7), new ItemStack(Blocks.WOOL, 1, 7), Items.BOOK, new ItemStack(Blocks.WOOL, 1, 0), new ItemStack(Blocks.WOOL, 1, 0), new ItemStack(Blocks.WOOL, 1, 0));
		}

		if (ConfigHandler.grimoireOfAlice.crafting.mikoCape) {
			MICO_CAPE = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.MIKO_CLOAK)
					, new ItemStack(Items.BANNER, 1, 13), "paper", ModItems.HARDENED_LEATHER, "paper", "paper", ModItems.HARDENED_LEATHER, "paper", new ItemStack(Items.DYE, 1, 13));
		}

		if (ConfigHandler.grimoireOfAlice.crafting.mikoStick) {
			MIKO_STICK = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.MIKO_STICK)
					, PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.HEALING), "stickWood");
		}

		if (ConfigHandler.grimoireOfAlice.crafting.mochiHammer) {
			MOCHI_HAMMER = GrimoireOfAliceAPI.registerRecipeMoonPhase(6, new ItemStack(ModItems.MOCHI_HAMMER)
					, "logWood", "logWood", "logWood", Items.RABBIT_HIDE, Items.RABBIT_HIDE, "stickWood", "stickWood", Items.SLIME_BALL);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.momijisScimitarSword) {
			MOMIJIS_SCIMITAR_SWORD = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.MOMIJIS_SCIMITAR_SWORD)
					, ModItems.TAMAHAGANE_STEEL, ModItems.TAMAHAGANE_STEEL, ModItems.TAMAHAGANE_STEEL, ModItems.TAMAHAGANE_STEEL, ModItems.IMPURE_ROCK, ModItems.IMPURE_ROCK, ModItems.IMPURE_ROCK, ModItems.IMPURE_ROCK);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.narzinStick) {
			NAZRIN_STICK_ONE = GrimoireOfAliceAPI.registerRecipe(ItemNazrinStick.TYPEA
					, Items.COMPASS, Items.EMERALD, new ItemStack(Items.COAL, 1, 0), new ItemStack(Items.COAL, 1, 0), new ItemStack(Items.COAL, 1, 0), new ItemStack(Items.COAL, 1, 0));
			NAZRIN_STICK_TWO = GrimoireOfAliceAPI.registerRecipe(ItemNazrinStick.TYPEB
					, Items.COMPASS, Items.EMERALD, new ItemStack(Items.COAL, 1, 1), new ItemStack(Items.COAL, 1, 1), new ItemStack(Items.COAL, 1, 1), new ItemStack(Items.COAL, 1, 1));
		}

		if (ConfigHandler.grimoireOfAlice.crafting.needle) {
			NEEDLE = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.SHINMYOUMARU_NEEDLE)
					, ModItems.TAMAHAGANE_STEEL, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, Items.FEATHER);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.nazrinPendulum) {
			NAZRIN_PENDULUM = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.NAZRIN_PENDULUM)
					, "blockGlassCyan", "blockGlassCyan", "blockGlassCyan", "blockGlassCyan", "blockGlassCyan", "blockGlassCyan", Items.END_CRYSTAL, Blocks.DAYLIGHT_DETECTOR);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.nimbleFabric) {
			ItemStack item = new ItemStack(Blocks.CARPET, 1, OreDictionary.WILDCARD_VALUE);
			NIMBLE_FABRIC = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.NIMBLE_FABRIC)
					, Items.LEATHER, ModItems.HARDENED_LEATHER, Items.LEATHER, item, item, item);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.nueTrident) {
			NUE_TRIDENT = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.NUE_TRIDENT)
					, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Items.BLAZE_ROD);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.ibukiGourd) {
			IBUKI_GOURD = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.IBUKI_GOURD)
					, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER, Items.FERMENTED_SPIDER_EYE, ModItems.SOLDIFIED_PAPER, ModItems.SOLDIFIED_PAPER, ModItems.SOLDIFIED_PAPER);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.rodOfRemorse) {
			ROD_OF_REMORSE = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.ROD_REMORSE)
					, "logWood", Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, new ItemStack(Items.DYE, 1, 0));
		}

		if (ConfigHandler.grimoireOfAlice.crafting.roukanken) {
			ROUKANKEN = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.ROUKANKEN)
					, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, "stickWood", Blocks.RED_FLOWER);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.rumiaSword) {
			RUMIA_SWORD = GrimoireOfAliceAPI.registerRecipeNether(new ItemStack(ModItems.RUMIA_SWORD)
					, ModBlocks.HIHIIROKANE_BLOCK, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.sacredToyosatomimiSword) {
			SACRED_TOYOSATOMIMI_SWORD = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.SACRED_TOYOSATOMIMI)
					, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.SOLDIFIED_PAPER, ModItems.SOLDIFIED_PAPER, ModItems.TAMAHAGANE_STEEL);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.sanaeGohei) {
			SANAE_GOHEI = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.SANAE_GOHEI)
					, "paper", "paper", "paper", "paper", "stickWood", "stickWood", ModItems.SHIMENAWA_ROPE);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.sarielWand) {
			SARIEL_WAND = GrimoireOfAliceAPI.registerRecipeThunder(new ItemStack(ModItems.SARIEL_WAND)
					, Items.NETHER_STAR, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.shimenawaRope) {
			SHIMENAWA_ROPE = GrimoireOfAliceAPI.registerRecipeRain(new ItemStack(ModItems.SHIMENAWA_ROPE)
					, ModBlocks.ROPE_BLOCK, ModBlocks.ROPE_BLOCK, ModBlocks.ROPE_BLOCK, ModBlocks.PAPER_BLOCK, ModBlocks.PAPER_BLOCK, ModBlocks.PAPER_BLOCK, ModBlocks.PAPER_BLOCK);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.sichiSeiken) {
			SHICHI_SEIKEN = GrimoireOfAliceAPI.registerRecipeMoonPhase(7, new ItemStack(ModItems.SHICHI_SEIKEN)
					, Items.IRON_INGOT, Items.GOLD_INGOT, ModItems.TAMAHAGANE_STEEL);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.kanakoOnbashira) {
			KANAKO_ONBASHIRA = GrimoireOfAliceAPI.registerRecipeRain(new ItemStack(ModItems.KANAKO_ONBASHIRA)
					, "logWood", "logWood", "logWood", "logWood", "stickWood", ModItems.SHIMENAWA_ROPE);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.skull) {
			ORIN_SKULL = GrimoireOfAliceAPI.registerRecipeNether(new ItemStack(ModItems.SKULL)
					, new ItemStack(Items.SKULL, 1, 1));
		}

		if (ConfigHandler.grimoireOfAlice.crafting.stopWatch) {
			STOP_WATCH = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.STOP_WATCH)
					, Items.CLOCK, Items.REDSTONE, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, ModItems.NETHER_SHARD, ModItems.TIME_ORB, ModItems.TIME_ORB);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.substituteJizo) {
			SUBSTITUTE_JIZO = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.SUBSTITUTE_JIZO)
					, Items.LEATHER, ModItems.HARDENED_LEATHER, Items.LEATHER, ModItems.SOLDIFIED_PAPER, ModItems.SOLDIFIED_PAPER, ModItems.SOLDIFIED_PAPER);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.suwakoHat) {
			SUWAKO_HAT = GrimoireOfAliceAPI.registerRecipeRain(new ItemStack(ModItems.SUWAKO_HAT)
					, Items.ENDER_EYE, Items.ENDER_EYE, Blocks.WATERLILY, Items.LEATHER_HELMET);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.swallowCowrieShell) {
			SWALLOW_COWRIE_SHELL = GrimoireOfAliceAPI.registerRecipeMoonPhase(5, new ItemStack(ModItems.COWRIE_SHELL)
					, Items.EGG, Items.EGG, Items.EGG, Items.EGG, Items.EGG, Items.EGG, Items.EGG, Items.EGG, Blocks.BONE_BLOCK, Blocks.BONE_BLOCK, Blocks.BONE_BLOCK, Blocks.BONE_BLOCK, Blocks.BONE_BLOCK, Blocks.BONE_BLOCK, Items.MILK_BUCKET, Items.MILK_BUCKET);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.shinmyoumaruBowl) {
			SHINMYOUMARU_HAT = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.SHINMYOUMARU_HAT)
					, Items.BRICK, Items.BRICK, Items.BRICK, Items.BRICK, Items.BRICK, Items.BRICK, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.tenguCamera) {
			AYA_CAMERA = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.AYA_CAMERA)
					, "blockGlassColorless", Blocks.LEVER, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, Blocks.REDSTONE_LAMP);
			HATATE_CAMERA = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.HATATE_CAMERA)
					, "blockGlassColorless", Blocks.STONE_BUTTON, Items.REDSTONE, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, Blocks.REDSTONE_LAMP);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.ufo) {
			UFOS = GrimoireOfAliceAPI.registerRecipeEnd(new ItemStack(ModItems.UFOS)
					, ModItems.UFO_BLUE, ModItems.UFO_GREEN, ModItems.UFO_RED);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.wallPassingChisel) {
			WALL_PASSING_CHISEL = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.WALL_PASSING_CHISEL)
					, Items.ENDER_EYE, "stickWood", "stickWood", Items.NAME_TAG);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.youkaiBook) {
			YOUKAI_BOOK = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.YOUKAI_BOOK)
					, Items.BOOK, Items.FEATHER, Items.RABBIT_FOOT);
		}

		if (ConfigHandler.grimoireOfAlice.food.ultramarineOrbElixir) {
			ItemStack stack = new ItemStack(ModItems.SHROOM_POWDER, 1, OreDictionary.WILDCARD_VALUE);
			ItemStack potion = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.STRONG_REGENERATION);
			ULTRAMARINE_ORB_ELIXIR = GrimoireOfAliceAPI.registerRecipeEnd(new ItemStack(ModItems.ORB_ELIXIR)
					, potion, stack, stack, stack, stack, stack, stack, stack, stack, stack, stack, stack, stack, stack, stack, stack);
		}

		if (ConfigHandler.grimoireOfAlice.food.houraiElixir) {
			HOURAI_ELIXIR = GrimoireOfAliceAPI.registerRecipeEnd(new ItemStack(ModItems.HOURAI_ELIXIR)
					, Blocks.DRAGON_EGG, Items.FLOWER_POT, ModItems.ORB_ELIXIR, ModItems.MORTAR_AND_PESTLE);
		}

		if (ConfigHandler.grimoireOfAlice.food.kappaNostrum) {
			KAPPA_NOSTRUM = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.KAPPAS_NOSTRUM)
					, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.GHAST_TEAR, new ItemStack(Items.FLOWER_POT));
		}

		if (ConfigHandler.grimoireOfAlice.food.yuugiSake) {
			YUUGI_SAKE = GrimoireOfAliceAPI.registerRecipeNether(new ItemStack(ModItems.YUUGI_SAKE)
					, ModItems.IBUKI_GOURD, Items.FLOWER_POT, Items.WHEAT, Items.WHEAT, Items.WHEAT, Items.WHEAT, Items.GHAST_TEAR, Items.FERMENTED_SPIDER_EYE);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.toyosatomimiHat) {
			TOYOSATOMIMI_HAT = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.TOYOSATOMIMI_HAT)
					, Items.LEATHER_HELMET, Items.RABBIT_FOOT, new ItemStack(Items.DYE, 1, 13), new ItemStack(Items.DYE, 1, 13), new ItemStack(Items.DYE, 1, 13), new ItemStack(Items.DYE, 1, 13), new ItemStack(Items.DYE, 1, 13), new ItemStack(Items.DYE, 1, 13));
		}

		if (ConfigHandler.grimoireOfAlice.crafting.utsuhoWings) {
			UTSUHO_WINGS = GrimoireOfAliceAPI.registerRecipeNether(new ItemStack(ModItems.UTSUHO_WINGS)
					, ModItems.BLACK_FEATHER, ModItems.BLACK_FEATHER, ModItems.BLACK_FEATHER, ModItems.BLACK_FEATHER, ModItems.BLACK_FEATHER, ModItems.BLACK_FEATHER, ModItems.BLACK_FEATHER, Items.ELYTRA, ModBlocks.HIHIIROKANE_BLOCK, Blocks.COAL_BLOCK, ModItems.BLACK_FEATHER, ModItems.BLACK_FEATHER, ModItems.BLACK_FEATHER, ModItems.BLACK_FEATHER, ModItems.BLACK_FEATHER, ModItems.BLACK_FEATHER);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.netherShard) {
			NETHER_SHARD = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.NETHER_SHARD)
					, Items.DIAMOND, Items.QUARTZ, Items.QUARTZ, Items.QUARTZ, Items.QUARTZ, Items.QUARTZ, Items.QUARTZ, Items.QUARTZ);
		}

		//DanmakuCore
		if (GrimoireOfAlice.danmakuCoreInstalled) {
			if (ConfigHandler.grimoireOfAlice.crafting.danmakuCore.ellyScythe) {
				ELLY_SCYTHE = GrimoireOfAliceAPI.registerRecipeNether(new ItemStack(ModItems.ELLY_SCYTHE)
						, new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 1), ModItems.IMPURE_ROCK, ModItems.IMPURE_ROCK);
			}

			if (ConfigHandler.grimoireOfAlice.crafting.danmakuCore.laevatein) {
				LEVATEIN = GrimoireOfAliceAPI.registerRecipeNether(new ItemStack(ModItems.LAEVATEIN)
						, Blocks.MAGMA, Blocks.MAGMA, Blocks.MAGMA, Blocks.MAGMA, Blocks.COAL_BLOCK, Blocks.COAL_BLOCK, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE);
			}

			if (ConfigHandler.grimoireOfAlice.crafting.danmakuCore.miracleMallet) {
				MIRACLE_MALLET = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.MIRACLE_MALLET)
						, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Items.GOLD_NUGGET, Blocks.GOLD_BLOCK, Items.GOLD_NUGGET, Items.GOLD_NUGGET, Blocks.SLIME_BLOCK, Items.GOLD_NUGGET, Items.MELON_SEEDS, Items.MELON_SEEDS, Items.MELON_SEEDS, Items.LEAD, Items.NETHER_STAR);
			}

			if (ConfigHandler.grimoireOfAlice.crafting.danmakuCore.trumpet) {
				TRUMPET = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.MERLIN_TRUMPET)
						, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, ModItems.SOLDIFIED_PAPER, ModItems.SOLDIFIED_PAPER);
			}

			if (ConfigHandler.grimoireOfAlice.crafting.danmakuCore.piano) {
				PIANO = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.LYRICA_PIANO)
						, Items.IRON_INGOT, Items.IRON_INGOT, Items.CLAY_BALL, Items.CLAY_BALL, Items.CLAY_BALL, Items.CLAY_BALL);
			}

			if (ConfigHandler.grimoireOfAlice.crafting.danmakuCore.violin) {
				VIOLIN = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.LUNASA_VIOLIN)
						, "plankWood", "plankWood", "plankWood", Items.STRING, Items.STRING, "stickWood");
			}

			if (ConfigHandler.grimoireOfAlice.crafting.danmakuCore.spellCardPuch) {
				SPELL_CARD_POUCH = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.POUCH)
						, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER, Blocks.CHEST);
			}

			if (ConfigHandler.grimoireOfAlice.crafting.danmakuCore.swordOfHisou) {
				SWORD_OF_HISOU = GrimoireOfAliceAPI.registerRecipeRain(new ItemStack(ModItems.HISOU)
						, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, Items.GOLDEN_SWORD, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.NETHER_SHARD, ModItems.NETHER_SHARD, ModItems.NETHER_SHARD, ModItems.NETHER_SHARD, ModItems.NETHER_SHARD, ModItems.NETHER_SHARD, ModItems.NETHER_SHARD, ModItems.NETHER_SHARD);
			}

			if (ConfigHandler.grimoireOfAlice.crafting.danmakuCore.tenguFan) {
				TENGU_FAN = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.TENGU_FAN)
						, "stickWood", "stickWood", "stickWood", "stickWood", "stickWood", "stickWood", "treeLeaves", "treeLeaves");
			}

			if (ConfigHandler.grimoireOfAlice.crafting.danmakuCore.shouLamp) {
				SHOU_LAMP = GrimoireOfAliceAPI.registerRecipe(new ItemStack(ModItems.SHOU_LAMP)
						, Blocks.SEA_LANTERN, Blocks.SEA_LANTERN, Blocks.SEA_LANTERN, Blocks.SEA_LANTERN, Blocks.SEA_LANTERN, Blocks.SEA_LANTERN, Blocks.GLOWSTONE, Blocks.GLOWSTONE, ModBlocks.ROPE_BLOCK, ModBlocks.ROPE_BLOCK, ModBlocks.ROPE_BLOCK, ModBlocks.ROPE_BLOCK, ModBlocks.ROPE_BLOCK, ModBlocks.ROPE_BLOCK, ModBlocks.ROPE_BLOCK, ModBlocks.ROPE_BLOCK);
			}

			if (ConfigHandler.grimoireOfAlice.crafting.danmakuCore.jeweledHourai) {
				JEWELED_HOURAI = GrimoireOfAliceAPI.registerRecipeMoonPhase(4, new ItemStack(ModItems.JEWELED_HOURAI)
						, Blocks.GOLD_BLOCK, "treeSapling", Blocks.GOLD_BLOCK, Items.DIAMOND, Items.DIAMOND, Items.DIAMOND, Items.NETHER_STAR, Items.DIAMOND, Items.EMERALD, Items.EMERALD, Items.EMERALD, Items.PRISMARINE_CRYSTALS, Items.PRISMARINE_CRYSTALS, Items.PRISMARINE_CRYSTALS, Items.EMERALD, Items.PRISMARINE_CRYSTALS);
			}
		}
		//Blocks
		if (ConfigHandler.grimoireOfAlice.crafting.holyStone) {
			HOLYSTONE = GrimoireOfAliceAPI.registerRecipeRain(new ItemStack(ModBlocks.HOLY_STONE)
					, ModBlocks.ROPE_BLOCK, ModBlocks.PAPER_BLOCK, ModBlocks.PAPER_BLOCK, ModBlocks.PAPER_BLOCK, ModBlocks.PAPER_BLOCK, "stone");
		}

		if (ConfigHandler.grimoireOfAlice.crafting.holyKeyStone) {
			HOLYKEYSTONE = GrimoireOfAliceAPI.registerRecipeThunder(new ItemStack(ModBlocks.HOLY_KEY_STONE)
					, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModItems.SHIMENAWA_ROPE, Items.STRING, ModItems.SHIMENAWA_ROPE, Items.STRING, ModItems.SHIMENAWA_ROPE, Items.STRING, ModItems.SHIMENAWA_ROPE, Items.STRING);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.onbashira) {
			ONBASHIRA = GrimoireOfAliceAPI.registerRecipeRain(new ItemStack(ModBlocks.ONBASHIRA)
					, ModBlocks.PILLAR_ALTAR, ModBlocks.HOLY_STONE, ModBlocks.HOLY_STONE, ModBlocks.PILLAR_ALTAR, ModBlocks.HOLY_STONE, ModBlocks.PILLAR_ALTAR, ModBlocks.PILLAR_ALTAR, ModBlocks.HOLY_STONE);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.hihiirokaneBlock) {
			HIHIIROKANE_BLOCK = GrimoireOfAliceAPI.registerRecipeThunder(new ItemStack(ModBlocks.HIHIIROKANE_BLOCK)
					, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.endCrystal) {
			END_CRYSTAL = GrimoireOfAliceAPI.registerRecipeEnd(new ItemStack(Items.END_CRYSTAL)
					, "blockGlassColorless", "blockGlassColorless", "blockGlassColorless", "blockGlassColorless", "blockGlassColorless", "blockGlassColorless", "blockGlassColorless", Items.ENDER_EYE, Items.GHAST_TEAR);
		}
	}
}
