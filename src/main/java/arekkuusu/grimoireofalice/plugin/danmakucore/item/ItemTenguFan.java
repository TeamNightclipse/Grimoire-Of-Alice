/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.plugin.danmakucore.item;

import java.util.List;

import arekkuusu.grimoireofalice.item.ItemMod;
import arekkuusu.grimoireofalice.lib.LibItemName;
import arekkuusu.grimoireofalice.plugin.danmakucore.LibGOAShotData;
import arekkuusu.grimoireofalice.plugin.danmakucore.subentity.SubEntityWind;
import arekkuusu.grimoireofalice.plugin.danmakucore.variant.GOADanmakuVariants;
import net.katsstuff.danmakucore.entity.danmaku.DanmakuBuilder;
import net.katsstuff.danmakucore.entity.danmaku.EntityDanmaku;
import net.katsstuff.danmakucore.entity.danmaku.subentity.SubEntity;
import net.katsstuff.danmakucore.helper.DanmakuHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTenguFan extends ItemMod {

	public ItemTenguFan() {
		super(LibItemName.TENGU_FAN);
		setMaxDamage(500);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Fan to manipulate winds");
		list.add(TextFormatting.ITALIC + "Hold right click to charge");
		list.add(TextFormatting.ITALIC + "and release");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}


	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entityLiving;
			player.swingArm(EnumHand.MAIN_HAND);
			int timeUsed = getMaxItemUseDuration(stack) - timeLeft;
			float speed = timeUsed / 7.0F;
			if(timeUsed <= 5) {
				return;
			}
			DanmakuHelper.playShotSound(entityLiving);
			if(!worldIn.isRemote) {
				EntityDanmaku danmaku = DanmakuBuilder.builder()
						.setUser(entityLiving)
						.setMovementData(speed)
						.setShot(LibGOAShotData.WIND)
						.build().asEntity();

				worldIn.spawnEntityInWorld(danmaku);
				SubEntity subEntity = danmaku.getSubEntity();
				if(subEntity instanceof SubEntityWind.Wind) {
					((SubEntityWind.Wind)subEntity).setTimeUsed(timeUsed);
				}
			}
			stack.damageItem(1, entityLiving);
		}
	}

	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == Items.LEAD;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack p_77661_1_) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemStack) {
		return 24;
	}

	@Override
	public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2) {
		return false;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
