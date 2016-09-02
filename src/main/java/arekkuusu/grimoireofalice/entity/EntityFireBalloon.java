/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * A fireball entity that doesn't destroy blocks
 */
public class EntityFireBalloon extends EntityFireball {

	public EntityFireBalloon(World world) {
		super(world);
	}

	public EntityFireBalloon(World world, EntityLivingBase shooter, Vec3d position, Vec3d acceleration) {
		super(world, position.xCoord, position.yCoord, position.zCoord, acceleration.xCoord, acceleration.yCoord, acceleration.zCoord);
		shootingEntity = shooter;
	}
	@Override
	protected void onImpact(RayTraceResult result) {
		if(!worldObj.isRemote) {
			if(result.entityHit != null) {
				result.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, shootingEntity), 6.0F);
			}

			worldObj.createExplosion(this, posX, posY, posZ, 2, false);
			setDead();
		}
	}
}
