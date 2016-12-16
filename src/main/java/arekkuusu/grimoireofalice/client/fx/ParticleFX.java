/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.fx;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum ParticleFX implements IStringSerializable {
	SHINMYOUMARU_SPARKLE,
	RED_MIST,
	NEEDLE_SWING,
	RED_GAS,
	NETHER_FIRE;

	@Override
	public String getName() {
		return name().toLowerCase(Locale.ROOT);
	}
}
