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
import net.minecraft.entity.MoverType;
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

	public EntityNetherSoul(World world) {
		super(world);
	}

	public EntityNetherSoul(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityNetherSoul(World world, EntityLivingBase throwerIn) {
		super(world, throwerIn);
	}

	public EntityNetherSoul(World world, EntityLivingBase throwerIn,@Nullable Entity target) {
		super(world, throwerIn);
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

			move(MoverType.SELF, motionX, motionY, motionZ);
		}
		else {
			List<Entity> list = world.getEntitiesInAABBexcluding(getThrower(), getEntityBoundingBox().grow(30),
					entity -> entity instanceof EntityLivingBase && entity.canBeCollidedWith());
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
				setThrowableHeading(vec3d.x, vec3d.y, vec3d.z, 0.5F, 0);
			} else if(!world.isRemote) setDead();
		}
		if (ticksExisted >= 200 && !world.isRemote) {
			setDead();
		}
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (result.entityHit != null) {
			//TODO: DamageSource is mutable. Don't mutate the generic one. Create a new damage source
			//result.entityHit.attackEntityFrom(DamageSource.GENERIC.setDamageBypassesArmor(), 10);
			if (!world.isRemote) setDead();
		}
	}

	@Override
	protected float getGravityVelocity() {
		return 0;
	}
}
