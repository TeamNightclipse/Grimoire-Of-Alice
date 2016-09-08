/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.tile;

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
			Random random = worldObj.rand;
			double d0 = 0.0625D;
			for(int l = 0; l < 6; ++l) {
				double posX = getPos().getX();
				double posY = getPos().getY();
				double posZ = getPos().getZ();
				double d1 = posX + random.nextFloat();
				double d2 = posY + random.nextFloat();
				double d3 = posZ + random.nextFloat();
				if(l == 0 && !worldObj.getBlockState(new BlockPos(posX, posY + 1, posZ)).isOpaqueCube()) d2 = posY + 1 + d0;
				if(l == 1 && !worldObj.getBlockState(new BlockPos(posX, posY - 1, posZ)).isOpaqueCube()) d2 = posY - d0;
				if(l == 2 && !worldObj.getBlockState(new BlockPos(posX, posY, posZ + 1)).isOpaqueCube()) d3 = posZ + 1 + d0;
				if(l == 3 && !worldObj.getBlockState(new BlockPos(posX, posY, posZ - 1)).isOpaqueCube()) d3 = posZ - d0;
				if(l == 4 && !worldObj.getBlockState(new BlockPos(posX + 1, posY, posZ)).isOpaqueCube()) d1 = posX + 1 + d0;
				if(l == 5 && !worldObj.getBlockState(new BlockPos(posX - 1, posY, posZ)).isOpaqueCube()) d1 = posX - d0;
				if(d1 < posX || d1 > posX + 1 || d2 < 0.0D || d2 > posZ + 1 || d3 < posZ || d3 > posZ + 1) {
					worldObj.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, d1, d2, d3, 0.0D, 0.0D, 0.0D);
				}
			}
		}
	}

	@Override
	protected int getRange() {
		return 3;
	}
}
