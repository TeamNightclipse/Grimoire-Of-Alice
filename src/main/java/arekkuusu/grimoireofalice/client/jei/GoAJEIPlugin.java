package arekkuusu.grimoireofalice.client.jei;

import arekkuusu.grimoireofalice.api.GrimoireOfAliceAPI;
import arekkuusu.grimoireofalice.common.block.ModBlocks;
import arekkuusu.grimoireofalice.common.item.ModItems;
import arekkuusu.grimoireofalice.common.lib.LibJEI;
import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

@JEIPlugin
public class GoAJEIPlugin extends BlankModPlugin {

	@Override
	public void register(IModRegistry registry) {
		registry.addRecipeCategories(new AltarRecipeItemsCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeHandlers(new AltarRecipeItemsHandler(registry.getJeiHelpers().getStackHelper()));
		registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.ALTAR), LibJEI.ALTER_CATEGORY_UID);
		registry.addRecipes(GrimoireOfAliceAPI.getRecipes());

		addDescriptions(registry);
	}

	private void addDescriptions(IModRegistry registry) {
		final String LINE = "--------------------------";
		registry.addDescription(new ItemStack(ModItems.BUDAH_BOUL), "grimoire.jei.descriptions.budah_boul", LINE
				, "grimoire.jei.descriptions_body.budah_boul");

		registry.addDescription(new ItemStack(ModItems.NAZRIN_STICK_ITEM), "grimoire.jei.descriptions.nazrin_stick", LINE
				, "grimoire.jei.descriptions_body.nazrin_stick");

		registry.addDescription(new ItemStack(ModItems.AMENONUHOKO), "grimoire.jei.descriptions.amenonuhoko", LINE
				, "grimoire.jei.descriptions_body.amenonuhoko");

		registry.addDescription(new ItemStack(ModItems.AYA_CAMERA), "grimoire.jei.descriptions.aya_camera", LINE
				, "grimoire.jei.descriptions_body.aya_camera");

		registry.addDescription(new ItemStack(ModItems.BLOOD_ORB), "grimoire.jei.descriptions.blood_orb", LINE
				, "grimoire.jei.descriptions_body.blood_orb");

		registry.addDescription(new ItemStack(ModItems.CATTAIL_PLANT), "grimoire.jei.descriptions.cattail_plant", LINE);

		registry.addDescription(new ItemStack(ModItems.CHERRY), "grimoire.jei.descriptions.cherry", LINE);

		registry.addDescription(new ItemStack(ModItems.COWRIE_SHELL), "grimoire.jei.descriptions.cowrie_shell", LINE);

		registry.addDescription(new ItemStack(ModItems.CURSED_DECOY_DOLL), "grimoire.jei.descriptions.cursed_decoy_doll", LINE
				, "grimoire.jei.descriptions_body.cursed_decoy_doll");

		registry.addDescription(new ItemStack(ModItems.DEATH_SCYTHE), "grimoire.jei.descriptions.death_scythe", LINE
				, "grimoire.jei.descriptions_body.death_scythe");

		registry.addDescription(new ItemStack(ModItems.DRAGON_JEWEL), "grimoire.jei.descriptions.dragon_jewel", LINE
				, "grimoire.jei.descriptions_body.dragon_jewel");

		registry.addDescription(new ItemStack(ModItems.DRAGON_SCALE), "grimoire.jei.descriptions.dragon_scale", LINE);

		registry.addDescription(new ItemStack(ModBlocks.DRAGON_STONE), "grimoire.jei.descriptions.dragon_stone", LINE);

		registry.addDescription(new ItemStack(ModItems.ELLY_SCYTHE), "grimoire.jei.descriptions.elly_scythe", LINE
				, "grimoire.jei.descriptions_body.elly_scythe");

		registry.addDescription(new ItemStack(ModItems.FAITH), "grimoire.jei.descriptions.faith", LINE);

		registry.addDescription(new ItemStack(ModItems.FAKE_MIRACLE_MALLET), "grimoire.jei.descriptions.fake_miracle_mallet", LINE);

		registry.addDescription(new ItemStack(ModItems.FIRE_ROBE), "grimoire.jei.descriptions.fire_robe", LINE
				, "grimoire.jei.descriptions_body.fire_robe");

		registry.addDescription(new ItemStack(ModItems.FOLDING_UMBRELLA), "grimoire.jei.descriptions.gap_folding_umbrella", LINE);

		registry.addDescription(new ItemStack(ModItems.FOX_MASK), "grimoire.jei.descriptions.fox_mask", LINE);

		registry.addDescription(new ItemStack(ModItems.FUKU_NO_KAMI_MASK), "grimoire.jei.descriptions.fuku_no_kami_mask", LINE);

		registry.addDescription(new ItemStack(ModItems.FULL_POWER), "grimoire.jei.descriptions.full_power", LINE);

		registry.addDescription(new ItemStack(ModItems.GHASTLY_SEND_OFF_LANTERN), "grimoire.jei.descriptions.ghastly_send_off_lantern", LINE);

		registry.addDescription(new ItemStack(ModItems.GHOST_DIPPER), "grimoire.jei.descriptions.ghost_dipper", LINE
				, "grimoire.jei.descriptions_body.ghost_dipper");

		registry.addDescription(new ItemStack(ModItems.GRILLED_LAMPREY), "grimoire.jei.descriptions.grilled_lamprey", LINE);

		registry.addDescription(new ItemStack(ModItems.GRIMOIRE_BOOK), "grimoire.jei.descriptions.grimoire_book", LINE);

		registry.addDescription(new ItemStack(ModItems.HAKUREI_GOHEI), "grimoire.jei.descriptions.hakurei_gohei", LINE
				, "grimoire.jei.descriptions_passive.hakurei_gohei"
				, "grimoire.jei.descriptions_aura_manipulation.hakurei_gohei"
				, "grimoire.jei.descriptions_hakurei_yin_yang_orbs.hakurei_gohei"
				, "grimoire.jei.descriptions_exploding_barrier.hakurei_gohei"
				, "grimoire.jei.descriptions_motion_barrier.hakurei_gohei"
				, "grimoire.jei.descriptions_offensive.hakurei_gohei");

		registry.addDescription(new ItemStack(ModItems.HANNYA_MASK), "grimoire.jei.descriptions.hannya_mask", LINE);

		registry.addDescription(new ItemStack(ModItems.HATATE_CAMERA), "grimoire.jei.descriptions.hatate_camera", LINE
				, "grimoire.jei.descriptions_body.hatate_camera");

		registry.addDescription(new ItemStack(ModItems.HEAVENLY_PEACH), "grimoire.jei.descriptions.heavenly_peach", LINE);

		registry.addDescription(new ItemStack(ModItems.HIHIIROKANE), "grimoire.jei.descriptions.hihiirokane", LINE);

		registry.addDescription(new ItemStack(ModItems.HISOU), "grimoire.jei.descriptions.hisou", LINE
				, "grimoire.jei.descriptions_body.hisou");

		registry.addDescription(new ItemStack(ModItems.HOURAI_ELIXIR), "grimoire.jei.descriptions.hourai_elixir", LINE
				, "grimoire.jei.descriptions_body.hourai_elixir");

		registry.addDescription(new ItemStack(ModItems.HYOTTOKO_MASK), "grimoire.jei.descriptions.hyottoko_mask", LINE);

		registry.addDescription(new ItemStack(ModItems.IBARAKI_BOX_EMPTY), "grimoire.jei.descriptions.ibaraki_box", LINE);

		registry.addDescription(new ItemStack(ModItems.IBUKI_GOURD), "grimoire.jei.descriptions.ibuki_gourd", LINE);

		registry.addDescription(new ItemStack(ModItems.ICHIRIN_UNZAN), "grimoire.jei.descriptions.ichirin_unzan", LINE);

		registry.addDescription(new ItemStack(ModItems.ICHIRIN_RING), "grimoire.jei.descriptions.ichirin_ring", LINE
				, "grimoire.jei.descriptions_body.ichirin_ring");

		registry.addDescription(new ItemStack(ModItems.IMPURE_ROCK), "grimoire.jei.descriptions.impure_rock", LINE);

		registry.addDescription(new ItemStack(ModItems.JEWELED_HOURAI), "grimoire.jei.descriptions.jeweled_hourai", LINE
				, "grimoire.jei.descriptions_body.jeweled_hourai");

		registry.addDescription(new ItemStack(ModItems.KANAKO_SHIMENAWA), "grimoire.jei.descriptions.kanako_shimenawa", LINE
				, "grimoire.jei.descriptions_body.kanako_shimenawa");

		registry.addDescription(new ItemStack(ModItems.KANAKO_ONBASHIRA), "grimoire.jei.descriptions.kanako_onbashira", LINE
				, "grimoire.jei.descriptions_body.kanako_onbashira");

		registry.addDescription(new ItemStack(ModItems.KAPPA_HAT), "grimoire.jei.descriptions.kappa_hat", LINE);

		registry.addDescription(new ItemStack(ModItems.KAPPAS_NOSTRUM), "grimoire.jei.descriptions.kappa_nostrum", LINE);

		registry.addDescription(new ItemStack(ModItems.KOKOROS_MASKS), "grimoire.jei.descriptions.kokoros_masks", LINE
				, "grimoire.jei.descriptions_body.kokoros_masks");

		registry.addDescription(new ItemStack(ModItems.KOOMOTE_MASK), "grimoire.jei.descriptions.koomote_mask", LINE);

		registry.addDescription(new ItemStack(ModItems.LAEVATEIN), "grimoire.jei.descriptions.laevatein", LINE
				, "grimoire.jei.descriptions_body.laevatein");

		registry.addDescription(new ItemStack(ModItems.LEAF), "grimoire.jei.descriptions.leaf", LINE);

		registry.addDescription(new ItemStack(ModItems.LUNASA_VIOLIN), "grimoire.jei.descriptions.lunasa_violin", LINE);

		registry.addDescription(new ItemStack(ModItems.LYRICA_PIANO), "grimoire.jei.descriptions.lyrica_piano", LINE);

		registry.addDescription(new ItemStack(ModItems.MAPLE_LEAF_SHIELD), "grimoire.jei.descriptions.maple_leaf_shield", LINE);

		registry.addDescription(new ItemStack(ModItems.MARISA_HAT), "grimoire.jei.descriptions.marisa_hat", LINE);

		registry.addDescription(new ItemStack(ModItems.MASK), "grimoire.jei.descriptions.mask", LINE);

		registry.addDescription(new ItemStack(ModItems.MASK_OF_HOPE), "grimoire.jei.descriptions.mask_of_hope", LINE);

		registry.addDescription(new ItemStack(ModItems.MERLIN_TRUMPET), "grimoire.jei.descriptions.merlin_trumpet", LINE);

		registry.addDescription(new ItemStack(ModItems.MIKO_CLOAK), "grimoire.jei.descriptions.miko_cloak", LINE
				, "grimoire.jei.descriptions_body.miko_cloak");

		registry.addDescription(new ItemStack(ModItems.MIKO_STICK), "grimoire.jei.descriptions.miko_stick", LINE);

		registry.addDescription(new ItemStack(ModItems.MIRACLE_MALLET), "grimoire.jei.descriptions.miracle_mallet", LINE
				, "grimoire.jei.descriptions_body.miracle_mallet");

		registry.addDescription(new ItemStack(ModItems.MOCHI_HAMMER), "grimoire.jei.descriptions.mochi_hammer", LINE);

		registry.addDescription(new ItemStack(ModItems.MOMIJIS_SCIMITAR_SWORD), "grimoire.jei.descriptions.momijis_scimitar_sword", LINE);

		registry.addDescription(new ItemStack(ModItems.MONKEY_MASK), "grimoire.jei.descriptions.monkey_mask", LINE);

		registry.addDescription(new ItemStack(ModItems.MORTAR_AND_PESTLE), "grimoire.jei.descriptions.mortal_n_pestle", LINE);

		registry.addDescription(new ItemStack(ModItems.NAZRIN_PENDULUM), "grimoire.jei.descriptions.nazrin_pendulum", LINE);

		registry.addDescription(new ItemStack(ModItems.NIMBLE_FABRIC), "grimoire.jei.descriptions.nimble_fabric", LINE);

		registry.addDescription(new ItemStack(ModItems.NUE_TRIDENT), "grimoire.jei.descriptions.nue_trident", LINE
				, "grimoire.jei.descriptions_body.nue_trident");

		registry.addDescription(new ItemStack(ModItems.NUCLEAR_BOOTS), "grimoire.jei.descriptions.nuclear_boots", LINE
				, "grimoire.jei.descriptions_body.nuclear_boots");

		registry.addDescription(new ItemStack(ModItems.NUCLEAR_ROD), "grimoire.jei.descriptions.nuclear_rod", LINE
				, "grimoire.jei.descriptions_body.nuclear_rod");

		registry.addDescription(new ItemStack(ModItems.ORB_ELIXIR), "grimoire.jei.descriptions.orb_elixir", LINE
				, "grimoire.jei.descriptions_body.orb_elixir");

		registry.addDescription(new ItemStack(ModItems.PATCHY_BOOK), "grimoire.jei.descriptions.patchy_book", LINE);

		registry.addDescription(new ItemStack(ModItems.POPSICLE_STICK), "grimoire.jei.descriptions.popsicle_stick", LINE);

		registry.addDescription(new ItemStack(ModItems.POUCH), "grimoire.jei.descriptions.pouch", LINE);

		registry.addDescription(new ItemStack(ModItems.RAIDEN_MASK), "grimoire.jei.descriptions.raiden_mask", LINE);

		registry.addDescription(new ItemStack(ModItems.ROD_REMORSE), "grimoire.jei.descriptions.rod_of_remorse", LINE);

		registry.addDescription(new ItemStack(ModItems.ROUKANKEN), "grimoire.jei.descriptions.roukanken", LINE
				, "grimoire.jei.descriptions_body.roukanken");

		registry.addDescription(new ItemStack(ModItems.RUMIA_SWORD), "grimoire.jei.descriptions.rumia_sword", LINE
				, "grimoire.jei.descriptions_body.rumia_sword");

		registry.addDescription(new ItemStack(ModItems.SACRED_TOYOSATOMIMI), "grimoire.jei.descriptions.sacred_toyosatomimi_sword", LINE);

		registry.addDescription(new ItemStack(ModItems.SANAE_GOHEI), "grimoire.jei.descriptions.sanae_gohei", LINE
				,"grimoire.jei.descriptions_body.sanae_gohei"
				,"grimoire.jei.descriptions_body.sanae_gohei_clear"
				,"grimoire.jei.descriptions_body.sanae_gohei_rain"
				,"grimoire.jei.descriptions_body.sanae_gohei_thunder"
				,"grimoire.jei.descriptions_body.sanae_gohei_wind"
				,"grimoire.jei.descriptions_body.sanae_gohei_moses"
				,"grimoire.jei.descriptions_body.sanae_gohei_heal"
				,"grimoire.jei.descriptions_body.sanae_gohei_potions"
				,"grimoire.jei.descriptions_body.sanae_gohei_crops"
				,"grimoire.jei.descriptions_body.sanae_gohei_time");

		registry.addDescription(new ItemStack(ModItems.SARIEL_WAND), "grimoire.jei.descriptions.sariel_wand", LINE
				, "grimoire.jei.descriptions_body.sariel_wand");

		registry.addDescription(new ItemStack(ModItems.SHICHI_SEIKEN), "grimoire.jei.descriptions.shichi_seiken", LINE
				, "grimoire.jei.descriptions_body.shichi_seiken");

		registry.addDescription(new ItemStack(ModItems.SHINMYOUMARU_HAT), "grimoire.jei.descriptions.shinmyoumaru_hat", LINE);

		registry.addDescription(new ItemStack(ModItems.SHINMYOUMARU_NEEDLE), "grimoire.jei.descriptions.shinmyoumaru_needle", LINE
				, "grimoire.jei.descriptions_body.shinmyoumaru_needle");

		registry.addDescription(new ItemStack(ModItems.SHOU_LAMP), "grimoire.jei.descriptions.shou_lamp");

		registry.addDescription(new ItemStack(ModItems.SHROOM_POWDER, 1, OreDictionary.WILDCARD_VALUE), "grimoire.jei.descriptions.shroom_powder", LINE);

		registry.addDescription(new ItemStack(ModItems.SKULL), "grimoire.jei.descriptions.skull");

		registry.addDescription(new ItemStack(ModItems.STAR), "grimoire.jei.descriptions.star", LINE);

		registry.addDescription(new ItemStack(ModItems.STOP_WATCH), "grimoire.jei.descriptions.stop_watch", LINE);

		registry.addDescription(new ItemStack(ModItems.SUBSTITUTE_JIZO), "grimoire.jei.descriptions.substitute_jizo", LINE);

		registry.addDescription(new ItemStack(ModItems.SUWAKO_HAT), "grimoire.jei.descriptions.suwako_hat", LINE
				, "grimoire.jei.descriptions_body.suwako_hat");

		registry.addDescription(new ItemStack(ModItems.SWALLOW_COWRIE_SHELL), "grimoire.jei.descriptions.swallow_cowrie_shell", LINE
				, "grimoire.jei.descriptions_body.swallow_cowrie_shell");

		registry.addDescription(new ItemStack(ModItems.SWALLOW_EGG), "grimoire.jei.descriptions.swallow_egg", LINE);

		registry.addDescription(new ItemStack(ModItems.SWORD_OF_KUSANAGI), "grimoire.jei.descriptions.sword_of_kusanagi", LINE
				, "grimoire.jei.descriptions_body.sword_of_kusanagi");

		registry.addDescription(new ItemStack(ModItems.SYRINGE), "grimoire.jei.descriptions.syringe", LINE);

		registry.addDescription(new ItemStack(ModItems.TENGU_FAN), "grimoire.jei.descriptions.tengu_fan", LINE);

		registry.addDescription(new ItemStack(ModItems.THIRD_EYE), "grimoire.jei.descriptions.third_eye", LINE
				, "grimoire.jei.descriptions_body.third_eye");

		registry.addDescription(new ItemStack(ModItems.TIME_ORB), "grimoire.jei.descriptions.time_orb", LINE);

		registry.addDescription(new ItemStack(ModItems.TOYOSATOMIMI_HAT), "grimoire.jei.descriptions.toyosatomimi_hat", LINE
				, "grimoire.jei.descriptions_body.toyosatomimi_hat");

		registry.addDescription(new ItemStack(ModItems.UBA_MASK), "grimoire.jei.descriptions.uba_mask", LINE);

		registry.addDescription(new ItemStack(ModItems.UFO_BLUE), "grimoire.jei.descriptions.ufo_blue", LINE);

		registry.addDescription(new ItemStack(ModItems.UFO_GREEN), "grimoire.jei.descriptions.ufo_green", LINE);

		registry.addDescription(new ItemStack(ModItems.UFO_RED), "grimoire.jei.descriptions.ufo_red", LINE);

		registry.addDescription(new ItemStack(ModItems.UFOS), "grimoire.jei.descriptions.ufos", LINE
				, "grimoire.jei.descriptions_body.ufos");

		registry.addDescription(new ItemStack(ModItems.UTSUHO_WINGS), "grimoire.jei.descriptions.utsuho_wings", LINE
				,"grimoire.jei.descriptions_body.utsuho_wings");

		registry.addDescription(new ItemStack(ModItems.WALL_PASSING_CHISEL), "grimoire.jei.descriptions.wall_passing_chisel", LINE);

		registry.addDescription(new ItemStack(ModItems.WATERMELON_BLADE), "grimoire.jei.descriptions.watermelon_blade", LINE);

		registry.addDescription(new ItemStack(ModItems.WATERMELON_SWORD), "grimoire.jei.descriptions.watermelon_sword", LINE);

		registry.addDescription(new ItemStack(ModItems.YOUKAI_BOOK), "grimoire.jei.descriptions.youkai_book", LINE);

		registry.addDescription(new ItemStack(ModItems.YUUGI_SAKE), "grimoire.jei.descriptions.yuugi_sake", LINE
				,"grimoire.jei.descriptions_body.yuugi_sake");

		registry.addDescription(new ItemStack(ModBlocks.IMPURE_STONE), "grimoire.jei.descriptions.impure_stone", LINE);

		registry.addDescription(new ItemStack(ModBlocks.HOLY_STONE), "grimoire.jei.descriptions.holy_stone", LINE
				, "grimoire.jei.descriptions_body.holy_stone"
				, "grimoire.jei.descriptions.holy_stone_gold_nugget"
				, "grimoire.jei.descriptions.holy_stone_gold_ingot"
				, "grimoire.jei.descriptions.holy_stone_iron_ingot"
				, "grimoire.jei.descriptions.holy_stone_blaze_powder"
				, "grimoire.jei.descriptions.holy_stone_speckled_melon"
				, "grimoire.jei.descriptions.holy_stone_diamond");

		registry.addDescription(new ItemStack(ModBlocks.ONBASHIRA), "grimoire.jei.descriptions.onbashira", LINE
				, "grimoire.jei.descriptions_body.onbashira");

		registry.addDescription(new ItemStack(ModBlocks.PILLAR_ALTAR), "grimoire.jei.descriptions.pillar", LINE);

		registry.addDescription(new ItemStack(ModBlocks.ALTAR), "grimoire.jei.descriptions.altar", LINE);
	}
}
