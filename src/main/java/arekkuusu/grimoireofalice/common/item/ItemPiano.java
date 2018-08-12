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

public class ItemPiano extends ItemInstrument  {

	public ItemPiano() {
		super(LibName.LYRICA_PIANO);
		setMaxDamage(150);
	}

	@Override
	public double getVelocity() {
		return 0.8D;
	}

	@Override
	public float getSize() {
		return 2F;
	}

	@Override
	public double getDistance() {
		return 2D;
	}
}
