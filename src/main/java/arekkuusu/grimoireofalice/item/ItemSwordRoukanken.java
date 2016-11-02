package arekkuusu.grimoireofalice.item;

import java.util.List;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSwordRoukanken extends ItemModSword {

	public ItemSwordRoukanken(ToolMaterial material) {
		super(material, LibItemName.ROUKANKEN);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + I18n.format("grimoire.tooltip.roukanken_header.name"));
		if(GuiScreen.isShiftKeyDown()) {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.roukanken_description_top.name"));
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.roukanken_description_bottom.name"));
		}
		else {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.roukanken_shift.name"));
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(entityIn instanceof EntityPlayer & isSelected) {
			EntityPlayer player = (EntityPlayer)entityIn;
			Vec3d vec = player.getLookVec();
			if(Math.sqrt(player.motionX * player.motionX + player.motionZ * player.motionZ) > 3.0) {
				List<Entity> list = worldIn.getEntitiesWithinAABBExcludingEntity(player,
						player.getEntityBoundingBox().addCoord(player.motionX, player.motionY, player.motionZ).expand(1.0D, 1.0D, 1.0D));
				for(Entity entity : list) {
					if(!entity.canBeCollidedWith()) {
						continue;
					}
					if(entity instanceof EntityLivingBase) {
						EntityLivingBase living = (EntityLivingBase)entity;
						if(player.canEntityBeSeen(living)) {
							if(!worldIn.isRemote) {
								if(living.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) {
									living.attackEntityFrom(DamageSource.causeMobDamage(player), 16.0F);
									player.onEnchantmentCritical(living);
								}
								else {
									living.attackEntityFrom(DamageSource.causeMobDamage(player), 8F);
								}
							}

							for(int i = 0; i < 4; i++) {
								worldIn.spawnParticle(EnumParticleTypes.SWEEP_ATTACK, player.posX, player.posY + 1, player.posZ, vec.xCoord,
										vec.yCoord, vec.zCoord);
								player.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, 0.5F, 1F);
							}
						}
					}

					stack.damageItem(1, player);
					player.swingArm(EnumHand.MAIN_HAND);
				}
			}
		}
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entityLiving;
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
