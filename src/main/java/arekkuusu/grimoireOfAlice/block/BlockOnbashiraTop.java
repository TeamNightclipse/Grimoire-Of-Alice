package arekkuusu.grimoireOfAlice.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import arekkuusu.grimoireOfAlice.GrimoireOfAlice;

public class BlockOnbashiraTop extends Block{

	public BlockOnbashiraTop() {
		super(Material.rock);
		setCreativeTab(null);
		setResistance(-1F);
		setStepSound(Block.soundTypeWood);
		setHardness(-1F);
	}
	
	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity collidingEntity) {
		setBlockBounds(0f, 0f, 0f, 1F, 0.5F, 1F);
		super.addCollisionBoxesToList(world, x, y, z, mask, list, collidingEntity);
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	public boolean isOpaqueCube() {
	    return false;
    }
	
	@Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon("grimoireofalice:InvisibleBlock");
    }
	
}
