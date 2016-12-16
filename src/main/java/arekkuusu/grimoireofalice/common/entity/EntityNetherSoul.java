/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.entity;

import arekkuusu.grimoireofalice.client.fx.ParticleFX;
import arekkuusu.grimoireofalice.common.GrimoireOfAlice;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class EntityNetherSoul extends EntityThrowable {

	private static final double MOVING_STRENGTH = 0.01D;
	private Entity target;

	public EntityNetherSoul(World worldIn) {
		super(worldIn);
	}

	public EntityNetherSoul(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityNetherSoul(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
	}

	public EntityNetherSoul(World worldIn, EntityLivingBase throwerIn,@Nullable Entity target) {
		super(worldIn, throwerIn);
		this.target = target;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (isDead) return;
		GrimoireOfAlice.proxy.sparkleFX(ParticleFX.NETHER_FIRE, null, posX, posY, posZ, 0, 0, 0);
		if (ticksExisted % 4 == 0) {
			for (int i = 0; i < 5; i++) {
				GrimoireOfAlice.proxy.sparkleFX(ParticleFX.RED_GAS, null, posX, posY, posZ, 0, 0, 0);
			}
		}
		if (target != null && !target.isDead) {
			double dx = posX - target.posX;
			double dy = posY - target.posY - target.getEyeHeight();
			double dz = posZ - target.posZ;

			double d = Math.sqrt(dx * dx + dy * dy + dz * dz);
			dx /= d;
			dy /= d;
			dz /= d;

			motionX -= MOVING_STRENGTH * dx;
			motionY -= MOVING_STRENGTH * dy;
			motionZ -= MOVING_STRENGTH * dz;

			prevPosX = posX += motionX;
			prevPosY = posY += motionY;
			prevPosZ = posZ += motionZ;

			moveEntity(motionX, motionY, motionZ);
		}
		else {
			List<Entity> list = worldObj.getEntitiesInAABBexcluding(getThrower(), getEntityBoundingBox().expandXyz(30), entity -> entity instanceof EntityLivingBase && entity.canBeCollidedWith());
			if (!list.isEmpty()) {
				float closest = 30;
				for (Entity entity : list) {
					float i = entity.getDistanceToEntity(this);
					if (i < closest) {
						closest = i;
						target = entity;
					}
				}
			}
			else if (getThrower() != null) {
				Vec3d vec3d = getThrower().getLookVec();
				setThrowableHeading(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord, 0.5F, 0);
			} else if(!worldObj.isRemote) setDead();
		}
		if (ticksExisted >= 200 && !worldObj.isRemote) {
			setDead();
		}
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (result.entityHit != null) {
			result.entityHit.attackEntityFrom(DamageSource.generic.setDamageBypassesArmor(), 10);
			if (!worldObj.isRemote) setDead();
		}
	}

	@Override
	protected float getGravityVelocity() {
		return 0;
	}
}
