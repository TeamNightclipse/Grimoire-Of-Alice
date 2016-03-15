/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.item;

import java.util.List;

import arekkuusu.grimoireOfAlice.entity.mob.EntityAlicesDoll;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemAlicesDoll extends ItemGOABase {

	public ItemAlicesDoll() {
		super();
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_){
		
		p_77624_3_.add(EnumChatFormatting.DARK_AQUA
				+ "Alice's Curse ~ Eerily Luminous Shanghai Dolls");

	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

		if(!player.capabilities.isCreativeMode) {
			--stack.stackSize;
		}

		if(!world.isRemote) {
			world.spawnEntityInWorld(new EntityAlicesDoll(world, player));
		}

		return stack;
	}
}
