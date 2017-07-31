package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;

@Optional.Interface(iface = "net.katsstuff.danmakucore.item.IOwnedBy", modid = "danmakucore")
public class ItemCharmofHealing extends ItemMod implements IOwnedBy {

	public ItemCharmofHealing() {
		super(LibItemName.CHARM_OF_HEALING);
		setMaxDamage(25);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entityIn, int itemSlot, boolean isSelected) {
		if(entityIn instanceof EntityLivingBase && entityIn.ticksExisted % 125 == 0) {
			EntityLivingBase livingBase = (EntityLivingBase) entityIn;
			if(livingBase.getHealth() < livingBase.getMaxHealth()) {
				livingBase.heal(0.1F);
			}
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
		if(entityLiving.getHealth() < entityLiving.getMaxHealth()) {
			stack.damageItem(1, entityLiving);
			entityLiving.heal(2.5F);
			if(entityLiving instanceof EntityPlayer) {
				((EntityPlayer) entityLiving).getCooldownTracker().setCooldown(this, 25);
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

	@Optional.Method(modid = "danmakucore")
	@Override
	public net.katsstuff.danmakucore.entity.living.TouhouCharacter character(ItemStack stack) {
		return net.katsstuff.danmakucore.entity.living.TouhouCharacter.SANAE_KOCHIYA;
	}
}
