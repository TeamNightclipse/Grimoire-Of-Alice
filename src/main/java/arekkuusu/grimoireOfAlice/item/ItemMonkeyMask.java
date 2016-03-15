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
import arekkuusu.grimoireOfAlice.lib.LibMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;

public class ItemMonkeyMask extends ItemArmor{

	public ItemMonkeyMask(ArmorMaterial p_i45325_1_, int p_i45325_2_, int p_i45325_3_) {
		
		super(p_i45325_1_, p_i45325_2_, p_i45325_3_);

	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {

		p_77624_3_.add(EnumChatFormatting.DARK_AQUA
				+ "Tsukumogami of Awkwardness");
		if(p_77624_2_.experienceLevel >= 60){
			
		p_77624_3_.add(EnumChatFormatting.LIGHT_PURPLE
				+ " -Invisibility");
		
		}
		p_77624_3_.add(EnumChatFormatting.LIGHT_PURPLE
				+ " -Slowness III");
		p_77624_3_.add(EnumChatFormatting.DARK_PURPLE
				+ " *Invulnerable to Most Damage");

	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
		if(player.experienceLevel <= 60){
			
			player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 50, 2));
			
		}else{
			
			 player.addPotionEffect(new PotionEffect(Potion.invisibility.id, 50, 0));
			 player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 50, 2));
	 
		}
	 
	}
	
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type){
		
		if(stack.getItem() == GOAItem.itemMonkeyMask){
			
			return LibMod.MODID + ":textures/models/armor/MonkeyMask.png";
		} else{
			
			return null;
			
		}
		
	}

	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		
		if (source == source.generic) {
			
			return new ArmorProperties(1, 1, MathHelper.floor_double(damage * 0.2D));
			
		}
		
		return new ArmorProperties(0, 0, 0);
		
	}

	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {

		return 3;
		
	}

	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		
		stack.damageItem(damage * 2, entity);
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		
		return EnumRarity.uncommon;
		
	}
	
	@Override
	public boolean hasEffect(ItemStack par1ItemStack) {
		
		return true;
		
	}

}