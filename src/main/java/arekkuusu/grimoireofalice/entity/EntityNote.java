package arekkuusu.grimoireofalice.entity;

import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityNote extends EntityThrowable {
	
	private int timeLive = 30;
	private float ticksInAir; //TODO: Use and AT to get access to this field

	public EntityNote(World worldIn) {
		super(worldIn);
	}
	
	public EntityNote(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    public EntityNote(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

	@Override
	protected void entityInit() {
		
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		worldObj.spawnParticle(EnumParticleTypes.NOTE, (double)posX + 0.5D, (double)posY + 0.5D, (double)posZ + 0.5D, (double)ticksInAir / 24.0D, 0.0D, 0.0D);
		Random rand = new Random();
		if (rand.nextInt(6) == 3) {
			worldObj.playSound(null, posX, posY, posZ, SoundEvents.BLOCK_NOTE_HARP, SoundCategory.RECORDS, 0.5F, 1F);
		}
		if(this.ticksInAir >= timeLive){
			setDead();
		}
		++this.ticksInAir;
	}
	
	@Override
	public float getGravityVelocity() {
        return 0F;
    }
	
	@Override
	protected void onImpact(RayTraceResult result) {
		if(result.entityHit instanceof EntityLiving){
			result.entityHit.attackEntityFrom(DamageSource.magic, 2);
		}
		setDead();
	}
}
