/*
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import arekkuusu.grimoireofalice.common.lib.LibName;
import arekkuusu.grimoireofalice.common.potion.ModPotions;
import arekkuusu.grimoireofalice.compat.danmakucore.LibGOAShotData;
import com.google.common.collect.Lists;
import net.katsstuff.teamnightclipse.danmakucore.DanmakuCore;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.DanmakuTemplate;
import net.katsstuff.teamnightclipse.danmakucore.lib.LibColor;
import net.katsstuff.teamnightclipse.danmakucore.scalastuff.DanmakuCreationHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import scala.collection.JavaConversions;

import java.util.List;

public class ItemNuclearRod extends ItemBase {

	public ItemNuclearRod() {
		super(LibName.NUCLEAR_ROD);
		setMaxStackSize(1);
		addPropertyOverride(new ResourceLocation("using"),
				(stack, world, entity) -> entity != null && entity.isHandActive() && entity.getActiveItemStack() == stack ? 1F : 0F);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		player.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase livingBase, int count) {
		if(livingBase instanceof EntityPlayer && hasFullSet((EntityPlayer) livingBase)) {
			List<EntityLivingBase> list = livingBase.world.getEntitiesWithinAABB(EntityLivingBase.class, livingBase.getEntityBoundingBox().grow(5), entity -> !livingBase.equals(entity));
			for(EntityLivingBase living : list) {
				living.addPotionEffect(new PotionEffect(ModPotions.RADIATION_POISONING, 100));
			}
		}
		if(count % 50 == 0) {
			livingBase.playSound(GrimoireSoundEvents.CAUTION, 0.2F, 0F);
		}
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer) {
			((EntityPlayer) entityLiving).getCooldownTracker().setCooldown(this, 15);
		}
		int timeUsed = getMaxItemUseDuration(stack) - timeLeft;
		if(timeUsed < 20) {
			return;
		}
		entityLiving.playSound(GrimoireSoundEvents.WAVE, 0.2F, 1F);
		if(!world.isRemote) {
			if(entityLiving.isSneaking()) {
				DanmakuTemplate danmaku = DanmakuTemplate.builder()
						.setUser(entityLiving)
						.setMovementData(0.5F)
						.setShot(LibGOAShotData.SUN.setMainColor(LibColor.COLOR_SATURATED_RED).setSize(5))
						.build();
				if(entityLiving instanceof EntityPlayer && hasFullSet((EntityPlayer) entityLiving)) {
					DanmakuCreationHelper.createSphereShot(danmaku, 4, 5, entityLiving.rotationPitch, 1D);
				} else {
					DanmakuCreationHelper.createCircleShot(danmaku, 5, entityLiving.rotationPitch, 1D);
				}
			} else {
				DanmakuTemplate.Builder builder = DanmakuTemplate.builder()
						.setUser(entityLiving)
						.setShot(LibGOAShotData.SUN.setMainColor(LibColor.COLOR_SATURATED_RED).setSize(5))
						.setMovementData(1F);
				DanmakuCore.proxy().spawnDanmaku(JavaConversions.asScalaBuffer(Lists.newArrayList(builder.build().asEntity())));
				Vec3d vec3 = entityLiving.getLookVec();
				entityLiving.motionX -= vec3.x;
				entityLiving.motionY -= vec3.y;
				entityLiving.motionZ -= vec3.z;
			}
		}
	}

	private static boolean hasFullSet(EntityPlayer player) {
		ItemStack boots = player.inventory.armorInventory.get(0);
		ItemStack body = player.inventory.armorInventory.get(2);
		return !boots.isEmpty() && !body.isEmpty() && boots.getItem() == ModItems.NUCLEAR_BOOTS && body.getItem() == ModItems.UTSUHO_WINGS;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 50;
	}
}
