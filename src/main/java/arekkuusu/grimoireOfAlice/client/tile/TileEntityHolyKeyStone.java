/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.client.tile;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;

public class TileEntityHolyKeyStone extends TileEntity {

	private boolean anyPlayerInRange() {
		boolean isRain = worldObj.isRaining();
		if(isRain) {
			EntityPlayer player = worldObj.getClosestPlayer(xCoord, yCoord, zCoord, 3);
			if(player != null) {
				player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 50, 3));
				player.addPotionEffect(new PotionEffect(Potion.digSpeed.getId(), 50, 3));
				player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 50, 3));
				return true;
			}
		}

		return false;
	}

	@Override
	public void updateEntity() {
		if(anyPlayerInRange()) {
			if(worldObj.isRemote) {
				Random random = worldObj.rand;
				double d0 = 0.0625D;
				for(int l = 0; l < 6; ++l) {
					double d1 = xCoord + random.nextFloat();
					double d2 = yCoord + random.nextFloat();
					double d3 = zCoord + random.nextFloat();
					if(l == 0 && !worldObj.getBlock(xCoord, yCoord + 1, zCoord).isOpaqueCube()) d2 = yCoord + 1 + d0;
					if(l == 1 && !worldObj.getBlock(xCoord, yCoord - 1, zCoord).isOpaqueCube()) d2 = yCoord - d0;
					if(l == 2 && !worldObj.getBlock(xCoord, yCoord, zCoord + 1).isOpaqueCube()) d3 = zCoord + 1 + d0;
					if(l == 3 && !worldObj.getBlock(xCoord, yCoord, zCoord - 1).isOpaqueCube()) d3 = zCoord - d0;
					if(l == 4 && !worldObj.getBlock(xCoord + 1, yCoord, zCoord).isOpaqueCube()) d1 = xCoord + 1 + d0;
					if(l == 5 && !worldObj.getBlock(xCoord - 1, yCoord, zCoord).isOpaqueCube()) d1 = xCoord - d0;
					if(d1 < xCoord || d1 > xCoord + 1 || d2 < 0.0D || d2 > yCoord + 1 || d3 < zCoord || d3 > zCoord + 1) {
						worldObj.spawnParticle("enchantmenttable", d1, d2, d3, 0.0D, 0.0D, 0.0D);
					}
				}
			}
		}
	}
}
