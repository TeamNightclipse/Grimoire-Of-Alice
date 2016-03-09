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
import arekkuusu.grimoireofalice.lib.libMod;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class GOABlock {
	
	public static Block blockHolyKeyStone;

	public static void preInit(){
		
		blockHolyKeyStone = new BlockHolyKeyStone(Material.rock).setBlockName("HolyKeyStone").setBlockTextureName(libMod.MODID + ":HolyKeyStone");
		
		GameRegistry.registerBlock(blockHolyKeyStone, LibBlockName.ILLUBLOCK);
	}
	
}
