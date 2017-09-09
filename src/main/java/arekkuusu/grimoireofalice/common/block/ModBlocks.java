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
	public static final Block compact_stone = PLACE_HOLDER;
	public static final Block holy_key_stone = PLACE_HOLDER;
	public static final Block holy_stone = PLACE_HOLDER;
	public static final Block kyoumarubotan = PLACE_HOLDER;
	public static final Block onbashira = PLACE_HOLDER;
	public static final Block paper_block = PLACE_HOLDER;
	public static final Block rope_block = PLACE_HOLDER;
	public static final Block shroom = PLACE_HOLDER;
	public static final Block crafting_altar = PLACE_HOLDER;
	public static final Block pillar_altar = PLACE_HOLDER;
	public static final Block impure_stone = PLACE_HOLDER;
	public static final Block hihiirokane_block = PLACE_HOLDER;
	public static final Block sugar_block = PLACE_HOLDER;
	public static final Block hyper_magic = PLACE_HOLDER;
	public static final Block dragon_stone = PLACE_HOLDER;

	public static void register(IForgeRegistry<Block> registry) {
		Block sugarBlock = new BlockBase(LibBlockName.SUGAR_BLOCK, Material.CLAY)
				.setSound(SoundType.SNOW)
				.setHardness(0.2F)
				.setResistance(5.0F);
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
