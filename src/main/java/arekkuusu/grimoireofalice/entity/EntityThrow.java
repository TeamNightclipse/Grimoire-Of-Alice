/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.entity;

import java.util.List;

import net.minecraftforge.fml.common.registry.IThrowableEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import static arekkuusu.grimoireofalice.entity.EntityThrow.PickupMode.*;

public class EntityThrow extends EntityArrow implements IThrowableEntity {

	public enum PickupMode {
		NO_PICKUP,
		PICKUP_ALL,
		PICKUP_CREATIVE,
		PICKUP_OWNER
	}

	protected int tileX = -1;
	protected int tileY = -1;
	protected int tileZ = -1;
    protected Block inBlock;
    protected int inData;
    protected boolean inGround;
    protected boolean wasInGround;
    public PickupMode canBePickedUp;
    public int arrowShake;
    public Entity shootingEntity;
    protected int ticksInGround;
    protected int ticksInAir;
    protected int timeToLive;
    private double damage = 2.0D;
    private int knockbackStrength;

	private static final DataParameter<Byte> CRITICAL = EntityDataManager.<Byte>createKey(EntityThrow.class, DataSerializers.BYTE);
	
	public EntityThrow(World world) {
		super(world);
		tileX = -1;
		tileY = -1;
		tileZ = -1;
		inBlock = null;
		inData = 0;
		inGround = false;
		wasInGround = false;
		arrowShake = 0;
		ticksInAir = 0;
		rotationYaw = 0F;
		canBePickedUp = NO_PICKUP;
		damage = 0;
		knockbackStrength = 0;
		setTimeToLive(2400);
		setSize(0.5F, 0.5F);
	}

	@Override
	public void entityInit() {
		super.entityInit();
	}
	
	@Override
	public void setThrowableHeading(double x, double y, double z, float speed, float deviation) {
		super.setThrowableHeading(x, y, z, speed, deviation);
		ticksInGround = 0;
	}
	
	@Override
	public void setVelocity(double x, double y, double z) {
		if (prevRotationPitch == 0.0F && prevRotationYaw == 0.0F) {
			ticksInGround = 0;
		}
		super.setVelocity(x, y, z);
}
	
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
	
	@Override
	public void onUpdate() {
		onEntityUpdate();
	}
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if (aimRotation()) {
			float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
			prevRotationYaw = rotationYaw = (float) ((Math.atan2(motionX, motionZ) * 180D) / Math.PI);
			prevRotationPitch = rotationPitch = (float) ((Math.atan2(motionY, f) * 180D) / Math.PI);
		}

		IBlockState block = worldObj.getBlockState(new BlockPos(tileX, tileY, tileZ));
		if (block.getBlock() != null) {
			//block.setBlockBoundsBasedOnState(worldObj, tileX, tileY, tileZ);
			AxisAlignedBB axisalignedbb = block.getCollisionBoundingBox(worldObj, new BlockPos(tileX, tileY, tileZ));
			if (axisalignedbb != null && axisalignedbb.isVecInside(new Vec3d(posX, posY, posZ))) {
				inGround = true;
			}
		}
		
		if (arrowShake > 0) {
			arrowShake--;
		}
		
		if (inGround) {
			int metadata = worldObj.getBlockState(new BlockPos(tileX, tileY, tileZ)).getBlock().getMetaFromState(worldObj.getBlockState(new BlockPos(tileX, tileY, tileZ)));
			if (block == inBlock && metadata == inData) {
				ticksInGround++;
				int t = getTimeToLive();
				if (t != 0 && ticksInGround >= t) {
					setDead();
				}
			} else {
				inGround = false;
				motionX *= rand.nextFloat() * 0.2F;
				motionY *= rand.nextFloat() * 0.2F;
				motionZ *= rand.nextFloat() * 0.2F;
				ticksInGround = 0;
				ticksInAir = 0;
			}
			return;
		}
		
		ticksInAir++;
		
		Vec3d vec3d = new Vec3d(posX, posY, posZ);
		Vec3d vec3d1 = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);
		RayTraceResult mop = worldObj.rayTraceBlocks(vec3d, vec3d1, false, true, false);
		vec3d = new Vec3d(posX, posY, posZ);
		vec3d1 = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);
		if (mop != null) {
			vec3d1 = new Vec3d(mop.hitVec.xCoord, mop.hitVec.yCoord, mop.hitVec.zCoord);
		}
		
		Entity entity = null;
		@SuppressWarnings("unchecked")
		List<Entity> list = worldObj.getEntitiesWithinAABBExcludingEntity(this, getCollisionBoundingBox().addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
		double d = 0.0D;
		for(Entity entity1 : list) {
			if(!entity1.canBeCollidedWith() || entity1 == shootingEntity && ticksInAir < 5) {
				continue;
			}
			float f4 = 0.3F;
			AxisAlignedBB axisalignedbb1 = entity1.getEntityBoundingBox().expand(f4, f4, f4);
			RayTraceResult mop1 = axisalignedbb1.calculateIntercept(vec3d, vec3d1);
			if(mop1 == null) {
				continue;
			}
			double d1 = vec3d.distanceTo(mop1.hitVec);
			if(d1 < d || d == 0.0D) {
				entity = entity1;
				d = d1;
			}
		}
		
		if (entity != null) {
			mop = new RayTraceResult(entity);
		}
		
		if (mop != null)
		{
			if (mop.entityHit != null) {
				onEntityHit(mop.entityHit);
			} else {
				onGroundHit(mop);
			}
		}
		
		if (getIsCritical()) {
			for (int i1 = 0; i1 < 2; i1++) {
				worldObj.spawnParticle(EnumParticleTypes.CRIT_MAGIC, posX + (motionX * i1) / 4D, posY + (motionY * i1) / 4D, posZ + (motionZ * i1) / 4D, -motionX, -motionY + 0.2D, -motionZ);
			}
		}
		
		posX += motionX;
		posY += motionY;
		posZ += motionZ;

		if (aimRotation()) {
			float f2 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
			rotationYaw = (float) ((Math.atan2(motionX, motionZ) * 180D) / Math.PI);

			rotationPitch = (float) ((Math.atan2(motionY, f2) * 180D) / Math.PI);
			while(rotationPitch - prevRotationPitch < -180F) {prevRotationPitch -= 360F;}
			while(rotationPitch - prevRotationPitch >= 180F) {prevRotationPitch += 360F;}
			while(rotationYaw - prevRotationYaw < -180F) {prevRotationYaw -= 360F;}
			while(rotationYaw - prevRotationYaw >= 180F) {prevRotationYaw += 360F;}
			rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
			rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
		}

		float res = getAirResistance();
		float grav = getGravity();
		if (isInWater()) {
			wasInGround = true;
			for (int i = 0; i < 4; i++) {
				float f6 = 0.25F;
				worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, posX - motionX * f6, posY - motionY * f6, posZ - motionZ * f6, motionX, motionY, motionZ);
			}
			
			res *= 0.80808080F;
		}
		motionX *= res;
		motionY *= res;
		motionZ *= res;
		motionY -= grav;
		setPosition(posX, posY, posZ);
		//func_145775_I();
	}
	
	public void onEntityHit(Entity entity) {
		bounceBack();
		applyHitEffects(entity);
	}
	
	protected void bounceBack() {
		motionX *= -0.1D;
		motionY *= -0.1D;
		motionZ *= -0.1D;
		rotationYaw += 180F;
		prevRotationYaw += 180F;
		ticksInAir = 0;
	}
	
	public void applyHitEffects(Entity entity) {
		if (isBurning() && !(entity instanceof EntityEnderman)) {
			entity.setFire(5);
		}

		if (entity instanceof EntityLivingBase) {
			EntityLivingBase entityliving = (EntityLivingBase) entity;
			if (knockbackStrength > 0) {
				float loli = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
				if (loli > 0.0F) {
					entity.addVelocity(motionX * knockbackStrength * 0.6D / loli, 0.1D, motionZ * knockbackStrength * 0.6D / loli);
				}
			}
			if (shootingEntity instanceof EntityLivingBase) {
				EnchantmentHelper.applyThornEnchantments(entityliving, this.shootingEntity);
				EnchantmentHelper.applyArthropodEnchantments((EntityLivingBase) this.shootingEntity, entityliving);
			}
			if (shootingEntity instanceof EntityPlayerMP && shootingEntity != entity && entity instanceof EntityPlayer) {
				((EntityPlayerMP) shootingEntity).connection.sendPacket(new SPacketChangeGameState(6, 0));
			}
		}
	}
	
	public void onGroundHit(RayTraceResult mop) {
		BlockPos pos = mop.getBlockPos();
		tileX = pos.getX();
		tileY = pos.getY();
		tileZ = pos.getZ();
		inBlock = worldObj.getBlockState(new BlockPos(tileX, tileY, tileY)).getBlock();
		inData = worldObj.getBlockState(new BlockPos(tileX, tileY, tileZ)).getBlock().getMetaFromState(worldObj.getBlockState(new BlockPos(tileX, tileY, tileZ)));
		motionX = mop.hitVec.xCoord - posX;
		motionY = mop.hitVec.yCoord - posY;
		motionZ = mop.hitVec.zCoord - posZ;
		double velocity = getTotalVelocity();
		posX -= motionX / velocity * 0.05D;
		posY -= motionY / velocity * 0.05D;
		posZ -= motionZ / velocity * 0.05D;
		inGround = true;
		wasInGround = true;
		setIsCritical(false);
		arrowShake = getMaxShake();
		
		if (inBlock != null) {
			inBlock.onEntityCollidedWithBlock(worldObj, new BlockPos(tileX, tileY, tileZ), worldObj.getBlockState(new BlockPos(tileX, tileY, tileY)), this);
		}
	}
	
	public boolean canPickup(EntityPlayer entityplayer) {
		if (canBePickedUp == PICKUP_ALL) {
			return true;
		} else if(canBePickedUp == PICKUP_CREATIVE) {
			return entityplayer.capabilities.isCreativeMode;
		}
		else return canBePickedUp == PICKUP_OWNER && entityplayer == shootingEntity;
	}
	
	@Override
	public void onCollideWithPlayer(EntityPlayer entityplayer) {
		if (inGround && arrowShake <= 0) {
			if (canPickup(entityplayer)) {
				if (!worldObj.isRemote) {
					ItemStack item = getPickupItem();
					if (item == null) return;
					
					if (canBePickedUp == PICKUP_CREATIVE && entityplayer.capabilities.isCreativeMode || entityplayer.inventory.addItemStackToInventory(item)) {
						worldObj.playSound(this.posX, this.posY, this.posZ, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F, false);
						onItemPickup(entityplayer);
						setDead();
					}
				}
			}
		}
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		nbttagcompound.setShort("xTile", (short) tileX);
		nbttagcompound.setShort("yTile", (short) tileY);
		nbttagcompound.setShort("zTile", (short) tileZ);
		nbttagcompound.setByte("inTile", (byte) Block.getIdFromBlock(inBlock));
		nbttagcompound.setByte("inData", (byte) inData);
		nbttagcompound.setByte("shake", (byte) arrowShake);
		nbttagcompound.setBoolean("inGround", inGround);
		nbttagcompound.setBoolean("beenInGround", wasInGround);
		nbttagcompound.setString("pickup", canBePickedUp.name());
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		tileX = nbttagcompound.getShort("xTile");
		tileY = nbttagcompound.getShort("yTile");
		tileZ = nbttagcompound.getShort("zTile");
		inBlock = Block.getBlockById(nbttagcompound.getByte("inTile") & 0xFF);
		inData = nbttagcompound.getByte("inData") & 0xFF;
		arrowShake = nbttagcompound.getByte("shake") & 0xFF;
		inGround = nbttagcompound.getBoolean("inGround");
		wasInGround = nbttagcompound.getBoolean("beenInGrond");
		canBePickedUp = PickupMode.valueOf(nbttagcompound.getString("pickup"));
	}
	
	protected void onItemPickup(EntityPlayer entityplayer) {
		entityplayer.onItemPickup(this, 1);
	}
	
	@Override
	protected boolean canTriggerWalking() {
		return false;
	}
	
	public boolean canBeCritical() {
		return false;
	}
	
	@Override
	public void setIsCritical(boolean flag) {
		byte b0 = ((Byte)dataManager.get(CRITICAL)).byteValue();
		if (canBeCritical()) {
			dataManager.set(CRITICAL, Byte.valueOf((byte)(b0 | 1)));
		}
	}
	
	@Override
	public boolean getIsCritical() {
		byte b0 = ((Byte)this.dataManager.get(CRITICAL)).byteValue();
        return (b0 & 1) != 0;
	}
	
	public void setExtraDamage(float loli) {
		damage = loli;
	}
	
	public boolean aimRotation() {
		return false;
	}
	
	public void playHitSound() {}
	
	@Override
	public void setKnockbackStrength(int i) {
		knockbackStrength = i;
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
	
	public final double getTotalVelocity() {
		return Math.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ);
	}
	
	public void setTimeToLive(int time) {
		timeToLive = time;
	}
	
	public int getTimeToLive() {
		return timeToLive;
	}
	
	public ItemStack getPickupItem() {
		return null;
	}
	
	public float getAirResistance() {
		return 0.99F;
	}
	
	public float getGravity() {
		return 0.05F;
	}
	
	public int getMaxShake() {
		return 0;
	}
	
	@Override
	public Entity getThrower() {
		return shootingEntity;
	}

	@Override
	public void setThrower(Entity entity) {
		shootingEntity = entity;
	}

	@Override
	protected ItemStack getArrowStack() {
		return null;
	}
	
}