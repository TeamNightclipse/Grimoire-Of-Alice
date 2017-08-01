/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.block.tile;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import arekkuusu.grimoireofalice.api.tile.ITileItemHolder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

public abstract class TileItemHandler extends TileEntity implements ITileItemHolder {

	protected ItemStackHandlerTile itemHandler = createItemHandler();

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, @Nonnull IBlockState oldState, @Nonnull IBlockState newState) {
		return oldState.getBlock() != newState.getBlock(); //TODO: Can this cause bugs with BlockOnbashira?
	}

	@Nonnull
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound par1nbtTagCompound) {
		NBTTagCompound ret = super.writeToNBT(par1nbtTagCompound);
		writeDataNBT(ret);
		return ret;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readFromNBT(par1nbtTagCompound);
		readDataNBT(par1nbtTagCompound);
	}

	@Override
	public boolean removeItem(@Nullable EntityPlayer player) {
		boolean removed = false;
		if(hasItem()) {
			world.playSound(null, getPos(), SoundEvents.ENTITY_ITEMFRAME_REMOVE_ITEM, SoundCategory.BLOCKS, 1F, 0.5F);
			removed = true;

			ItemStack stackToTake = itemHandler.extractItem(0, 1, false);
			if(player != null && !player.capabilities.isCreativeMode) {
				ItemHandlerHelper.giveItemToPlayer(player, stackToTake, player.inventory.currentItem);
			}
		}
		return removed;
	}

	@Override
	public void destroy() {
		if(!world.isRemote) {
			ItemStack output = itemHandler.extractItem(0, 1, false);
			if(!output.isEmpty()) {
				EntityItem outputItem = new EntityItem(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, output);
				world.spawnEntity(outputItem);
			}
		}
	}

	@Override
	public boolean hasItem() {
		return !itemHandler.getStackInSlot(0).isEmpty();
	}

	@Nonnull
	@Override
	public final NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}

	@Override
	public final SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound tag = getUpdateTag();
		writeDataNBT(tag);
		return new SPacketUpdateTileEntity(pos, 0, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		super.onDataPacket(net, packet);
		readDataNBT(packet.getNbtCompound()); //FIXME: Still a Client side Method... isnt it supposed to be readFromNBT(nbt)?
	}

	public void readDataNBT(NBTTagCompound tagCompound) {
		itemHandler = createItemHandler();
		itemHandler.deserializeNBT(tagCompound);
	}

	public void writeDataNBT(NBTTagCompound tagCompound) {
		tagCompound.merge(itemHandler.serializeNBT());
	}

	protected ItemStackHandlerTile createItemHandler() {
		return new ItemStackHandlerTile(this, true);
	}

	@Override
	public boolean hasCapability(@Nonnull Capability<?> cap, @Nullable EnumFacing side) {
		return cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(cap, side);
	}

	@Nullable
	@Override
	public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing side) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY
				? CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemHandler)
				: super.getCapability(capability, side);
	}

	public abstract int getSizeInventory();

	public static class ItemStackHandlerTile extends ItemStackHandler {

		private final TileItemHandler tile;
		private boolean allow;

		ItemStackHandlerTile(TileItemHandler tile, boolean allow) {
			super(tile.getSizeInventory());
			this.tile = tile;
			this.allow = allow;
		}

		@Override
		protected int getStackLimit(int slot, ItemStack stack) {
			return 1;
		}

		@Override
		public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
			if(allow) {
				return super.insertItem(slot, stack, simulate);
			}
			else {
				return stack;
			}
		}

		@Override
		public ItemStack extractItem(int slot, int amount, boolean simulate) {
			if(allow) {
				return super.extractItem(slot, amount, simulate);
			}
			else {
				return ItemStack.EMPTY;
			}
		}

		public ItemStack getItemSimulate(int slot) {
			if(allow) {
				return super.extractItem(slot, 1, true);
			}
			else {
				return ItemStack.EMPTY;
			}
		}

		@Override
		public void onContentsChanged(int slot) {
			tile.markDirty();
			IBlockState state = tile.world.getBlockState(tile.getPos());
			tile.world.notifyBlockUpdate(tile.getPos(), state, state, 8);
		}
	}
}
