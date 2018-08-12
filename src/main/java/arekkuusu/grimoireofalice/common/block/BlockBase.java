/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.block;

import arekkuusu.grimoireofalice.client.util.helper.IModel;
import arekkuusu.grimoireofalice.client.util.helper.ModelHandler;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBase extends Block implements IModel {

	public BlockBase(String id, Material material) {
		super(material);
		ModBlocks.setRegistry(this, id);
	}

	public Block setHarvestLevel(Tool lvl, ToolLevel i) {
		setHarvestLevel(lvl.name, i.ordinal());
		return this;
	}

	public Block setSound(SoundType type) {
		return super.setSoundType(type);
	}

	protected IBlockState defaultState() {
		return blockState.getBaseState();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerModel() {
		ModelHandler.registerModel(this, 0);
	}

	public enum Tool {
		PICK("pickaxe"),
		AXE("axe"),
		SHOVEL("shovel"),
		SHEARS("shears");

		final String name;

		Tool(String name) {
			this.name = name;
		}
	}

	public enum ToolLevel {
		WOOD_GOLD,
		STONE,
		IRON,
		DIAMOND
	}
}
