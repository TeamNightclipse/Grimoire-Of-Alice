/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.block;

import arekkuusu.grimoireofalice.lib.LibBlockName;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(LibMod.MODID)
public class ModBlocks {

	@ObjectHolder(LibBlockName.COMPACTSTONE)
	public static final Block COMPACT_STONE = new Block(Material.ROCK);

	@ObjectHolder(LibBlockName.HOLYKEY)
	public static final Block HOLY_KEY_STONE = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.HOLYSTONE)
	public static final Block HOLY_STONE = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.KYOUMARUBOTAN)
	public static final Block KYOUMARUBOTAN = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.ONBASHIRA)
	public static final Block ONBASHIRA = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.ONBASHIRAMIDDLE)
	public static final Block ONBASHIRA_MIDDLE = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.ONBASHIRATOP)
	public static final Block ONBASHIRA_TOP = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.PAPERBLOCK)
	public static final Block PAPER_BLOCK = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.ROPEBLOCK)
	public static final Block ROPE_BLOCK = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.SHROOM)
	public static final Block SHROOM = new Block(Material.ROCK);

	@ObjectHolder(LibBlockName.SUGARBLOCK)
	public static final Block SUGAR_BLOCK = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.HYPERMAGIC)
	public static final Block HYPER_CONCENTRATED_MAGIC = new Block(Material.ROCK);
}
