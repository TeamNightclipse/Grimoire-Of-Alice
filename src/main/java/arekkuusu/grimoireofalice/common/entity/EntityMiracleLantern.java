/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.entity;

import arekkuusu.grimoireofalice.client.fx.ParticleFX;
import arekkuusu.grimoireofalice.common.GrimoireOfAlice;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityMiracleLantern extends EntityThrowable {

	public EntityMiracleLantern(World world) {
		super(world);
		isImmuneToFire = true;
	}

	public EntityMiracleLantern(World world, double x, double y, double z) {
		super(world, x, y, z);
		isImmuneToFire = true;
	}

	public EntityMiracleLantern(World world, EntityLivingBase throwerIn) {
		super(world, throwerIn);
		Vec3d look = throwerIn.getLookVec();
		float distance = 2F;
		double dx = throwerIn.posX + look.x * distance;
		double dy = throwerIn.posY + throwerIn.getEyeHeight();
		double dz = throwerIn.posZ + look.z * distance;
		setPosition(dx, dy, dz);
		isImmuneToFire = true;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(ticksExisted % 4 == 0) {
			for(int i = 0; i < 5; i++) {
				GrimoireOfAlice.proxy.sparkleFX(ParticleFX.SHINMYOUMARU_SPARKLE, null, posX, posY, posZ, 0, 0.1, 0);
			}
		}
	}

	@Override
	protected float getGravityVelocity() {
		return 0.01F;
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if(result.entityHit != null) {
			result.entityHit.attackEntityFrom(DamageSource.MAGIC, 5F);
		}
		playSound(SoundEvents.BLOCK_ANVIL_PLACE, 0.1F, 0.1F);
		if(!world.isRemote) {
			setDead();
		}
	}
}
