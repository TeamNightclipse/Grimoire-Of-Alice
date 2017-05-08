package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.entity.EntitySpiritualStrikeTalisman;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;

@Optional.Interface(iface = "IOwnedBy", modid = "danmakucore", striprefs = true)
public class ItemSpiritualStrikeTalisman extends ItemMod implements IOwnedBy {

	public ItemSpiritualStrikeTalisman() {
		super(LibItemName.SPIRITUAL_STRIKE_TALISMAN);
		setMaxStackSize(16);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.getCooldownTracker().setCooldown(this, 50);
		playerIn.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 0.1F, 0.1F);
		if(!worldIn.isRemote) {
			EntitySpiritualStrikeTalisman talisman = new EntitySpiritualStrikeTalisman(worldIn, playerIn);
			Vec3d look = playerIn.getLookVec();
			float distance = 2F;
			double dx = playerIn.posX + look.xCoord * distance;
			double dy = playerIn.posY + look.yCoord * distance + playerIn.getEyeHeight();
			double dz = playerIn.posZ + look.zCoord * distance;
			talisman.setPosition(dx, dy, dz);
			worldIn.spawnEntityInWorld(talisman);
		}
		if(!playerIn.capabilities.isCreativeMode) {
			--itemStackIn.stackSize;
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Optional.Method(modid = "danmakucore")
	@Override
	public net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters character(ItemStack stack) {
		return net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters.REIMU_HAKUREI;
	}
}
