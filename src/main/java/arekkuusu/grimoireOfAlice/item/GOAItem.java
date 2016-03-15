/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.item;

import arekkuusu.grimoireOfAlice.GrimoireOfAlice;
import arekkuusu.grimoireOfAlice.lib.LibItemName;
import arekkuusu.grimoireOfAlice.lib.LibMod;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class GOAItem {

	//Material
	public static ArmorMaterial SoldifiedPaper = EnumHelper.addArmorMaterial("SolidPaper", 99, new int[]{3}, 30);
	public static ToolMaterial GoldenIron = EnumHelper.addToolMaterial("GoldenIron", 3, 2000, 15.0F, 8F, 30);
	//Item
	public static Item itemEnchantedBook;
	public static Item itemGrimoireBook;
	public static Item itemVolatileString;
	public static Item itemShimenawaRope;
	public static Item itemThrowingExplosiveDoll;
	public static Item itemThrowingNeedleDoll;
	public static Item itemAlicesDoll;
	public static Item itemYoukaiBook;
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
	//Weapons
	public static Item itemMochiHammer;
	public static Item itemMomijisScimitarSword;
	
	public static void preInit() {
		//@formatter:off
		//Item
		itemEnchantedBook = new ItemEnchantedBook().setUnlocalizedName("EnchantedBook").setTextureName(LibMod.MODID + ":EnchantedBook");
		itemGrimoireBook = new ItemGrimoireBook().setUnlocalizedName("GrimoireBook").setTextureName(LibMod.MODID + ":GrimoireBook");
		itemVolatileString = new ItemVolatileString().setUnlocalizedName("VolatileString").setTextureName(LibMod.MODID + ":VolatileString");
		itemShimenawaRope = new ItemShimenawaRope().setUnlocalizedName("ShimenawaRope").setTextureName(LibMod.MODID + ":ShimenawaRope");
		itemThrowingExplosiveDoll = new ItemThrowingExplosiveDoll().setUnlocalizedName("ThrowingExplosiveDoll").setTextureName(LibMod.MODID + ":ThrowingExplosiveDoll");
		itemThrowingNeedleDoll = new ItemThrowingNeedleDoll().setUnlocalizedName("ThrowingNeedleDoll").setTextureName(LibMod.MODID + ":ThrowingNeedleDoll");
		itemAlicesDoll = new ItemAlicesDoll().setUnlocalizedName("AlicesDoll").setTextureName(LibMod.MODID + ":AlicesDoll");
		itemYoukaiBook = new ItemYoukaiBook().setUnlocalizedName("YoukaiBook").setTextureName(LibMod.MODID + ":YoukaiBook");
		//Armor
		itemFoxMask= new ItemFoxMask(SoldifiedPaper, GrimoireOfAlice.proxy.addArmor("FoxMask"), 0).setUnlocalizedName("FoxMask").setTextureName(LibMod.MODID + ":FoxMask");
		itemRaidenMask= new ItemRaidenMask(SoldifiedPaper, GrimoireOfAlice.proxy.addArmor("RaidenMask"), 0).setUnlocalizedName("RaidenMask").setTextureName(LibMod.MODID + ":RaidenMask");
		itemMonkeyMask= new ItemMonkeyMask(SoldifiedPaper, GrimoireOfAlice.proxy.addArmor("MonkeyMask"), 0).setUnlocalizedName("MonkeyMask").setTextureName(LibMod.MODID + ":MonkeyMask");
		itemHyottokoMask= new ItemHyottokoMask(SoldifiedPaper, GrimoireOfAlice.proxy.addArmor("HyottokoMask"), 0).setUnlocalizedName("HyottokoMask").setTextureName(LibMod.MODID + ":HyottokoMask");
		itemFukuNoKamiMask= new ItemFukuNoKamiMask(SoldifiedPaper, GrimoireOfAlice.proxy.addArmor("FukuNoKamiMask"), 0).setUnlocalizedName("FukuNoKamiMask").setTextureName(LibMod.MODID + ":FukuNoKamiMask");
		itemUbaMask= new ItemUbaMask(SoldifiedPaper, GrimoireOfAlice.proxy.addArmor("UbaMask"), 0).setUnlocalizedName("UbaMask").setTextureName(LibMod.MODID + ":UbaMask");
		itemHannyaMask= new ItemHannyaMask(SoldifiedPaper, GrimoireOfAlice.proxy.addArmor("HannyaMask"), 0).setUnlocalizedName("HannyaMask").setTextureName(LibMod.MODID + ":HannyaMask");
		itemKoomoteMask= new ItemKoomoteMask(SoldifiedPaper, GrimoireOfAlice.proxy.addArmor("KoomoteMask"), 0).setUnlocalizedName("KoomoteMask").setTextureName(LibMod.MODID + ":KoomoteMask");
		itemMaskOfHope= new ItemMaskOfHope(SoldifiedPaper, GrimoireOfAlice.proxy.addArmor("MaskOfHope"), 0).setUnlocalizedName("MaskOfHope").setTextureName(LibMod.MODID + ":MaskOfHope");
		itemKokorosMasks= new ItemKokorosMasks().setUnlocalizedName("KokorosMasks").setTextureName("KokorosMasks");
		//Weapon
		itemMochiHammer= new ItemMochiHammer(GoldenIron).setUnlocalizedName("MochiHammer").setTextureName(LibMod.MODID + ":MochiHammer");
		itemMomijisScimitarSword= new ItemMomijisScimitarSword(GoldenIron).setUnlocalizedName("MomijisScimitarSword").setTextureName(LibMod.MODID + ":MomijisScimitarSword");
		//@formatter:on

		//Item
		GameRegistry.registerItem(itemEnchantedBook, LibItemName.ENCHANTEDBOOK);
		GameRegistry.registerItem(itemGrimoireBook, LibItemName.GRIMOIREBOOK);
		GameRegistry.registerItem(itemVolatileString, LibItemName.VOLATILESTRING);
		GameRegistry.registerItem(itemShimenawaRope, LibItemName.SHIMENAWAROPE);
		GameRegistry.registerItem(itemThrowingExplosiveDoll, LibItemName.EXPLOSIVEDOLL);
		GameRegistry.registerItem(itemThrowingNeedleDoll, LibItemName.NEEDLEDOLL);
		GameRegistry.registerItem(itemAlicesDoll, LibItemName.ALICESDOLL);
		GameRegistry.registerItem(itemYoukaiBook, LibItemName.YOUKAIBOOK);
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
		//Weapon
		GameRegistry.registerItem(itemMochiHammer, LibItemName.MOCHIHAMMER);
		GameRegistry.registerItem(itemMomijisScimitarSword, LibItemName.MOMIJISSCIMITARSWORD);
	}
}
