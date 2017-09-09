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
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.entity.Entity;
import net.minecraft.util.Tuple;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class NeedleSwing extends ParticleBase {

	private final float initScale;

	public NeedleSwing(World world, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int age, float scale) {
		super(world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);
		particleMaxAge = age;
		particleScale = scale;
		initScale = particleScale;
		canCollide = false;

		motionX = xSpeed;
		motionY = ySpeed;
		motionZ = zSpeed;
	}

	@Override
	public void renderParticle(VertexBuffer buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		int life = Minecraft.getMinecraft().player.ticksExisted;
		SpriteLibrary.NEEDLE_SWING.bindManager();
		Tuple<Double, Double> uv = SpriteLibrary.NEEDLE_SWING.getUVFrame(life);
		double uOffset = SpriteLibrary.NEEDLE_SWING.getU();
		double u = uv.getFirst();

		renderEasy(buffer, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ, u, u + uOffset, 0, 1);
	}

	@Override
	public void onUpdate() {
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

	@Override
	public int getBrightnessForRender(float ignored) {
		return 255;
	}
}
