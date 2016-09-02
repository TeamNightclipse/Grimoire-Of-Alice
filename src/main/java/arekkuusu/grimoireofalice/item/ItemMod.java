package arekkuusu.grimoireofalice.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import arekkuusu.grimoireofalice.GOACreativeTab;
import arekkuusu.grimoireofalice.lib.LibMod;

public class ItemMod extends Item {

	public ItemMod() {
		super();
		setCreativeTab(GOACreativeTab.INSTANCE);
	}

	@Override
	public Item setUnlocalizedName(String par1Str) {
		GameRegistry.registerItem(this, par1Str);
		return super.setUnlocalizedName(par1Str);
	}

	@Override
	public String getUnlocalizedNameInefficiently(ItemStack par1ItemStack) {
		return super.getUnlocalizedNameInefficiently(par1ItemStack).replaceAll("item\\.", "item." + LibMod.MODNAME);
	}

}
