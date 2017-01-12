/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityBarrier extends Entity {

	private EntityPlayer player;
	private boolean isStatic = false;
	private Barrier type;

	public EntityBarrier(World world) {
		super(world);
	}

	public EntityBarrier(World world, EntityPlayer player, byte type) {
		super(world);
		this.player = player;
		this.type = Barrier.fromType(type);
		Vec3d look = player.getLookVec();
		float distance = 4F;
		double dx = player.posX + look.xCoord * distance;
		double dy = player.posY + 1 + look.yCoord * distance;
		double dz = player.posZ + look.zCoord * distance;
		setPosition(dx, dy, dz);
		setNoGravity(true);
	}

	@Override
	protected void entityInit() {}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(player == null) {
			if(!worldObj.isRemote) {
				setDead();
			}
		}
		else {
			if(player.isHandActive() && !isStatic) {
				Vec3d look = player.getLookVec();
				float distance = 4F;
				double dx = player.posX + look.xCoord * distance;
				double dy = player.posY + 1 + look.yCoord * distance;
				double dz = player.posZ + look.zCoord * distance;
				setPosition(dx, dy, dz);
				setRotation(player.rotationYaw, player.rotationPitch);
			}
			else if(!isStatic) {
				isStatic = true;
			}

			List<Entity> entities = worldObj.getEntitiesInAABBexcluding(this, getEntityBoundingBox(),
					entity1 -> entity1 != null && !(entity1 instanceof EntityBarrier) && entity1.canBeCollidedWith() && entity1 != player);

			if(!entities.isEmpty()) {
				onDetectEntity(entities.get(0));
			}

			if(ticksExisted > 200 && !worldObj.isRemote) {
				setDead();
			}
		}
	}

	private void onDetectEntity(Entity living) {
		if(type == Barrier.EXPLODE) {
			worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, posX + 0.5, posY + 0.5, posZ + 0.5, motionX, motionY, motionZ);
			playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1F, rand.nextFloat() * 1.0F + 0.8F);

			if (!worldObj.isRemote) {
				living.attackEntityFrom(DamageSource.causeExplosionDamage(player), 5);
				setDead();
			}
		}
		else if(type == Barrier.MOTION) {
			Vec3d playerPos = getPositionVector();
			Vec3d mobPos = living.getPositionVector();
			double ratio = playerPos.distanceTo(mobPos) / 4;
			double scaling = 1 - ratio;
			Vec3d motion = playerPos.subtract(mobPos).scale(scaling);
			living.motionX = -motion.xCoord * 2;
			living.motionY = .3F;
			living.motionZ = -motion.zCoord * 2;
		}
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbtTagCompound) {}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbtTagCompound) {}

	@Override
	public Vec3d getLookVec() {
		return this.getLook(1.0F);
	}

	@Override
	public Vec3d getLook(float partialTicks) {
		if (partialTicks == 1.0F) {
			return this.getVectorForRotation(this.rotationPitch, this.rotationYaw);
		} else {
			float f = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * partialTicks;
			float f1 = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * partialTicks;
			return this.getVectorForRotation(f, f1);
		}
	}

	@Override
	public AxisAlignedBB getEntityBoundingBox() {
		AxisAlignedBB alignedBB = super.getEntityBoundingBox();
		Vec3d vec = getLookVec();
		return new AxisAlignedBB(alignedBB.minX - 1, alignedBB.minY - 1, alignedBB.minZ - 1, alignedBB.minX + 1.6, alignedBB.minY + 1.6, alignedBB.minZ + 1.6).offset(vec.xCoord, vec.yCoord, vec.zCoord);
	}

	public enum Barrier {

		EXPLODE,
		MOTION,
		NOTHING;

		public static Barrier fromType(byte type) {
			switch (type) {
				case 3:
					return EXPLODE;
				case 4:
					return MOTION;
				default:
					return NOTHING;
			}
		}
	}
}
