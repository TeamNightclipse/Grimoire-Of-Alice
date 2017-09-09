package arekkuusu.grimoireofalice.common.event;

import arekkuusu.grimoireofalice.common.block.ModBlocks;
import arekkuusu.grimoireofalice.common.item.ModItems;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLMissingMappingsEvent.MissingMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.minecraftforge.fml.common.registry.GameRegistry.Type.ITEM;

/**
 * This class was created by Arekkuusu on 08/09/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
public class Mappings {

	private static final Map<ResourceLocation, Item> ITEM_MAPPING = new HashMap<>();
	private static final Map<ResourceLocation, Block> BLOCK_MAPPING = new HashMap<>();

	public static void remap(List<MissingMapping> mappings) {
		for(MissingMapping mapping : mappings) {
			if(mapping.type == ITEM) {
				mapping.remap(ITEM_MAPPING.get(mapping.resourceLocation));
			} else {
				mapping.remap(BLOCK_MAPPING.get(mapping.resourceLocation));
			}
		}
	}

	public static void init() {
		addItem("grimoirebook", ModItems.grimoire_book);
		addItem("soldifiedpaper", ModItems.solidified_paper);
		addItem("gloriousnipponsteel", ModItems.tamahagane_steel);
		addItem("shimenawarope", ModItems.shimenawa_rope);
		addItem("youkaibook", ModItems.youkai_book);
		addItem("impurerock", ModItems.impure_rock);
		addItem("thirdeye", ModItems.third_eye);
		addItem("lunasaviolin", ModItems.lunasa_violin);
		addItem("lyricapiano", ModItems.lyrica_piano);
		addItem("merlintrumpet", ModItems.merlin_trumpet);
		addItem("shoulamp", ModItems.shou_lamp);
		addItem("patchybook", ModItems.patchy_book);
		addItem("orinskull", ModItems.orin_skull);
		addItem("tengufan", ModItems.tengu_fan);
		addItem("nazrinpendulum", ModItems.nazrin_pendulum);
		addItem("ghostdipper", ModItems.ghost_dipper);
		addItem("wallpassingchisel", ModItems.wall_passing_chisel);
		addItem("miraclemallet", ModItems.miracle_mallet);
		addItem("rodofremorse", ModItems.rod_of_remorse);
		addItem("jeweledhourai", ModItems.jeweled_hourai);
		addItem("budahboul", ModItems.budah_bowl);
		addItem("dragonjewel", ModItems.dragon_jewel);
		addItem("swallowcowrieshell", ModItems.swallow_cowrie_shell);
		addItem("foldingumbrella", ModItems.folding_umbrella);
		addItem("mortarnpestle", ModItems.mortar_n_pestle);
		addItem("fullpoweritem", ModItems.full_power_item);
		addItem("staritem", ModItems.star_item);
		addItem("cherryitem", ModItems.cherry_item);
		addItem("timeorb", ModItems.time_orb);
		addItem("faithitem", ModItems.faith_item);
		addItem("ufored", ModItems.ufo_red);
		addItem("ufoblue", ModItems.ufo_blue);
		addItem("ufogreen", ModItems.ufo_green);
		addItem("ufos", ModItems.ufo);
		addItem("spellcardpouch", ModItems.spellcard_pouch);
		addItem("leafitem", ModItems.leaf_item);
		addItem("bloodthirstyorb", ModItems.blood_thirsty_orb);
		addItem("sendofflantern", ModItems.send_off_lantern);
		addItem("substitutejizo", ModItems.substitute_jizo);
		addItem("nimblefabric", ModItems.nimble_fabric);
		addItem("fakemiraclemallet", ModItems.fake_miracle_mallet);
		addItem("decoydoll", ModItems.cursed_decoy_doll);
		addItem("ayacamera", ModItems.aya_camera);
		addItem("hatatecamera", ModItems.hatate_camera);
		addItem("sanaegohei", ModItems.sanae_gohei);
		addItem("hakureigohei", ModItems.hakurei_gohei);
		addItem("nethershard", ModItems.nether_shard);
		addItem("ironnuggetgoa", ModItems.iron_nugget);
		addItem("hardenedleathergoa", ModItems.hardened_leather);
		addItem("shroompowder", ModItems.shroom_powder);
		addItem("grilledlamprey", ModItems.grilled_lamprey);
		addItem("ibarakiboxfilled", ModItems.ibaraki_box_filled);
		addItem("ibarakiboxempty", ModItems.ibaraki_box_empty);
		addItem("kappasnostrum", ModItems.kappas_nostrum);
		addItem("heavenlypeach", ModItems.heavenly_peach);
		addItem("ibukigourd", ModItems.ibuki_gourd);
		addItem("orbelixir", ModItems.orb_elixir);
		addItem("houraielixir", ModItems.hourai_elixir);
		addItem("foxmask", ModItems.fox_mask);
		addItem("raidenmask", ModItems.raiden_mask);
		addItem("monkeymask", ModItems.monkey_mask);
		addItem("hyottokomask", ModItems.hyottoko_mask);
		addItem("fukunokamimask", ModItems.fuku_no_kami_mask);
		addItem("ubamask", ModItems.uba_mask);
		addItem("hannyamask", ModItems.hannya_mask);
		addItem("koomotemask", ModItems.koomote_mask);
		addItem("maskofhope", ModItems.mask_of_hope);
		addItem("kokorosmasks", ModItems.kokoro_masks);
		addItem("mapleleafshield", ModItems.maple_leaf_shield);
		addItem("toyosatomimihat", ModItems.toyosatomimi_hat);
		addItem("kanakoshimenawa", ModItems.kanako_shimenawa);
		addItem("ichirinunzan", ModItems.ichirin_unzan);
		addItem("suwakohat", ModItems.suwako_hat);
		addItem("firerobe", ModItems.fire_robe);
		addItem("utsuhowings", ModItems.utsuho_wings);
		addItem("kappahat", ModItems.kappa_hat);
		addItem("marisahat", ModItems.marisa_hat);
		addItem("mikocloak", ModItems.miko_cloak);
		addItem("shinmyoumaruhat", ModItems.shinmyoumaru_hat);
		addItem("tenshihat", ModItems.tenshi_hat);
		addItem("mochihammer", ModItems.mochi_hammer);
		addItem("momijisscimitarsword", ModItems.momiji_scimitar_sword);
		addItem("nazrinstick", ModItems.nazrin_stick);
		addItem("ellyscythe", ModItems.elly_scythe);
		addItem("mikostick", ModItems.miko_stick);
		addItem("shinmyoumaruneedle", ModItems.shinmyoumaru_needle);
		addItem("nuetrident", ModItems.nue_trident);
		addItem("swordofkusanagi", ModItems.sword_of_kusanagi);
		addItem("ichirinring", ModItems.ichirin_ring);
		addItem("kanakoonbashira", ModItems.kanako_onbashira);
		addItem("shichiseiken", ModItems.shichi_seiken);
		addItem("cattailplant", ModItems.cattail_plant);
		addItem("popsiclestick", ModItems.popsicle_stick);
		addItem("rumiasword", ModItems.rumia_sword);
		addItem("sarielwand", ModItems.sariel_wand);
		addItem("watermelonblade", ModItems.watermelon_blade);
		addItem("watermelonsword", ModItems.watermelon_sword);
		addItem("sacredswordoftoyosatomimi", ModItems.sacred_sword_of_toyosatomimi);
		addItem("hisou", ModItems.hisou_sword);
		addItem("deathscythe", ModItems.komachi_scythe);
		addItem("yuugisake", ModItems.yuugi_sake);
		addItem("blackfeather", ModItems.black_feather);
		addItem("nuclearrod", ModItems.nuclear_rod);
		addItem("nuclearboots", ModItems.nuclear_boots);
		addItem("swallowegg", ModItems.swallow_egg);
		addItem("cowrieshell", ModItems.cowrie_shell);
		addItem("dragonscale", ModItems.dragon_scale);
		addItem("iciclesword", ModItems.icicle_sword);
		addItem("charmofhealing", ModItems.charm_of_healing);
		addItem("ghostanchor", ModItems.ghost_anchor);
		addItem("spiritualstriketalisman", ModItems.spiritual_strike_talisman);
		addItem("seamlessceilingofkinkakuji", ModItems.seamless_ceiling_of_kinkakuji);
		addItem("redstoneofaja", ModItems.red_stone_of_aja);

		addBlock("holykeystone", ModBlocks.holy_key_stone);
		addBlock("sugarblock", ModBlocks.sugar_block);
		addBlock("ropeblock", ModBlocks.rope_block);
		addBlock("paperblock", ModBlocks.paper_block);
		addBlock("onbashira", ModBlocks.onbashira);
		addBlock("holystone", ModBlocks.holy_stone);
		addBlock("compactstone", ModBlocks.compact_stone);
		addBlock("hyperconcentratedmagic", ModBlocks.hyper_magic);
		addBlock("impurestone", ModBlocks.impure_stone);
		addBlock("hihiirokaneblock", ModBlocks.hihiirokane_block);
		addBlock("dragonstone", ModBlocks.dragon_stone);
		addBlock("craftingaltar", ModBlocks.crafting_altar);
		addBlock("pillaraltar", ModBlocks.pillar_altar);
	}

	public static void addItem(String old, Item item) {
		ITEM_MAPPING.put(new ResourceLocation(LibMod.MOD_ID, old), item);
	}

	public static void addBlock(String old, Block block) {
		BLOCK_MAPPING.put(new ResourceLocation(LibMod.MOD_ID, old), block);
		addItem(old, Item.getItemFromBlock(block));
	}
}
