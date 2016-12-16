/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item.food;

import java.util.List;

import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
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
	@SideOnly(Side.CLIENT)
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
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.hourai_elixir_header.name"));
		}
		else {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.hourai_elixir_shift.name"));
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
		} //*Laughs maniacally*
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.DRINK;
	}
}
