package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.List;

public class ItemGhostAnchor extends ItemModSword {

	public ItemGhostAnchor(ToolMaterial material) {
		super(material, LibItemName.GHOST_ANCHOR);
		addPropertyOverride(new ResourceLocation("active"),
				(stack, world, entity) -> entity != null && entity instanceof EntityPlayer
						&& ((EntityPlayer)entity).getCooldownTracker().hasCooldown(this) ? 1F : 0F);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (isSelected && entityIn instanceof EntityLivingBase) {
			EntityLivingBase player = (EntityLivingBase) entityIn;

			if(player.motionX * player.motionX + player.motionZ * player.motionZ + player.motionY * player.motionY > 9D) {
				List<Entity> list = worldIn.getEntitiesWithinAABBExcludingEntity(player,
						player.getEntityBoundingBox().expand(2.0D, 2.0D, 2.0D));
				for(Entity entity : list) {
					if (!entity.canBeCollidedWith()) {
						continue;
					}
					if (player.canEntityBeSeen(entity)) {
						if (!worldIn.isRemote) {
							entity.attackEntityFrom(DamageSource.causeMobDamage(player), 16.0F);
							if (worldIn instanceof WorldServer) {
								((WorldServer) worldIn).spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, player.posX, player.posY + 1, player.posZ, 5
										, 0D, 0D, 0D, 0D);
							}

							EntityPlayerMP playerMP = (EntityPlayerMP) player;
							playerMP.setPositionAndUpdate(playerMP.prevPosX, playerMP.posY, playerMP.prevPosZ);
						}
						player.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1F, 1F);

						player.motionX = 0;
						player.motionZ = 0;
					}

					stack.damageItem(1, player);
				}
			}
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entityLiving;
			int timeUsed = getMaxItemUseDuration(stack) - timeLeft;
			if(timeUsed < 40) {
				return;
			}
			double speed = 6;
			player.motionX = -Math.sin(Math.toRadians(player.rotationYaw)) * speed;
			player.motionY = -Math.sin(Math.toRadians(player.rotationPitch)) * speed;
			player.motionZ = Math.cos(Math.toRadians(player.rotationYaw)) * speed;
			if(!player.capabilities.isCreativeMode) {
				player.addExhaustion(1.5F);
			}
			player.getCooldownTracker().setCooldown(this, 50);
		}
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100));
	}

	@Override
	public EnumAction getItemUseAction(ItemStack p_77661_1_) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemStack) {
		return 500;
	}
}
