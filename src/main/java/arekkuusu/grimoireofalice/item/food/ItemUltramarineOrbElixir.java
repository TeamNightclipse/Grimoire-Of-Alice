package arekkuusu.grimoireofalice.item.food;

import arekkuusu.grimoireofalice.entity.EntityMagicCircle;
import arekkuusu.grimoireofalice.handler.EnumTextures;
import arekkuusu.grimoireofalice.lib.LibItemName;
import arekkuusu.grimoireofalice.potion.ModPotions;
import net.minecraft.entity.player.EntityPlayer;
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

import java.util.List;

public class ItemUltramarineOrbElixir extends ItemModFood {

	public ItemUltramarineOrbElixir() {
		super(0, 0, false, LibItemName.ORB_ELIXIR);
		setMaxStackSize(1);
		setAlwaysEdible();
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.DARK_AQUA + "Removes the impurity of death");
		list.add(TextFormatting.DARK_AQUA + "and allows the user to experience");
		list.add(TextFormatting.DARK_AQUA + "the immediate future");
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onFoodEaten(stack, world, player);
		world.playSound(player, new BlockPos(player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D),
				SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.HOSTILE, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		//noinspection ConstantConditions
		player.addPotionEffect(new PotionEffect(ModPotions.ELIXIR, 1000, 0));
		if(!world.isRemote) {
			EntityMagicCircle circle = new EntityMagicCircle(world, player, EnumTextures.RED_NORMAL, 1000);
			world.spawnEntityInWorld(circle);
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.DRINK;
	}
}
