/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.block;

import arekkuusu.grimoireofalice.common.lib.LibBlockName;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

//Much taken from BlockStairs
public class BlockRope extends BlockMod {

    @SuppressWarnings("WeakerAccess")
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    @SuppressWarnings("WeakerAccess")
    public static final PropertyEnum<BlockStairs.EnumHalf> HALF = PropertyEnum.create("half", BlockStairs.EnumHalf.class);
    @SuppressWarnings("WeakerAccess")
    public static final PropertyEnum<BlockStairs.EnumShape> SHAPE = PropertyEnum.create("shape", BlockStairs.EnumShape.class);
    private static final AxisAlignedBB BOTTOM_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.6D, 1.0D);
    private static final AxisAlignedBB TOP_AABB = new AxisAlignedBB(0.0D, 0.4D, 0.0D, 1.0D, 1.0D, 1.0D);


    public BlockRope() {
		super(LibBlockName.ROPE_BLOCK, Material.WOOD);
		setSoundType(SoundType.LADDER);
		setHarvestLevel("axe", 1);
		setResistance(5.0F);
		setHardness(0.5F);
	}

	@Override
	protected IBlockState defaultState() {
		return super.defaultState()
				.withProperty(FACING, EnumFacing.NORTH)
				.withProperty(HALF, BlockStairs.EnumHalf.BOTTOM)
				.withProperty(SHAPE, BlockStairs.EnumShape.STRAIGHT);
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		state.neighborChanged(world, pos, Blocks.AIR, pos);
	}

	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
			EntityLivingBase placer) {
		IBlockState iblockstate = super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer);
		iblockstate = iblockstate.withProperty(FACING, placer.getHorizontalFacing()).withProperty(SHAPE, BlockStairs.EnumShape.STRAIGHT);
		return facing != EnumFacing.DOWN && (facing == EnumFacing.UP || hitY <= 0.5F) ?
				iblockstate.withProperty(HALF, BlockStairs.EnumHalf.BOTTOM) :
				iblockstate.withProperty(HALF, BlockStairs.EnumHalf.TOP);
	}

	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState iblockstate = getDefaultState().withProperty(HALF, (meta & 4) > 0 ? BlockStairs.EnumHalf.TOP : BlockStairs.EnumHalf.BOTTOM);
		iblockstate = iblockstate.withProperty(FACING, EnumFacing.getFront(5 - (meta & 3)));
		return iblockstate;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;

		if(state.getValue(HALF) == BlockStairs.EnumHalf.TOP) {
			i |= 4;
		}

		i = i | 5 - state.getValue(FACING).getIndex();
		return i;
	}

	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		return state.withProperty(SHAPE, getRopeShape(state, world, pos));
	}

	@SuppressWarnings("deprecation")
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if (isBlockRope(state)) {
			if (state.getValue(HALF) == BlockStairs.EnumHalf.TOP) {
				return TOP_AABB;
			}
			else if (state.getValue(HALF) == BlockStairs.EnumHalf.BOTTOM) {
				return BOTTOM_AABB;
			}
		}

		return super.getBoundingBox(state, source, pos);
	}

	private static BlockStairs.EnumShape getRopeShape(IBlockState blockState, IBlockAccess blockAccess, BlockPos blockPos) {
		EnumFacing enumfacing = blockState.getValue(FACING);
		IBlockState iblockstate = blockAccess.getBlockState(blockPos.offset(enumfacing));

		if(isBlockRope(iblockstate) && blockState.getValue(HALF) == iblockstate.getValue(HALF)) {
			EnumFacing enumfacing1 = iblockstate.getValue(FACING);

			if(enumfacing1.getAxis() != blockState.getValue(FACING).getAxis()
					&& isDifferentRope(blockState, blockAccess, blockPos, enumfacing1.getOpposite())) {
				if(enumfacing1 == enumfacing.rotateYCCW()) return BlockStairs.EnumShape.OUTER_LEFT;

				return BlockStairs.EnumShape.OUTER_RIGHT;
			}
		}

		IBlockState iblockstate1 = blockAccess.getBlockState(blockPos.offset(enumfacing.getOpposite()));

		if(isBlockRope(iblockstate1) && blockState.getValue(HALF) == iblockstate1.getValue(HALF)) {
			EnumFacing enumfacing2 = iblockstate1.getValue(FACING);

			if(enumfacing2.getAxis() != blockState.getValue(FACING).getAxis() && isDifferentRope(blockState, blockAccess, blockPos, enumfacing2)) {
				if(enumfacing2 == enumfacing.rotateYCCW()) return BlockStairs.EnumShape.INNER_LEFT;

				return BlockStairs.EnumShape.INNER_RIGHT;
			}
		}

		return BlockStairs.EnumShape.STRAIGHT;
	}

	private static boolean isDifferentRope(IBlockState state, IBlockAccess blockAccess, BlockPos pos, EnumFacing facing) {
		IBlockState otherState = blockAccess.getBlockState(pos.offset(facing));
		return !isBlockRope(otherState)
				|| (otherState.getValue(FACING) != state.getValue(FACING))
				|| (otherState.getValue(HALF) != state.getValue(HALF));
	}

	public static boolean isBlockRope(IBlockState state) {
		return state.getBlock() instanceof BlockRope;
	}

	@SuppressWarnings("deprecation")
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}

	@SuppressWarnings("deprecation")
	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		EnumFacing enumfacing = state.getValue(FACING);
		BlockStairs.EnumShape shape = state.getValue(SHAPE);

		switch(mirrorIn) {
			case LEFT_RIGHT:

				if(enumfacing.getAxis() == EnumFacing.Axis.Z) {
					switch(shape) {
						case OUTER_LEFT:
							return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, BlockStairs.EnumShape.OUTER_RIGHT);
						case OUTER_RIGHT:
							return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, BlockStairs.EnumShape.OUTER_LEFT);
						case INNER_RIGHT:
							return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, BlockStairs.EnumShape.INNER_LEFT);
						case INNER_LEFT:
							return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, BlockStairs.EnumShape.INNER_RIGHT);
						default:
							return state.withRotation(Rotation.CLOCKWISE_180);
					}
				}

				break;
			case FRONT_BACK:

				if(enumfacing.getAxis() == EnumFacing.Axis.X) {
					switch(shape) {
						case OUTER_LEFT:
							return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, BlockStairs.EnumShape.OUTER_RIGHT);
						case OUTER_RIGHT:
							return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, BlockStairs.EnumShape.OUTER_LEFT);
						case INNER_RIGHT:
							return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, BlockStairs.EnumShape.INNER_RIGHT);
						case INNER_LEFT:
							return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, BlockStairs.EnumShape.INNER_LEFT);
						case STRAIGHT:
							return state.withRotation(Rotation.CLOCKWISE_180);
					}
				}
		}

		return super.withMirror(state, mirrorIn);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING, HALF, SHAPE);
	}

	@SuppressWarnings("deprecation") //Internal
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@SuppressWarnings("deprecation") //Internal
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
}
