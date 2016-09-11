package arekkuusu.grimoireofalice.handler.textures;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import arekkuusu.grimoireofalice.lib.LibMod;

public class Mesh3rdEye extends ItemModMesh {
	
	@Override
	public ModelResourceLocation getModelLocation(ItemStack stack) {
		if(stack.isItemDamaged()){
			return new ModelResourceLocation(LibMod.MODID + ":3rdeye0", "inventory");
		}
		return new ModelResourceLocation(stack.getItem().getRegistryName(), "inventory");
	}

}
