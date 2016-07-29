package arekkuusu.grimoireOfAlice.item.food;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import arekkuusu.grimoireOfAlice.GrimoireOfAlice;
import arekkuusu.grimoireOfAlice.item.GOAItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemIbarakiBoxFilled extends ItemFood {

	public ItemIbarakiBoxFilled() {
		super(2, 2F, false);
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabFood);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(EnumChatFormatting.GREEN + "Cure all illnesses or heal any injuries");
		list.add(EnumChatFormatting.GREEN + "to the person who drinks from it");
		list.add(EnumChatFormatting.DARK_PURPLE + "In exchange for curing illnesses,");
		list.add(EnumChatFormatting.DARK_PURPLE + "the personality of the one who drinks");
		list.add(EnumChatFormatting.DARK_PURPLE + "from will temporarily become like an Oni's");
	}
	
	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
        if (!player.capabilities.isCreativeMode) {
            --stack.stackSize;
        }
            player.curePotionEffects(new ItemStack(Items.milk_bucket));
            player.heal(100);
            player.addPotionEffect(new PotionEffect(Potion.confusion.id, 2400, 0));
        return stack.stackSize <= 0 ? new ItemStack(GOAItem.ibarakiBoxEmpty) : stack;
    }
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        return stack;
    }
	
	@Override
	public EnumAction getItemUseAction(ItemStack p_77661_1_) {
        return EnumAction.drink;
    }
	
	@Override
	public int getMaxItemUseDuration(ItemStack p_77626_1_) {
        return 32;
    }
	
}
