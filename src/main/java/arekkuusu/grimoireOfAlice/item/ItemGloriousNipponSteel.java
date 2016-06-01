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

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemGloriousNipponSteel extends ItemGOABase{

	public ItemGloriousNipponSteel() {
		super();
		setMaxStackSize(16);
		setCreativeTab(CreativeTabs.tabMaterials);
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(EnumChatFormatting.DARK_AQUA
				+ "100% quality guaranteed");
		p_77624_3_.add(EnumChatFormatting.GOLD
				+ "Glorious Nippon Steel");
		p_77624_3_.add(EnumChatFormatting.GOLD
				+ "Flolded over 1000 times");
		p_77624_3_.add(EnumChatFormatting.GOLD
				+ "Can cut through anything");

	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.uncommon;
	}
	
}
