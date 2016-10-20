package arekkuusu.grimoireofalice.block.tile;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nullable;

public class TilePillarAltar extends TileItemHandler {

	public int tickCount;

	public boolean addItem(@Nullable EntityPlayer player, ItemStack stack) {
		boolean did = false;
		if (itemHandler.getStackInSlot(0) == null) {
			did = true;
			ItemStack stackToAdd = stack.copy();
			stackToAdd.stackSize = 1;
			itemHandler.setStackInSlot(0, stackToAdd);

			if (player == null || !player.capabilities.isCreativeMode) {
				stack.stackSize--;
				if (stack.stackSize == 0 && player != null)
					player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
			}
		}
		if (did) {
			IBlockState state = getWorld().getBlockState(getPos());
			getWorld().notifyBlockUpdate(getPos(), state, state, 8);
		}

		return true;
	}

	public boolean removeItem(EntityPlayer player) {
		boolean did = false;
		if (itemHandler.getStackInSlot(0) != null) {
			did = true;
			ItemStack stackToTake = itemHandler.getStackInSlot(0);
			itemHandler.setStackInSlot(0, null);

			if (player != null && !player.capabilities.isCreativeMode)
				if (!player.inventory.addItemStackToInventory(new ItemStack(stackToTake.getItem(), 1))) {
					player.dropItem(stackToTake.getItem(), 1);
				}
		}
		if (did) {
			IBlockState state = getWorld().getBlockState(getPos());
			getWorld().notifyBlockUpdate(getPos(), state, state, 8);
		}

		return true;
	}

	@Override
	public void writePacketNBT(NBTTagCompound tagCompound) {
		super.writePacketNBT(tagCompound);
	}

	@Override
	public void readPacketNBT(NBTTagCompound tagCompound) {
		super.readPacketNBT(tagCompound);
	}

	@Override
	public void update() {
		tickCount++;
	}
}
