package arekkuusu.grimoireOfAlice.plugin.touhou;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import arekkuusu.grimoireOfAlice.GrimoireOfAlice;
import arekkuusu.grimoireOfAlice.lib.LibGuiID;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSpellCardPouch extends Item{

	public ItemSpellCardPouch() {
		super();
		setMaxStackSize(1);
		setCreativeTab(GrimoireOfAlice.CREATIVE_TAB);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 1;
	}
   
    	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			if (!player.isSneaking()) {
				player.openGui(GrimoireOfAlice.instance, LibGuiID.POUCH_BAG, world, 0, 0, 0);
			}
		}
		
		return itemstack;
	}
	
}
