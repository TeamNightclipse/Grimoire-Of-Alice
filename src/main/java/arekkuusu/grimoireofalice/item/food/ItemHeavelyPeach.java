package arekkuusu.grimoireofalice.item.food;

import java.util.List;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHeavelyPeach extends ItemModFood {

	public ItemHeavelyPeach() {
		super(10, 5F, false, LibItemName.HEAVENLYPEACH);
		setMaxStackSize(16);
		setAlwaysEdible();
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.DARK_AQUA + "Peach from the skies");
		list.add(TextFormatting.ITALIC + "Someone had to fly very high to reach it");
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onFoodEaten(stack, world, player);
		player.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 160, 0));
	}
}
