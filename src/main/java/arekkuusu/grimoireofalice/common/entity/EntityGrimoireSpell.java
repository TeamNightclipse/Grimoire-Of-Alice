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
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityGrimoireSpell extends Entity {

	private static final DataParameter<Float> TIME = EntityDataManager.createKey(EntityMagicCircle.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> END_TIME = EntityDataManager.createKey(EntityMagicCircle.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> BOOK_PAGE_FLIP = EntityDataManager.createKey(EntityMagicCircle.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> BOOK_PAGE_FLIP_PREV = EntityDataManager.createKey(EntityMagicCircle.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> BOOK_SPREAD = EntityDataManager.createKey(EntityMagicCircle.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> BOOK_SPREAD_PREV = EntityDataManager.createKey(EntityMagicCircle.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> BOOK_ROTATION = EntityDataManager.createKey(EntityMagicCircle.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> BOOK_ROTATION_PREV = EntityDataManager.createKey(EntityMagicCircle.class, DataSerializers.FLOAT);
	private EntityPlayer host;
	private int tickCount;
	private float pageFlip;
	private float flipT;
	private float flipA;
	private float bookSpread;
	private float bookRotation;

	public EntityGrimoireSpell(World world) {
		super(world);
	}

	public EntityGrimoireSpell(World worldIn, EntityPlayer entityLiving, int end) {
		super(worldIn);
		setSize(0.5F, 0.5F);
		setEndTime(end);
		host = entityLiving;
		posX = host.posX;
		posY = host.posY + 0.1D;
		posZ = host.posZ;
		setPosition(posX, posY, posZ);
		setRotation(host.rotationYaw, 0);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(!worldObj.isRemote) {
			if(host != null) {
				if(host.isDead || ticksExisted > getEndTime()) {
					setDead();
					return;
				}

				posX = host.posX;
				posY = host.posY + 0.1D;
				posZ = host.posZ;
				rotationYaw = host.rotationYawHead;
				rotationPitch = host.rotationPitch;
				setPosition(posX, posY, posZ);

				if(rotationYaw > 180F) {
					rotationYaw -= 360F;
				}
				if(rotationYaw < -180F) {
					rotationYaw += 360F;
				}
				if(rotationPitch > 180F) {
					rotationPitch -= 360F;
				}
				if(rotationPitch < -180F) {
					rotationPitch += 360F;
				}

				setRotation(rotationYaw, rotationPitch);

				bookPart();
			}
			else {
				setDead();
			}
		}
	}

	private void bookPart() {
		float bookSpreadPrev = bookSpread;
		float bookRotationPrev = bookRotation;
		double d0 = host.posX - (posX + 0.5F);
		double d1 = host.posZ - (posZ + 0.5F);
		float tRot = (float)MathHelper.atan2(d1, d0);
		bookSpread += 0.1F;

		if(bookSpread < 0.5F || rand.nextInt(40) == 0) {
			float f1 = flipT;

			while(f1 != flipT) {
				flipT += rand.nextInt(4) - rand.nextInt(4);
			}
		}
		else {
			tRot += 0.02F;
			bookSpread -= 0.1F;
		}

		while(bookRotation >= (float)Math.PI) {
			bookRotation -= (float)Math.PI * 2F;
		}

		while(bookRotation < -(float)Math.PI) {
			bookRotation += (float)Math.PI * 2F;
		}

		while(tRot >= (float)Math.PI) {
			tRot -= (float)Math.PI * 2F;
		}

		while(tRot < -(float)Math.PI) {
			tRot += (float)Math.PI * 2F;
		}

		float f2 = tRot - bookRotation;
		while(f2 >= (float)Math.PI) {
			f2 -= (float)Math.PI * 2F;
		}

		while(f2 < -(float)Math.PI) {
			f2 += (float)Math.PI * 2F;
		}

		bookRotation += f2 * 0.4F;
		bookSpread = MathHelper.clamp_float(bookSpread, 0.0F, 1.0F);
		++tickCount;
		float pageFlipPrev = pageFlip;
		float f = (flipT - pageFlip) * 0.4F;
		f = MathHelper.clamp_float(f, -0.2F, 0.2F);
		flipA += (f - flipA) * 0.9F;
		pageFlip += flipA;
		setTickCount(tickCount);
		setPageFlip(pageFlip);
		setPageFlipPrev(pageFlipPrev);
		setBookSpread(bookSpread);
		setBookSpreadPrev(bookSpreadPrev);
		setBookRotation(bookRotation);
		setBookRotationPrev(bookRotationPrev);
	}

	@Override
	protected void entityInit() {
		dataManager.register(TIME, 0F);
		dataManager.register(END_TIME, 0F);
		dataManager.register(BOOK_PAGE_FLIP, 0F);
		dataManager.register(BOOK_PAGE_FLIP_PREV, 0F);
		dataManager.register(BOOK_ROTATION, 0F);
		dataManager.register(BOOK_ROTATION_PREV, 0F);
		dataManager.register(BOOK_SPREAD, 0F);
		dataManager.register(BOOK_SPREAD_PREV, 0F);
	}

	private void setEndTime(float time) {
		dataManager.set(END_TIME, time);
	}

	private void setTickCount(float tick) {
		dataManager.set(TIME, tick);
	}

	private void setPageFlip(float pageFlip) {
		dataManager.set(BOOK_PAGE_FLIP, pageFlip);
	}

	private void setPageFlipPrev(float pageFlipPrev) {
		dataManager.set(BOOK_PAGE_FLIP_PREV, pageFlipPrev);
	}

	private void setBookSpread(float bookSpread) {
		dataManager.set(BOOK_SPREAD, bookSpread);
	}

	private void setBookSpreadPrev(float bookSpreadPrev) {
		dataManager.set(BOOK_SPREAD_PREV, bookSpreadPrev);
	}

	private void setBookRotation(float bookRotation) {
		dataManager.set(BOOK_ROTATION, bookRotation);
	}

	private void setBookRotationPrev(float bookRotationPrev) {
		dataManager.set(BOOK_ROTATION_PREV, bookRotationPrev);
	}

	private float getEndTime() {
		return dataManager.get(END_TIME);
	}

	public float getTickCount() {
		return dataManager.get(TIME);
	}

	public float getPageFlip() {
		return dataManager.get(BOOK_PAGE_FLIP);
	}

	public float getPageFlipPrev() {
		return dataManager.get(BOOK_PAGE_FLIP_PREV);
	}

	public float getBookSpread() {
		return dataManager.get(BOOK_SPREAD);
	}

	public float getBookSpreadPrev() {
		return dataManager.get(BOOK_SPREAD_PREV);
	}

	public float getBookRotation() {
		return dataManager.get(BOOK_ROTATION);
	}

	public float getBookRotationPrev() {
		return dataManager.get(BOOK_ROTATION_PREV);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {

	}

}
