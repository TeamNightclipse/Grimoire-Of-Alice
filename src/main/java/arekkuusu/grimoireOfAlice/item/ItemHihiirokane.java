package arekkuusu.grimoireOfAlice.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemHihiirokane extends ItemGOABase {

	ItemHihiirokane() {
		super(EnumRarity.uncommon);
		setMaxStackSize(32);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(EnumChatFormatting.DARK_AQUA + "Crimson ore");
		list.add(EnumChatFormatting.GOLD + "Red-colored mythic metal depicted in some ancient Japanese");
		list.add(EnumChatFormatting.GOLD + "legends that allegedly possessed strange physical properties");
		list.add(EnumChatFormatting.GOLD + "and could be used to forge some extraordinary metal alloys.");
	}
	
}
