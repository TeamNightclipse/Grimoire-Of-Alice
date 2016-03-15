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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;

public class ItemEnchantedBook extends ItemGOABase {
	
	public ItemEnchantedBook() {
		super();
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabCombat);
	}

	public static boolean validBookTagContents(NBTTagCompound p_77828_0_)
    {
        if (!ItemYoukaiBook.func_150930_a(p_77828_0_))
        {
            return false;
        }
        else if (!p_77828_0_.hasKey("title", 8))
        {
            return false;
        }
        else
        {
            String s = p_77828_0_.getString("title");
            return s != null && s.length() <= 16 ? p_77828_0_.hasKey("author", 8) : false;
        }
    }
	
	@Override
    public String getItemStackDisplayName(ItemStack p_77653_1_)
    {
        if (p_77653_1_.hasTagCompound())
        {
            NBTTagCompound nbttagcompound = p_77653_1_.getTagCompound();
            String s = nbttagcompound.getString("title");

            if (!StringUtils.isNullOrEmpty(s))
            {
                return s;
            }
        }

        return super.getItemStackDisplayName(p_77653_1_);
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_)
    {
        if (p_77624_1_.hasTagCompound())
        {
            NBTTagCompound nbttagcompound = p_77624_1_.getTagCompound();
            String s = nbttagcompound.getString("author");

            if (!StringUtils.isNullOrEmpty(s))
            {
            	 p_77624_3_.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocalFormatted("book.byAuthor", new Object[] {s}));
            }
        }
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack p_77624_1_, World p_77624_3_, EntityPlayer p_77624_2_)
    {
    	//p_77624_3_.playSoundAtEntity(p_77624_2_, "portal.travel", 0.5F, 0.4F);
    	p_77624_2_.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 100, 0));
		return p_77624_1_;
    }

    /**
     * If this function returns true (or the item is damageable), the ItemStack's NBT tag will be sent to the client.
     */
    @Override
    public boolean getShareTag()
    {
        return true;
    }
    
	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}
	
	@Override
	public boolean hasEffect(ItemStack par1ItemStack) {
		return true;
	}
}
