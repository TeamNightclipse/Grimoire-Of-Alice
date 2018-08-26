/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item.crafting;

import arekkuusu.grimoireofalice.api.AliceAPI;
import arekkuusu.grimoireofalice.api.recipes.MoonPhase;
import arekkuusu.grimoireofalice.common.block.ModBlocks;
import arekkuusu.grimoireofalice.common.core.handler.ConfigHandler;
import arekkuusu.grimoireofalice.common.item.ItemNazrinStick;
import arekkuusu.grimoireofalice.common.item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.world.DimensionType;
import net.minecraftforge.oredict.OreDictionary;

public final class AltarRecipes {

	private AltarRecipes() {
	}

	public static void init() {
		//General
		if(ConfigHandler.grimoireOfAlice.crafting.altar.mask) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.MASK)
					, ModItems.PASTE, ModItems.SOLIDIFIED_PAPER, ModItems.SOLIDIFIED_PAPER);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.hihiirokane) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.HIHIIROKANE)
					, ModItems.IMPURE_ROCK, Blocks.COAL_BLOCK);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.patchyBook) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.PATCHY_BOOK)
					, Items.WRITABLE_BOOK, "stickWood", Items.FEATHER);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.syringe) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.SYRINGE)
					, Items.GLASS_BOTTLE, new ItemStack(ModItems.SHROOM_POWDER, 1, OreDictionary.WILDCARD_VALUE));
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.simpleUfo) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.UFO_GREEN)
					, Blocks.SLIME_BLOCK, ModItems.PASTE, ModItems.PASTE, "blockGlassGreen", "blockGlassGreen", "blockGlassGreen", "blockGlassGreen", "blockGlassGreen");
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.UFO_RED)
					, Blocks.SLIME_BLOCK, ModItems.PASTE, ModItems.PASTE, "blockGlassRed", "blockGlassRed", "blockGlassRed", "blockGlassRed", "blockGlassRed");
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.UFO_BLUE)
					, Blocks.SLIME_BLOCK, ModItems.PASTE, ModItems.PASTE, "blockGlassBlue", "blockGlassBlue", "blockGlassBlue", "blockGlassBlue", "blockGlassBlue");
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.masks.kokoroMask) {
			ItemStack potion = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.AWKWARD);

			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.KOKORO_MASKS), ModItems.FOX_MASK,
					ModItems.FUKU_NO_KAMI_MASK, ModItems.HANNYA_MASK, ModItems.HYOTTOKO_MASK, ModItems.KOOMOTE_MASK, ModItems.MASK_OF_HOPE,
					ModItems.MONKEY_MASK, ModItems.RAIDEN_MASK, ModItems.UBA_MASK, potion, ModItems.MASK, Items.NETHER_STAR);

			AliceAPI.registerAltarRecipeMoonPhase(MoonPhase.FIRST_QUARTER, new ItemStack(ModItems.FOX_MASK), ModItems.MASK, ModItems.NETHER_SHARD,
					ModItems.HARDENED_LEATHER, potion, Items.SUGAR, "dyeBlack");

			AliceAPI.registerAltarRecipeDimension(DimensionType.THE_END, new ItemStack(ModItems.FUKU_NO_KAMI_MASK), ModItems.MASK, ModItems.NETHER_SHARD,
					ModItems.HARDENED_LEATHER, potion, Items.BLAZE_POWDER, "dyeOrange");

			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.HANNYA_MASK), ModItems.MASK, ModItems.NETHER_SHARD,
					ModItems.HARDENED_LEATHER, potion, Items.POISONOUS_POTATO, "dyeRed");

			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.HYOTTOKO_MASK), ModItems.MASK, ModItems.NETHER_SHARD,
					ModItems.HARDENED_LEATHER, potion, Items.GHAST_TEAR, "dyePink");

			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.KOOMOTE_MASK), ModItems.MASK, ModItems.NETHER_SHARD,
					ModItems.HARDENED_LEATHER, potion, Items.ROTTEN_FLESH, "dyeBlack");

			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.MASK_OF_HOPE), ModItems.MASK, ModItems.NETHER_SHARD,
					ModItems.HARDENED_LEATHER, potion, Items.FERMENTED_SPIDER_EYE, "dyeGray");

			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.MONKEY_MASK), ModItems.MASK, ModItems.NETHER_SHARD,
					ModItems.HARDENED_LEATHER, potion, Items.MUSHROOM_STEW, "dyeLightGray");

			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.RAIDEN_MASK), ModItems.MASK, ModItems.NETHER_SHARD,
					ModItems.HARDENED_LEATHER, potion, Items.SLIME_BALL, "dyePink");

			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.UBA_MASK), ModItems.MASK, ModItems.NETHER_SHARD,
					ModItems.HARDENED_LEATHER, potion, Items.BEETROOT_SOUP, "dyePink");
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.thirdEye) {
			AliceAPI.registerAltarRecipeDimension(DimensionType.NETHER, new ItemStack(ModItems.THIRD_EYE)
					, Blocks.OBSIDIAN, Items.ENDER_EYE, Items.ENDER_EYE, Items.ENDER_PEARL, Items.ENDER_PEARL);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.amenonuhoko) {
			AliceAPI.registerAltarRecipeRain(new ItemStack(ModItems.AMENONUHOKO)
					, Items.END_CRYSTAL, Items.END_CRYSTAL, Items.END_CRYSTAL, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModBlocks.COMPACT_STONE, Items.NETHER_STAR, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.bloodThirstyOrb) {
			AliceAPI.registerAltarRecipeDimension(DimensionType.NETHER, new ItemStack(ModItems.BLOOD_THIRSTY_ORB)
					, Items.ENDER_EYE, Items.NETHER_WART, Items.NETHER_WART, Items.NETHER_WART, Items.NETHER_WART);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.budahBoul) {
			AliceAPI.registerAltarRecipeMoonPhase(MoonPhase.WAXING_CRESCENT, new ItemStack(ModItems.BUDAH_BOWL)
					, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, ModBlocks.COMPACT_STONE, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.STONE, Blocks.CLAY, Blocks.STONE, Items.NETHER_STAR);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.cursedDecoyDoll) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.CURSED_DECOY_DOLL)
					, Items.BEEF, Items.BEEF, Items.BEEF, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER, Blocks.PUMPKIN);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.deathScythe) {
			AliceAPI.registerAltarRecipeDimension(DimensionType.NETHER, new ItemStack(ModItems.KOMACHI_SCYTHE)
					, Blocks.SOUL_SAND, ModItems.HIHIIROKANE, "stickWood", ModItems.HIHIIROKANE, new ItemStack(Items.SKULL, 1, 1), Blocks.SOUL_SAND, ModItems.HIHIIROKANE, "stickWood");
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.dragonJewel) {
			AliceAPI.registerAltarRecipeMoonPhase(MoonPhase.NEW_MOON, new ItemStack(ModItems.DRAGON_JEWEL)
					, new ItemStack(Items.SKULL, 1, 5), ModBlocks.DRAGON_STONE, "nuggetGold", "nuggetGold", "nuggetGold", "nuggetGold", "nuggetGold", "nuggetGold", ModItems.NETHER_SHARD, ModItems.NETHER_SHARD, ModItems.NETHER_SHARD, ModItems.NETHER_SHARD, ModItems.NETHER_SHARD, ModItems.NETHER_SHARD, ModItems.NETHER_SHARD, ModItems.NETHER_SHARD);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.dragonStone) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModBlocks.DRAGON_STONE)
					, ModItems.DRAGON_SCALE, ModItems.DRAGON_SCALE, ModItems.DRAGON_SCALE, ModItems.DRAGON_SCALE, ModItems.DRAGON_SCALE, ModItems.DRAGON_SCALE, ModItems.DRAGON_SCALE, ModItems.DRAGON_SCALE, ModItems.DRAGON_SCALE, ModItems.DRAGON_SCALE, ModItems.DRAGON_SCALE, ModItems.DRAGON_SCALE, ModItems.DRAGON_SCALE, ModItems.DRAGON_SCALE, ModItems.DRAGON_SCALE, ModItems.DRAGON_SCALE);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.fakeMiracleMallet) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.FAKE_MIRACLE_MALLET)
					, ModItems.PASTE, ModItems.PASTE, ModItems.PASTE, ModItems.PASTE, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, ModItems.IMPURE_ROCK);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.kakanoShimenawa) {
			AliceAPI.registerAltarRecipeRain(new ItemStack(ModItems.KANAKO_SHIMENAWA)
					, ModBlocks.ROPE, ModBlocks.ROPE, ModBlocks.ROPE, ModBlocks.ROPE, ModBlocks.ROPE, ModBlocks.ROPE, ModBlocks.ROPE, ModBlocks.ROPE, ModBlocks.ROPE, ModBlocks.ROPE, ModBlocks.ROPE, Items.NETHER_STAR, ModBlocks.PAPER, ModBlocks.PAPER, ModBlocks.PAPER, ModBlocks.PAPER);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.fireRobe) {
			AliceAPI.registerAltarRecipeMoonPhase(MoonPhase.WANING_CRESCENT, new ItemStack(ModItems.FIRE_ROBE)
					, Items.CHAINMAIL_CHESTPLATE, Items.LEATHER_CHESTPLATE, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.RABBIT_HIDE, Items.RABBIT_HIDE, ModItems.HARDENED_LEATHER, Items.RABBIT_HIDE, ModItems.HARDENED_LEATHER, Items.RABBIT_HIDE, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.gapFoldingUmbrella) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.FOLDING_UMBRELLA)
					, ModItems.PASTE, new ItemStack(Blocks.WOOL, 1, 10), ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER, "stickWood");
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.ghostDipper) {
			AliceAPI.registerAltarRecipeRain(new ItemStack(ModItems.GHOST_DIPPER)
					, "stickWood", "stickWood", Items.BOWL, Items.BOWL, new ItemStack(Blocks.SPONGE, 1, 1), new ItemStack(Blocks.SPONGE, 1, 1), Blocks.SPONGE, Blocks.SPONGE);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.hakureiGohei) {
			AliceAPI.registerAltarRecipe(new RecipeHakurei());
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.ibarakiBox) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.IBARAKI_BOX_EMPTY)
					, "plankWood", "plankWood", "plankWood", "plankWood", "plankWood", "plankWood", "plankWood", Items.GLASS_BOTTLE);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.ichirinUnzan) {
/*			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.ICHIRIN_UNZAN)
					, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK, ModBlocks.SUGAR_BLOCK);*/
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.ichirinRing) {
			AliceAPI.registerAltarRecipeThunder(new ItemStack(ModItems.ICHIRIN_RING)
					, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.kappaHat) {
			AliceAPI.registerAltarRecipeRain(new ItemStack(ModItems.KAPPA_HAT)
					, new ItemStack(Blocks.WOOL, 1, 5), new ItemStack(Blocks.WOOL, 1, 5), new ItemStack(Blocks.WOOL, 1, 5), new ItemStack(Blocks.WOOL, 1, 5), Items.SLIME_BALL, Blocks.WATERLILY, Blocks.WATERLILY, Blocks.WATERLILY);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.mapleLeafShield) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.MAPLE_LEAF_SHIELD)
					, ModItems.TAMAHAGANE_STEEL, ModItems.TAMAHAGANE_STEEL, ModItems.TAMAHAGANE_STEEL, ModItems.TAMAHAGANE_STEEL, "treeLeaves", "treeLeaves", "treeLeaves", "treeLeaves");
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.marisaHat) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.MARISA_HAT)
					, new ItemStack(Blocks.WOOL, 1, 7), new ItemStack(Blocks.WOOL, 1, 7), new ItemStack(Blocks.WOOL, 1, 7), new ItemStack(Blocks.WOOL, 1, 7), Items.BOOK, new ItemStack(Blocks.WOOL, 1, 0), new ItemStack(Blocks.WOOL, 1, 0), new ItemStack(Blocks.WOOL, 1, 0));
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.mikoCloak) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.MIKO_CLOAK)
					, new ItemStack(Blocks.WOOL, 1, 2), "paper", ModItems.HARDENED_LEATHER, "paper", "paper", ModItems.HARDENED_LEATHER, "paper", "dyeMagenta");
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.mikoStick) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.MIKO_STICK)
					, PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.HEALING), "stickWood");
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.mochiHammer) {
			AliceAPI.registerAltarRecipeMoonPhase(MoonPhase.WANING_GIBBOUS, new ItemStack(ModItems.MOCHI_HAMMER)
					, "logWood", "logWood", "logWood", Items.RABBIT_HIDE, Items.RABBIT_HIDE, "stickWood", "stickWood", Items.SLIME_BALL);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.momijisScimitarSword) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.MOMIJI_SCIMITAR_SWORD)
					, ModItems.TAMAHAGANE_STEEL, ModItems.TAMAHAGANE_STEEL, ModItems.TAMAHAGANE_STEEL, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.narzinStick) {
			AliceAPI.registerAltarRecipe(ItemNazrinStick.TYPEA
					, Items.COMPASS, Items.EMERALD, new ItemStack(Items.COAL, 1, 0), new ItemStack(Items.COAL, 1, 0), new ItemStack(Items.COAL, 1, 0), new ItemStack(Items.COAL, 1, 0));
			AliceAPI.registerAltarRecipe(ItemNazrinStick.TYPEB
					, Items.COMPASS, Items.EMERALD, new ItemStack(Items.COAL, 1, 1), new ItemStack(Items.COAL, 1, 1), new ItemStack(Items.COAL, 1, 1), new ItemStack(Items.COAL, 1, 1));
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.needle) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.SHINMYOUMARU_NEEDLE)
					, ModItems.TAMAHAGANE_STEEL, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, Items.FEATHER);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.nazrinPendulum) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.NAZRIN_PENDULUM)
					, "blockGlassCyan", "blockGlassCyan", "blockGlassCyan", "blockGlassCyan", "blockGlassCyan", "blockGlassCyan", Items.END_CRYSTAL, Blocks.DAYLIGHT_DETECTOR);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.nimbleFabric) {
			ItemStack item = new ItemStack(Blocks.CARPET, 1, OreDictionary.WILDCARD_VALUE);
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.NIMBLE_FABRIC)
					, Items.LEATHER, ModItems.HARDENED_LEATHER, Items.LEATHER, item, item, item);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.nueTrident) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.NUE_TRIDENT)
					, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Items.BLAZE_ROD);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.ibukiGourd) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.IBUKI_GOURD)
					, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER, Items.FERMENTED_SPIDER_EYE, ModItems.SOLIDIFIED_PAPER, ModItems.SOLIDIFIED_PAPER, ModItems.SOLIDIFIED_PAPER);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.icicleSword) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.ICICLE_SWORD)
					, Items.STICK, Items.STICK, Blocks.PACKED_ICE, Blocks.ICE, Blocks.ICE, Blocks.PACKED_ICE, Blocks.ICE, Blocks.PACKED_ICE);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.rodOfRemorse) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.ROD_REMORSE)
					, "logWood", Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, "dyeBlack");
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.roukanken) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.ROUKANKEN)
					, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, "stickWood", Blocks.RED_FLOWER);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.rumiaSword) {
			AliceAPI.registerAltarRecipeDimension(DimensionType.NETHER, new ItemStack(ModItems.RUMIA_SWORD)
					, ModBlocks.HIHIIROKANE_BLOCK, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.sacredToyosatomimiSword) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.SACRED_SWORD_OF_TOYOSATOMIMI)
					, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.SOLIDIFIED_PAPER, ModItems.SOLIDIFIED_PAPER, ModItems.TAMAHAGANE_STEEL);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.sanaeGohei) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.SANAE_GOHEI)
					, "paper", "paper", "stickWood", ModItems.SHIMENAWA_ROPE, "paper", "stickWood", "paper", "paper");
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.sarielWand) {
			AliceAPI.registerAltarRecipeThunder(new ItemStack(ModItems.SARIEL_WAND)
					, Items.NETHER_STAR, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.sichiSeiken) {
			AliceAPI.registerAltarRecipeMoonPhase(MoonPhase.FIRST_QUARTER, new ItemStack(ModItems.SHICHI_SEIKEN)
					, Items.IRON_INGOT, Items.GOLD_INGOT, ModItems.TAMAHAGANE_STEEL);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.kanakoOnbashira) {
			AliceAPI.registerAltarRecipeRain(new ItemStack(ModItems.KANAKO_ONBASHIRA)
					, "logWood", "logWood", "logWood", "logWood", "stickWood", ModItems.SHIMENAWA_ROPE);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.skull) {
			AliceAPI.registerAltarRecipeDimension(DimensionType.NETHER, new ItemStack(ModItems.ORIN_SKULL)
					, new ItemStack(Items.SKULL, 1, OreDictionary.WILDCARD_VALUE));
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.substituteJizo) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.SUBSTITUTE_JIZO)
					, Items.LEATHER, ModItems.HARDENED_LEATHER, Items.LEATHER, ModItems.SOLIDIFIED_PAPER, ModItems.SOLIDIFIED_PAPER, ModItems.SOLIDIFIED_PAPER);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.suwakoHat) {
			AliceAPI.registerAltarRecipeRain(new ItemStack(ModItems.SUWAKO_HAT)
					, Items.ENDER_EYE, Items.ENDER_EYE, Blocks.WATERLILY, Items.LEATHER_HELMET);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.swallowCowrieShell) {
			AliceAPI.registerAltarRecipeMoonPhase(MoonPhase.WAXING_GIBBOUS, new ItemStack(ModItems.SWALLOW_COWRIE_SHELL)
					, ModItems.COWRIE_SHELL, ModItems.SWALLOW_EGG, ModItems.COWRIE_SHELL, ModItems.SWALLOW_EGG, ModItems.SWALLOW_EGG, ModItems.COWRIE_SHELL, ModItems.SWALLOW_EGG, ModItems.COWRIE_SHELL, Blocks.BONE_BLOCK, Blocks.BONE_BLOCK, Blocks.BONE_BLOCK, Blocks.BONE_BLOCK, Blocks.BONE_BLOCK, Blocks.BONE_BLOCK, Blocks.BONE_BLOCK, Blocks.BONE_BLOCK);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.shinmyoumaruBowl) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.SHINMYOUMARU_HAT)
					, Items.BRICK, Items.BRICK, Items.BRICK, Items.BRICK, Items.BRICK, Items.BRICK, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.wallPassingChisel) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.WALL_PASSING_CHISEL)
					, Items.ENDER_EYE, "stickWood", "stickWood", Items.NAME_TAG);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.ufo) {
			AliceAPI.registerAltarRecipeDimension(DimensionType.THE_END, new ItemStack(ModItems.UFO)
					, ModItems.UFO_BLUE, ModItems.UFO_GREEN, ModItems.UFO_RED, Blocks.LEVER, Items.REDSTONE, Items.REDSTONE, Items.REDSTONE, Items.REDSTONE);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.youkaiBook) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.YOUKAI_BOOK)
					, Items.BOOK, Items.FEATHER, Items.RABBIT_FOOT);
		}

		if(ConfigHandler.grimoireOfAlice.food.altar.ultramarineOrbElixir) {
			ItemStack stack = new ItemStack(ModBlocks.SHROOM, 1, 14);
			ItemStack potion = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.STRONG_REGENERATION);
			AliceAPI.registerAltarRecipeDimension(DimensionType.THE_END, new ItemStack(ModItems.ORB_ELIXIR)
					, potion, stack, stack, stack, stack, stack, stack, stack, stack, stack, stack, stack, stack, stack, stack, stack);
		}

		if(ConfigHandler.grimoireOfAlice.food.altar.houraiElixir) {
			AliceAPI.registerAltarRecipeDimension(DimensionType.THE_END, new ItemStack(ModItems.HOURAI_ELIXIR)
					, Blocks.DRAGON_EGG, Items.FLOWER_POT, Items.FLOWER_POT, Items.FLOWER_POT, Items.FLOWER_POT, Items.FLOWER_POT, Items.FLOWER_POT, ModItems.ORB_ELIXIR);
		}

		if(ConfigHandler.grimoireOfAlice.food.altar.kappaNostrum) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.KAPPAS_NOSTRUM)
					, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.GHAST_TEAR, new ItemStack(Items.FLOWER_POT));
		}

		if(ConfigHandler.grimoireOfAlice.food.altar.yuugiSake) {
			AliceAPI.registerAltarRecipeDimension(DimensionType.NETHER, new ItemStack(ModItems.YUUGI_SAKE)
					, ModItems.IBUKI_GOURD, Items.FLOWER_POT, Items.WHEAT, Items.WHEAT, Items.WHEAT, Items.WHEAT, Items.GHAST_TEAR, Items.FERMENTED_SPIDER_EYE);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.toyosatomimiHat) {
			String dye = "dyeMagenta";
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.TOYOSATOMIMI_HAT)
					, Items.LEATHER_HELMET, Items.RABBIT_FOOT, dye, dye, dye, dye, dye, dye);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.utsuhoWings) {
			AliceAPI.registerAltarRecipeDimension(DimensionType.NETHER, new ItemStack(ModItems.UTSUHO_WINGS)
					, ModItems.BLACK_FEATHER, ModItems.BLACK_FEATHER, ModItems.BLACK_FEATHER, ModItems.BLACK_FEATHER, ModItems.BLACK_FEATHER, ModItems.BLACK_FEATHER, ModItems.BLACK_FEATHER, Items.ELYTRA, ModBlocks.HIHIIROKANE_BLOCK, Blocks.COAL_BLOCK, ModItems.BLACK_FEATHER, ModItems.BLACK_FEATHER, ModItems.BLACK_FEATHER, ModItems.BLACK_FEATHER, ModItems.BLACK_FEATHER, ModItems.BLACK_FEATHER);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.nuclearBoots) {
			AliceAPI.registerAltarRecipeDimension(DimensionType.NETHER, new ItemStack(ModItems.NUCLEAR_BOOTS)
					, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER, Items.LEATHER_BOOTS, ModItems.HIHIIROKANE);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.netherShard) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.NETHER_SHARD)
					, Items.DIAMOND, Items.QUARTZ, Items.QUARTZ, Items.QUARTZ, Items.QUARTZ, Items.QUARTZ, Items.QUARTZ, Items.QUARTZ);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.gap) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.GAP)
					, Blocks.REDSTONE_BLOCK, Items.CHORUS_FRUIT, Blocks.REDSTONE_BLOCK, Blocks.REDSTONE_BLOCK, Blocks.REDSTONE_BLOCK);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.ghostAnchor) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.GHOST_ANCHOR)
					, Items.IRON_INGOT, Items.IRON_INGOT, ModItems.HIHIIROKANE, Items.IRON_INGOT, Items.IRON_INGOT);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.healingCharm) {
			String dye = "dyeLightBlue";
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.CHARM_OF_HEALING)
					, dye, dye, dye, dye, dye, dye, dye, Items.GHAST_TEAR);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.kinkakuCeiling) {
			AliceAPI.registerAltarRecipeMoonPhase(MoonPhase.NEW_MOON, new ItemStack(ModItems.SEAMLESS_CEILING_OF_KINKAKUJI)
					, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.ANVIL, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.redstoneAja) {
			AliceAPI.registerAltarRecipeMoonPhase(MoonPhase.FULL_MOON, new ItemStack(ModItems.RED_STONE_OF_AJA)
					, Items.GOLD_INGOT, Items.EMERALD, ModItems.NETHER_SHARD, "blockGlassColorless", ModItems.NETHER_SHARD, Items.EMERALD, Items.GOLD_INGOT, ModItems.NETHER_SHARD);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.stopWatch) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.STOPWATCH)
					, Items.CLOCK, Items.REDSTONE, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, ModItems.NETHER_SHARD, ModItems.TIME_ORB, ModItems.TIME_ORB, ModItems.TIME_ORB, ModItems.TIME_ORB, ModItems.TIME_ORB, ModItems.TIME_ORB, ModItems.TIME_ORB);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.danmakuCore.tenguCamera) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.AYA_CAMERA)
					, "blockGlassColorless", Blocks.LEVER, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, Blocks.REDSTONE_LAMP);
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.HATATE_CAMERA)
					, "blockGlassColorless", Blocks.STONE_BUTTON, Items.REDSTONE, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, Blocks.REDSTONE_LAMP);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.danmakuCore.nuclearRod) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.NUCLEAR_ROD)
					, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.danmakuCore.ellyScythe) {
			AliceAPI.registerAltarRecipeDimension(DimensionType.NETHER, new ItemStack(ModItems.ELLY_SCYTHE)
					, "dyeRed", Items.NETHERBRICK, Items.NETHERBRICK, Items.NETHERBRICK, Items.NETHERBRICK, Items.NETHERBRICK, ModItems.IMPURE_ROCK, ModItems.IMPURE_ROCK);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.danmakuCore.laevatein) {
			AliceAPI.registerAltarRecipeDimension(DimensionType.NETHER, new ItemStack(ModItems.LAEVATEIN)
					, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_POWDER, Items.BLAZE_POWDER, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.danmakuCore.miracleMallet) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.MIRACLE_MALLET)
					, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Items.GOLD_NUGGET, Items.LEAD, Items.GOLD_NUGGET, Items.GOLD_NUGGET, Blocks.SLIME_BLOCK, Items.GOLD_NUGGET, Items.MELON_SEEDS, Items.MELON_SEEDS, Items.MELON_SEEDS, Items.NETHER_STAR, Items.MELON_SEEDS);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.danmakuCore.trumpet) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.MERLIN_TRUMPET)
					, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, ModItems.SOLIDIFIED_PAPER, ModItems.SOLIDIFIED_PAPER);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.danmakuCore.piano) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.LYRICA_PIANO)
					, Items.IRON_INGOT, Items.IRON_INGOT, Items.CLAY_BALL, Items.CLAY_BALL, Items.CLAY_BALL, Items.CLAY_BALL);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.danmakuCore.violin) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.LUNASA_VIOLIN)
					, "plankWood", "plankWood", "plankWood", Items.STRING, Items.STRING, "stickWood");
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.danmakuCore.spellCardPuch) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.SPELLCARD_POUCH)
					, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER, ModItems.HARDENED_LEATHER, Blocks.CHEST);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.danmakuCore.swordOfHisou) {
			AliceAPI.registerAltarRecipeRain(new ItemStack(ModItems.HISOU_SWORD)
					, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, Items.NETHER_STAR, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.HIHIIROKANE, ModItems.NETHER_SHARD, ModItems.NETHER_SHARD, ModItems.NETHER_SHARD, ModItems.NETHER_SHARD, ModItems.NETHER_SHARD, ModItems.NETHER_SHARD, ModItems.NETHER_SHARD, ModItems.NETHER_SHARD);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.danmakuCore.tenguFan) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.TENGU_FAN)
					, "stickWood", "stickWood", "stickWood", "stickWood", "stickWood", "stickWood", "treeLeaves", "treeLeaves");
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.danmakuCore.shouLamp) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.SHOU_LAMP)
					, Blocks.SEA_LANTERN, Blocks.SEA_LANTERN, Blocks.SEA_LANTERN, Blocks.SEA_LANTERN, Blocks.SEA_LANTERN, Blocks.SEA_LANTERN, Blocks.GLOWSTONE, Blocks.GLOWSTONE, ModBlocks.ROPE, ModBlocks.ROPE, ModBlocks.ROPE, ModBlocks.ROPE, ModBlocks.ROPE, ModBlocks.ROPE, ModBlocks.ROPE, ModBlocks.ROPE);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.danmakuCore.jeweledHourai) {
			AliceAPI.registerAltarRecipeMoonPhase(MoonPhase.WANING_CRESCENT, new ItemStack(ModItems.JEWELED_HOURAI)
					, "treeSapling", Items.NETHER_STAR, Items.DIAMOND, ModItems.NETHER_SHARD, ModItems.NETHER_SHARD, Items.DIAMOND, ModItems.NETHER_SHARD, Items.DIAMOND, Items.GOLD_INGOT, ModItems.NETHER_SHARD, ModItems.NETHER_SHARD, Items.GOLD_INGOT, Items.GOLD_INGOT, ModItems.NETHER_SHARD, ModItems.NETHER_SHARD, Items.GOLD_INGOT);
		}

		AliceAPI.registerAltarRecipeDimension(DimensionType.THE_END, new ItemStack(Items.END_CRYSTAL)
				, "blockGlassColorless", "blockGlassColorless", "blockGlassColorless", "blockGlassColorless", "blockGlassColorless", "blockGlassColorless", "blockGlassColorless", Items.ENDER_EYE, Items.GHAST_TEAR);
	}
}
