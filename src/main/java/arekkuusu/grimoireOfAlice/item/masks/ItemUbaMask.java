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
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemUbaMask extends ItemMask {

	public ItemUbaMask(ArmorMaterial material, int p_i45325_2_) {
		super(material, p_i45325_2_, LibMod.MODID + ":textures/models/armor/UbaMask.png");
	}

	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(EnumChatFormatting.DARK_AQUA + "Tsukumogami of Phatos");

		if(player.experienceLevel <= 90) {
			list.add(EnumChatFormatting.LIGHT_PURPLE + " -Dig Slowdown");
			list.add(EnumChatFormatting.LIGHT_PURPLE + " -Blindness");
		}
		else {
			list.add(EnumChatFormatting.LIGHT_PURPLE + " -Melancholy");
		}

		list.add(EnumChatFormatting.DARK_PURPLE + " *Vulnerable to Magic");
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
		if(player.experienceLevel <= 90) {
			player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 0, 4));
			player.addPotionEffect(new PotionEffect(Potion.blindness.id, 0, 0));
		}
		else {
			player.addPotionEffect(new PotionEffect(Potion.weakness.id, 0, 2));
		}
	}
}
