/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.util;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public final class SpriteLibrary {

	//Particle
	public static final ResourceLocation NEEDLE_SWING = ResourceLibrary.getEffect("needle_swing");
	public static final ResourceLocation NETHER_FIRE = ResourceLibrary.getEffect("nether_fire");
	public static final ResourceLocation RED_GAS = ResourceLibrary.getEffect("red_gas");
	public static final ResourceLocation RED_MIST = ResourceLibrary.getEffect("red_mist");
	public static final ResourceLocation SHINMYOUMARU_SPARKLE = ResourceLibrary.getEffect("shinmyoumaru_sparkle");
}
