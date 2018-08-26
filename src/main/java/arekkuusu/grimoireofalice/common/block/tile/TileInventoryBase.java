/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.block.tile;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class TileInventoryBase extends TileEntity {

	protected final InventoryHandler handler;

	public TileInventoryBase(int size) {
		handler = new InventoryHandler(this, size);
	}

	public void handleItemTransfer(EntityPlayer player, EnumHand hand) {
		ItemStack held = player.getHeldItem(hand);
		if(!held.isEmpty()) {
			if(hasItemStack()) {
				ItemStack insert = held.copy();
				insert.setCount(1);

				held.shrink(1);

				ItemHandlerHelper.giveItemToPlayer(player, getItemStack());
				setItemStack(insert);
			} else {
				ItemStack insert = held.copy();
				insert.setCount(1);

				held.shrink(1);

				setItemStack(insert);
			}
		} else {
			player.setHeldItem(hand, getItemStack());
			setItemStack(ItemStack.EMPTY);
		}
	}

	public boolean hasItemStack() {
		return !getItemStack().isEmpty();
	}

	public ItemStack getItemStack() {
		return handler.getStackInSlot(0);
	}

	public void setItemStack(ItemStack stack) {
		handler.setStackInSlot(0, stack);
		handler.onContentsChanged(0);
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(getPos(), 0, getUpdateTag());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		readNBT(tag);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag = super.writeToNBT(tag);
		writeNBT(tag);
		return tag;
	}

	public void destroy() {
		if(!world.isRemote) {
			ItemStack output = handler.extractItem(0, 1, false);
			if(!output.isEmpty()) {
				EntityItem outputItem = new EntityItem(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, output);
				world.spawnEntity(outputItem);
			}
		}
	}

	private void readNBT(NBTTagCompound tagCompound) {
		handler.deserializeNBT(tagCompound);
	}

	private void writeNBT(NBTTagCompound tagCompound) {
		tagCompound.merge(handler.serializeNBT());
	}

	@Override
	public boolean hasCapability(@Nonnull Capability<?> cap, @Nullable EnumFacing side) {
		return cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(cap, side);
	}

	@Nullable
	@Override
	public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing side) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY
				? CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(handler)
				: super.getCapability(capability, side);
	}

	public static class InventoryHandler extends ItemStackHandler {

		private final TileInventoryBase tile;

		InventoryHandler(TileInventoryBase tile, int size) {
			super(size);
			this.tile = tile;
		}

		@Override
		protected int getStackLimit(int slot, ItemStack stack) {
			return 1;
		}

		@Override
		public void onContentsChanged(int slot) {
			IBlockState state = tile.world.getBlockState(tile.getPos());
			tile.world.notifyBlockUpdate(tile.getPos(), state, state, 8);
			tile.markDirty();
		}
	}
}
