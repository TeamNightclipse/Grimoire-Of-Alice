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

import arekkuusu.grimoireofalice.common.core.helper.MathUtil;
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
			stackToAdd.setCount(1);
			if(!world.isRemote) {
				itemHandler.insertItem(0, stackToAdd, false);
			}

			if(player == null || !player.capabilities.isCreativeMode) {
				stack.shrink(1);
				if(stack.isEmpty() && player != null) {
					player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
				}
			}
		}
		return added;
	}

	public ItemStack getItemStack() {
		return itemHandler.getItemSimulate(0);
	}

	public float getRenderHeight() {
		float height = getTileData().getFloat("RenderHeight");
		return MathUtil.fuzzyEqual(height, 0F) ? 1.4F : height;
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
