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

import arekkuusu.grimoireofalice.common.core.capability.IHouraiCapability;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Optional.Interface(iface = "net.katsstuff.danmakucore.item.IOwnedBy", modid = "danmakucore")
public class ItemHouraiElixir extends ItemModFood implements IOwnedBy {

	@CapabilityInject(IHouraiCapability.class)
	private static final Capability<IHouraiCapability> HOURAI_CAPABILITY = null;

	public ItemHouraiElixir() {
		super(15, 0, false, LibItemName.HOURAI_ELIXIR);
		setMaxDamage(2);
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
			list.add(TextFormatting.DARK_GRAY + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.hourai_elixir_shift.name"));
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
		if(!world.isRemote) {
			setPlayerElixir(player);
		}
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entityLiving) {
		if(entityLiving instanceof EntityPlayer) {
			stack.damageItem(1, entityLiving);
			onFoodEaten(stack, world, (EntityPlayer) entityLiving);
		}
		return stack;
	}

	@SuppressWarnings("ConstantConditions")
	private void setPlayerElixir(EntityPlayer player) {
		if(player.hasCapability(HOURAI_CAPABILITY, null)) {
			IHouraiCapability capability = player.getCapability(HOURAI_CAPABILITY, null);
			byte maxLevel = capability.getMaxHouraiLevel();
			byte level = capability.getHouraiLevel();
			switch(level) {
				case 0:
					player.sendMessage(
							new TextComponentString(TextFormatting.DARK_RED + "" + TextFormatting.ITALIC
									+ new TextComponentTranslation("item.hourai.level_0").getFormattedText()));
					break;
				case 1:
					player.sendMessage(
							new TextComponentString(TextFormatting.DARK_RED + "" + TextFormatting.ITALIC
									+ new TextComponentTranslation("item.hourai.level_1").getFormattedText()));
					break;
				case 2:
					player.sendMessage(
							new TextComponentString(TextFormatting.DARK_RED + "" + TextFormatting.ITALIC
									+ new TextComponentTranslation("item.hourai.level_2").getFormattedText()));
					break;
				default:
					player.sendMessage(
							new TextComponentString(TextFormatting.DARK_RED + "" + TextFormatting.ITALIC
									+ new TextComponentTranslation("item.hourai.level_3").getFormattedText()));
					break;
			}
			if(level < maxLevel) {
				capability.setHouraiLevel((byte) (level + 1));
			}
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.DRINK;
	}

	@Optional.Method(modid = "danmakucore")
	@Override
	public net.katsstuff.danmakucore.entity.living.TouhouCharacter character(ItemStack stack) {
		return net.katsstuff.danmakucore.entity.living.TouhouCharacter.FUJIWARA_NO_MOKOU;
	}
}
