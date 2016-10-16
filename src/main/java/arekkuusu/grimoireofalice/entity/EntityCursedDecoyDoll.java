package arekkuusu.grimoireofalice.entity;

import arekkuusu.grimoireofalice.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

public class EntityCursedDecoyDoll extends EntityLiving{

	private EntityPlayer user;

	public EntityCursedDecoyDoll(World worldIn) {
		super(worldIn);
	}

	public EntityCursedDecoyDoll(World worldIn, EntityPlayer user) {
		super(worldIn);
		this.user = user;
		setRotation(user.rotationYaw,0);
		setHealth(20);
		setNoGravity(true);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(ticksExisted > 500){
			stopEntity();
		}
		getEntities();
		motionY = motionX = motionZ = 0;
	}

	private void getEntities(){
		AxisAlignedBB axis = new AxisAlignedBB(getPosition());
		List<Entity> list = worldObj.getEntitiesWithinAABBExcludingEntity(user, axis.expandXyz(20.0D));
		if(!list.isEmpty()) {
			list.stream().filter(mob -> mob instanceof EntityMob).map(mob -> (EntityMob)mob).forEach(mob -> {
				mob.setAttackTarget(this);
				mob.setRevengeTarget(this);
			});
		}
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		setDead();
	}

	private void stopEntity() {
		if(!worldObj.isRemote) {
			if(user != null) {
				if(user.capabilities.isCreativeMode) {
					setDead();
					return;
				}
				if(!user.inventory.addItemStackToInventory(new ItemStack(ModItems.cursedDecoyDoll, 1))) {
					user.dropItem(ModItems.cursedDecoyDoll, 1);
				}
			}
			else {
				dropItem(ModItems.cursedDecoyDoll, 1);
			}
			setDead();
		}
	}

	@Override
	public AxisAlignedBB getEntityBoundingBox() {
		return super.getEntityBoundingBox().contract(0.7);
	}
}
