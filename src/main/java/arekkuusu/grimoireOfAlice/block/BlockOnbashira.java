/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.block;

import java.util.List;
import java.util.Random;

import arekkuusu.grimoireOfAlice.tile.TileEntityOnbashira;
import arekkuusu.grimoireOfAlice.tmp.CleanupDone;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

@CleanupDone
public class BlockOnbashira extends BlockGOABase implements ITileEntityProvider {

	BlockOnbashira() {
		super(Material.wood);
		setHardness(2.0F);
		setStepSound(Block.soundTypeWood);
		setHarvestLevel("axe", 1);
		setResistance(-1F);
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity collidingEntity) {
		setBlockBounds(0f, 0f, 0f, 1F, 3.5F, 1F);
		super.addCollisionBoxesToList(world, x, y, z, mask, list, collidingEntity);
	}
	
	public int onBlockPlaced(World world, int x, int y, int z, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
		world.setBlock(x, y + 1, z, GOABlock.onbashiraMiddle);
		world.setBlock(x, y + 2, z, GOABlock.onbashiraMiddle);
		world.setBlock(x, y + 3, z, GOABlock.onbashiraTop);
		return p_149660_9_;
    }

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
		return new TileEntityOnbashira();
	}

	@Override
	public Item getItemDropped(int metadata, Random random, int fortune) {
		return null;
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int e) {
		world.createExplosion(null, x + 0.5, y, z + 0.5, 2.0F, false);

		for(int i = 1; i < 4; i++) {
			world.setBlockToAir(x, y + i, z);
			world.createExplosion(null, x + 0.5, y + i, z + 0.5, 2.0F, false);
		}
	}

	@Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		return side == 1 || side == 0 ? Blocks.stone.getBlockTextureFromSide(side) : blockIcon;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icon) {
		blockIcon = icon.registerIcon(getTextureName() + "_side");
	}
}
