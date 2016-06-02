/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.client.tile;

import java.util.Optional;

import net.minecraft.entity.player.EntityPlayer;

public abstract class TileEntityRangeEffect extends TileEntityBase {

	protected Optional<EntityPlayer> getPlayerInRange(int range, boolean requireRain) {
		boolean valid = true;

		if(requireRain) {
			valid = worldObj.isRaining();
		}

		if(valid) {
			Optional.ofNullable(worldObj.getClosestPlayer(xCoord, yCoord, zCoord, range));
		}

		return Optional.empty();
	}

	protected abstract void addPlayerEffect(EntityPlayer player);
}
