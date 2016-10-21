package arekkuusu.grimoireofalice.block.tile;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;

public class TilePillarAltar extends TileItemHandler implements ITickable {

	public int tickCount;

	public boolean addItem(@Nullable EntityPlayer player, ItemStack stack) {
		if (itemHandler.getStackInSlot(0) == null) {
			ItemStack stackToAdd = stack.copy();
			stackToAdd.stackSize = 1;
			itemHandler.setStackInSlot(0, stackToAdd);

			if (player == null || !player.capabilities.isCreativeMode) {
				stack.stackSize--;
				if (stack.stackSize == 0 && player != null)
					player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
			}

			IBlockState state = getWorld().getBlockState(getPos());
			getWorld().notifyBlockUpdate(getPos(), state, state, 8);
		}

		return true;
	}

	public boolean removeItem(EntityPlayer player) {
		if (itemHandler.getStackInSlot(0) != null) {
			ItemStack stackToTake = itemHandler.getStackInSlot(0);
			itemHandler.setStackInSlot(0, null);

			if(player != null && !player.capabilities.isCreativeMode) {
				ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(stackToTake.getItem(), 1));
			}

			IBlockState state = getWorld().getBlockState(getPos());
			getWorld().notifyBlockUpdate(getPos(), state, state, 8);
		}

		return true;
	}

	@Override
	public void writeDataNBT(NBTTagCompound tagCompound) {
		super.writeDataNBT(tagCompound);
	}

	@Override
	public void readDataNBT(NBTTagCompound tagCompound) {
		super.readDataNBT(tagCompound);
	}

	@Override
	public void update() {
		tickCount++;
	}
}
