package arekkuusu.grimoireOfAlice.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemKokorosMasks extends ItemGOABase{

	public ItemKokorosMasks() {
		super();
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabMaterials);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.epic;
	}
	
}
