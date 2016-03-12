package arekkuusu.grimoireOfAlice.item;

import arekkuusu.grimoireOfAlice.client.gui.GuiScreenYoukaiBook;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemYoukaiBook extends ItemGOABase{
	
private static final String __OBFID = "CL_00000076";
	
	public ItemYoukaiBook() {
		super();
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabMisc);
	}

	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {
    	if (p_77659_3_.worldObj.isRemote){   		
    	Minecraft.getMinecraft().displayGuiScreen(new GuiScreenYoukaiBook(p_77659_3_, p_77659_1_, true));
    	}
    	return p_77659_1_;
    }

    /**
     * If this function returns true (or the item is damageable), the ItemStack's NBT tag will be sent to the client.
     */
    public boolean getShareTag()
    {
        return true;
    }

    public static boolean func_150930_a(NBTTagCompound p_150930_0_)
    {
        if (p_150930_0_ == null)
        {
            return false;
        }
        else if (!p_150930_0_.hasKey("pages", 9))
        {
            return false;
        }
        else
        {
            NBTTagList nbttaglist = p_150930_0_.getTagList("pages", 8);

            for (int i = 0; i < nbttaglist.tagCount(); ++i)
            {
                String s = nbttaglist.getStringTagAt(i);

                if (s == null)
                {
                    return false;
                }

                if (s.length() > 256)
                {
                    return false;
                }
            }

            return true;
        }
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.uncommon;
	}

}
