/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.block;

import arekkuusu.grimoireOfAlice.lib.LibBlockName;
import arekkuusu.grimoireOfAlice.lib.LibMod;
import arekkuusu.grimoireOfAlice.tile.TileEntityHolyKeyStone;
import arekkuusu.grimoireOfAlice.tile.TileEntityHolyStone;
import arekkuusu.grimoireOfAlice.tile.TileEntityOnbashira;
import arekkuusu.grimoireOfAlice.tmp.CleanupDone;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

@CleanupDone
public class GOABlock {

	public static Block holyKeyStone;
	public static Block sugarBlock;
	public static Block ropeBlock;
	public static Block paperBlock;
	public static Block onbashira;
	public static Block holyStone;
	public static Block compactStone;
	public static Block hyperconcentratedMagic;
	public static Block shroom;

	public static void preInit() {
		//@formatter:off
		holyKeyStone = new BlockHolyKeyStone().setBlockName(LibBlockName.ILLUBLOCK).setBlockTextureName(LibMod.MODID + ":HolyKeyStone");
		onbashira = new BlockOnbashira().setBlockName(LibBlockName.ONBASHIRA).setBlockTextureName(LibMod.MODID + ":Onbashira");
		holyStone = new BlockHolyStone().setBlockName(LibBlockName.HOLYSTONE).setBlockTextureName(LibMod.MODID + ":HolyKeyStone");
		sugarBlock = new BlockSugar().setBlockName(LibBlockName.SUGARBLOCK);
		ropeBlock = new BlockRope().setBlockName(LibBlockName.ROPEBLOCK);
		paperBlock = new BlockPaper().setBlockName(LibBlockName.PAPERBLOCK);
		compactStone = new BlockCompactStone().setBlockName(LibBlockName.COMPACTSTONE);
		hyperconcentratedMagic = new BlockGOABase(Material.iron).setBlockName(LibBlockName.HYPERMAGIC).setBlockTextureName(LibMod.MODID + ":HyperconcentratedMagic").setHardness(0.5F).setStepSound(Block.soundTypeSnow).setResistance(5.0F);
		hyperconcentratedMagic.setHarvestLevel("pickaxe", 3);
		shroom = new BlockShroom().setBlockName(LibBlockName.SHROOM).setBlockTextureName(LibMod.MODID + ":Shroom");

		GameRegistry.registerBlock(holyKeyStone, LibBlockName.ILLUBLOCK);
		GameRegistry.registerTileEntity(TileEntityHolyKeyStone.class, LibBlockName.ILLUBLOCK);
		GameRegistry.registerBlock(onbashira, LibBlockName.ONBASHIRA);
		GameRegistry.registerTileEntity(TileEntityOnbashira.class, LibBlockName.ONBASHIRA);
		GameRegistry.registerBlock(holyStone, LibBlockName.HOLYSTONE);
		GameRegistry.registerTileEntity(TileEntityHolyStone.class,LibBlockName.HOLYSTONE);
		GameRegistry.registerBlock(sugarBlock, LibBlockName.SUGARBLOCK);
		GameRegistry.registerBlock(ropeBlock, LibBlockName.ROPEBLOCK);
		GameRegistry.registerBlock(paperBlock, LibBlockName.PAPERBLOCK);
		GameRegistry.registerBlock(compactStone, LibBlockName.COMPACTSTONE);
		GameRegistry.registerBlock(hyperconcentratedMagic, LibBlockName.HYPERMAGIC);
		GameRegistry.registerBlock(shroom, LibBlockName.SHROOM);
		//@formatter:on
	}
}
