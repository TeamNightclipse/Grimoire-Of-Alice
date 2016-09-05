package arekkuusu.grimoireofalice.entity;

import static arekkuusu.grimoireofalice.entity.EntityThrow.PickupMode.*;

import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public abstract class EntityThrow extends EntityThrowable {

	public enum PickupMode {
		NO_PICKUP,
		PICKUP_ALL,
		PICKUP_CREATIVE,
		PICKUP_OWNER
	}

	private static final DataParameter<Boolean> CRITICAL = EntityDataManager.createKey(EntityThrow.class, DataSerializers.BOOLEAN);

	private PickupMode canBePickedUp = NO_PICKUP;

	public EntityThrow(World world) {
		super(world);
	}

	public EntityThrow(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityThrow(World world, EntityLivingBase thrower) {
		super(world, thrower);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(CRITICAL, false);
	}

	public boolean getCritical() {
		return dataManager.get(CRITICAL);
	}

	public void setCritical(boolean flag) {
		dataManager.set(CRITICAL, flag);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if(result.typeOfHit == RayTraceResult.Type.BLOCK) {
			onImpactBlock(result);
		}
		else if(result.typeOfHit == RayTraceResult.Type.ENTITY) {
			onImpactEntity(result);
		}
	}

	protected void onImpactBlock(RayTraceResult result) {
		BlockPos pos = result.getBlockPos();
		IBlockState tile = worldObj.getBlockState(pos);
		motionX = result.hitVec.xCoord - posX;
		motionY = result.hitVec.yCoord - posY;
		motionZ = result.hitVec.zCoord - posZ;
		double velocity = Math.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ);
		posX -= motionX / velocity * 0.05D;
		posY -= motionY / velocity * 0.05D;
		posZ -= motionZ / velocity * 0.05D;
		inGround = true;
		setCritical(false);
		throwableShake = getMaxShake();

		if (tile != Blocks.AIR.getDefaultState()) {
			tile.getBlock().onEntityCollidedWithBlock(worldObj, pos, tile, this);
		}
	}

	protected void onImpactEntity(RayTraceResult result) {
		bounceBack();
		if(result.entityHit != null) {
			applyHitEffects(result.entityHit);
		}
	}

	public void applyHitEffects(Entity entity) {
		if (isBurning() && !(entity instanceof EntityEnderman)) {
			entity.setFire(5);
		}

		if (entity instanceof EntityLivingBase) {
			EntityLivingBase entityliving = (EntityLivingBase) entity;
			if (knockbackStrength > 0) {
				double speed = Math.sqrt(motionX * motionX + motionZ * motionZ);
				if (speed > 0.0F) {
					entity.addVelocity(motionX * knockbackStrength * 0.6D / speed, 0.1D, motionZ * knockbackStrength * 0.6D / speed);
				}
			}
			EntityLivingBase thrower = getThrower();
			if (thrower != null) {
				EnchantmentHelper.applyThornEnchantments(entityliving, thrower);
				EnchantmentHelper.applyArthropodEnchantments(thrower, entityliving);

				if (thrower instanceof EntityPlayerMP && thrower != entity && entity instanceof EntityPlayer) {
					((EntityPlayerMP) thrower).connection.sendPacket(new SPacketChangeGameState(6, 0));
				}
			}
		}
	}

	@Override
	protected float getGravityVelocity() {
		double speed = Math.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ);
		if(speed < gravityThreshold()) {
			return super.getGravityVelocity();
		}
		else return 0F;
	}

	protected abstract double gravityThreshold();

	protected void setPickupModeFromEntity(EntityLivingBase entityliving) {
		if (entityliving instanceof EntityPlayer) {
			if (((EntityPlayer) entityliving).capabilities.isCreativeMode) {
				canBePickedUp(PICKUP_CREATIVE);
			} else {
				canBePickedUp(PICKUP_OWNER);
			}
		} else {
			canBePickedUp(NO_PICKUP);
		}
	}

	protected void bounceBack() {
		motionX *= -0.1D;
		motionY *= -0.1D;
		motionZ *= -0.1D;
		rotationYaw += 180F;
		prevRotationYaw += 180F;
	}

	public boolean canPickup(EntityPlayer entityplayer) {
		if (canBePickedUp == PICKUP_ALL) {
			return true;
		} else if(canBePickedUp == PICKUP_CREATIVE) {
			return entityplayer.capabilities.isCreativeMode;
		}
		else return canBePickedUp == PICKUP_OWNER && entityplayer == getThrower();
	}

	public void setPickupMode(PickupMode i) {
		canBePickedUp = i;
	}

	public PickupMode getPickupMode() {
		return canBePickedUp;
	}

	private void canBePickedUp(PickupMode canI){
		canBePickedUp = canI;
	}

	public int getMaxShake() {
		return 0;
	}
}
