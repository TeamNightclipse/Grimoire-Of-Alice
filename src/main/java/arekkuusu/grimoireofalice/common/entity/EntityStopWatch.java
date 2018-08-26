/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.entity;

import arekkuusu.grimoireofalice.common.core.handler.ConfigHandler;
import arekkuusu.grimoireofalice.common.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EntityStopWatch extends Entity {

	public static final double RANGE = 32D;

	private EntityPlayer user;
	private Set<EntityPlayer> excludedPlayers = new HashSet<>();
	private Set<Entity> frozen = new HashSet<>();

	public EntityStopWatch(World world) {
		super(world);
	}

	public EntityStopWatch(World world, EntityPlayer player) {
		super(world);
		this.user = player;
		this.excludedPlayers.add(user);
		this.ignoreFrustumCheck = true;
		this.preventEntitySpawning = true;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(ConfigHandler.grimoireOfAlice.features.timeStopEffect && user != null && !user.isDead) {
			if(ticksExisted > 1000 || (user.isSneaking() && user.isSwingInProgress) || user.getFoodStats().getFoodLevel() <= 6) {
				stopEntity();
				return;
			} else {
				ItemStack stack = user.getHeldItem(user.getActiveHand());
				if(user.isHandActive() && !stack.isEmpty() && stack.getItem() == ModItems.STOPWATCH) {
					stopEntity();
					return;
				}
			}
			followPlayer();
			if(!world.isRemote) {
				List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(user, user.getEntityBoundingBox().grow(RANGE));
				list.forEach(this::haltEntity);
			}
		} else {
			stopEntity();
			return;
		}
		if(ticksExisted % 8 == 0) {
			playSound(SoundEvents.BLOCK_METAL_PRESSPLATE_CLICK_OFF, 1.0F, 1.0F + 0.8F);
			if(!user.capabilities.isCreativeMode) {
				user.getFoodStats().addExhaustion(4);
			}
		}
	}

	public void followPlayer() {
		Vec3d look = user.getLookVec();
		float distance = 1F;
		double dx = user.posX + look.x;
		double dy = user.posY + user.getEyeHeight() - 1 + look.y * distance;
		double dz = user.posZ + look.z + distance;
		setPosition(dx, dy, dz);
	}

	private void addIgnoredPlayers(Entity entity) {
		if(entity instanceof EntityStopWatch) {
			excludedPlayers.add(((EntityStopWatch) entity).getUser());
		}
	}

	private void haltEntity(Entity entity) {
		if(entity instanceof EntityStopWatch
				|| entity instanceof EntityCameraSquare
				|| entity instanceof EntityBarrier
				|| entity instanceof EntityMagicCircle
				|| entity instanceof EntityMiracleCircle
				|| entity instanceof EntityHanging) {
			return;
		}
		addIgnoredPlayers(entity);
		//noinspection SuspiciousMethodCalls
		if(entity.ticksExisted >= 2 && !excludedPlayers.contains(entity)) {
			frozen.add(entity);
			entity.updateBlocked = true;
		}
	}

	@Override
	public void setDead() {
		super.setDead();
		if(!world.isRemote) {
			frozen.forEach(e -> e.updateBlocked = false);
		}
	}

	private void stopEntity() {
		if(!world.isRemote) {
			if(user != null) {
				if(!user.capabilities.isCreativeMode) {
					ItemHandlerHelper.giveItemToPlayer(user, new ItemStack(ModItems.STOPWATCH));
				}
			} else {
				dropItem(ModItems.STOPWATCH, 1);
			}
			setDead();
		}
	}

	public EntityPlayer getUser() {
		return user;
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
