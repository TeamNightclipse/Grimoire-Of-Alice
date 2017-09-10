/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.effect;

import arekkuusu.grimoireofalice.client.util.SpriteLibrary;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.katsstuff.danmakucore.helper.MathUtil;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RedGas extends ParticleBase {

	public RedGas(World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
		super(world, x, y, z, xSpeed, ySpeed, zSpeed);
		motionY *= 0.20000000298023224D;

		if(MathUtil.fuzzyEqual(xSpeed, 0D) && MathUtil.fuzzyEqual(zSpeed, 0D)) {
			motionX *= 0.10000000149011612D;
			motionZ *= 0.10000000149011612D;
		}

		particleScale *= rand.nextFloat();
		particleMaxAge = (int) (80.0D / (Math.random() * 0.8D + 0.2D));
		setAtlas(SpriteLibrary.RED_GAS);
	}

	@Override
	public int getBrightnessForRender(float i) {
		return 255;
	}

	@Override
	public boolean shouldDisableDepth() {
		return true;
	}
}
