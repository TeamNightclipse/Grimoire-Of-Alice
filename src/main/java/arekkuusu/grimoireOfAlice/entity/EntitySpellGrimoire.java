package arekkuusu.grimoireOfAlice.entity;

import static arekkuusu.grimoireOfAlice.entity.EntityThrow.PickupMode.PICKUP_ALL;
import static arekkuusu.grimoireOfAlice.entity.EntityThrow.PickupMode.PICKUP_OWNER;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntitySpellGrimoire extends Entity{

	public EntitySpellGrimoire(World world) {
		super(world);
	}

	@Override
	protected void entityInit() {
		dataWatcher.addObject(25, 0F);
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
		
	}

}
