package arekkuusu.grimoireofalice.block.tile;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class TileItemHandler extends TileEntity implements ITickable {

	protected ItemHandler itemHandler = createItemHandler();

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, @Nonnull IBlockState oldState, @Nonnull IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

	@Nonnull
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound par1nbtTagCompound) {
		NBTTagCompound ret = super.writeToNBT(par1nbtTagCompound);
		writePacketNBT(ret);
		return ret;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readFromNBT(par1nbtTagCompound);
		readPacketNBT(par1nbtTagCompound);
	}

	@Nonnull
	@Override
	public final NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}

	@Override
	public final SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound tag = new NBTTagCompound();
		writePacketNBT(tag);
		return new SPacketUpdateTileEntity(pos, -999, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		super.onDataPacket(net, packet);
		readPacketNBT(packet.getNbtCompound());
	}

	public void readPacketNBT(NBTTagCompound tagCompound) {
		itemHandler = createItemHandler();
		itemHandler.deserializeNBT(tagCompound);
	}

	public void writePacketNBT(NBTTagCompound tagCompound) {
		tagCompound.merge(itemHandler.serializeNBT());
	}

	protected ItemHandler createItemHandler() {
		return new ItemHandler(this, true);
	}

	public IItemHandlerModifiable getItemHandler() {
		return itemHandler;
	}

	@Override
	public boolean hasCapability(@Nonnull Capability<?> cap, @Nonnull EnumFacing side) {
		return cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(cap, side);
	}

	@Nonnull
	@Override
	public <T> T getCapability(@Nonnull Capability<T> capability, @Nonnull EnumFacing side) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemHandler);
		return super.getCapability(capability, side);
	}

	protected static class ItemHandler extends ItemStackHandler {

		private boolean allow;
		private final TileItemHandler tile;

		ItemHandler(TileItemHandler tile, boolean allow) {
			this.tile = tile;
			this.allow = allow;
		}

		@Override
		protected int getStackLimit(int slot, ItemStack stack) {
			return 1;
		}

		@Override
		public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
			if (allow) {
				return super.insertItem(slot, stack, simulate);
			} else return stack;
		}

		@Override
		public ItemStack extractItem(int slot, int amount, boolean simulate) {
			if (allow) {
				return super.extractItem(slot, amount, simulate);
			} else return null;
		}

		@Override
		public void onContentsChanged(int slot) {
			tile.markDirty();
		}
	}

	@Override
	public void update() {

	}
}
