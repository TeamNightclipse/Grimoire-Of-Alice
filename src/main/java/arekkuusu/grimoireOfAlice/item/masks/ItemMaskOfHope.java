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
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

@CleanupDone
public class ItemMaskOfHope extends ItemMask {

	public ItemMaskOfHope(ArmorMaterial p_i45325_1_, int p_i45325_2_) {
		super(p_i45325_1_, p_i45325_2_, LibMod.MODID + ":textures/models/armor/MaskOfHope.png");
	}

	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(EnumChatFormatting.DARK_AQUA + "Tsukumogami of Jizo Statue");
		list.add(EnumChatFormatting.DARK_GRAY + "Return to Kokoro if found... or not...");

		if(player.experienceLevel <= 120) {
			list.add(EnumChatFormatting.LIGHT_PURPLE + " -Wither");
		}
		else {
			list.add(EnumChatFormatting.LIGHT_PURPLE + " -Poison.");
		}

		list.add(EnumChatFormatting.DARK_PURPLE + " *Vulnerable to Wither");
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
		if(player.experienceLevel <= 120) {
			player.addPotionEffect(new PotionEffect(Potion.wither.id, 666, 4));
		}
		else {
			player.addPotionEffect(new PotionEffect(Potion.poison.id, 666, 4));
		}
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.rare;
	}
}
