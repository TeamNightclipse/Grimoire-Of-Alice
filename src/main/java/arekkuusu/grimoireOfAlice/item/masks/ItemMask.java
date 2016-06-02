/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.item.masks;

import net.minecraft.entity.Entity;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemMask extends ItemArmor {

	private final String texture;

	public ItemMask(ArmorMaterial material, int p_i45325_2_, String texture) {
		super(material, p_i45325_2_, 0);
		this.texture = texture;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {

		if(stack.getItem() == this) {
			return texture;
		}
		else {
			return null;
		}
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.uncommon;
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack, int pass) {
		return true;
	}
}
