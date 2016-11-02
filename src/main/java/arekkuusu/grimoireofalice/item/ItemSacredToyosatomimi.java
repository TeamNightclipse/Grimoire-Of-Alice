package arekkuusu.grimoireofalice.item;

import java.util.List;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSacredToyosatomimi extends ItemSwordOwner {

	public ItemSacredToyosatomimi(ToolMaterial material) {
		super(material, LibItemName.SACRED_TOYOSATOMIMI);
		setNoRepair();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + I18n.format("grimoire.tooltip.sacred_sword_of_toyosatomimi_header.name"));
		if(GuiScreen.isShiftKeyDown()) {
			list.add(TextFormatting.GRAY + I18n.format("grimoire.tooltip.sacred_sword_of_toyosatomimi_description_top.name"));
			list.add(TextFormatting.GRAY + I18n.format("grimoire.tooltip.sacred_sword_of_toyosatomimi_description_middle.name"));
			list.add(TextFormatting.GRAY + I18n.format("grimoire.tooltip.sacred_sword_of_toyosatomimi_description_bottom.name"));
		}
		else {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.sacred_sword_of_toyosatomimi_shift.name"));
		}
		super.addInformation(stack, player, list, p_77624_4_);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		target.motionX = -Math.sin(Math.toRadians(attacker.rotationYaw));
		target.motionZ = Math.cos(Math.toRadians(attacker.rotationYaw));
		return true;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if(!worldIn.isRemote) {
			int timeUsed = getMaxItemUseDuration(stack) - timeLeft;
			if(timeUsed > 50) return;

			if(entityLiving instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer)entityLiving;
				if(isOwner(stack, player)) {
					List<EntityMob> list = worldIn.getEntitiesWithinAABB(EntityMob.class, player.getEntityBoundingBox().expandXyz(timeUsed));
					if(!list.isEmpty()) {
						player.addChatComponentMessage(
								new TextComponentString(TextFormatting.GOLD + "- - - - - - - - - - - - - - - - - - - - - - - - -"));
						list.forEach(mob -> player.addChatComponentMessage(new TextComponentString(
								TextFormatting.GOLD + "- " + mob.getName() + " : {" + mob.posX + ", " + mob.posY + ", " + mob.posZ + "}")));
						player.addChatComponentMessage(
								new TextComponentString(TextFormatting.GOLD + "- - - - - - - - - - - - - - - - - - - - - - - - -"));
					}
					else {
						player.addChatComponentMessage(new TextComponentString(TextFormatting.GOLD + "- - - - - - - No souls found - - - - - - -"));
					}
				}
				player.getCooldownTracker().setCooldown(this, timeLeft);
				stack.damageItem(1, player);
			}
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BLOCK;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 50;
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
