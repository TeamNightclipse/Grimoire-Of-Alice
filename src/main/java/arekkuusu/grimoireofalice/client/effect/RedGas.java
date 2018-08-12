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
import net.katsstuff.teamnightclipse.danmakucore.helper.MathUtil;
import net.katsstuff.teamnightclipse.mirror.data.Vector3;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RedGas extends ParticleBase {

	public RedGas(World world, Vector3 pos, Vector3 speed) {
		super(world, pos, speed);
		motionY *= 0.20000000298023224D;

		if(MathUtil.fuzzyEqual(speed.x(), 0D) && MathUtil.fuzzyEqual(speed.z(), 0D)) {
			motionX *= 0.10000000149011612D;
			motionZ *= 0.10000000149011612D;
		}

		particleScale *= rand.nextFloat();
		particleMaxAge = (int) (8.0D / (Math.random() * 0.8D + 0.2D));
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
