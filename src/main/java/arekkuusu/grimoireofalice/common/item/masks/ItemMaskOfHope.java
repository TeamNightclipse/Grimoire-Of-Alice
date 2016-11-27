/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item.masks;

import java.util.List;

import arekkuusu.grimoireofalice.common.lib.LibItemName;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMaskOfHope extends ItemModMask {

	public ItemMaskOfHope(ArmorMaterial material, int dmg) {
		super(material, dmg, LibItemName.MASKOF_HOPE);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.DARK_AQUA + I18n.format("grimoire.tooltip.mask_of_hope_header.name"));
		list.add(TextFormatting.DARK_GRAY + I18n.format("grimoire.tooltip.mask_of_hope_description.name"));

		if(player.experienceLevel <= 120) {
			list.add(TextFormatting.LIGHT_PURPLE + I18n.format("grimoire.tooltip.mask_of_hope_good_buff.name"));
		}
		else {
			list.add(TextFormatting.LIGHT_PURPLE + I18n.format("grimoire.tooltip.mask_of_hope_bad_buff.name"));
		}

		list.add(TextFormatting.DARK_PURPLE + I18n.format("grimoire.tooltip.mask_of_hope_vulnerable.name"));
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
		if(player.experienceLevel <= 120) {
			player.addPotionEffect(new PotionEffect(MobEffects.WITHER, 666, 4));
		}
		else {
			player.addPotionEffect(new PotionEffect(MobEffects.POISON, 666, 4));
		}
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return LibMod.MODID + ":textures/models/armor/maskofhope.png";
	}

}
