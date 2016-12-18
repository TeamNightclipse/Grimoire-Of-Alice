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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

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
		float distance = 4F;
		double dx = thrower.posX + look.xCoord * distance;
		double dy = thrower.posY + 1 + look.yCoord * distance;
		double dz = thrower.posZ + look.zCoord * distance;
		setPosition(dx, dy, dz);
	}

	@Override
	protected void entityInit() {}

	@Override
	public void onUpdate() {
		super.onUpdate();
		EntityLivingBase thrower = getThrower();
		if(thrower == null) {
			if(!worldObj.isRemote) {
				setDead();
			}
		}
		float motionMultiplier = 0.99F;
		if(isInWater()) {
			for(int i = 0; i < 4; i++) {
				float f6 = 0.25F;
				worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, posX - motionX * f6, posY - motionY * f6, posZ - motionZ * f6, motionX,
						motionY, motionZ);
			}
			motionMultiplier *= 0.80808080F;
		}
		motionX *= motionMultiplier;
		motionY *= motionMultiplier;
		motionZ *= motionMultiplier;
		if(ticksExisted > 50 && !worldObj.isRemote) {
			setDead();
		}
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, posX + 0.5, posY + 0.5, posZ + 0.5, motionX, motionY, motionZ);
		playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1F, rand.nextFloat() * 1.0F + 0.8F);
		if(result.typeOfHit == RayTraceResult.Type.BLOCK) {
			onImpactBlock(result);
		}
		else if(result.typeOfHit == RayTraceResult.Type.ENTITY) {
			onImpactEntity(result);
		}
	}

	private void onImpactBlock(RayTraceResult result) {
		explosion();
		IBlockState base = worldObj.getBlockState(result.getBlockPos());
		boolean canHitBlock = base.getBlock() != Blocks.TALLGRASS && base.getBlock() != Blocks.DOUBLE_PLANT;
		if(canHitBlock && !worldObj.isRemote) {
			setDead();
		}
	}

	private void onImpactEntity(RayTraceResult result) {
		if(result.entityHit != null && result.entityHit != this) {
			if(result.entityHit == getThrower()) {
				if(!worldObj.isRemote) {
					setDead();
				}
				return;
			}
			applyHitEffects(result.entityHit);
		}
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityplayer) {
		if(entityplayer != getThrower()) {
			applyHitEffects(entityplayer);
		}
		else {
			if(!worldObj.isRemote) {
				setDead();
			}
		}
	}

	private void applyHitEffects(Entity entity) {
		explosion();
		entity.attackEntityFrom(DamageSource.generic, 5);
		double speed = 0.5;
		entity.motionX = -Math.sin(Math.toRadians(rotationYaw)) * speed;
		entity.motionZ = Math.cos(Math.toRadians(rotationYaw)) * speed;
	}

	private void explosion(){
		worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, posX + 0.5, posY + 0.5, posZ + 0.5, motionX, motionY, motionZ);
		playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1F, rand.nextFloat() * 1.0F + 0.8F);
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
