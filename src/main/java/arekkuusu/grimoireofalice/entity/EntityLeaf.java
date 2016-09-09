package arekkuusu.grimoireofalice.entity;

import arekkuusu.grimoireofalice.helper.LogHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

//TODO: Replace with SubEntity and Form once DanmakuCore can be used
public class EntityLeaf extends EntityThrowable {
	
	private int timeLive = 7;
	private int ticksInAir; //TODO: Use and AT to get access to this field

	public EntityLeaf(World worldIn) {
        super(worldIn);
    }

    public EntityLeaf(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    public EntityLeaf(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
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
			//FIXME: Only works for two facing directions
			Vec3d vec = getVectorForRotation(-rotationPitch, -rotationYaw).rotateYaw(45F).rotatePitch(45F); //These needs to be negative for some reason
			entityAnimalShot.setThrowableHeading(vec.xCoord, vec.yCoord, vec.zCoord, 0.3F, 0.0F);
			this.worldObj.spawnEntityInWorld(entityAnimalShot);
			this.setDead();
		}
	}

}
