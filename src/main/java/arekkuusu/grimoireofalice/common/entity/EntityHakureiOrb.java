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
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityHakureiOrb extends EntityThrowable {

	private static final DataParameter<Float> SIZE = EntityDataManager.createKey(EntityMagicCircle.class, DataSerializers.FLOAT);
	private boolean isMoving = false;

	public EntityHakureiOrb(World world) {
		super(world);
	}

	public EntityHakureiOrb(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityHakureiOrb(World world, EntityLivingBase entity) {
		super(world, entity);
		posX = entity.posX;
		posY = entity.posY + 6;
		posZ = entity.posZ;
		setPosition(posX, posY, posZ);
		setSize(1F);
	}

	@Override
	protected void entityInit() {
		dataManager.register(SIZE, 0F);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		EntityLivingBase player = getThrower();
		if(player == null) {
			if(!worldObj.isRemote) {
				setDead();
			}
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
				setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0F, 1F, 0F);
				isMoving = true;
			}
			else {
				motionY -= 0.05D;
			}

			if(ticksExisted > 200 && !worldObj.isRemote) {
				setDead();
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

	private void bounce(RayTraceResult rayTraceResult) {
		EnumFacing facing = rayTraceResult.sideHit;
		if(facing == EnumFacing.UP || facing == EnumFacing.DOWN) {
			rotationYaw *= -1.0F;
			rotationPitch *= -1.0F;
			motionX *= 0.8F;
			motionY *= -0.8F;
			motionZ *= 0.8F;
		}
		else {
			rotationYaw *= -1.0F;
			motionX *= -0.8F;
			motionZ *= -0.8F;
		}
		setRotation(rotationYaw, rotationPitch);
	}

	private void onImpactEntity(RayTraceResult result) {
		if(result.entityHit != null && result.entityHit != getThrower()) {
			applyHitEffects(result.entityHit);
			if(!worldObj.isRemote) {
				setDead();
			}
		}
	}

	private void applyHitEffects(Entity entity) {
		entity.attackEntityFrom(DamageSource.causeThornsDamage(this), getSize() * 2);
	}

	@Override
	public AxisAlignedBB getEntityBoundingBox() {
		return super.getEntityBoundingBox().expandXyz(getSize());
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
		if (width != this.width || height != this.height) {
			this.width = size;
			this.height = size;
			AxisAlignedBB axisalignedbb = this.getEntityBoundingBox();
			this.setEntityBoundingBox(new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.minX + (double) this.width, axisalignedbb.minY + (double) this.height, axisalignedbb.minZ + (double) this.width));
		}
		dataManager.set(SIZE, size);
	}

	public float getSize() {
		return dataManager.get(SIZE);
	}
}
