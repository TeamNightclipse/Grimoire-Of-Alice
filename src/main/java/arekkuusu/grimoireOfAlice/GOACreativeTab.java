/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice;

import arekkuusu.grimoireOfAlice.item.GOAItem;
import arekkuusu.grimoireOfAlice.tmp.CleanupDone;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

@CleanupDone
public class GOACreativeTab extends CreativeTabs {

	GOACreativeTab(String label) {
		super(label);
	}

	@Override
	public Item getTabIconItem() {
		return GOAItem.grimoireBook;
	}

	@Override
	public boolean hasSearchBar() {
		return true;
	}
}