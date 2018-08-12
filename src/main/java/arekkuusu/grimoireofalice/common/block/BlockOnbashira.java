/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.block;

import arekkuusu.grimoireofalice.common.block.tile.TilePillarAltar;
import arekkuusu.grimoireofalice.common.lib.LibName;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockOnbashira extends BlockBase implements ITileEntityProvider {

	public static final PropertyEnum<Part> PART = PropertyEnum.create("part", Part.class);
	private static final AxisAlignedBB BB_TOP = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);

	public BlockOnbashira() {
		super(LibName.ONBASHIRA, Material.WOOD);
		setHardness(2.0F);
		setSoundType(SoundType.STONE);
		setHarvestLevel(Tool.AXE, ToolLevel.STONE);
		setResistance(2000.0F);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, PART);
	}

	@Override
	protected IBlockState defaultState() {
		return super.defaultState().withProperty(PART, Part.LOWER);
	}

	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(PART, Part.fromIndex(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(PART).getIndex();
	}

	@SuppressWarnings("deprecation")
	@Override
	public float getBlockHardness(IBlockState state, World world, BlockPos pos) {
		if (state.getValue(PART) == Part.MIDDLE) {
			return 2000F;
		} else {
			return super.getBlockHardness(state, world, pos);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if (state.getValue(PART) == Part.TOP) {
			return BB_TOP;
		} else {
			return super.getBoundingBox(state, source, pos);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public Material getMaterial(IBlockState state) {
		if (state.getValue(PART) == Part.MIDDLE) {
			return Material.AIR;
		} else {
			return super.getMaterial(state);
		}
	}

	@Override
	public void onEntityWalk(World world, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityIn;
			player.addPotionEffect(new PotionEffect(MobEffects.LUCK, 125, 5));
		}
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return state.getValue(PART) == Part.TOP;
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		if (Part.fromIndex(meta) == Part.TOP) {
			return new TilePillarAltar().setRenderHeight(1F);
		} else {
			return null;
		}
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
									EnumFacing side, float hitX, float hitY, float hitZ) {
		if (state.getValue(PART) == Part.TOP) {
			TilePillarAltar tile = (TilePillarAltar) world.getTileEntity(pos);
			if (tile == null) return false;
			tile.handleItemTransfer(player, hand);
			return true;
		} else {
			return super.onBlockActivated(world, pos, state, player, hand, side, hitX, hitY, hitZ);
		}
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		for (int i = 1; i < 4; i++) {
			pos = pos.up();
			Block block = world.getBlockState(pos).getBlock();
			if (!block.isReplaceable(world, pos)) {
				return false;
			}
		}
		return world.getBlockState(pos).getBlock().isReplaceable(world, pos);
	}

	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
											EntityLivingBase placer) {
		world.setBlockState(pos.up(1), getDefaultState().withProperty(PART, Part.MIDDLE));
		world.setBlockState(pos.up(2), getDefaultState().withProperty(PART, Part.MIDDLE));
		world.setBlockState(pos.up(3), getDefaultState().withProperty(PART, Part.TOP));
		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer);
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		if (hasTileEntity(state)) {
			TilePillarAltar tile = (TilePillarAltar) world.getTileEntity(pos);

			if (tile != null && !world.isRemote) {
				ItemStack output = tile.getItemStack();
				if (!output.isEmpty()) {
					EntityItem item = new EntityItem(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, output);
					item.setPickupDelay(40);

					world.spawnEntity(item);
				}
			}
			world.removeTileEntity(pos);
		}
	}

	@Override
	public void onPlayerDestroy(World world, BlockPos pos, IBlockState state) {
		switch (state.getValue(PART)) {
			case LOWER:
				for (int i = 0; i < 4; i++) {
					world.setBlockToAir(pos.up(i));
				}
				break;
			case TOP:
				for (int i = 0; i < 4; i++) {
					world.setBlockToAir(pos.down(i));
				}
				break;
			default:
				break;
		}
	}

	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return false;
	}

	@Override
	public boolean isReplaceable(IBlockAccess world, BlockPos pos) {
		return false;
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

	public enum Part implements IStringSerializable {
		LOWER("lower", 0),
		MIDDLE("middle", 1),
		TOP("top", 2);

		private final String name;
		private final int index;

		Part(String name, int index) {
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

		public static Part fromIndex(int index) {
			switch (index) {
				case 2:
					return TOP;
				case 1:
					return MIDDLE;
				default:
					return LOWER;
			}
		}
	}

	public enum Model implements IStringSerializable {
		NEW("new"),
		OLD("old");

		private final String name;

		Model(String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return name;
		}

		public static Model fromModel(boolean model) {
			return model ? NEW : OLD;
		}
	}
}
