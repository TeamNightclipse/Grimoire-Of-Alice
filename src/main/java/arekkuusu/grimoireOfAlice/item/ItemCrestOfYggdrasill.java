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

import arekkuusu.grimoireOfAlice.block.GOABlock;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCrestOfYggdrasill extends ItemSword {
	
	public ItemCrestOfYggdrasill(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		// TODO Auto-generated constructor stub
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(EnumChatFormatting.WHITE
				+ "Contains time itself");
		p_77624_3_.add(EnumChatFormatting.GOLD
				+ "Once used by a great God to");
		p_77624_3_.add(EnumChatFormatting.GOLD
				+ "manipulate space and time");
		p_77624_3_.add(EnumChatFormatting.RED
				+ "EXTREMELY[WARNING]EXPLOSIVE");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.epic;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack) {
		return true;
	}
	
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
		p_77659_2_.spawnEntityInWorld(new EntityLightningBolt(p_77659_2_, p_77659_3_.posX+0.5, p_77659_3_.posY+3, p_77659_3_.posZ+0.5));
		return p_77659_1_;
	}
	
	
	public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_) {
		p_77644_2_.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 4));
		return true;
    }
	
	public boolean onBlockDestroyed(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_) {
		for(float i=0; i<5; i++) {
		p_150894_2_.createExplosion(null, p_150894_4_+0.5, p_150894_5_-4 + i, p_150894_6_+0.5, 8.0F, true);
		}
		return true;
	}
}
