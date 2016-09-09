/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item;

import java.util.List;

import arekkuusu.grimoireofalice.helper.LogHelper;
import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTimeOrb extends ItemMod{

	ItemTimeOrb() {
		super(LibItemName.TIMEORB);
		setMaxStackSize(64);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Exclusive to Imperishable Night");
		list.add(TextFormatting.ITALIC + "They are believed to slow");
		list.add(TextFormatting.ITALIC + "the passing of the night");
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
    }
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer){
			if(!worldIn.isDaytime()) {
				EntityPlayer player = (EntityPlayer)entityLiving;
				if (!player.capabilities.isCreativeMode) {
					ItemStack itemstack2 = stack.copy();
					if (--itemstack2.stackSize == 0) {
						itemstack2 = null;
					}
					player.inventory.mainInventory[player.inventory.currentItem] = itemstack2;
				}

				LogHelper.info("Test");
				//Daytime test only works on the server
				worldIn.playSound(null, new BlockPos(player.posX, player.posY, player.posZ), SoundEvents.ENTITY_FIREWORK_TWINKLE,
						SoundCategory.HOSTILE, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
				if(!worldIn.isRemote) {
					long moonTime = worldIn.getWorldTime() - 500;
					worldIn.setWorldTime(moonTime);
				}
			}
		}
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack p_77661_1_) {
        return EnumAction.BLOCK;
    }
	
	@Override
	public int getMaxItemUseDuration(ItemStack p_77626_1_) {
        return 42;
    }
	
}
