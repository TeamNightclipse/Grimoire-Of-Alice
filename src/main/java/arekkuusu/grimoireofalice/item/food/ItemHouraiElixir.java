package arekkuusu.grimoireofalice.item.food;

import java.util.List;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHouraiElixir extends ItemModFood {

	public ItemHouraiElixir() {
		super(50, 0, false, LibItemName.HOURAI_ELIXIR);
		setMaxStackSize(1);
		setAlwaysEdible();
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		if(GuiScreen.isShiftKeyDown()) {
			list.add(TextFormatting.DARK_RED + "Anyone who drinks this forbidden elixir will cease to age, "
					+ "never become ill, and will never be able to die. One will be as frail and vulnerable as before, "
					+ "but every injury shall cease; critical injuries will be healed in a matter of days. "
					+ "Complete destruction results in complete restoration.");
		}
		else {
			list.add(TextFormatting.ITALIC + "SHIFT for details");
		}
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onFoodEaten(stack, world, player);
		player.playSound(SoundEvents.ENTITY_PLAYER_BURP, 0.5F, itemRand.nextFloat() * 0.1F + 0.9F);
		player.playSound(SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE, 0.5F, itemRand.nextFloat() * 0.1F + 0.9F);

		player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 100, 0));
		player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100, 0));
		player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 100, 0));
		player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100, 0));
		player.addPotionEffect(new PotionEffect(MobEffects.WITHER, 100, 0));
		player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 100, 0));
		player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 0));
		player.addPotionEffect(new PotionEffect(MobEffects.POISON, 100, 0));
		if(!player.getEntityData().hasKey("Eternal")) {
			player.getEntityData().setBoolean("Eternal", true);
		}
		else {
			player.getEntityData().setBoolean("Eternal", false);
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.DRINK;
	}
}
