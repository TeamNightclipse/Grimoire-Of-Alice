/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.plugin.danmakucore.item;

import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.item.ItemStack;

public class ItemAyaCamera extends ItemCamera implements IOwnedBy {

	public ItemAyaCamera() {
		super(LibItemName.AYA_CAMERA);
	}

	@Override
	public int getSize() {
		return 5;
	}

	@Override
	public EnumTouhouCharacters character(ItemStack stack) {
		return EnumTouhouCharacters.AYA_SHAMEIMARU;
	}
}
