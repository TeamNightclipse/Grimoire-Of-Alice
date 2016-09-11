package arekkuusu.grimoireofalice.handler.textures;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import arekkuusu.grimoireofalice.lib.LibMod;

public class ItemModMesh implements ItemMeshDefinition {

	@Override
	public ModelResourceLocation getModelLocation(ItemStack stack) {
		return new ModelResourceLocation("");
	}

}
