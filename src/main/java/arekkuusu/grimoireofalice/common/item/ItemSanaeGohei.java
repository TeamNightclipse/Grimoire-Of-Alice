/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.Alice;
import arekkuusu.grimoireofalice.common.entity.EntityMiracleCircle;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.entity.living.TouhouCharacter;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemSanaeGohei extends ItemGohei<ItemSanaeGohei.EnumMiracle> implements IOwnedBy {

	public ItemSanaeGohei() {
		super(LibItemName.SANAE_GOHEI, EnumMiracle.values());
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean advanced) {
		list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.sanae_gohei_header.name"));
		if(GuiScreen.isShiftKeyDown()) {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.sanae_gohei_use.name"));
			list.add(TextFormatting.AQUA + I18n.format("grimoire.tooltip.sanae_gohei_charge.name") + " " + getCharge(stack));
		}
		else {
			list.add(TextFormatting.DARK_GRAY + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.sanae_gohei_shift.name"));
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if(player.isSneaking()) {
			increaseType(stack);
			if(world.isRemote) {
				String modeName = getType(stack).toString() + ".name";
				ITextComponent text = new TextComponentTranslation("grimoire.tooltip.sanae_gohei_mode_header.name");
				text.appendSibling(new TextComponentTranslation("grimoire.tooltip.sanae_gohei_mode_" + modeName));

				Alice.proxy.displayRecordText(text);
			}
		}
		else {
			if(!world.isRemote) {
				EntityMiracleCircle miracleCircle = new EntityMiracleCircle(world, player, stack);
				world.spawnEntity(miracleCircle);
			}
			player.setActiveHand(hand);
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		for(int i = 0; i < 2; ++i) {
			player.world.spawnParticle(EnumParticleTypes.REDSTONE,
					player.posX + (itemRand.nextDouble() - 0.5D) * player.width,
					player.posY + itemRand.nextDouble() * player.height - 0.25D,
					player.posZ + (itemRand.nextDouble() - 0.5D) * player.width,
					(itemRand.nextDouble() - 0.5D) * 2.0D, -itemRand.nextDouble(), (itemRand.nextDouble() - 0.5D) * 2.0D);
		}
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		World world = entityLiving.world;
		if(entityLiving instanceof EntityPlayer && !world.isRemote) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			if(!player.isSneaking() && !player.getCooldownTracker().hasCooldown(this)) {
				EnumMiracle mode = getType(stack);
				final int charge = getCharge(stack);

				if(mode.behavior.canUse(charge, player)) {
					int subtract = mode.behavior.execute(this, charge, player);
					setCharge(stack, charge - subtract);
				}
			}
		}
		return true;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer) {
			((EntityPlayer) entityLiving).getCooldownTracker().setCooldown(this, 25);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack) {
		return I18n.format("item.sanaegohei.name") + " :"
				+ I18n.format("grimoire.tooltip.sanae_gohei_mode_" + getType(stack).toString() + ".name");
	}

	private static void setCharge(ItemStack itemStack, int charge) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		if(nbt == null) {
			nbt = new NBTTagCompound();
			itemStack.setTagCompound(nbt);
		}
		nbt.setInteger("GoheiCharge", charge);
	}

	private static int getCharge(ItemStack itemStack) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		return nbt == null ? 0 : nbt.getInteger("GoheiCharge");
	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemstack) {
		return EnumAction.NONE;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 2000;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}

	public enum EnumMiracle {

		CLEAR(Miracles.CLEAR),
		WIND(Miracles.WIND),
		HEAL(Miracles.HEAL),
		POTIONS(Miracles.POTIONS),
		RAIN(Miracles.RAIN),
		THUNDER(Miracles.THUNDER),
		SOIL(Miracles.SOIL),
		TIME(Miracles.TIME),
		TESTIFICATE(Miracles.TESTIFICATE);

		private final Miracle behavior;

		EnumMiracle(Miracle behavior) {
			this.behavior = behavior;
		}

		@Override
		public String toString() {
			return behavior.getName();
		}
	}

	@Override
	public TouhouCharacter character(ItemStack stack) {
		return TouhouCharacter.SANAE_KOCHIYA;
	}
}
