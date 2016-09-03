package arekkuusu.grimoireofalice.block;

import arekkuusu.grimoireofalice.GrimoireOfAlice;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockMod extends Block {

	public BlockMod(String id, Material material) {
		super(material);
		setRegistryName(id);
		setUnlocalizedName(id);
		GameRegistry.register(this);
		setCreativeTab(GrimoireOfAlice.CREATIVE_TAB);
	}
}
