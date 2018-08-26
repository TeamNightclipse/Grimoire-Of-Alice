/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.block;

import arekkuusu.grimoireofalice.common.Alice;
import arekkuusu.grimoireofalice.common.block.tile.TileCraftingAltar;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import arekkuusu.grimoireofalice.common.lib.LibName;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

@ObjectHolder(LibMod.MOD_ID)
public class ModBlocks {

	private static final Block PLACE_HOLDER = new Block(Material.AIR);
	//--------------------------------Blocks--------------------------------//
	@ObjectHolder(LibName.COMPACT_STONE)
	public static final Block COMPACT_STONE = PLACE_HOLDER;
	@ObjectHolder(LibName.KYOUMARUBOTAN)
	public static final Block KYOUMARUBOTAN = PLACE_HOLDER;
	@ObjectHolder(LibName.ONBASHIRA)
	public static final Block ONBASHIRA = PLACE_HOLDER;
	@ObjectHolder(LibName.PAPER_BLOCK)
	public static final Block PAPER = PLACE_HOLDER;
	@ObjectHolder(LibName.ROPE_BLOCK)
	public static final Block ROPE = PLACE_HOLDER;
	@ObjectHolder(LibName.SHROOM)
	public static final Block SHROOM = PLACE_HOLDER;
	@ObjectHolder(LibName.CRAFTING_ALTAR)
	public static final Block CRAFTING_ALTAR = PLACE_HOLDER;
	@ObjectHolder(LibName.IMPURE_STONE)
	public static final Block IMPURE_STONE = PLACE_HOLDER;
	@ObjectHolder(LibName.HIHIIROKANE_BLOCK)
	public static final Block HIHIIROKANE_BLOCK = PLACE_HOLDER;
	@ObjectHolder(LibName.DRAGON_STONE)
	public static final Block DRAGON_STONE = PLACE_HOLDER;

	public static void register(IForgeRegistry<Block> registry) {
		registry.register(new BlockBase(LibName.COMPACT_STONE, Material.ROCK).setHardness(2.0F).setResistance(-1F));
		registry.register(new BlockKyoumarubotan());
		registry.register(new BlockOnbashira());
		registry.register(new BlockPaper());
		registry.register(new BlockRope());
		registry.register(new BlockShroom());
		registry.register(new BlockCraftingAltar());
		registry.register(new BlockImpureRock());
		registry.register(new BlockHihiirokane());
		registry.register(new BlockDragonStone());
		registerTiles();
	}

	private static void registerTiles() {
		registerTile(TileCraftingAltar.class, LibName.CRAFTING_ALTAR);
	}

	private static <T extends TileEntity> void registerTile(Class<T> tile, String name) {
		GameRegistry.registerTileEntity(tile, new ResourceLocation(LibMod.MOD_NAME, name));
	}

	@SuppressWarnings({"UnusedReturnValue", "WeakerAccess"}) //Shut up
	public static Block setRegistry(Block block, String id) {
		block.setTranslationKey(id);
		block.setRegistryName(LibMod.MOD_ID, id);
		block.setCreativeTab(Alice.CREATIVE_TAB);
		return block;
	}
}
