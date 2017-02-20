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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class EntityMagicCircle extends Entity {

	private static final DataParameter<Float> SIZE = EntityDataManager.createKey(EntityMagicCircle.class, DataSerializers.FLOAT);
	private static final DataParameter<Integer> TEXTURE = EntityDataManager.createKey(EntityMagicCircle.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> ANIMATION = EntityDataManager.createKey(EntityMagicCircle.class, DataSerializers.VARINT);
	private EntityLivingBase host;
	private int last;
	private int endTime = 0;

	public EntityMagicCircle(World world) {
		super(world);
	}

	public EntityMagicCircle(World worldIn, EntityLivingBase entityLiving, EnumTextures texture, int end) {
		super(worldIn);
		setSize(0.5F, 0.5F);
		setTexture(texture.ordinal());
		endTime = end;
		host = entityLiving;
		posX = host.posX;
		posY = host.posY + 0.1D;
		posZ = host.posZ;
		setPositionAndRotation(posX, posY, posZ, host.rotationYaw, host.rotationPitch);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(!worldObj.isRemote) {
			if(host != null && !host.isDead && !host.isHandActive()) {
				setAnimationCount(last);

				if(endTime < ticksExisted && endTime >= 0) {
					if(!worldObj.isRemote) {
						setDead();
					}
				}

				if(getAnimationCount() < 5) {
					setCircleSize(getAnimationCount() / 5.0F);
				}
				else {
					float end2 = endTime;
					setCircleSize((end2 - getAnimationCount()) / end2);
				}

				rotationYaw = host.rotationYawHead;
				rotationPitch = host.rotationPitch;
				posX = host.posX;
				posY = host.posY + 0.1D;
				posZ = host.posZ;
				setPosition(posX, posY, posZ);

				while(rotationYaw > 180F) {
					rotationYaw -= 360F;
				}
				while(rotationYaw < -180F) {
					rotationYaw += 360F;
				}
				while(rotationPitch > 180F) {
					rotationPitch -= 360F;
				}
				while(rotationPitch < -180F) {
					rotationPitch += 360F;
				}

				setRotation(rotationYaw, rotationPitch);

				if(ticksExisted > last) {
					last = ticksExisted;
				}
			}
			else {
				setDead();
			}
		}
	}

	@Override
	protected void entityInit() {
		dataManager.register(SIZE, 0F);
		dataManager.register(TEXTURE, 0);
		dataManager.register(ANIMATION, 0);
	}

	private void setCircleSize(float size) {
		dataManager.set(SIZE, size);
	}

	public float getCircleSize() {
		return dataManager.get(SIZE);
	}

	private void setAnimationCount(int time) {
		dataManager.set(ANIMATION, time);
	}

	public int getAnimationCount() {
		return dataManager.get(ANIMATION);
	}

	private void setTexture(int texture) {
		dataManager.set(TEXTURE, texture);
	}

	public int getTexture() {
		return dataManager.get(TEXTURE);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {}

	public enum EnumTextures {
		RED_NORMAL,
		PURPLE_CIRCLES,
		BLUE_STAR,
		GOLD_STAR_SMALL,
		BLUE_NORMAL,
		EMPTY;
	}
}
