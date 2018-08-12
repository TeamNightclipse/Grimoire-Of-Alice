/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.entity.EntityEllyScythe;
import arekkuusu.grimoireofalice.common.entity.EntityMagicCircle;
import arekkuusu.grimoireofalice.common.lib.LibName;
import com.google.common.collect.Lists;
import net.katsstuff.teamnightclipse.danmakucore.DanmakuCore;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.DanmakuTemplate;
import net.katsstuff.teamnightclipse.danmakucore.lib.LibColor;
import net.katsstuff.teamnightclipse.danmakucore.lib.data.LibShotData;
import net.katsstuff.teamnightclipse.mirror.data.AbstractVector3;
import net.katsstuff.teamnightclipse.mirror.data.Vector3;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import scala.collection.JavaConversions;

import java.util.Random;

public class ItemEllyScythe extends ItemBaseSword {

	public ItemEllyScythe(ToolMaterial material) {
		super(material, LibName.ELLY_SCYTHE);
		setNoRepair();
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase user) {
		target.attackEntityFrom(DamageSource.causeThornsDamage(user), 5);
		stack.damageItem(1, user);
		return true;
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
			if(player.isSneaking()) {
				int duration = getMaxItemUseDuration(stack) - timeLeft;
				float durationSeconds = duration / 20F;
				durationSeconds = (durationSeconds * durationSeconds + durationSeconds * 2.0F) / 3F;
				if(durationSeconds < 0.1F) {
					return;
				}

				boolean critical = false;
				if(durationSeconds > 1.5F) {
					durationSeconds = 1.5F;
					critical = true;
				}
				durationSeconds *= 1.5F;

				if(!world.isRemote) {
					EntityEllyScythe scythe = new EntityEllyScythe(world, player, stack, durationSeconds);
					scythe.setCritical(critical);
					Vec3d look = player.getLookVec();
					float distance = 1.5F;
					double dx = player.posX + look.x * distance;
					double dz = player.posZ + look.z * distance;
					scythe.setPosition(dx, player.posY + 1.5, dz);
					world.spawnEntity(scythe);
				}

				if(!player.capabilities.isCreativeMode) {
					player.inventory.mainInventory.set(player.inventory.currentItem, ItemStack.EMPTY);
				}
			} else if(!world.isRemote) {
				for(int i = 0; i < 25; i++) {
					spawnGroundDanmaku(player);
				}
				EntityMagicCircle circle = new EntityMagicCircle(world, player, EntityMagicCircle.EnumTextures.RED_NORMAL, 50);
				world.spawnEntity(circle);
				player.getCooldownTracker().setCooldown(this, 50);
			}
		}
	}

	private static void spawnGroundDanmaku(EntityPlayer player) {
		AbstractVector3 angle = Vector3.getVecWithoutY(Vector3.randomVector());
		Random random = new Random();
		Vector3 posSource = new Vector3(player).offset(angle, random.nextDouble() * 16);
		Vector3 posReach = posSource.offset(Vector3.Down(), 16);
		RayTraceResult ray = player.world.rayTraceBlocks(posSource.toVec3d(), posReach.toVec3d());
		Vector3 spawnPos = ray != null ? new Vector3(ray.hitVec) : posReach;
		DanmakuTemplate.Builder builder = DanmakuTemplate.builder()
				.setUser(player)
				.setDirection(Vector3.Up())
				.setMovementData(0.2D)
				.setPos(spawnPos)
				.setShot(LibShotData.SHOT_SCALE.setMainColor(LibColor.COLOR_SATURATED_RED));
		DanmakuCore.proxy().spawnDanmaku(JavaConversions.asScalaBuffer(Lists.newArrayList(builder.build().asEntity())));
	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemstack) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 72_000;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
