/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.lib.LibName;
import arekkuusu.grimoireofalice.compat.danmakucore.LibGOAShotData;
import com.google.common.collect.Lists;
import net.katsstuff.teamnightclipse.danmakucore.DanmakuCore;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.DanmakuTemplate;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import scala.collection.JavaConversions;

public class ItemTenguFan extends ItemBase {

	public ItemTenguFan() {
		super(LibName.TENGU_FAN);
		setMaxDamage(500);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		player.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}


	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			player.swingArm(EnumHand.MAIN_HAND);
			int timeUsed = getMaxItemUseDuration(stack) - timeLeft;
			float speed = timeUsed / 7.0F;
			if(timeUsed <= 5) {
				return;
			}
			if(!world.isRemote) {
				DanmakuTemplate.Builder builder = DanmakuTemplate.builder()
						.setUser(entityLiving)
						.setMovementData(speed)
						.setShot(LibGOAShotData.WIND.setSize(5).setDamage(timeUsed));
				DanmakuCore.proxy().spawnDanmaku(JavaConversions.asScalaBuffer(Lists.newArrayList(builder.build().asEntity())));
			}
			stack.damageItem(1, entityLiving);
		}
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == Items.LEAD;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemStack) {
		return 24;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
