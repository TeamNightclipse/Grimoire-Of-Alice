package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import arekkuusu.grimoireofalice.common.lib.LibName;
import com.google.common.collect.Lists;
import net.katsstuff.teamnightclipse.danmakucore.DanmakuCore;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.DanmakuTemplate;
import net.katsstuff.teamnightclipse.danmakucore.lib.data.LibShotData;
import net.katsstuff.teamnightclipse.danmakucore.scalastuff.DanmakuCreationHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import scala.collection.JavaConversions;

public class ItemRedStoneofAja extends ItemBase {

	public ItemRedStoneofAja() {
		super(LibName.RED_STONE_OF_AJA);
		setMaxDamage(150);
		setMaxStackSize(1);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1F, 1F);
		player.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
		if(timeLeft <= 90) {
			if(!world.isRemote) {
				if(world.canSeeSky(entityLiving.getPosition())) {
					DanmakuTemplate.Builder builder = DanmakuTemplate.builder()
							.setUser(entityLiving)
							.setShot(LibShotData.SHOT_POINTED_LASER.setSizeZ(4))
							.setMovementData(3D);
					DanmakuCore.proxy().spawnDanmaku(JavaConversions.asScalaBuffer(Lists.newArrayList(builder.build().asEntity())));
					for(int i = 0; i < 4; i++) {
						DanmakuTemplate circle = DanmakuTemplate.builder()
								.setUser(entityLiving)
								.setShot(LibShotData.SHOT_CIRCLE.setSize(1.5F).setDelay(i * 2))
								.setMovementData(3D)
								.build();
						DanmakuCreationHelper.createWideShot(circle, 2, 15, 0, 1F);
					}
				} else {
					DanmakuTemplate.Builder builder = DanmakuTemplate.builder()
							.setUser(entityLiving)
							.setShot(LibShotData.SHOT_POINTED_LASER.setSizeZ(4))
							.setMovementData(4D);
					DanmakuCore.proxy().spawnDanmaku(JavaConversions.asScalaBuffer(Lists.newArrayList(builder.build().asEntity())));
				}
			}
			entityLiving.playSound(GrimoireSoundEvents.POWER_UP, 0.2F, 1F);

			if(entityLiving instanceof EntityPlayer) {
				int xp = ((EntityPlayer) entityLiving).experienceLevel;
				if(xp == 0 || itemRand.nextInt(xp) == 0) {
					stack.damageItem(1, entityLiving);
				}
			}
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 100;
	}
}
