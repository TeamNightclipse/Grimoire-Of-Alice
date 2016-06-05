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
import net.minecraft.util.IIcon;

@CleanupDone
public class BlockPaper extends BlockGOABase {

	@SideOnly(Side.CLIENT)
	private IIcon bottomTop;
	@SideOnly(Side.CLIENT)
	private IIcon sides;

	BlockPaper() {
		super(Material.carpet);
		setHardness(0.5F);
		setStepSound(Block.soundTypeCloth);
		setHarvestLevel("shears", 1);
		setResistance(5.0F);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icon) {
		bottomTop = icon.registerIcon(LibMod.MODID + ":Paper0");
		sides = icon.registerIcon(LibMod.MODID + ":Paper1");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		if(side == 0 || side == 1) {
			return bottomTop;
		}
		else return sides;
	}
}
