package arekkuusu.grimoireofalice.common.entity;

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
		if(!worldObj.isRemote && (player == null || !player.isHandActive() || player.isDead)) {
			setDead();
		}
	}

	@Override
	protected void entityInit() {}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {}
}
