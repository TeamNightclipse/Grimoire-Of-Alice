package arekkuusu.grimoireofalice.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityIceBlock extends Entity {

	private static final DataParameter<Float> WIDTH = EntityDataManager.createKey(EntityMagicCircle.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> HEIGHT = EntityDataManager.createKey(EntityMagicCircle.class, DataSerializers.FLOAT);

	private EntityLivingBase frozen;

	public EntityIceBlock(World worldIn) {
		super(worldIn);
	}

	public EntityIceBlock(World worldIn, EntityLivingBase frozen, float width, float height) {
		super(worldIn);
		setPosition(frozen.posX, frozen.posY, frozen.posZ);
		setIceBlockWidth(width);
		setIceBlockHeight(height + 0.8F);
		this.frozen = frozen;
		preventEntitySpawning = true;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote) {
			if (ticksExisted > 150) {
				setDead();
			}
			if(ticksExisted >= 125 && getIceBlockHeight() > 0) {
				setIceBlockHeight(getIceBlockHeight() - 0.1F);
				return;
			}
			if(frozen != null && !frozen.isDead && !frozen.isBurning()) {
				setPosition(frozen.posX, frozen.posY, frozen.posZ);
				freezeEntity();
				if(ticksExisted % 20 == 0) {
					frozen.attackEntityFrom(DamageSource.drown, 0.15F);
				}
			}
			else {
				setDead();
			}
		}
	}

	@Override
	public void setDead() {
		if (worldObj instanceof WorldServer) {
			((WorldServer) worldObj).spawnParticle(EnumParticleTypes.SMOKE_LARGE, posX, posY, posZ, 20, 0D, 0D, 0D, 0D);
		}
		super.setDead();
	}

	private void freezeEntity() {
		frozen.setPosition(frozen.posX, frozen.posY, frozen.posZ);
		frozen.rotationYawHead = frozen.prevRotationYawHead;
		frozen.rotationPitch = frozen.prevRotationPitch;
		frozen.rotationYaw = frozen.prevRotationYaw;
		frozen.motionX = 0;

		if (!frozen.onGround) {
			frozen.motionY = 0;
		}
		frozen.motionZ = 0;

		frozen.setAir(0);
		frozen.ticksExisted--;
		frozen.fallDistance = 0;

		if(frozen instanceof EntityPlayerMP) {
			EntityPlayerMP playerMP = (EntityPlayerMP) frozen;
			playerMP.setPositionAndUpdate(playerMP.prevPosX, playerMP.prevPosY, playerMP.prevPosZ);
		}
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
		dataManager.register(WIDTH, 0F);
		dataManager.register(HEIGHT, 0F);
	}

	public float getIceBlockWidth() {
		return dataManager.get(WIDTH);
	}

	public void setIceBlockWidth(float iceBlockWidth) {
		dataManager.set(WIDTH, iceBlockWidth);
	}

	public float getIceBlockHeight() {
		return dataManager.get(HEIGHT);
	}

	public void setIceBlockHeight(float iceBlockHeight) {
		dataManager.set(HEIGHT, iceBlockHeight);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {

	}
}
