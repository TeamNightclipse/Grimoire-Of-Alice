/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import arekkuusu.grimoireOfAlice.lib.LibMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

public class BlockPaper extends BlockGOABase{

	public IIcon BottomTop;
	public IIcon Sides;
	
	public BlockPaper(Material material) {
		super(material);
		setHardness(0.5F);
		setStepSound(Block.soundTypeCloth);
		setHarvestLevel("shears", 1);
		setResistance(5.0F);
		setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icon){
		BottomTop = icon.registerIcon(LibMod.MODID + ":Paper0");
		Sides = icon.registerIcon(LibMod.MODID + ":Paper1");
		
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta){
		if(side == 0 || side == 1){
			return BottomTop;
		}else if(side == 2 || side == 3 || side == 4 || side == 5){
			return Sides;
		}
		return null;
		
	}

}
