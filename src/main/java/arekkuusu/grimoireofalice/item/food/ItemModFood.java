/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item.food;

import arekkuusu.grimoireofalice.GrimoireOfAlice;
import net.minecraft.item.ItemFood;

public class ItemModFood extends ItemFood {

	public ItemModFood(int healAmount, float potionEffectProbability, boolean isWolfFood, String id) {
		super(healAmount, potionEffectProbability, isWolfFood);
		setRegistryName(id);
		setUnlocalizedName(id);
		setCreativeTab(GrimoireOfAlice.CREATIVE_TAB);
	}
}
