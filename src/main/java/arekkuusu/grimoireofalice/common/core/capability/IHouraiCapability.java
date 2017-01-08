/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of Stratoprism. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Stratoprism
 *
 * Stratoprism is Open Source and distributed under the
 * MIT Licence: https://github.com/ArekkuusuJerii/Stratoprism/blob/master/LICENSE
 */
package arekkuusu.grimoireofalice.common.core.capability;

import arekkuusu.grimoireofalice.common.core.net.MessageHouraiUpdate;
import arekkuusu.grimoireofalice.common.core.net.PacketHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

public interface IHouraiCapability {

	/**
	 * Do NOT use unless you want to give an initial amount.
	 *
	 * @param level Initial level, must be more than 0 and less than max
	 */
	void setHouraiLevel(byte level);

	byte getHouraiLevel();

	byte getMaxHouraiLevel();

	/**
	 * Sets a Hourai level to a player
	 *
	 * @param player Player
	 * @param level Amount
	 */
	default void setHouraiLevel(EntityPlayer player, byte level) {
		setHouraiLevel(level);
		dataChanged(player);
	}

	NBTTagCompound saveNBTData();

	void loadNBTData(NBTTagCompound compound);

	/**
	 * Called when a player joins a world
	 *
	 * @param player Player
	 */
	default void entitySpawned(EntityPlayer player) {
		dataChanged(player);
	}

	/**
	 * For use with Players
	 *
	 * @param player Player
	 */
	default void dataChanged(EntityPlayer player) {
		if (player instanceof EntityPlayerMP)
			PacketHandler.sendTo((EntityPlayerMP)player, new MessageHouraiUpdate(saveNBTData(), player));
	}
}
