package arekkuusu.grimoireofalice.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityCameraSquare extends Entity {

	private EntityPlayer player;

	public EntityCameraSquare(World worldIn) {
		super(worldIn);
	}

	public EntityCameraSquare(World worldIn, EntityPlayer player) {
		super(worldIn);
		this.player = player;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(player == null){
			if(!worldObj.isRemote) setDead();
		} else if(!player.isHandActive() || player.isDead){
			if(!worldObj.isRemote) setDead();
		}
	}

	@Override
	protected void entityInit() {

	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {

	}
}
