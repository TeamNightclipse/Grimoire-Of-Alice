/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.entity;

import arekkuusu.grimoireofalice.plugin.danmakucore.LibGOAShotData;
import net.katsstuff.danmakucore.entity.danmaku.DanmakuBuilder;
import net.katsstuff.danmakucore.entity.danmaku.EntityDanmaku;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

//TODO: Replace with SubEntity and Form once DanmakuCore can be used
public class EntityLeaf extends EntityThrowable {

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
	protected void entityInit(){
    }
	
    /*
     * After living for some ticksInAir, it will spawn 
     * smoke in its position, spawn an EntityAnimalShot,
     * and then setDead.
    */
	@Override
	public void onUpdate() {
		super.onUpdate();
		int timeLive = 15;
		if(ticksExisted >= timeLive){
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
				result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 5F);
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

			EntityDanmaku danmaku = DanmakuBuilder.builder()
					.setSource(this)
					.setMovementData(0.1D)
					.setShot(LibGOAShotData.UFO).build().asEntity();
			worldObj.spawnEntityInWorld(danmaku);
			this.setDead();
		}
	}
}
