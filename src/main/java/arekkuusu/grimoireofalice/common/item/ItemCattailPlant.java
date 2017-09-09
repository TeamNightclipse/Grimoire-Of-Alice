/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.core.format.FormattedString;
import arekkuusu.grimoireofalice.common.core.format.ItemFlavor;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.entity.living.TouhouCharacter;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional;

public class ItemCattailPlant extends ItemBaseSwordFlavored implements IOwnedBy {

	public ItemCattailPlant(ToolMaterial material) {
		super(material, LibItemName.CATTAIL_PLANT, ItemFlavor.of(FormattedString.withItalics("grimoire.tooltip.cattail_plant_header.name")));
		setNoRepair();
	}

	@Override
	public TouhouCharacter character(ItemStack stack) {
		return TouhouCharacter.NITORI_KAWASHIRO;
	}
}
