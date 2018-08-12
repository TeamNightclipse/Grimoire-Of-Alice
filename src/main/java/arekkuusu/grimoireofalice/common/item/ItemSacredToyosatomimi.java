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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

import static net.minecraft.util.text.TextFormatting.*;

public class ItemSacredToyosatomimi extends ItemSwordOwner  {

	public ItemSacredToyosatomimi(ToolMaterial material) {
		super(material, LibName.SACRED_SWORD_OF_TOYOSATOMIMI);
		setNoRepair();
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		target.motionX = -MathHelper.sin((float) Math.toRadians(attacker.rotationYaw));
		target.motionZ = MathHelper.cos((float) Math.toRadians(attacker.rotationYaw));
		return true;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		player.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
		if(!world.isRemote) {
			int timeUsed = getMaxItemUseDuration(stack) - timeLeft;
			if(timeUsed > 50) {
				return;
			}

			if(entityLiving instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) entityLiving;
				if(isOwner(stack, player)) {
					List<EntityLivingBase> list = world.getEntitiesWithinAABB(EntityLivingBase.class, player.getEntityBoundingBox().grow(timeUsed), livingBase -> !player.equals(livingBase));
					if(!list.isEmpty()) {
						player.sendMessage(
								new TextComponentString(GOLD + "- - - - - - - - - - - - - - - - - - - - - - - - -"));
						list.forEach(mob -> player.sendMessage(new TextComponentString(
								getColorForEntity(mob) + "- " + mob.getName() + RESET + ITALIC + " : {" + (int) mob.posX + ", " + (int) mob.posY + ", " + (int) mob.posZ + "}")));
						player.sendMessage(
								new TextComponentString(GOLD + "- - - - - - - - - - - - - - - - - - - - - - - - -"));
					}
					else {
						player.sendMessage(new TextComponentString(GOLD + ""
								+ new TextComponentTranslation("item.toyosatomimi.empty").getFormattedText()));
					}
				}
				player.getCooldownTracker().setCooldown(this, timeLeft);
				stack.damageItem(1, player);
			}
		}
	}

	private static TextFormatting getColorForEntity(EntityLivingBase livingBase) {
		if(livingBase instanceof EntityMob) {
			return DARK_RED;
		}
		else if(livingBase instanceof EntityAnimal) {
			return GOLD;
		}
		else if(livingBase instanceof EntityWaterMob) {
			return DARK_AQUA;
		}

		return DARK_GRAY;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 50;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
