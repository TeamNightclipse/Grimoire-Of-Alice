/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.entity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.List;

public class EntityUnzanFist extends EntityThrowable {

    private int tick;

	public EntityUnzanFist(World world) {
		super(world);
	}

	public EntityUnzanFist(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityUnzanFist(World world, EntityLivingBase thrower) {
		super(world, thrower);
	}

	@Override
	public void onUpdate() {
        onEntityUpdate();
        this.lastTickPosX = this.posX;
        this.lastTickPosY = this.posY;
        this.lastTickPosZ = this.posZ;

        if (this.throwableShake > 0) {
            --this.throwableShake;
        }

        Vec3d vec3d = new Vec3d(this.posX, this.posY, this.posZ);
        Vec3d vec3d1 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        RayTraceResult raytraceresult = this.world.rayTraceBlocks(vec3d, vec3d1);
        vec3d = new Vec3d(this.posX, this.posY, this.posZ);
        vec3d1 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

        if (raytraceresult != null) {
            vec3d1 = new Vec3d(raytraceresult.hitVec.xCoord, raytraceresult.hitVec.yCoord, raytraceresult.hitVec.zCoord);
        }

        Entity entity = null;
        List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ).expandXyz(1.0D));
        double d0 = 0.0D;

        for (Entity aList : list) {
            if (aList.canBeCollidedWith()) {
                if (aList != this.ignoreEntity) {
                    if (this.ticksExisted < 2 && this.ignoreEntity == null) {
                        this.ignoreEntity = aList;
                    }
                    else {
                        AxisAlignedBB axisalignedbb = aList.getEntityBoundingBox().expandXyz(0.30000001192092896D);
                        RayTraceResult result = axisalignedbb.calculateIntercept(vec3d, vec3d1);

                        if (result != null) {
                            double d1 = vec3d.squareDistanceTo(result.hitVec);

                            if (d1 < d0 || d0 == 0.0D) {
                                entity = aList;
                                d0 = d1;
                            }
                        }
                    }
                }
            }
        }

        if (entity != null) {
            raytraceresult = new RayTraceResult(entity);
        }

        if (raytraceresult != null) {
            if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK && this.world.getBlockState(raytraceresult.getBlockPos()).getBlock() == Blocks.PORTAL) {
                this.setPortal(raytraceresult.getBlockPos());
            }
            else if (!net.minecraftforge.common.ForgeHooks.onThrowableImpact(this, raytraceresult)) {
                this.onImpact(raytraceresult);
            }
        }

        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;

        float f1 = 0.99F;

        this.motionX *= (double) f1;
        this.motionY *= (double) f1;
        this.motionZ *= (double) f1;

        if (!this.hasNoGravity()) {
            this.motionY -= (double) getGravityVelocity();
        }

        this.setPosition(this.posX, this.posY, this.posZ);

        if (!world.isRemote && tick > 20) {
            setDead();
        }
        ++tick;
    }

	@Override
	protected void entityInit() {}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (!world.isRemote) {
			if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
				onImpactBlock(result);
			}
			else if (result.typeOfHit == RayTraceResult.Type.ENTITY) {
				onImpactEntity(result);
			}
		}
	}

	private void onImpactBlock(RayTraceResult result) {
		IBlockState base = world.getBlockState(result.getBlockPos());
		boolean canHitBlock = base.getBlock() != Blocks.TALLGRASS && base.getBlock() != Blocks.DOUBLE_PLANT;
		if (canHitBlock) {
			explode();
		}
	}

	private void onImpactEntity(RayTraceResult result) {
        if (result.entityHit != null && result.entityHit != this && result.entityHit != getThrower()) {
            explode();
        }
    }

	@Override
	public void onCollideWithPlayer(EntityPlayer entityplayer) {
        if (!world.isRemote && entityplayer != getThrower()) {
            explode();
        }
    }

	private void explode() {
		playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1F, rand.nextFloat() * 1.0F + 0.8F);
		if(world instanceof WorldServer) {
			((WorldServer) world).spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, posX + 0.5, posY + 0.5, posZ + 0.5, 3, motionX, motionY, motionZ, 0.1);
		}

		EntityLivingBase thrower = getThrower();

		List<EntityLivingBase> list = world.getEntitiesWithinAABB(EntityLivingBase.class, getEntityBoundingBox(), entity -> entity != thrower);
		list.forEach(entity -> entity.attackEntityFrom(DamageSource.causeExplosionDamage(thrower), 6F));

		setDead();
	}

	@Override
	public AxisAlignedBB getEntityBoundingBox() {
		return super.getEntityBoundingBox().expandXyz(2);
	}

	@Override
	protected float getGravityVelocity() {
		return 0;
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	public boolean canBeAttackedWithItem() {
		return true;
	}

	@Override
	public boolean canBeCollidedWith() {
		return false;
	}
}
