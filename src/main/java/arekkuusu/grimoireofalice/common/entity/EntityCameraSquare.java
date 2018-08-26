/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class EntityCameraSquare extends Entity {

	private static final DataParameter<Float> SIZE = EntityDataManager.createKey(EntityCameraSquare.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> ANGLE = EntityDataManager.createKey(EntityCameraSquare.class, DataSerializers.FLOAT);
	private EntityPlayer player;
	private int maxSize;
	private float angle;

	public EntityCameraSquare(World world) {
		super(world);
	}

	public EntityCameraSquare(World world, EntityPlayer player, int maxSize, float angle) {
		super(world);
		this.player = player;
		this.maxSize = maxSize;
		this.angle = angle;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(!world.isRemote) {
			setAngle(angle);
			if(player == null || player.isDead) {
				setDead();
			} else if(player.isHandActive()) {
				setPosition();
				float size = getSize();
				if(size < maxSize) {
					setSize(size + 0.1F);
				}
				for(Entity entity : getEntities()) {
					entity.motionY = 0;
					entity.motionX = 0;
					entity.motionZ = 0;
				}
			} else {
				finish();
			}
			if(ticksExisted > 200) {
				finish();
			}
		}
	}

	public void setPosition() {
		Vec3d look = player.getLookVec();
		double distance = 4;
		double dx = player.posX + look.x * distance;
		double dy = player.posY + player.getEyeHeight() + look.y * distance;
		double dz = player.posZ + look.z * distance;
		setPosition(dx, dy, dz);
	}

	private void finish() {
		if(!world.isRemote) {
			getEntities().forEach(entity -> {
				entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 10);
			});
			setDead();
		}
	}

	private List<Entity> getEntities() {
		Vec3d look = player.getLookVec();
		double dx = look.x * getSize() * 0.5D;
		double dy = player.getEyeHeight() + look.y * getSize() * 0.5D;
		double dz = look.z * getSize() * 0.5D;
		AxisAlignedBB bb = getEntityBoundingBox().offset(dx, dy, dz);
		return world.getEntitiesWithinAABB(Entity.class, bb, entity -> entity != null && entity != player && !entity.getIsInvulnerable() && !(entity instanceof EntityHanging));
	}

	@Override
	public AxisAlignedBB getEntityBoundingBox() {
		AxisAlignedBB alignedBB = super.getEntityBoundingBox();
		double size = getSize() * 0.5D;
		return new AxisAlignedBB(alignedBB.minX + 0.1D - size, alignedBB.minY - 0.5D - size, alignedBB.minZ + 0.1D - size, alignedBB.minX + 0.5D + size, alignedBB.minY + size, alignedBB.minZ + 0.5D + size);
	}

	private void setSize(float size) {
		dataManager.set(SIZE, size);
	}

	public float getSize() {
		return dataManager.get(SIZE);
	}

	private void setAngle(float angle) {
		dataManager.set(ANGLE, angle);
	}

	public float getAngle() {
		return dataManager.get(ANGLE);
	}

	@Override
	protected void entityInit() {
		dataManager.register(SIZE, 0F);
		dataManager.register(ANGLE, 0F);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
	}
}