package arekkuusu.grimoireofalice.item.food;

import java.util.List;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemkappasNostrum extends ItemModFood {

	public ItemkappasNostrum() {
		super(1, 1F, false, LibItemName.KAPPASNOSTRUM);
		setMaxStackSize(1);
		setMaxDamage(4);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.DARK_AQUA + "Powerful healing ointment said to be created");
		list.add(TextFormatting.DARK_AQUA + "by cutting off the arm of a kappa");
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}
	
	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onFoodEaten(stack, world, player);
		player.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 160, 0));
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		if(entityLiving instanceof EntityPlayer){
        stack.damageItem(1, entityLiving);
        ((EntityPlayer)entityLiving).getFoodStats().addStats(this, stack);
        worldIn.playSound((EntityPlayer) entityLiving, new BlockPos(entityLiving.posX + 0.5D, entityLiving.posY + 0.5D, entityLiving.posZ + 0.5D),
				SoundEvents.ENTITY_WITCH_DRINK, SoundCategory.HOSTILE, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
        this.onFoodEaten(stack, worldIn, (EntityPlayer)entityLiving);
		}
        return stack;
    }
	
	@Override
	public EnumAction getItemUseAction(ItemStack p_77661_1_) {
        return EnumAction.DRINK;
    }
	
	@Override
	public int getMaxItemUseDuration(ItemStack p_77626_1_) {
        return 32;
    }
	
}
