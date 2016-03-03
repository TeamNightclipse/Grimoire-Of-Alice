/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimore Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package Arekkuusu.GrimoireOfAlice.item.crafting;

import Arekkuusu.GrimoireOfAlice.handler.ConfigHandler;
import Arekkuusu.GrimoireOfAlice.item.GOAItem;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;

public class CraftingVanilla {

	public static void misc() {
		if(ConfigHandler.BookEnabled) {
			GameRegistry.addRecipe(new ItemStack(GOAItem.EnhancedBook, 1, 2));
		}
	}

	public static void Table() {}
}
