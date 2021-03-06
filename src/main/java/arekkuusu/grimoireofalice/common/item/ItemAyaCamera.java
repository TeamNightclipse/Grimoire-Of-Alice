/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.lib.LibName;

public class ItemAyaCamera extends ItemCamera  {

	public ItemAyaCamera() {
		super(LibName.AYA_CAMERA);
	}

	@Override
	public int getSize() {
		return 4;
	}

	@Override
	public float getAngle() {
		return 0;
	}
}
