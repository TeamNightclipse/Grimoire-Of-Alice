package arekkuusu.grimoireofalice.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import arekkuusu.grimoireofalice.lib.LibBlockName;

public class BlockOnbashiraTop extends BlockMod{

	protected static final AxisAlignedBB BOTTOM = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
	
	public BlockOnbashiraTop() {
		super(LibBlockName.ONBASHIRATOP, Material.ROCK);
		setCreativeTab(null);
		setResistance(-1F);
		setSoundType(SoundType.WOOD);
		setHardness(-1F);
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
		return BOTTOM;
	}
	
	/*@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity collidingEntity) {
		setBlockBounds(0f, 0f, 0f, 1F, 0.5F, 1F);
		super.addCollisionBoxesToList(world, x, y, z, mask, list, collidingEntity);
	}*/
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
}
