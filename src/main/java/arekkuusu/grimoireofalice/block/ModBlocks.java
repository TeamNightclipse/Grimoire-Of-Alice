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
	public static final Block compactStone = new Block(Material.ROCK);

	@ObjectHolder(LibBlockName.HOLYKEY)
	public static final Block holyKeyStone = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.HOLYSTONE)
	public static final Block holyStone = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.KYOUMARUBOTAN)
	public static final Block kyoumarubotan = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.ONBASHIRA)
	public static final Block onbashira = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.ONBASHIRAMIDDLE)
	public static final Block onbashiraMiddle = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.ONBASHIRATOP)
	public static final Block onbashiraTop = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.PAPERBLOCK)
	public static final Block paperBlock = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.ROPEBLOCK)
	public static final Block ropeBlock = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.SHROOM)
	public static final Block shroom = new Block(Material.ROCK);

	@ObjectHolder(LibBlockName.SUGARBLOCK)
	public static final Block sugarBlock = new Block(Material.ROCK);
	@ObjectHolder(LibBlockName.HYPERMAGIC)
	public static final Block hyperconcentratedMagic = new Block(Material.ROCK);
}
