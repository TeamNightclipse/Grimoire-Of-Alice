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

import arekkuusu.grimoireofalice.client.ResourceLocations;
import arekkuusu.grimoireofalice.client.model.ModelMask;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemUbaMask extends ItemModMask {

	@SideOnly(Side.CLIENT)
	private ModelBiped model;

	public ItemUbaMask(ArmorMaterial material, int dmg) {
		super(material, dmg, LibItemName.UBA_MASK);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.DARK_AQUA + I18n.format("grimoire.tooltip.uba_mask_header.name"));

		if(player.experienceLevel <= 90) {
			list.add(TextFormatting.LIGHT_PURPLE + I18n.format("grimoire.tooltip.uba_mask_bad_buff_one.name"));
			list.add(TextFormatting.LIGHT_PURPLE + I18n.format("grimoire.tooltip.uba_mask_bad_buff_two.name"));
		}
		else {
			list.add(TextFormatting.LIGHT_PURPLE + I18n.format("grimoire.tooltip.uba_mask_bad_buff_tree.name"));
		}

		list.add(TextFormatting.DARK_PURPLE + I18n.format("grimoire.tooltip.uba_mask_vulnerable.name"));
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
		if(player.experienceLevel <= 90) {
			player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 0, 4));
			player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 0, 0));
		}
		else {
			player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 0, 0));
		}
	}

	@Override
	public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		if(source.isExplosion()) {
			player.attackEntityFrom(DamageSource.generic, (float)damage * 10);
			return new ArmorProperties(0, 0, 0);
		}
		else {
			return new ArmorProperties(4, 10, 50);
		}
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		if(source.isExplosion()) {
			stack.damageItem(damage * 10, entity);
		}
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return ResourceLocations.UBA_MASK.toString();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped imodel) {
		if (model == null) model = new ModelMask();
		model.setModelAttributes(imodel);
		return model;
	}
}
