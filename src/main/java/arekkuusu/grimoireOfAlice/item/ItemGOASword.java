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
import arekkuusu.grimoireOfAlice.tmp.CleanupDone;
import net.minecraft.item.ItemSword;

@CleanupDone
public class ItemGOASword extends ItemSword {

	public ItemGOASword(ToolMaterial material) {
		super(material);
		setCreativeTab(GrimoireOfAlice.CREATIVE_TAB);
	}
}
