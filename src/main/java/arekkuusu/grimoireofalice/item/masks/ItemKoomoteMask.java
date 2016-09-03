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
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemKoomoteMask extends ItemModMask {

	public ItemKoomoteMask(ArmorMaterial material, int dmg) {
		super(material, dmg, EntityEquipmentSlot.HEAD, LibItemName.KOOMOTEMASK);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.DARK_AQUA + "Tsukumogami of Joy");

		if(player.experienceLevel >= 110) {
			list.add(TextFormatting.GOLD + " -Absorption");
		}
		else {
			list.add(TextFormatting.LIGHT_PURPLE + " -Hunger");
		}

		list.add(TextFormatting.DARK_PURPLE + " *Vulnerable to Explosions");
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
		if(player.experienceLevel <= 110) {
			Potion potion1 = Potion.REGISTRY.getObject(new ResourceLocation("")); //TODO: id 17
			if(potion1 != null) {
				player.addPotionEffect(new PotionEffect(potion1, 200, 2));
			}
		}
		else {
			Potion potion1 = Potion.REGISTRY.getObject(new ResourceLocation("")); //TODO: id 22
			if(potion1 != null) {
				player.addPotionEffect(new PotionEffect(potion1, 200, 2));
			}
		}
	}
	
	@Override
	public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		if (player instanceof EntityPlayer && source.isExplosion()) {
			player.attackEntityFrom(source.generic, (float)damage*2);
		}
		return new ArmorProperties(1, 5, 10);
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		if(source.isExplosion()){
			stack.damageItem(damage * 10, entity);
		}
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return LibMod.MODID + ":textures/models/armor/KoomoteMask.png";
    }
	
}