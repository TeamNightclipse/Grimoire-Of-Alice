package arekkuusu.grimoireofalice.common.item;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemBlockOnbashira extends ItemBlock {

	@SuppressWarnings("ConstantConditions")
	public ItemBlockOnbashira(Block block) {
		super(block);
		this.setRegistryName(block.getRegistryName());
	}

	@SuppressWarnings("deprecation")
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		if(facing != EnumFacing.UP) {
			return EnumActionResult.FAIL;
		}
		else {
			IBlockState iblockstate = world.getBlockState(pos);
			Block block = iblockstate.getBlock();

			if(!block.isReplaceable(world, pos)) {
				pos = pos.offset(facing);
			}

			if(!stack.isEmpty() && player.canPlayerEdit(pos, facing, stack)
					&& world.mayPlace(this.block, pos, false, facing, null) && this.block.canPlaceBlockAt(world, pos)) {
				int i = this.getMetadata(stack.getMetadata());
				IBlockState iblockstate1 = this.block.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, i, player);

				if(placeBlockAt(stack, player, world, pos, facing, hitX, hitY, hitZ, iblockstate1)) {
					SoundType soundtype = world.getBlockState(pos).getBlock().getSoundType(world.getBlockState(pos), world, pos, player);
					world.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
					stack.shrink(1);
				}

				return EnumActionResult.SUCCESS;
			}
			else {
				return EnumActionResult.FAIL;
			}
		}
	}
}