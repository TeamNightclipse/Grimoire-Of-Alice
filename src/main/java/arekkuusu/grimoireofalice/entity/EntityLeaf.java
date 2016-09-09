package arekkuusu.grimoireofalice.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
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
        if (result.entityHit != null) {
            int i = 0;

            if (result.entityHit instanceof EntityPlayer) {
                i = 3;
            }

            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)i);
        }

        for (int j = 0; j < 8; ++j) {
            this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }

        if(!worldObj.isRemote) {
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
		EntityAnimalShot entityAnimalShot = new EntityAnimalShot(worldObj, this.posX, this.posY, this.posZ);
		//TODO: Spawn Entity always heading up with 45 degrees in the direction of this Entity.
		if(worldObj.isRemote){ //Looks like it does nothing if the !worldObj.isRemote
		entityAnimalShot.setThrowableHeading(45, this.rotationYaw, 0.0F, 0.3F, 5.0F);
		this.worldObj.spawnEntityInWorld(entityAnimalShot);
			this.setDead();
		}
	}

}
