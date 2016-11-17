package arekkuusu.grimoireofalice.item.crafting;

import arekkuusu.grimoireofalice.GrimoireOfAlice;
import arekkuusu.grimoireofalice.block.ModBlocks;
import arekkuusu.grimoireofalice.handler.ConfigHandler;
import arekkuusu.grimoireofalice.item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;

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
	public static IRecipeItems CREST_OF_YGGDRASILL;
	public static IRecipeItems CURSED_DECOY_DOLL;
	public static IRecipeItems DEATH_SCYTHE;
	public static IRecipeItems DRAGON_JEWEL;
	public static IRecipeItems FAKE_MIRACLE_MALLET;
	public static IRecipeItems FIRE_ROBE;
	public static IRecipeItems GAP_FOLDING_UMBRELLA;
	public static IRecipeItems GHASTLY_SEND_OFF_LANTERN;
	public static IRecipeItems GHOST_DIPPER;
	public static IRecipeItems GRIMOIRE_BOOK;
	public static IRecipeItems HAKUREI_GOHEI;
	public static IRecipeItems HIHIIROKANE;
	public static IRecipeItems IBARAKI_BOX_EMPTY;
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
	public static IRecipeItems ONBASHIRA_SWORD;
	public static IRecipeItems ROD_OF_REMORSE;
	public static IRecipeItems RUMIA_SWORD;
	public static IRecipeItems SACRED_TOYOSATOMIMI_SWORD;
	public static IRecipeItems SANAE_GOHEI;
	public static IRecipeItems SARIEL_WAND;
	public static IRecipeItems SHICHI_SEIKEN;
	public static IRecipeItems SHIMENAWA_ROPE;
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
	public static IRecipeItems ONBASHIRA;
	public static IRecipeItems END_CRYSTAL;

	public static void init() {
		//General
		MASK = RecipeAltar.registerRecipe(new ItemStack(ModItems.MASK)
				, ModItems.VOLATILE_STRING, ModItems.SOLDIFIED_PAPER, ModItems.SOLDIFIED_PAPER, ModItems.SOLDIFIED_PAPER, Items.CLAY_BALL, Items.CLAY_BALL);

		HIHIIROKANE = RecipeAltar.registerRecipe(new ItemStack(ModItems.HIHIIROKANE)
				, Blocks.COAL_BLOCK, ModItems.IMPURE_ROCK, Blocks.COAL_BLOCK, ModItems.IMPURE_ROCK, Blocks.COAL_BLOCK, ModItems.IMPURE_ROCK, Blocks.COAL_BLOCK, ModItems.IMPURE_ROCK);

		if (ConfigHandler.grimoireOfAlice.crafting.masks.altarMaskRecipes) {
			ItemStack potion = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.AWKWARD);

			KOKOROS_MASKS = RecipeAltar.registerRecipe(new ItemStack(ModItems.KOKOROS_MASKS), potion, ModItems.MASK, ModItems.FOX_MASK,
					ModItems.FUKU_NO_KAMI_MASK, ModItems.HANNYA_MASK, ModItems.HYOTTOKO_MASK, ModItems.KOOMOTE_MASK, ModItems.MASK_OF_HOPE,
					ModItems.MONKEY_MASK, ModItems.RAIDEN_MASK, ModItems.UBA_MASK);

			FOX_MASK = RecipeAltar.registerRecipe(new ItemStack(ModItems.FOX_MASK), ModItems.MASK, Items.NETHER_STAR, ModItems.VOLATILE_STRING,
					potion, Items.SUGAR, new ItemStack(Items.DYE, 1, 0));
			FUKU_NO_KAMI_MASK = RecipeAltar.registerRecipe(new ItemStack(ModItems.FUKU_NO_KAMI_MASK), ModItems.MASK, Items.NETHER_STAR, potion,
					Items.BLAZE_POWDER, new ItemStack(Items.DYE, 1, 14));
			HANNYA_MASK = RecipeAltar.registerRecipe(new ItemStack(ModItems.HANNYA_MASK), ModItems.MASK, Items.NETHER_STAR,
					ModItems.VOLATILE_STRING, potion, Items.POISONOUS_POTATO, new ItemStack(Items.DYE, 1, 1));
			HYOTTOKO_MASK = RecipeAltar.registerRecipe(new ItemStack(ModItems.HYOTTOKO_MASK), ModItems.MASK, Items.NETHER_STAR,
					ModItems.VOLATILE_STRING, potion, Items.GHAST_TEAR, new ItemStack(Items.DYE, 1, 9));
			KOOMOTE_MASK = RecipeAltar.registerRecipe(new ItemStack(ModItems.KOOMOTE_MASK), ModItems.MASK, Items.NETHER_STAR,
					ModItems.VOLATILE_STRING, potion, Items.ROTTEN_FLESH, new ItemStack(Items.DYE, 1, 0));
			MASK_OF_HOPE = RecipeAltar.registerRecipe(new ItemStack(ModItems.MASK_OF_HOPE), ModItems.MASK, Items.NETHER_STAR,
					ModItems.VOLATILE_STRING, potion, Items.FERMENTED_SPIDER_EYE, new ItemStack(Items.DYE, 1, 8));
			MONKEY_MASK = RecipeAltar.registerRecipe(new ItemStack(ModItems.MONKEY_MASK), ModItems.MASK, Items.NETHER_STAR, ModItems.VOLATILE_STRING,
					potion, Items.MUSHROOM_STEW, new ItemStack(Items.DYE, 1, 7));
			RAIDEN_MASK = RecipeAltar.registerRecipe(new ItemStack(ModItems.RAIDEN_MASK), ModItems.MASK, Items.NETHER_STAR, ModItems.VOLATILE_STRING,
					potion, Items.SLIME_BALL, new ItemStack(Items.DYE, 1, 9));
			UBA_MASK = RecipeAltar.registerRecipe(new ItemStack(ModItems.UBA_MASK), ModItems.MASK, Items.NETHER_STAR, ModItems.VOLATILE_STRING,
					potion, Items.BEETROOT_SOUP, new ItemStack(Items.DYE, 1, 9));
		}

		if (ConfigHandler.grimoireOfAlice.crafting.thirdEye) {
			THIRD_EYE = RecipeAltar.registerRecipeNether(new ItemStack(ModItems.THIRD_EYE)
					, Blocks.OBSIDIAN, Items.ENDER_EYE, Items.ENDER_EYE, Items.ENDER_PEARL, Items.ENDER_PEARL);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.amenonuhoko) {
			AMENONUHOKO = RecipeAltar.registerRecipeEnd(new ItemStack(ModItems.AMENONUHOKO)
					, Items.END_CRYSTAL, Items.END_CRYSTAL, Items.END_CRYSTAL, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, Items.NETHER_STAR);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.bloodThirstyOrb) {
			BLOOD_THIRSTY_ORB = RecipeAltar.registerRecipeNether(new ItemStack(ModItems.BLOOD_ORB)
					, Items.ENDER_EYE, Blocks.NETHER_WART, Blocks.NETHER_WART, Blocks.NETHER_WART, Blocks.NETHER_WART);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.budahBoul) {
			BUDAH_BOUL = RecipeAltar.registerRecipeMoonPhase(1, new ItemStack(ModItems.BUDAH_BOUL)
					, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.STONE, Blocks.STONE, Blocks.CLAY, Items.NETHER_STAR);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.crestOfYggdrasill) {
			CREST_OF_YGGDRASILL = RecipeAltar.registerRecipeEnd(new ItemStack(ModItems.CREST_OF_YGGDRASILL)
					, Blocks.LEAVES, Blocks.LEAVES, Blocks.LEAVES, Blocks.LEAVES, Blocks.LEAVES, Blocks.LEAVES, Blocks.LOG, Blocks.LOG, Blocks.LOG, Blocks.LOG, Items.NETHER_STAR);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.cursedDecoyDoll) {
			CURSED_DECOY_DOLL = RecipeAltar.registerRecipe(new ItemStack(ModItems.CURSED_DECOY_DOLL)
					, Items.BEEF, Items.BEEF, Items.BEEF, Items.PAPER, Items.PAPER, Blocks.PUMPKIN);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.deathScythe) {
			DEATH_SCYTHE = RecipeAltar.registerRecipeNether(new ItemStack(ModItems.DEATH_SCYTHE)
					, new ItemStack(Items.SKULL, 1, 1), ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, Blocks.SOUL_SAND, Blocks.SOUL_SAND, Items.STICK, Items.STICK);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.dragonJewel) {
			DRAGON_JEWEL = RecipeAltar.registerRecipeMoonPhase(4, new ItemStack(ModItems.DRAGON_JEWEL)
					, ModItems.AMENONUHOKO, Blocks.GOLD_BLOCK, Items.GOLD_NUGGET, Items.GOLD_NUGGET, Items.GOLD_NUGGET, Items.GOLD_NUGGET, Items.GOLD_NUGGET, Items.GOLD_NUGGET, Items.GOLD_NUGGET, Items.GOLD_NUGGET, Items.GOLD_NUGGET, Items.GOLD_NUGGET, Items.GOLD_NUGGET, Items.GOLD_NUGGET, Items.GOLD_NUGGET, Items.GOLD_NUGGET);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.fakeMiracleMallet) {
			FAKE_MIRACLE_MALLET = RecipeAltar.registerRecipe(new ItemStack(ModItems.FAKE_MIRACLE_MALLET)
					, ModItems.SOLDIFIED_PAPER, ModItems.SOLDIFIED_PAPER, ModItems.SOLDIFIED_PAPER, ModItems.SOLDIFIED_PAPER, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, ModItems.IMPURE_ROCK);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.fireRobe) {
			FIRE_ROBE = RecipeAltar.registerRecipeMoonPhase(3, new ItemStack(ModItems.FIRE_ROBE)
					, Items.CHAINMAIL_CHESTPLATE, Items.LEATHER_CHESTPLATE, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_POWDER, Items.BLAZE_POWDER, Items.RABBIT_HIDE, Items.RABBIT_HIDE, Items.RABBIT_HIDE, Items.RABBIT_HIDE, Items.RABBIT_HIDE, Items.RABBIT_HIDE, Items.RABBIT_HIDE, Items.RABBIT_HIDE);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.gapFoldingUmbrella) {
			GAP_FOLDING_UMBRELLA = RecipeAltar.registerRecipe(new ItemStack(ModItems.FOLDING_UMBRELLA)
					, ModItems.SOLDIFIED_PAPER, ModItems.SOLDIFIED_PAPER, ModItems.SOLDIFIED_PAPER, ModItems.SOLDIFIED_PAPER, ModItems.VOLATILE_STRING, ModItems.VOLATILE_STRING, ModItems.VOLATILE_STRING, Items.STICK);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.ghostDipper) {
			GHOST_DIPPER = RecipeAltar.registerRecipeRain(new ItemStack(ModItems.GHOST_DIPPER)
					, Items.STICK, Items.STICK, Items.BOWL, Items.BOWL, Items.WATER_BUCKET, Items.WATER_BUCKET, Items.BUCKET, Items.BUCKET);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.grimoireBook) {
			GRIMOIRE_BOOK = RecipeAltar.registerRecipe(new ItemStack(ModItems.GRIMOIRE_BOOK)
					, Items.WRITABLE_BOOK, ModItems.SOLDIFIED_PAPER, ModItems.SOLDIFIED_PAPER, ModItems.SOLDIFIED_PAPER, ModItems.SHROOM_POWDER, ModItems.SHROOM_POWDER, ModItems.SHROOM_POWDER, ModItems.SHROOM_POWDER, ModItems.SHROOM_POWDER, ModItems.SHROOM_POWDER);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.hakureiGohei) {
			HAKUREI_GOHEI = RecipeAltar.registerRecipe(new ItemStack(ModItems.HAKUREI_GOHEI)
					, Items.STICK, Items.STICK, ModItems.SHIMENAWA_ROPE, ModItems.SHIMENAWA_ROPE, ModItems.SHIMENAWA_ROPE, ModItems.SHIMENAWA_ROPE, ModItems.SHIMENAWA_ROPE, ModItems.SHIMENAWA_ROPE);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.ibarakiBox) {
			IBARAKI_BOX_EMPTY = RecipeAltar.registerRecipe(new ItemStack(ModItems.IBARAKI_BOX_EMPTY)
					, Blocks.PLANKS, Blocks.PLANKS, Blocks.PLANKS, Blocks.PLANKS, Blocks.PLANKS, Blocks.PLANKS, Blocks.PLANKS, Items.GLASS_BOTTLE);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.ichirinRing) {
			ICHIRIN_RING = RecipeAltar.registerRecipeThunder(new ItemStack(ModItems.ICHIRIN_RING)
					, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.EXPERIENCE_BOTTLE);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.kappaHat) {
			KAPPA_HAT = RecipeAltar.registerRecipeRain(new ItemStack(ModItems.KAPPA_HAT)
					, new ItemStack(Blocks.WOOL, 1, EnumDyeColor.LIME.getMetadata()), new ItemStack(Blocks.WOOL, 1, EnumDyeColor.LIME.getMetadata()), new ItemStack(Blocks.WOOL, 1, EnumDyeColor.LIME.getMetadata()), new ItemStack(Blocks.WOOL, 1, EnumDyeColor.LIME.getMetadata()), Items.SLIME_BALL);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.mapleLeafShield) {
			MAPLE_LEAF_SHIELD = RecipeAltar.registerRecipe(new ItemStack(ModItems.MAPLE_LEAF_SHIELD)
					, ModItems.TAMAHAGANE_STEEL, ModItems.TAMAHAGANE_STEEL, ModItems.TAMAHAGANE_STEEL, ModItems.TAMAHAGANE_STEEL, Blocks.LEAVES, Blocks.LEAVES, Blocks.LEAVES, Blocks.LEAVES);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.marisaHat) {
			MARISA_HAT = RecipeAltar.registerRecipe(new ItemStack(ModItems.MARISA_HAT)
					, new ItemStack(Blocks.WOOL, 1, EnumDyeColor.GRAY.getMetadata()), new ItemStack(Blocks.WOOL, 1, EnumDyeColor.GRAY.getMetadata()), new ItemStack(Blocks.WOOL, 1, EnumDyeColor.GRAY.getMetadata()), new ItemStack(Blocks.WOOL, 1, EnumDyeColor.WHITE.getMetadata()), Items.BOOK);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.mikoCape) {
			MICO_CAPE = RecipeAltar.registerRecipe(new ItemStack(ModItems.MIKO_CAPE)
					, Items.BANNER, Items.PAPER, Items.PAPER, Items.PAPER, Items.PAPER, Items.PAPER, Items.PAPER, new ItemStack(Items.DYE, 1, EnumDyeColor.MAGENTA.getMetadata()));
		}

		if (ConfigHandler.grimoireOfAlice.crafting.mikoStick) {
			MIKO_STICK = RecipeAltar.registerRecipe(new ItemStack(ModItems.MIKO_STICK)
					, PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.HEALING), Items.STICK);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.mochiHammer) {
			MOCHI_HAMMER = RecipeAltar.registerRecipeMoonPhase(6, new ItemStack(ModItems.MOCHI_HAMMER)
					, Blocks.LOG, Blocks.LOG, Blocks.LOG, Blocks.LOG, Blocks.LOG, Items.STICK, Items.STICK, Items.SLIME_BALL);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.momijisScimitarSword) {
			MOMIJIS_SCIMITAR_SWORD = RecipeAltar.registerRecipe(new ItemStack(ModItems.MOMIJIS_SCIMITAR_SWORD)
					, ModItems.TAMAHAGANE_STEEL, ModItems.TAMAHAGANE_STEEL, ModItems.IMPURE_ROCK, ModItems.IMPURE_ROCK, Blocks.LEAVES, Items.STICK);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.narzinStick) {
			NAZRIN_STICK_ONE = RecipeAltar.registerRecipe(new ItemStack(ModItems.NAZRIN_STICK_ITEM, 1, 0)
					, Items.COMPASS, Blocks.EMERALD_ORE, new ItemStack(Items.COAL, 1, 0), new ItemStack(Items.COAL, 1, 0), new ItemStack(Items.COAL, 1, 0), new ItemStack(Items.COAL, 1, 0));
			NAZRIN_STICK_TWO = RecipeAltar.registerRecipe(new ItemStack(ModItems.NAZRIN_STICK_ITEM, 1, 1)
					, Items.COMPASS, Blocks.EMERALD_ORE, new ItemStack(Items.COAL, 1, 1), new ItemStack(Items.COAL, 1, 1), new ItemStack(Items.COAL, 1, 1), new ItemStack(Items.COAL, 1, 1));
		}

		if (ConfigHandler.grimoireOfAlice.crafting.nazrinPendulum) {
			NAZRIN_PENDULUM = RecipeAltar.registerRecipe(new ItemStack(ModItems.NAZRIN_PENDULUM)
					, new ItemStack(Blocks.GLASS, 1, EnumDyeColor.CYAN.getMetadata()), new ItemStack(Blocks.GLASS, 1, EnumDyeColor.CYAN.getMetadata()), new ItemStack(Blocks.GLASS, 1, EnumDyeColor.CYAN.getMetadata()), new ItemStack(Blocks.GLASS, 1, EnumDyeColor.CYAN.getMetadata()), new ItemStack(Blocks.GLASS, 1, EnumDyeColor.CYAN.getMetadata()), new ItemStack(Blocks.GLASS, 1, EnumDyeColor.CYAN.getMetadata()), Items.END_CRYSTAL, Blocks.DAYLIGHT_DETECTOR);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.nimbleFabric) {
			NIMBLE_FABRIC = RecipeAltar.registerRecipe(new ItemStack(ModItems.NIMBLE_FABRIC)
					, Items.LEATHER, Items.LEATHER, Items.LEATHER, Blocks.CARPET, Blocks.CARPET, Blocks.CARPET);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.nueTrident) {
			NUE_TRIDENT = RecipeAltar.registerRecipe(new ItemStack(ModItems.NUE_TRIDENT)
					, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Items.BLAZE_ROD);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.rodOfRemorse) {
			ROD_OF_REMORSE = RecipeAltar.registerRecipe(new ItemStack(ModItems.ROD_REMORSE)
					, Blocks.LOG, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, new ItemStack(Items.DYE, 1, 0));
		}

		if (ConfigHandler.grimoireOfAlice.crafting.roukanken) {
			ROUKANKEN = RecipeAltar.registerRecipeEnd(new ItemStack(ModItems.ROUKANKEN)
					, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, Items.STICK, Blocks.RED_FLOWER);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.rumiaSword) {
			RUMIA_SWORD = RecipeAltar.registerRecipeNether(new ItemStack(ModItems.RUMIA_SWORD)
					, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, Blocks.NETHER_WART_BLOCK, Blocks.NETHER_WART_BLOCK, Blocks.NETHER_WART_BLOCK, Blocks.NETHER_WART_BLOCK);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.sacredToyosatomimiSword) {
			SACRED_TOYOSATOMIMI_SWORD = RecipeAltar.registerRecipe(new ItemStack(ModItems.SACRED_TOYOSATOMIMI)
					, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.SOLDIFIED_PAPER, ModItems.SOLDIFIED_PAPER, ModItems.TAMAHAGANE_STEEL);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.sanaeGohei) {
			SANAE_GOHEI = RecipeAltar.registerRecipe(new ItemStack(ModItems.SANAE_GOHEI)
					, Items.PAPER, Items.PAPER, Items.PAPER, Items.PAPER, Items.STICK, Items.STICK, ModItems.SHIMENAWA_ROPE);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.sarielWand) {
			SARIEL_WAND = RecipeAltar.registerRecipeThunder(new ItemStack(ModItems.SARIEL_WAND)
					, Items.NETHER_STAR, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.shimenawaRope) {
			SHIMENAWA_ROPE = RecipeAltar.registerRecipeRain(new ItemStack(ModItems.SHIMENAWA_ROPE)
					, ModBlocks.ROPE_BLOCK, ModBlocks.PAPER_BLOCK, ModBlocks.PAPER_BLOCK, ModBlocks.PAPER_BLOCK, ModBlocks.PAPER_BLOCK);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.sichiSeiken) {
			SHICHI_SEIKEN = RecipeAltar.registerRecipeMoonPhase(8, new ItemStack(ModItems.SHICHI_SEIKEN)
					, Items.IRON_INGOT, Items.IRON_INGOT, ModItems.TAMAHAGANE_STEEL);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.skull) {
			ORIN_SKULL = RecipeAltar.registerRecipeNether(new ItemStack(ModItems.SKULL)
					, Blocks.SKULL);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.stopWatch) {
			STOP_WATCH = RecipeAltar.registerRecipe(new ItemStack(ModItems.STOP_WATCH)
					, Items.CLOCK, Items.REDSTONE, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.substituteJizo) {
			SUBSTITUTE_JIZO = RecipeAltar.registerRecipe(new ItemStack(ModItems.SUBSTITUTE_JIZO)
					, Items.LEATHER, Items.LEATHER, Items.LEATHER, ModItems.SOLDIFIED_PAPER, ModItems.SOLDIFIED_PAPER, ModItems.SOLDIFIED_PAPER, new ItemStack(Blocks.SKULL, 1, 0));
		}

		if (ConfigHandler.grimoireOfAlice.crafting.suwakoHat) {
			SUWAKO_HAT = RecipeAltar.registerRecipeRain(new ItemStack(ModItems.SUWAKO_HAT)
					, Items.ENDER_EYE, Blocks.WATERLILY, Items.LEATHER_HELMET);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.swallowCowrieShell) {
			SWALLOW_COWRIE_SHELL = RecipeAltar.registerRecipeMoonPhase(5, new ItemStack(ModItems.COWRIE_SHELL)
					, Items.EGG, Items.EGG, Items.EGG, Items.EGG, Items.EGG, Items.EGG, Items.EGG, Items.EGG, Blocks.BONE_BLOCK, Blocks.BONE_BLOCK, Blocks.BONE_BLOCK, Blocks.BONE_BLOCK, Blocks.BONE_BLOCK, Blocks.BONE_BLOCK, Items.MILK_BUCKET, Items.MILK_BUCKET);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.tenguCamera) {
			AYA_CAMERA = RecipeAltar.registerRecipe(new ItemStack(ModItems.AYA_CAMERA)
					, Blocks.GLASS, Blocks.LEVER, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, Blocks.SEA_LANTERN);
			HATATE_CAMERA = RecipeAltar.registerRecipe(new ItemStack(ModItems.HATATE_CAMERA)
					, Blocks.GLASS, Blocks.STONE_BUTTON, Items.REDSTONE, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, Blocks.SEA_LANTERN);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.ufo) {
			UFOS = RecipeAltar.registerRecipeEnd(new ItemStack(ModItems.UFOS)
					, ModItems.UFO_BLUE, ModItems.UFO_GREEN, ModItems.UFO_RED);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.wallPassingChisel) {
			WALL_PASSING_CHISEL = RecipeAltar.registerRecipe(new ItemStack(ModItems.WALL_PASSING_CHISEL)
					, Items.ENDER_EYE, Items.STICK, Items.STICK, Items.NAME_TAG);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.youkaiBook) {
			YOUKAI_BOOK = RecipeAltar.registerRecipe(new ItemStack(ModItems.YOUKAI_BOOK)
					, Items.BOOK, Items.FEATHER, Items.RABBIT_FOOT);
		}

		if (ConfigHandler.grimoireOfAlice.food.ultramarineOrbElixir) {
			ULTRAMARINE_ORB_ELIXIR = RecipeAltar.registerRecipeEnd(new ItemStack(ModItems.ORB_ELIXIR)
					, Items.GLASS_BOTTLE, Items.NETHER_STAR);
		}

		if (ConfigHandler.grimoireOfAlice.food.houraiElixir) {
			HOURAI_ELIXIR = RecipeAltar.registerRecipeEnd(new ItemStack(ModItems.HOURAI_ELIXIR)
					, Blocks.DRAGON_EGG, Items.NETHER_STAR, Blocks.FLOWER_POT, Items.CHORUS_FRUIT_POPPED, Items.PRISMARINE_CRYSTALS, Items.PRISMARINE_CRYSTALS, Items.PRISMARINE_CRYSTALS, Items.PRISMARINE_CRYSTALS, Items.PRISMARINE_CRYSTALS, Items.PRISMARINE_CRYSTALS, Items.PRISMARINE_CRYSTALS, Items.PRISMARINE_CRYSTALS, Items.PRISMARINE_CRYSTALS, Items.PRISMARINE_CRYSTALS, Items.PRISMARINE_CRYSTALS, Items.PRISMARINE_CRYSTALS);
		}

		if (ConfigHandler.grimoireOfAlice.food.kappaNostrum) {
			KAPPA_NOSTRUM = RecipeAltar.registerRecipe(new ItemStack(ModItems.KAPPAS_NOSTRUM)
					, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.BRICK, Items.BRICK);
		}

		//DanmakuCore
		if (GrimoireOfAlice.danmakuCoreInstalled) {
			if (ConfigHandler.grimoireOfAlice.crafting.danmakuCore.ellyScythe) {
				ELLY_SCYTHE = RecipeAltar.registerRecipeNether(new ItemStack(ModItems.ELLY_SCYTHE)
						, new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 1), ModItems.IMPURE_ROCK, ModItems.IMPURE_ROCK);
			}

			if (ConfigHandler.grimoireOfAlice.crafting.danmakuCore.laevatein) {
				LEVATEIN = RecipeAltar.registerRecipeNether(new ItemStack(ModItems.LAEVATEIN)
						, Blocks.MAGMA, Blocks.MAGMA, Blocks.MAGMA, Blocks.MAGMA, Blocks.COAL_BLOCK, Blocks.COAL_BLOCK, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE);
			}

			if (ConfigHandler.grimoireOfAlice.crafting.danmakuCore.miracleMallet) {
				MIRACLE_MALLET = RecipeAltar.registerRecipe(new ItemStack(ModItems.MIRACLE_MALLET)
						, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Items.GOLD_NUGGET, Items.GOLD_NUGGET, Items.GOLD_NUGGET, Items.GOLD_NUGGET, Blocks.SLIME_BLOCK, Items.MELON_SEEDS, Items.MELON_SEEDS, Items.MELON_SEEDS, Items.MELON_SEEDS, Items.LEAD);
			}

			if (ConfigHandler.grimoireOfAlice.crafting.danmakuCore.trumpet) {
				TRUMPET = RecipeAltar.registerRecipe(new ItemStack(ModItems.MERLIN_TRUMPET)
						, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, ModItems.SOLDIFIED_PAPER, ModItems.SOLDIFIED_PAPER);
			}

			if (ConfigHandler.grimoireOfAlice.crafting.danmakuCore.piano) {
				PIANO = RecipeAltar.registerRecipe(new ItemStack(ModItems.LYRICA_PIANO)
						, Items.IRON_INGOT, Items.IRON_INGOT, Items.CLAY_BALL, Items.CLAY_BALL, Items.CLAY_BALL, Items.CLAY_BALL);
			}

			if (ConfigHandler.grimoireOfAlice.crafting.danmakuCore.violin) {
				VIOLIN = RecipeAltar.registerRecipe(new ItemStack(ModItems.LUNASA_VIOLIN)
						, Blocks.PLANKS, Blocks.PLANKS, Blocks.PLANKS, Blocks.PLANKS, Items.STRING, Items.STRING, Items.STICK);
			}

			if (ConfigHandler.grimoireOfAlice.crafting.danmakuCore.spellCardPuch) {
				SPELL_CARD_POUCH = RecipeAltar.registerRecipe(new ItemStack(ModItems.POUCH)
						, Items.LEATHER, Items.LEATHER, Items.LEATHER, Items.LEATHER, Blocks.CHEST);
			}

			if (ConfigHandler.grimoireOfAlice.crafting.danmakuCore.swordOfHisou) {
				SWORD_OF_HISOU = RecipeAltar.registerRecipeRain(new ItemStack(ModItems.HISOU)
						, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, Blocks.MAGMA, Blocks.MAGMA, Blocks.MAGMA, Blocks.MAGMA, Items.GOLDEN_SWORD);
			}

			if (ConfigHandler.grimoireOfAlice.crafting.danmakuCore.tenguFan) {
				TENGU_FAN = RecipeAltar.registerRecipe(new ItemStack(ModItems.TENGU_FAN)
						, Items.STICK, Items.STICK, Items.STICK, Items.STICK, Items.STICK, Items.STICK, Blocks.LEAVES, Blocks.LEAVES);
			}

			if (ConfigHandler.grimoireOfAlice.crafting.danmakuCore.shouLamp) {
				SHOU_LAMP = RecipeAltar.registerRecipe(new ItemStack(ModItems.SHOU_LAMP)
						, Blocks.SEA_LANTERN, Blocks.SEA_LANTERN, Blocks.SEA_LANTERN, Blocks.SEA_LANTERN, Blocks.SEA_LANTERN, Blocks.SEA_LANTERN, Blocks.GLOWSTONE, Blocks.GLOWSTONE, ModBlocks.ROPE_BLOCK, ModBlocks.ROPE_BLOCK);
			}

			if (ConfigHandler.grimoireOfAlice.crafting.danmakuCore.jeweledHourai) {
				JEWELED_HOURAI = RecipeAltar.registerRecipeMoonPhase(4, new ItemStack(ModItems.JEWELED_HOURAI)
						, Blocks.SAPLING, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Items.DIAMOND, Items.DIAMOND, Items.DIAMOND, Items.DIAMOND, Items.EMERALD, Items.EMERALD, Items.EMERALD, Items.EMERALD, Items.PRISMARINE_CRYSTALS, Items.PRISMARINE_CRYSTALS, Items.PRISMARINE_CRYSTALS, Items.PRISMARINE_CRYSTALS, Items.NETHER_STAR);
			}
		}
		//Blocks
		if (ConfigHandler.grimoireOfAlice.crafting.holyStone) {
			HOLYSTONE = RecipeAltar.registerRecipeRain(new ItemStack(ModBlocks.HOLY_STONE)
					, ModBlocks.ROPE_BLOCK, ModBlocks.PAPER_BLOCK, ModBlocks.PAPER_BLOCK, ModBlocks.PAPER_BLOCK, ModBlocks.PAPER_BLOCK, Blocks.STONE);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.onbashira) {
			ONBASHIRA = RecipeAltar.registerRecipeRain(new ItemStack(ModBlocks.ONBASHIRA), ModBlocks.PILLAR_ALTAR, ModBlocks.HOLY_STONE,
					ModBlocks.PILLAR_ALTAR, ModBlocks.HOLY_STONE, ModBlocks.PILLAR_ALTAR, ModBlocks.HOLY_STONE, ModBlocks.PILLAR_ALTAR,
					ModBlocks.HOLY_STONE);
		}

		if (ConfigHandler.grimoireOfAlice.crafting.endCrystal) {
			END_CRYSTAL = RecipeAltar.registerRecipeEnd(new ItemStack(Items.END_CRYSTAL)
					, Blocks.GLASS, Blocks.GLASS, Blocks.GLASS, Blocks.GLASS, Blocks.GLASS, Blocks.GLASS, Blocks.GLASS, Items.ENDER_EYE, Items.GHAST_TEAR);
		}
	}
}
