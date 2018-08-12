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

public class ItemTrumpet extends ItemInstrument  {

	public ItemTrumpet() {
		super(LibName.MERLIN_TRUMPET);
		setMaxDamage(100);
	}

	@Override
	public double getVelocity() {
		return 1D;
	}

	@Override
	public float getSize() {
		return 0.5F;
	}

	@Override
	public double getDistance() {
		return 0.4D;
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == Items.GOLD_INGOT;
	}
}
