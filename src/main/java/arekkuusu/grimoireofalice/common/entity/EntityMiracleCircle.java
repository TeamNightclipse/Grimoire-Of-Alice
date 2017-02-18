package arekkuusu.grimoireofalice.common.entity;

import arekkuusu.grimoireofalice.common.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityMiracleCircle extends Entity {

	@CapabilityInject(IItemHandler.class)
	private static final Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;

	private EntityPlayer user;
	private ItemStack stack;
	private int charge;

	private int circles;
	public float turnAngle;

	public EntityMiracleCircle(World worldIn) {
		super(worldIn);
	}

	public EntityMiracleCircle(World worldIn, EntityPlayer user, @Nullable ItemStack stack) {
		super(worldIn);
		Vec3d vec3 = user.getLookVec();
		posX = user.posX + vec3.xCoord * 2;
		posY = user.posY + vec3.yCoord + user.getEyeHeight();
		posZ = user.posZ + vec3.zCoord * 2;
		setPosition(posX, posY, posZ);
		this.user = user;
		this.stack = stack;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if((user == null || user.isDead || !user.isHandActive()) && !worldObj.isRemote) {
			if(stack != null && user != null) {
				user.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, 0.2F, 0.1F);
				if(getCharge() < 30) {
					addCharge(charge);
				}
			}
			setDead();
		}

		if(ticksExisted > 30) {
			turnAngle += 1.2F;
		}

		if(user != null) {
			Vec3d vec3 = getRotationVectorFromAngle(user.rotationYaw, user.rotationPitch);

			rotationYaw = user.rotationYaw;
			rotationPitch = user.rotationPitch;

			posX = user.posX + vec3.xCoord * 2;
			posY = user.posY + vec3.yCoord + (double) user.getEyeHeight();
			posZ = user.posZ + vec3.zCoord * 2;
			setPosition(posX, posY, posZ);

			if(!worldObj.isRemote && stack != null && ticksExisted % 60 == 0 && circles < 4) {
				if(getCharge() < 30) {
					if (hasFaith(user) && consumeFaith(user)) {
						charge += 5;
					}
					else if (!user.capabilities.isCreativeMode && user.getFoodStats().getFoodLevel() > 10) {
						int food = user.getFoodStats().getFoodLevel();
						user.getFoodStats().setFoodLevel(food - 5);
						charge += 1;
					}
				}
				EntityMiracleCircle miracleCircle = new EntityMiracleCircle(worldObj, user, null);
				worldObj.spawnEntityInWorld(miracleCircle);
				++circles;
			}
		}
	}

	private void addCharge(int charge) {
		NBTTagCompound nbt = stack.getTagCompound();
		if (nbt == null) {
			nbt = new NBTTagCompound();
			stack.setTagCompound(nbt);
		}
		nbt.setInteger("GoheiCharge", Math.min(30, getCharge() + charge));
	}

	private int getCharge() {
		NBTTagCompound nbt = stack.getTagCompound();
		return nbt == null ? 0 : nbt.getInteger("GoheiCharge");
	}

	private boolean hasFaith(EntityPlayer player) {
		//noinspection ConstantConditions
		return player.inventory.hasItemStack(new ItemStack(ModItems.FAITH));
	}

	@SuppressWarnings("ConstantConditions")
	private boolean consumeFaith(EntityPlayer player) {
		if (player.hasCapability(ITEM_HANDLER_CAPABILITY, null)) {
			IItemHandler capability = player.getCapability(ITEM_HANDLER_CAPABILITY, null);

			for (int i = 0; i < capability.getSlots(); ++i) {
				ItemStack stack = capability.getStackInSlot(i);
				if (stack != null && stack.getItem() == ModItems.FAITH && stack.stackSize > 0) {
					if (!player.capabilities.isCreativeMode) {
						capability.extractItem(i, 1, false);
					}
					return true;
				}
			}
		}
		return false;
	}

	@Nonnull
	private Vec3d getRotationVectorFromAngle(float yaw, float pitch) {
		Vec3d vec3 = getVecFromAngle(yaw, pitch);
		return getRotationVector(vec3.xCoord, vec3.yCoord, vec3.zCoord);
	}

	private Vec3d getRotationVector(double vectorX, double vectorY, double vectorZ) {
		float disXZ = (float) Math.sqrt(vectorX * vectorX + vectorZ * vectorZ);
		float angleYaw = (float) Math.atan2(-vectorX, vectorZ);
		float anglePitch = (float) Math.atan2(-vectorY, disXZ);
		float baseYaw = angleYaw;
		float basePitch = anglePitch;
		double X1, X2, Z1, Z2, vecX, vecY, vecZ;
		float anglePitch_rad;
		Vec3d lookAt = new Vec3d(vectorX, vectorY, vectorZ);
		lookAt.rotateYaw((float) Math.PI * 2);

		angleYaw = turnAngle / 180.0F * (float) Math.PI;
		anglePitch = 0;
		anglePitch_rad = (float) (anglePitch / 180.0D * Math.PI);

		X1 = Math.sin(angleYaw) * Math.cos(baseYaw);
		Z1 = Math.sin(angleYaw) * Math.sin(baseYaw);
		X2 = Math.cos(angleYaw) * Math.sin(anglePitch_rad) * Math.sin(basePitch) * Math.sin(baseYaw);
		Z2 = Math.cos(angleYaw) * Math.sin(anglePitch_rad) * Math.sin(basePitch) * Math.cos(baseYaw);

		vecY = -Math.cos(angleYaw) * Math.sin(basePitch - anglePitch_rad);
		vecX = Math.cos(angleYaw) * Math.cos(anglePitch_rad) * lookAt.xCoord + X1 - X2;
		vecZ = Math.cos(angleYaw) * Math.cos(anglePitch_rad) * lookAt.zCoord + Z1 + Z2;

		return new Vec3d(vecX, vecY, vecZ);
	}

	private Vec3d getVecFromAngle(float yaw, float pitch) {
		double yaw_rad = (double) yaw / 180.0D * Math.PI;
		double pitch_rad = (double) pitch / 180.0D * Math.PI;
		double vectorX = -Math.sin(yaw_rad) * Math.cos(pitch_rad);
		double vectorY = -Math.sin(pitch_rad);
		double vectorZ = Math.cos(yaw_rad) * Math.cos(pitch_rad);
		return new Vec3d(vectorX, vectorY, vectorZ);
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
