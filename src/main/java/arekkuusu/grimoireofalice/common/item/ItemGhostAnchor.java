package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.client.resources.I18n;
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
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@Optional.Interface(iface = "net.katsstuff.danmakucore.item.IOwnedBy", modid = "danmakucore")
public class ItemGhostAnchor extends ItemModSword implements IOwnedBy {

	public ItemGhostAnchor(ToolMaterial material) {
		super(material, LibItemName.GHOST_ANCHOR);
		addPropertyOverride(new ResourceLocation("active"),
				(stack, world, entity) -> entity != null && (entity.motionX * entity.motionX + entity.motionZ * entity.motionZ + entity.motionY * entity.motionY > 9D) ? 1F : 0F);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly (Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.ghost_anchor_header.name"));
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!world.isRemote && isSelected && entityIn instanceof EntityLivingBase) {
            EntityLivingBase player = (EntityLivingBase) entityIn;
            if (player.motionX * player.motionX + player.motionZ * player.motionZ + player.motionY * player.motionY > 9D) {
                List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(player,
                        player.getEntityBoundingBox().expand(2.0D, 2.0D, 2.0D));
                for (Entity entity : list) {
                    if (!entity.canBeCollidedWith()) {
                        continue;
                    }
                    entity.attackEntityFrom(DamageSource.causeMobDamage(player), 16.0F);
                    if (world instanceof WorldServer) {
                        ((WorldServer) world).spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, player.posX, player.posY + 1, player.posZ, 5
                                , 0D, 0D, 0D, 0D);
                    }

                    if(player instanceof EntityPlayerMP) {
                        EntityPlayerMP playerMP = (EntityPlayerMP) player;
                        playerMP.setPositionAndUpdate(playerMP.prevPosX, playerMP.posY, playerMP.prevPosZ);
                    }

                    world.playSound(null, player.getPosition(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 1F, 1F );

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
			EntityPlayer player = (EntityPlayer)entityLiving;
			int timeUsed = getMaxItemUseDuration(stack) - timeLeft;
			if(timeUsed < 40) {
				return;
			}
			double speed = 6;
			player.motionX = -MathHelper.sin((float)Math.toRadians(player.rotationYaw)) * speed;
			player.motionY = -MathHelper.sin((float)Math.toRadians(player.rotationPitch)) * speed;
			player.motionZ = MathHelper.cos((float)Math.toRadians(player.rotationYaw)) * speed;
			if(!player.capabilities.isCreativeMode) {
				player.addExhaustion(1.5F);
			}
			player.getCooldownTracker().setCooldown(this, 50);
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack p_77661_1_) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemStack) {
		return 500;
	}

	@Optional.Method(modid = "danmakucore")
	@Override
	public net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters character(ItemStack stack) {
		return net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters.MINAMITSU_MURASA;
	}
}
