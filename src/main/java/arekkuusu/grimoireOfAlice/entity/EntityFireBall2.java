/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.entity;

import arekkuusu.grimoireOfAlice.tmp.CleanupDone;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/**
 * A fireball entity that doesn't destroy blocks
 */
@CleanupDone
public class EntityFireBall2 extends EntityFireball {

	public EntityFireBall2(World world) {
		super(world);
	}

	public EntityFireBall2(World world, EntityLivingBase shooter, Vec3 position, Vec3 acceleration) {
		super(world, position.xCoord, position.yCoord, position.zCoord, acceleration.xCoord, acceleration.yCoord, acceleration.zCoord);
		shootingEntity = shooter;
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if(!worldObj.isRemote) {
			if(mop.entityHit != null) {
				mop.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, shootingEntity), 6.0F);
			}

			worldObj.createExplosion(this, posX, posY, posZ, 2, false);
			setDead();
		}
	}
}
