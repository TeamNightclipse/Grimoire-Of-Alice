/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.block;

import arekkuusu.grimoireofalice.lib.LibBlockName;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

//Lots of code from BlockLog
public class BlockRope extends BlockModPillar {

	BlockRope() {
		super(LibBlockName.ROPEBLOCK, Material.WOOD);
		setHardness(0.5F);
		setSoundType(SoundType.LADDER);
		setHarvestLevel("axe", 1);
		setResistance(5.0F);
	}
}
