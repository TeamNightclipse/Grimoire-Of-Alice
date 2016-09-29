package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class ItemSwordRoukanken extends ItemModSword {

	ItemSwordRoukanken(ToolMaterial material) {
		super(material, LibItemName.ROUKANKEN);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityIn;
			if (isSelected && player.getCooldownTracker().hasCooldown(stack.getItem())) {
				Vec3d vec = player.getLookVec();
				worldIn.spawnParticle(EnumParticleTypes.SWEEP_ATTACK, player.posX + 1.5 + vec.xCoord, player.posY + 1, player.posZ + 1.5 + vec.zCoord, vec.xCoord, vec.yCoord, vec.zCoord);
				if (!worldIn.isRemote) {
					List<EntityLivingBase> entitiesWithinAABB = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, player.getEntityBoundingBox().expandXyz(1));
					if (!entitiesWithinAABB.isEmpty()) {
						for (EntityLivingBase living : entitiesWithinAABB) {
							living.attackEntityFrom(DamageSource.generic, 4);
							living.motionX = vec.xCoord * 0.5;
							living.motionY = 0.1;
							living.motionZ = vec.zCoord * 0.5;
						}
					}
				}
			}
		}
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			int timeUsed = getMaxItemUseDuration(stack) - timeLeft;
			if(timeUsed > 30 ){
				timeUsed = 30;
			}
			double speed = timeUsed * 0.3;
			player.motionX = -Math.sin(Math.toRadians(player.rotationYaw)) * speed;
			player.motionZ = Math.cos(Math.toRadians(player.rotationYaw)) * speed;
			if (!player.capabilities.isCreativeMode) {
				player.addExhaustion(1.5F);
			}
			player.getCooldownTracker().setCooldown(this, timeUsed);
			stack.damageItem(1, player);
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 7000;
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
