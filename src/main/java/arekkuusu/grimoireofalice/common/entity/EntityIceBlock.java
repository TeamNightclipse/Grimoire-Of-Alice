package arekkuusu.grimoireofalice.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityIceBlock extends Entity {

	private static final DataParameter<Float> WIDTH_PARAMETER = EntityDataManager.createKey(EntityMagicCircle.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> HEIGHT_PARAMETER = EntityDataManager.createKey(EntityMagicCircle.class, DataSerializers.FLOAT);

	private EntityLivingBase frozen;

	public EntityIceBlock(World world) {
		super(world);
		isImmuneToFire = true;
	}

	public EntityIceBlock(World world, EntityLivingBase frozen, float width, float height) {
		super(world);
		setPosition(frozen.posX, frozen.posY, frozen.posZ);
		setIceBlockWidth(width);
		setIceBlockHeight(height + 0.8F);
		this.frozen = frozen;
		preventEntitySpawning = true;
		isImmuneToFire = true;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(!world.isRemote) {
			if(ticksExisted > 150) {
				setDead();
			}
			if(ticksExisted >= 125 && getIceBlockHeight() > 0) {
				frozen.updateBlocked = false;
				setIceBlockHeight(getIceBlockHeight() - 0.1F);
				return;
			}
			if(ticksExisted < 125) {
				if(!frozen.isDead && !frozen.isBurning()) {
					frozen.updateBlocked = true;
					if(ticksExisted % 20 == 0) {
						frozen.attackEntityFrom(DamageSource.DROWN, 0.15F);
					}
				} else {
					setDead();
				}
			}
		}
	}

	@Override
	public void setDead() {
		if(world instanceof WorldServer) {
			((WorldServer) world).spawnParticle(EnumParticleTypes.SMOKE_LARGE, posX, posY, posZ, 20, 0D, 0D, 0D, 0D);
		}

		if(!world.isRemote) {
			frozen.updateBlocked = false;
		}
		frozen = null;
		super.setDead();
	}

	@Override
	public boolean canBePushed() {
		return true;
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	protected void entityInit() {
		dataManager.register(WIDTH_PARAMETER, 0F);
		dataManager.register(HEIGHT_PARAMETER, 0F);
	}

	public float getIceBlockWidth() {
		return dataManager.get(WIDTH_PARAMETER);
	}

	public void setIceBlockWidth(float iceBlockWidth) {
		dataManager.set(WIDTH_PARAMETER, iceBlockWidth);
	}

	public float getIceBlockHeight() {
		return dataManager.get(HEIGHT_PARAMETER);
	}

	public void setIceBlockHeight(float iceBlockHeight) {
		dataManager.set(HEIGHT_PARAMETER, iceBlockHeight);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {

	}
}
