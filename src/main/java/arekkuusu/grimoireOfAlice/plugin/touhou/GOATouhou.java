package arekkuusu.grimoireOfAlice.plugin.touhou;

import arekkuusu.grimoireOfAlice.lib.LibItemName;
import arekkuusu.grimoireOfAlice.lib.LibMod;
import arekkuusu.grimoireOfAlice.plugin.touhou.crafting.THCrafting;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

//import thKaguyaMod.init.THKaguyaItems;

public class GOATouhou {
	
	public static Item pouch;
	
	public static void preInit() {
		
		pouch = new ItemSpellCardPouch().setUnlocalizedName("SpellCardPouch").setTextureName(LibMod.MODID + ":Pouch");
		
		GameRegistry.registerItem(pouch, LibItemName.POUCH);
	}
	
	public static void init() {
		THCrafting.pointsAndItems();
	}
}
