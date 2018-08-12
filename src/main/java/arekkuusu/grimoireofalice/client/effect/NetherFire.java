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
import net.katsstuff.teamnightclipse.mirror.data.Vector3;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class NetherFire extends ParticleBase {

	private final float initScale;

	public NetherFire(World world, Vector3 pos, Vector3 speed, int age, float scale) {
		super(world, pos, speed);
		particleMaxAge = age;
		particleScale = scale;
		initScale = particleScale;
		canCollide = false;

		motionX = speed.x();
		motionY = speed.y();
		motionZ = speed.z();
		setAtlas(SpriteLibrary.NETHER_FIRE);
	}

	@Override
	public boolean shouldDisableDepth() {
		return true;
	}

	@Override
	public int getBrightnessForRender(float p_189214_1_) {
		return 255;
	}

	@Override
	public void onUpdateGlow() {
		if(this.particleAge++ >= this.particleMaxAge) {
			this.setExpired();
		}
		float life = (float) particleAge / (float) particleMaxAge;
		this.particleScale = initScale - initScale * life;
		this.particleAlpha = 0.25F * (1F - life);

		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		this.move(this.motionX, this.motionY, this.motionZ);
	}
}
