package arekkuusu.grimoireofalice.item;

import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import arekkuusu.grimoireofalice.lib.LibItemName;

public class ItemIchirinRing extends ItemModSword {

	public ItemIchirinRing(ToolMaterial material) {
		super(material, LibItemName.ICHIRINRING);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Nyuudou bender");
		if(GuiScreen.isShiftKeyDown()){
			list.add(TextFormatting.ITALIC + "Use with Rings in both Hands");
			if(!isHoldingItemsBothHands(player)){
				list.add(TextFormatting.DARK_RED + "Inactive");
			}
			else {
				list.add(TextFormatting.DARK_AQUA + "Active");
			}
		} else {
			list.add(TextFormatting.ITALIC + "Shift for details");
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(isHoldingItemsBothHands(playerIn)){
			playerIn.setActiveHand(hand);
			return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
		}
		return new ActionResult<>(EnumActionResult.FAIL, itemStackIn);
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer){
				int i = this.getMaxItemUseDuration(stack) - timeLeft;
		}
	}
		
	private boolean isHoldingItemsBothHands(EntityPlayer player) {
		ItemStack main = player.getHeldItemMainhand();
		ItemStack off = player.getHeldItemOffhand();
		return main != null && off != null && main.getItem() == off.getItem();
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BLOCK;
    }

	@Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }
	
}
