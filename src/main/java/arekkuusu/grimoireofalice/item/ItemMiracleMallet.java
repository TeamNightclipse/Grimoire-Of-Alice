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
				AxisAlignedBB axisAlignedBB = playerIn.getEntityBoundingBox(); //Get Bounding Box
				float eyeHeight = playerIn.eyeHeight;
				eyeHeight += playerIn.isSneaking() ? -0.92F: 1.00F;
				if(eyeHeight < 3.00F) eyeHeight += 1.00F;
				axisAlignedBB.expand(0, playerIn.isSneaking() ? -2.00F: 2.00F, 0); //Expand bounding Box
				playerIn.eyeHeight = eyeHeight;
				playerIn.setEntityBoundingBox(axisAlignedBB); //Set Bounding Box (Which never happens)
			} else {
				float size = playerIn.getEntityData().getFloat("MalletResized");
				AxisAlignedBB axisAlignedBB = playerIn.getEntityBoundingBox();
				float eyeHeight = playerIn.getEyeHeight();
				if (playerIn.isSneaking()) {
					size -= 0.5;
					if (eyeHeight > 1.00F) {
						eyeHeight -= 0.92F;
						playerIn.eyeHeight = eyeHeight;
					}
				} else {
					size += 0.5;
					axisAlignedBB.expand(0, size, 0);
					if(eyeHeight < 3.00F) eyeHeight += 1.00F;
					playerIn.eyeHeight = eyeHeight;
				}
				if(size <= 0){size = 0.5F;}
				if(size > 2){size = 2.0F;}
				playerIn.setEntityBoundingBox(axisAlignedBB);
				playerIn.getEntityData().setFloat("MalletResized", size);
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
			if (!target.getEntityData().hasKey("MalletResized")) {
				float mode = playerIn.isSneaking() ? 0.5F : 1.5F;
				target.getEntityData().setFloat("MalletResized", mode);
			} else {
				float size = target.getEntityData().getFloat("MalletResized");
				if (playerIn.isSneaking()) {
					size -= 0.5F;
				} else {
					size += 0.5F;
				}
				if(size <= 0){size = 0.5F;}
				if(size > 2){size = 2.0F;}
				target.getEntityData().setFloat("MalletResized", size);
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
