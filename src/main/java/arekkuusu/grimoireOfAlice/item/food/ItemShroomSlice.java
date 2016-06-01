/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.item.food;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemShroomSlice extends ItemFood{

	public ItemShroomSlice(int p_i45339_1_, float p_i45339_2_, boolean p_i45339_3_) {
		super(p_i45339_1_, p_i45339_2_, p_i45339_3_);
		this.setHasSubtypes(true);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.uncommon;
	}
	
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack p_77636_1_)
    {
        return p_77636_1_.getItemDamage() > 0;
    }
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
	    p_77624_3_.add(EnumChatFormatting.GREEN
				+ "Psilocybin mushroom");
		p_77624_3_.add(EnumChatFormatting.DARK_PURPLE
				+ "WaNna HaVE a TrIP bRo??!?");
		}
	
	protected void onFoodEaten(ItemStack p_77849_1_, World p_77849_2_, EntityPlayer p_77849_3_) {
        if (!p_77849_2_.isRemote) {
            p_77849_3_.addPotionEffect(new PotionEffect(Potion.confusion.id, 2400, 0));
            p_77849_3_.addPotionEffect(new PotionEffect(Potion.blindness.id, 2400, 0));
            p_77849_3_.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 2400, 1));
        }

        if (p_77849_1_.getItemDamage() > 0) {
            if (!p_77849_2_.isRemote) {
                p_77849_3_.addPotionEffect(new PotionEffect(Potion.hunger.id, 600, 0));
                p_77849_3_.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 2400, 1));
                p_77849_3_.addPotionEffect(new PotionEffect(Potion.weakness.id, 2400, 0));
            }
        }
        else {
            super.onFoodEaten(p_77849_1_, p_77849_2_, p_77849_3_);
        }
    }
	
	public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_) {
        p_150895_3_.add(new ItemStack(p_150895_1_, 1, 0));
        p_150895_3_.add(new ItemStack(p_150895_1_, 1, 1));
    }

}
