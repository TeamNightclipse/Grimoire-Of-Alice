package arekkuusu.grimoireofalice.common.entity;

import java.util.List;

import arekkuusu.grimoireofalice.common.core.helper.MiscHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntitySpiritualStrikeTalisman extends Entity {

	private EntityLivingBase living;

	public EntitySpiritualStrikeTalisman(World world) {
		super(world);
		ignoreFrustumCheck = true;
		preventEntitySpawning = true;
		isImmuneToFire = true;
	}

	public EntitySpiritualStrikeTalisman(World world, EntityLivingBase living) {
		super(world);
		this.living = living;
		isImmuneToFire = true;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(!world.isRemote) {
			if(ticksExisted < 24) {
				List<Entity> list = world.getEntitiesInAABBexcluding(this, new AxisAlignedBB(living.getPosition()).grow(5), entity -> !living.equals(entity));
				list.forEach(entity -> {
					Vec3d vec = getPositionVector();
					Vec3d mobPos = entity.getPositionVector();
					if(vec.squareDistanceTo(mobPos) < 5 * 5) {
						MiscHelper.pushEntity(this, entity);
					}
				});
			}
			else {
				if(world instanceof WorldServer) {
					((WorldServer) world).spawnParticle(EnumParticleTypes.SMOKE_NORMAL, posX, posY, posZ, 30, 0D, 0D, 0D, 0.05D);
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
