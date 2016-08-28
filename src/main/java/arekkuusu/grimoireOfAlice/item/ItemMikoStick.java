/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.item;

import java.util.List;

import arekkuusu.grimoireOfAlice.tmp.CleanupDone;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

@CleanupDone
public class ItemMikoStick extends ItemGOASword {

	ItemMikoStick(ToolMaterial material) {
		super(material);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.uncommon;
	}

	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(EnumChatFormatting.GOLD + "Property of Toyosatomimi no Miko");
		list.add(EnumChatFormatting.ITALIC + "Better than a bucket of water");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(player.experienceLevel < 30) {
			player.setFire(120);
		}
		else {
			player.addExperienceLevel(-1);
			player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 60, 4));
		}
		player.setItemInUse(stack, getMaxItemUseDuration(stack));
		return stack;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase user) {
		target.extinguish();
		return true;
	}
}
