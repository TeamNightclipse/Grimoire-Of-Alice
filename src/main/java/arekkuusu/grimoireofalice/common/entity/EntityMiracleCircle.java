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

public class EntityMiracleCircle extends Entity {

	@CapabilityInject(IItemHandler.class)
	private static final Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;

	private EntityPlayer user;
	private ItemStack stack;
	private int charge;

	private int circles;
	private float turnAngle;

	public EntityMiracleCircle(World world) {
		super(world);
		isImmuneToFire = true;
	}

	public EntityMiracleCircle(World world, EntityPlayer user, ItemStack stack) {
		super(world);
		Vec3d vec3 = user.getLookVec();
		posX = user.posX + vec3.x * 2;
		posY = user.posY + user.getEyeHeight();
		posZ = user.posZ + vec3.z * 2;
		setPosition(posX, posY, posZ);
		this.user = user;
		this.stack = stack;
		isImmuneToFire = true;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if((user == null || user.isDead || !user.isHandActive()) && !world.isRemote) {
			if(!stack.isEmpty() && user != null) {
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
			rotationYaw = user.rotationYaw;
			rotationPitch = user.rotationPitch;

			Vec3d vec3 = getRotationVector(getVectorForRotation(0F, rotationYaw));
			posX = user.posX + vec3.x * 2;
			posY = user.posY + user.getEyeHeight();
			posZ = user.posZ + vec3.z * 2;
			setPosition(posX, posY, posZ);

			if(!world.isRemote && !stack.isEmpty() && ticksExisted % 60 == 0 && circles < 4) {
				if(getCharge() < 30) {
					if(hasFaith(user) && consumeFaith(user)) {
						charge += 5;
					}
					else if(!user.capabilities.isCreativeMode && user.getFoodStats().getFoodLevel() > 8) {
						int food = user.getFoodStats().getFoodLevel();
						user.getFoodStats().setFoodLevel(food - 5);
						charge += 1;
					}
				}
				EntityMiracleCircle miracleCircle = new EntityMiracleCircle(world, user, ItemStack.EMPTY);
				world.spawnEntity(miracleCircle);
				++circles;
			}
		}
	}

	private Vec3d getRotationVector(Vec3d vec) {
		float angleYaw = turnAngle / 180.0F * (float) Math.PI;
		return vec.rotateYaw(angleYaw);
	}

	private void addCharge(int charge) {
		NBTTagCompound nbt = stack.getTagCompound();
		if(nbt == null) {
			nbt = new NBTTagCompound();
			stack.setTagCompound(nbt);
		}
		nbt.setInteger("GoheiCharge", Math.min(30, getCharge() + charge));
	}

	private int getCharge() {
		NBTTagCompound nbt = stack.getTagCompound();
		return nbt == null ? 0 : nbt.getInteger("GoheiCharge");
	}

	private static boolean hasFaith(EntityPlayer player) {
		//noinspection ConstantConditions
		return player.inventory.hasItemStack(new ItemStack(ModItems.FAITH));
	}

	@SuppressWarnings("ConstantConditions")
	private static boolean consumeFaith(EntityPlayer player) {
		if(player.hasCapability(ITEM_HANDLER_CAPABILITY, null)) {
			IItemHandler capability = player.getCapability(ITEM_HANDLER_CAPABILITY, null);

			for(int i = 0; i < capability.getSlots(); ++i) {
				ItemStack stack = capability.getStackInSlot(i);
				if(!stack.isEmpty() && stack.getItem() == ModItems.FAITH) {
					if(!player.capabilities.isCreativeMode) {
						capability.extractItem(i, 1, false);
					}
					return true;
				}
			}
		}
		return false;
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
