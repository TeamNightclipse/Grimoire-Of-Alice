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
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

public class BlockSugar extends BlockGOABase {

	@SideOnly(Side.CLIENT)
	private IIcon Bottom;
	@SideOnly(Side.CLIENT)
	private IIcon Top;
	@SideOnly(Side.CLIENT)
	private IIcon Sugar2;
	@SideOnly(Side.CLIENT)
	private IIcon Sugar3;
	@SideOnly(Side.CLIENT)
	private IIcon Sugar4;
	@SideOnly(Side.CLIENT)
	private IIcon Sugar5;

	BlockSugar(Material material) {
		super(material);
		setHardness(0.2F);
		setStepSound(Block.soundTypeSnow);
		setHarvestLevel("axe", 1);
		setResistance(2.0F);
		setCreativeTab(CreativeTabs.tabDecorations);
	}

	@Override
	public void registerBlockIcons(IIconRegister icon) {
		Bottom = icon.registerIcon(LibMod.MODID + ":Sugar0");
		Top = icon.registerIcon(LibMod.MODID + ":Sugar1");
		Sugar2 = icon.registerIcon(LibMod.MODID + ":Sugar2");
		Sugar3 = icon.registerIcon(LibMod.MODID + ":Sugar3");
		Sugar4 = icon.registerIcon(LibMod.MODID + ":Sugar4");
		Sugar5 = icon.registerIcon(LibMod.MODID + ":Sugar5");
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		if(side == 0) {
			return Bottom;
		}
		else if(side == 1) {
			return Top;
		}
		else if(side == 2) {
			return Sugar2;
		}
		else if(side == 3) {
			return Sugar3;
		}
		else if(side == 4) {
			return Sugar4;
		}
		else return Sugar5;
	}
}
