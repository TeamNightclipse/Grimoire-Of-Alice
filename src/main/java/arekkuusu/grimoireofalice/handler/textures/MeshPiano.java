package arekkuusu.grimoireofalice.handler.textures;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import arekkuusu.grimoireofalice.lib.LibMod;

public class MeshPiano extends ItemModMesh {
	
	@Override
	public ModelResourceLocation getModelLocation(ItemStack stack) {
		if(stack.isItemDamaged()){
			return new ModelResourceLocation(LibMod.MODID + ":lyricapianoused", "inventory");
		}
		return new ModelResourceLocation(stack.getItem().getRegistryName(), "inventory");
	}

}
