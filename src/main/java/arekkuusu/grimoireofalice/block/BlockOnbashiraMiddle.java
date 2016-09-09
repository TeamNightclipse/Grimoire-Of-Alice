package arekkuusu.grimoireofalice.block;

import arekkuusu.grimoireofalice.lib.LibBlockName;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockOnbashiraMiddle extends BlockMod {

	@SuppressWarnings("ConstantConditions")
	BlockOnbashiraMiddle() {
		super(LibBlockName.ONBASHIRAMIDDLE, Material.AIR);
		setCreativeTab(null);
		setResistance(-1F);
		setSoundType(SoundType.WOOD);
		setHardness(-1F);
	}
	
	@SuppressWarnings("deprecation") //Internal
	public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }
	
	@SuppressWarnings("deprecation") //Internal
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
}
