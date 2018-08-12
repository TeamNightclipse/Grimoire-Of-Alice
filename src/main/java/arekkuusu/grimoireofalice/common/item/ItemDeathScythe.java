/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.lib.LibName;
import net.katsstuff.teamnightclipse.danmakucore.helper.MathUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class ItemDeathScythe extends ItemBaseSword  {

	public ItemDeathScythe(ToolMaterial material) {
		super(material, LibName.KOMACHI_SCYTHE);
		setNoRepair();
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entityIn, int itemSlot, boolean isSelected) {
		if(!world.isRemote) {
			List<EntityMob> list = world.getEntitiesWithinAABB(EntityMob.class, entityIn.getEntityBoundingBox().grow(10));
			list.stream().filter(entityMob -> entityMob.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD)
					.forEach(entityMob -> {
						entityMob.setAttackTarget(null);
						entityMob.setRevengeTarget(null);
					});
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		player.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase user, int count) {
		if(user instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) user;
			double range = 16.0D;
			Vec3d look = player.getLookVec();
			Vec3d vec3d = new Vec3d(player.posX, player.posY + player.getEyeHeight(), player.posZ);
			Vec3d vec3d1 = new Vec3d(player.posX + look.x * range, player.posY + look.y * range, player.posZ + look.z * range);
			RayTraceResult movingObjectPosition = player.world.rayTraceBlocks(vec3d, vec3d1, false, true, true);
			if(movingObjectPosition != null) {
				vec3d1 = new Vec3d(movingObjectPosition.hitVec.x, movingObjectPosition.hitVec.y, movingObjectPosition.hitVec.z);
			}
			EntityLivingBase entity = null;
			List<EntityLivingBase> list = player.world.getEntitiesWithinAABB(EntityLivingBase.class,
					player.getEntityBoundingBox().expand(look.x * range, look.y * range, look.z * range).grow(1.0D),
					entityFound -> !player.equals(entityFound));
			double d = 0.0D;
			for(EntityLivingBase entity1 : list) {
				float f2 = 0.3F;
				AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().expand(f2, f2, f2);
				RayTraceResult movingObjectPosition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
				if(movingObjectPosition1 != null) {
					double d1 = vec3d.distanceTo(movingObjectPosition1.hitVec);
					if(d1 < d || MathUtil.fuzzyEqual(d, 0D)) {
						entity = entity1;
						d = d1;
					}
				}
			}

			if(entity != null && !player.world.isRemote) {
				double back = 0.6D;
				if(player.isSneaking()) {
					entity.motionX += look.x * back;
					entity.motionY += look.y * back;
					entity.motionZ += look.z * back;
				}
				else if(entity.getDistanceSq(player) > 2) {
					entity.motionX -= look.x * back;
					entity.motionY -= look.y * back;
					entity.motionZ -= look.z * back;
				}
				stack.damageItem(1, player);
			}
		}
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
		if(!world.isRemote && entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entityLiving;
			player.getCooldownTracker().setCooldown(this, 10);
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.NONE;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 7000;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
