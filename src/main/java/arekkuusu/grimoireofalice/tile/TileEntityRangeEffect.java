/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.tile;

import java.util.Optional;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ITickable;

public abstract class TileEntityRangeEffect extends TileEntityBase implements ITickable {

	protected Optional<EntityPlayer> getPlayerInRange() {
		boolean valid = true;

		if(requireRain()) {
			valid = worldObj.isRaining();
		}

		if(valid) {
			return Optional.ofNullable(worldObj.getClosestPlayer(getPos().getX(), getPos().getY(), getPos().getZ(), getRange(), false));
		}

		return Optional.empty();
	}

	@Override
	public void update() {
		Optional<EntityPlayer> optPlayer = getPlayerInRange();
		if(optPlayer.isPresent()) {
			EntityPlayer player = optPlayer.get();
			addPlayerEffect(player);
			ifNear(player);
		}
	}

	protected abstract void addPlayerEffect(EntityPlayer player);

	protected abstract void ifNear(EntityPlayer player);

	protected abstract int getRange();

	protected boolean requireRain() {
		return true;
	}
}
