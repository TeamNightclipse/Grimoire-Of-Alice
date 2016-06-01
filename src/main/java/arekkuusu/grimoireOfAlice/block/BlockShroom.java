/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.block;

import static net.minecraftforge.common.EnumPlantType.Cave;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;


public class BlockShroom extends BlockBush {

	public BlockShroom(Material material) {
		super(material);
		setTickRandomly(true);
		setLightLevel(0.5F);
		setHardness(0.0F);
		setStepSound(soundTypeGrass);
		float f = 0.4F;
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		return Cave;
	}

	@Override
	public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_) {
		canBlockStay(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_);
	}

	@Override
	public boolean canBlockStay(World p_149718_1_, int p_149718_2_, int p_149718_3_, int p_149718_4_) {
		return canPlaceBlockOn(p_149718_1_.getBlock(p_149718_2_, p_149718_3_ - 1, p_149718_4_));
	}

	@Override
	public boolean canPlaceBlockOn(Block p_149854_1_) {
		return p_149854_1_ == Blocks.hardened_clay || p_149854_1_ == Blocks.stained_hardened_clay || p_149854_1_ == Blocks.stone
				|| p_149854_1_ == Blocks.grass || p_149854_1_ == Blocks.dirt || p_149854_1_ == GOABlock.blockCompactStone;
	}
}
