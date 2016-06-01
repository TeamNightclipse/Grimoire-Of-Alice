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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;;

public class ItemPrimordialShield extends ItemSword{

	public ItemPrimordialShield(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		setMaxDamage(1000);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.epic;
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(EnumChatFormatting.WHITE
				+ "Supermassive unidentified object");
		p_77624_3_.add(EnumChatFormatting.GOLD
				+ "After melting the bases of a");
		p_77624_3_.add(EnumChatFormatting.GOLD
				+ "forgoten universe, an unknown");
		p_77624_3_.add(EnumChatFormatting.GOLD
				+ "entity decided to store the");
		p_77624_3_.add(EnumChatFormatting.GOLD
				+ "remains in the form of a shield");
		p_77624_3_.add(EnumChatFormatting.RED
				+ "Only a god would be able to lift it");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onUpdate(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_){
		if (p_77663_1_.getItemDamage() < p_77663_1_.getMaxDamage()) {
			p_77663_1_.setItemDamage(p_77663_1_.getItemDamage() - 1);
        }

        if (p_77663_5_ && (p_77663_3_ instanceof EntityPlayer)) {
                EntityPlayer entityplayer = (EntityPlayer)p_77663_3_;
                if (!p_77663_2_.isRemote && !entityplayer.capabilities.isCreativeMode) {
                        if (entityplayer.getCurrentEquippedItem().getItem() == GOAItem.itemPrimordialShield) {
                                if (entityplayer.experienceLevel < 1000) {
                                        entityplayer.fallDistance = 5.0F;
                                }
                        }
                	}
        		}
			super.onUpdate(p_77663_1_, p_77663_2_, p_77663_3_, p_77663_4_, p_77663_5_);{
			EntityPlayer player = (EntityPlayer) p_77663_3_;
			ItemStack equiped = player.getCurrentEquippedItem();
			if (equiped == p_77663_1_){
				if(player.experienceLevel < 1000){
				player.motionX *= 0.0D;
		        player.motionZ *= 0.0D;
		        player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 25, 5));
		        player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 25, 5));
				}
			}
		}
	}

	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_){
		if(p_77659_3_.experienceLevel > 1000){
			if(p_77659_1_.getItemDamage() == 0) {
			 p_77659_3_.capabilities.disableDamage = true;
	         p_77659_3_.setItemInUse(p_77659_1_, this.getMaxItemUseDuration(p_77659_1_));
	                 }
				}
        return p_77659_1_;
	}
	
	/*@SubscribeEvent
	public void onLivingAttackEvent(LivingHurtEvent event) {
		if(isUse){
	    if (event.entity instanceof EntityPlayer && (event.source == DamageSource.fall || event.source == DamageSource.anvil || event.source == DamageSource.cactus ||
	    											event.source == DamageSource.drown || event.source == DamageSource.fall || event.source == DamageSource.fallingBlock ||
	    											event.source == DamageSource.generic || event.source == DamageSource.inFire || event.source == DamageSource.inWall ||
	    											event.source == DamageSource.lava || event.source == DamageSource.magic || event.source == DamageSource.onFire ||
	    											event.source == DamageSource.outOfWorld || event.source == DamageSource.wither)) {
	    		EntityPlayer player = (EntityPlayer)event.entity;
	    		event.setCanceled(true);
	    		}
			}
	    }*/
	
	@Override
	public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_) {
		if(p_77615_1_.getItemDamage() == 0) {
			p_77615_3_.capabilities.disableDamage = false;
			p_77615_1_.damageItem(999, p_77615_3_);
	                 }
	}
	
	public ItemStack onItemSwing(ItemStack p_77659_2_, World p_77659_3_, EntityPlayer p_77659_4_){
		return p_77659_2_;
		}
	
	@Override
	public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_) {
        return true;
    }

}
