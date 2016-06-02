/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.item;

import arekkuusu.grimoireOfAlice.client.gui.GuiScreenYoukaiBook;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.world.World;

public class ItemYoukaiBook extends ItemWritableBook {

	ItemYoukaiBook() {
		super();
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabMisc);
	}


	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player) {
		if(player.worldObj.isRemote) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiScreenYoukaiBook(player, item, true));
		}
		return item;
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.uncommon;
	}
}
