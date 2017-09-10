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
	@ObjectHolder(LibItemName.PASTE)
	public static final Item PASTE = PLACE_HOLDER;
	@ObjectHolder(LibItemName.SOLIDIFIED_PAPER)
	public static final Item SOLIDIFIED_PAPER = PLACE_HOLDER;
	@ObjectHolder(LibItemName.TAMAHAGANE_STEEL)
	public static final Item TAMAHAGANE_STEEL = PLACE_HOLDER;
	@ObjectHolder(LibItemName.SHIMENAWA_ROPE)
	public static final Item SHIMENAWA_ROPE = PLACE_HOLDER;
	@ObjectHolder(LibItemName.YOUKAI_BOOK)
	public static final Item YOUKAI_BOOK = PLACE_HOLDER;
	@ObjectHolder(LibItemName.MASK)
	public static final Item MASK = PLACE_HOLDER;
	@ObjectHolder(LibItemName.HIHIIROKANE)
	public static final Item HIHIIROKANE = PLACE_HOLDER;
	@ObjectHolder(LibItemName.IBARAKI_BOX_EMPTY)
	public static final Item IBARAKI_BOX_EMPTY = PLACE_HOLDER;
	@ObjectHolder(LibItemName.IMPURE_ROCK)
	public static final Item IMPURE_ROCK = PLACE_HOLDER;
	@ObjectHolder(LibItemName.LUNASA_VIOLIN)
	public static final Item LUNASA_VIOLIN = PLACE_HOLDER;
	@ObjectHolder(LibItemName.LYRICA_PIANO)
	public static final Item LYRICA_PIANO = PLACE_HOLDER;
	@ObjectHolder(LibItemName.MERLIN_TRUMPET)
	public static final Item MERLIN_TRUMPET = PLACE_HOLDER;
	@ObjectHolder(LibItemName.SHOU_LAMP)
	public static final Item SHOU_LAMP = PLACE_HOLDER;
	@ObjectHolder(LibItemName.PATCHY_BOOK)
	public static final Item PATCHY_BOOK = PLACE_HOLDER;
	@ObjectHolder(LibItemName.ORIN_SKULL)
	public static final Item ORIN_SKULL = PLACE_HOLDER;
	@ObjectHolder(LibItemName.POUCH)
	public static final Item SPELLCARD_POUCH = PLACE_HOLDER;
	@ObjectHolder(LibItemName.TENGU_FAN)
	public static final Item TENGU_FAN = PLACE_HOLDER;
	@ObjectHolder(LibItemName.NAZRIN_PENDULUM)
	public static final Item NAZRIN_PENDULUM = PLACE_HOLDER;
	@ObjectHolder(LibItemName.GHOST_DIPPER)
	public static final Item GHOST_DIPPER = PLACE_HOLDER;
	@ObjectHolder(LibItemName.WALL_PASSING_CHISEL)
	public static final Item WALL_PASSING_CHISEL = PLACE_HOLDER;
	@ObjectHolder(LibItemName.MIRACLE_MALLET)
	public static final Item MIRACLE_MALLET = PLACE_HOLDER;
	@ObjectHolder(LibItemName.ROD_REMORSE)
	public static final Item ROD_REMORSE = PLACE_HOLDER;
	@ObjectHolder(LibItemName.JEWELED_HOURAI)
	public static final Item JEWELED_HOURAI = PLACE_HOLDER;
	@ObjectHolder(LibItemName.BUDAH_BOUL)
	public static final Item BUDAH_BOWL = PLACE_HOLDER;
	@ObjectHolder(LibItemName.DRAGON_JEWEL)
	public static final Item DRAGON_JEWEL = PLACE_HOLDER;
	@ObjectHolder(LibItemName.STOPWATCH)
	public static final Item STOPWATCH = PLACE_HOLDER;
	@ObjectHolder(LibItemName.MORTAR_AND_PESTLE)
	public static final Item MORTAR_AND_PESTLE = PLACE_HOLDER;
	@ObjectHolder(LibItemName.FULL_POWER)
	public static final Item FULL_POWER = PLACE_HOLDER;
	@ObjectHolder(LibItemName.STAR)
	public static final Item STAR = PLACE_HOLDER;
	@ObjectHolder(LibItemName.CHERRY)
	public static final Item CHERRY = PLACE_HOLDER;
	@ObjectHolder(LibItemName.TIME_ORB)
	public static final Item TIME_ORB = PLACE_HOLDER;
	@ObjectHolder(LibItemName.FAITH)
	public static final Item FAITH = PLACE_HOLDER;
	@ObjectHolder(LibItemName.UFO_RED)
	public static final Item UFO_RED = PLACE_HOLDER;
	@ObjectHolder(LibItemName.UFO_BLUE)
	public static final Item UFO_BLUE = PLACE_HOLDER;
	@ObjectHolder(LibItemName.UFO_GREEN)
	public static final Item UFO_GREEN = PLACE_HOLDER;
	@ObjectHolder(LibItemName.UFO)
	public static final Item UFO = PLACE_HOLDER;
	@ObjectHolder(LibItemName.THIRD_EYE)
	public static final Item THIRD_EYE = PLACE_HOLDER;
	@ObjectHolder(LibItemName.LEAF)
	public static final Item LEAF = PLACE_HOLDER;
	@ObjectHolder(LibItemName.BLOOD_THIRSTY_ORB)
	public static final Item BLOOD_THIRSTY_ORB = PLACE_HOLDER;
	@ObjectHolder(LibItemName.FOLDING_UMBRELLA)
	public static final Item FOLDING_UMBRELLA = PLACE_HOLDER;
	@ObjectHolder(LibItemName.SUBSTITUTE_JIZO)
	public static final Item SUBSTITUTE_JIZO = PLACE_HOLDER;
	@ObjectHolder(LibItemName.NIMBLE_FABRIC)
	public static final Item NIMBLE_FABRIC = PLACE_HOLDER;
	@ObjectHolder(LibItemName.MIRACLE_MALLET)
	public static final Item FAKE_MIRACLE_MALLET = PLACE_HOLDER;
	@ObjectHolder(LibItemName.SEND_OFF_LANTERN)
	public static final Item SEND_OFF_LANTERN = PLACE_HOLDER;
	@ObjectHolder(LibItemName.CURSED_DECOY_DOLL)
	public static final Item CURSED_DECOY_DOLL = PLACE_HOLDER;
	@ObjectHolder(LibItemName.AYA_CAMERA)
	public static final Item AYA_CAMERA = PLACE_HOLDER;
	@ObjectHolder(LibItemName.HATATE_CAMERA)
	public static final Item HATATE_CAMERA = PLACE_HOLDER;
	@ObjectHolder(LibItemName.HAKUREI_GOHEI)
	public static final Item HAKUREI_GOHEI = PLACE_HOLDER;
	@ObjectHolder(LibItemName.SANAE_GOHEI)
	public static final Item SANAE_GOHEI = PLACE_HOLDER;
	@ObjectHolder(LibItemName.BLACK_FEATHER)
	public static final Item BLACK_FEATHER = PLACE_HOLDER;
	@ObjectHolder(LibItemName.NETHER_SHARD)
	public static final Item NETHER_SHARD = PLACE_HOLDER;
	@ObjectHolder(LibItemName.IRON_NUGGET)
	public static final Item IRON_NUGGET = PLACE_HOLDER;
	@ObjectHolder(LibItemName.HARDENED_LEATHER)
	public static final Item HARDENED_LEATHER = PLACE_HOLDER;
	@ObjectHolder(LibItemName.SWALLOW_EGG)
	public static final Item SWALLOW_EGG = PLACE_HOLDER;
	@ObjectHolder(LibItemName.COWRIE_SHELL)
	public static final Item COWRIE_SHELL = PLACE_HOLDER;
	@ObjectHolder(LibItemName.DRAGON_SCALE)
	public static final Item DRAGON_SCALE = PLACE_HOLDER;
	@ObjectHolder(LibItemName.CHARM_OF_HEALING)
	public static final Item CHARM_OF_HEALING = PLACE_HOLDER;
	@ObjectHolder(LibItemName.SPIRITUAL_STRIKE_TALISMAN)
	public static final Item SPIRITUAL_STRIKE_TALISMAN = PLACE_HOLDER;
	@ObjectHolder(LibItemName.GAP)
	public static final Item GAP = PLACE_HOLDER;
	@ObjectHolder(LibItemName.RED_STONE_OF_AJA)
	public static final Item RED_STONE_OF_AJA = PLACE_HOLDER;
	@ObjectHolder(LibItemName.SEAMLESS_CEILING_OF_KINKAKUJI)
	public static final Item SEAMLESS_CEILING_OF_KINKAKUJI = PLACE_HOLDER;

	@ObjectHolder(LibItemName.SHROOM_POWDER)
	public static final Item SHROOM_POWDER = PLACE_HOLDER;
	@ObjectHolder(LibItemName.GRILLED_LAMPREY)
	public static final Item GRILLED_LAMPREY = PLACE_HOLDER;
	@ObjectHolder(LibItemName.IBARAKI_BOX_FILLED)
	public static final Item IBARAKI_BOX_FILLED = PLACE_HOLDER;
	@ObjectHolder(LibItemName.KAPPAS_NOSTRUM)
	public static final Item KAPPAS_NOSTRUM = PLACE_HOLDER;
	@ObjectHolder(LibItemName.HEAVENLY_PEACH)
	public static final Item HEAVENLY_PEACH = PLACE_HOLDER;
	@ObjectHolder(LibItemName.IBUKI_GOURD)
	public static final Item IBUKI_GOURD = PLACE_HOLDER;
	@ObjectHolder(LibItemName.SWALLOW_COWRIE_SHELL)
	public static final Item SWALLOW_COWRIE_SHELL = PLACE_HOLDER;
	@ObjectHolder(LibItemName.ORB_ELIXIR)
	public static final Item ORB_ELIXIR = PLACE_HOLDER;
	@ObjectHolder(LibItemName.HOURAI_ELIXIR)
	public static final Item HOURAI_ELIXIR = PLACE_HOLDER;
	@ObjectHolder(LibItemName.YUUGI_SAKE)
	public static final Item YUUGI_SAKE = PLACE_HOLDER;

	@ObjectHolder(LibItemName.FOX_MASK)
	public static final Item FOX_MASK = PLACE_HOLDER;
	@ObjectHolder(LibItemName.RAIDEN_MASK)
	public static final Item RAIDEN_MASK = PLACE_HOLDER;
	@ObjectHolder(LibItemName.MONKEY_MASK)
	public static final Item MONKEY_MASK = PLACE_HOLDER;
	@ObjectHolder(LibItemName.HYOTTOKO_MASK)
	public static final Item HYOTTOKO_MASK = PLACE_HOLDER;
	@ObjectHolder(LibItemName.FUKU_NO_KAMI_MASK)
	public static final Item FUKU_NO_KAMI_MASK = PLACE_HOLDER;
	@ObjectHolder(LibItemName.UBA_MASK)
	public static final Item UBA_MASK = PLACE_HOLDER;
	@ObjectHolder(LibItemName.HANNYA_MASK)
	public static final Item HANNYA_MASK = PLACE_HOLDER;
	@ObjectHolder(LibItemName.KOOMOTE_MASK)
	public static final Item KOOMOTE_MASK = PLACE_HOLDER;
	@ObjectHolder(LibItemName.MASK_OF_HOPE)
	public static final Item MASK_OF_HOPE = PLACE_HOLDER;
	@ObjectHolder(LibItemName.KOKORO_MASKS)
	public static final Item KOKORO_MASKS = PLACE_HOLDER;
	@ObjectHolder(LibItemName.MAPLE_LEAF_SHIELD)
	public static final Item MAPLE_LEAF_SHIELD = PLACE_HOLDER;
	@ObjectHolder(LibItemName.TOYOSATOMIMI_HAT)
	public static final Item TOYOSATOMIMI_HAT = PLACE_HOLDER;
	@ObjectHolder(LibItemName.KANAKO_SHIMENAWA)
	public static final Item KANAKO_SHIMENAWA = PLACE_HOLDER;
	@ObjectHolder(LibItemName.ICHIRIN_UNZAN)
	public static final Item ICHIRIN_UNZAN = PLACE_HOLDER;
	@ObjectHolder(LibItemName.SUWAKO_HAT)
	public static final Item SUWAKO_HAT = PLACE_HOLDER;
	@ObjectHolder(LibItemName.FIRE_ROBE)
	public static final Item FIRE_ROBE = PLACE_HOLDER;
	@ObjectHolder(LibItemName.UTSUHO_WINGS)
	public static final Item UTSUHO_WINGS = PLACE_HOLDER;
	@ObjectHolder(LibItemName.KAPPA_HAT)
	public static final Item KAPPA_HAT = PLACE_HOLDER;
	@ObjectHolder(LibItemName.MARISA_HAT)
	public static final Item MARISA_HAT = PLACE_HOLDER;
	@ObjectHolder(LibItemName.MIKO_CLOAK)
	public static final Item MIKO_CLOAK = PLACE_HOLDER;
	@ObjectHolder(LibItemName.SHINMYOUMARU_HAT)
	public static final Item SHINMYOUMARU_HAT = PLACE_HOLDER;
	@ObjectHolder(LibItemName.TENSHI_HAT)
	public static final Item TENSHI_HAT = PLACE_HOLDER;

	@ObjectHolder(LibItemName.MOCHI_HAMMER)
	public static final Item MOCHI_HAMMER = PLACE_HOLDER;
	@ObjectHolder(LibItemName.MOMIJI_SCIMITAR_SWORD)
	public static final Item MOMIJI_SCIMITAR_SWORD = PLACE_HOLDER;
	@ObjectHolder(LibItemName.LAEVATEIN)
	public static final Item LAEVATEIN = PLACE_HOLDER;
	@ObjectHolder(LibItemName.NAZRIN_STICK)
	public static final Item NAZRIN_STICK = PLACE_HOLDER;
	@ObjectHolder(LibItemName.ELLY_SCYTHE)
	public static final Item ELLY_SCYTHE = PLACE_HOLDER;
	@ObjectHolder(LibItemName.MIKO_STICK)
	public static final Item MIKO_STICK = PLACE_HOLDER;
	@ObjectHolder(LibItemName.AMENONUHOKO)
	public static final Item AMENONUHOKO = PLACE_HOLDER;
	@ObjectHolder(LibItemName.SHINMYOUMARU_NEEDLE)
	public static final Item SHINMYOUMARU_NEEDLE = PLACE_HOLDER;
	@ObjectHolder(LibItemName.NUE_TRIDENT)
	public static final Item NUE_TRIDENT = PLACE_HOLDER;
	@ObjectHolder(LibItemName.SWORD_OF_KUSANAGI)
	public static final Item SWORD_OF_KUSANAGI = PLACE_HOLDER;
	@ObjectHolder(LibItemName.SYRINGE)
	public static final Item SYRINGE = PLACE_HOLDER;
	@ObjectHolder(LibItemName.ICHIRIN_RING)
	public static final Item ICHIRIN_RING = PLACE_HOLDER;
	@ObjectHolder(LibItemName.KANAKO_ONBASHIRA)
	public static final Item KANAKO_ONBASHIRA = PLACE_HOLDER;
	@ObjectHolder(LibItemName.SHICHI_SEIKEN)
	public static final Item SHICHI_SEIKEN = PLACE_HOLDER;
	@ObjectHolder(LibItemName.CATTAIL_PLANT)
	public static final Item CATTAIL_PLANT = PLACE_HOLDER;
	@ObjectHolder(LibItemName.POPSICLE_STICK)
	public static final Item POPSICLE_STICK = PLACE_HOLDER;
	@ObjectHolder(LibItemName.RUMIA_SWORD)
	public static final Item RUMIA_SWORD = PLACE_HOLDER;
	@ObjectHolder(LibItemName.SARIEL_WAND)
	public static final Item SARIEL_WAND = PLACE_HOLDER;
	@ObjectHolder(LibItemName.WATERMELON_BLADE)
	public static final Item WATERMELON_BLADE = PLACE_HOLDER;
	@ObjectHolder(LibItemName.WATERMELON_SWORD)
	public static final Item WATERMELON_SWORD = PLACE_HOLDER;
	@ObjectHolder(LibItemName.SACRED_SWORD_OF_TOYOSATOMIMI)
	public static final Item SACRED_SWORD_OF_TOYOSATOMIMI = PLACE_HOLDER;
	@ObjectHolder(LibItemName.HISOU_SWORD)
	public static final Item HISOU_SWORD = PLACE_HOLDER;
	@ObjectHolder(LibItemName.KOMACHI_SCYTHE)
	public static final Item KOMACHI_SCYTHE = PLACE_HOLDER;
	@ObjectHolder(LibItemName.ROUKANKEN)
	public static final Item ROUKANKEN = PLACE_HOLDER;
	@ObjectHolder(LibItemName.NUCLEAR_ROD)
	public static final Item NUCLEAR_ROD = PLACE_HOLDER;
	@ObjectHolder(LibItemName.NUCLEAR_BOOTS)
	public static final Item NUCLEAR_BOOTS = PLACE_HOLDER;
	@ObjectHolder(LibItemName.ICICLE_SWORD)
	public static final Item ICICLE_SWORD = PLACE_HOLDER;
	@ObjectHolder(LibItemName.HAKUROUKEN)
	public static final Item HAKUROUKEN = PLACE_HOLDER;
	@ObjectHolder(LibItemName.GHOST_ANCHOR)
	public static final Item GHOST_ANCHOR = PLACE_HOLDER;

	public static void register(IForgeRegistry<Item> registry) {
		registry.register(new ItemTamahaganeSteel());
		registry.register(new ItemHihiirokane());
		registry.register(new ItemShimenawaRope());
		registry.register(new ItemTimeOrb());
		registry.register(new ItemBase(LibItemName.MASK));
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
		registry.register(itemBlock(ModBlocks.HOLY_KEY_STONE));
		registry.register(itemBlock(ModBlocks.COMPACT_STONE));
		registry.register(itemBlock(ModBlocks.HOLY_STONE));
		registry.register(itemBlock(ModBlocks.KYOUMARUBOTAN));
		registry.register(new ItemBlockOnbashira(ModBlocks.ONBASHIRA));
		registry.register(itemBlock(ModBlocks.PAPER));
		registry.register(itemBlock(ModBlocks.ROPE));
		registry.register(new ItemBlockShroom(ModBlocks.SHROOM));
		registry.register(itemBlock(ModBlocks.SUGAR_BLOCK));
		registry.register(itemBlock(ModBlocks.HYPER_MAGIC));
		registry.register(itemBlock(ModBlocks.CRAFTING_ALTAR));
		registry.register(itemBlock(ModBlocks.PILLAR_ALTAR));
		registry.register(itemBlock(ModBlocks.IMPURE_STONE));
		registry.register(itemBlock(ModBlocks.HIHIIROKANE_BLOCK));
		registry.register(itemBlock(ModBlocks.DRAGON_STONE));
	}

	@SuppressWarnings("ConstantConditions")
	private static Item itemBlock(Block block) {
		return new ItemBlock(block).setRegistryName(block.getRegistryName());
	}

	public static void init() {
		OreDictionary.registerOre("nuggetIron", new ItemStack(ModItems.IRON_NUGGET));
		OreDictionary.registerOre("oreImpureStone", new ItemStack(ModBlocks.IMPURE_STONE));

		OreDictionary.registerOre("dyeBlack", new ItemStack(ModItems.SHROOM_POWDER, 1, 0));
		OreDictionary.registerOre("dyeRed", new ItemStack(ModItems.SHROOM_POWDER, 1, 1));
		OreDictionary.registerOre("dyeGreen", new ItemStack(ModItems.SHROOM_POWDER, 1, 2));
		OreDictionary.registerOre("dyeBrown", new ItemStack(ModItems.SHROOM_POWDER, 1, 3));
		OreDictionary.registerOre("dyeBlue", new ItemStack(ModItems.SHROOM_POWDER, 1, 4));
		OreDictionary.registerOre("dyePurple", new ItemStack(ModItems.SHROOM_POWDER, 1, 5));
		OreDictionary.registerOre("dyeCyan", new ItemStack(ModItems.SHROOM_POWDER, 1, 6));
		OreDictionary.registerOre("dyeLightGray", new ItemStack(ModItems.SHROOM_POWDER, 1, 7));
		OreDictionary.registerOre("dyeGray", new ItemStack(ModItems.SHROOM_POWDER, 1, 8));
		OreDictionary.registerOre("dyePink", new ItemStack(ModItems.SHROOM_POWDER, 1, 9));
		OreDictionary.registerOre("dyeLime", new ItemStack(ModItems.SHROOM_POWDER, 1, 10));
		OreDictionary.registerOre("dyeYellow", new ItemStack(ModItems.SHROOM_POWDER, 1, 11));
		OreDictionary.registerOre("dyeLightBlue", new ItemStack(ModItems.SHROOM_POWDER, 1, 12));
		OreDictionary.registerOre("dyeMagenta", new ItemStack(ModItems.SHROOM_POWDER, 1, 13));
		OreDictionary.registerOre("dyeOrange", new ItemStack(ModItems.SHROOM_POWDER, 1, 14));
		OreDictionary.registerOre("dyeWhite", new ItemStack(ModItems.SHROOM_POWDER, 1, 15));

		AliceAPI.registerFlyingItem(new ItemStack(ModItems.JEWELED_HOURAI));
		AliceAPI.registerFlyingArmor(new ItemStack(ModItems.UTSUHO_WINGS));
		AliceAPI.registerFlyingArmor(new ItemStack(ModItems.KANAKO_SHIMENAWA));
	}
}
