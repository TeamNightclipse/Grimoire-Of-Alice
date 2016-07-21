package arekkuusu.grimoireOfAlice.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import arekkuusu.grimoireOfAlice.GrimoireOfAlice;

public class BlockOnbashiraMiddle extends Block{

	public BlockOnbashiraMiddle() {
		super(Material.rock);
		setCreativeTab(null);
		setResistance(-1F);
		setStepSound(Block.soundTypeWood);
		setHardness(-1F);
	}
	
	public boolean isOpaqueCube() {
	    return false;
    }
	
	@Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon("grimoireofalice:InvisibleBlock");
    }
	
}
