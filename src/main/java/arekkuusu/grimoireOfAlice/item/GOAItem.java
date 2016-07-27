/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.item;

import arekkuusu.grimoireOfAlice.item.food.ItemGrilledLamprey;
import arekkuusu.grimoireOfAlice.item.food.ItemShroomSlice;
import arekkuusu.grimoireOfAlice.item.food.ItemIbarakiBoxFilled;
import arekkuusu.grimoireOfAlice.item.food.ItemkappasNostrum;
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
import arekkuusu.grimoireOfAlice.tmp.CleanupDone;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

@CleanupDone
public class GOAItem {

	//Material
	public static final ArmorMaterial SOLID_PAPER = EnumHelper.addArmorMaterial("solidPaper", 1000, new int[] {3}, 30);
	public static final ToolMaterial GOLDYRON = EnumHelper.addToolMaterial("goldyron", 3, 1000, 15.0F, 3F, 30);
	public static final ToolMaterial WET_NOODLE = EnumHelper.addToolMaterial("wetNoodle", 3, 1000, 15.0F, 0F, 30);
	public static final ToolMaterial NOT_A_MELEE_WEAPON = EnumHelper.addToolMaterial("weakMaterial", 3, 10, 15.0F, -2F, 30);
	//Item
	public static Item enchantedBook;
	public static Item grimoireBook;
	public static Item volatileString;
	public static Item soldifiedPaper;
	public static Item gloriousNipponSteel;
	public static Item shimenawaRope;
	public static Item youkaiBook;
	public static Item mask;
	public static Item Hihiirokane;
	public static Item ibarakiBoxEmpty;
	public static Item impureRock;
	//Food
	public static Item shroomSlice;
	public static Item grilledLamprey;
	public static Item ibarakiBoxFilled;
	public static Item kappasNostrum;
	//Armor
	public static Item foxMask;
	public static Item raidenMask;
	public static Item nonkeyMask;
	public static Item hyottokoMask;
	public static Item fukuNoKamiMask;
	public static Item ubaMask;
	public static Item hannyaMask;
	public static Item koomoteMask;
	public static Item maskOfHope;
	public static Item kokorosMasks;
	public static Item primordialShield;
	public static Item momijisMapleLeafShield;
	//Weapons
	public static Item mochiHammer;
	public static Item momijisScimitarSword;
	public static Item laevatein;
	public static Item nazrinStick;
	public static Item ellyScythe;
	public static Item mikoStick;
	public static Item crestOfYggdrasill;
	public static Item amenonuhoko;
	public static Item needle;
	public static Item NueTrident;
	public static Item swordOfKusanagi;
	
	//PointItem
	public static Item FullPower;
	public static Item Star;
	public static Item Cherry;
	public static Item TimeOrb;
	public static Item Faith;
	public static Item UFORed;
	public static Item UFOBlue;
	public static Item UFOGreen;
	public static Item UFOs;

	public static void preInit() {
		//@formatter:off

		//Item
		enchantedBook = new ItemEnchantedBook().setUnlocalizedName("EnchantedBook").setTextureName(LibMod.MODID + ":EnchantedBook");
		grimoireBook = new ItemGOABase(EnumRarity.epic, true).setUnlocalizedName("GrimoireBook").setTextureName(LibMod.MODID + ":GrimoireBook").setMaxStackSize(1);
		volatileString = new ItemGOABase(EnumRarity.uncommon).setUnlocalizedName("VolatileString").setTextureName(LibMod.MODID + ":VolatileString");
		soldifiedPaper = new ItemGOABase().setUnlocalizedName("SoldifiedPaper").setTextureName(LibMod.MODID + ":SoldifiedPaper").setMaxStackSize(16);
		gloriousNipponSteel = new ItemGloriousNipponSteel().setUnlocalizedName("GloriousNipponSteel").setTextureName(LibMod.MODID + ":GloriousNipponSteel");
		shimenawaRope = new ItemShimenawaRope().setUnlocalizedName("ShimenawaRope").setTextureName(LibMod.MODID + ":ShimenawaRope");
		youkaiBook = new ItemYoukaiBook().setUnlocalizedName("YoukaiBook").setTextureName(LibMod.MODID + ":YoukaiBook");
		mask = new ItemGOABase().setUnlocalizedName("Mask").setTextureName(LibMod.MODID + ":Mask").setMaxStackSize(16);
		Hihiirokane = new ItemHihiirokane().setUnlocalizedName("Hihiirokane").setTextureName(LibMod.MODID + ":Hihiirokane");
		ibarakiBoxEmpty = new ItemIbarakiBoxEmpty(Blocks.air).setUnlocalizedName("IbarakiBox").setMaxStackSize(16).setTextureName(LibMod.MODID + ":IbarakiBoxEmpty");
		impureRock = new ItemGOABase().setUnlocalizedName("ImpureRock").setTextureName(LibMod.MODID + ":ImpureRock");
		
		//Food
		shroomSlice =new ItemShroomSlice().setAlwaysEdible().setUnlocalizedName("ShroomSlice").setTextureName(LibMod.MODID + ":ShroomSlice");
		grilledLamprey =new ItemGrilledLamprey().setAlwaysEdible().setUnlocalizedName("GrilledLamprey").setTextureName(LibMod.MODID + ":GrilledLamprey");
		ibarakiBoxFilled =new ItemIbarakiBoxFilled().setAlwaysEdible().setUnlocalizedName("IbarakiBox").setTextureName(LibMod.MODID + ":IbarakiBox");
		kappasNostrum =new ItemkappasNostrum().setAlwaysEdible().setUnlocalizedName("KappasNostrum").setTextureName(LibMod.MODID + ":KappasNostrum");
		ibarakiBoxFilled =new ItemIbarakiBoxFilled().setAlwaysEdible().setUnlocalizedName("IbarakiBox").setTextureName(LibMod.MODID + ":IbarakiBoxFull");
		
		//Armor
		foxMask= new ItemFoxMask(SOLID_PAPER, 0).setUnlocalizedName("FoxMask").setTextureName(LibMod.MODID + ":FoxMask");
		raidenMask= new ItemRaidenMask(SOLID_PAPER, 0).setUnlocalizedName("RaidenMask").setTextureName(LibMod.MODID + ":RaidenMask");
		nonkeyMask= new ItemMonkeyMask(SOLID_PAPER, 0).setUnlocalizedName("MonkeyMask").setTextureName(LibMod.MODID + ":MonkeyMask");
		hyottokoMask= new ItemHyottokoMask(SOLID_PAPER, 0).setUnlocalizedName("HyottokoMask").setTextureName(LibMod.MODID + ":HyottokoMask");
		fukuNoKamiMask= new ItemFukuNoKamiMask(SOLID_PAPER, 0).setUnlocalizedName("FukuNoKamiMask").setTextureName(LibMod.MODID + ":FukuNoKamiMask");
		ubaMask= new ItemUbaMask(SOLID_PAPER, 0).setUnlocalizedName("UbaMask").setTextureName(LibMod.MODID + ":UbaMask");
		hannyaMask= new ItemHannyaMask(SOLID_PAPER, 0).setUnlocalizedName("HannyaMask").setTextureName(LibMod.MODID + ":HannyaMask");
		koomoteMask= new ItemKoomoteMask(SOLID_PAPER, 0).setUnlocalizedName("KoomoteMask").setTextureName(LibMod.MODID + ":KoomoteMask");
		maskOfHope= new ItemMaskOfHope(SOLID_PAPER, 0).setUnlocalizedName("MaskOfHope").setTextureName(LibMod.MODID + ":MaskOfHope");
		kokorosMasks= new ItemKokorosMasks(SOLID_PAPER, 0).setUnlocalizedName("KokorosMasks").setTextureName(LibMod.MODID + ":KokorosMasks");
		primordialShield= new ItemPrimordialShield(WET_NOODLE).setUnlocalizedName("PrimordialShield").setTextureName(LibMod.MODID + ":PrimordialShield");
		momijisMapleLeafShield= new ItemMapleLeafShield(WET_NOODLE).setUnlocalizedName("MapleLeafShield").setTextureName(LibMod.MODID + ":MapleLeafShield");

		//Weapon
		mochiHammer= new ItemMochiHammer(GOLDYRON).setUnlocalizedName("MochiHammer").setTextureName(LibMod.MODID + ":MochiHammer");
		momijisScimitarSword= new ItemMomijisScimitarSword(GOLDYRON).setUnlocalizedName("MomijisScimitarSword").setTextureName(LibMod.MODID + ":MomijisScimitarSword");
		laevatein= new ItemLaevatein(WET_NOODLE).setUnlocalizedName("Laevatein").setTextureName(LibMod.MODID + ":Laevatein");
		nazrinStick= new ItemNazrinStick(WET_NOODLE).setUnlocalizedName("NazrinStick").setTextureName(LibMod.MODID + ":NazrinStick");
		ellyScythe= new ItemEllyScythe(WET_NOODLE).setUnlocalizedName("EllyScythe").setTextureName(LibMod.MODID + ":EllyScythe");
		mikoStick= new ItemMikoStick(WET_NOODLE).setUnlocalizedName("MikoStick").setTextureName(LibMod.MODID + ":MikoStick");
		crestOfYggdrasill= new ItemCrestOfYggdrasill(WET_NOODLE).setUnlocalizedName("CrestOfYggdrasill").setTextureName(LibMod.MODID + ":CrestOfYggdrasill");
		amenonuhoko= new ItemAmenonuhoko(WET_NOODLE).setUnlocalizedName("Amenonuhoko").setTextureName(LibMod.MODID + ":Amenonuhoko");
		needle= new ItemNeedle(NOT_A_MELEE_WEAPON).setUnlocalizedName("Needle").setTextureName(LibMod.MODID + ":Needle");
		NueTrident= new ItemNueTrident(WET_NOODLE).setUnlocalizedName("NueTrident").setTextureName(LibMod.MODID + ":NueTrident");
		swordOfKusanagi= new ItemSwordofKusanagi(GOLDYRON).setUnlocalizedName("SwordofKusanagi").setTextureName(LibMod.MODID + ":SwordofKusanagi");
		
		//PointItem
		FullPower = new ItemPoint().setUnlocalizedName("FullPower").setTextureName(LibMod.MODID + ":FullPower");
		Star = new ItemPoint().setUnlocalizedName("Star").setTextureName(LibMod.MODID + ":Star");
		Cherry = new ItemPoint().setUnlocalizedName("Cherry").setTextureName(LibMod.MODID + ":Cherry");
		TimeOrb = new ItemTimeOrb().setUnlocalizedName("TimeOrb").setTextureName(LibMod.MODID + ":TimeOrb");
		Faith = new ItemPoint().setUnlocalizedName("Faith").setTextureName(LibMod.MODID + ":Faith");
		UFORed = new ItemPoint().setUnlocalizedName("UFOs").setTextureName(LibMod.MODID + ":UFORed");
		UFOBlue = new ItemPoint().setUnlocalizedName("UFOs").setTextureName(LibMod.MODID + ":UFOBlue");
		UFOGreen = new ItemPoint().setUnlocalizedName("UFOs").setTextureName(LibMod.MODID + ":UFOGreen");
		UFOs = new ItemUFOs().setUnlocalizedName("UFOs").setTextureName(LibMod.MODID + ":UFOs");
		
		//@formatter:on

		//Item
		GameRegistry.registerItem(enchantedBook, LibItemName.ENCHANTEDBOOK);
		GameRegistry.registerItem(grimoireBook, LibItemName.GRIMOIREBOOK);
		GameRegistry.registerItem(volatileString, LibItemName.VOLATILESTRING);
		GameRegistry.registerItem(soldifiedPaper, LibItemName.SOLDIFIEDPAPER);
		GameRegistry.registerItem(gloriousNipponSteel, LibItemName.GLORIOUSNIPPONSTEEL);
		GameRegistry.registerItem(shimenawaRope, LibItemName.SHIMENAWAROPE);
		GameRegistry.registerItem(youkaiBook, LibItemName.YOUKAIBOOK);
		GameRegistry.registerItem(mask, LibItemName.MASK);
		GameRegistry.registerItem(Hihiirokane, LibItemName.HIHIIROKANE);
		GameRegistry.registerItem(ibarakiBoxEmpty, LibItemName.IBARAKIBOXEMPTY);
		GameRegistry.registerItem(impureRock, LibItemName.IMPUREROCK);

		//Food
		GameRegistry.registerItem(shroomSlice, LibItemName.SHROOMSLICE);
		GameRegistry.registerItem(grilledLamprey, LibItemName.GRILLEDLAMPREY);
		GameRegistry.registerItem(ibarakiBoxFilled, LibItemName.IBARAKIBOXFILLED);
		GameRegistry.registerItem(kappasNostrum, LibItemName.KAPPASNOSTRUM);

		//Armor
		GameRegistry.registerItem(foxMask, LibItemName.FOXMASK);
		GameRegistry.registerItem(raidenMask, LibItemName.RAIDENMASK);
		GameRegistry.registerItem(nonkeyMask, LibItemName.MONKEYMASK);
		GameRegistry.registerItem(hyottokoMask, LibItemName.HYOTTOKOMASK);
		GameRegistry.registerItem(fukuNoKamiMask, LibItemName.FUKUNOKAMIMASK);
		GameRegistry.registerItem(ubaMask, LibItemName.UBAMASK);
		GameRegistry.registerItem(hannyaMask, LibItemName.HANNYAMASK);
		GameRegistry.registerItem(koomoteMask, LibItemName.KOOMOTEMASK);
		GameRegistry.registerItem(maskOfHope, LibItemName.MASKOFHOPE);
		GameRegistry.registerItem(kokorosMasks, LibItemName.KOKOROSMASKS);
		GameRegistry.registerItem(primordialShield, LibItemName.PRIMORDIALSHIELD);
		GameRegistry.registerItem(momijisMapleLeafShield, LibItemName.MAPLELEAFSHIELD);

		//Weapon
		GameRegistry.registerItem(mochiHammer, LibItemName.MOCHIHAMMER);
		GameRegistry.registerItem(momijisScimitarSword, LibItemName.MOMIJISSCIMITARSWORD);
		GameRegistry.registerItem(laevatein, LibItemName.LAEVATEIN);
		GameRegistry.registerItem(nazrinStick, LibItemName.NAZRINSTICK);
		GameRegistry.registerItem(ellyScythe, LibItemName.ELLYSCYTHE);
		GameRegistry.registerItem(mikoStick, LibItemName.MIKOSTICK);
		GameRegistry.registerItem(crestOfYggdrasill, LibItemName.CRESTOFYGGDRASILL);
		GameRegistry.registerItem(amenonuhoko, LibItemName.AMENONUHOKO);
		GameRegistry.registerItem(needle, LibItemName.NEEDLE);
		GameRegistry.registerItem(NueTrident, LibItemName.NUETRIDENT);
		GameRegistry.registerItem(swordOfKusanagi, LibItemName.SWORDOFKUSANAGI);
		
		//PointItem
		GameRegistry.registerItem(FullPower, LibItemName.FULLPOWER);
		GameRegistry.registerItem(Star, LibItemName.STAR);
		GameRegistry.registerItem(Cherry, LibItemName.CHERRY);
		GameRegistry.registerItem(TimeOrb, LibItemName.TIMEORB);
		GameRegistry.registerItem(Faith, LibItemName.FAITH);
		GameRegistry.registerItem(UFORed, LibItemName.UFORED);
		GameRegistry.registerItem(UFOBlue, LibItemName.UFOBLUE);
		GameRegistry.registerItem(UFOGreen, LibItemName.UFOGREEN);
		GameRegistry.registerItem(UFOs, LibItemName.UFOs);
	}
}
