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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class ItemNueTrident extends ItemBaseSword  {

	public ItemNueTrident(ToolMaterial material) {
		super(material, LibName.NUE_TRIDENT);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase user) {
		stack.damageItem(1, user);
		target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 2400, 0));
		target.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 2400, 0));
		return true;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
