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

	BlockShroom(Material material) {
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
	public void updateTick(World world, int x, int y, int z, Random random) {
		canBlockStay(world, x, y, z);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return canPlaceBlockOn(world.getBlock(x, y - 1, z));
	}

	@Override
	public boolean canPlaceBlockOn(Block block) {
		return block == Blocks.hardened_clay || block == Blocks.stained_hardened_clay || block == Blocks.stone
				|| block == Blocks.grass || block == Blocks.dirt || block == GOABlock.blockCompactStone;
	}
}
