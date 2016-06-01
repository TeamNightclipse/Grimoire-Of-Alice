/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.block;

import arekkuusu.grimoireOfAlice.lib.LibMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

public class BlockSugar extends BlockGOABase{

	public IIcon Bottom;
	public IIcon Top;
	public IIcon Sugar2;
	public IIcon Sugar3;
	public IIcon Sugar4;
	public IIcon Sugar5;
	
	public BlockSugar(Material material) {
		super(material);
		setHardness(0.2F);
		setStepSound(Block.soundTypeSnow);
		setHarvestLevel("axe", 1);
		setResistance(2.0F);
		setCreativeTab(CreativeTabs.tabDecorations);
	}

	public void registerBlockIcons(IIconRegister icon){
		Bottom = icon.registerIcon(LibMod.MODID + ":Sugar0");
		Top = icon.registerIcon(LibMod.MODID + ":Sugar1");
		Sugar2 = icon.registerIcon(LibMod.MODID + ":Sugar2");
		Sugar3 = icon.registerIcon(LibMod.MODID + ":Sugar3");
		Sugar4 = icon.registerIcon(LibMod.MODID + ":Sugar4");
		Sugar5 = icon.registerIcon(LibMod.MODID + ":Sugar5");
		
	}
	
	public IIcon getIcon(int side, int meta){
		if(side == 0){
			return Bottom;
		}else if(side == 1){
			return Top;
		} else if(side == 2){
			return Sugar2;
		} else if(side == 3){
			return Sugar3;
		} else if(side == 4){
			return Sugar4;
		} else if(side == 5){
			return Sugar5;
		}
		
		return null;
		
	}
}
