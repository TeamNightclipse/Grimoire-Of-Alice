package arekkuusu.grimoireofalice.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;


public class EntityEllyScythe extends EntityThrow {

	private static final double RETURN_STRENGTH	= 0.05D;
	private static final float MIN_FLOAT_STRENGTH = 0.4F;
	private float strength;

	public EntityEllyScythe(World world) {
		super(world);
	}

	public EntityEllyScythe(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityEllyScythe(World world, EntityLivingBase thrower, ItemStack itemstack, float f) {
		super(world, thrower);
		setHeadingFromThrower(thrower, thrower.rotationPitch, thrower.rotationYaw, 0.0F, f, 0.0F);
		setAngles(thrower.rotationYaw,thrower.rotationPitch);
		setPickupModeFromEntity(thrower);
		setStack(itemstack);
		strength = Math.min(1.5F, f);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		EntityLivingBase thrower = getThrower();
		if(thrower == null) {
			if (!worldObj.isRemote) {
				setDead();
			}
		}
		if (inGround) return;

		strength *= 0.994F;
		if (strength < MIN_FLOAT_STRENGTH) {
			if (getCritical()) {
				setCritical(false);
			}
			strength = 0F;
		}

		if (!inGround) {
			rotationYaw += 20F * strength;
		}

		if (!inGround && getThrower() != null && strength > 0F) {
			EntityLivingBase shootingEntity = getThrower();
			double dx;
			double dy;
			double dz;
			dx = posX - shootingEntity.posX;
			dy = posY - shootingEntity.posY - shootingEntity.getEyeHeight();
			dz = posZ - shootingEntity.posZ;

			double d = Math.sqrt(dx * dx + dy * dy + dz * dz);
			dx /= d;
			dy /= d;
			dz /= d;

			motionX -= RETURN_STRENGTH * dx;
			motionY -= RETURN_STRENGTH * dy;
			motionZ -= RETURN_STRENGTH * dz;
		}

		if (throwableShake > 0) {
			throwableShake--;
		}

		Vec3d vec3d = new Vec3d(posX, posY, posZ);
		Vec3d vec3d1 = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);
		RayTraceResult mop = worldObj.rayTraceBlocks(vec3d, vec3d1, false, true, false);
		if (mop != null) {
			vec3d1 = new Vec3d(mop.hitVec.xCoord, mop.hitVec.yCoord, mop.hitVec.zCoord);
		}

		Entity entity = null;
		List<Entity> list = worldObj.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().addCoord(motionX, motionY, motionZ).expandXyz(1.0D));
		double d = 0.0D;
		for(Entity entity1 : list) {
			if (!entity1.canBeCollidedWith() || entity1 == thrower && ticksExisted < 5) {
				continue;
			}
			float f4 = 0.3F;
			AxisAlignedBB axisalignedbb1 = entity1.getEntityBoundingBox().expandXyz(f4);
			RayTraceResult mop1 = axisalignedbb1.calculateIntercept(vec3d, vec3d1);
			if (mop1 == null) {
				continue;
			}
			double d1 = vec3d.distanceTo(mop1.hitVec);
			if (d1 < d || d == 0.0D) {
				entity = entity1;
				d = d1;
			}
		}

		if (entity != null) {
			mop = new RayTraceResult(entity);
		}

		if (mop != null) {
			if (mop.entityHit != null) {
				onImpactEntity(mop);
			} else {
				onImpactBlock(mop);
			}
		}

		posX += motionX;
		posY += motionY;
		posZ += motionZ;

		float res = 0.99F;
		float grav = getGravityVelocity();
		if (isInWater()) {
			for (int i = 0; i < 4; i++) {
				float f6 = 0.25F;
				worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, posX - motionX * f6, posY - motionY * f6, posZ - motionZ * f6, motionX, motionY, motionZ);
			}
			res *= 0.80808080F;
		}
		motionX *= res;
		motionY *= res;
		motionZ *= res;
		motionY -= grav;
		setPosition(posX, posY, posZ);
	}

	@Override
	void applyHitEffects(Entity entity) {
		entity.attackEntityFrom(DamageSource.causeThornsDamage(this), 5);
	}

	@Override
	double gravityThreshold() {
		return 0;
	}
}