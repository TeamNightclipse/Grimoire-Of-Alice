/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityHakureiOrb extends EntityThrowable {

	private static final DataParameter<Float> SIZE = EntityDataManager.createKey(EntityMagicCircle.class, DataSerializers.FLOAT);
	private boolean isMoving = false;

	public EntityHakureiOrb(World world) {
		super(world);
		isImmuneToFire = true;
	}

	public EntityHakureiOrb(World world, double x, double y, double z) {
		super(world, x, y, z);
		isImmuneToFire = true;
	}

	public EntityHakureiOrb(World world, EntityLivingBase entity) {
		super(world, entity);
		posX = entity.posX;
		posY = entity.posY + 6;
		posZ = entity.posZ;
		setPosition(posX, posY, posZ);
		setSize(1F);
		isImmuneToFire = true;
	}

	@Override
	protected void entityInit() {
		dataManager.register(SIZE, 0F);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(!world.isRemote) {
			EntityLivingBase player = getThrower();
			if(player == null) {
				setDead();
			}
			else {
				if(player.isHandActive() && !isMoving) {
					float size = getSize();
					posX = player.posX;
					posY = player.posY + 4;
					posZ = player.posZ;
					setPosition(posX, posY, posZ);
					if(size < 4) {
						setSize(size + 0.1F);
					}
				}
				else if(!isMoving) {
					setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0F, 1F, 1F);
					isMoving = true;
				}
				else {
					motionY -= 0.05D;
				}

				if(ticksExisted > 200) {
					setDead();
				}
			}
		}
	}

	@Override
	protected void onImpact(RayTraceResult rayTraceResult) {
		if(rayTraceResult.typeOfHit == RayTraceResult.Type.ENTITY) {
			onImpactEntity(rayTraceResult);
		}
		else if(rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK) {
			bounce(rayTraceResult);
		}
	}

	private void bounce(RayTraceResult raytrace) {
		EnumFacing facing = raytrace.sideHit;
		Vec3d vec = getLook(1);
		if(facing == EnumFacing.UP || facing == EnumFacing.DOWN) {
			setDirection(new Vec3d(vec.x, vec.y * -1D, vec.z));
			motionY *= -0.5D;
		}
		else {
			double x = vec.x;
			double z = vec.z;
			if(facing == EnumFacing.EAST || facing == EnumFacing.WEST) {
				motionX *= -0.5D;
				x *= -1D;
			}
			else {
				motionZ *= -0.5D;
				z *= -1D;
			}

			setDirection(new Vec3d(x, vec.y, z));
		}
	}

	private void setDirection(Vec3d vec) {
		float f = MathHelper.sqrt(vec.x * vec.x + vec.y * vec.y + vec.z * vec.z);
		double x = vec.x / (double) f;
		double y = vec.y / (double) f;
		double z = vec.z / (double) f;
		double velocity = motionX * motionX + motionZ * motionZ + motionY * motionY;
		x = x * velocity * 0.1;
		y = y * velocity * 0.1;
		z = z * velocity * 0.1;
		float f1 = MathHelper.sqrt(x * x + z * z);
		this.rotationYaw = (float) (MathHelper.atan2(x, z) * (180D / Math.PI));
		this.rotationPitch = (float) (MathHelper.atan2(y, (double) f1) * (180D / Math.PI));
		this.prevRotationYaw = this.rotationYaw;
		this.prevRotationPitch = this.rotationPitch;
	}

	private void onImpactEntity(RayTraceResult result) {
		if(result.entityHit != null && result.entityHit != getThrower()) {
			applyHitEffects(result.entityHit);
			if(!world.isRemote) {
				setDead();
			}
		}
	}

	private void applyHitEffects(Entity entity) {
		entity.attackEntityFrom(DamageSource.causeThornsDamage(this), getSize() * 2);
	}

	@Override
	public AxisAlignedBB getEntityBoundingBox() {
		return super.getEntityBoundingBox().grow(getSize());
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity entityIn) {
		return this.getEntityBoundingBox();
	}

	@Override
	protected float getGravityVelocity() {
		return 0;
	}

	public float getTicksAlive() {
		return ticksExisted;
	}

	private void setSize(float size) {
		dataManager.set(SIZE, size);
	}

	public float getSize() {
		return dataManager.get(SIZE);
	}
}
