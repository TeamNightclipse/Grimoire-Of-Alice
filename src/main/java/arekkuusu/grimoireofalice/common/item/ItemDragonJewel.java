/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.entity.EntityDragonJewel;
import arekkuusu.grimoireofalice.common.lib.LibName;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.DanmakuTemplate;
import net.katsstuff.teamnightclipse.danmakucore.lib.data.LibShotData;
import net.katsstuff.teamnightclipse.danmakucore.scalastuff.DanmakuCreationHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemDragonJewel extends ItemBase {

	public ItemDragonJewel() {
		super(LibName.DRAGON_JEWEL);
		setMaxStackSize(1);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entityIn, int itemSlot, boolean isSelected) {
		if(entityIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityIn;
			if(player.getCooldownTracker().hasCooldown(this) && player.ticksExisted % 2 == 0) {
				if(!world.isRemote) {
					DanmakuTemplate.Builder builder = DanmakuTemplate.builder()
							.setUser(player)
							.setShot(LibShotData.SHOT_CRYSTAL1.setCoreColor(0x000000).setEdgeColor(0XFFFFFF))
							.setMovementData(0.5F);
					float cooldown = player.getCooldownTracker().getCooldown(this, 0);
					DanmakuCreationHelper.createWideShot(builder.build(), 3, 270, 360 * 2 * cooldown, 1);
				} else {
					world.playSound(player, player.getPosition(), SoundEvents.ENTITY_ILLAGER_CAST_SPELL, SoundCategory.PLAYERS, 0.1F, 1F);
				}
			}
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if(player.isSneaking()) {
			spawnJewel(stack, world, player);
		} else {
			player.getCooldownTracker().setCooldown(this, 120);
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	private static void spawnJewel(ItemStack stack, World world, EntityPlayer player) {
		world.playSound(player, player.getPosition(), SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE, SoundCategory.PLAYERS, 1F, 1F);
		if(!world.isRemote) {
			EntityDragonJewel jewel = new EntityDragonJewel(world, player);
			jewel.setPosition(player.posX, player.posY + 2, player.posZ);
			world.spawnEntity(jewel);
		}
		if(!player.capabilities.isCreativeMode) {
			stack.shrink(1);
		}
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
									  float hitX, float hitY, float hitZ) {
		spawnJewel(player.getHeldItem(hand), world, player);
		return EnumActionResult.SUCCESS;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 100;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
