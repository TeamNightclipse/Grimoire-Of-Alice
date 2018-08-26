/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.block;

import arekkuusu.grimoireofalice.common.lib.LibName;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

@SuppressWarnings("deprecation")
public class BlockOnbashira extends BlockBase {

	public static final PropertyEnum<OnbashiraPiece> PART_LISTED = PropertyEnum.create("onbashira_piece", OnbashiraPiece.class);
	private static final AxisAlignedBB BB_TOP = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);

	public BlockOnbashira() {
		super(LibName.ONBASHIRA, Material.WOOD);
		setHardness(2.0F);
		setSoundType(SoundType.STONE);
		setHarvestLevel(Tool.AXE, ToolLevel.STONE);
		setResistance(2000.0F);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return getActualState(state, source, pos).getValue(PART_LISTED) == OnbashiraPiece.TOP ? BB_TOP : FULL_BLOCK_AABB;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState();
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		boolean up = isOnbashira(world, pos, EnumFacing.UP);
		boolean down = isOnbashira(world, pos, EnumFacing.DOWN);
		if(up && down) {
			state = state.withProperty(PART_LISTED, OnbashiraPiece.MIDDLE);
		} else if(up) {
			state = state.withProperty(PART_LISTED, OnbashiraPiece.LOWER);
		} else if(down) {
			state = state.withProperty(PART_LISTED, OnbashiraPiece.TOP);
		} else {
			state = state.withProperty(PART_LISTED, OnbashiraPiece.NONE);
		}
		return state;
	}

	private boolean isOnbashira(IBlockAccess world, BlockPos pos, EnumFacing facing) {
		return world.getBlockState(pos.offset(facing)).getBlock() == ModBlocks.ONBASHIRA;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer.Builder(this).add(PART_LISTED).build();
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public enum OnbashiraPiece implements IStringSerializable {
		LOWER("lower", 0),
		MIDDLE("middle", 1),
		TOP("top", 2),
		NONE("none", 3);

		private final String name;
		private final int index;

		OnbashiraPiece(String name, int index) {
			this.name = name;
			this.index = index;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public String toString() {
			return name;
		}

		public int getIndex() {
			return index;
		}

		public static OnbashiraPiece fromIndex(int index) {
			switch(index) {
				case 2:
					return TOP;
				case 1:
					return MIDDLE;
				default:
					return LOWER;
			}
		}
	}
}
