package arekkuusu.grimoireOfAlice.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTimeOrb extends ItemPoint {

	ItemTimeOrb() {
		super(EnumRarity.uncommon);
		setMaxStackSize(64);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(EnumChatFormatting.DARK_AQUA + "Exclusive to Imperishable Night,");
		list.add(EnumChatFormatting.DARK_AQUA + "they are believed to slow");
		list.add(EnumChatFormatting.DARK_AQUA + "the passing of the night");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
		p_77659_3_.setItemInUse(p_77659_1_, getMaxItemUseDuration(p_77659_1_));
        return p_77659_1_;
    }
	
	@Override
	public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer entityplayer, int i){
		if (!entityplayer.capabilities.isCreativeMode) {
			ItemStack itemstack2 = itemstack.copy();
			if (--itemstack2.stackSize == 0) {
				itemstack2 = null;
			}
			world.playSoundEffect(entityplayer.posX + 0.5D, entityplayer.posY + 0.5D, entityplayer.posZ + 0.5D, "fireworks.twinkle", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
			entityplayer.inventory.mainInventory[entityplayer.inventory.currentItem] = itemstack2;
			if(!world.isDaytime()){
			long moonTime = world.getWorldTime() - 500;
			world.setWorldTime(moonTime);
			}
		}
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack p_77661_1_) {
        return EnumAction.block;
    }
	
	@Override
	public int getMaxItemUseDuration(ItemStack p_77626_1_) {
        return 42;
    }
	
}
