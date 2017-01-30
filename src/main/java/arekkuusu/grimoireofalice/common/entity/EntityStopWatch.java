/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.entity;

import arekkuusu.grimoireofalice.common.core.handler.ConfigHandler;
import arekkuusu.grimoireofalice.common.core.handler.StopWatchHandler;
import arekkuusu.grimoireofalice.common.item.ModItems;
import net.katsstuff.danmakucore.entity.danmaku.EntityDanmaku;
import net.minecraft.entity.Entity;
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
	private ArrayList<UUID> players = new ArrayList<>();
	private Map<UUID, double[]> dataEntities = new HashMap<>();

	public EntityStopWatch(World worldIn) {
		super(worldIn);
	}

	public EntityStopWatch(World worldIn, EntityPlayer player) {
		super(worldIn);
		user = player;
		players.add(user.getUniqueID());
		ignoreFrustumCheck = true;
		preventEntitySpawning = true;
		StopWatchHandler.addClock(this);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (ConfigHandler.grimoireOfAlice.features.timeStopEffect && user != null && !user.isDead) {
			if (ticksExisted > 500 || (user.isSneaking() && user.isSwingInProgress)) {
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

			if (!worldObj.isRemote) {
				worldObj.setWorldTime(worldObj.getWorldTime() - 1);

				List<Entity> list = worldObj.getEntitiesWithinAABBExcludingEntity(user, user.getEntityBoundingBox().expandXyz(RANGE));
				list.forEach(this::haltEntity);
			}
		}
		else {
			stopEntity();
			return;
		}
		if (ticksExisted % 8 == 0) {
			playSound(SoundEvents.BLOCK_METAL_PRESSPLATE_CLICK_OFF, 1.0F, 1.0F + 0.8F);
		}
	}

	//Gets the player other Watches have and adds them to a list of UUID.
	private void addIgnoredPlayers(Entity entity) {
		if (entity instanceof EntityStopWatch) {//If the entity is an instance of EntityStopWatch
			UUID id = ((EntityStopWatch) entity).getPlayer().getUniqueID(); //Get the UUID of the player the Watch contains
			if (!players.contains(id)) {// Check the player list does not contain the UUID
				players.add(id);//Add the UUID
			}
		}
	}

	private void haltEntity(Entity entity) {
		if (entity instanceof EntityStopWatch
				|| entity instanceof EntityCameraSquare
				|| entity instanceof EntityBarrier
				|| entity instanceof EntityMagicCircle
				|| entity instanceof EntityGrimoireSpell) {
			return;
		}

		addIgnoredPlayers(entity);

		if (entity instanceof EntityLivingBase) {
			return;
		}

		if (!worldObj.isRemote) {

			//We use the delay to never actually call update at all
			if (entity instanceof EntityDanmaku) {
				EntityDanmaku danmaku = (EntityDanmaku) entity;
				danmaku.setShotData(danmaku.getShotData().setDelay(2));

				return;
			}

			if (!dataEntities.containsKey(entity.getUniqueID())) {
				double x = entity.motionX;
				double y = entity.motionY;
				double z = entity.motionZ;
				dataEntities.put(entity.getUniqueID(), new double[]{x, y, z});
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

	private void stopEntity() {
		if (!worldObj.isRemote) {
			StopWatchHandler.removeClock(this);
			if (user != null) {
				List<Entity> list = worldObj.getEntitiesWithinAABBExcludingEntity(user, user.getEntityBoundingBox().expandXyz(40));
				list.stream().filter(entity -> dataEntities.containsKey(entity.getUniqueID()) && !(entity instanceof EntityDanmaku)).forEach(entity -> {

					double[] data = dataEntities.get(entity.getUniqueID());
					entity.motionX = data[0];
					entity.motionY = data[1];
					entity.motionZ = data[2];
				});
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

	public EntityPlayer getPlayer() {
		return user;
	}

	public List<UUID> getPlayers() {
		return players;
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
