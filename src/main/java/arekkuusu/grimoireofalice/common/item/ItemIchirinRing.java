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

import arekkuusu.grimoireofalice.common.entity.EntityUnzanFist;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemIchirinRing extends ItemModSword {

	public ItemIchirinRing(ToolMaterial material) {
		super(material, LibItemName.ICHIRIN_RING);
		setMaxStackSize(1);
		setNoRepair();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.ichirin_ring_header.name"));
		if(GuiScreen.isShiftKeyDown()) {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.ichirin_use.name"));
			if(!isHoldingItemsBothHands(player)) {
				list.add(TextFormatting.DARK_RED + I18n.format("grimoire.tooltip.ichirin_inactive.name"));
			}
			else {
				list.add(TextFormatting.DARK_AQUA + I18n.format("grimoire.tooltip.ichirin_active.name"));
			}
		}
		else {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.ichirin_shift.name"));
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(isHoldingItemsBothHands(playerIn)) {
			playerIn.setActiveHand(hand);
			return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
		}
		return new ActionResult<>(EnumActionResult.FAIL, itemStackIn);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer playerIn = (EntityPlayer) entityLiving;
			if (isWearingUnzan(playerIn)) {
				if (!worldIn.isRemote) {
					EntityUnzanFist fist = new EntityUnzanFist(worldIn, playerIn);
					worldIn.spawnEntityInWorld(fist);
					fist.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0F, 2F, 0F);
				}
				playerIn.playSound(SoundEvents.ENTITY_IRONGOLEM_HURT, 1F, itemRand.nextFloat() * 0.4F + 0.8F);
				playerIn.getCooldownTracker().setCooldown(this, 10);
			}
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if(attacker instanceof EntityPlayer && !isWearingUnzan((EntityPlayer) attacker)) {
			stack.damageItem(1, attacker);
		} else {
			target.attackEntityFrom(DamageSource.causeThornsDamage(attacker), 5);
			attacker.playSound(SoundEvents.ENTITY_IRONGOLEM_HURT, 1F, itemRand.nextFloat() * 0.4F + 0.8F);
		}
		return true;
	}

	private boolean isHoldingItemsBothHands(EntityPlayer player) {
		ItemStack main = player.getHeldItemMainhand();
		ItemStack off = player.getHeldItemOffhand();
		return main != null && off != null && main.getItem() == off.getItem();
	}

	private boolean isWearingUnzan(EntityPlayer player) {
		ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		return stack != null && stack.getItem() == ModItems.ICHIRIN_UNZAN;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.NONE;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 100;
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
