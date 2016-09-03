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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMonkeyMask extends ItemModMask {

	public ItemMonkeyMask(ArmorMaterial material, int dmg) {
		super(material, dmg, EntityEquipmentSlot.HEAD, LibItemName.MONKEYMASK);
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
			Potion potion1 = Potion.REGISTRY.getObject(new ResourceLocation("")); //TODO: id 2
			if(potion1 != null) {
				player.addPotionEffect(new PotionEffect(potion1, 0, 2));
			}
		}
		else {
			Potion potion1 = Potion.REGISTRY.getObject(new ResourceLocation("")); //TODO: id 14
			Potion potion2 = Potion.REGISTRY.getObject(new ResourceLocation("")); //TODO: id 2
			if(potion1 != null && potion2 != null) {
				player.addPotionEffect(new PotionEffect(potion1, 80, 0));
				player.addPotionEffect(new PotionEffect(potion2, 0, 2));
			}
		}
	}
	
	@Override
	public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		if (player instanceof EntityPlayer && (source.isProjectile() || source.isExplosion())) {
			EntityPlayer Player = (EntityPlayer)player;
			player.attackEntityFrom(source.generic, (float)damage*50);
		}
		return new ArmorProperties(1, 100, 100);
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		if(source.isProjectile() || source.isExplosion()){
			stack.damageItem(damage * 10, entity);
		}
	}
}
