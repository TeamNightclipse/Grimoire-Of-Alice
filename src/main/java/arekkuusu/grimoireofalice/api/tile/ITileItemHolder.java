/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of Grimore-Of-Alice. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore-Of-Alice is Open Source and distributed under the
 * MIT Licence: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE
 */
package arekkuusu.grimoireofalice.api.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

/**
 * Interface for TileEntities that can hold Items
 */
public interface ITileItemHolder {

	/**
	 * Ads an ItemStack to the TileEntity
	 *
	 * @param player The player adding the ItemStack
	 * @param stack  The ItemStack added
	 * @return If it was added
	 */
	boolean addItem(@Nullable EntityPlayer player, ItemStack stack);

	/**
	 * Removes an Item from the TileEntity
	 *
	 * @param player The Player removing the ItemStack
	 * @return If it was removed
	 */
	boolean removeItem(@Nullable EntityPlayer player);

	/**
	 * @return If it has an Item
	 */
	boolean hasItem();

	/**
	 * Destroys the TileEntity
	 */
	void destroy();
}
