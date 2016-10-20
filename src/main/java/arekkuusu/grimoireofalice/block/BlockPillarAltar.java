package arekkuusu.grimoireofalice.block;

import arekkuusu.grimoireofalice.block.tile.TilePillarAltar;
import arekkuusu.grimoireofalice.lib.LibBlockName;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class BlockPillarAltar extends BlockMod implements ITileEntityProvider {

	public BlockPillarAltar() {
		super(LibBlockName.PILLARALTAR, Material.ROCK);
		setHardness(2.0F);
		setSoundType(SoundType.STONE);
		setHarvestLevel("axe", 1);
		setResistance(2000.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Honored Pillar used in the Crafting Altar");
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		TilePillarAltar tile = (TilePillarAltar) worldIn.getTileEntity(pos);
		boolean ok = false;
		if (tile != null)
			if (playerIn.isSneaking()) {
				ok = tile.removeItem(playerIn);
			} else if (heldItem != null) {
				ok = tile.addItem(playerIn, heldItem);
			}
		return ok;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		for (int i = -2; i <= 2; ++i) {
			for (int j = -2; j <= 2; ++j) {
				if (i > -2 && i < 2 && j == -1) {
					j = 2;
				}

				if (rand.nextInt(16) == 0) {
					for (int k = 0; k <= 1; ++k) {

						if (!worldIn.isAirBlock(pos.add(i / 2, 0, j / 2))) {
							break;
						}

						worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, (double) pos.getX() + 0.5D, (double) pos.getY() + 2.0D, (double) pos.getZ() + 0.5D, (double) ((float) i + rand.nextFloat()) - 0.5D, (double) ((float) k - rand.nextFloat() - 1.0F), (double) ((float) j + rand.nextFloat()) - 0.5D);
					}
				}
			}
		}
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
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos.down()).getBlock() == ModBlocks.COMPACT_STONE;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TilePillarAltar();
	}
}
