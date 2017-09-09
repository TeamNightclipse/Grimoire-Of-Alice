/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.block;

import arekkuusu.grimoireofalice.common.item.ModItems;
import arekkuusu.grimoireofalice.common.lib.LibBlockName;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.Random;

public class BlockImpureRock extends BlockBase {

	public BlockImpureRock() {
		super(LibBlockName.IMPURE_STONE, Material.ROCK);
		setHarvestLevel("pickaxe", 2);
		setResistance(10);
		setHardness(5F);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ModItems.IMPURE_ROCK;
	}

	@Override
	public int quantityDroppedWithBonus(int fortune, Random random) {
		if(fortune > 0) {
			int i = random.nextInt(fortune + 2) - 1;

			if(i < 0) {
				i = 0;
			}

			return this.quantityDropped(random) * (i + 1);
		}
		else {
			return this.quantityDropped(random);
		}
	}

	@Override
	public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
		return 3 + fortune;
	}
}
