package arekkuusu.grimoireofalice.client.gui.jei;

import arekkuusu.grimoireofalice.api.AliceAPI;
import arekkuusu.grimoireofalice.api.recipes.AltarRecipe;
import arekkuusu.grimoireofalice.common.block.ModBlocks;
import arekkuusu.grimoireofalice.common.item.ModItems;
import arekkuusu.grimoireofalice.common.lib.LibJEI;
import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

@JEIPlugin
public class GoAJEIPlugin extends BlankModPlugin {
	private static final String LINE = "--------------------------";

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new AltarRecipeItemsCategory(registry.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void register(IModRegistry registry) {
		registry.handleRecipes(AltarRecipe.class, recipe -> new AltarRecipeItemsWrapper(recipe, registry.getJeiHelpers().getStackHelper()), LibJEI.ALTAR_CATEGORY_UID);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.crafting_altar), LibJEI.ALTAR_CATEGORY_UID);
		registry.addRecipes(AliceAPI.getAltarRecipes(), LibJEI.ALTAR_CATEGORY_UID);

		addDescriptions(registry);
	}

	private static void addDescriptions(IModRegistry registry) {
		addDescription(registry, new ItemStack(ModItems.budah_bowl), "grimoire.jei.descriptions.budah_bowl", LINE
				, "grimoire.jei.descriptions_body.budah_bowl");

		addDescription(registry, new ItemStack(ModItems.nazrin_stick), "grimoire.jei.descriptions.nazrin_stick", LINE
				, "grimoire.jei.descriptions_body.nazrin_stick");

		addDescription(registry, new ItemStack(ModItems.amenonuhoko), "grimoire.jei.descriptions.amenonuhoko", LINE
				, "grimoire.jei.descriptions_body.amenonuhoko");

		addDescription(registry, new ItemStack(ModItems.aya_camera), "grimoire.jei.descriptions.aya_camera", LINE
				, "grimoire.jei.descriptions_body.aya_camera");

		addDescription(registry, new ItemStack(ModItems.blood_thirsty_orb), "grimoire.jei.descriptions.blood_orb", LINE
				, "grimoire.jei.descriptions_body.blood_orb");

		addDescription(registry, new ItemStack(ModItems.cattail_plant), "grimoire.jei.descriptions.cattail_plant", LINE);

		addDescription(registry, new ItemStack(ModItems.charm_of_healing), "grimoire.jei.descriptions.charm_of_healing", LINE,
				"grimoire.jei.descriptions_body.charm_of_healing");

		addDescription(registry, new ItemStack(ModItems.cherry_item), "grimoire.jei.descriptions.cherry", LINE);

		addDescription(registry, new ItemStack(ModItems.cowrie_shell), "grimoire.jei.descriptions.cowrie_shell", LINE);

		addDescription(registry, new ItemStack(ModItems.cursed_decoy_doll), "grimoire.jei.descriptions.cursed_decoy_doll", LINE
				, "grimoire.jei.descriptions_body.cursed_decoy_doll");

		addDescription(registry, new ItemStack(ModItems.komachi_scythe), "grimoire.jei.descriptions.komachi_scythe", LINE
				, "grimoire.jei.descriptions_body.komachi_scythe");

		addDescription(registry, new ItemStack(ModItems.dragon_jewel), "grimoire.jei.descriptions.dragon_jewel", LINE
				, "grimoire.jei.descriptions_body.dragon_jewel");

		addDescription(registry, new ItemStack(ModItems.dragon_scale), "grimoire.jei.descriptions.dragon_scale", LINE);

		addDescription(registry, new ItemStack(ModBlocks.dragon_stone), "grimoire.jei.descriptions.dragon_stone", LINE);

		addDescription(registry, new ItemStack(ModItems.elly_scythe), "grimoire.jei.descriptions.elly_scythe", LINE
				, "grimoire.jei.descriptions_body.elly_scythe");

		addDescription(registry, new ItemStack(ModItems.faith_item), "grimoire.jei.descriptions.faith", LINE);

		addDescription(registry, new ItemStack(ModItems.fake_miracle_mallet), "grimoire.jei.descriptions.fake_miracle_mallet", LINE);

		addDescription(registry, new ItemStack(ModItems.fire_robe), "grimoire.jei.descriptions.fire_robe", LINE
				, "grimoire.jei.descriptions_body.fire_robe");

		addDescription(registry, new ItemStack(ModItems.folding_umbrella), "grimoire.jei.descriptions.gap_folding_umbrella", LINE);

		addDescription(registry, new ItemStack(ModItems.fox_mask), "grimoire.jei.descriptions.fox_mask", LINE);

		addDescription(registry, new ItemStack(ModItems.fuku_no_kami_mask), "grimoire.jei.descriptions.fuku_no_kami_mask", LINE);

		addDescription(registry, new ItemStack(ModItems.full_power_item), "grimoire.jei.descriptions.full_power", LINE);

		addDescription(registry, new ItemStack(ModItems.gap), "grimoire.jei.descriptions.gap", LINE,
				"grimoire.jei.descriptions_body.gap",
				"grimoire.jei.descriptions_color.gap");

		addDescription(registry, new ItemStack(ModItems.send_off_lantern), "grimoire.jei.descriptions.ghastly_send_off_lantern", LINE);

		addDescription(registry, new ItemStack(ModItems.ghost_anchor), "grimoire.jei.descriptions.ghost_anchor", LINE
				, "grimoire.jei.descriptions_body.ghost_anchor");

		addDescription(registry, new ItemStack(ModItems.ghost_dipper), "grimoire.jei.descriptions.ghost_dipper", LINE
				, "grimoire.jei.descriptions_body.ghost_dipper");

		addDescription(registry, new ItemStack(ModItems.grilled_lamprey), "grimoire.jei.descriptions.grilled_lamprey", LINE);

		addDescription(registry, new ItemStack(ModItems.grimoire_book), "grimoire.jei.descriptions.grimoire_book", LINE);

		addDescription(registry, new ItemStack(ModItems.hakurei_gohei), "grimoire.jei.descriptions.hakurei_gohei", LINE
				, "grimoire.jei.descriptions_body.hakurei_gohei"
				, "grimoire.jei.descriptions_passive.hakurei_gohei"
				, "grimoire.jei.descriptions_aura_manipulation.hakurei_gohei"
				, "grimoire.jei.descriptions_hakurei_yin_yang_orbs.hakurei_gohei"
				, "grimoire.jei.descriptions_exploding_barrier.hakurei_gohei"
				, "grimoire.jei.descriptions_motion_barrier.hakurei_gohei"
				, "grimoire.jei.descriptions_offensive.hakurei_gohei");

		addDescription(registry, new ItemStack(ModItems.hannya_mask), "grimoire.jei.descriptions.hannya_mask", LINE);

		addDescription(registry, new ItemStack(ModItems.hatate_camera), "grimoire.jei.descriptions.hatate_camera", LINE
				, "grimoire.jei.descriptions_body.hatate_camera");

		addDescription(registry, new ItemStack(ModItems.heavenly_peach), "grimoire.jei.descriptions.heavenly_peach", LINE);

		addDescription(registry, new ItemStack(ModItems.hihiirokane), "grimoire.jei.descriptions.hihiirokane", LINE);

		addDescription(registry, new ItemStack(ModItems.hisou_sword), "grimoire.jei.descriptions.hisou", LINE
				, "grimoire.jei.descriptions_body.hisou");

		addDescription(registry, new ItemStack(ModItems.hourai_elixir), "grimoire.jei.descriptions.hourai_elixir", LINE
				, "grimoire.jei.descriptions_body.hourai_elixir");

		addDescription(registry, new ItemStack(ModItems.hyottoko_mask), "grimoire.jei.descriptions.hyottoko_mask", LINE);

		addDescription(registry, new ItemStack(ModItems.ibaraki_box_empty), "grimoire.jei.descriptions.ibaraki_box", LINE);

		addDescription(registry, new ItemStack(ModItems.ibuki_gourd), "grimoire.jei.descriptions.ibuki_gourd", LINE);

		addDescription(registry, new ItemStack(ModItems.icicle_sword), "grimoire.jei.descriptions.icicle_sword", LINE,
				"grimoire.jei.descriptions_body.icicle_sword");

		addDescription(registry, new ItemStack(ModItems.ichirin_unzan), "grimoire.jei.descriptions.ichirin_unzan", LINE);

		addDescription(registry, new ItemStack(ModItems.ichirin_ring), "grimoire.jei.descriptions.ichirin_ring", LINE
				, "grimoire.jei.descriptions_body.ichirin_ring");

		addDescription(registry, new ItemStack(ModItems.impure_rock), "grimoire.jei.descriptions.impure_rock", LINE);

		addDescription(registry, new ItemStack(ModItems.jeweled_hourai), "grimoire.jei.descriptions.jeweled_hourai", LINE
				, "grimoire.jei.descriptions_body.jeweled_hourai");

		addDescription(registry, new ItemStack(ModItems.kanako_shimenawa), "grimoire.jei.descriptions.kanako_shimenawa", LINE
				, "grimoire.jei.descriptions_body.kanako_shimenawa");

		addDescription(registry, new ItemStack(ModItems.kanako_onbashira), "grimoire.jei.descriptions.kanako_onbashira", LINE
				, "grimoire.jei.descriptions_body.kanako_onbashira");

		addDescription(registry, new ItemStack(ModItems.kappa_hat), "grimoire.jei.descriptions.kappa_hat", LINE);

		addDescription(registry, new ItemStack(ModItems.kappas_nostrum), "grimoire.jei.descriptions.kappa_nostrum", LINE);

		addDescription(registry, new ItemStack(ModItems.kokoro_masks), "grimoire.jei.descriptions.kokoros_masks", LINE
				, "grimoire.jei.descriptions_body.kokoros_masks");

		addDescription(registry, new ItemStack(ModItems.koomote_mask), "grimoire.jei.descriptions.koomote_mask", LINE);

		addDescription(registry, new ItemStack(ModItems.laevatein), "grimoire.jei.descriptions.laevatein", LINE
				, "grimoire.jei.descriptions_body.laevatein");

		addDescription(registry, new ItemStack(ModItems.leaf_item), "grimoire.jei.descriptions.leaf", LINE);

		addDescription(registry, new ItemStack(ModItems.lunasa_violin), "grimoire.jei.descriptions.lunasa_violin", LINE);

		addDescription(registry, new ItemStack(ModItems.lyrica_piano), "grimoire.jei.descriptions.lyrica_piano", LINE);

		addDescription(registry, new ItemStack(ModItems.maple_leaf_shield), "grimoire.jei.descriptions.maple_leaf_shield", LINE);

		addDescription(registry, new ItemStack(ModItems.marisa_hat), "grimoire.jei.descriptions.marisa_hat", LINE);

		addDescription(registry, new ItemStack(ModItems.mask), "grimoire.jei.descriptions.mask", LINE);

		addDescription(registry, new ItemStack(ModItems.mask_of_hope), "grimoire.jei.descriptions.mask_of_hope", LINE);

		addDescription(registry, new ItemStack(ModItems.merlin_trumpet), "grimoire.jei.descriptions.merlin_trumpet", LINE);

		addDescription(registry, new ItemStack(ModItems.miko_cloak), "grimoire.jei.descriptions.miko_cloak", LINE
				, "grimoire.jei.descriptions_body.miko_cloak");

		addDescription(registry, new ItemStack(ModItems.miko_stick), "grimoire.jei.descriptions.miko_stick", LINE);

		addDescription(registry, new ItemStack(ModItems.miracle_mallet), "grimoire.jei.descriptions.miracle_mallet", LINE
				, "grimoire.jei.descriptions_body.miracle_mallet");

		addDescription(registry, new ItemStack(ModItems.mochi_hammer), "grimoire.jei.descriptions.mochi_hammer", LINE
				, "grimoire.jei.descriptions_body.mochi_hammer");

		addDescription(registry, new ItemStack(ModItems.momiji_scimitar_sword), "grimoire.jei.descriptions.momijis_scimitar_sword", LINE);

		addDescription(registry, new ItemStack(ModItems.monkey_mask), "grimoire.jei.descriptions.monkey_mask", LINE);

		addDescription(registry, new ItemStack(ModItems.mortar_n_pestle), "grimoire.jei.descriptions.mortal_n_pestle", LINE);

		addDescription(registry, new ItemStack(ModItems.nazrin_pendulum), "grimoire.jei.descriptions.nazrin_pendulum", LINE
				, "grimoire.jei.descriptions_body.nazrin_pendulum");

		addDescription(registry, new ItemStack(ModItems.nimble_fabric), "grimoire.jei.descriptions.nimble_fabric", LINE);

		addDescription(registry, new ItemStack(ModItems.nue_trident), "grimoire.jei.descriptions.nue_trident", LINE
				, "grimoire.jei.descriptions_body.nue_trident");

		addDescription(registry, new ItemStack(ModItems.nuclear_boots), "grimoire.jei.descriptions.nuclear_boots", LINE
				, "grimoire.jei.descriptions_body.nuclear_boots");

		addDescription(registry, new ItemStack(ModItems.nuclear_rod), "grimoire.jei.descriptions.nuclear_rod", LINE
				, "grimoire.jei.descriptions_body.nuclear_rod");

		addDescription(registry, new ItemStack(ModItems.orb_elixir), "grimoire.jei.descriptions.orb_elixir", LINE
				, "grimoire.jei.descriptions_body.orb_elixir");

		addDescription(registry, new ItemStack(ModItems.patchy_book), "grimoire.jei.descriptions.patchy_book", LINE);

		addDescription(registry, new ItemStack(ModItems.popsicle_stick), "grimoire.jei.descriptions.popsicle_stick", LINE);

		addDescription(registry, new ItemStack(ModItems.spellcard_pouch), "grimoire.jei.descriptions.pouch", LINE);

		addDescription(registry, new ItemStack(ModItems.raiden_mask), "grimoire.jei.descriptions.raiden_mask", LINE);

		addDescription(registry, new ItemStack(ModItems.red_stone_of_aja), "grimoire.jei.descriptions.red_stone_of_aja", LINE,
				"grimoire.jei.descriptions_body.red_stone_of_aja");

		addDescription(registry, new ItemStack(ModItems.rod_of_remorse), "grimoire.jei.descriptions.rod_of_remorse", LINE);

		addDescription(registry, new ItemStack(ModItems.roukanken), "grimoire.jei.descriptions.roukanken", LINE
				, "grimoire.jei.descriptions_body.roukanken");

		addDescription(registry, new ItemStack(ModItems.rumia_sword), "grimoire.jei.descriptions.rumia_sword", LINE
				, "grimoire.jei.descriptions_body.rumia_sword");

		addDescription(registry, new ItemStack(ModItems.sacred_sword_of_toyosatomimi), "grimoire.jei.descriptions.sacred_toyosatomimi_sword", LINE);

		addDescription(registry, new ItemStack(ModItems.sanae_gohei), "grimoire.jei.descriptions.sanae_gohei", LINE
				, "grimoire.jei.descriptions_body.sanae_gohei"
				, "grimoire.jei.descriptions_body.sanae_gohei_clear"
				, "grimoire.jei.descriptions_body.sanae_gohei_rain"
				, "grimoire.jei.descriptions_body.sanae_gohei_thunder"
				, "grimoire.jei.descriptions_body.sanae_gohei_wind"
				, "grimoire.jei.descriptions_body.sanae_gohei_moses"
				, "grimoire.jei.descriptions_body.sanae_gohei_heal"
				, "grimoire.jei.descriptions_body.sanae_gohei_potions"
				, "grimoire.jei.descriptions_body.sanae_gohei_crops"
				, "grimoire.jei.descriptions_body.sanae_gohei_time");

		addDescription(registry, new ItemStack(ModItems.sariel_wand), "grimoire.jei.descriptions.sariel_wand", LINE
				, "grimoire.jei.descriptions_body.sariel_wand");

		addDescription(registry, new ItemStack(ModItems.seamless_ceiling_of_kinkakuji), "grimoire.jei.descriptions.seamless_ceiling_of_kinkaku_ji", LINE
				, "grimoire.jei.descriptions_body.seamless_ceiling_of_kinkaku_ji");

		addDescription(registry, new ItemStack(ModItems.shichi_seiken), "grimoire.jei.descriptions.shichi_seiken", LINE
				, "grimoire.jei.descriptions_body.shichi_seiken");

		addDescription(registry, new ItemStack(ModItems.shinmyoumaru_hat), "grimoire.jei.descriptions.shinmyoumaru_hat", LINE);

		addDescription(registry, new ItemStack(ModItems.shinmyoumaru_needle), "grimoire.jei.descriptions.shinmyoumaru_needle", LINE
				, "grimoire.jei.descriptions_body.shinmyoumaru_needle");

		addDescription(registry, new ItemStack(ModItems.shou_lamp), "grimoire.jei.descriptions.shou_lamp");

		addDescription(registry, new ItemStack(ModItems.shroom_powder, 1, OreDictionary.WILDCARD_VALUE), "grimoire.jei.descriptions.shroom_powder", LINE);

		addDescription(registry, new ItemStack(ModItems.orin_skull), "grimoire.jei.descriptions.skull");

		addDescription(registry, new ItemStack(ModItems.star_item), "grimoire.jei.descriptions.star", LINE);

		addDescription(registry, new ItemStack(ModItems.stopwatch), "grimoire.jei.descriptions.stop_watch", LINE);

		addDescription(registry, new ItemStack(ModItems.substitute_jizo), "grimoire.jei.descriptions.substitute_jizo", LINE);

		addDescription(registry, new ItemStack(ModItems.suwako_hat), "grimoire.jei.descriptions.suwako_hat", LINE
				, "grimoire.jei.descriptions_body.suwako_hat");

		addDescription(registry, new ItemStack(ModItems.swallow_cowrie_shell), "grimoire.jei.descriptions.swallow_cowrie_shell", LINE
				, "grimoire.jei.descriptions_body.swallow_cowrie_shell");

		addDescription(registry, new ItemStack(ModItems.swallow_egg), "grimoire.jei.descriptions.swallow_egg", LINE);

		addDescription(registry, new ItemStack(ModItems.sword_of_kusanagi), "grimoire.jei.descriptions.sword_of_kusanagi", LINE
				, "grimoire.jei.descriptions_body.sword_of_kusanagi");

		addDescription(registry, new ItemStack(ModItems.syringe), "grimoire.jei.descriptions.syringe", LINE);

		addDescription(registry, new ItemStack(ModItems.tengu_fan), "grimoire.jei.descriptions.tengu_fan", LINE);

		addDescription(registry, new ItemStack(ModItems.third_eye), "grimoire.jei.descriptions.third_eye", LINE
				, "grimoire.jei.descriptions_body.third_eye");

		addDescription(registry, new ItemStack(ModItems.time_orb), "grimoire.jei.descriptions.time_orb", LINE);

		addDescription(registry, new ItemStack(ModItems.toyosatomimi_hat), "grimoire.jei.descriptions.toyosatomimi_hat", LINE
				, "grimoire.jei.descriptions_body.toyosatomimi_hat");

		addDescription(registry, new ItemStack(ModItems.uba_mask), "grimoire.jei.descriptions.uba_mask", LINE);

		addDescription(registry, new ItemStack(ModItems.ufo_blue), "grimoire.jei.descriptions.ufo_blue", LINE);

		addDescription(registry, new ItemStack(ModItems.ufo_green), "grimoire.jei.descriptions.ufo_green", LINE);

		addDescription(registry, new ItemStack(ModItems.ufo_red), "grimoire.jei.descriptions.ufo_red", LINE);

		addDescription(registry, new ItemStack(ModItems.ufo), "grimoire.jei.descriptions.ufo", LINE
				, "grimoire.jei.descriptions_body.ufo");

		addDescription(registry, new ItemStack(ModItems.utsuho_wings), "grimoire.jei.descriptions.utsuho_wings", LINE
				, "grimoire.jei.descriptions_body.utsuho_wings");

		addDescription(registry, new ItemStack(ModItems.wall_passing_chisel), "grimoire.jei.descriptions.wall_passing_chisel", LINE);

		addDescription(registry, new ItemStack(ModItems.watermelon_blade), "grimoire.jei.descriptions.watermelon_blade", LINE);

		addDescription(registry, new ItemStack(ModItems.watermelon_sword), "grimoire.jei.descriptions.watermelon_sword", LINE);

		addDescription(registry, new ItemStack(ModItems.youkai_book), "grimoire.jei.descriptions.youkai_book", LINE);

		addDescription(registry, new ItemStack(ModItems.yuugi_sake), "grimoire.jei.descriptions.yuugi_sake", LINE
				, "grimoire.jei.descriptions_body.yuugi_sake");

		addDescription(registry, new ItemStack(ModBlocks.impure_stone), "grimoire.jei.descriptions.impure_stone", LINE);

		addDescription(registry, new ItemStack(ModBlocks.holy_stone), "grimoire.jei.descriptions.holy_stone", LINE
				, "grimoire.jei.descriptions_body.holy_stone"
				, "grimoire.jei.descriptions.holy_stone_gold_nugget"
				, "grimoire.jei.descriptions.holy_stone_gold_ingot"
				, "grimoire.jei.descriptions.holy_stone_iron_ingot"
				, "grimoire.jei.descriptions.holy_stone_blaze_powder"
				, "grimoire.jei.descriptions.holy_stone_speckled_melon"
				, "grimoire.jei.descriptions.holy_stone_diamond");

		addDescription(registry, new ItemStack(ModBlocks.onbashira), "grimoire.jei.descriptions.onbashira", LINE
				, "grimoire.jei.descriptions_body.onbashira");

		addDescription(registry, new ItemStack(ModBlocks.pillar_altar), "grimoire.jei.descriptions.pillar", LINE);

		addDescription(registry, new ItemStack(ModBlocks.crafting_altar), "grimoire.jei.descriptions.altar", LINE);
	}

	private static void addDescription(IModRegistry registry, ItemStack stack, String... keys) {
		registry.addIngredientInfo(stack, ItemStack.class, keys);
	}
}
