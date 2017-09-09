/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
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
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.mask)
					, ModItems.paste, ModItems.solidified_paper, ModItems.solidified_paper);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.hihiirokane) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.hihiirokane)
					, ModItems.impure_rock, Blocks.COAL_BLOCK);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.patchyBook) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.patchy_book)
					, Items.WRITABLE_BOOK, "stickWood", Items.FEATHER);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.syringe) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.syringe)
					, Items.GLASS_BOTTLE, new ItemStack(ModItems.shroom_powder, 1, OreDictionary.WILDCARD_VALUE));
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.simpleUfo) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.ufo_green)
					, Blocks.SLIME_BLOCK, ModItems.paste, ModItems.paste, "blockGlassGreen", "blockGlassGreen", "blockGlassGreen", "blockGlassGreen", "blockGlassGreen");
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.ufo_red)
					, Blocks.SLIME_BLOCK, ModItems.paste, ModItems.paste, "blockGlassRed", "blockGlassRed", "blockGlassRed", "blockGlassRed", "blockGlassRed");
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.ufo_blue)
					, Blocks.SLIME_BLOCK, ModItems.paste, ModItems.paste, "blockGlassBlue", "blockGlassBlue", "blockGlassBlue", "blockGlassBlue", "blockGlassBlue");
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.masks.kokoroMask) {
			ItemStack potion = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.AWKWARD);

			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.kokoro_masks), ModItems.fox_mask,
					ModItems.fuku_no_kami_mask, ModItems.hannya_mask, ModItems.hyottoko_mask, ModItems.koomote_mask, ModItems.mask_of_hope,
					ModItems.monkey_mask, ModItems.raiden_mask, ModItems.uba_mask, potion, ModItems.mask, Items.NETHER_STAR);

			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.fox_mask), ModItems.mask, ModItems.nether_shard,
					ModItems.hardened_leather, potion, Items.SUGAR, "dyeBlack");

			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.fuku_no_kami_mask), ModItems.mask, ModItems.nether_shard,
					ModItems.hardened_leather, potion, Items.BLAZE_POWDER, "dyeOrange");

			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.hannya_mask), ModItems.mask, ModItems.nether_shard,
					ModItems.hardened_leather, potion, Items.POISONOUS_POTATO, "dyeRed");

			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.hyottoko_mask), ModItems.mask, ModItems.nether_shard,
					ModItems.hardened_leather, potion, Items.GHAST_TEAR, "dyePink");

			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.koomote_mask), ModItems.mask, ModItems.nether_shard,
					ModItems.hardened_leather, potion, Items.ROTTEN_FLESH, "dyeBlack");

			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.mask_of_hope), ModItems.mask, ModItems.nether_shard,
					ModItems.hardened_leather, potion, Items.FERMENTED_SPIDER_EYE, "dyeGray");

			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.monkey_mask), ModItems.mask, ModItems.nether_shard,
					ModItems.hardened_leather, potion, Items.MUSHROOM_STEW, "dyeLightGray");

			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.raiden_mask), ModItems.mask, ModItems.nether_shard,
					ModItems.hardened_leather, potion, Items.SLIME_BALL, "dyePink");

			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.uba_mask), ModItems.mask, ModItems.nether_shard,
					ModItems.hardened_leather, potion, Items.BEETROOT_SOUP, "dyePink");
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.thirdEye) {
			AliceAPI.registerAltarRecipeDimension(DimensionType.NETHER, new ItemStack(ModItems.third_eye)
					, Blocks.OBSIDIAN, Items.ENDER_EYE, Items.ENDER_EYE, Items.ENDER_PEARL, Items.ENDER_PEARL);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.amenonuhoko) {
			AliceAPI.registerAltarRecipeRain(new ItemStack(ModItems.amenonuhoko)
					, Items.END_CRYSTAL, Items.END_CRYSTAL, Items.END_CRYSTAL, ModItems.hihiirokane, ModItems.hihiirokane, ModBlocks.compact_stone, Items.NETHER_STAR, ModBlocks.compact_stone, ModBlocks.compact_stone, ModBlocks.compact_stone, ModBlocks.compact_stone, ModItems.hihiirokane, ModItems.hihiirokane);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.bloodThirstyOrb) {
			AliceAPI.registerAltarRecipeDimension(DimensionType.NETHER, new ItemStack(ModItems.blood_thirsty_orb)
					, Items.ENDER_EYE, Items.NETHER_WART, Items.NETHER_WART, Items.NETHER_WART, Items.NETHER_WART);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.budahBoul) {
			AliceAPI.registerAltarRecipeMoonPhase(MoonPhase.WAXING_CRESCENT, new ItemStack(ModItems.budah_bowl)
					, ModBlocks.compact_stone, ModBlocks.compact_stone, ModBlocks.compact_stone, ModBlocks.compact_stone, ModBlocks.compact_stone, ModBlocks.compact_stone, ModBlocks.compact_stone, ModBlocks.compact_stone, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.STONE, Blocks.CLAY, Blocks.STONE, Items.NETHER_STAR);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.cursedDecoyDoll) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.cursed_decoy_doll)
					, Items.BEEF, Items.BEEF, Items.BEEF, ModItems.hardened_leather, ModItems.hardened_leather, ModItems.hardened_leather, Blocks.PUMPKIN);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.deathScythe) {
			AliceAPI.registerAltarRecipeDimension(DimensionType.NETHER, new ItemStack(ModItems.komachi_scythe)
					, Blocks.SOUL_SAND, ModItems.hihiirokane, "stickWood", ModItems.hihiirokane, new ItemStack(Items.SKULL, 1, 1), Blocks.SOUL_SAND, ModItems.hihiirokane, "stickWood");
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.dragonJewel) {
			AliceAPI.registerAltarRecipeMoonPhase(MoonPhase.NEW_MOON, new ItemStack(ModItems.dragon_jewel)
					, new ItemStack(Items.SKULL, 1, 5), ModBlocks.dragon_stone, "nuggetGold", "nuggetGold", "nuggetGold", "nuggetGold", "nuggetGold", "nuggetGold", ModItems.nether_shard, ModItems.nether_shard, ModItems.nether_shard, ModItems.nether_shard, ModItems.nether_shard, ModItems.nether_shard, ModItems.nether_shard, ModItems.nether_shard);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.dragonStone) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModBlocks.dragon_stone)
					, ModItems.dragon_scale, ModItems.dragon_scale, ModItems.dragon_scale, ModItems.dragon_scale, ModItems.dragon_scale, ModItems.dragon_scale, ModItems.dragon_scale, ModItems.dragon_scale, ModItems.dragon_scale, ModItems.dragon_scale, ModItems.dragon_scale, ModItems.dragon_scale, ModItems.dragon_scale, ModItems.dragon_scale, ModItems.dragon_scale, ModItems.dragon_scale);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.fakeMiracleMallet) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.fake_miracle_mallet)
					, ModItems.paste, ModItems.paste, ModItems.paste, ModItems.paste, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, ModItems.impure_rock);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.kakanoShimenawa) {
			AliceAPI.registerAltarRecipeRain(new ItemStack(ModItems.kanako_shimenawa)
					, ModBlocks.rope_block, ModBlocks.rope_block, ModBlocks.rope_block, ModBlocks.rope_block, ModBlocks.rope_block, ModBlocks.rope_block, ModBlocks.rope_block, ModBlocks.rope_block, ModBlocks.rope_block, ModBlocks.rope_block, ModBlocks.rope_block, Items.NETHER_STAR, ModBlocks.paper_block, ModBlocks.paper_block, ModBlocks.paper_block, ModBlocks.paper_block);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.fireRobe) {
			AliceAPI.registerAltarRecipeMoonPhase(MoonPhase.WANING_CRESCENT, new ItemStack(ModItems.fire_robe)
					, Items.CHAINMAIL_CHESTPLATE, Items.LEATHER_CHESTPLATE, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.RABBIT_HIDE, Items.RABBIT_HIDE, ModItems.hardened_leather, Items.RABBIT_HIDE, ModItems.hardened_leather, Items.RABBIT_HIDE, ModItems.hardened_leather, ModItems.hardened_leather);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.gapFoldingUmbrella) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.folding_umbrella)
					, ModItems.paste, new ItemStack(Blocks.WOOL, 1, 10), ModItems.hardened_leather, ModItems.hardened_leather, ModItems.hardened_leather, "stickWood");
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.ghostDipper) {
			AliceAPI.registerAltarRecipeRain(new ItemStack(ModItems.ghost_dipper)
					, "stickWood", "stickWood", Items.BOWL, Items.BOWL, new ItemStack(Blocks.SPONGE, 1, 1), new ItemStack(Blocks.SPONGE, 1, 1), Blocks.SPONGE, Blocks.SPONGE);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.grimoireBook) {
			ItemStack item = new ItemStack(ModItems.shroom_powder, 1, OreDictionary.WILDCARD_VALUE);
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.grimoire_book)
					, Items.WRITABLE_BOOK, ModItems.solidified_paper, ModItems.solidified_paper, ModItems.solidified_paper, item, item, item, item, item, item);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.hakureiGohei) {
			AliceAPI.registerAltarRecipe(new RecipeHakurei());
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.ibarakiBox) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.ibaraki_box_empty)
					, "plankWood", "plankWood", "plankWood", "plankWood", "plankWood", "plankWood", "plankWood", Items.GLASS_BOTTLE);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.ichirinUnzan) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.ichirin_unzan)
					, ModBlocks.sugar_block, ModBlocks.sugar_block, ModBlocks.sugar_block, ModBlocks.sugar_block, ModBlocks.sugar_block, ModBlocks.sugar_block, ModBlocks.sugar_block, ModBlocks.sugar_block, ModBlocks.sugar_block, ModBlocks.sugar_block, ModBlocks.sugar_block, ModBlocks.sugar_block, ModBlocks.sugar_block, ModBlocks.sugar_block, ModBlocks.sugar_block, ModBlocks.sugar_block);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.ichirinRing) {
			AliceAPI.registerAltarRecipeThunder(new ItemStack(ModItems.ichirin_ring)
					, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.kappaHat) {
			AliceAPI.registerAltarRecipeRain(new ItemStack(ModItems.kappa_hat)
					, new ItemStack(Blocks.WOOL, 1, 5), new ItemStack(Blocks.WOOL, 1, 5), new ItemStack(Blocks.WOOL, 1, 5), new ItemStack(Blocks.WOOL, 1, 5), Items.SLIME_BALL, Blocks.WATERLILY, Blocks.WATERLILY, Blocks.WATERLILY);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.mapleLeafShield) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.maple_leaf_shield)
					, ModItems.tamahagane_steel, ModItems.tamahagane_steel, ModItems.tamahagane_steel, ModItems.tamahagane_steel, "treeLeaves", "treeLeaves", "treeLeaves", "treeLeaves");
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.marisaHat) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.marisa_hat)
					, new ItemStack(Blocks.WOOL, 1, 7), new ItemStack(Blocks.WOOL, 1, 7), new ItemStack(Blocks.WOOL, 1, 7), new ItemStack(Blocks.WOOL, 1, 7), Items.BOOK, new ItemStack(Blocks.WOOL, 1, 0), new ItemStack(Blocks.WOOL, 1, 0), new ItemStack(Blocks.WOOL, 1, 0));
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.mikoCloak) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.miko_cloak)
					, new ItemStack(Blocks.WOOL, 1, 2), "paper", ModItems.hardened_leather, "paper", "paper", ModItems.hardened_leather, "paper", "dyeMagenta");
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.mikoStick) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.miko_stick)
					, PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.HEALING), "stickWood");
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.mochiHammer) {
			AliceAPI.registerAltarRecipeMoonPhase(MoonPhase.WANING_GIBBOUS, new ItemStack(ModItems.mochi_hammer)
					, "logWood", "logWood", "logWood", Items.RABBIT_HIDE, Items.RABBIT_HIDE, "stickWood", "stickWood", Items.SLIME_BALL);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.momijisScimitarSword) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.momiji_scimitar_sword)
					, ModItems.tamahagane_steel, ModItems.tamahagane_steel, ModItems.tamahagane_steel, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.narzinStick) {
			AliceAPI.registerAltarRecipe(ItemNazrinStick.TYPEA
					, Items.COMPASS, Items.EMERALD, new ItemStack(Items.COAL, 1, 0), new ItemStack(Items.COAL, 1, 0), new ItemStack(Items.COAL, 1, 0), new ItemStack(Items.COAL, 1, 0));
			AliceAPI.registerAltarRecipe(ItemNazrinStick.TYPEB
					, Items.COMPASS, Items.EMERALD, new ItemStack(Items.COAL, 1, 1), new ItemStack(Items.COAL, 1, 1), new ItemStack(Items.COAL, 1, 1), new ItemStack(Items.COAL, 1, 1));
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.needle) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.shinmyoumaru_needle)
					, ModItems.tamahagane_steel, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, Items.FEATHER);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.nazrinPendulum) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.nazrin_pendulum)
					, "blockGlassCyan", "blockGlassCyan", "blockGlassCyan", "blockGlassCyan", "blockGlassCyan", "blockGlassCyan", Items.END_CRYSTAL, Blocks.DAYLIGHT_DETECTOR);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.nimbleFabric) {
			ItemStack item = new ItemStack(Blocks.CARPET, 1, OreDictionary.WILDCARD_VALUE);
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.nimble_fabric)
					, Items.LEATHER, ModItems.hardened_leather, Items.LEATHER, item, item, item);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.nueTrident) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.nue_trident)
					, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Items.BLAZE_ROD);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.ibukiGourd) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.ibuki_gourd)
					, ModItems.hardened_leather, ModItems.hardened_leather, ModItems.hardened_leather, Items.FERMENTED_SPIDER_EYE, ModItems.solidified_paper, ModItems.solidified_paper, ModItems.solidified_paper);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.icicleSword) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.icicle_sword)
					, Items.STICK, Items.STICK, Blocks.PACKED_ICE, Blocks.ICE, Blocks.ICE, Blocks.PACKED_ICE, Blocks.ICE, Blocks.PACKED_ICE);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.rodOfRemorse) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.rod_of_remorse)
					, "logWood", Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, "dyeBlack");
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.roukanken) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.roukanken)
					, ModItems.hihiirokane, ModItems.hihiirokane, ModItems.hihiirokane, "stickWood", Blocks.RED_FLOWER);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.rumiaSword) {
			AliceAPI.registerAltarRecipeDimension(DimensionType.NETHER, new ItemStack(ModItems.rumia_sword)
					, ModBlocks.hihiirokane_block, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.sacredToyosatomimiSword) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.sacred_sword_of_toyosatomimi)
					, ModItems.hihiirokane, ModItems.hihiirokane, ModItems.hihiirokane, ModItems.hihiirokane, ModItems.hihiirokane, ModItems.solidified_paper, ModItems.solidified_paper, ModItems.tamahagane_steel);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.sanaeGohei) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.sanae_gohei)
					, "paper", "paper", "stickWood", ModItems.shimenawa_rope, "paper", "stickWood", "paper", "paper");
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.sarielWand) {
			AliceAPI.registerAltarRecipeThunder(new ItemStack(ModItems.sariel_wand)
					, Items.NETHER_STAR, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.sichiSeiken) {
			AliceAPI.registerAltarRecipeMoonPhase(MoonPhase.FIRST_QUARTER, new ItemStack(ModItems.shichi_seiken)
					, Items.IRON_INGOT, Items.GOLD_INGOT, ModItems.tamahagane_steel);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.kanakoOnbashira) {
			AliceAPI.registerAltarRecipeRain(new ItemStack(ModItems.kanako_onbashira)
					, "logWood", "logWood", "logWood", "logWood", "stickWood", ModItems.shimenawa_rope);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.skull) {
			AliceAPI.registerAltarRecipeDimension(DimensionType.NETHER, new ItemStack(ModItems.orin_skull)
					, new ItemStack(Items.SKULL, 1, OreDictionary.WILDCARD_VALUE));
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.substituteJizo) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.substitute_jizo)
					, Items.LEATHER, ModItems.hardened_leather, Items.LEATHER, ModItems.solidified_paper, ModItems.solidified_paper, ModItems.solidified_paper);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.suwakoHat) {
			AliceAPI.registerAltarRecipeRain(new ItemStack(ModItems.suwako_hat)
					, Items.ENDER_EYE, Items.ENDER_EYE, Blocks.WATERLILY, Items.LEATHER_HELMET);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.swallowCowrieShell) {
			AliceAPI.registerAltarRecipeMoonPhase(MoonPhase.WAXING_GIBBOUS, new ItemStack(ModItems.swallow_cowrie_shell)
					, ModItems.cowrie_shell, ModItems.swallow_egg, ModItems.cowrie_shell, ModItems.swallow_egg, ModItems.swallow_egg, ModItems.cowrie_shell, ModItems.swallow_egg, ModItems.cowrie_shell, Blocks.BONE_BLOCK, Blocks.BONE_BLOCK, Blocks.BONE_BLOCK, Blocks.BONE_BLOCK, Blocks.BONE_BLOCK, Blocks.BONE_BLOCK, Blocks.BONE_BLOCK, Blocks.BONE_BLOCK);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.shinmyoumaruBowl) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.shinmyoumaru_hat)
					, Items.BRICK, Items.BRICK, Items.BRICK, Items.BRICK, Items.BRICK, Items.BRICK, ModItems.hardened_leather, ModItems.hardened_leather);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.wallPassingChisel) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.wall_passing_chisel)
					, Items.ENDER_EYE, "stickWood", "stickWood", Items.NAME_TAG);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.ufo) {
			AliceAPI.registerAltarRecipeDimension(DimensionType.THE_END, new ItemStack(ModItems.ufo)
					, ModItems.ufo_blue, ModItems.ufo_green, ModItems.ufo_red, Blocks.LEVER, Items.REDSTONE, Items.REDSTONE, Items.REDSTONE, Items.REDSTONE);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.youkaiBook) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.youkai_book)
					, Items.BOOK, Items.FEATHER, Items.RABBIT_FOOT);
		}

		if(ConfigHandler.grimoireOfAlice.food.altar.ultramarineOrbElixir) {
			ItemStack stack = new ItemStack(ModBlocks.shroom, 1, 14);
			ItemStack potion = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.STRONG_REGENERATION);
			AliceAPI.registerAltarRecipeDimension(DimensionType.THE_END, new ItemStack(ModItems.orb_elixir)
					, potion, stack, stack, stack, stack, stack, stack, stack, stack, stack, stack, stack, stack, stack, stack, stack);
		}

		if(ConfigHandler.grimoireOfAlice.food.altar.houraiElixir) {
			AliceAPI.registerAltarRecipeDimension(DimensionType.THE_END, new ItemStack(ModItems.hourai_elixir)
					, Blocks.DRAGON_EGG, Items.FLOWER_POT, Items.FLOWER_POT, Items.FLOWER_POT, Items.FLOWER_POT, Items.FLOWER_POT, Items.FLOWER_POT, ModItems.orb_elixir);
		}

		if(ConfigHandler.grimoireOfAlice.food.altar.kappaNostrum) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.kappas_nostrum)
					, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.GHAST_TEAR, new ItemStack(Items.FLOWER_POT));
		}

		if(ConfigHandler.grimoireOfAlice.food.altar.yuugiSake) {
			AliceAPI.registerAltarRecipeDimension(DimensionType.NETHER, new ItemStack(ModItems.yuugi_sake)
					, ModItems.ibuki_gourd, Items.FLOWER_POT, Items.WHEAT, Items.WHEAT, Items.WHEAT, Items.WHEAT, Items.GHAST_TEAR, Items.FERMENTED_SPIDER_EYE);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.toyosatomimiHat) {
			String dye = "dyeMagenta";
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.toyosatomimi_hat)
					, Items.LEATHER_HELMET, Items.RABBIT_FOOT, dye, dye, dye, dye, dye, dye);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.utsuhoWings) {
			AliceAPI.registerAltarRecipeDimension(DimensionType.NETHER, new ItemStack(ModItems.utsuho_wings)
					, ModItems.black_feather, ModItems.black_feather, ModItems.black_feather, ModItems.black_feather, ModItems.black_feather, ModItems.black_feather, ModItems.black_feather, Items.ELYTRA, ModBlocks.hihiirokane_block, Blocks.COAL_BLOCK, ModItems.black_feather, ModItems.black_feather, ModItems.black_feather, ModItems.black_feather, ModItems.black_feather, ModItems.black_feather);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.nuclearBoots) {
			AliceAPI.registerAltarRecipeDimension(DimensionType.NETHER, new ItemStack(ModItems.nuclear_boots)
					, ModItems.hardened_leather, ModItems.hardened_leather, ModItems.hardened_leather, ModItems.hardened_leather, ModItems.hardened_leather, Items.LEATHER_BOOTS, ModItems.hihiirokane);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.netherShard) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.nether_shard)
					, Items.DIAMOND, Items.QUARTZ, Items.QUARTZ, Items.QUARTZ, Items.QUARTZ, Items.QUARTZ, Items.QUARTZ, Items.QUARTZ);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.gap) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.gap)
					, Blocks.REDSTONE_BLOCK, Items.CHORUS_FRUIT, Blocks.REDSTONE_BLOCK, Blocks.REDSTONE_BLOCK, Blocks.REDSTONE_BLOCK);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.ghostAnchor) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.ghost_anchor)
					, Items.IRON_INGOT, Items.IRON_INGOT, ModItems.hihiirokane, Items.IRON_INGOT, Items.IRON_INGOT);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.healingCharm) {
			String dye = "dyeLightBlue";
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.charm_of_healing)
					, dye, dye, dye, dye, dye, dye, dye, Items.GHAST_TEAR);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.kinkakuCeiling) {
			AliceAPI.registerAltarRecipeMoonPhase(MoonPhase.NEW_MOON, new ItemStack(ModItems.seamless_ceiling_of_kinkakuji)
					, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.ANVIL, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.redstoneAja) {
			AliceAPI.registerAltarRecipeMoonPhase(MoonPhase.FULL_MOON, new ItemStack(ModItems.red_stone_of_aja)
					, Items.GOLD_INGOT, Items.EMERALD, ModItems.nether_shard, "blockGlassColorless", ModItems.nether_shard, Items.EMERALD, Items.GOLD_INGOT, ModItems.nether_shard);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.stopWatch) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.stopwatch)
					, Items.CLOCK, Items.REDSTONE, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, ModItems.nether_shard, ModItems.time_orb, ModItems.time_orb, ModItems.time_orb, ModItems.time_orb, ModItems.time_orb, ModItems.time_orb, ModItems.time_orb);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.danmakuCore.tenguCamera) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.aya_camera)
					, "blockGlassColorless", Blocks.LEVER, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, Blocks.REDSTONE_LAMP);
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.hatate_camera)
					, "blockGlassColorless", Blocks.STONE_BUTTON, Items.REDSTONE, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, Blocks.REDSTONE_LAMP);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.danmakuCore.nuclearRod) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.nuclear_rod)
					, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.danmakuCore.ellyScythe) {
			String dye = "dyeRed";
			AliceAPI.registerAltarRecipeDimension(DimensionType.NETHER, new ItemStack(ModItems.elly_scythe)
					, dye, dye, dye, dye, dye, dye, ModItems.impure_rock, ModItems.impure_rock);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.danmakuCore.laevatein) {
			AliceAPI.registerAltarRecipeDimension(DimensionType.NETHER, new ItemStack(ModItems.laevatein)
					, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_POWDER, Items.BLAZE_POWDER, ModItems.hihiirokane, ModItems.hihiirokane);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.danmakuCore.miracleMallet) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.miracle_mallet)
					, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Items.GOLD_NUGGET, Items.LEAD, Items.GOLD_NUGGET, Items.GOLD_NUGGET, Blocks.SLIME_BLOCK, Items.GOLD_NUGGET, Items.MELON_SEEDS, Items.MELON_SEEDS, Items.MELON_SEEDS, Items.NETHER_STAR, Items.MELON_SEEDS);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.danmakuCore.trumpet) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.merlin_trumpet)
					, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, ModItems.solidified_paper, ModItems.solidified_paper);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.danmakuCore.piano) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.lyrica_piano)
					, Items.IRON_INGOT, Items.IRON_INGOT, Items.CLAY_BALL, Items.CLAY_BALL, Items.CLAY_BALL, Items.CLAY_BALL);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.danmakuCore.violin) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.lunasa_violin)
					, "plankWood", "plankWood", "plankWood", Items.STRING, Items.STRING, "stickWood");
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.danmakuCore.spellCardPuch) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.spellcard_pouch)
					, ModItems.hardened_leather, ModItems.hardened_leather, ModItems.hardened_leather, ModItems.hardened_leather, ModItems.hardened_leather, ModItems.hardened_leather, ModItems.hardened_leather, Blocks.CHEST);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.danmakuCore.swordOfHisou) {
			AliceAPI.registerAltarRecipeRain(new ItemStack(ModItems.hisou_sword)
					, ModItems.hihiirokane, ModItems.hihiirokane, ModItems.hihiirokane, Items.NETHER_STAR, ModItems.hihiirokane, ModItems.hihiirokane, ModItems.hihiirokane, ModItems.hihiirokane, ModItems.nether_shard, ModItems.nether_shard, ModItems.nether_shard, ModItems.nether_shard, ModItems.nether_shard, ModItems.nether_shard, ModItems.nether_shard, ModItems.nether_shard);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.danmakuCore.tenguFan) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.tengu_fan)
					, "stickWood", "stickWood", "stickWood", "stickWood", "stickWood", "stickWood", "treeLeaves", "treeLeaves");
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.danmakuCore.shouLamp) {
			AliceAPI.registerAltarRecipe(new ItemStack(ModItems.shou_lamp)
					, Blocks.SEA_LANTERN, Blocks.SEA_LANTERN, Blocks.SEA_LANTERN, Blocks.SEA_LANTERN, Blocks.SEA_LANTERN, Blocks.SEA_LANTERN, Blocks.GLOWSTONE, Blocks.GLOWSTONE, ModBlocks.rope_block, ModBlocks.rope_block, ModBlocks.rope_block, ModBlocks.rope_block, ModBlocks.rope_block, ModBlocks.rope_block, ModBlocks.rope_block, ModBlocks.rope_block);
		}

		if(ConfigHandler.grimoireOfAlice.crafting.altar.danmakuCore.jeweledHourai) {
			AliceAPI.registerAltarRecipeMoonPhase(MoonPhase.WANING_CRESCENT, new ItemStack(ModItems.jeweled_hourai)
					, "treeSapling", Items.NETHER_STAR, Items.DIAMOND, ModItems.nether_shard, ModItems.nether_shard, Items.DIAMOND, ModItems.nether_shard, Items.DIAMOND, Items.GOLD_INGOT, ModItems.nether_shard, ModItems.nether_shard, Items.GOLD_INGOT, Items.GOLD_INGOT, ModItems.nether_shard, ModItems.nether_shard, Items.GOLD_INGOT);
		}
		AliceAPI.registerAltarRecipeRain(new ItemStack(ModBlocks.holy_stone)
				, ModBlocks.compact_stone, ModBlocks.rope_block, ModBlocks.paper_block, ModBlocks.paper_block, ModBlocks.paper_block, ModBlocks.paper_block, ModBlocks.paper_block, ModBlocks.paper_block, "stone", "stone", "stone", "stone", "stone", "stone", "stone", "stone");

		AliceAPI.registerAltarRecipeThunder(new ItemStack(ModBlocks.holy_key_stone)
				, ModBlocks.compact_stone, ModBlocks.compact_stone, ModBlocks.compact_stone, ModBlocks.compact_stone, ModBlocks.compact_stone, ModBlocks.compact_stone, ModBlocks.compact_stone, ModBlocks.compact_stone, ModItems.shimenawa_rope, Items.STRING, Items.STRING, ModItems.shimenawa_rope, ModItems.shimenawa_rope, Items.STRING, Items.STRING, ModItems.shimenawa_rope);

		AliceAPI.registerAltarRecipeRain(new ItemStack(ModBlocks.onbashira)
				, ModBlocks.rope_block, ModBlocks.pillar_altar, ModBlocks.pillar_altar, ModBlocks.paper_block, ModBlocks.pillar_altar, ModBlocks.rope_block, ModBlocks.paper_block, ModBlocks.pillar_altar);

		AliceAPI.registerAltarRecipeDimension(DimensionType.THE_END, new ItemStack(Items.END_CRYSTAL)
				, "blockGlassColorless", "blockGlassColorless", "blockGlassColorless", "blockGlassColorless", "blockGlassColorless", "blockGlassColorless", "blockGlassColorless", Items.ENDER_EYE, Items.GHAST_TEAR);
	}
}
