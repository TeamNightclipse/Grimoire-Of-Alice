/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item.masks;

import java.util.List;

import arekkuusu.grimoireofalice.lib.LibItemName;
import arekkuusu.grimoireofalice.lib.LibMod;
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

public class ItemMonkeyMask extends ItemModMask {

	public ItemMonkeyMask(ArmorMaterial material, int dmg) {
		super(material, dmg, LibItemName.MONKEY_MASK);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.DARK_AQUA + "Tsukumogami of Awkwardness");

		if(player.experienceLevel >= 60) {
			list.add(TextFormatting.LIGHT_PURPLE + " -Invisibility");
		}

		list.add(TextFormatting.LIGHT_PURPLE + " -Slowness III");
		list.add(TextFormatting.DARK_PURPLE + " *Invulnerable to Most Damage");
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
		if(player.experienceLevel <= 60) {
			player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 0, 2));
		}
		else {
			player.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 80, 0));
			player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 0, 2));
		}
	}

	@Override
	public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		if(player instanceof EntityPlayer && (source.isProjectile() || source.isExplosion())) {
			player.attackEntityFrom(DamageSource.generic, (float)damage * 50);
		}
		return new ArmorProperties(1, 100, 100);
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		if(source.isProjectile() || source.isExplosion()) {
			stack.damageItem(damage * 10, entity);
		}
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return LibMod.MODID + ":textures/models/armor/monkeymask.png";
	}

}
