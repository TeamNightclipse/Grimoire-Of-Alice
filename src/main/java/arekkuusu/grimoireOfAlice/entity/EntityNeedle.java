package arekkuusu.grimoireOfAlice.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityNeedle extends EntityThrow{

	public static final double RETURN_STRENGTH	= 0.05D;
	public static final float MIN_FLOAT_STRENGTH = 0.4F;
	private float soundTimer;
	public float strength;
	protected ItemStack	itemThrown;
	
	public EntityNeedle(World world) {
		super(world);
	}

	public EntityNeedle(World world, double x, double y, double z) {
		this(world);
		setPosition(x, y, z);
	}
	
	public EntityNeedle(World world, EntityLivingBase entityliving, ItemStack itemstack, float f) {
		this(world);
		shootingEntity = entityliving;
		setPickupModeFromEntity(entityliving);
		setThrownItemStack(itemstack);
		setLocationAndAngles(entityliving.posX, entityliving.posY + entityliving.getEyeHeight(), entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
		posX -= MathHelper.cos((rotationYaw / 180F) * 3.141593F) * 0.16F;
		posY -= 0.1D;
		posZ -= MathHelper.sin((rotationYaw / 180F) * 3.141593F) * 0.16F;
		setPosition(posX, posY, posZ);
		yOffset = 0.0F;
		motionX = -MathHelper.sin((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F);
		motionZ = MathHelper.cos((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F);
		motionY = -MathHelper.sin((rotationPitch / 180F) * 3.141593F);
		setThrowableHeading(motionX, motionY, motionZ, f, 5.0F);
		soundTimer = 0;
		strength = Math.min(1.5F, f);
		setTimeToLive(600);
		dataWatcher.updateObject(30, Integer.valueOf(Float.floatToRawIntBits(strength)));
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		strength = Float.intBitsToFloat(dataWatcher.getWatchableObjectInt(30));
		
		if (inGround) return;
		
		strength *= 0.994F;
		if (strength < MIN_FLOAT_STRENGTH) {
			if (getIsCritical()) {
				setIsCritical(false);
			}
			strength = 0F;
		}
		float limitedStrength = Math.min(1F, strength);
		
		if (!wasInGround) {
			rotationYaw += 20F * strength;
		}
		
		if (!wasInGround && shootingEntity != null && strength > 0F) {
			double dx;
			double dy;
			double dz;
			dx = posX - shootingEntity.posX;
			dy = posY - shootingEntity.posY - shootingEntity.getEyeHeight();
			dz = posZ - shootingEntity.posZ;
			
			double d = Math.sqrt(dx * dx + dy * dy + dz * dz);
			dx /= d;
			dy /= d;
			dz /= d;
			
			soundTimer += limitedStrength;
			if (soundTimer > 3F) {
				worldObj.playSoundAtEntity(this, "random.bow", 0.6F, 1.0F / (rand.nextFloat() * 0.2F + 2.2F - limitedStrength));
				soundTimer %= 3F;
			}
		}
		
		dataWatcher.updateObject(30, Integer.valueOf(Float.floatToRawIntBits(strength)));
	}
	
	@Override
	public void onEntityHit(Entity entity) {
		if (worldObj.isRemote || strength < MIN_FLOAT_STRENGTH) return;
		
		DamageSource damagesource = null;
		float damage = 3.5F;
		damage += getMeleeHitDamage(entity);
		if (getIsCritical()) {
			damage += 2;
		}
		if (entity.attackEntityFrom(damagesource.generic, damage)) {
			applyHitEffects(entity);
			if (entity instanceof EntityLivingBase) {
				EntityLivingBase Potionable = (EntityLivingBase)entity;
				Potionable.addPotionEffect(new PotionEffect(Potion.poison.id, 80, 1));
			}
			playHitSound();
			float f1 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
			posX -= (motionX / f1) * 0.05D;
			posY -= (motionY / f1) * 0.05D;
			posZ -= (motionZ / f1) * 0.05D;
			
			motionX *= -rand.nextFloat() * 0.5F;
			motionZ *= -rand.nextFloat() * 0.5F;
			motionY = rand.nextFloat() * 0.1F;
			
			inGround = true;
			setIsCritical(false);
			wasInGround = true;
			strength = 0F;
		} else {
			setDead();
		}
	}
	
	@Override
	public void onGroundHit(MovingObjectPosition mop) {
		tileX = mop.blockX;
		tileY = mop.blockY;
		tileZ = mop.blockZ;
		inBlock = worldObj.getBlock(tileX, tileY, tileZ);
		motionX = (float) (mop.hitVec.xCoord - posX);
		motionY = (float) (mop.hitVec.yCoord - posY);
		motionZ = (float) (mop.hitVec.zCoord - posZ);
		float f1 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
		posX -= (motionX / f1) * 0.05D;
		posY -= (motionY / f1) * 0.05D;
		posZ -= (motionZ / f1) * 0.05D;
		
		motionX *= -rand.nextFloat() * 0.5F;
		motionZ *= -rand.nextFloat() * 0.5F;
		motionY = rand.nextFloat() * 0.1F;
		inGround = true;
		setIsCritical(false);
		wasInGround = true;
		strength = 0F;
		
		if (inBlock != null) {
			inBlock.onEntityCollidedWithBlock(worldObj, tileX, tileY, tileZ, this);
		}
	}
	
	@Override
	public void playHitSound() {
		worldObj.playSoundAtEntity(this, "random.bowhit", 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.9F));
	}
	
	@Override
	public boolean aimRotation() {
		return wasInGround || strength < MIN_FLOAT_STRENGTH;
	}
	
	@Override
	public int getTimeToLive() {
		return canBePickedUp == PICKUP_ALL || canBePickedUp == PICKUP_OWNER ? 0 : 1200;
	}
	
	@Override
	public boolean canBeCritical() {
		return true;
	}
	
	@Override
	public int getMaxShake() {
		return 0;
	}
	
	@Override
	public float getGravity() {
		return wasInGround || strength < MIN_FLOAT_STRENGTH ? 0.05F : 0F;
	}
	
	@Override
	public float getAirResistance() {
		return 0.98F;
	}
	
	@Override
	public void onCollideWithPlayer(EntityPlayer entityplayer) {
		if (!wasInGround && ticksInAir > 5 && strength >= MIN_FLOAT_STRENGTH) {
			if (entityplayer == shootingEntity) {
				ItemStack item = getPickupItem();
				if (item == null) return;
				
				if (entityplayer.capabilities.isCreativeMode || entityplayer.inventory.addItemStackToInventory(item)) {
					worldObj.playSoundAtEntity(this, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
					onItemPickup(entityplayer);
					setDead();
					return;
				}
			}
		}
		
		super.onCollideWithPlayer(entityplayer);
	}
	
	@Override
	public void applyHitEffects(Entity entity) {
		super.applyHitEffects(entity);
		if (shootingEntity instanceof EntityLivingBase && entity instanceof EntityLivingBase) {
			int i = EnchantmentHelper.getKnockbackModifier((EntityLivingBase) shootingEntity, (EntityLivingBase) entity);
			if (i != 0) {
				entity.addVelocity(-MathHelper.sin(rotationYaw * (float) Math.PI / 180.0F) * i * 0.5F, 0.1D, MathHelper.cos(rotationYaw * (float) Math.PI / 180.0F) * i * 0.5F);
			}
			
			i = EnchantmentHelper.getFireAspectModifier((EntityLivingBase) shootingEntity);
			if (i > 0 && !entity.isBurning()) {
				entity.setFire(1);
			}
		}
	}
	
	@Override
	public void entityInit() {
		super.entityInit();
		dataWatcher.addObject(30, Integer.valueOf(Float.floatToRawIntBits(0F)));
	}
	
	public float getMeleeHitDamage(Entity entity) {
		if (shootingEntity instanceof EntityLivingBase && entity instanceof EntityLivingBase) {
			return EnchantmentHelper.getEnchantmentModifierLiving((EntityLivingBase) shootingEntity, (EntityLivingBase) entity);
		}
		return 2F;
	}
	
	public void setThrownItemStack(ItemStack itemstack) {
		itemThrown = itemstack;
	}
	
	@Override
	public ItemStack getPickupItem() {
		return null;
	}
	
	public int getWeaponMaterialId() {
		return dataWatcher.getWatchableObjectInt(30);
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		if (itemThrown != null) {
			nbttagcompound.setTag("thrI", itemThrown.writeToNBT(new NBTTagCompound()));
		}
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		setThrownItemStack(ItemStack.loadItemStackFromNBT(nbttagcompound.getCompoundTag("thrI")));
	}

}
