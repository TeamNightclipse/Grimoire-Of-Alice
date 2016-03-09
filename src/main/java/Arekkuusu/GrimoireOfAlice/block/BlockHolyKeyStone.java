/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockHolyKeyStone extends BlockGOABase {

	@SideOnly(Side.CLIENT)
	private IIcon txTop;
	@SideOnly(Side.CLIENT)
	private IIcon txFront;

	public BlockHolyKeyStone(Material material) {

		super(material);
		setHardness(2.0F);
		setStepSound(Block.soundTypeStone);
		setHarvestLevel("pickaxe", 1);
		setResistance(15.0F);

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World w, int x, int y, int z, Random rand) {
		renderParticle(w, x, y, z);
	}

	@SideOnly(Side.CLIENT)
	private void renderParticle(World w, int x, int y, int z) {
		Random random = w.rand;
		double d0 = 0.0625D;
		for(int l = 0; l < 6; ++l) {
			double d1 = x + random.nextFloat();
			double d2 = y + random.nextFloat();
			double d3 = z + random.nextFloat();
			if(l == 0 && !w.getBlock(x, y + 1, z).isOpaqueCube()) d2 = y + 1 + d0;
			if(l == 1 && !w.getBlock(x, y - 1, z).isOpaqueCube()) d2 = y + 0 - d0;
			if(l == 2 && !w.getBlock(x, y, z + 1).isOpaqueCube()) d3 = z + 1 + d0;
			if(l == 3 && !w.getBlock(x, y, z - 1).isOpaqueCube()) d3 = z + 0 - d0;
			if(l == 4 && !w.getBlock(x + 1, y, z).isOpaqueCube()) d1 = x + 1 + d0;
			if(l == 5 && !w.getBlock(x - 1, y, z).isOpaqueCube()) d1 = x + 0 - d0;
			if(d1 < x || d1 > x + 1 || d2 < 0.0D || d2 > y + 1 || d3 < z || d3 > z + 1) {
				w.spawnParticle("enchantmenttable", d1, d2, d3, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int e) {

		world.createExplosion(null, x, y, z, 2.0F, false);

	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {

		return side == 1 ? Blocks.stone.getBlockTextureFromSide(side) : side == 0 ? Blocks.stone.getBlockTextureFromSide(side) : blockIcon;

	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icon) {

		blockIcon = icon.registerIcon(getTextureName() + "_side");

	}
}

