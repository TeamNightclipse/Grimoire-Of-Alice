/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimore Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package Arekkuusu.GrimoireOfAlice.item;

import Arekkuusu.GrimoireOfAlice.lib.LibItemName;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class GOAItem extends Item {

	public static Item EnhancedBook;
	//public static Item compEnhancedBook;

	public static void preInit() {

		EnhancedBook = new ItemEnchantedBook().setCreativeTab(CreativeTabs.tabCombat);
		//compEnhancedBook = new ItemGOABase().setTextureName(LibMod.MODID + ":EnchantedBook").setUnlocalizedName("enchantedBook");
		
		GameRegistry.registerItem(EnhancedBook, LibItemName.ENHANCED_BOOK);
	}

}
