/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityGrimoireSpell extends Entity {

	private static final DataParameter<Integer> TIME = EntityDataManager.createKey(EntityMagicCircle.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> END_TIME = EntityDataManager.createKey(EntityMagicCircle.class, DataSerializers.VARINT);
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

	public EntityGrimoireSpell(World world){
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
    	this.setPositionAndRotation(posX, posY, posZ, host.rotationYaw, host.rotationPitch);
	}

	@Override
    public void onUpdate() {
    	super.onUpdate();
		if(host != null) {
			if(host.isDead){
				setDead();
			}
			if (ticksExisted > getEndTime()  && !worldObj.isRemote) {
				setDead();
				return;
			}
			posX = host.posX;
			posY = host.posY + 0.1D;
			posZ = host.posZ;
			rotationYaw = host.rotationYawHead;
			rotationPitch = host.rotationPitch;
			setPosition(posX, posY, posZ);

			if (rotationYaw > 180F) rotationYaw -= 360F;
			if (rotationYaw < -180F) rotationYaw += 360F;
			if (rotationPitch > 180F) rotationPitch -= 360F;
			if (rotationPitch < -180F) rotationPitch += 360F;

			setRotation(rotationYaw, rotationPitch);

			bookPart();
		} else if (!worldObj.isRemote) {
			setDead();
		}
    }

    private void bookPart() {
		float bookSpreadPrev = this.bookSpread;
		float bookRotationPrev = this.bookRotation;
		double d0 = host.posX - (double) ((float) this.posX + 0.5F);
		double d1 = host.posZ - (double) ((float) this.posZ + 0.5F);
		float tRot = (float) MathHelper.atan2(d1, d0);
		this.bookSpread += 0.1F;

		if (this.bookSpread < 0.5F || rand.nextInt(40) == 0) {
			float f1 = this.flipT;

			while (true) {
				this.flipT += (float) (rand.nextInt(4) - rand.nextInt(4));

				if (f1 != this.flipT) {
					break;
				}
			}
		} else {
			tRot += 0.02F;
			this.bookSpread -= 0.1F;
		}

		while (this.bookRotation >= (float) Math.PI) {
			this.bookRotation -= ((float) Math.PI * 2F);
		}

		while (this.bookRotation < -(float) Math.PI) {
			this.bookRotation += ((float) Math.PI * 2F);
		}

		while (tRot >= (float) Math.PI) {
			tRot -= ((float) Math.PI * 2F);
		}

		while (tRot < -(float) Math.PI) {
			tRot += ((float) Math.PI * 2F);
		}

		float f2;

		for (f2 = tRot - this.bookRotation; f2 >= (float) Math.PI; f2 -= ((float) Math.PI * 2F)) {
			;
		}

		while (f2 < -(float) Math.PI) {
			f2 += ((float) Math.PI * 2F);
		}

		this.bookRotation += f2 * 0.4F;
		this.bookSpread = MathHelper.clamp_float(this.bookSpread, 0.0F, 1.0F);
		++this.tickCount;
		float pageFlipPrev = this.pageFlip;
		float f = (this.flipT - this.pageFlip) * 0.4F;
		float f3 = 0.2F;
		f = MathHelper.clamp_float(f, -0.2F, 0.2F);
		this.flipA += (f - this.flipA) * 0.9F;
		this.pageFlip += this.flipA;
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
		dataManager.register(TIME, 0);
		dataManager.register(END_TIME, 0);
		dataManager.register(BOOK_PAGE_FLIP, 0F);
		dataManager.register(BOOK_PAGE_FLIP_PREV, 0F);
		dataManager.register(BOOK_ROTATION, 0F);
		dataManager.register(BOOK_ROTATION_PREV, 0F);
		dataManager.register(BOOK_SPREAD, 0F);
		dataManager.register(BOOK_SPREAD_PREV, 0F);
	}

	private void setEndTime(int time) {
		dataManager.set(END_TIME, time);
	}

	private void setTickCount(int tick) {
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

	private int getEndTime() {
		return dataManager.get(END_TIME);
	}

	public int getTickCount(){
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
