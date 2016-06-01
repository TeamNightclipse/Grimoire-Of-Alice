/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

public class BlockHyperconcentratedMagic extends BlockGOABase {

	BlockHyperconcentratedMagic(Material material) {
		super(material);
		setHardness(0.5F);
		setStepSound(Block.soundTypeSnow);
		setHarvestLevel("pickaxe", 3);
		setResistance(5.0F);
		setCreativeTab(CreativeTabs.tabDecorations);
	}
}
