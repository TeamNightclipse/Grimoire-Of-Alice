package arekkuusu.grimoireOfAlice.item.food;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import arekkuusu.grimoireOfAlice.GrimoireOfAlice;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemkappasNostrum extends ItemFood {

	public ItemkappasNostrum() {
		super(1, 1F, false);
		setMaxStackSize(1);
		setMaxDamage(4);
		setCreativeTab(GrimoireOfAlice.CREATIVE_TAB);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(EnumChatFormatting.DARK_AQUA + "Powerful healing ointment said to be created");
		list.add(EnumChatFormatting.DARK_AQUA + "by cutting off the arm of a kappa");
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.uncommon;
	}
	
	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onFoodEaten(stack, world, player);
		player.addPotionEffect(new PotionEffect(Potion.heal.id, 160, 0));
	}
	
	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
        stack.damageItem(1, player);
        player.getFoodStats().func_151686_a(this, stack);
        world.playSoundAtEntity(player, "entity.witch.drink", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
        this.onFoodEaten(stack, world, player);
        return stack;
    }
	
	@Override
	public EnumAction getItemUseAction(ItemStack p_77661_1_) {
        return EnumAction.drink;
    }
	
}
