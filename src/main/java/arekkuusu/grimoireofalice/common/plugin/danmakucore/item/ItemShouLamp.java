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
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.data.Quat;
import net.katsstuff.danmakucore.entity.danmaku.DanmakuTemplate;
import net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters;
import net.katsstuff.danmakucore.helper.DanmakuCreationHelper;
import net.katsstuff.danmakucore.helper.ItemNBTHelper;
import net.katsstuff.danmakucore.item.IOwnedBy;
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
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemShouLamp extends ItemJeweled implements IOwnedBy {

	private static final String TAG = "Jewels";

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
		list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.shou_lamp_header.name"));
		if(GuiScreen.isShiftKeyDown()) {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.shou_lamp_use.name"));
			list.add(TextFormatting.AQUA + I18n.format("grimoire.tooltip.shou_lamp_jewels.name") + " " + getJewels(stack));
		}
		else {
			list.add(TextFormatting.DARK_GRAY + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.shou_lamp_shift.name"));
		}
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return getJewels(stack) > 0;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entityIn, int itemSlot, boolean isSelected) {
		if(entityIn instanceof EntityPlayer) {
			EntityPlayer player = ((EntityPlayer) entityIn);
			if(isActive(player, stack)) {
				DanmakuTemplate danmaku = DanmakuTemplate.builder()
						.setUser(player)
						.setMovementData(0.5D, 0.5D, 0.1D)
						.setShot(LibShotData.SHOT_POINTED_LASER_LONG.setColor(LibColor.COLOR_SATURATED_YELLOW).setSizeZ(4))
						.build();

				DanmakuCreationHelper.createRandomRingShot(Quat.orientationOf(player), danmaku, 1, 20, 0D);
				player.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 30, 0));
				addJewels(stack, (short) -1);
			}
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		player.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		if(getJewels(stack) < 500 && player instanceof EntityPlayer) {
			player.addPotionEffect(new PotionEffect(MobEffects.LUCK, 10, 5));
			if(count % 4 == 0) {
				player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 0.1F, 1F);
			}
			addJewels(stack, (short) 1);
		}
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			if(player.isSneaking() && !isActive(player, stack)) {
				short jewels = getJewels(stack);
				if(!world.isRemote) {
					EntityMagicCircle circle = new EntityMagicCircle(world, player, EntityMagicCircle.EnumTextures.GOLD_STAR_SMALL, jewels);
					world.spawnEntity(circle);
				}
				player.getCooldownTracker().setCooldown(this, jewels + 1);

				if(timeLeft < 200) {
					List<EntityLivingBase> list = world.getEntitiesWithinAABB(EntityLivingBase.class,
							player.getEntityBoundingBox().grow(4.0D));
					for(EntityLivingBase mob : list) {
						mob.addPotionEffect(new PotionEffect(MobEffects.LUCK, 125, 5));
						if(!mob.world.isRemote) {
							EntityMagicCircle circle = new EntityMagicCircle(world, mob, EntityMagicCircle.EnumTextures.GOLD_STAR_SMALL,
									jewels * 2);
							world.spawnEntity(circle);
						}
					}
				}
			}
		}
	}

	private boolean isActive(EntityPlayer player, ItemStack stack) {
		return player.getCooldownTracker().hasCooldown(this) && ItemNBTHelper.getInt(stack, TAG, 0) > 0;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.NONE;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 500;
	}

	@Override
	public EnumTouhouCharacters character(ItemStack stack) {
		return EnumTouhouCharacters.SHOU_TORAMARU;
	}
}
