/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.block;

import arekkuusu.grimoireofalice.GrimoireOfAlice;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockMod extends Block {

	public BlockMod(String id, Material material) {
		super(material);
		setUnlocalizedName(id);
		setDefaultState(defualtState());
		setRegistryName(id);
		GameRegistry.register(this);
		setCreativeTab(GrimoireOfAlice.CREATIVE_TAB);
	}
	
	public void setSound(SoundType type){
		super.setSoundType(type);
	}

	protected IBlockState defualtState() {
		return blockState.getBaseState();
	}
}
