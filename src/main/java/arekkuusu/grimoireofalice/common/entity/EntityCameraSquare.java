/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.List;

public class EntityCameraSquare extends Entity {

	private static final DataParameter<Float> SIZE = EntityDataManager.createKey(EntityCameraSquare.class, DataSerializers.FLOAT);
	private EntityPlayer player;
	private int maxSize;

	public EntityCameraSquare(World worldIn) {
		super(worldIn);
	}

	public EntityCameraSquare(World worldIn, EntityPlayer player, int maxSize) {
		super(worldIn);
		this.player = player;
		this.maxSize = maxSize;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (player == null || player.isDead) {
			if (!worldObj.isRemote) {
				setDead();
			}
		}
		else if (!isDead) {
			if (player.isHandActive()) {
				float size = getSize();
				if (size < maxSize) {
					setSize(size + 0.1F);
				}
				if (!worldObj.isRemote) {
					for (Entity entity : getEntities()) {
						entity.motionY = entity.motionX = entity.motionZ = 0;
					}
				}
			}
			else {
				finish();
			}

			if (ticksExisted > 200 && !worldObj.isRemote) {
				finish();
			}
		}
	}

	private void finish() {
		if (!worldObj.isRemote) {
			getEntities().forEach(entity -> entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 10));
			setDead();
		}
	}

	private List<Entity> getEntities() {
		return worldObj.getEntitiesWithinAABB(Entity.class,
				getEntityBoundingBox().expandXyz(getSize() * 1.5), entity -> entity != player);
	}

	private void setSize(float size) {
		dataManager.set(SIZE, size);
	}

	public float getSize() {
		return dataManager.get(SIZE);
	}

	@Override
	protected void entityInit() { dataManager.register(SIZE, 0F); }

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {}
}
