/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.plugin.danmakucore.item;

import java.util.List;

import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.entity.living.TouhouCharacter;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPiano extends ItemInstrument implements IOwnedBy {

	public ItemPiano() {
		super(LibItemName.LYRICA_PIANO);
		setMaxDamage(150);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.piano_header.name"));
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.piano_use.name"));
	}

	@Override
	public double getVelocity() {
		return 0.8D;
	}

	@Override
	public float getSize() {
		return 2F;
	}

	@Override
	public double getDistance() {
		return 2D;
	}

	@Override
	public TouhouCharacter character(ItemStack stack) {
		return TouhouCharacter.LYRICA_PRIMSRIVER;
	}
}
