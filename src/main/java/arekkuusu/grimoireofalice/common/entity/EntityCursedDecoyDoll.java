/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.entity;

import java.util.ArrayList;
import java.util.List;

import arekkuusu.grimoireofalice.common.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;

public class EntityCursedDecoyDoll extends EntityLivingBase {

	private EntityPlayer user;

	public EntityCursedDecoyDoll(World world) {
		super(world);
	}

	public EntityCursedDecoyDoll(World world, EntityPlayer user) {
		super(world);
		this.user = user;
		setRotation(user.rotationYaw, 0);
		setHealth(20);
		setNoGravity(true);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(ticksExisted > 500) {
			stopEntity();
		}
		stealAgroAround();
		motionY = motionX = motionZ = 0;
	}

	@Override
	public EnumHandSide getPrimaryHand() {
		return EnumHandSide.RIGHT;
	}

	private void stealAgroAround() {
		AxisAlignedBB axis = new AxisAlignedBB(getPosition());
		List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(user, axis.grow(20.0D));
		list.stream().filter(mob -> mob instanceof EntityMob).map(mob -> (EntityMob)mob).forEach(mob -> {
			mob.setAttackTarget(this);
			mob.setRevengeTarget(this);
		});
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		setDead();
	}

	@Override
	public Iterable<ItemStack> getArmorInventoryList() {
		return new ArrayList<>();
	}

	@Override
	public ItemStack getItemStackFromSlot(EntityEquipmentSlot slotIn) {
		return ItemStack.EMPTY;
	}

	@Override
	public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack) {}

	private void stopEntity() {
		if(!world.isRemote) {
			if(user != null) {
				if(!user.capabilities.isCreativeMode) {
					ItemHandlerHelper.giveItemToPlayer(user, new ItemStack(ModItems.CURSED_DECOY_DOLL));
				}
			}
			else {
				dropItem(ModItems.CURSED_DECOY_DOLL, 1);
			}
			setDead();
		}
	}

	@Override
	public AxisAlignedBB getEntityBoundingBox() {
		AxisAlignedBB alignedBB = super.getEntityBoundingBox();
		return new AxisAlignedBB(alignedBB.minX, alignedBB.minY, alignedBB.minZ, alignedBB.minX + 0.5, alignedBB.minY + 0.8, alignedBB.minZ + 0.5);
	}
}
