/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.block;

import arekkuusu.grimoireofalice.common.Alice;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BlockModBush extends BlockBush {

	public BlockModBush(String id, Material material) {
		super(material);
		setUnlocalizedName(id);
		setDefaultState(defaultState());
		setRegistryName(id);
		setCreativeTab(Alice.CREATIVE_TAB);
	}

	protected IBlockState defaultState() {
		return blockState.getBaseState();
	}

}
