package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.entity.EntitySpiritualStrikeTalisman;
import arekkuusu.grimoireofalice.common.lib.LibName;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemSpiritualStrikeTalisman extends ItemBase  {

	public ItemSpiritualStrikeTalisman() {
		super(LibName.SPIRITUAL_STRIKE_TALISMAN);
		setMaxStackSize(16);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		player.getCooldownTracker().setCooldown(this, 50);
		player.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 0.1F, 0.1F);
		if(!world.isRemote) {
			EntitySpiritualStrikeTalisman talisman = new EntitySpiritualStrikeTalisman(world, player);
			Vec3d look = player.getLookVec();
			float distance = 2F;
			double dx = player.posX + look.x * distance;
			double dy = player.posY + look.y * distance + player.getEyeHeight();
			double dz = player.posZ + look.z * distance;
			talisman.setPosition(dx, dy, dz);
			world.spawnEntity(talisman);
		}
		if(!player.capabilities.isCreativeMode) {
			stack.shrink(1);
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}
}
