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

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if (!playerIn.getFoodStats().needFood() || playerIn.capabilities.isCreativeMode) {
			if (!playerIn.getEntityData().hasKey("MalletResized")) {
				float size = playerIn.isSneaking() ? 0.5F : 1.5F;
				playerIn.getEntityData().setFloat("MalletResized", size);

				float eyeHeight = playerIn.eyeHeight;
				eyeHeight += playerIn.isSneaking() ? -0.92F: 1.00F;
				playerIn.eyeHeight = eyeHeight;

				AxisAlignedBB axisAlignedBB = playerIn.getEntityBoundingBox(); //Get Bounding Box
				axisAlignedBB.expandXyz(playerIn.isSneaking() ? -1.00F: 1.00F); //Expand bounding Box
				playerIn.setEntityBoundingBox(axisAlignedBB); //Set Bounding Box (Which never happens)
			} else {
				float size = playerIn.getEntityData().getFloat("MalletResized");
				float eyeHeight = playerIn.eyeHeight;
				if (playerIn.isSneaking()) {
					size -= 0.5;
					if (eyeHeight > 1.00F) {
						eyeHeight -= 0.92F;
						playerIn.eyeHeight = eyeHeight;
					}
				} else {
					size += 0.5;
					if(eyeHeight < 3.00F) eyeHeight += 1.00F;
					playerIn.eyeHeight = eyeHeight;
				}
				if(size <= 0){size = 0.5F;}
				if(size > 2){size = 2.0F;}
				playerIn.getEntityData().setFloat("MalletResized", size);
				AxisAlignedBB axisAlignedBB = playerIn.getEntityBoundingBox(); //Get Bounding Box
				axisAlignedBB.expandXyz(playerIn.isSneaking() ? -1.00F: 1.00F); //Expand bounding Box
				playerIn.setEntityBoundingBox(axisAlignedBB); //Set Bounding Box (Which never happens)
			}
		}
		playerIn.swingArm(hand);
		playerIn.getCooldownTracker().setCooldown(this, 50);
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
		if (!playerIn.getFoodStats().needFood() || playerIn.capabilities.isCreativeMode) {
			if (target instanceof EntityPlayer) {
				EntityPlayer player = ((EntityPlayer)target);
				if (!player.getEntityData().hasKey("MalletResized")) {
					float size = playerIn.isSneaking() ? 0.5F : 1.5F;
					player.getEntityData().setFloat("MalletResized", size);

					float eyeHeight = player.eyeHeight;
					eyeHeight += playerIn.isSneaking() ? player.isSneaking() ? -0.92F : -1.00F : player.isSneaking() ? 1.08F : 1.00F;
					player.eyeHeight = eyeHeight;

					AxisAlignedBB axisAlignedBB = player.getEntityBoundingBox(); //Get Bounding Box
					axisAlignedBB.expandXyz(playerIn.isSneaking() ? -1.00F: 1.00F); //Expand bounding Box
					player.setEntityBoundingBox(axisAlignedBB); //Set Bounding Box (Which never happens)
				} else {
					float size = player.getEntityData().getFloat("MalletResized");
					float eyeHeight = player.eyeHeight;
					if (playerIn.isSneaking()) {
						size -= 0.5F;
						if (eyeHeight > 1.00F) {
							eyeHeight -= player.isSneaking() ? -0.92F : -1.00F;
							player.eyeHeight = eyeHeight;
						}
					} else {
						size += 0.5F;
						if (eyeHeight < 3.00F) {
							eyeHeight += player.isSneaking() ? 1.08F : 1.00F;
							player.eyeHeight = eyeHeight;
						}
					}
					if (size <= 0) {size = 0.5F;}
					if (size > 2) {size = 2.0F;}
					player.getEntityData().setFloat("MalletResized", size);
					AxisAlignedBB axisAlignedBB = playerIn.getEntityBoundingBox(); //Get Bounding Box
					axisAlignedBB.expandXyz(playerIn.isSneaking() ? -1.00F: 1.00F); //Expand bounding Box
					playerIn.setEntityBoundingBox(axisAlignedBB); //Set Bounding Box (Which never happens)
				}
			}
		}
		playerIn.swingArm(hand);
		playerIn.getCooldownTracker().setCooldown(this, 50);
		playerIn.setActiveHand(hand);
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
