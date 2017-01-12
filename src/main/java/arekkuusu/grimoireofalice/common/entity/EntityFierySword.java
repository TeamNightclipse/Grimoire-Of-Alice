/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class EntityFierySword extends Entity {

	public EntityPlayer player;

	public EntityFierySword(World worldIn) {
		super(worldIn);
	}

	public EntityFierySword(World worldIn, EntityPlayer player) {
		super(worldIn);
		setGlowing(true);
		setPosition(player.posX, player.posY + player.getEyeHeight(), player.posZ);
		this.player = player;
		this.rotationYaw = player.rotationYawHead - 65;
		this.rotationPitch = player.rotationPitch;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(player == null || player.isDead){
			stop();
		} else {
			if(ticksExisted > 20){
				stop();
			}
			if(!worldObj.isRemote) {
				List<EntityLivingBase> list = worldObj.getEntitiesWithinAABB(EntityLivingBase.class,
						getEntityBoundingBox(), input -> input != player);
				list.forEach(this::burn);
			}
			setPosition(player.posX, player.posY + player.getEyeHeight(), player.posZ);
			rotationYaw += 6;
		}
	}

	private void burn(EntityLivingBase livingBase){
		livingBase.setFire(3 + rand.nextInt(4));
	}

	private void stop(){ //Hammertime!
		if(!worldObj.isRemote){
			setDead();
		}
	}

	@Override
	public Vec3d getLookVec() {
		return this.getLook(1.0F);
	}

	@Override
	public Vec3d getLook(float partialTicks) {
		if (partialTicks == 1.0F) {
			return this.getVectorForRotation(this.rotationPitch, this.rotationYaw);
		} else {
			float f = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * partialTicks;
			float f1 = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * partialTicks;
			return this.getVectorForRotation(f, f1);
		}
	}

	@Override
	public AxisAlignedBB getEntityBoundingBox() {
		Vec3d vec = getLookVec();

		AxisAlignedBB alignedBB = super.getEntityBoundingBox();
		return new AxisAlignedBB(alignedBB.minX - 0.9, alignedBB.minY - 1.4, alignedBB.minZ - 0.9, alignedBB.minX + 1.5, alignedBB.minY + 1.5, alignedBB.minZ + 1.5).offset(vec.xCoord * 3, vec.yCoord * 3, vec.zCoord * 3);
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
