package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.entity.EntityStopWatch;
import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemStopWatch extends ItemMod {

	ItemStopWatch() {
		super(LibItemName.STOPWATCH);
		setMaxStackSize(1);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Sakuya stop watch");
		list.add(TextFormatting.ITALIC + "Right click to activate");
		list.add(TextFormatting.ITALIC + "Shift + left click to dismiss");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(!worldIn.isRemote) {
			EntityStopWatch watch = new EntityStopWatch(worldIn, playerIn);
			Vec3d look = playerIn.getLookVec();
			float distance = 1F;
			double dx = playerIn.posX + (look.xCoord * distance);
			double dy = playerIn.posY + playerIn.getEyeHeight() - 0.5;
			double dz = playerIn.posZ + (look.zCoord * distance);
			watch.setPosition(dx, dy, dz);
			worldIn.spawnEntityInWorld(watch);
		}
		--itemStackIn.stackSize;
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}
}
