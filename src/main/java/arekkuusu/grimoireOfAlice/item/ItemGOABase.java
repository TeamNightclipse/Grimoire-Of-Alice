/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemGOABase extends Item {

	private final EnumRarity rarity;
	private final boolean effect;

	public ItemGOABase() {
		this(EnumRarity.common);
	}

	public ItemGOABase(EnumRarity rarity) {
		this(rarity, false);
	}

	public ItemGOABase(EnumRarity rarity, boolean hasEffect) {
		super();
		effect = hasEffect;
		this.rarity = rarity;
	}

	@Override
	public EnumRarity getRarity(ItemStack p_77613_1_) {
		return rarity;
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack, int pass) {
		return effect || super.hasEffect(par1ItemStack, pass);
	}
}
