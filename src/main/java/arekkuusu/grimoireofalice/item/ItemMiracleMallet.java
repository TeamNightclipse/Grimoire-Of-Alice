package arekkuusu.grimoireofalice.item;

import java.util.List;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMiracleMallet extends ItemMod {

	public ItemMiracleMallet() {
		super(LibItemName.MIRACLE_MALLET);
		setNoRepair();
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Ancient inchling artifact, passed down through the generations,");
		list.add(TextFormatting.GOLD + "said to be able to grant any wish the user wishes.");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	private void useMallet(EntityPlayer player, EnumHand hand) {
		if (!player.getFoodStats().needFood() || player.capabilities.isCreativeMode) {
			if (!player.getEntityData().hasKey("MalletResized")) {
				float size = player.isSneaking() ? 0.5F : 1.5F;
				player.getEntityData().setFloat("MalletResized", size);

				player.eyeHeight = player.eyeHeight + (player.isSneaking() ? -0.92F : 1.00F);

				AxisAlignedBB axisAlignedBB = player.getEntityBoundingBox(); //Get Bounding Box
				axisAlignedBB.expandXyz(player.isSneaking() ? -1.00F: 1.00F); //Expand bounding Box
				player.setEntityBoundingBox(axisAlignedBB); //Set Bounding Box (Which never happens)
			} else {
				float size = player.getEntityData().getFloat("MalletResized");
				float eyeHeight = player.eyeHeight;

				if (player.isSneaking()) {
					size -= 0.5;
					if (eyeHeight > 1.00F) eyeHeight -= 0.92F;
				} else {
					size += 0.5;
					if(eyeHeight < 3.00F) eyeHeight += 1.00F;
				}

				if(size <= 0){size = 0.5F;}
				if(size > 2){size = 2.0F;}

				player.eyeHeight = eyeHeight;
				player.getEntityData().setFloat("MalletResized", size);

				AxisAlignedBB axisAlignedBB = player.getEntityBoundingBox(); //Get Bounding Box
				axisAlignedBB.expandXyz(player.isSneaking() ? -1.00F: 1.00F); //Expand bounding Box
				player.setEntityBoundingBox(axisAlignedBB); //Set Bounding Box (Which never happens)
			}
		}
		player.swingArm(hand);
		player.getCooldownTracker().setCooldown(this, 50);
		player.setActiveHand(hand);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		useMallet(playerIn, hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
		useMallet(playerIn, hand);
		return true;
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
