/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;

public class TileEntityHolyStone extends TileEntityRangeEffect {

	@Override
	protected void addPlayerEffect(EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 50, 1));
		player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 50, 1));
	}

	@Override
	protected void ifNear(EntityPlayer player) {
		if(worldObj.isRemote) {
			double yus1 = getPos().getX() + worldObj.rand.nextFloat();
			double yus3 = getPos().getY() + worldObj.rand.nextFloat();
			double yus5 = getPos().getZ() + worldObj.rand.nextFloat();
			worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, yus1, yus3, yus5, 0.0D, 0.0D, 0.0D);
			worldObj.spawnParticle(EnumParticleTypes.FLAME, yus1, yus3, yus5, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	protected int getRange() {
		return 10;
	}
}
