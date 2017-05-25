/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.plugin.danmakucore.entity;

import arekkuusu.grimoireofalice.common.core.handler.ConfigHandler;
import arekkuusu.grimoireofalice.common.core.handler.StopWatchHandler;
import arekkuusu.grimoireofalice.common.entity.EntityBarrier;
import arekkuusu.grimoireofalice.common.entity.EntityGrimoireSpell;
import arekkuusu.grimoireofalice.common.entity.EntityMagicCircle;
import arekkuusu.grimoireofalice.common.entity.EntityMiracleCircle;
import arekkuusu.grimoireofalice.common.item.ModItems;
import net.katsstuff.danmakucore.entity.danmaku.EntityDanmaku;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.*;

public class EntityStopWatch extends Entity {

	public static final double RANGE = 32D;

	private EntityPlayer user;
	private List<EntityPlayer> excludedPlayers = new ArrayList<>();
	private Map<Entity, double[]> dataEntities = new HashMap<>();
	private List<EntityDanmaku> frozenDanmaku = new ArrayList<>();

	public EntityStopWatch(World worldIn) {
		super(worldIn);
	}

	public EntityStopWatch(World worldIn, EntityPlayer player) {
		super(worldIn);
		user = player;
		excludedPlayers.add(user);
		ignoreFrustumCheck = true;
		preventEntitySpawning = true;
		StopWatchHandler.addClock(this);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (ConfigHandler.grimoireOfAlice.features.timeStopEffect && user != null && !user.isDead) {
			if (ticksExisted > 1000 || (user.isSneaking() && user.isSwingInProgress) || user.getFoodStats().getFoodLevel() <= 6) {
				stopEntity();
				return;
			}
			else {
				ItemStack stack = user.getHeldItem(user.getActiveHand());
				if (user.isHandActive() && stack != null && stack.getItem() == ModItems.STOP_WATCH) {
					stopEntity();
					return;
				}
			}

			Vec3d look = user.getLookVec();
			float distance = 1F;
			double dx = user.posX + look.xCoord + distance;
			double dy = user.posY + user.getEyeHeight() - 1;
			double dz = user.posZ + look.zCoord + distance;
			setPosition(dx, dy, dz);

			if (!world.isRemote) {
				world.setWorldTime(world.getWorldTime() - 1);

				List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(user, user.getEntityBoundingBox().expandXyz(RANGE));
				list.forEach(this::haltEntity);
			}
		}
		else {
			stopEntity();
			return;
		}
		if (ticksExisted % 8 == 0) {
			playSound(SoundEvents.BLOCK_METAL_PRESSPLATE_CLICK_OFF, 1.0F, 1.0F + 0.8F);
			if(!user.capabilities.isCreativeMode) {
                user.getFoodStats().addExhaustion(4);
            }
		}
	}

	private void addIgnoredPlayers(Entity entity) {
		if (entity instanceof EntityStopWatch) {
			EntityPlayer player = ((EntityStopWatch) entity).getUser();
			if (!excludedPlayers.contains(player)) {
				excludedPlayers.add(player);
			}
		}
	}

	private void haltEntity(Entity entity) {
		if (entity instanceof EntityStopWatch
				|| entity instanceof EntityCameraSquare
				|| entity instanceof EntityBarrier
				|| entity instanceof EntityMagicCircle
                || entity instanceof EntityMiracleCircle
				|| entity instanceof EntityGrimoireSpell
				|| entity instanceof EntityHanging) {
			return;
		}

		addIgnoredPlayers(entity);

		if (entity instanceof EntityLivingBase) {
			return;
		}

		if (!world.isRemote) {

			//We use the delay to never actually call update at all
			if (entity instanceof EntityDanmaku && !frozenDanmaku.contains(entity)) {
				EntityDanmaku danmaku = (EntityDanmaku)entity;
				danmaku.setFrozen(true);
				frozenDanmaku.add(danmaku);
				return;
			}

			if (!dataEntities.containsKey(entity)) {
				double x = entity.motionX;
				double y = entity.motionY;
				double z = entity.motionZ;
				dataEntities.put(entity, new double[]{x, y, z});
			}
		}

		if (entity.ticksExisted >= 2) {
			entity.setPosition(entity.prevPosX, entity.prevPosY, entity.prevPosZ);
			entity.rotationYaw = entity.prevRotationYaw;
			entity.rotationPitch = entity.prevRotationPitch;
			entity.motionX = 0;

			if (!entity.onGround) {
				entity.motionY = 0;
			}
			entity.motionZ = 0;

			entity.setAir(0);
			entity.ticksExisted--;
			entity.fallDistance = 0;

			if (entity instanceof EntityThrowable) {
				++((EntityThrowable) entity).throwableShake;
			}
			else if (entity instanceof EntityArrow) {
				++((EntityArrow) entity).arrowShake;
			}
		}
	}

	@Override
	public void setDead() {
		super.setDead();
		StopWatchHandler.removeClock(this);
	}

	private void stopEntity() {
		if (!world.isRemote) {
			if (user != null) {
				for(Map.Entry<Entity, double[]> entityEntry : dataEntities.entrySet()) {
					Entity entity = entityEntry.getKey();
					double[] data = entityEntry.getValue();
					entity.motionX = data[0];
					entity.motionY = data[1];
					entity.motionZ = data[2];
				}

				for(EntityDanmaku danmaku : frozenDanmaku) {
					danmaku.setFrozen(false);
				}

				if (!user.capabilities.isCreativeMode) {
					ItemHandlerHelper.giveItemToPlayer(user, new ItemStack(ModItems.STOP_WATCH));
				}
				setDead();
			}
			else {
				dropItem(ModItems.STOP_WATCH, 1);
				setDead();
			}
		}
	}

	public EntityPlayer getUser() {
		return user;
	}

	public List<EntityPlayer> getExcludedPlayers() {
		return excludedPlayers;
	}

	@Override
	protected void entityInit() {
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
	}
}
