/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.block;

import arekkuusu.grimoireOfAlice.client.tile.TileEntityHolyKeyStone;
import arekkuusu.grimoireOfAlice.client.tile.TileEntityHolyStone;
import arekkuusu.grimoireOfAlice.client.tile.TileEntityOnbashira;
import arekkuusu.grimoireOfAlice.lib.LibBlockName;
import arekkuusu.grimoireOfAlice.lib.LibMod;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class GOABlock {

	public static Block blockHolyKeyStone;
	public static Block blockSugar;
	public static Block blockRope;
	public static Block blockPaper;
	public static Block blockOnbashira;
	public static Block blockHolyStone;
	public static Block blockCompactStone;
	public static Block blockHyperconcentratedMagic;
	public static Block blockShroom;

	public static void preInit() {
		//@formatter:off
		blockHolyKeyStone = new BlockHolyKeyStone(Material.rock).setBlockName(LibBlockName.ILLUBLOCK).setBlockTextureName(LibMod.MODID + ":HolyKeyStone");
		blockOnbashira = new BlockOnbashira(Material.wood).setBlockName(LibBlockName.ONBASHIRA).setBlockTextureName(LibMod.MODID + ":Onbashira");
		blockHolyStone = new BlockHolyStone(Material.rock).setBlockName(LibBlockName.HOLYSTONE).setBlockTextureName(LibMod.MODID + ":HolyKeyStone");
		blockSugar = new BlockSugar(Material.clay).setBlockName(LibBlockName.SUGARBLOCK);
		blockRope = new BlockRope(Material.wood).setBlockName(LibBlockName.ROPEBLOCK);
		blockPaper = new BlockPaper(Material.carpet).setBlockName(LibBlockName.PAPERBLOCK);
		blockCompactStone = new BlockCompactStone(Material.rock).setBlockName(LibBlockName.COMPACTSTONE);
		blockHyperconcentratedMagic = new BlockHyperconcentratedMagic(Material.iron).setBlockName(LibBlockName.HYPERMAGIC).setBlockTextureName(LibMod.MODID + ":HyperconcentratedMagic");
		blockShroom = new BlockShroom(Material.plants).setBlockName(LibBlockName.SHROOM).setBlockTextureName(LibMod.MODID + ":Shroom");
		
		GameRegistry.registerBlock(blockHolyKeyStone, LibBlockName.ILLUBLOCK);
		GameRegistry.registerTileEntity(TileEntityHolyKeyStone.class, LibBlockName.ILLUBLOCK);
		GameRegistry.registerBlock(blockOnbashira, LibBlockName.ONBASHIRA);
		GameRegistry.registerTileEntity(TileEntityOnbashira.class, LibBlockName.ONBASHIRA);
		GameRegistry.registerBlock(blockHolyStone, LibBlockName.HOLYSTONE);
		GameRegistry.registerTileEntity(TileEntityHolyStone.class,LibBlockName.HOLYSTONE);
		GameRegistry.registerBlock(blockSugar, LibBlockName.SUGARBLOCK);
		GameRegistry.registerBlock(blockRope, LibBlockName.ROPEBLOCK);
		GameRegistry.registerBlock(blockPaper, LibBlockName.PAPERBLOCK);
		GameRegistry.registerBlock(blockCompactStone, LibBlockName.COMPACTSTONE);
		GameRegistry.registerBlock(blockHyperconcentratedMagic, LibBlockName.HYPERMAGIC);
		GameRegistry.registerBlock(blockShroom, LibBlockName.SHROOM);
		//@formatter:on
	}
}
