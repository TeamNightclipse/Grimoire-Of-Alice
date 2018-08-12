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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class ItemSwordRoukanken extends ItemBaseSword  {

	public ItemSwordRoukanken(ToolMaterial material) {
		super(material, LibName.ROUKANKEN);
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
	public void onUpdate(ItemStack stack, World world, Entity entityIn, int itemSlot, boolean isSelected) {
		if(entityIn instanceof EntityPlayer && isSelected) {
			EntityPlayer player = (EntityPlayer) entityIn;

			if(player.motionX * player.motionX + player.motionZ * player.motionZ > 3D * 3D) {
				List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(player,
						player.getEntityBoundingBox().expand(player.motionX, player.motionY, player.motionZ).expand(1.0D, 1.0D, 1.0D));
				for(Entity entity : list) {
					if(!entity.canBeCollidedWith()) {
						continue;
					}
					if(entity instanceof EntityLivingBase) {
						EntityLivingBase living = (EntityLivingBase) entity;
						if(player.canEntityBeSeen(living)) {
							if(!world.isRemote) {
								if(living.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) {
									living.attackEntityFrom(DamageSource.causeMobDamage(player), 16.0F);
									player.onEnchantmentCritical(living);
								}
								else {
									living.attackEntityFrom(DamageSource.causeMobDamage(player), 8F);
								}
							}

							Vec3d vec = player.getLookVec();
							for(int i = 0; i < 4; i++) {
								world.spawnParticle(EnumParticleTypes.SWEEP_ATTACK, player.posX, player.posY + 1, player.posZ, vec.x,
										vec.y, vec.z);
								player.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, 0.5F, 1F);
							}
						}
					}

					stack.damageItem(1, player);
					EnumHand hand = player.getHeldItemMainhand() == stack ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
					player.swingArm(hand);
				}
			}
		}
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer && ((EntityPlayer) entityLiving).onGround) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			int timeUsed = getMaxItemUseDuration(stack) - timeLeft;
			if(timeUsed > 30) {
				timeUsed = 30;
			}
			double speed = timeUsed * 0.3;
			player.motionX = -Math.sin(Math.toRadians(player.rotationYaw)) * speed;
			player.motionZ = Math.cos(Math.toRadians(player.rotationYaw)) * speed;
			if(!player.capabilities.isCreativeMode) {
				player.addExhaustion(1.5F);
			}
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
	public int getItemEnchantability() {
		return 0;
	}
}
