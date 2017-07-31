package arekkuusu.grimoireofalice.common.plugin.danmakucore.item;

import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import arekkuusu.grimoireofalice.common.item.ItemMod;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.data.Quat;
import net.katsstuff.danmakucore.entity.danmaku.DanmakuTemplate;
import net.katsstuff.danmakucore.entity.danmaku.EntityDanmaku;
import net.katsstuff.danmakucore.entity.living.TouhouCharacter;
import net.katsstuff.danmakucore.helper.DanmakuCreationHelper;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.katsstuff.danmakucore.lib.data.LibShotData;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
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

import java.util.List;

public class ItemRedStoneofAja extends ItemMod implements IOwnedBy {

	public ItemRedStoneofAja() {
		super(LibItemName.RED_STONE_OF_AJA);
		setMaxDamage(150);
		setMaxStackSize(1);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		tooltip.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.red_stone_of_aja_header.name"));
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
					EntityDanmaku lazer = DanmakuTemplate.builder()
							.setUser(entityLiving)
							.setShot(LibShotData.SHOT_POINTED_LASER.setSizeZ(4))
							.setMovementData(3D)
							.build().asEntity();
					for(int i = 0; i < 4; i++) {
						DanmakuTemplate circle = DanmakuTemplate.builder()
								.setUser(entityLiving)
								.setShot(LibShotData.SHOT_CIRCLE.setSize(1.5F).setDelay(i * 2))
								.setMovementData(3D)
								.build();
						DanmakuCreationHelper.createWideShot(Quat.orientationOf(entityLiving), circle, 2, 15, 0, 1F);
					}
					world.spawnEntity(lazer);
				}
				else {
					EntityDanmaku danmaku = DanmakuTemplate.builder()
							.setUser(entityLiving)
							.setShot(LibShotData.SHOT_POINTED_LASER.setSizeZ(4))
							.setMovementData(4D)
							.build().asEntity();
					world.spawnEntity(danmaku);
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

	@Override
	public TouhouCharacter character(ItemStack stack) {
		return TouhouCharacter.KAGUYA_HOURAISAN;
	}
}
