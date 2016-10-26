package arekkuusu.grimoireofalice.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class EntityBarrier extends Entity {

	private EntityPlayer player;
	private boolean isStatic = false;
	private byte type;

	public EntityBarrier(World world) {
		super(world);
	}

	public EntityBarrier(World world, EntityPlayer player, byte type) {
		super(world);
		this.player = player;
		this.type = type;
		Vec3d look = player.getLookVec();
		float distance = 2F;
		double dx = player.posX + (look.xCoord * distance);
		double dy = player.posY + 1 + (look.yCoord * distance);
		double dz = player.posZ + (look.zCoord * distance);
		setPosition(dx, dy, dz);
		setNoGravity(true);
	}

	@Override
	protected void entityInit() {}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (player == null) {
			if (!worldObj.isRemote) {
				setDead();
			}
		} else {
			if (player.isHandActive() && !isStatic) {
				Vec3d look = player.getLookVec();
				float distance = 2F;
				double dx = player.posX + (look.xCoord * distance);
				double dy = player.posY + 1 + (look.yCoord * distance);
				double dz = player.posZ + (look.zCoord * distance);
				setPosition(dx, dy, dz);
				setRotation(player.rotationYaw, player.rotationPitch);
			} else if (!isStatic) {
				isStatic = true;
			}

			@SuppressWarnings("ConstantConditions") Entity entity = worldObj.getEntitiesInAABBexcluding(this, getEntityBoundingBox(),
					entity1 -> entity1.canBeCollidedWith() || entity1 != player).get(0);

			if (entity != null) {
				onDetectEntity(entity);
			}

			if (ticksExisted > 200) {
				if (!worldObj.isRemote) {
					setDead();
				}
			}
		}
	}

	private void onDetectEntity(Entity living) {
		if (type == 3) {
			worldObj.createExplosion(living, living.posX, living.posY + 1, living.posZ, 2.5F, false);
			if (!worldObj.isRemote) {
				setDead();
			}
		} else if (type == 4){
			Vec3d playerPos = getPositionVector();
			Vec3d mobPos = living.getPositionVector();
			double ratio = playerPos.distanceTo(mobPos) / 4;
			double scaling = (1 - ratio);
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
	public AxisAlignedBB getEntityBoundingBox() {
		return super.getEntityBoundingBox().expand(2,2,0);
	}
}
