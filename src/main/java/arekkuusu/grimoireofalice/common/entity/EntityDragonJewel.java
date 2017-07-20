/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.entity;

import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import arekkuusu.grimoireofalice.common.item.ModItems;
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
		if(!world.isRemote) {
			if(host == null) {
				setDead();
			}
			else if((ticksExisted > 10 && (host.isSneaking() && host.isSwingInProgress)) || ticksExisted > 500) {
				stopEntity();
			}
		}
		attackEntities();
		if(ticksExisted % 50 == 0) {
			world.playSound(null, posX, posY, posZ, GrimoireSoundEvents.HORN, SoundCategory.NEUTRAL, 0.5F, 1F);
		}
		if(ticksExisted % 10 == 0) {
			for(int i = 0; i < 2; ++i) {
				world.spawnParticle(EnumParticleTypes.PORTAL
						, posX + (rand.nextDouble() - 0.5D) * width, posY + rand.nextDouble() * height - 0.25D,
						posZ + (rand.nextDouble() - 0.5D) * width, (rand.nextDouble() - 0.5D) * 2.0D, -rand.nextDouble()
						, (rand.nextDouble() - 0.5D) * 2.0D);
			}
		}
	}

	private void attackEntities() {
		AxisAlignedBB axis = new AxisAlignedBB(getPosition());
		List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(host, axis.grow(20.0D));
		list.stream().filter(mob -> mob instanceof EntityLiving).map(mob -> (EntityLiving) mob).forEach(mob -> {
			if(!world.isRemote && (mob instanceof EntityMob || mob.getAttackTarget() == host)) {
				mob.setRevengeTarget(null);

				if(mob.getHealth() > 1) {
					mob.attackEntityFrom(DamageSource.DRAGON_BREATH, rand.nextInt(25));
				}
			}

			for(int i = 0; i < 2; ++i) {
				mob.world.spawnParticle(EnumParticleTypes.PORTAL,
						mob.posX + (rand.nextDouble() - 0.5D) * mob.width,
						mob.posY + rand.nextDouble() * mob.height - 0.25D,
						mob.posZ + (rand.nextDouble() - 0.5D) * mob.width,
						(rand.nextDouble() - 0.5D) * 2.0D, -rand.nextDouble(), (rand.nextDouble() - 0.5D) * 2.0D);
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
			}
			else {
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
