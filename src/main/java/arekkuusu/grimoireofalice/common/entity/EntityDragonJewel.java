/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.entity;

import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import arekkuusu.grimoireofalice.common.item.ModItems;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.DanmakuTemplate;
import net.katsstuff.teamnightclipse.danmakucore.lib.data.LibShotData;
import net.katsstuff.teamnightclipse.danmakucore.scalastuff.DanmakuCreationHelper;
import net.katsstuff.teamnightclipse.mirror.data.Vector3;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.List;

import static arekkuusu.grimoireofalice.common.item.ItemDragonJewel.COLORS;

public class EntityDragonJewel extends Entity {

	private EntityLivingBase host;

	public EntityDragonJewel(World world) {
		super(world);
		isImmuneToFire = true;
	}

	public EntityDragonJewel(World world, EntityLivingBase player) {
		super(world);
		host = player;
		ignoreFrustumCheck = true;
		preventEntitySpawning = true;
		isImmuneToFire = true;
	}

	@Override
	protected void entityInit() {
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(host == null) {
			if(!world.isRemote) {
				setDead();
			}
		} else if(ticksExisted % 50 == 0) {
			if(!world.isRemote) {
				attackEntities();
				stopEntity();
			} else {
				world.playSound(null, posX, posY, posZ, GrimoireSoundEvents.HORN, SoundCategory.NEUTRAL, 0.5F, 1F);
			}
		}
		if(ticksExisted % 10 == 0) {
			for(int i = 0; i < 2; ++i) {
				world.spawnParticle(EnumParticleTypes.PORTAL,
						posX + (rand.nextDouble() - 0.5D) * width, posY + rand.nextDouble() * height - 0.25D,
						posZ + (rand.nextDouble() - 0.5D) * width, (rand.nextDouble() - 0.5D) * 2.0D, -rand.nextDouble(),
						(rand.nextDouble() - 0.5D) * 2.0D);
			}
		}
	}

	private void attackEntities() {
		AxisAlignedBB axis = new AxisAlignedBB(getPosition());
		List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(this, axis.grow(20.0D));
		list.stream().filter(mob -> mob instanceof EntityLiving && mob != host).map(mob -> (EntityLiving) mob).forEach(mob -> {
			if(!world.isRemote && (mob instanceof EntityMob || host.equals(mob.getAttackTarget()))) {
				mob.setRevengeTarget(null);
				int color = COLORS[rand.nextInt(COLORS.length)];
				DanmakuTemplate.Builder builder = DanmakuTemplate.builder()
						.setUser(host)
						.setPos(new Vector3.WrappedVec3d(mob.getPositionEyes(0F)).asImmutable())
						.setShot(LibShotData.SHOT_SMALLSTAR.setMainColor(color))
						.setMovementData(-0.01F);
				DanmakuCreationHelper.createSphereShot(builder.build(), 4, 6, 20, 2);
			}
		});
	}

	private void stopEntity() {
		if(!world.isRemote) {
			if(host != null && host instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) host;
				if(!player.capabilities.isCreativeMode) {
					ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(ModItems.DRAGON_JEWEL));
				}
			} else {
				dropItem(ModItems.DRAGON_JEWEL, 1);
			}
			setDead();
		}
	}

	@Override
	public AxisAlignedBB getEntityBoundingBox() {
		AxisAlignedBB alignedBB = super.getEntityBoundingBox();
		return new AxisAlignedBB(alignedBB.minX + 0.1, alignedBB.minY - 0.2, alignedBB.minZ + 0.1, alignedBB.minX + 0.5, alignedBB.minY + 0.5, alignedBB.minZ + 0.5);
	}

	public int getTicksAlive() {
		return ticksExisted;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
	}
}
