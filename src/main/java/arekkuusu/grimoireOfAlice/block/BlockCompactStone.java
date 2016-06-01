/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.block;

import arekkuusu.grimoireOfAlice.lib.LibMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockCompactStone extends BlockGOABase {

	@SideOnly(Side.CLIENT)
	private IIcon BottomTop;
	@SideOnly(Side.CLIENT)
	private IIcon Sides;

	BlockCompactStone(Material material) {
		super(material);
		setHardness(3.0F);
		setStepSound(Block.soundTypeStone);
		setHarvestLevel("pickaxe", 2);
		setResistance(20.0F);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icon) {
		BottomTop = icon.registerIcon(LibMod.MODID + ":CompactStone0");
		Sides = icon.registerIcon(LibMod.MODID + ":CompactStone1");

	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		if(side == 0 || side == 1) {
			return BottomTop;
		}
		else if(side == 2 || side == 3 || side == 4 || side == 5) { return Sides; }
		return null;
	}
}
