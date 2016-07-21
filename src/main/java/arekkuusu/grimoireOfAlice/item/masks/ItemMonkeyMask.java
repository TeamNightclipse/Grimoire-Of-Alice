/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.item.masks;

import java.util.List;

import arekkuusu.grimoireOfAlice.lib.LibMod;
import arekkuusu.grimoireOfAlice.tmp.CleanupDone;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;

@CleanupDone
public class ItemMonkeyMask extends ItemMask {

	public ItemMonkeyMask(ArmorMaterial material, int p_i45325_2_) {
		super(material, p_i45325_2_, LibMod.MODID + ":textures/models/armor/MonkeyMask.png");
	}

	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(EnumChatFormatting.DARK_AQUA + "Tsukumogami of Awkwardness");

		if(player.experienceLevel >= 60) {
			list.add(EnumChatFormatting.LIGHT_PURPLE + " -Invisibility");
		}

		list.add(EnumChatFormatting.LIGHT_PURPLE + " -Slowness III");
		list.add(EnumChatFormatting.DARK_PURPLE + " *Invulnerable to Most Damage");
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
		if(player.experienceLevel <= 60) {
			player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 0, 2));
		}
		else {
			player.addPotionEffect(new PotionEffect(Potion.invisibility.id, 80, 0));
			player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 0, 2));
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
