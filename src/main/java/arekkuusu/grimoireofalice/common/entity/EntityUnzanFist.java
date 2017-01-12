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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.*;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.List;

public class EntityUnzanFist extends EntityThrowable {

	public EntityUnzanFist(World world) {
		super(world);
	}

	public EntityUnzanFist(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityUnzanFist(World world, EntityLivingBase thrower) {
		super(world, thrower);
		Vec3d look = thrower.getLookVec();
		float distance = 5F;
		double dx = thrower.posX + look.xCoord * distance;
		double dy = thrower.posY + 1 + look.yCoord * distance;
		double dz = thrower.posZ + look.zCoord * distance;
		setPosition(dx, dy, dz);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
	}

	@Override
	protected void entityInit() {}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (!worldObj.isRemote) {
			if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
				onImpactBlock(result);
			}
			else if (result.typeOfHit == RayTraceResult.Type.ENTITY) {
				onImpactEntity(result);
			}
		}
	}

	private void onImpactBlock(RayTraceResult result) {
		IBlockState base = worldObj.getBlockState(result.getBlockPos());
		boolean canHitBlock = base.getBlock() != Blocks.TALLGRASS && base.getBlock() != Blocks.DOUBLE_PLANT;
		if (canHitBlock) {
			explode();
		}
	}

	private void onImpactEntity(RayTraceResult result) {
		if(result.entityHit != null && result.entityHit != this) {
			if(result.entityHit == getThrower()) {
				setDead();
			}
			else {
				explode();
			}
		}
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityplayer) {
		if(!worldObj.isRemote) {
			if (entityplayer != getThrower()) {
				explode();
			} else {
				setDead();
			}
		}
	}

	private void explode() {
		playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1F, rand.nextFloat() * 1.0F + 0.8F);
		if(worldObj instanceof WorldServer) {
			((WorldServer) worldObj).spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, posX + 0.5, posY + 0.5, posZ + 0.5, 3, motionX, motionY, motionZ, 0.1);
		}

		EntityLivingBase thrower = getThrower();

		List<EntityLivingBase> list = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, getEntityBoundingBox(), entity -> entity != thrower);
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
