package arekkuusu.grimoireofalice.block;

import java.util.Random;

import arekkuusu.grimoireofalice.lib.LibBlockName;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public class BlockKyoumarubotan extends BlockModBush {
	
	BlockKyoumarubotan() {
		super(LibBlockName.KYOUMARUBOTAN, Material.ROCK);
		setTickRandomly(true);
		setLightLevel(0.5F);
		setHardness(0.5F);
		setSoundType(SoundType.METAL);
		//float f = 0.4F;
		//setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Cave;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		canBlockStay(worldIn, pos, state);
	}

	@Override
	public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
		return canPlaceBlockAt(world, pos);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items.GOLD_NUGGET;
	}
	
	@Override
	public int quantityDropped(Random p_149745_1_) {
        return  4 + p_149745_1_.nextInt(5);
    }

	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return true;
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		IBlockState soil = worldIn.getBlockState(pos.down());
		Block block = soil.getBlock();
        return super.canPlaceBlockAt(worldIn, pos)
        		|| block == Blocks.HARDENED_CLAY 
        		|| block == Blocks.STAINED_HARDENED_CLAY 
        		|| block == Blocks.STONE 
        		|| block == ModBlocks.compactStone;
	}

}
