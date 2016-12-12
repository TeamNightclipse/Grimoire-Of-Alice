package arekkuusu.grimoireofalice.common.block.tile;

import javax.annotation.Nullable;

import arekkuusu.grimoireofalice.api.tile.ITileItemHolder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.items.ItemHandlerHelper;

public class TilePillarAltar extends TileItemHandler implements ITileItemHolder, ITickable {

	public int tickCount;

	@Override
	public boolean addItem(@Nullable EntityPlayer player, ItemStack stack) {
		boolean added = false;
		if(!hasItem()) {
			worldObj.playSound(null, getPos(), SoundEvents.ENTITY_ITEMFRAME_ADD_ITEM, SoundCategory.BLOCKS, 1F, 0.5F);
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

	@Override
	public boolean removeItem(@Nullable EntityPlayer player) {
		boolean removed = false;
		if(hasItem()) {
			worldObj.playSound(null, getPos(), SoundEvents.ENTITY_ITEMFRAME_REMOVE_ITEM, SoundCategory.BLOCKS, 1F, 0.5F);
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

	@Override
	public void destroy() {
		if (!worldObj.isRemote) {
			ItemStack output = itemHandler.extractItem(0, 1, false);
			if (output != null) {
				EntityItem outputItem = new EntityItem(worldObj, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, output);
				worldObj.spawnEntityInWorld(outputItem);
			}
		}
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
