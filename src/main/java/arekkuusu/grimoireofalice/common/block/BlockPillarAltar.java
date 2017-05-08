/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.block;

import java.util.List;

import javax.annotation.Nullable;

import arekkuusu.grimoireofalice.api.tile.ITileItemHolder;
import arekkuusu.grimoireofalice.common.block.tile.TilePillarAltar;
import arekkuusu.grimoireofalice.common.core.handler.ConfigHandler;
import arekkuusu.grimoireofalice.common.lib.LibBlockName;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPillarAltar extends BlockMod implements ITileEntityProvider {

	public static final PropertyDirection PROPERTYFACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyEnum<Model> MODEL = PropertyEnum.create("model", Model.class);

	public BlockPillarAltar() {
		super(LibBlockName.PILLAR_ALTAR, Material.ROCK);
		setHardness(2.0F);
		setSoundType(SoundType.STONE);
		setHarvestLevel("axe", 1);
		setResistance(2000.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.pillar_altar_header.name"));
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand,
			@Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		TilePillarAltar tile = (TilePillarAltar)worldIn.getTileEntity(pos);
		boolean ok = false;
		if(tile != null) {
			if(playerIn.isSneaking()) {
				ok = tile.removeItem(playerIn);
			}
			else if(heldItem != null && heldItem.stackSize > 0) {
				ok = tile.addItem(playerIn, heldItem);
			}
		}
		return ok;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		ITileItemHolder tile = (ITileItemHolder) worldIn.getTileEntity(pos);
		if (tile != null) tile.destroy();
		worldIn.removeTileEntity(pos);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, PROPERTYFACING, MODEL);
	}

	@Override
	protected IBlockState defaultState() {
		return super.defaultState().withProperty(MODEL, Model.fromModel(ConfigHandler.grimoireOfAlice.features.vanillaBlockModels));
	}

	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing facing = EnumFacing.getHorizontal(meta);
		return getDefaultState().withProperty(PROPERTYFACING, facing).withProperty(MODEL, Model.fromModel(ConfigHandler.grimoireOfAlice.features.vanillaBlockModels));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumFacing facing = state.getValue(PROPERTYFACING);
		return facing.getHorizontalIndex();
	}

	@SuppressWarnings("deprecation")
	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
									 EntityLivingBase placer) {
		EnumFacing enumfacing = EnumFacing.fromAngle(placer.rotationYaw);
		return this.getDefaultState().withProperty(PROPERTYFACING, enumfacing);
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

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TilePillarAltar().setRenderHeight(1.4F);
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
