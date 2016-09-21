package arekkuusu.grimoireofalice.item;

import java.util.List;

import javax.annotation.Nullable;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemWallPassingChisel extends ItemMod {

	public ItemWallPassingChisel() {
		super(LibItemName.WALLPASSINGCHISEL);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Wall passing chisel");
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x,
			float y, float z) {
		BlockPos suPos = getPos(world, pos, facing);
		if(suPos != null && world.getBlockState(suPos.up()).getBlock() == Blocks.AIR) { //If BlockPos isn't null and the block above is Air -->
			player.setPosition(suPos.getX() + 0.5, suPos.getY(), suPos.getZ() + 0.5); //Move player to center of block
			stack.damageItem(1, player);
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.PASS;
	}

	/**
	 * @param world The world
	 * @param pos Pos of the block right clicked
	 * @param facing Facing of the block right clicked
	 * @return Position for player
	 */
	@Nullable
	private BlockPos getPos(World world, BlockPos pos, EnumFacing facing) {
		BlockPos triedPos = pos;

		for(int i = 0; i < 50; i++) {
			if(world.getBlockState(triedPos).getBlock() == Blocks.AIR) {
				return triedPos.offset(facing);
			}
			else {
				triedPos = triedPos.offset(facing);
			}
		}

		return null;
	}
}
