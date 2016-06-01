/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;

public class ItemEllyScythe extends ItemSword {

	public ItemEllyScythe(ToolMaterial material) {
		super(material);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.rare;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(EnumChatFormatting.WHITE + "War ma f�, heman zo eun Anko drouk");
		p_77624_3_.add(EnumChatFormatting.GOLD + "Oberour ar maro known as the grave");
		p_77624_3_.add(EnumChatFormatting.GOLD + "yard watcher, they said that he");
		p_77624_3_.add(EnumChatFormatting.GOLD + "protects the graveyard and the souls");
		p_77624_3_.add(EnumChatFormatting.GOLD + "around it for some unknown reason and");
		p_77624_3_.add(EnumChatFormatting.GOLD + "collects the lost souls on his land");
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase user) {
		EntityPlayer player = (EntityPlayer)user;
		if(player.experienceLevel > 40) {
			target.addPotionEffect(new PotionEffect(Potion.harm.id, 128, 0));
			target.addPotionEffect(new PotionEffect(Potion.blindness.id, 128, 0));
			user.addPotionEffect(new PotionEffect(Potion.heal.id, 128, 0));
			stack.damageItem(1, user);
		}
		else {
			target.addPotionEffect(new PotionEffect(Potion.heal.id, 128, 0));
			user.addPotionEffect(new PotionEffect(Potion.harm.id, 128, 0));
			user.addPotionEffect(new PotionEffect(Potion.blindness.id, 128, 0));
			stack.damageItem(1, user);
		}
		return true;
	}

}
