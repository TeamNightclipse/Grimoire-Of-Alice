/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

//Much taken from BlockPillar
public class BlockModPillar extends BlockMod {

	public static final PropertyEnum<EnumFacing.Axis> AXIS = PropertyEnum.create("axis", EnumFacing.Axis.class);

	public BlockModPillar(String id, Material material) {
		super(id, material);
	}

	@Override
	public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis) {
		IBlockState state = world.getBlockState(pos);
		for(IProperty<?> prop : state.getProperties().keySet()) {
			if(prop.getName().equals("axis")) {
				world.setBlockState(pos, state.cycleProperty(prop));
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable,
	 * returns the passed blockstate.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		switch(rot) {
			case COUNTERCLOCKWISE_90:
			case CLOCKWISE_90:
				switch(state.getValue(AXIS)) {
					case X:
						return state.withProperty(AXIS, EnumFacing.Axis.Z);
					case Z:
						return state.withProperty(AXIS, EnumFacing.Axis.X);
					default:
						return state;
				}
			default:
				return state;
		}
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing.Axis facing = EnumFacing.Axis.Y;
		int i = meta & 12;

		if(i == 4) {
			facing = EnumFacing.Axis.X;
		}
		else if(i == 8) {
			facing = EnumFacing.Axis.Z;
		}

		return getDefaultState().withProperty(AXIS, facing);
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		EnumFacing.Axis facing = state.getValue(AXIS);

		if(facing == EnumFacing.Axis.X) {
			i |= 4;
		}
		else if(facing == EnumFacing.Axis.Z) {
			i |= 8;
		}

		return i;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, AXIS);
	}

	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		Item itemBlock = Item.getItemFromBlock(this);
		return itemBlock == Items.AIR ? ItemStack.EMPTY : new ItemStack(itemBlock);
	}

	/**
	 * Called by ItemBlocks just before a block is actually set in the world, to allow for
	 * adjustments to the IBlockstate
	 */
	@SuppressWarnings("deprecation") //Internal, not deprecated
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
											EntityLivingBase placer) {
		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(AXIS, facing.getAxis());
	}
}
