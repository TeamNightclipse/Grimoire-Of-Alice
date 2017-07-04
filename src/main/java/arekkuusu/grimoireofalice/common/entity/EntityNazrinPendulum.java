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
import java.util.Arrays;
import java.util.List;

import arekkuusu.grimoireofalice.common.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.oredict.OreDictionary;

public class EntityNazrinPendulum extends Entity {

	private ItemStack stack;
	private String ore = "";

	public EntityPlayer user;
	private boolean follow;

	public EntityNazrinPendulum(World world) {
		super(world);
		isImmuneToFire = true;
	}

	public EntityNazrinPendulum(World world, EntityPlayer player, ItemStack stack, String ore, boolean follow) {
		super(world);
		user = player;
		this.stack = stack.copy();
		this.follow = follow;
		this.ore = ore;
		isImmuneToFire = true;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(!world.isRemote) {
			if(user == null || isEntityInsideOpaqueBlock()) {
				stopEntity();
			}
			else {
				if(ticksExisted > 10 && user.isSneaking()) {
					stopEntity();
				}
				else if(user.hurtTime > 0) {
					stopEntity();
				}
			}

			if(user != null && follow) {
				Vec3d look = user.getLookVec();
				float distance = 2F;
				double dx = user.posX + look.x * distance;
				double dy = user.posY + user.getEyeHeight() - 0.5;
				double dz = user.posZ + look.z * distance;
				setPosition(dx, dy, dz);
			}

			List<Block> blockLayer = new ArrayList<>(20);
			BlockPos pos = new BlockPos(posX, posY, posZ);
			for(int i = 1; i < 20; i++) {
				Block block = world.getBlockState(pos.down(i)).getBlock();
				ItemStack stack = new ItemStack(block);

				//noinspection ConstantConditions Liar
				if(stack.isEmpty()) {
					continue;
				}
				boolean isOre = Arrays.stream(OreDictionary.getOreIDs(stack)).mapToObj(OreDictionary::getOreName).anyMatch(
						s -> !ore.isEmpty() ? s.equals(ore) : s.startsWith("ore")) || block == Blocks.CHEST;

				if(isOre) {
					blockLayer.add(block);
				}
			}
			blockLayer.forEach(ignored -> {
				if(rand.nextInt(8) == 2 && world instanceof WorldServer) {
					((WorldServer) world).spawnParticle(EnumParticleTypes.CRIT_MAGIC, posX, posY, posZ, 1, 0D, rand.nextDouble(), 0D, 0.1D);
				}
			});
		}
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected void entityInit() {
	}

	private void stopEntity() {
		if(!world.isRemote) {
			if(user != null) {
				if(user.capabilities.isCreativeMode) {
					setDead();
					return;
				}
				ItemHandlerHelper.giveItemToPlayer(user, stack);
			}
			else {
				dropItem(ModItems.NAZRIN_PENDULUM, 1);
			}
			setDead();
		}
	}

	@Override
	public AxisAlignedBB getEntityBoundingBox() {
		AxisAlignedBB alignedBB = super.getEntityBoundingBox();
		return new AxisAlignedBB(alignedBB.minX + 0.1, alignedBB.minY - 0.2, alignedBB.minZ + 0.1, alignedBB.minX + 0.5, alignedBB.minY + 0.2, alignedBB.minZ + 0.5);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
	}
}
