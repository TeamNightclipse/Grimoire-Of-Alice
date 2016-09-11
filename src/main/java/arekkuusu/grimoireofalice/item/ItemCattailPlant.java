package arekkuusu.grimoireofalice.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import arekkuusu.grimoireofalice.lib.LibItemName;

public class ItemCattailPlant extends ItemModSword {

	public ItemCattailPlant(ToolMaterial material) {
		super(material, LibItemName.CATTAILPLANT);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Nitori's Cattail Plant");
		list.add(TextFormatting.ITALIC + "She used big machinery just to pick it up...");
	}

}
