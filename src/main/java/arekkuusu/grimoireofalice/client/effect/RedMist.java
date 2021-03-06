/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.effect;

import arekkuusu.grimoireofalice.client.util.SpriteLibrary;
import net.katsstuff.teamnightclipse.mirror.data.Vector3;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RedMist extends ParticleBase {

	private static final double RETURN_STRENGTH = 0.01D;
	private final Entity entity;

	public RedMist(World world, Entity entity, Vector3 pos, Vector3 speed) {
		super(world, pos, speed);
		this.entity = entity;
		particleScale = 16F + rand.nextInt(4);
		particleMaxAge = (int) entity.getDistance(posX, posY, posZ) * 3;
		setAtlas(SpriteLibrary.RED_MIST);
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
		if(entity != null && !entity.isDead) {
			double dx = posX - entity.posX;
			double dy = posY - entity.posY - entity.getEyeHeight();
			double dz = posZ - entity.posZ;
			double d = Math.sqrt(dx * dx + dy * dy + dz * dz);
			dx /= d;
			dy /= d;
			dz /= d;
			motionX -= RETURN_STRENGTH * dx;
			motionY -= RETURN_STRENGTH * dy;
			motionZ -= RETURN_STRENGTH * dz;
			prevPosX = posX += motionX;
			prevPosY = posY += motionY;
			prevPosZ = posZ += motionZ;
			if(particleAge++ >= particleMaxAge) {
				setExpired();
			}
			move(motionX, motionY, motionZ);
		} else {
			setExpired();
		}
	}
}
