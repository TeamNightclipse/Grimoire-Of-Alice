/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.block;

import arekkuusu.grimoireofalice.client.effect.ParticleUtil;
import arekkuusu.grimoireofalice.common.lib.LibBlockName;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockHihiirokane extends BlockBase {

	public BlockHihiirokane() {
		super(LibBlockName.HIHIIROKANE_BLOCK, Material.IRON);
		setHarvestLevel("pickaxe", 2);
		setSound(SoundType.METAL);
		setResistance(100F);
		setHardness(10F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		float x = pos.getX() + rand.nextFloat();
		float y = pos.getY() + rand.nextFloat();
		float z = pos.getZ() + rand.nextFloat();
		ParticleUtil.spawnRedGas(world, x, y, z, 0F, 0.1F, 0F);
	}
}
