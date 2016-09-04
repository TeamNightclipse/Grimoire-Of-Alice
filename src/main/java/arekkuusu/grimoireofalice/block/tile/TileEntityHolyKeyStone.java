/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.block.tile;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;

public class TileEntityHolyKeyStone extends TileEntityRangeEffect {

	@Override
	protected void addPlayerEffect(EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 50, 3));
		player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 50, 3));
		player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 50, 3));
	}

	@Override
	protected void ifNear(EntityPlayer player) {
		if(worldObj.isRemote) {
			int xCoord = pos.getX();
			int yCoord = pos.getY();
			int zCoord = pos.getZ();
			Random random = worldObj.rand;
			double d0 = 0.0625D;
			for(int l = 0; l < 6; ++l) {
				double d1 = xCoord + random.nextFloat();
				double d2 = yCoord + random.nextFloat();
				double d3 = zCoord + random.nextFloat();
				if(l == 0 && !(worldObj.getBlockLightOpacity(new BlockPos(xCoord, yCoord + 1, zCoord))==0)) d2 = yCoord + 1 + d0;
				if(l == 1 && !(worldObj.getBlockLightOpacity(new BlockPos(xCoord, yCoord - 1, zCoord))==0)) d2 = yCoord - d0;
				if(l == 2 && !(worldObj.getBlockLightOpacity(new BlockPos(xCoord, yCoord, zCoord + 1))==0)) d3 = zCoord + 1 + d0;
				if(l == 3 && !(worldObj.getBlockLightOpacity(new BlockPos(xCoord, yCoord - 1, zCoord))==0)) d3 = zCoord - d0;
				if(l == 4 && !(worldObj.getBlockLightOpacity(new BlockPos(xCoord + 1, yCoord, zCoord))==0)) d1 = xCoord + 1 + d0;
				if(l == 5 && !(worldObj.getBlockLightOpacity(new BlockPos(xCoord - 1, yCoord, zCoord))==0)) d1 = xCoord - d0;
				if(d1 < xCoord || d1 > xCoord + 1 || d2 < 0.0D || d2 > yCoord + 1 || d3 < zCoord || d3 > zCoord + 1) {
					worldObj.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, d1, d2, d3, 0.0D, 0.0D, 0.0D, 0);
				}
			}
		}
	}

	@Override
	protected int getRange() {
		return 3;
	}
}
