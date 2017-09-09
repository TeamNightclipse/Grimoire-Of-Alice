/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.block;

import arekkuusu.grimoireofalice.common.block.tile.TileCraftingAltar;
import arekkuusu.grimoireofalice.common.block.tile.TilePillarAltar;
import arekkuusu.grimoireofalice.common.lib.LibBlockName;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

@ObjectHolder(LibMod.MOD_ID)
public class ModBlocks {

	private static final Block PLACE_HOLDER = new Block(Material.AIR);
	//--------------------------------Blocks--------------------------------//
	@ObjectHolder(LibBlockName.COMPACT_STONE)
	public static final Block COMPACT_STONE = PLACE_HOLDER;
	@ObjectHolder(LibBlockName.HOLY_KEY_STONE)
	public static final Block HOLY_KEY_STONE = PLACE_HOLDER;
	@ObjectHolder(LibBlockName.HOLY_STONE)
	public static final Block HOLY_STONE = PLACE_HOLDER;
	@ObjectHolder(LibBlockName.KYOUMARUBOTAN)
	public static final Block KYOUMARUBOTAN = PLACE_HOLDER;
	@ObjectHolder(LibBlockName.ONBASHIRA)
	public static final Block ONBASHIRA = PLACE_HOLDER;
	@ObjectHolder(LibBlockName.PAPER_BLOCK)
	public static final Block PAPER = PLACE_HOLDER;
	@ObjectHolder(LibBlockName.ROPE_BLOCK)
	public static final Block ROPE = PLACE_HOLDER;
	@ObjectHolder(LibBlockName.SHROOM)
	public static final Block SHROOM = PLACE_HOLDER;
	@ObjectHolder(LibBlockName.CRAFTING_ALTAR)
	public static final Block CRAFTING_ALTAR = PLACE_HOLDER;
	@ObjectHolder(LibBlockName.PILLAR_ALTAR)
	public static final Block PILLAR_ALTAR = PLACE_HOLDER;
	@ObjectHolder(LibBlockName.IMPURE_STONE)
	public static final Block IMPURE_STONE = PLACE_HOLDER;
	@ObjectHolder(LibBlockName.HIHIIROKANE_BLOCK)
	public static final Block HIHIIROKANE_BLOCK = PLACE_HOLDER;
	@ObjectHolder(LibBlockName.SUGAR_BLOCK)
	public static final Block SUGAR_BLOCK = PLACE_HOLDER;
	@ObjectHolder(LibBlockName.HYPER_MAGIC)
	public static final Block HYPER_MAGIC = PLACE_HOLDER;
	@ObjectHolder(LibBlockName.DRAGON_STONE)
	public static final Block DRAGON_STONE = PLACE_HOLDER;

	public static void register(IForgeRegistry<Block> registry) {
		Block sugarBlock = new BlockBase(LibBlockName.SUGAR_BLOCK, Material.CLAY).setSound(SoundType.SNOW).setHardness(0.2F).setResistance(5.0F);
		sugarBlock.setHarvestLevel("axe", 1);

		Block hyperConcentratedMagic = new BlockBase(LibBlockName.HYPER_MAGIC, Material.IRON)
				.setSound(SoundType.SNOW)
				.setHardness(0.5F)
				.setResistance(2.0F);
		hyperConcentratedMagic.setHarvestLevel("pickaxe", 3);

		registry.register(new BlockBase(LibBlockName.COMPACT_STONE, Material.ROCK).setHardness(2.0F).setResistance(-1F));
		registry.register(new BlockHolyKeyStone());
		registry.register(new BlockHolyStone());
		registry.register(new BlockKyoumarubotan());
		registry.register(new BlockOnbashira());
		registry.register(new BlockPaper());
		registry.register(new BlockRope());
		registry.register(new BlockShroom());
		registry.register(sugarBlock);
		registry.register(hyperConcentratedMagic);
		registry.register(new BlockCraftingAltar());
		registry.register(new BlockPillarAltar());
		registry.register(new BlockImpureRock());
		registry.register(new BlockHihiirokane());
		registry.register(new BlockDragonStone());
		registerTiles();
	}

	private static void registerTiles() {
		GameRegistry.registerTileEntity(TileCraftingAltar.class, LibMod.MOD_ID + ":crafting_altar");
		GameRegistry.registerTileEntity(TilePillarAltar.class, LibMod.MOD_ID + ":pillar_altar");
	}
}
