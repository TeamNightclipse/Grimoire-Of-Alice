package arekkuusu.grimoireofalice.item;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import arekkuusu.grimoireofalice.lib.LibItemName;

public class ItemViolin extends ItemMod {

	public ItemViolin() {
		super(LibItemName.LUNASAVIOLIN);
		addPropertyOverride(new ResourceLocation("playing"), (stack, world, entity) ->
				entity != null && entity.isHandActive() && entity.getActiveItemStack() == stack ? 1F : 0F);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Poltergeists posses this violin");
		list.add(TextFormatting.ITALIC + "Hold right click to use");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.NONE;
    }
	
	@Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 1800000;
    }
}
