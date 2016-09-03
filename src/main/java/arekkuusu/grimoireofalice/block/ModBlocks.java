package arekkuusu.grimoireofalice.block;

import arekkuusu.grimoireofalice.lib.LibBlockName;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

	public static Block holyKeyStone;
	public static Block sugarBlock;
	public static Block ropeBlock;
	public static Block paperBlock;
	public static Block onbashira;
	public static Block holyStone;
	public static Block compactStone;
	public static Block hyperconcentratedMagic;
	public static Block shroom;
	public static Block kyoumarubotan;
	
	public static Block onbashiraMiddle;
	public static Block onbashiraTop;

	public static void init() {
		
		compactStone = new BlockMod(LibBlockName.COMPACTSTONE, Material.ROCK);
		GameRegistry.register(new ItemBlock(compactStone).setRegistryName(compactStone.getRegistryName()));
	}

}
