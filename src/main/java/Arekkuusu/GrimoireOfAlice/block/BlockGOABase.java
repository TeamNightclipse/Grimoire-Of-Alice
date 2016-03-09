package arekkuusu.grimoireOfAlice.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockGOABase extends Block {

	public BlockGOABase(Material material) {
		super(material);
		setCreativeTab(CreativeTabs.tabDecorations);
	}
}
