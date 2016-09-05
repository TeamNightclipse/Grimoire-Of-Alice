package arekkuusu.grimoireofalice.block;

import arekkuusu.grimoireofalice.lib.LibBlockName;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BlockOnbashiraMiddle extends BlockMod {

	public BlockOnbashiraMiddle() {
		super(LibBlockName.ONBASHIRAMIDDLE, Material.ROCK);
		setCreativeTab(null);
		setResistance(-1F);
		setSoundType(SoundType.WOOD);
		setHardness(-1F);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
}
