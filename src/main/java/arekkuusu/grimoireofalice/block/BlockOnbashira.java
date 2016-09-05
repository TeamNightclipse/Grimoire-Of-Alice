/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.block;

import java.util.List;
import java.util.Random;

import arekkuusu.grimoireofalice.lib.LibBlockName;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockOnbashira extends BlockMod /*implements ITileEntityProvider*/ {

	BlockOnbashira() {
		super(LibBlockName.ONBASHIRA, Material.WOOD);
		setHardness(2.0F);
		setSoundType(SoundType.STONE);
		setHarvestLevel("axe", 1);
		setResistance(-1F);
	}

	/*@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity collidingEntity) {
		setBlockBounds(0f, 0f, 0f, 1F, 3.5F, 1F);
		super.addCollisionBoxesToList(world, x, y, z, mask, list, collidingEntity);
	}*/
	
	public int onBlockPlaced(World world, int x, int y, int z, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
		world.setBlockState(new BlockPos(x, y + 1, z), ModBlocks.onbashiraMiddle.getDefaultState());
		world.setBlockState(new BlockPos(x, y + 2, z), ModBlocks.onbashiraMiddle.getDefaultState());
		world.setBlockState(new BlockPos(x, y + 3, z), ModBlocks.onbashiraTop.getDefaultState());
		return p_149660_9_;
    }

	/*@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
		return new TileEntityOnbashira();
	}*/

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.createExplosion(null, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 2.0F, false);

		for(int i = 1; i < 4; i++) {
			worldIn.setBlockToAir(pos);
			worldIn.createExplosion(null, pos.getX() + 0.5, pos.getY() + i, pos.getZ() + 0.5, 2.0F, false);
		}
	}

	@Override
	public boolean canSilkHarvest() {
		return false;
	}
}
