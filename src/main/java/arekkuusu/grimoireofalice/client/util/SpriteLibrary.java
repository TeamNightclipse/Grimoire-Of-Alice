/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.util;

import arekkuusu.grimoireofalice.client.util.resource.FrameSpriteResource;
import arekkuusu.grimoireofalice.client.util.resource.SpriteLoader;
import arekkuusu.grimoireofalice.client.util.resource.SpriteResource;
import arekkuusu.grimoireofalice.common.core.helper.LogHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static arekkuusu.grimoireofalice.client.util.ResourceLibrary.TextureLocation.EFFECT;

@SideOnly(Side.CLIENT)
public final class SpriteLibrary {

	//Particle
	public static final FrameSpriteResource NEEDLE_SWING = SpriteLoader.load(EFFECT, "needle_swing", 0, 3);
	public static final SpriteResource NETHER_FIRE = SpriteLoader.load(EFFECT, "nether_fire");
	public static final SpriteResource RED_GAS = SpriteLoader.load(EFFECT, "red_gas");
	public static final SpriteResource RED_MIST = SpriteLoader.load(EFFECT, "red_mist");
	public static final SpriteResource SHINMYOUMARU_SPARKLE = SpriteLoader.load(EFFECT, "shinmyoumaru_sparkle");

	public static void init() {
		LogHelper.warn("[NEKO MIKU REIMU]");
	}
}
