/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.lib.LibName;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.DanmakuTemplate;
import net.katsstuff.teamnightclipse.danmakucore.lib.LibColor;
import net.katsstuff.teamnightclipse.danmakucore.lib.data.LibShotData;
import net.katsstuff.teamnightclipse.danmakucore.scalastuff.DanmakuCreationHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemJeweledHourai extends ItemJeweled {

	private static final int[] COLORS = {
			LibColor.COLOR_SATURATED_GREEN,
			LibColor.COLOR_SATURATED_YELLOW,
			LibColor.COLOR_SATURATED_MAGENTA,
			LibColor.COLOR_SATURATED_RED,
			LibColor.COLOR_SATURATED_BLUE
	};

	public ItemJeweledHourai() {
		super(LibName.JEWELED_HOURAI);
		setNoRepair();
		setMaxStackSize(1);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entityIn, int itemSlot, boolean isSelected) {
		if(!world.isRemote && entityIn instanceof EntityPlayer) {
			short jewels = JEWELS.get(stack);
			int i = MathHelper.clamp(((EntityPlayer) entityIn).experienceLevel * 2, 0, 150);
			if(jewels < 5 && entityIn.ticksExisted % (200 - i) == 0) {
				jewels += 1;
				if(jewels < 0) {
					jewels = 0;
				} else if(jewels > 5) {
					jewels = 5;
				}
				JEWELS.set(jewels, stack);
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
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		target.attackEntityFrom(DamageSource.causeThornsDamage(attacker), 5);
		return true;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
		if(!world.isRemote) {
			short jewels = JEWELS.get(stack);
			if(jewels >= 1) {
				int timeUsed = stack.getMaxItemUseDuration() - timeLeft;
				if(timeUsed > 30) {
					timeUsed = 30;
				}
				if(!entityLiving.isSneaking()) {
					int color = COLORS[itemRand.nextInt(COLORS.length)];
					DanmakuTemplate danmaku = DanmakuTemplate.builder()
							.setUser(entityLiving)
							.setShot(LibShotData.SHOT_CRYSTAL1.setMainColor(color).setDamage(5F))
							.setMovementData(0.8D)
							.build();
					DanmakuCreationHelper.createRingShot(danmaku, timeUsed, timeUsed * 0.5F, 15, 0D);
					addJewels(stack, (short) -1);
				} else {
					for(int i = 0; i < jewels; i++) {
						int color = COLORS[itemRand.nextInt(COLORS.length)];
						DanmakuTemplate danmaku = DanmakuTemplate.builder()
								.setUser(entityLiving)
								.setShot(LibShotData.SHOT_CRYSTAL1.setSize(0.4F).setMainColor(color).setDamage(5F).setDelay(i))
								.setMovementData(0.8D)
								.build();
						DanmakuCreationHelper.createRingShot(danmaku, timeUsed, 1 + timeUsed * 0.75F * ((float)i / (float)jewels), 15F, 0D);
					}
					JEWELS.set((short) 0, stack);
				}
			}
		}
		world.playSound(null, new BlockPos(entityLiving.posX + 0.5D, entityLiving.posY + 0.5D, entityLiving.posZ + 0.5D),
				SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemstack) {
		return EnumAction.BLOCK;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 500;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
