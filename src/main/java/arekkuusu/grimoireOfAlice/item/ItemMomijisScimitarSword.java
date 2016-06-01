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
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemMomijisScimitarSword extends ItemSword{

	public ItemMomijisScimitarSword(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(EnumChatFormatting.AQUA
				+ "Awwww~!! Momiji~!!! Wooooff!!! ");
		p_77624_3_.add(EnumChatFormatting.GOLD
				+ "Awooooo~!!");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onUpdate(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_){
		
		super.onUpdate(p_77663_1_, p_77663_2_, p_77663_3_, p_77663_4_, p_77663_5_);{
			EntityPlayer player = (EntityPlayer) p_77663_3_;
			ItemStack equiped = player.getCurrentEquippedItem();
			if (equiped == p_77663_1_){
				player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 0, 0));
				
			}
		}
	}
}
