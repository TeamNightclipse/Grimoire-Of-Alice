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
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

public class BlockHyperconcentratedMagic extends BlockGOABase{

	public IIcon Sides;
	
	public BlockHyperconcentratedMagic(Material material) {
		super(material);
		setHardness(0.5F);
		setStepSound(Block.soundTypeSnow);
		setHarvestLevel("pickaxe", 3);
		setResistance(5.0F);
		setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	/*@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icon){
		Sides = icon.registerIcon(LibMod.MODID + ":Paper1");
		
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta){
		if(side == 0 || side == 1 || side == 2 || side == 3 || side == 4 || side == 5){
			return Sides;
		}
		return null;
	}*/
	
}
