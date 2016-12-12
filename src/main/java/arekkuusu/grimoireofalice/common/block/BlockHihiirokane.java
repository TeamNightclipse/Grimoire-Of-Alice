package arekkuusu.grimoireofalice.common.block;

import arekkuusu.grimoireofalice.client.fx.ParticleFX;
import arekkuusu.grimoireofalice.common.GrimoireOfAlice;
import arekkuusu.grimoireofalice.common.lib.LibBlockName;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockHihiirokane extends BlockMod {

	public BlockHihiirokane() {
		super(LibBlockName.HIHIIROKANE_BLOCK, Material.ROCK);
		setSound(SoundType.METAL);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		float x = pos.getX() + rand.nextFloat();
		float y = pos.getY() + rand.nextFloat();
		float z = pos.getZ() + rand.nextFloat();
		GrimoireOfAlice.proxy.sparkleFX(ParticleFX.RED_GAS, null, x, y, z, 0.0F, 0.1F, 0.0F);
	}
}
