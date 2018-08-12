/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.block;

import arekkuusu.grimoireofalice.common.lib.LibName;
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

import java.util.Random;

public class BlockKyoumarubotan extends BlockModBush {

	public BlockKyoumarubotan() {
		super(LibName.KYOUMARUBOTAN, Material.ROCK);
		setTickRandomly(true);
		setLightLevel(0.5F);
		setHardness(0.5F);
		setSoundType(SoundType.METAL);
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Cave;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		canBlockStay(world, pos, state);
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
	public int quantityDropped(Random rand) {
		return 3 + rand.nextInt(5);
	}

	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return true;
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		IBlockState soil = world.getBlockState(pos.down());
		Block block = soil.getBlock();
		return super.canPlaceBlockAt(world, pos)
				|| block == Blocks.HARDENED_CLAY
				|| block == Blocks.STAINED_HARDENED_CLAY
				|| block == Blocks.STONE
				|| block == ModBlocks.COMPACT_STONE;
	}

}
