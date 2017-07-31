/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import java.util.List;

import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Optional.Interface(iface = "net.katsstuff.danmakucore.item.IOwnedBy", modid = "danmakucore")
public class ItemCattailPlant extends ItemModSword implements IOwnedBy {

	public ItemCattailPlant(ToolMaterial material) {
		super(material, LibItemName.CATTAIL_PLANT);
		setNoRepair();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.cattail_plant_header.name"));
	}

	@Optional.Method(modid = "danmakucore")
	@Override
	public net.katsstuff.danmakucore.entity.living.TouhouCharacter character(ItemStack stack) {
		return net.katsstuff.danmakucore.entity.living.TouhouCharacter.NITORI_KAWASHIRO;
	}
}
