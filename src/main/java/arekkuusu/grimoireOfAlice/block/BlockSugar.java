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
import arekkuusu.grimoireOfAlice.tmp.CleanupDone;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

@CleanupDone
public class BlockSugar extends BlockGOABase {

	@SideOnly(Side.CLIENT)
	private IIcon bottom;
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon sugar2;
	@SideOnly(Side.CLIENT)
	private IIcon sugar3;
	@SideOnly(Side.CLIENT)
	private IIcon sugar4;
	@SideOnly(Side.CLIENT)
	private IIcon sugar5;

	BlockSugar() {
		super(Material.clay);
		setHardness(0.2F);
		setStepSound(Block.soundTypeSnow);
		setHarvestLevel("axe", 1);
		setResistance(2.0F);
		setCreativeTab(CreativeTabs.tabDecorations);
	}

	@Override
	public void registerBlockIcons(IIconRegister icon) {
		bottom = icon.registerIcon(LibMod.MODID + ":Sugar0");
		top = icon.registerIcon(LibMod.MODID + ":Sugar1");
		sugar2 = icon.registerIcon(LibMod.MODID + ":Sugar2");
		sugar3 = icon.registerIcon(LibMod.MODID + ":Sugar3");
		sugar4 = icon.registerIcon(LibMod.MODID + ":Sugar4");
		sugar5 = icon.registerIcon(LibMod.MODID + ":Sugar5");
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		if(side == 0) {
			return bottom;
		}
		else if(side == 1) {
			return top;
		}
		else if(side == 2) {
			return sugar2;
		}
		else if(side == 3) {
			return sugar3;
		}
		else if(side == 4) {
			return sugar4;
		}
		else return sugar5;
	}
}
