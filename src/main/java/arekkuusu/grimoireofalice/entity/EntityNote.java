/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

//TODO: Replace with DanmakuCore form once that is here
public class EntityNote extends EntityThrowable {

	private float ticksInAir; //TODO: Use and AT to get access to this field

	public EntityNote(World world){ super(world);}

	public EntityNote(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
		Vec3d look = throwerIn.getLookVec();
		look.rotateYaw(45);
		float distance = 1.5F;
		double dx = throwerIn.posX + (look.xCoord * distance);
		double dy = throwerIn.posY + throwerIn.getEyeHeight();
		double dz = throwerIn.posZ + (look.zCoord * distance);
		setPosition(dx, dy, dz);
    }

	@Override
	protected void entityInit() {
		
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		worldObj.spawnParticle(EnumParticleTypes.NOTE, posX + 0.5, posY, posZ + 0.5, 0.0D, 0.0D, 0.0D);
		if (rand.nextInt(8) == 4) {
			worldObj.playSound(null, posX, posY, posZ, SoundEvents.BLOCK_NOTE_HARP, SoundCategory.RECORDS, 0.5F, 1F);
		}
		int timeLive = 50;
		if(this.ticksInAir >= timeLive && !worldObj.isRemote){
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
			result.entityHit.attackEntityFrom(DamageSource.magic, 3);
		}
		if(!worldObj.isRemote) {
			setDead();
		}
	}
}
