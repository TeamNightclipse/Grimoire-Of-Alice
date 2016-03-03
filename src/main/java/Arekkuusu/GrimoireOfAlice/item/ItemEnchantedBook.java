/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimore Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package Arekkuusu.GrimoireOfAlice.item;

import java.util.List;

import Arekkuusu.GrimoireOfAlice.lib.LibMod;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemEnchantedBook extends ItemGOABase {

	public static final String[] NAMES = {"Demon", "Youkai", "Koakuma"};

	public ItemEnchantedBook() {
		super();
		setHasSubtypes(true);
	}

	public IIcon[] icons = new IIcon[NAMES.length];

	@Override
	public void registerIcons(IIconRegister reg) {
		for(int i = 0; i < NAMES.length; i++) {
			icons[i] = reg.registerIcon(LibMod.MODID + NAMES[i] + "Echanted Book");
		}

	}

	@Override
	public IIcon getIconFromDamage(int meta) {
		if(meta > NAMES.length - 1) {
			meta = 0;
		}
		return icons[meta];
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for(int i = 0; i < NAMES.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}

	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "item.EnhancedBook" + "_" + stack.getItemDamage();
	}
}
