package arekkuusu.grimoireofalice.common.block.tile;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;
import net.minecraftforge.items.ItemHandlerHelper;

public class TilePillarAltar extends TileItemHandler implements ITickable {

	public int tickCount;

	public boolean addItem(@Nullable EntityPlayer player, ItemStack stack) {
		boolean added = false;
		if(!hasItem()) {
			added = true;
			ItemStack stackToAdd = stack.copy();
			stackToAdd.stackSize = 1;
			itemHandler.insertItem(0, stackToAdd, false);

			if(player == null || !player.capabilities.isCreativeMode) {
				stack.stackSize--;
				if(stack.stackSize == 0 && player != null) {
					player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
				}
			}

			IBlockState state = getWorld().getBlockState(getPos());
			getWorld().notifyBlockUpdate(getPos(), state, state, 8);
		}
		return added;
	}

	public boolean removeItem(@Nullable EntityPlayer player) {
		boolean removed = false;
		if(hasItem()) {
			removed = true;
			ItemStack stackToTake = itemHandler.extractItem(0, 1, false);

			if(player != null && !player.capabilities.isCreativeMode) {
				ItemHandlerHelper.giveItemToPlayer(player, stackToTake);
			}

			IBlockState state = getWorld().getBlockState(getPos());
			getWorld().notifyBlockUpdate(getPos(), state, state, 8);
		}
		return removed;
	}

	public boolean hasItem() {
		return itemHandler.getStackInSlot(0) != null;
	}

	public ItemStack getItemStack() {
		return itemHandler.getStackInSlot(0);
	}

	@Override
	public void update() {
		tickCount++;
	}
}
