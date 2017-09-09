/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.api.AliceAPI;
import arekkuusu.grimoireofalice.common.block.ModBlocks;
import arekkuusu.grimoireofalice.common.item.food.*;
import arekkuusu.grimoireofalice.common.item.masks.*;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.common.registry.IForgeRegistry;
import net.minecraftforge.oredict.OreDictionary;

@ObjectHolder(LibMod.MOD_ID)
public final class ModItems {

	private static final Item PLACE_HOLDER = new Item();
	//--------------------------------Items--------------------------------//
	public static final Item grimoire_book = PLACE_HOLDER;
	public static final Item paste = PLACE_HOLDER;
	public static final Item solidified_paper = PLACE_HOLDER;
	public static final Item tamahagane_steel = PLACE_HOLDER;
	public static final Item shimenawa_rope = PLACE_HOLDER;
	public static final Item youkai_book = PLACE_HOLDER;
	public static final Item mask = PLACE_HOLDER;
	public static final Item hihiirokane = PLACE_HOLDER;
	public static final Item ibaraki_box_empty = PLACE_HOLDER;
	public static final Item impure_rock = PLACE_HOLDER;
	public static final Item lunasa_violin = PLACE_HOLDER;
	public static final Item lyrica_piano = PLACE_HOLDER;
	public static final Item merlin_trumpet = PLACE_HOLDER;
	public static final Item shou_lamp = PLACE_HOLDER;
	public static final Item patchy_book = PLACE_HOLDER;
	public static final Item orin_skull = PLACE_HOLDER;
	public static final Item spellcard_pouch = PLACE_HOLDER;
	public static final Item tengu_fan = PLACE_HOLDER;
	public static final Item nazrin_pendulum = PLACE_HOLDER;
	public static final Item ghost_dipper = PLACE_HOLDER;
	public static final Item wall_passing_chisel = PLACE_HOLDER;
	public static final Item miracle_mallet = PLACE_HOLDER;
	public static final Item rod_of_remorse = PLACE_HOLDER;
	public static final Item jeweled_hourai = PLACE_HOLDER;
	public static final Item budah_bowl = PLACE_HOLDER;
	public static final Item dragon_jewel = PLACE_HOLDER;
	public static final Item stopwatch = PLACE_HOLDER;
	public static final Item mortar_n_pestle = PLACE_HOLDER;
	public static final Item full_power_item = PLACE_HOLDER;
	public static final Item star_item = PLACE_HOLDER;
	public static final Item cherry_item = PLACE_HOLDER;
	public static final Item time_orb = PLACE_HOLDER;
	public static final Item faith_item = PLACE_HOLDER;
	public static final Item ufo_red = PLACE_HOLDER;
	public static final Item ufo_blue = PLACE_HOLDER;
	public static final Item ufo_green = PLACE_HOLDER;
	public static final Item ufo = PLACE_HOLDER;
	public static final Item third_eye = PLACE_HOLDER;
	public static final Item leaf_item = PLACE_HOLDER;
	public static final Item blood_thirsty_orb = PLACE_HOLDER;
	public static final Item folding_umbrella = PLACE_HOLDER;
	public static final Item substitute_jizo = PLACE_HOLDER;
	public static final Item nimble_fabric = PLACE_HOLDER;
	public static final Item fake_miracle_mallet = PLACE_HOLDER;
	public static final Item send_off_lantern = PLACE_HOLDER;
	public static final Item cursed_decoy_doll = PLACE_HOLDER;
	public static final Item aya_camera = PLACE_HOLDER;
	public static final Item hatate_camera = PLACE_HOLDER;
	public static final Item hakurei_gohei = PLACE_HOLDER;
	public static final Item sanae_gohei = PLACE_HOLDER;
	public static final Item black_feather = PLACE_HOLDER;
	public static final Item nether_shard = PLACE_HOLDER;
	public static final Item iron_nugget = PLACE_HOLDER;
	public static final Item hardened_leather = PLACE_HOLDER;
	public static final Item swallow_egg = PLACE_HOLDER;
	public static final Item cowrie_shell = PLACE_HOLDER;
	public static final Item dragon_scale = PLACE_HOLDER;
	public static final Item charm_of_healing = PLACE_HOLDER;
	public static final Item spiritual_strike_talisman = PLACE_HOLDER;
	public static final Item gap = PLACE_HOLDER;
	public static final Item red_stone_of_aja = PLACE_HOLDER;
	public static final Item seamless_ceiling_of_kinkakuji = PLACE_HOLDER;

	public static final Item shroom_powder = PLACE_HOLDER;
	public static final Item grilled_lamprey = PLACE_HOLDER;
	public static final Item ibaraki_box_filled = PLACE_HOLDER;
	public static final Item kappas_nostrum = PLACE_HOLDER;
	public static final Item heavenly_peach = PLACE_HOLDER;
	public static final Item ibuki_gourd = PLACE_HOLDER;
	public static final Item swallow_cowrie_shell = PLACE_HOLDER;
	public static final Item orb_elixir = PLACE_HOLDER;
	public static final Item hourai_elixir = PLACE_HOLDER;
	public static final Item yuugi_sake = PLACE_HOLDER;

	public static final Item fox_mask = PLACE_HOLDER;
	public static final Item raiden_mask = PLACE_HOLDER;
	public static final Item monkey_mask = PLACE_HOLDER;
	public static final Item hyottoko_mask = PLACE_HOLDER;
	public static final Item fuku_no_kami_mask = PLACE_HOLDER;
	public static final Item uba_mask = PLACE_HOLDER;
	public static final Item hannya_mask = PLACE_HOLDER;
	public static final Item koomote_mask = PLACE_HOLDER;
	public static final Item mask_of_hope = PLACE_HOLDER;
	public static final Item kokoro_masks = PLACE_HOLDER;
	public static final Item maple_leaf_shield = PLACE_HOLDER;
	public static final Item toyosatomimi_hat = PLACE_HOLDER;
	public static final Item kanako_shimenawa = PLACE_HOLDER;
	public static final Item ichirin_unzan = PLACE_HOLDER;
	public static final Item suwako_hat = PLACE_HOLDER;
	public static final Item fire_robe = PLACE_HOLDER;
	public static final Item utsuho_wings = PLACE_HOLDER;
	public static final Item kappa_hat = PLACE_HOLDER;
	public static final Item marisa_hat = PLACE_HOLDER;
	public static final Item miko_cloak = PLACE_HOLDER;
	public static final Item shinmyoumaru_hat = PLACE_HOLDER;
	public static final Item tenshi_hat = PLACE_HOLDER;

	public static final Item mochi_hammer = PLACE_HOLDER;
	public static final Item momiji_scimitar_sword = PLACE_HOLDER;
	public static final Item laevatein = PLACE_HOLDER;
	public static final Item nazrin_stick = PLACE_HOLDER;
	public static final Item elly_scythe = PLACE_HOLDER;
	public static final Item miko_stick = PLACE_HOLDER;
	public static final Item amenonuhoko = PLACE_HOLDER;
	public static final Item shinmyoumaru_needle = PLACE_HOLDER;
	public static final Item nue_trident = PLACE_HOLDER;
	public static final Item sword_of_kusanagi = PLACE_HOLDER;
	public static final Item syringe = PLACE_HOLDER;
	public static final Item ichirin_ring = PLACE_HOLDER;
	public static final Item kanako_onbashira = PLACE_HOLDER;
	public static final Item shichi_seiken = PLACE_HOLDER;
	public static final Item cattail_plant = PLACE_HOLDER;
	public static final Item popsicle_stick = PLACE_HOLDER;
	public static final Item rumia_sword = PLACE_HOLDER;
	public static final Item sariel_wand = PLACE_HOLDER;
	public static final Item watermelon_blade = PLACE_HOLDER;
	public static final Item watermelon_sword = PLACE_HOLDER;
	public static final Item sacred_sword_of_toyosatomimi = PLACE_HOLDER;
	public static final Item hisou_sword = PLACE_HOLDER;
	public static final Item komachi_scythe = PLACE_HOLDER;
	public static final Item roukanken = PLACE_HOLDER;
	public static final Item nuclear_rod = PLACE_HOLDER;
	public static final Item nuclear_boots = PLACE_HOLDER;
	public static final Item icicle_sword = PLACE_HOLDER;
	public static final Item hakurouken = PLACE_HOLDER;
	public static final Item ghost_anchor = PLACE_HOLDER;

	public static void register(IForgeRegistry<Item> registry) {
		registry.register(new ItemTamahaganeSteel());
		registry.register(new ItemHihiirokane());
		registry.register(new ItemShimenawaRope());
		registry.register(new ItemTimeOrb());
		registry.register(new ItemBase(LibItemName.MASK));
		registry.register(new ItemGrimoireBook());
		registry.register(new ItemYoukaiBook());
		registry.register(new ItemBase(LibItemName.PASTE));
		registry.register(new ItemBase(LibItemName.SOLIDIFIED_PAPER));
		registry.register(new ItemBase(LibItemName.IMPURE_ROCK));
		registry.register(new ItemIbarakiBoxEmpty());
		registry.register(new ItemBase(LibItemName.FULL_POWER));
		registry.register(new ItemBase(LibItemName.STAR));
		registry.register(new ItemBase(LibItemName.CHERRY));
		registry.register(new ItemBase(LibItemName.FAITH));
		registry.register(new ItemBase(LibItemName.UFO_RED));
		registry.register(new ItemBase(LibItemName.UFO_BLUE));
		registry.register(new ItemBase(LibItemName.UFO_GREEN));
		registry.register(new ItemUFOs());
		registry.register(new ItemPatchyBook());
		registry.register(new ItemSkull());
		registry.register(new ItemNazrinPendulum());
		registry.register(new ItemGhostDipper());
		registry.register(new ItemWallPassingChisel());
		registry.register(new ItemRodOfRemorse());
		registry.register(new ItemBudahBoul(ModMaterials.BUDAH_BOUL));
		registry.register(new ItemDragonJewel());
		registry.register(new ItemBloodThirstyOrb());
		registry.register(new ItemGapFoldingUmbrella());
		registry.register(new ItemSubstituteJizo());
		registry.register(new ItemNimbleFabric());
		registry.register(new ItemFakeMiracleMallet());
		registry.register(new ItemGhastlySendOffLantern());
		registry.register(new ItemCursedDecoyDoll());
		registry.register(new ItemHakureiGohei());
		registry.register(new ItemSanaeGohei());
		registry.register(new ItemMortarPestle());
		registry.register(new ItemBase(LibItemName.BLACK_FEATHER));
		registry.register(new ItemBase(LibItemName.NETHER_SHARD));
		registry.register(new ItemBase(LibItemName.IRON_NUGGET));
		registry.register(new ItemBase(LibItemName.HARDENED_LEATHER));
		registry.register(new ItemBase(LibItemName.SWALLOW_EGG));
		registry.register(new ItemBase(LibItemName.COWRIE_SHELL));
		registry.register(new ItemBase(LibItemName.DRAGON_SCALE));
		registry.register(new ItemCharmofHealing());
		registry.register(new ItemSpiritualStrikeTalisman());
		registry.register(new ItemGap());
		registry.register(new ItemKinkakuJiCeiling());

		//Food
		registry.register(new ItemShroomPowder());
		registry.register(new ItemGrilledLamprey());
		registry.register(new ItemIbarakiBoxFilled());
		registry.register(new ItemKappasNostrum());
		registry.register(new ItemHeavelyPeach());
		registry.register(new ItemIbukiGourd());
		registry.register(new ItemSwallowCowrieShell());
		registry.register(new ItemUltramarineOrbElixir());
		registry.register(new ItemHouraiElixir());
		registry.register(new ItemYuugiSake());

		//Armor
		registry.register(new ItemMapleLeafShield());
		registry.register(new ItemFoxMask(ModMaterials.MASK, 3));
		registry.register(new ItemRaidenMask(ModMaterials.MASK, 3));
		registry.register(new ItemMonkeyMask(ModMaterials.MASK, 3));
		registry.register(new ItemHyottokoMask(ModMaterials.MASK, 3));
		registry.register(new ItemFukuNoKamiMask(ModMaterials.MASK, 3));
		registry.register(new ItemUbaMask(ModMaterials.MASK, 3));
		registry.register(new ItemHannyaMask(ModMaterials.MASK, 3));
		registry.register(new ItemKoomoteMask(ModMaterials.MASK, 3));
		registry.register(new ItemMaskOfHope(ModMaterials.MASK, 3));
		registry.register(new ItemKokorosMasks(ModMaterials.STRONG_HIHIIROKANE, 3));
		registry.register(new ItemToyosatomimiHat(ModMaterials.WEAK_PAPER, 3));
		registry.register(new ItemKanakoShimenawa(ModMaterials.ENSHRINED_SHIMENAWA, 3));
		registry.register(new ItemIchirinUnzan(ItemArmor.ArmorMaterial.CHAIN, 3));
		registry.register(new ItemSuwakoHat(ModMaterials.STRONG_LEATHER, 3));
		registry.register(new ItemFireRobe(ModMaterials.FIRE_LEATHER, 3));
		registry.register(new ItemUtsuhoWings(ModMaterials.FIRE_LEATHER, 5));
		registry.register(new ItemKappaHat(ModMaterials.WEAK_PAPER, 3));
		registry.register(new ItemMarisaHat(ModMaterials.STRONG_LEATHER, 3));
		registry.register(new ItemToyosatomimiCloak(ModMaterials.WEAK_PAPER, 3));
		registry.register(new ItemShinmyoumaruHat(ModMaterials.STRONG_LEATHER, 5));
		registry.register(new ItemNuclearBoots(ModMaterials.WEAK_PAPER, 5));
		registry.register(new ItemThirdEye(ItemArmor.ArmorMaterial.CHAIN, 4));
		registry.register(new ItemTenshiHat(ItemArmor.ArmorMaterial.LEATHER, 2));

		//Weapons
		registry.register(new ItemAmenonuhoko(Item.ToolMaterial.DIAMOND));
		registry.register(new ItemToyosatomimiStick(ModMaterials.WEAK_MATERIAL));
		registry.register(new ItemMochiHammer(ModMaterials.STRONG_STONE));
		registry.register(new ItemMomijisScimitarSword(ModMaterials.STRONG_IRON));
		registry.register(new ItemNazrinStick(Item.ToolMaterial.STONE, LibItemName.NAZRIN_STICK));
		registry.register(new ItemNueTrident(Item.ToolMaterial.DIAMOND));
		registry.register(new ItemSwordofKusanagi(ModMaterials.STRONG_GOLD));
		registry.register(new ItemSyringe(ModMaterials.WEAK_MATERIAL));
		registry.register(new ItemIchirinRing(ModMaterials.WEAK_MATERIAL));
		registry.register(new ItemOnbashira(Item.ToolMaterial.STONE));
		registry.register(new ItemShichiSeiken(ModMaterials.NOODLE));
		registry.register(new ItemCattailPlant(Item.ToolMaterial.WOOD));
		registry.register(new ItemPopsicleStick(Item.ToolMaterial.WOOD));
		registry.register(new ItemRumiaSword(ModMaterials.STRONG_GOLD));
		registry.register(new ItemSarielWand(ModMaterials.NOODLE));
		registry.register(new ItemWatermelonBlade(Item.ToolMaterial.STONE));
		registry.register(new ItemWatermelonSword(Item.ToolMaterial.DIAMOND));
		registry.register(new ItemSacredToyosatomimi(ModMaterials.STRONG_GOLD));
		registry.register(new ItemShinmyoumaruNeedle(Item.ToolMaterial.IRON));
		registry.register(new ItemDeathScythe(Item.ToolMaterial.DIAMOND));
		registry.register(new ItemSwordRoukanken(ModMaterials.STRONG_IRON));
		registry.register(new ItemIcicleSword(ModMaterials.STRONG_GOLD));
		registry.register(new ItemGhostAnchor(ModMaterials.STRONG_IRON));

		registry.register(new ItemStopWatch());
		registry.register(new ItemMiracleMallet());
		registry.register(new ItemViolin());
		registry.register(new ItemPiano());
		registry.register(new ItemTrumpet());
		registry.register(new ItemTenguFan());
		registry.register(new ItemLaevatein(ModMaterials.NOODLE));
		registry.register(new ItemShouLamp());
		registry.register(new ItemJeweledHourai());
		registry.register(new ItemSpellCardPouch());
		registry.register(new ItemEllyScythe(ModMaterials.NOODLE));
		registry.register(new ItemSwordOfHisou(ModMaterials.STRONG_GOLD));
		registry.register(new ItemLeaf());
		registry.register(new ItemNuclearRod());
		registry.register(new ItemAyaCamera());
		registry.register(new ItemHatateCamera());
		registry.register(new ItemHakuroukenSword(Item.ToolMaterial.WOOD));
		registry.register(new ItemRedStoneofAja());

		//Blocks
		registry.register(itemBlock(ModBlocks.holy_key_stone));
		registry.register(itemBlock(ModBlocks.compact_stone));
		registry.register(itemBlock(ModBlocks.holy_stone));
		registry.register(itemBlock(ModBlocks.kyoumarubotan));
		registry.register(new ItemBlockOnbashira(ModBlocks.onbashira));
		registry.register(itemBlock(ModBlocks.paper_block));
		registry.register(itemBlock(ModBlocks.rope_block));
		registry.register(new ItemBlockShroom(ModBlocks.shroom));
		registry.register(itemBlock(ModBlocks.sugar_block));
		registry.register(itemBlock(ModBlocks.hyper_magic));
		registry.register(itemBlock(ModBlocks.crafting_altar));
		registry.register(itemBlock(ModBlocks.pillar_altar));
		registry.register(itemBlock(ModBlocks.impure_stone));
		registry.register(itemBlock(ModBlocks.hihiirokane_block));
		registry.register(itemBlock(ModBlocks.dragon_stone));
	}

	@SuppressWarnings("ConstantConditions")
	private static Item itemBlock(Block block) {
		return new ItemBlock(block).setRegistryName(block.getRegistryName());
	}

	public static void init() {
		OreDictionary.registerOre("nuggetIron", new ItemStack(ModItems.iron_nugget));
		OreDictionary.registerOre("oreImpureStone", new ItemStack(ModBlocks.impure_stone));

		OreDictionary.registerOre("dyeBlack", new ItemStack(ModItems.shroom_powder, 1, 0));
		OreDictionary.registerOre("dyeRed", new ItemStack(ModItems.shroom_powder, 1, 1));
		OreDictionary.registerOre("dyeGreen", new ItemStack(ModItems.shroom_powder, 1, 2));
		OreDictionary.registerOre("dyeBrown", new ItemStack(ModItems.shroom_powder, 1, 3));
		OreDictionary.registerOre("dyeBlue", new ItemStack(ModItems.shroom_powder, 1, 4));
		OreDictionary.registerOre("dyePurple", new ItemStack(ModItems.shroom_powder, 1, 5));
		OreDictionary.registerOre("dyeCyan", new ItemStack(ModItems.shroom_powder, 1, 6));
		OreDictionary.registerOre("dyeLightGray", new ItemStack(ModItems.shroom_powder, 1, 7));
		OreDictionary.registerOre("dyeGray", new ItemStack(ModItems.shroom_powder, 1, 8));
		OreDictionary.registerOre("dyePink", new ItemStack(ModItems.shroom_powder, 1, 9));
		OreDictionary.registerOre("dyeLime", new ItemStack(ModItems.shroom_powder, 1, 10));
		OreDictionary.registerOre("dyeYellow", new ItemStack(ModItems.shroom_powder, 1, 11));
		OreDictionary.registerOre("dyeLightBlue", new ItemStack(ModItems.shroom_powder, 1, 12));
		OreDictionary.registerOre("dyeMagenta", new ItemStack(ModItems.shroom_powder, 1, 13));
		OreDictionary.registerOre("dyeOrange", new ItemStack(ModItems.shroom_powder, 1, 14));
		OreDictionary.registerOre("dyeWhite", new ItemStack(ModItems.shroom_powder, 1, 15));

		AliceAPI.registerFlyingItem(new ItemStack(ModItems.jeweled_hourai));
		AliceAPI.registerFlyingArmor(new ItemStack(ModItems.utsuho_wings));
		AliceAPI.registerFlyingArmor(new ItemStack(ModItems.kanako_shimenawa));
	}
}
