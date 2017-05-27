/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import java.util.List;

import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.item.IOwnedBy;
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
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Optional.Interface(iface = "net.katsstuff.danmakucore.item.IOwnedBy", modid = "danmakucore")
public class ItemSwordRoukanken extends ItemModSword implements IOwnedBy {

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
		list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.roukanken_header.name"));
		if(GuiScreen.isShiftKeyDown()) {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.roukanken_description_top.name"));
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.roukanken_description_bottom.name"));
		}
		else {
			list.add(TextFormatting.DARK_GRAY + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.roukanken_shift.name"));
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

			if(player.motionX * player.motionX + player.motionZ * player.motionZ > 3D * 3D) {
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

							Vec3d vec = player.getLookVec();
							for(int i = 0; i < 4; i++) {
								worldIn.spawnParticle(EnumParticleTypes.SWEEP_ATTACK, player.posX, player.posY + 1, player.posZ, vec.xCoord,
										vec.yCoord, vec.zCoord);
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
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer && ((EntityPlayer) entityLiving).onGround) {
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

	@Optional.Method(modid = "danmakucore")
	@Override
	public net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters character(ItemStack stack) {
		return net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters.YOUMU_KONPAKU;
	}
}
