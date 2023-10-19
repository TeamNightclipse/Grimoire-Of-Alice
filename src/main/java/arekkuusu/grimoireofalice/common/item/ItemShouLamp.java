/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.entity.EntityMagicCircle;
import arekkuusu.grimoireofalice.common.lib.LibName;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.DanmakuTemplate;
import net.katsstuff.teamnightclipse.danmakucore.lib.LibColor;
import net.katsstuff.teamnightclipse.danmakucore.lib.data.LibShotData;
import net.katsstuff.teamnightclipse.danmakucore.scalastuff.DanmakuCreationHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import java.util.List;

public class ItemShouLamp extends ItemJeweled {

	public ItemShouLamp() {
		super(LibName.SHOU_LAMP);
		setMaxStackSize(1);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return JEWELS.get(stack) > 0;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entityIn, int itemSlot, boolean isSelected) {
		if(entityIn instanceof EntityPlayer) {
			EntityPlayer player = ((EntityPlayer) entityIn);
			if(isActive(player, stack)) {
				DanmakuTemplate danmaku = DanmakuTemplate.builder()
						.setUser(player)
						.setMovementData(0.5D, 0.5D, 0.1D)
						.setShot(LibShotData.SHOT_POINTED_LASER_LONG.setMainColor(LibColor.COLOR_SATURATED_YELLOW).setSizeZ(4))
						.build();
				DanmakuCreationHelper.createRandomRingShot(danmaku, 1, 20, 8D);
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
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer player = ((EntityPlayer) entityLiving);
			int timeUsed = getMaxItemUseDuration(stack) - timeLeft;
			if(timeUsed > 500) {
				timeUsed = 500;
			}
			addJewels(stack, (short) timeUsed);
			player.addPotionEffect(new PotionEffect(MobEffects.LUCK, 10, 5));
			player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 0.1F, 1F);
			if(JEWELS.get(stack) > 500){
				JEWELS.set((short) 500, stack);
			}
		}
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			if(player.isSneaking() && !isActive(player, stack)) {
				short jewels = JEWELS.get(stack);
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
		return player.getCooldownTracker().hasCooldown(this) && JEWELS.get(stack) > 0;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.NONE;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 500;
	}
}
