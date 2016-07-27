package arekkuusu.grimoireOfAlice.entity;

import java.util.List;

import cpw.mods.fml.common.registry.IThrowableEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityThrow extends EntityArrow implements IThrowableEntity{

	public static final int	NO_PICKUP = 0, PICKUP_ALL = 1, PICKUP_CREATIVE = 2, PICKUP_OWNER = 3;
	protected int tileX = -1;
	protected int tileY = -1;
	protected int tileZ = -1;
    protected Block inBlock;
    protected int inData;
    protected boolean inGround;
    protected boolean wasInGround;
    public int canBePickedUp;
    public int arrowShake;
    public Entity shootingEntity;
    protected int ticksInGround;
    protected int ticksInAir;
    protected int timeToLive;
    private double damage = 2.0D;
    private int knockbackStrength;
	
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
		yOffset = 0F;
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
		float f2 = MathHelper.sqrt_double(x * x + y * y + z * z);
		x /= f2;
		y /= f2;
		z /= f2;
		x += rand.nextGaussian() * 0.0075F * deviation;
		y += rand.nextGaussian() * 0.0075F * deviation;
		z += rand.nextGaussian() * 0.0075F * deviation;
		x *= speed;
		y *= speed;
		z *= speed;
		motionX = x;
		motionY = y;
		motionZ = z;
		float f3 = MathHelper.sqrt_double(x * x + z * z);
		prevRotationYaw = rotationYaw = (float) ((Math.atan2(x, z) * 180D) / Math.PI);
		prevRotationPitch = rotationPitch = (float) ((Math.atan2(y, f3) * 180D) / Math.PI);
		ticksInGround = 0;
	}
	
	@Override
	public void setVelocity(double d, double d1, double d2) {
		motionX = d;
		motionY = d1;
		motionZ = d2;
		if (prevRotationPitch == 0.0F && prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt_double(d * d + d2 * d2);
			prevRotationYaw = rotationYaw = (float) ((Math.atan2(d, d2) * 180D) / Math.PI);
			prevRotationPitch = rotationPitch = (float) ((Math.atan2(d1, f) * 180D) / Math.PI);
			setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
			ticksInGround = 0;
		}
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
		Block i = worldObj.getBlock(tileX, tileY, tileZ);
		if (i != null) {
			i.setBlockBoundsBasedOnState(worldObj, tileX, tileY, tileZ);
			AxisAlignedBB axisalignedbb = i.getCollisionBoundingBoxFromPool(worldObj, tileX, tileY, tileZ);
			if (axisalignedbb != null && axisalignedbb.isVecInside(Vec3.createVectorHelper(posX, posY, posZ))) {
				inGround = true;
			}
		}
		
		if (arrowShake > 0) {
			arrowShake--;
		}
		
		if (inGround) {
			Block j = worldObj.getBlock(tileX, tileY, tileZ);
			int k = worldObj.getBlockMetadata(tileX, tileY, tileZ);
			if (j == inBlock && k == inData) {
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
		
		Vec3 vec3d = Vec3.createVectorHelper(posX, posY, posZ);
		Vec3 vec3d1 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
		MovingObjectPosition movingobjectposition = worldObj.func_147447_a(vec3d, vec3d1, false, true, false);
		vec3d = Vec3.createVectorHelper(posX, posY, posZ);
		vec3d1 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
		if (movingobjectposition != null) {
			vec3d1 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
		}
		
		Entity entity = null;
		@SuppressWarnings("unchecked")
		List<Entity> list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
		double d = 0.0D;
		for (int l = 0; l < list.size(); l++) {
			Entity entity1 = list.get(l);
			if (!entity1.canBeCollidedWith() || entity1 == shootingEntity && ticksInAir < 5) {
				continue;
			}
			float f4 = 0.3F;
			AxisAlignedBB axisalignedbb1 = entity1.boundingBox.expand(f4, f4, f4);
			MovingObjectPosition movingobjectposition1 = axisalignedbb1.calculateIntercept(vec3d, vec3d1);
			if (movingobjectposition1 == null) {
				continue;
			}
			double d1 = vec3d.distanceTo(movingobjectposition1.hitVec);
			if (d1 < d || d == 0.0D) {
				entity = entity1;
				d = d1;
			}
		}
		
		if (entity != null) {
			movingobjectposition = new MovingObjectPosition(entity);
		}
		
		if (movingobjectposition != null)
		{
			if (movingobjectposition.entityHit != null) {
				onEntityHit(movingobjectposition.entityHit);
			} else {
				onGroundHit(movingobjectposition);
			}
		}
		
		if (getIsCritical()) {
			for (int i1 = 0; i1 < 2; i1++) {
				worldObj.spawnParticle("magicCrit", posX + (motionX * i1) / 4D, posY + (motionY * i1) / 4D, posZ + (motionZ * i1) / 4D, -motionX, -motionY + 0.2D, -motionZ);
			}
		}
		
		posX += motionX;
		posY += motionY;
		posZ += motionZ;
		if (aimRotation()) {
			float f2 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
			rotationYaw = (float) ((Math.atan2(motionX, motionZ) * 180D) / Math.PI);
			for (rotationPitch = (float) ((Math.atan2(motionY, f2) * 180D) / Math.PI); rotationPitch - prevRotationPitch < -180F; prevRotationPitch -= 360F) {}
			for (; rotationPitch - prevRotationPitch >= 180F; prevRotationPitch += 360F) {}
			for (; rotationYaw - prevRotationYaw < -180F; prevRotationYaw -= 360F) {}
			for (; rotationYaw - prevRotationYaw >= 180F; prevRotationYaw += 360F) {}
			rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
			rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
		}
		float res = getAirResistance();
		float grav = getGravity();
		if (isInWater()) {
			wasInGround = true;
			for (int i1 = 0; i1 < 4; i1++) {
				float f6 = 0.25F;
				worldObj.spawnParticle("bubble", posX - motionX * f6, posY - motionY * f6, posZ - motionZ * f6, motionX, motionY, motionZ);
			}
			
			res *= 0.80808080F;
		}
		motionX *= res;
		motionY *= res;
		motionZ *= res;
		motionY -= grav;
		setPosition(posX, posY, posZ);
		func_145775_I();
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
				EnchantmentHelper.func_151384_a(entityliving, this.shootingEntity);
				EnchantmentHelper.func_151385_b((EntityLivingBase) this.shootingEntity, entityliving);
			}
			if (shootingEntity instanceof EntityPlayerMP && shootingEntity != entity && entity instanceof EntityPlayer) {
				((EntityPlayerMP) shootingEntity).playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(6, 0));
			}
		}
	}
	
	public void onGroundHit(MovingObjectPosition mop) {
		tileX = mop.blockX;
		tileY = mop.blockY;
		tileZ = mop.blockZ;
		inBlock = worldObj.getBlock(tileX, tileY, tileY);
		inData = worldObj.getBlockMetadata(tileX, tileY, tileZ);
		motionX = mop.hitVec.xCoord - posX;
		motionY = mop.hitVec.yCoord - posY;
		motionZ = mop.hitVec.zCoord - posZ;
		float f1 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
		posX -= motionX / f1 * 0.05D;
		posY -= motionY / f1 * 0.05D;
		posZ -= motionZ / f1 * 0.05D;
		inGround = true;
		wasInGround = true;
		setIsCritical(false);
		arrowShake = getMaxShake();
		
		if (inBlock != null) {
			inBlock.onEntityCollidedWithBlock(worldObj, tileX, tileY, tileZ, this);
		}
	}
	
	public boolean canPickup(EntityPlayer entityplayer)
	{
		if (canBePickedUp == PICKUP_ALL)
		{
			return true;
		} else if (canBePickedUp == PICKUP_CREATIVE)
		{
			return entityplayer.capabilities.isCreativeMode;
		} else if (canBePickedUp == PICKUP_OWNER)
		{
			return entityplayer == shootingEntity;
		} else
		{
			return false;
		}
	}
	
	@Override
	public void onCollideWithPlayer(EntityPlayer entityplayer) {
		if (inGround && arrowShake <= 0) {
			if (canPickup(entityplayer)) {
				if (!worldObj.isRemote) {
					ItemStack item = getPickupItem();
					if (item == null) return;
					
					if (canBePickedUp == PICKUP_CREATIVE && entityplayer.capabilities.isCreativeMode || entityplayer.inventory.addItemStackToInventory(item)) {
						worldObj.playSoundAtEntity(this, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
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
		nbttagcompound.setByte("pickup", (byte) canBePickedUp);
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
		canBePickedUp = nbttagcompound.getByte("pickup");
	}
	
	protected void onItemPickup(EntityPlayer entityplayer) {
		entityplayer.onItemPickup(this, 1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public float getShadowSize() {
		return 0.0F;
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
		if (canBeCritical()) {
			dataWatcher.updateObject(16, Byte.valueOf((byte) (flag ? 1 : 0)));
		}
	}
	
	@Override
	public boolean getIsCritical() {
		return canBeCritical() && dataWatcher.getWatchableObjectByte(16) != 0;
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
	
	public void setPickupMode(int i) {
		canBePickedUp = i;
	}
	
	public int getPickupMode() {
		return canBePickedUp;
	}
	
	private void canBePickedUp(int canI){
		canBePickedUp= canI;
	}
	
	public final double getTotalVelocity() {
		return Math.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ);
	}
	
	public void setTimeToLive(int time) {
		timeToLive=time;
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
	
}