package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemSwordRoukanken extends ItemModSword {

	ItemSwordRoukanken(ToolMaterial material) {
		super(material, LibItemName.ROUKANKEN);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Long-bladed katana said to be made by youkai");
		if(GuiScreen.isShiftKeyDown()){
			list.add(TextFormatting.ITALIC + " \"The things that cannot be cut by my Roukanken,");
			list.add(TextFormatting.ITALIC + " forged by youkai, are close to none!\"");
		} else {
			list.add(TextFormatting.ITALIC + "SHIFT for details");
		}
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
			if (isSelected) {
				Vec3d vec = player.getLookVec();
				double xx, zz;
				xx = player.motionX;
				zz = player.motionZ;
				if (Math.sqrt(xx*xx + zz*zz) > 3.0) {
					List<?> list = worldIn.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().addCoord(player.motionX, player.motionY, player.motionZ).expand(1.0D, 1.0D, 1.0D));
					for (Object aList : list) {
						Entity entity1 = (Entity) aList;
						if (!entity1.canBeCollidedWith()) {
							continue;
						}
						RayTraceResult rayTraceResult = new RayTraceResult(entity1);
						if (rayTraceResult.entityHit instanceof EntityLivingBase) {
							EntityLivingBase living = (EntityLivingBase) rayTraceResult.entityHit;
							if (player.canEntityBeSeen(living)) {
								if (living.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) {
									living.attackEntityFrom(DamageSource.causeMobDamage(player), 16.0F);
									player.onEnchantmentCritical(living);
								} else {
									living.attackEntityFrom(DamageSource.causeMobDamage(player), 8F);
								}
								for(int i = 0; i < 4; i++) {
									worldIn.spawnParticle(EnumParticleTypes.SWEEP_ATTACK, player.posX + 0.5, player.posY + 1, player.posZ + 0.5, vec.xCoord, vec.yCoord, vec.zCoord);
									worldIn.playSound(null, player.posX + 0.5, player.posY + 1, player.posZ + 0.5, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 0.5F, 1F);
								}
							}
						}
						stack.damageItem(1, player);
						player.swingArm(EnumHand.MAIN_HAND);
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
