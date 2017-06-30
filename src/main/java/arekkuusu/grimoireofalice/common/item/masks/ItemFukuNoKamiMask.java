/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item.masks;

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

import java.util.List;

public class ItemFukuNoKamiMask extends ItemModMask {

	@SideOnly(Side.CLIENT)
	private ModelBiped model;

	public ItemFukuNoKamiMask(ArmorMaterial material, int dmg) {
		super(material, dmg, LibItemName.FUKUNOKAMI_MASK);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.DARK_AQUA + I18n.format("grimoire.tooltip.fuku_no_kami_mask_header.name"));

		if(player.experienceLevel >= 80) {
			list.add(TextFormatting.GOLD + I18n.format("grimoire.tooltip.fuku_no_kami_mask_good_buff_one.name"));
			list.add(TextFormatting.GOLD + I18n.format("grimoire.tooltip.fuku_no_kami_mask_good_buff_two.name"));
		}
		else {
			list.add(TextFormatting.LIGHT_PURPLE + I18n.format("grimoire.tooltip.fuku_no_kami_mask_bad_buff.name"));
		}

		list.add(TextFormatting.DARK_PURPLE + I18n.format("grimoire.tooltip.fuku_no_kami_mask_vulnerable.name"));
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
		if(player.experienceLevel <= 80) {
			player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 200, 0));
		}
		else {
			player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 0, 0));
			player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 0, 0));
		}
	}

	@Override
	public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		if(source.equals(DamageSource.WITHER)) {
			player.attackEntityFrom(DamageSource.GENERIC, (float)damage * 10);
			return new ArmorProperties(0, 0, 0);
		}
		else {
			return new ArmorProperties(4, 10, 4);
		}
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		if(source.equals(DamageSource.WITHER)) {
			stack.damageItem(damage * 10, entity);
		}
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return ResourceLocations.FUKU_NO_KAMI_MASK.toString();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped imodel) {
		if (model == null) model = new ModelMask();
		model.setModelAttributes(imodel);
		return model;
	}
}
