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

import arekkuusu.grimoireOfAlice.client.entity.EntityFireBall;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemLaevatein extends ItemSword{

	public ItemLaevatein(ToolMaterial p_i45356_1_) {
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
				+ "'Damage twig'");
		p_77624_3_.add(EnumChatFormatting.GOLD
				+ "Only true Lolis can wield this sword");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onUpdate(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_){
		super.onUpdate(p_77663_1_, p_77663_2_, p_77663_3_, p_77663_4_, p_77663_5_);{
			EntityPlayer player = (EntityPlayer) p_77663_3_;
			ItemStack equiped = player.getCurrentEquippedItem();
			if (equiped == p_77663_1_){
				player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 0, 0));
			}	
		}
	}
	
	@Override
	public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_){
		p_77644_2_.addPotionEffect(new PotionEffect(Potion.weakness.id, 128, 0));
        p_77644_1_.damageItem(1, p_77644_3_);
        return true;
    }
	
	@Override
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_){
		if(p_77659_3_.experienceLevel > 42){
		p_77659_1_.damageItem(10, p_77659_3_);
		if (!p_77659_2_.isRemote) {
			if (p_77659_3_.capabilities.isCreativeMode || p_77659_3_.inventory.hasItem(Items.fire_charge)) {
			p_77659_2_.playSoundEffect((double)p_77659_3_.posX + 0.5D, (double)p_77659_3_.posY + 0.5D, (double)p_77659_3_.posZ + 0.5D, "mob.ghast.scream", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
			Vec3 look = p_77659_3_.getLookVec();
			EntityFireBall fireball2 = new EntityFireBall(p_77659_2_, p_77659_3_, 1, 1, 1);
			fireball2.setPosition(
			p_77659_3_.posX + look.xCoord * 5,
			p_77659_3_.posY + look.yCoord * 5,
			p_77659_3_.posZ + look.zCoord * 5);
			fireball2.accelerationX = look.xCoord * 0.1;
			fireball2.accelerationY = look.yCoord * 0.1;
			fireball2.accelerationZ = look.zCoord * 0.1;
			p_77659_2_.spawnEntityInWorld(fireball2);
			p_77659_3_.inventory.consumeInventoryItem(Items.fire_charge);
				}
			}
		} else {
			p_77659_3_.addPotionEffect(new PotionEffect(Potion.confusion.id, 256, 0));
			p_77659_2_.playSoundEffect((double)p_77659_3_.posX + 0.5D, (double)p_77659_3_.posY + 0.5D, (double)p_77659_3_.posZ + 0.5D, "mob.ghast.scream", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
			}
		return p_77659_1_;
    }
	
	@Override
	public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        if (p_77648_7_ == 0){
            --p_77648_5_;
        }

        if (p_77648_7_ == 1){
            ++p_77648_5_;
        }

        if (p_77648_7_ == 2){
            --p_77648_6_;
        }

        if (p_77648_7_ == 3){
            ++p_77648_6_;
        }

        if (p_77648_7_ == 4){
            --p_77648_4_;
        }

        if (p_77648_7_ == 5){
            ++p_77648_4_		;
        }

        if (!p_77648_2_.canPlayerEdit(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_)){
            return false;
        } else {
            if (p_77648_3_.isAirBlock(p_77648_4_, p_77648_5_, p_77648_6_)){
                p_77648_3_.playSoundEffect((double)p_77648_4_ + 0.5D, (double)p_77648_5_ + 0.5D, (double)p_77648_6_ + 0.5D, "mob.blaze.death", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
                p_77648_3_.setBlock(p_77648_4_, p_77648_5_, p_77648_6_, Blocks.fire);
                if (p_77648_3_.isAirBlock(p_77648_4_+1, p_77648_5_, p_77648_6_+1)){
                    p_77648_3_.setBlock(p_77648_4_+1, p_77648_5_, p_77648_6_+1, Blocks.fire);
                    if (p_77648_3_.isAirBlock(p_77648_4_-1, p_77648_5_, p_77648_6_-1)){
                        p_77648_3_.setBlock(p_77648_4_-1, p_77648_5_, p_77648_6_-1, Blocks.fire);
                        if (p_77648_3_.isAirBlock(p_77648_4_-1, p_77648_5_, p_77648_6_+1)){
                            p_77648_3_.setBlock(p_77648_4_-1, p_77648_5_, p_77648_6_+1, Blocks.fire);
                            if (p_77648_3_.isAirBlock(p_77648_4_+1, p_77648_5_, p_77648_6_-1)){
                                p_77648_3_.setBlock(p_77648_4_+1, p_77648_5_, p_77648_6_-1, Blocks.fire);
                            }
                        }
                    }
                }
            }
            p_77648_1_.damageItem(1, p_77648_2_);
            return true;
        }
    }

}
