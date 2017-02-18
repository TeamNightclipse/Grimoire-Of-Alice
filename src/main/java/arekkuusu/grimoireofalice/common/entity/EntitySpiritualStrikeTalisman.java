package arekkuusu.grimoireofalice.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.List;

public class EntitySpiritualStrikeTalisman extends Entity {

	private EntityLivingBase living;

	public EntitySpiritualStrikeTalisman(World worldIn) {
		super(worldIn);
		ignoreFrustumCheck = true;
		preventEntitySpawning = true;
	}

	public EntitySpiritualStrikeTalisman(World worldIn, EntityLivingBase living) {
		super(worldIn);
		this.living = living;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote) {
			if (ticksExisted < 24) {
				List<Entity> list = worldObj.getEntitiesInAABBexcluding(this, new AxisAlignedBB(living.getPosition()).expandXyz(5), entity -> entity != living);
				list.forEach(entity -> {
					Vec3d vec = getPositionVector();
					Vec3d mobPos = entity.getPositionVector();
					if(vec.distanceTo(mobPos) < 5) {
						double ratio = vec.distanceTo(mobPos) / 5;
						double scaling = 1 - ratio;
						Vec3d motion = vec.subtract(mobPos).scale(scaling);
						entity.motionX = -motion.xCoord * 2;
						entity.motionY = .3F;
						entity.motionZ = -motion.zCoord * 2;
					}
				});
			}
			else {
				if (worldObj instanceof WorldServer) {
					((WorldServer) worldObj).spawnParticle(EnumParticleTypes.SMOKE_NORMAL, posX, posY, posZ, 30, 0D, 0D, 0D, 0.05D);
				}
				setDead();
			}
		}
	}

	@Override
	protected void entityInit() {

	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {

	}
}
