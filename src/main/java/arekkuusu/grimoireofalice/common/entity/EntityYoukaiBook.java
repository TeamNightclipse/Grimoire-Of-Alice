package arekkuusu.grimoireofalice.common.entity;

import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

import javax.annotation.Nullable;

public class EntityYoukaiBook extends EntityLiving implements IAnimals {

	private static final DataParameter<Byte> LAYING = EntityDataManager.<Byte>createKey(EntityYoukaiBook.class, DataSerializers.BYTE);
	private BlockPos spawnPosition;

	public EntityYoukaiBook(World world) {
		super(world);
		setSize(0.5F, 0.5F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(LAYING, (byte) 0);
	}

	@Override
	protected float getSoundVolume() {
		return 0.1F;
	}

	@Override
	protected float getSoundPitch() {
		return super.getSoundPitch() * 0.95F;
	}

	@Override
	@Nullable
	protected SoundEvent getAmbientSound() {
		return this.getIsBookLaying() && this.rand.nextInt(2) != 0 ? null : GrimoireSoundEvents.PAGE_TURN;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource) {
		return GrimoireSoundEvents.PAGE_TURN;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return GrimoireSoundEvents.PAGE_TURN;
	}

	@Override
	public boolean canBePushed() {
		return true;
	}

	@Override
	public boolean canBeLeashedTo(EntityPlayer player) {
		return true;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
	}

	public boolean getIsBookLaying() {
		return (this.dataManager.get(LAYING) & 1) != 0;
	}

	public void setIsBookLaying(boolean isBookLaying) {
		byte b0 = dataManager.get(LAYING);

		if(isBookLaying) {
			dataManager.set(LAYING, (byte) (b0 | 1));
		}
		else {
			dataManager.set(LAYING, (byte) (b0 & -2));
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if(getIsBookLaying()) {
			motionX = 0.0D;
			motionZ = 0.0D;
		}
		else {
			if(ticksExisted % 8 == 0) {
				playSound(GrimoireSoundEvents.WING_FLAP, 0.5F, getSoundPitch());
				playSound(GrimoireSoundEvents.PAGE_TURN, getSoundVolume(), getSoundPitch());
			}
			motionY *= 0.6000000238418579D;
		}

		if(world.isDaytime() && !world.isRemote) {
			float f = this.getBrightness();
			if(f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.world.canSeeSky(getPosition())) {
				setFire(2);
			}
		}
	}

	@Override
	protected void updateAITasks() {
		super.updateAITasks();
		BlockPos blockpos = new BlockPos(this).down();

		if(getIsBookLaying()) {
			if(world.getBlockState(blockpos).getMaterial() == Material.WOOD) {
				if(rand.nextInt(200) == 0) {
					rotationYawHead = (float) rand.nextInt(360);
				}

				if(this.world.getNearestPlayerNotCreative(this, 4.0D) != null) {
					setIsBookLaying(false);
					world.playEvent(null, 1025, blockpos, 0);
				}
			}
			else {
				setIsBookLaying(false);
				world.playEvent(null, 1025, blockpos, 0);
			}
		}
		else {
			if(spawnPosition != null && (!world.isAirBlock(spawnPosition) || spawnPosition.getY() < 1)) {
				spawnPosition = null;
			}

			if(spawnPosition == null || rand.nextInt(30) == 0 || spawnPosition.distanceSq((double) ((int) posX), (double) ((int) posY), (double) ((int) posZ)) < 4.0D) {
				spawnPosition = new BlockPos((int) posX + rand.nextInt(7) - rand.nextInt(7), (int) posY + rand.nextInt(6) - 2, (int) posZ + rand.nextInt(7) - rand.nextInt(7));
			}

			double d0 = (double) spawnPosition.getX() + 0.5D - posX;
			double d1 = (double) spawnPosition.getY() - posY;
			double d2 = (double) spawnPosition.getZ() + 0.5D - posZ;
			motionX += (Math.signum(d0) * 0.5D - motionX) * 0.10000000149011612D;
			motionY += (Math.signum(d1) * 0.699999988079071D - motionY) * 0.10000000149011612D;
			motionZ += (Math.signum(d2) * 0.5D - motionZ) * 0.10000000149011612D;
			float f = (float) (MathHelper.atan2(motionZ, motionX) * (180D / Math.PI)) - 90.0F;
			float f1 = MathHelper.wrapDegrees(f - rotationYaw);
			moveForward = 0.5F;
			rotationYaw += f1;

			if(rand.nextInt(100) == 0 && world.getBlockState(blockpos).getMaterial() == Material.WOOD) {
				setIsBookLaying(true);
			}
		}
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
	}

	@Override
	protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
	}

	@Override
	public boolean doesEntityNotTriggerPressurePlate() {
		return true;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if(isEntityInvulnerable(source)) {
			return false;
		}
		else {
			if(!world.isRemote && getIsBookLaying()) {
				setIsBookLaying(false);
			}

			return super.attackEntityFrom(source, amount);
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		dataManager.set(LAYING, compound.getByte("BookFlags"));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setByte("BookFlags", dataManager.get(LAYING));
	}

	@Override
	public boolean getCanSpawnHere() {
		BlockPos blockpos = new BlockPos(posX, getEntityBoundingBox().minY, posZ);

		if(world.canSeeSky(blockpos) || world.getLightFromNeighbors(blockpos) < rand.nextInt(4)) {
			return false;
		}

		BlockPos posI = new BlockPos(posX - 4, getEntityBoundingBox().minY - 4, posZ - 4);
		BlockPos posF = new BlockPos(posX + 4, getEntityBoundingBox().minY + 4, posZ + 4);
		int bookCout = 0;

		for(BlockPos pos : BlockPos.getAllInBox(posI, posF)) {
			if(world.getBlockState(pos).getBlock() == Blocks.BOOKSHELF) {
				bookCout++;
			}
		}

		return bookCout > 0 && bookCout > rand.nextInt(16) && super.getCanSpawnHere();
	}

	@Override
	public float getEyeHeight() {
		return height / 2.0F;
	}

	@Override
	@Nullable
	protected ResourceLocation getLootTable() {
		return LootTableList.ENTITIES_BAT;
	}
}
