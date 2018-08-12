package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.lib.LibName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.List;

public class ItemGhostAnchor extends ItemBaseSword {

	public ItemGhostAnchor(ToolMaterial material) {
		super(material, LibName.GHOST_ANCHOR);
		addPropertyOverride(new ResourceLocation("active"),
				(stack, world, entity) -> entity != null && (entity.motionX * entity.motionX + entity.motionZ * entity.motionZ + entity.motionY * entity.motionY > 6D) ? 1F : 0F);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entityIn, int itemSlot, boolean isSelected) {
		if(!world.isRemote && isSelected && entityIn instanceof EntityLivingBase) {
			EntityLivingBase player = (EntityLivingBase) entityIn;
			if(player.motionX * player.motionX + player.motionZ * player.motionZ + player.motionY * player.motionY > 6D) {
				List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(player,
						player.getEntityBoundingBox().expand(2.0D, 2.0D, 2.0D));
				for(Entity entity : list) {
					if(!entity.canBeCollidedWith()) {
						continue;
					}
					entity.attackEntityFrom(DamageSource.causeMobDamage(player), 16.0F);
					if(world instanceof WorldServer) {
						((WorldServer) world).spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, player.posX, player.posY + 1, player.posZ, 5
								, 0D, 0D, 0D, 0D);
					}

					if(player instanceof EntityPlayerMP) {
						EntityPlayerMP playerMP = (EntityPlayerMP) player;
						playerMP.setPositionAndUpdate(playerMP.prevPosX, playerMP.posY, playerMP.prevPosZ);
					}

					world.playSound(null, player.getPosition(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 1F, 1F);

					player.motionX = 0;
					player.motionZ = 0;

					stack.damageItem(1, player);
				}
			}
		}
		if(entityIn.isInWater()) {
			entityIn.motionY -= 0.05;
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
			EntityPlayer player = (EntityPlayer) entityLiving;
			int timeUsed = getMaxItemUseDuration(stack) - timeLeft;
			if(timeUsed < 20) {
				return;
			}
			double speed = 6;
			player.motionX = -MathHelper.sin((float) Math.toRadians(player.rotationYaw)) * speed;
			player.motionY = -MathHelper.sin((float) Math.toRadians(player.rotationPitch)) * speed;
			player.motionZ = MathHelper.cos((float) Math.toRadians(player.rotationYaw)) * speed;
			if(!player.capabilities.isCreativeMode) {
				player.addExhaustion(1.5F);
			}
			player.getCooldownTracker().setCooldown(this, 50);
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemStack) {
		return 500;
	}
}
