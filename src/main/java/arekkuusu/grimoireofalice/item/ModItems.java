/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.item.masks.ItemFoxMask;
import arekkuusu.grimoireofalice.item.masks.ItemFukuNoKamiMask;
import arekkuusu.grimoireofalice.item.masks.ItemHannyaMask;
import arekkuusu.grimoireofalice.item.masks.ItemHyottokoMask;
import arekkuusu.grimoireofalice.item.masks.ItemKokorosMasks;
import arekkuusu.grimoireofalice.item.masks.ItemKoomoteMask;
import arekkuusu.grimoireofalice.item.masks.ItemMaskOfHope;
import arekkuusu.grimoireofalice.item.masks.ItemMonkeyMask;
import arekkuusu.grimoireofalice.item.masks.ItemRaidenMask;
import arekkuusu.grimoireofalice.item.masks.ItemUbaMask;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public final class ModItems {

	public static final ArmorMaterial SOLID_PAPER = EnumHelper.addArmorMaterial("solidPaper", "No", 1000, new int[] {1,2,3,4}, 30, SoundEvents.ENTITY_ENDERMEN_TELEPORT, 0);
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
		public static Item hihiirokane;
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
		public static Item monkeyMask;
		public static Item hyottokoMask;
		public static Item fukuNoKamiMask;
		public static Item ubaMask;
		public static Item hannyaMask;
		public static Item koomoteMask;
		public static Item maskOfHope;
		public static Item kokorosMasks;
		public static Item primordialShield;
		public static Item mapleLeafShield;
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
		public static Item nueTrident;
		public static Item swordOfKusanagi;
		
		//PointItem
		public static Item fullPower;
		public static Item star;
		public static Item cherry;
		public static Item timeOrb;
		public static Item faith;
		public static Item uFORed;
		public static Item uFOBlue;
		public static Item uFOGreen;
		public static Item uFOs;
		public static Item thirdEye;
	
	public static void init() {
		
		//Item
		thirdEye = new Item3rdEye();
		gloriousNipponSteel = new ItemGloriousNipponSteel();
		hihiirokane = new ItemHihiirokane();
		shimenawaRope = new ItemShimenawaRope();
		timeOrb = new ItemTimeOrb();
		uFOs = new ItemUFOs();
		
		//Food
		
		//Armor
		mapleLeafShield = new ItemMapleLeafShield();
		primordialShield = new ItemPrimordialShield();
		foxMask = new ItemFoxMask(SOLID_PAPER, 3);
		raidenMask = new ItemRaidenMask(SOLID_PAPER, 3);
		monkeyMask = new ItemMonkeyMask(SOLID_PAPER, 3);
		hyottokoMask = new ItemHyottokoMask(SOLID_PAPER, 3);
		fukuNoKamiMask = new ItemFukuNoKamiMask(SOLID_PAPER, 3);
		ubaMask = new ItemUbaMask(SOLID_PAPER, 3);
		hannyaMask = new ItemHannyaMask(SOLID_PAPER, 3);
		koomoteMask = new ItemKoomoteMask(SOLID_PAPER, 3);
		maskOfHope = new ItemMaskOfHope(SOLID_PAPER, 3);
		kokorosMasks = new ItemKokorosMasks(SOLID_PAPER, 3);
		
		//Weapons
		amenonuhoko = new ItemAmenonuhoko(WET_NOODLE);
		crestOfYggdrasill = new ItemCrestOfYggdrasill(WET_NOODLE);
		laevatein = new ItemLaevatein(WET_NOODLE);
		mikoStick = new ItemMikoStick(WET_NOODLE);
		mochiHammer = new ItemMochiHammer(GOLDYRON);
		momijisScimitarSword = new ItemMomijisScimitarSword(GOLDYRON);
		nazrinStick = new ItemNazrinStick(WET_NOODLE);
		nueTrident = new ItemNueTrident(WET_NOODLE);
		swordOfKusanagi = new ItemSwordofKusanagi(GOLDYRON);
		ellyScythe = new ItemEllyScythe(WET_NOODLE);
		
	}
	
}
