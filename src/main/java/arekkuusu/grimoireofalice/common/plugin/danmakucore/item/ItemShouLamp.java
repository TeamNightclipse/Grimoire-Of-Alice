/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.plugin.danmakucore.item;

import java.util.List;

import arekkuusu.grimoireofalice.common.entity.EntityMagicCircle;
import arekkuusu.grimoireofalice.common.item.ItemMod;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.entity.danmaku.DanmakuBuilder;
import net.katsstuff.danmakucore.helper.DanmakuCreationHelper;
import net.katsstuff.danmakucore.helper.DanmakuHelper;
import net.katsstuff.danmakucore.lib.LibColor;
import net.katsstuff.danmakucore.lib.data.LibShotData;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemShouLamp extends ItemMod {

	private final String TAG = "Jewels";

	public ItemShouLamp() {
		super(LibItemName.SHOU_LAMP);
		setMaxStackSize(1);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + I18n.format("grimoire.tooltip.shou_lamp_header.name"));
		if(GuiScreen.isShiftKeyDown()) {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.shou_lamp_use.name"));
			list.add("");
			list.add(TextFormatting.AQUA + I18n.format("grimoire.tooltip.shou_lamp_jewels.name") + " " + getJewels(stack));
		}
		else {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.shou_lamp_shift.name"));
		}
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof EntityPlayer) {
			EntityPlayer player = ((EntityPlayer) entityIn);
			if (isActive(player, stack)) {
				DanmakuHelper.playShotSound(entityIn);
				DanmakuBuilder danmaku = DanmakuBuilder.builder()
						.setUser(player)
						.setMovementData(0.5D, 1.5D, 0.1D)
						.setShot(LibShotData.SHOT_LASER_LONG.setColor(LibColor.COLOR_SATURATED_YELLOW).setSizeZ(4))
						.build();

				DanmakuCreationHelper.createRandomRingShot(danmaku, 1, 5, 5);
				player.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 30, 0));
				setJewels(stack, getJewels(stack) - 1);
			}
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		if (getJewels(stack) < 500 && player instanceof EntityPlayer) {
			player.addPotionEffect(new PotionEffect(MobEffects.LUCK, 10, 5));
			if(count % 4 == 0)
				player.worldObj.playSound(null, player.getPosition(), SoundEvents.ENTITY_EXPERIENCE_ORB_TOUCH, SoundCategory.PLAYERS, 0.1F, 1F);
			setJewels(stack, getJewels(stack) + 1);
		}
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			if (player.isSneaking() && !isActive(player, stack)) {
				if (!worldIn.isRemote) {
					EntityMagicCircle circle = new EntityMagicCircle(worldIn, player, EntityMagicCircle.EnumTextures.GOLD_STAR_SMALL, 250);
					worldIn.spawnEntityInWorld(circle);
				}
				player.getCooldownTracker().setCooldown(this, getJewels(stack) + 1);

				if (timeLeft < 200) {
					List<EntityLivingBase> list = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, player.getEntityBoundingBox().expandXyz(4.0D));
					for (EntityLivingBase mob : list) {
						mob.addPotionEffect(new PotionEffect(MobEffects.LUCK, 125, 5));
						if (!mob.worldObj.isRemote) {
							EntityMagicCircle circle = new EntityMagicCircle(worldIn, mob, EntityMagicCircle.EnumTextures.GOLD_STAR_SMALL, getJewels(stack) * 2);
							worldIn.spawnEntityInWorld(circle);
						}
					}
				}
			}
		}
	}

	private boolean isActive(EntityPlayer player, ItemStack stack) {
		NBTTagCompound nbt = stack.getTagCompound();
		return player.getCooldownTracker().hasCooldown(this)
				&& nbt != null
				&& nbt.hasKey(TAG) && nbt.getInteger(TAG) > 0;
	}

	private void setJewels(ItemStack itemStack, int jewels) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		if (nbt == null) {
			nbt = new NBTTagCompound();
			itemStack.setTagCompound(nbt);
			nbt.setInteger(TAG, jewels);
		}
		else if (jewels >= 0) {
			nbt.setInteger(TAG, jewels);
		}
	}

	private int getJewels(ItemStack itemStack) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		return nbt == null ? 0 : nbt.getInteger(TAG);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BLOCK;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 500;
	}
}
