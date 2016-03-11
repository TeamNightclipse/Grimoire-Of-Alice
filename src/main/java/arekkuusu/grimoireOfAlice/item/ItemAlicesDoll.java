/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.item;

import arekkuusu.grimoireOfAlice.entity.mob.EntityAlicesDoll;
import arekkuusu.grimoireOfAlice.entity.projectile.EntityThrowingExplosiveDoll;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAlicesDoll extends ItemGOABase {

	public ItemAlicesDoll() {
		super();
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
		
		if(!player.capabilities.isCreativeMode) {
			--stack.stackSize;
		}
		
		if(!world.isRemote) {
			world.spawnEntityInWorld(new EntityAlicesDoll(world, player));
		}

		return stack;
		
	}
	
}
