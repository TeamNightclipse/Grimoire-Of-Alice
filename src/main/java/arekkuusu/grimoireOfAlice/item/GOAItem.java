/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.item;

import arekkuusu.grimoireOfAlice.item.food.ItemShroomSlice;
import arekkuusu.grimoireOfAlice.item.masks.ItemFoxMask;
import arekkuusu.grimoireOfAlice.item.masks.ItemFukuNoKamiMask;
import arekkuusu.grimoireOfAlice.item.masks.ItemHannyaMask;
import arekkuusu.grimoireOfAlice.item.masks.ItemHyottokoMask;
import arekkuusu.grimoireOfAlice.item.masks.ItemKokorosMasks;
import arekkuusu.grimoireOfAlice.item.masks.ItemKoomoteMask;
import arekkuusu.grimoireOfAlice.item.masks.ItemMaskOfHope;
import arekkuusu.grimoireOfAlice.item.masks.ItemMonkeyMask;
import arekkuusu.grimoireOfAlice.item.masks.ItemRaidenMask;
import arekkuusu.grimoireOfAlice.item.masks.ItemUbaMask;
import arekkuusu.grimoireOfAlice.lib.LibItemName;
import arekkuusu.grimoireOfAlice.lib.LibMod;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class GOAItem {

	//Material
	public static ArmorMaterial SolidPaper = EnumHelper.addArmorMaterial("SolidPaper", 1000, new int[] {3}, 30);
	public static ToolMaterial Goldyron = EnumHelper.addToolMaterial("Goldyron", 3, 2000, 15.0F, 2.0F, 30);
	public static ToolMaterial WetNoodle = EnumHelper.addToolMaterial("WetNoodle", 3, 2000, 15.0F, 0.2F, 30);
	//Item
	public static Item itemEnchantedBook;
	public static Item itemGrimoireBook;
	public static Item itemVolatileString;
	public static Item itemSoldifiedPaper;
	public static Item itemGloriousNipponSteel;
	public static Item itemShimenawaRope;
	public static Item itemYoukaiBook;
	public static Item itemMask;
	//Food
	public static Item itemShroomSlice;
	//Armor
	public static Item itemFoxMask;
	public static Item itemRaidenMask;
	public static Item itemMonkeyMask;
	public static Item itemHyottokoMask;
	public static Item itemFukuNoKamiMask;
	public static Item itemUbaMask;
	public static Item itemHannyaMask;
	public static Item itemKoomoteMask;
	public static Item itemMaskOfHope;
	public static Item itemKokorosMasks;
	public static Item itemPrimordialShield;
	//Weapons
	public static Item itemMochiHammer;
	public static Item itemMomijisScimitarSword;
	public static Item itemLaevatein;
	public static Item itemNazrinStick;
	public static Item itemEllyScythe;
	public static Item itemMikoStick;
	public static Item itemCrestOfYggdrasill;
	public static Item itemAmenonuhoko;

	public static void preInit() {
		//@formatter:off

		//Item
		itemEnchantedBook = new ItemEnchantedBook().setUnlocalizedName("EnchantedBook").setTextureName(LibMod.MODID + ":EnchantedBook");
		itemGrimoireBook = new ItemGrimoireBook().setUnlocalizedName("GrimoireBook").setTextureName(LibMod.MODID + ":GrimoireBook");
		itemVolatileString = new ItemVolatileString().setUnlocalizedName("VolatileString").setTextureName(LibMod.MODID + ":VolatileString");
		itemSoldifiedPaper = new ItemSoldifiedPaper().setUnlocalizedName("SoldifiedPaper").setTextureName(LibMod.MODID + ":SoldifiedPaper");
		itemGloriousNipponSteel = new ItemGloriousNipponSteel().setUnlocalizedName("GloriousNipponSteel").setTextureName(LibMod.MODID + ":GloriousNipponSteel");
		itemShimenawaRope = new ItemShimenawaRope().setUnlocalizedName("ShimenawaRope").setTextureName(LibMod.MODID + ":ShimenawaRope");
		itemYoukaiBook = new ItemYoukaiBook().setUnlocalizedName("YoukaiBook").setTextureName(LibMod.MODID + ":YoukaiBook");
		itemMask = new ItemMask().setUnlocalizedName("Mask").setTextureName(LibMod.MODID + ":Mask");

		//Food
		itemShroomSlice =new ItemShroomSlice(4, 1.2F, false).setAlwaysEdible().setUnlocalizedName("ShroomSlice").setTextureName(LibMod.MODID + ":ShroomSlice");

		//Armor  Dont really know the dif between 0 and proxy.addArmor
		itemFoxMask= new ItemFoxMask(SolidPaper, 0/*GrimoireOfAlice.proxy.addArmor("FoxMask")*/, 0).setUnlocalizedName("FoxMask").setTextureName(LibMod.MODID + ":FoxMask");
		itemRaidenMask= new ItemRaidenMask(SolidPaper, 0/*GrimoireOfAlice.proxy.addArmor("RaidenMask")*/, 0).setUnlocalizedName("RaidenMask").setTextureName(LibMod.MODID + ":RaidenMask");
		itemMonkeyMask= new ItemMonkeyMask(SolidPaper, 0/*GrimoireOfAlice.proxy.addArmor("MonkeyMask")*/, 0).setUnlocalizedName("MonkeyMask").setTextureName(LibMod.MODID + ":MonkeyMask");
		itemHyottokoMask= new ItemHyottokoMask(SolidPaper, 0/*GrimoireOfAlice.proxy.addArmor("HyottokoMask")*/, 0).setUnlocalizedName("HyottokoMask").setTextureName(LibMod.MODID + ":HyottokoMask");
		itemFukuNoKamiMask= new ItemFukuNoKamiMask(SolidPaper, 0/*GrimoireOfAlice.proxy.addArmor("FukuNoKamiMask")*/, 0).setUnlocalizedName("FukuNoKamiMask").setTextureName(LibMod.MODID + ":FukuNoKamiMask");
		itemUbaMask= new ItemUbaMask(SolidPaper, 0/*GrimoireOfAlice.proxy.addArmor("UbaMask")*/, 0).setUnlocalizedName("UbaMask").setTextureName(LibMod.MODID + ":UbaMask");
		itemHannyaMask= new ItemHannyaMask(SolidPaper, 0/*GrimoireOfAlice.proxy.addArmor("HannyaMask")*/, 0).setUnlocalizedName("HannyaMask").setTextureName(LibMod.MODID + ":HannyaMask");
		itemKoomoteMask= new ItemKoomoteMask(SolidPaper, 0/*GrimoireOfAlice.proxy.addArmor("KoomoteMask")*/, 0).setUnlocalizedName("KoomoteMask").setTextureName(LibMod.MODID + ":KoomoteMask");
		itemMaskOfHope= new ItemMaskOfHope(SolidPaper, 0/*GrimoireOfAlice.proxy.addArmor("MaskOfHope")*/, 0).setUnlocalizedName("MaskOfHope").setTextureName(LibMod.MODID + ":MaskOfHope");
		itemKokorosMasks= new ItemKokorosMasks(SolidPaper, 0/*GrimoireOfAlice.proxy.addArmor("KokorosMasks")*/, 0).setUnlocalizedName("KokorosMasks").setTextureName(LibMod.MODID + ":KokorosMasks");
		itemPrimordialShield= new ItemPrimordialShield(WetNoodle).setUnlocalizedName("PrimordialShield").setTextureName(LibMod.MODID + ":PrimordialShield");

		//Weapon
		itemMochiHammer= new ItemMochiHammer(Goldyron).setUnlocalizedName("MochiHammer").setTextureName(LibMod.MODID + ":MochiHammer");
		itemMomijisScimitarSword= new ItemMomijisScimitarSword(Goldyron).setUnlocalizedName("MomijisScimitarSword").setTextureName(LibMod.MODID + ":MomijisScimitarSword");
		itemLaevatein= new ItemLaevatein(WetNoodle).setUnlocalizedName("Laevatein").setTextureName(LibMod.MODID + ":Laevatein");
		itemNazrinStick= new ItemNazrinStick(WetNoodle).setUnlocalizedName("NazrinStick").setTextureName(LibMod.MODID + ":NazrinStick");
		itemEllyScythe= new ItemEllyScythe(WetNoodle).setUnlocalizedName("EllyScythe").setTextureName(LibMod.MODID + ":EllyScythe");
		itemMikoStick= new ItemMikoStick(WetNoodle).setUnlocalizedName("MikoStick").setTextureName(LibMod.MODID + ":MikoStick2");
		itemCrestOfYggdrasill= new ItemCrestOfYggdrasill(WetNoodle).setUnlocalizedName("CrestOfYggdrasill").setTextureName(LibMod.MODID + ":CrestOfYggdrasill");
		itemAmenonuhoko= new ItemAmenonuhoko(Goldyron).setUnlocalizedName("Amenonuhoko").setTextureName(LibMod.MODID + ":Amenonuhoko");
		//@formatter:on

		//Item
		GameRegistry.registerItem(itemEnchantedBook, LibItemName.ENCHANTEDBOOK);
		GameRegistry.registerItem(itemGrimoireBook, LibItemName.GRIMOIREBOOK);
		GameRegistry.registerItem(itemVolatileString, LibItemName.VOLATILESTRING);
		GameRegistry.registerItem(itemSoldifiedPaper, LibItemName.SOLDIFIEDPAPER);
		GameRegistry.registerItem(itemGloriousNipponSteel, LibItemName.GLORIOUSNIPPONSTEEL);
		GameRegistry.registerItem(itemShimenawaRope, LibItemName.SHIMENAWAROPE);
		GameRegistry.registerItem(itemYoukaiBook, LibItemName.YOUKAIBOOK);
		GameRegistry.registerItem(itemMask, LibItemName.MASK);

		//Food
		GameRegistry.registerItem(itemShroomSlice, LibItemName.SHROOMSLICE);

		//Armor
		GameRegistry.registerItem(itemFoxMask, LibItemName.FOXMASK);
		GameRegistry.registerItem(itemRaidenMask, LibItemName.RAIDENMASK);
		GameRegistry.registerItem(itemMonkeyMask, LibItemName.MONKEYMASK);
		GameRegistry.registerItem(itemHyottokoMask, LibItemName.HYOTTOKOMASK);
		GameRegistry.registerItem(itemFukuNoKamiMask, LibItemName.FUKUNOKAMIMASK);
		GameRegistry.registerItem(itemUbaMask, LibItemName.UBAMASK);
		GameRegistry.registerItem(itemHannyaMask, LibItemName.HANNYAMASK);
		GameRegistry.registerItem(itemKoomoteMask, LibItemName.KOOMOTEMASK);
		GameRegistry.registerItem(itemMaskOfHope, LibItemName.MASKOFHOPE);
		GameRegistry.registerItem(itemKokorosMasks, LibItemName.KOKOROSMASKS);
		GameRegistry.registerItem(itemPrimordialShield, LibItemName.PRIMORDIALSHIELD);

		//Weapon
		GameRegistry.registerItem(itemMochiHammer, LibItemName.MOCHIHAMMER);
		GameRegistry.registerItem(itemMomijisScimitarSword, LibItemName.MOMIJISSCIMITARSWORD);
		GameRegistry.registerItem(itemLaevatein, LibItemName.LAEVATEIN);
		GameRegistry.registerItem(itemNazrinStick, LibItemName.NAZRINSTICK);
		GameRegistry.registerItem(itemEllyScythe, LibItemName.ELLYSCYTHE);
		GameRegistry.registerItem(itemMikoStick, LibItemName.MIKOSTICK);
		GameRegistry.registerItem(itemCrestOfYggdrasill, LibItemName.CRESTOFYGGDRASILL);
		GameRegistry.registerItem(itemAmenonuhoko, LibItemName.AMENONUHOKO);
	}
}
