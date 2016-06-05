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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

@CleanupDone
public class ItemHannyaMask extends ItemMask {

	public ItemHannyaMask(ArmorMaterial p_i45325_1_, int p_i45325_2_) {
		super(p_i45325_1_, p_i45325_2_, LibMod.MODID + ":textures/models/armor/HannyaMask.png");
	}

	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(EnumChatFormatting.DARK_AQUA + "Tsukumogami of Anger");
		p_77624_3_.add(EnumChatFormatting.GOLD + " -Strenght");
		p_77624_3_.add(EnumChatFormatting.DARK_PURPLE + " *Vulnerable to Magic");
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
		if(player.experienceLevel <= 100) {
			player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 0, 0));
		}
		else {
			player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 0, 4));
		}
	}
}
