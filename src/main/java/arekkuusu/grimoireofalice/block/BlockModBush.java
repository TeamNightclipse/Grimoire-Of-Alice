package arekkuusu.grimoireofalice.block;

import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.registry.GameRegistry;
import arekkuusu.grimoireofalice.GrimoireOfAlice;

public class BlockModBush extends BlockBush {
	
	public BlockModBush(String id, Material material) {
		super(material);
		setUnlocalizedName(id);
		setDefaultState(defualtState());
		setRegistryName(id);
		GameRegistry.register(this);
		setCreativeTab(GrimoireOfAlice.CREATIVE_TAB);
	}

	protected IBlockState defualtState() {
		return blockState.getBaseState();
	}

}
