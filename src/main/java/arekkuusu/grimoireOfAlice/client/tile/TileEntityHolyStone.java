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

public class TileEntityHolyStone extends TileEntity{

	public boolean anyPlayerInRange(){
		boolean isRain = worldObj.isRaining();
		if(isRain){
			EntityPlayer player = this.worldObj.getClosestPlayer(this.xCoord, this.yCoord, this.zCoord, 10);
			if(player!=null){
				player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 50, 1));
				player.addPotionEffect(new PotionEffect(Potion.jump.id, 50, 1));
				}
			else {
				return false;
				}
			return true;
		} else {
			return false;
			}
	}
	
    public void updateEntity() {
        if (this.anyPlayerInRange()) {
            if (this.worldObj.isRemote) {
                double yus1 = (double)((float)this.xCoord + this.worldObj.rand.nextFloat());
                double yus3 = (double)((float)this.yCoord + this.worldObj.rand.nextFloat());
                double yus5 = (double)((float)this.zCoord + this.worldObj.rand.nextFloat());
                this.worldObj.spawnParticle("smoke", yus1, yus3, yus5, 0.0D, 0.0D, 0.0D);
                this.worldObj.spawnParticle("flame", yus1, yus3, yus5, 0.0D, 0.0D, 0.0D);
            	}
            }
        }
}
