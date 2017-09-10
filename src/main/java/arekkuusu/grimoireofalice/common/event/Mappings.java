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
		addItem("soldifiedpaper", ModItems.SOLIDIFIED_PAPER);
		addItem("gloriousnipponsteel", ModItems.TAMAHAGANE_STEEL);
		addItem("shimenawarope", ModItems.SHIMENAWA_ROPE);
		addItem("youkaibook", ModItems.YOUKAI_BOOK);
		addItem("impurerock", ModItems.IMPURE_ROCK);
		addItem("thirdeye", ModItems.THIRD_EYE);
		addItem("lunasaviolin", ModItems.LUNASA_VIOLIN);
		addItem("lyricapiano", ModItems.LYRICA_PIANO);
		addItem("merlintrumpet", ModItems.MERLIN_TRUMPET);
		addItem("shoulamp", ModItems.SHOU_LAMP);
		addItem("patchybook", ModItems.PATCHY_BOOK);
		addItem("orinskull", ModItems.ORIN_SKULL);
		addItem("tengufan", ModItems.TENGU_FAN);
		addItem("nazrinpendulum", ModItems.NAZRIN_PENDULUM);
		addItem("ghostdipper", ModItems.GHOST_DIPPER);
		addItem("wallpassingchisel", ModItems.WALL_PASSING_CHISEL);
		addItem("miraclemallet", ModItems.MIRACLE_MALLET);
		addItem("rodofremorse", ModItems.ROD_REMORSE);
		addItem("jeweledhourai", ModItems.JEWELED_HOURAI);
		addItem("budahboul", ModItems.BUDAH_BOWL);
		addItem("dragonjewel", ModItems.DRAGON_JEWEL);
		addItem("swallowcowrieshell", ModItems.SWALLOW_COWRIE_SHELL);
		addItem("foldingumbrella", ModItems.FOLDING_UMBRELLA);
		addItem("mortarnpestle", ModItems.MORTAR_AND_PESTLE);
		addItem("fullpoweritem", ModItems.FULL_POWER);
		addItem("staritem", ModItems.STAR);
		addItem("cherryitem", ModItems.CHERRY);
		addItem("timeorb", ModItems.TIME_ORB);
		addItem("faithitem", ModItems.FAITH);
		addItem("ufored", ModItems.UFO_RED);
		addItem("ufoblue", ModItems.UFO_BLUE);
		addItem("ufogreen", ModItems.UFO_GREEN);
		addItem("ufos", ModItems.UFO);
		addItem("spellcardpouch", ModItems.SPELLCARD_POUCH);
		addItem("leafitem", ModItems.LEAF);
		addItem("bloodthirstyorb", ModItems.BLOOD_THIRSTY_ORB);
		addItem("sendofflantern", ModItems.SEND_OFF_LANTERN);
		addItem("substitutejizo", ModItems.SUBSTITUTE_JIZO);
		addItem("nimblefabric", ModItems.NIMBLE_FABRIC);
		addItem("fakemiraclemallet", ModItems.FAKE_MIRACLE_MALLET);
		addItem("decoydoll", ModItems.CURSED_DECOY_DOLL);
		addItem("ayacamera", ModItems.AYA_CAMERA);
		addItem("hatatecamera", ModItems.HATATE_CAMERA);
		addItem("sanaegohei", ModItems.SANAE_GOHEI);
		addItem("hakureigohei", ModItems.HAKUREI_GOHEI);
		addItem("nethershard", ModItems.NETHER_SHARD);
		addItem("ironnuggetgoa", ModItems.IRON_NUGGET);
		addItem("hardenedleathergoa", ModItems.HARDENED_LEATHER);
		addItem("shroompowder", ModItems.SHROOM_POWDER);
		addItem("grilledlamprey", ModItems.GRILLED_LAMPREY);
		addItem("ibarakiboxfilled", ModItems.IBARAKI_BOX_FILLED);
		addItem("ibarakiboxempty", ModItems.IBARAKI_BOX_EMPTY);
		addItem("kappasnostrum", ModItems.KAPPAS_NOSTRUM);
		addItem("heavenlypeach", ModItems.HEAVENLY_PEACH);
		addItem("ibukigourd", ModItems.IBUKI_GOURD);
		addItem("orbelixir", ModItems.ORB_ELIXIR);
		addItem("houraielixir", ModItems.HOURAI_ELIXIR);
		addItem("foxmask", ModItems.FOX_MASK);
		addItem("raidenmask", ModItems.RAIDEN_MASK);
		addItem("monkeymask", ModItems.MONKEY_MASK);
		addItem("hyottokomask", ModItems.HYOTTOKO_MASK);
		addItem("fukunokamimask", ModItems.FUKU_NO_KAMI_MASK);
		addItem("ubamask", ModItems.UBA_MASK);
		addItem("hannyamask", ModItems.HANNYA_MASK);
		addItem("koomotemask", ModItems.KOOMOTE_MASK);
		addItem("maskofhope", ModItems.MASK_OF_HOPE);
		addItem("kokorosmasks", ModItems.KOKORO_MASKS);
		addItem("mapleleafshield", ModItems.MAPLE_LEAF_SHIELD);
		addItem("toyosatomimihat", ModItems.TOYOSATOMIMI_HAT);
		addItem("kanakoshimenawa", ModItems.KANAKO_SHIMENAWA);
		addItem("ichirinunzan", ModItems.ICHIRIN_UNZAN);
		addItem("suwakohat", ModItems.SUWAKO_HAT);
		addItem("firerobe", ModItems.FIRE_ROBE);
		addItem("utsuhowings", ModItems.UTSUHO_WINGS);
		addItem("kappahat", ModItems.KAPPA_HAT);
		addItem("marisahat", ModItems.MARISA_HAT);
		addItem("mikocloak", ModItems.MIKO_CLOAK);
		addItem("shinmyoumaruhat", ModItems.SHINMYOUMARU_HAT);
		addItem("tenshihat", ModItems.TENSHI_HAT);
		addItem("mochihammer", ModItems.MOCHI_HAMMER);
		addItem("momijisscimitarsword", ModItems.MOMIJI_SCIMITAR_SWORD);
		addItem("nazrinstick", ModItems.NAZRIN_STICK);
		addItem("ellyscythe", ModItems.ELLY_SCYTHE);
		addItem("mikostick", ModItems.MIKO_STICK);
		addItem("shinmyoumaruneedle", ModItems.SHINMYOUMARU_NEEDLE);
		addItem("nuetrident", ModItems.NUE_TRIDENT);
		addItem("swordofkusanagi", ModItems.SWORD_OF_KUSANAGI);
		addItem("ichirinring", ModItems.ICHIRIN_RING);
		addItem("kanakoonbashira", ModItems.KANAKO_ONBASHIRA);
		addItem("shichiseiken", ModItems.SHICHI_SEIKEN);
		addItem("cattailplant", ModItems.CATTAIL_PLANT);
		addItem("popsiclestick", ModItems.POPSICLE_STICK);
		addItem("rumiasword", ModItems.RUMIA_SWORD);
		addItem("sarielwand", ModItems.SARIEL_WAND);
		addItem("watermelonblade", ModItems.WATERMELON_BLADE);
		addItem("watermelonsword", ModItems.WATERMELON_SWORD);
		addItem("sacredswordoftoyosatomimi", ModItems.SACRED_SWORD_OF_TOYOSATOMIMI);
		addItem("hisou", ModItems.HISOU_SWORD);
		addItem("deathscythe", ModItems.KOMACHI_SCYTHE);
		addItem("yuugisake", ModItems.YUUGI_SAKE);
		addItem("blackfeather", ModItems.BLACK_FEATHER);
		addItem("nuclearrod", ModItems.NUCLEAR_ROD);
		addItem("nuclearboots", ModItems.NUCLEAR_BOOTS);
		addItem("swallowegg", ModItems.SWALLOW_EGG);
		addItem("cowrieshell", ModItems.COWRIE_SHELL);
		addItem("dragonscale", ModItems.DRAGON_SCALE);
		addItem("iciclesword", ModItems.ICICLE_SWORD);
		addItem("charmofhealing", ModItems.CHARM_OF_HEALING);
		addItem("ghostanchor", ModItems.GHOST_ANCHOR);
		addItem("spiritualstriketalisman", ModItems.SPIRITUAL_STRIKE_TALISMAN);
		addItem("seamlessceilingofkinkakuji", ModItems.SEAMLESS_CEILING_OF_KINKAKUJI);
		addItem("redstoneofaja", ModItems.RED_STONE_OF_AJA);

		addBlock("holykeystone", ModBlocks.HOLY_KEY_STONE);
		addBlock("sugarblock", ModBlocks.SUGAR_BLOCK);
		addBlock("ropeblock", ModBlocks.ROPE);
		addBlock("paperblock", ModBlocks.PAPER);
		addBlock("onbashira", ModBlocks.ONBASHIRA);
		addBlock("holystone", ModBlocks.HOLY_STONE);
		addBlock("compactstone", ModBlocks.COMPACT_STONE);
		addBlock("hyperconcentratedmagic", ModBlocks.HYPER_MAGIC);
		addBlock("impurestone", ModBlocks.IMPURE_STONE);
		addBlock("hihiirokaneblock", ModBlocks.HIHIIROKANE_BLOCK);
		addBlock("dragonstone", ModBlocks.DRAGON_STONE);
		addBlock("craftingaltar", ModBlocks.CRAFTING_ALTAR);
		addBlock("pillaraltar", ModBlocks.PILLAR_ALTAR);
	}

	public static void addItem(String old, Item item) {
		ITEM_MAPPING.put(new ResourceLocation(LibMod.MOD_ID, old), item);
	}

	public static void addBlock(String old, Block block) {
		BLOCK_MAPPING.put(new ResourceLocation(LibMod.MOD_ID, old), block);
		addItem(old, Item.getItemFromBlock(block));
	}
}
