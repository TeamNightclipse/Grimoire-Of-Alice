/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.block;

import arekkuusu.grimoireofalice.common.lib.LibBlockName;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(LibMod.MODID)
public class ModBlocks {

	@ObjectHolder(LibBlockName.COMPACT_STONE)
	public static final Block COMPACT_STONE = new Block(Material.ROCK);

	@ObjectHolder(LibBlockName.HOLY_KEY)
	public static final Block HOLY_KEY_STONE = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.HOLY_STONE)
	public static final Block HOLY_STONE = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.KYOUMARUBOTAN)
	public static final Block KYOUMARUBOTAN = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.ONBASHIRA)
	public static final Block ONBASHIRA = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.PAPER_BLOCK)
	public static final Block PAPER_BLOCK = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.ROPE_BLOCK)
	public static final Block ROPE_BLOCK = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.SHROOM)
	public static final Block SHROOM = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.CRAFTING_ALTAR)
	public static final Block ALTAR = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.PILLAR_ALTAR)
	public static final Block PILLAR_ALTAR = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.IMPURE_STONE)
	public static final Block IMPURE_STONE = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.HIHIIROKANE_BLOCK)
	public static final Block HIHIIROKANE_BLOCK = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.SUGAR_BLOCK)
	public static final Block SUGAR_BLOCK = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.HYPER_MAGIC)
	public static final Block HYPER_CONCENTRATED_MAGIC = new Block(Material.ROCK);
}
