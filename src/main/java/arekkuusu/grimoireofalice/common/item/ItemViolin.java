/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.lib.LibName;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ItemViolin extends ItemInstrument  {

	public ItemViolin() {
		super(LibName.LUNASA_VIOLIN);
		setMaxDamage(50);
	}

	@Override
	public double getVelocity() {
		return 0.5D;
	}

	@Override
	public float getSize() {
		return 1F;
	}

	@Override
	public double getDistance() {
		return 1D;
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == Items.STRING;
	}
}
