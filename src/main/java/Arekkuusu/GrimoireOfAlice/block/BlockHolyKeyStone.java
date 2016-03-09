/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.block;

import java.util.Random;

import javax.swing.Icon;

import com.sun.xml.internal.stream.Entity;

import arekkuusu.grimoireofalice.lib.libMod;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockHolyKeyStone extends GOABaseB {

	@SideOnly(Side.CLIENT)
	private IIcon txTop;
	@SideOnly(Side.CLIENT)
	private IIcon txFront;
	
	public BlockHolyKeyStone(Material material){
		
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
			double d1 = (double)((float)x + random.nextFloat());
			double d2 = (double)((float)y + random.nextFloat());
			double d3 = (double)((float)z + random.nextFloat());
			if(l == 0 && !w.getBlock(x, y + 1, z).isOpaqueCube()) d2 = (double)(y + 1) + d0;
			if(l == 1 && !w.getBlock(x, y - 1, z).isOpaqueCube()) d2 = (double)(y + 0) - d0;
			if(l == 2 && !w.getBlock(x, y, z + 1).isOpaqueCube()) d3 = (double)(z + 1) + d0;   
			if(l == 3 && !w.getBlock(x, y, z - 1).isOpaqueCube()) d3 = (double)(z + 0) - d0;
			if(l == 4 && !w.getBlock(x + 1, y, z).isOpaqueCube()) d1 = (double)(x + 1) + d0;
			if(l == 5 && !w.getBlock(x - 1, y, z).isOpaqueCube()) d1 = (double)(x + 0) - d0;
			if(d1 < (double)x || d1 > (double)(x + 1) || d2 < 0.0D || d2 > (double)(y + 1) || d3 < (double)z || d3 > (double)(z + 1)) {
				w.spawnParticle("enchantmenttable", d1, d2, d3, 0.0D, 0.0D, 0.0D);
			}
		}
	}
	
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int e){
    	
        world.createExplosion(null, (double)x, (double)y, (double)z, 2.0F, false);
        
    }

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		
		return side == 1 ? Blocks.stone.getBlockTextureFromSide(side): side == 0 ? Blocks.stone.getBlockTextureFromSide(side) : blockIcon;
	
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icon) {
		
		blockIcon = icon.registerIcon(getTextureName() + "_side");
		
	}
}

