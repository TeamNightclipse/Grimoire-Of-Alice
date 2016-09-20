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
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityWind extends EntityThrowable {

    private float ticksInAir; //TODO: Use and AT to get access to this field
    private int timeUsed;

    public EntityWind(World worldIn) {
        super(worldIn);
    }

    public EntityWind(World worldIn, EntityLivingBase throwerIn, int timeUsed) {
        super(worldIn, throwerIn);
        this.timeUsed = timeUsed;
    }

    @Override
    protected void entityInit() {}

    @Override
    public void onUpdate() {
        super.onUpdate();
        if(!worldObj.isRemote) {
			if(isInWater() || isInLava()) {
				setDead();
			}
		}
        if (rand.nextInt(4) == 2) {
            worldObj.spawnParticle(EnumParticleTypes.CLOUD, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
            worldObj.playSound(null, posX, posY, posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 0.5F, 1F);
        }

		if(!worldObj.isRemote) {
			int timeLive = 50;
			if(this.ticksInAir >= timeLive){
				setDead();
			}
		}

        ++this.ticksInAir;
    }

    @Override
    public float getGravityVelocity() {
        return 0F;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
		if(!worldObj.isRemote) {
			if(result.entityHit instanceof EntityLiving){
				result.entityHit.attackEntityFrom(DamageSource.magic, timeUsed / 2);
				Vec3d windPos = this.getPositionVector();
				Vec3d mobPos = result.entityHit.getPositionVector();
				double ratio = windPos.distanceTo(mobPos) / 4;
				double scaling = (1 - ratio);
				Vec3d motion = windPos.subtract(mobPos).scale(scaling);
				result.entityHit.motionX = -motion.xCoord * timeUsed / 2;
				result.entityHit.motionY = this.motionY;
				result.entityHit.motionZ = -motion.zCoord * timeUsed / 2;
			}
			setDead();
		}
    }

	public float getTicksInAir() {
		return ticksInAir;
	}
}
