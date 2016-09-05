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
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {


	public static Block compactStone;
	
	public static Block holyKeyStone;
	public static Block holyStone;
	public static Block kyoumarubotan;
	public static Block onbashira;
	public static Block onbashiraMiddle;
	public static Block onbashiraTop;
	public static Block paperBlock;
	public static Block ropeBlock;
	public static Block shroom;
	
	public static Block sugarBlock;
	public static Block hyperconcentratedMagic;

	public static void init() {
		compactStone = new BlockMod(LibBlockName.COMPACTSTONE, Material.ROCK);
			GameRegistry.register(new ItemBlock(compactStone).setRegistryName(compactStone.getRegistryName()));
		holyKeyStone = new BlockHolyKeyStone();
			GameRegistry.register(new ItemBlock(holyKeyStone).setRegistryName(holyKeyStone.getRegistryName()));
		holyStone = new BlockHolyStone();
			GameRegistry.register(new ItemBlock(holyStone).setRegistryName(holyStone.getRegistryName()));
		kyoumarubotan = new BlockKyoumarubotan();
			GameRegistry.register(new ItemBlock(kyoumarubotan).setRegistryName(kyoumarubotan.getRegistryName()));
		onbashira = new BlockOnbashira();
			GameRegistry.register(new ItemBlock(onbashira).setRegistryName(onbashira.getRegistryName()));
		onbashiraMiddle = new BlockOnbashiraMiddle();
			GameRegistry.register(new ItemBlock(onbashiraMiddle).setRegistryName(onbashiraMiddle.getRegistryName()));
		onbashiraTop = new BlockOnbashiraTop();
			GameRegistry.register(new ItemBlock(onbashiraTop).setRegistryName(onbashiraTop.getRegistryName()));
		paperBlock = new BlockPaper();
			GameRegistry.register(new ItemBlock(paperBlock).setRegistryName(paperBlock.getRegistryName()));
		ropeBlock = new BlockRope();
			GameRegistry.register(new ItemBlock(ropeBlock).setRegistryName(ropeBlock.getRegistryName()));
		shroom = new BlockShroom();
			GameRegistry.register(new ItemBlock(shroom).setRegistryName(shroom.getRegistryName()));
	
		sugarBlock = new BlockMod(LibBlockName.SUGARBLOCK, Material.CLAY).setHardness(0.2F).setResistance(5.0F);
			sugarBlock.setHarvestLevel("axe", 1);
				((BlockMod) sugarBlock).setSound(SoundType.SNOW);
				GameRegistry.register(new ItemBlock(sugarBlock).setRegistryName(sugarBlock.getRegistryName()));
		hyperconcentratedMagic = new BlockMod(LibBlockName.HYPERMAGIC, Material.IRON).setHardness(0.5F).setResistance(2.0F);
			hyperconcentratedMagic.setHarvestLevel("pickaxe", 3);
				((BlockMod) hyperconcentratedMagic).setSound(SoundType.SNOW);
				GameRegistry.register(new ItemBlock(hyperconcentratedMagic).setRegistryName(hyperconcentratedMagic.getRegistryName()));
	}
}
