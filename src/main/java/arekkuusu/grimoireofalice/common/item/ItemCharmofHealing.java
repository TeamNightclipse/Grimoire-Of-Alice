package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemCharmofHealing extends ItemMod {

	public ItemCharmofHealing() {
		super(LibItemName.CHARM_OF_HEALING);
		setMaxDamage(25);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof EntityLivingBase && entityIn.ticksExisted % 125 == 0) {
			EntityLivingBase livingBase = (EntityLivingBase) entityIn;
			if (livingBase.getHealth() < livingBase.getMaxHealth()) {
				livingBase.heal(0.1F);
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
		if (entityLiving.getHealth() < entityLiving.getMaxHealth()) {
			stack.damageItem(1, entityLiving);
			entityLiving.heal(2.5F);
			if(entityLiving instanceof EntityPlayer) {
				((EntityPlayer)entityLiving).getCooldownTracker().setCooldown(this, 25);
			}
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack p_77661_1_) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemStack) {
		return 25;
	}
}
