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

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemMikoStick extends ItemSword{

	public ItemMikoStick(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.uncommon;
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(EnumChatFormatting.WHITE
				+ "Oh look! A stick! Uh-oh...");
		p_77624_3_.add(EnumChatFormatting.GOLD
				+ "Property of Toyosatomimi no Miko");
		p_77624_3_.add(EnumChatFormatting.RED
				+ "Use with caution, its a little");
		p_77624_3_.add(EnumChatFormatting.RED
				+ "hot to the touch");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_){
		if(p_77659_3_.experienceLevel < 40){
			p_77659_3_.setFire(120);
		} else {
			p_77659_3_.addExperienceLevel(-1);
			p_77659_3_.addPotionEffect(new PotionEffect(Potion.regeneration.id, 60, 4));
		}
        p_77659_3_.setItemInUse(p_77659_1_, this.getMaxItemUseDuration(p_77659_1_));
        return p_77659_1_;
    }
	
	@Override
	public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_) {
		p_77644_2_.extinguish();
		return true;
    }
	
}
