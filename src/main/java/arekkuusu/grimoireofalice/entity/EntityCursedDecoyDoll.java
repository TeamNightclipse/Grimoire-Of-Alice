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
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.List;

public class EntityCursedDecoyDoll extends EntityLiving{

	private EntityPlayer user;

	public EntityCursedDecoyDoll(World worldIn) {
		super(worldIn);
		setHealth(20);
		setNoGravity(true);
	}

	public EntityCursedDecoyDoll(World worldIn, EntityPlayer user) {
		super(worldIn);
		this.user = user;
		setRotation(user.rotationYaw, 0);
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

	private void stopEntity() {
		if(!worldObj.isRemote) {
			if(user != null) {
				if(user.capabilities.isCreativeMode) {
					setDead();
					return;
				}
				ItemHandlerHelper.giveItemToPlayer(user, new ItemStack(ModItems.CURSED_DECOY_DOLL));
			}
			else {
				dropItem(ModItems.CURSED_DECOY_DOLL, 1);
			}
			setDead();
		}
	}

	@Override
	public AxisAlignedBB getEntityBoundingBox() {
		return super.getEntityBoundingBox().contract(0.7);
	}
}
