/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.client.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;

public class TileEntityHolyStone extends TileEntity {

	private boolean anyPlayerInRange() {
		boolean isRain = worldObj.isRaining();
		if(isRain) {
			EntityPlayer player = worldObj.getClosestPlayer(xCoord, yCoord, zCoord, 10);
			if(player != null) {
				player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 50, 1));
				player.addPotionEffect(new PotionEffect(Potion.jump.id, 50, 1));
				return true;

			}
		}

		return false;
	}

	@Override
	public void updateEntity() {
		if(anyPlayerInRange()) {
			if(worldObj.isRemote) {
				double yus1 = xCoord + worldObj.rand.nextFloat();
				double yus3 = yCoord + worldObj.rand.nextFloat();
				double yus5 = zCoord + worldObj.rand.nextFloat();
				worldObj.spawnParticle("smoke", yus1, yus3, yus5, 0.0D, 0.0D, 0.0D);
				worldObj.spawnParticle("flame", yus1, yus3, yus5, 0.0D, 0.0D, 0.0D);
			}
		}
	}
}
