package arekkuusu.grimoireofalice.entity;

import arekkuusu.grimoireofalice.GrimoireOfAlice;
import arekkuusu.grimoireofalice.client.fx.ParticleFX;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityMiracleLantern extends EntityThrowable {

	public EntityMiracleLantern(World worldIn) {
		super(worldIn);
	}

	public EntityMiracleLantern(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityMiracleLantern(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
		Vec3d look = throwerIn.getLookVec();
		float distance = 2F;
		double dx = throwerIn.posX + look.xCoord * distance;
		double dy = throwerIn.posY + throwerIn.getEyeHeight() - 0.5;
		double dz = throwerIn.posZ + look.zCoord * distance;
		setPosition(dx, dy, dz);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (ticksExisted % 4 == 0) {
			for (int i = 0; i < 5; i++) {
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
		if (result.entityHit != null) result.entityHit.attackEntityFrom(DamageSource.magic, 5F);
		if (!worldObj.isRemote) setDead();
	}
}
