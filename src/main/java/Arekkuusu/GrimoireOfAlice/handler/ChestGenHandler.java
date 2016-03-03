/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimore Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package Arekkuusu.GrimoireOfAlice.handler;

import Arekkuusu.GrimoireOfAlice.item.GOAItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class ChestGenHandler {

	private static final WeightedRandomChestContent ENCHANTED_BOOK = new WeightedRandomChestContent(new ItemStack(GOAItem.EnhancedBook, 1, 0), 1, 2, 9);

	public static void init() {

		ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(ENCHANTED_BOOK);
	}
}
