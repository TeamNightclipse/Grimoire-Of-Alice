package arekkuusu.grimoireofalice.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityEllyScythe extends EntityThrow {

	private static final double RETURN_STRENGTH = 0.05D;
	private static final float MIN_FLOAT_STRENGTH = 0.4F;
	private float strength;

	public EntityEllyScythe(World world) {
		super(world);
	}

	public EntityEllyScythe(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityEllyScythe(World world, EntityLivingBase thrower, ItemStack itemstack, float velocity) {
		super(world, thrower);
		setHeadingFromThrower(thrower, thrower.rotationPitch, thrower.rotationYaw, 0.0F, velocity, 0.0F);
		setPickupModeFromEntity(thrower);
		setStack(itemstack);
		strength = Math.min(1.5F, velocity);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		EntityLivingBase thrower = getThrower();
		if(thrower == null) {
			if(!worldObj.isRemote) {
				setDead();
			}
		}
		if(inGround) return;

		strength *= 0.994F;
		if(strength < MIN_FLOAT_STRENGTH) {
			if(getCritical()) {
				setCritical(false);
			}
			strength = 0F;
		}

		if(!inGround) {
			rotationYaw += 20F * strength;
		}

		if(!inGround && getThrower() != null && strength > 0F) {
			EntityLivingBase shootingEntity = getThrower();
			double dx = posX - shootingEntity.posX;
			double dy = posY - shootingEntity.posY - shootingEntity.getEyeHeight();
			double dz = posZ - shootingEntity.posZ;

			double d = Math.sqrt(dx * dx + dy * dy + dz * dz);
			dx /= d;
			dy /= d;
			dz /= d;

			motionX -= RETURN_STRENGTH * dx;
			motionY -= RETURN_STRENGTH * dy;
			motionZ -= RETURN_STRENGTH * dz;
		}

		if(throwableShake > 0) {
			--throwableShake;
		}

		posX += motionX;
		posY += motionY;
		posZ += motionZ;

		float res = 0.99F;
		if(isInWater()) {
			for(int i = 0; i < 4; i++) {
				float f6 = 0.25F;
				worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, posX - motionX * f6, posY - motionY * f6, posZ - motionZ * f6, motionX,
						motionY, motionZ);
			}
			res *= 0.80808080F;
		}
		motionX *= res;
		motionY *= res;
		motionZ *= res;
		motionY -= getGravityVelocity();
		setPosition(posX, posY, posZ);
	}

	@Override
	void applyHitEffects(Entity entity) {
		if (getThrower() instanceof EntityPlayer && entity instanceof EntityLivingBase) {
			EntityLivingBase target = (EntityLivingBase) entity;
			if (target.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) {
				target.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 64, 0));
			}
			else {
				target.addPotionEffect(new PotionEffect(MobEffects.INSTANT_DAMAGE, 64, 0));
			}
			target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 64, 0));
			getThrower().addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 64, 3));
		}
	}

	@Override
	public AxisAlignedBB getEntityBoundingBox() {
		return super.getEntityBoundingBox().expandXyz(1D);
	}

	@Override
	double gravityThreshold() {
		return 0;
	}
}
