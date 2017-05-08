/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.block.tile;

import javax.annotation.Nullable;

import arekkuusu.grimoireofalice.api.tile.ITileItemHolder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.items.ItemHandlerHelper;

public class TilePillarAltar extends TileItemHandler implements ITickable {

	public int tickCount;

	@Override
	public boolean addItem(@Nullable EntityPlayer player, ItemStack stack) {
		boolean added = false;
		if(!hasItem()) {
            world.playSound(null, getPos(), SoundEvents.ENTITY_ITEMFRAME_ADD_ITEM, SoundCategory.BLOCKS, 1F, 0.5F);
			added = true;
			ItemStack stackToAdd = stack.copy();
			stackToAdd.stackSize = 1;
			if(!world.isRemote) {
				itemHandler.insertItem(0, stackToAdd, false);
			}

			if(player == null || !player.capabilities.isCreativeMode) {
				stack.stackSize--;
				if(stack.stackSize == 0 && player != null) {
					player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
				}
			}
		}
		return added;
	}

	@Override
	public boolean removeItem(@Nullable EntityPlayer player) {
		boolean removed = false;
		if (hasItem()) {
            world.playSound(null, getPos(), SoundEvents.ENTITY_ITEMFRAME_REMOVE_ITEM, SoundCategory.BLOCKS, 1F, 0.5F);
			removed = true;

			ItemStack stackToTake = itemHandler.extractItem(0, 1, false);
			if (player != null && !player.capabilities.isCreativeMode) {
				ItemHandlerHelper.giveItemToPlayer(player, stackToTake, player.inventory.currentItem);
			}
		}
		return removed;
	}

	@Override
	public void destroy() {
		if (!world.isRemote) {
			ItemStack output = itemHandler.extractItem(0, 1, false);
			if (output != null) {
				EntityItem outputItem = new EntityItem(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, output);
                world.spawnEntityInWorld(outputItem);
			}
		}
	}

	@Override
	public boolean hasItem() {
		return itemHandler.getStackInSlot(0) != null;
	}

	public ItemStack getItemStack() {
		return itemHandler.getItemSimulate(0);
	}

	public float getRenderHeight() {
		float height = getTileData().getFloat("RenderHeight");
		return height == 0 ? 1.4F : height;
	}

	public TilePillarAltar setRenderHeight(float renderHeight) {
		NBTTagCompound tag = getTileData();
		tag.setFloat("RenderHeight", renderHeight);
		return this;
	}

	@Override
	public void update() {
		tickCount++;
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}
}
