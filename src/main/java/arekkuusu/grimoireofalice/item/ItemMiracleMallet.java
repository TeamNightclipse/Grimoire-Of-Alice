package arekkuusu.grimoireofalice.item;

import java.util.List;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemMiracleMallet extends ItemMod {

	public ItemMiracleMallet() {
		super(LibItemName.MIRACLEMALLET);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(!worldIn.isRemote && (!playerIn.getFoodStats().needFood() || playerIn.capabilities.isCreativeMode)) {
			List<Entity> list = worldIn.getEntitiesWithinAABBExcludingEntity(playerIn, playerIn.getEntityBoundingBox().expandXyz(20.0D));
			if(!list.isEmpty())
			for(Entity ent : list){

			}
		}
		playerIn.swingArm(hand);
		playerIn.getCooldownTracker().setCooldown(this, 50);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}
}
