/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

//TODO: Replace with SubEntity and Form once DanmakuCore can be used
public class EntityLeaf extends EntityThrowable {
	
	private static final DataParameter<Float> TIME = EntityDataManager.createKey(EntityGrimoireSpell.class, DataSerializers.FLOAT);
	private int timeLive = 15;
	private float ticksInAir; //TODO: Use and AT to get access to this field

	public EntityLeaf(World worldIn) {
        super(worldIn);
    }

    public EntityLeaf(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    public EntityLeaf(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }
    
    @Override
	protected void entityInit() {
    	dataManager.register(TIME, ticksInAir);
    }
	
    /*
     * After living for some ticksInAir, it will spawn 
     * smoke in its position, spawn an EntityAnimalShot,
     * and then setDead.
    */
	@Override
	public void onUpdate() {
		super.onUpdate();
		++this.ticksInAir;
		setTime(ticksInAir);
		if(this.ticksInAir >= timeLive){
			doEffects();
		}
	}
	
	@Override
	protected void onImpact(RayTraceResult result) {

		for (int j = 0; j < 8; ++j) {
			this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
		}

		if(!worldObj.isRemote) {
			if (result.entityHit != null) {
				float i = 5F;

				if (result.entityHit instanceof EntityPlayer) {
					i = 3F;
				}

				result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), i);
			}
			this.setDead();
		}
    }
	
	@Override
	public float getGravityVelocity() {
        return 0.04F;
    }
	
	/*
	 * Makes Smoke particle, creates new EntityAnimalShot 
	 * with direction of this entity.
	*/
	private void doEffects(){
		for (int j = 0; j < 8; ++j) {
			this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX, this.posY, this.posZ, 0, 0, 0);
		}
		if(!worldObj.isRemote){
			EntityAnimalShot entityAnimalShot = new EntityAnimalShot(worldObj, this.posX, this.posY, this.posZ);
			//TODO: Make random movements
			Vec3d vec = getVectorForRotation(-rotationPitch, -rotationYaw).rotatePitch(45F).rotateYaw(45F);
			entityAnimalShot.setThrowableHeading(vec.xCoord, vec.yCoord, vec.zCoord, 0.3F, 0.0F);
			this.worldObj.spawnEntityInWorld(entityAnimalShot);
			this.setDead();
		}
	}
	
	public void setTime(float time) {
		dataManager.set(TIME, time);
	}

	public float getTime() {
		return dataManager.get(TIME);
	}

}
