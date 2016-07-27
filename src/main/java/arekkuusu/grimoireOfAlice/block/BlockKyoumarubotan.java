package arekkuusu.grimoireOfAlice.block;

import static net.minecraftforge.common.EnumPlantType.Cave;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public class BlockKyoumarubotan extends BlockBush {
	
	BlockKyoumarubotan() {
		super(Material.rock);
		setTickRandomly(true);
		setLightLevel(0.5F);
		setHardness(0.5F);
		setStepSound(soundTypeMetal);
		float f = 0.4F;
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		return Cave;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		canBlockStay(world, x, y, z);
	}
	
	@Override
	public Item getItemDropped(int metadata, Random random, int fortune) {
		return Items.gold_nugget;
	}
	
	@Override
	public int quantityDropped(Random p_149745_1_) {
        return  4 + p_149745_1_.nextInt(5);
    }
	
	@Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {
		return true;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return canPlaceBlockOn(world.getBlock(x, y - 1, z));
	}

	@Override
	public boolean canPlaceBlockOn(Block block) {
		return block == Blocks.hardened_clay || block == Blocks.stained_hardened_clay || block == Blocks.stone
				|| block == Blocks.grass || block == Blocks.dirt || block == GOABlock.compactStone;
	}

}
