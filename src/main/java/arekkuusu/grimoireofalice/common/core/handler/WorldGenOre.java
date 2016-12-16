package arekkuusu.grimoireofalice.common.core.handler;

		import arekkuusu.grimoireofalice.common.block.ModBlocks;
		import net.minecraft.block.state.pattern.BlockMatcher;
		import net.minecraft.init.Blocks;
		import net.minecraft.util.math.BlockPos;
		import net.minecraft.world.World;
		import net.minecraft.world.chunk.IChunkGenerator;
		import net.minecraft.world.chunk.IChunkProvider;
		import net.minecraft.world.gen.feature.WorldGenMinable;
		import net.minecraftforge.fml.common.IWorldGenerator;

		import java.util.Random;

public class WorldGenOre implements IWorldGenerator {

	@SuppressWarnings("unchecked")
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.getDimension() == 0) {
			int veinSize = 1 + ConfigHandler.grimoireOfAlice.worldGen.impureStoneModQuantity <= 0 ? 0 : random.nextInt(ConfigHandler.grimoireOfAlice.worldGen.impureStoneModQuantity);
			int rarity = ConfigHandler.grimoireOfAlice.worldGen.impureStoneRarity <= 0 ? 0 : ConfigHandler.grimoireOfAlice.worldGen.impureStoneRarity;
			int heightRange = 30;
			for (int i = 0; i < ConfigHandler.grimoireOfAlice.worldGen.impureStoneSpawnRate; i++) {
				if (rarity == 0 || 0 == random.nextInt(rarity)) {
					WorldGenMinable gen = new WorldGenMinable(ModBlocks.IMPURE_STONE.getDefaultState(), veinSize, BlockMatcher.forBlock(Blocks.STONE));
					int xRand = chunkX * 16 + random.nextInt(16);
					int yRand = random.nextInt(heightRange);
					int zRand = chunkZ * 16 + random.nextInt(16);
					gen.generate(world, random, new BlockPos(xRand, yRand, zRand));
					break;
				}
			}
		}
	}
}
