/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of Stratoprism. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Stratoprism
 *
 * Stratoprism is Open Source and distributed under the
 * MIT Licence: https://github.com/ArekkuusuJerii/Stratoprism/blob/master/LICENSE
 */
package arekkuusu.grimoireofalice.api.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ITileItemHolder {
	boolean addItem(EntityPlayer player, ItemStack stack);
	boolean removeItem(EntityPlayer player);
	void destroy();
}
