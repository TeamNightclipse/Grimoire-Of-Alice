/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.util;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static arekkuusu.grimoireofalice.client.util.ResourceLibrary.TextureLocation.EFFECT;

@SideOnly(Side.CLIENT)
public final class SpriteLibrary {

	//Particle
	public static final ResourceLocation NEEDLE_SWING = ResourceLibrary.getAtlas(EFFECT, "needle_swing2");
	public static final ResourceLocation NETHER_FIRE = ResourceLibrary.getAtlas(EFFECT, "nether_fire");
	public static final ResourceLocation RED_GAS = ResourceLibrary.getAtlas(EFFECT, "red_gas");
	public static final ResourceLocation RED_MIST = ResourceLibrary.getAtlas(EFFECT, "red_mist");
	public static final ResourceLocation SHINMYOUMARU_SPARKLE = ResourceLibrary.getAtlas(EFFECT, "shinmyoumaru_sparkle");
}
