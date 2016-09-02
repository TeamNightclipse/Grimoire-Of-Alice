package arekkuusu.grimoireofalice.item;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public final class ModItems {

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
		public static Item nonkeyMask;
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
		
		thirdEye = new Item3rdEye();
		amenonuhoko = new ItemAmenonuhoko(WET_NOODLE);
		crestOfYggdrasill = new ItemCrestOfYggdrasill(WET_NOODLE);
		gloriousNipponSteel = new ItemGloriousNipponSteel();
		hihiirokane = new ItemHihiirokane();
		laevatein = new ItemLaevatein(WET_NOODLE);
		mapleLeafShield = new ItemMapleLeafShield();
		mikoStick = new ItemMikoStick(WET_NOODLE);
		mochiHammer = new ItemMochiHammer(GOLDYRON);
		momijisScimitarSword = new ItemMomijisScimitarSword(GOLDYRON);
		nazrinStick = new ItemNazrinStick(WET_NOODLE);
		nueTrident = new ItemNueTrident(WET_NOODLE);
		primordialShield = new ItemPrimordialShield();
		shimenawaRope = new ItemShimenawaRope();
		swordOfKusanagi = new ItemSwordofKusanagi(GOLDYRON);
		timeOrb = new ItemTimeOrb();
		uFOs = new ItemUFOs();
		
	}
	
}
