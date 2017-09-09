/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.util.helper;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SuppressWarnings("ConstantConditions")
@SideOnly(Side.CLIENT)
public class ModelHandler {

	public static void registerModels() {
		for(Block block : Block.REGISTRY) {
			if(block instanceof IModel)
				((IModel) block).registerModel();
		}

		for(Item item : Item.REGISTRY) {
			if(item instanceof IModel)
				((IModel) item).registerModel();
		}
	}

	public static void registerModel(Block block, int meta, ModelResourceLocation location) {
		Item item = Item.getItemFromBlock(block);
		if(item == Items.AIR) throw new UnsupportedOperationException("This block has no Item!");
		registerModel(item, meta, location);
	}

	public static void registerModel(Item item, int meta, ModelResourceLocation location) {
		ModelLoader.setCustomModelResourceLocation(item, meta, location);
	}

	public static void registerModel(Block block, int meta, String variant) {
		Item item = Item.getItemFromBlock(block);
		if(item == Items.AIR) throw new UnsupportedOperationException("This block has no Item!");
		registerModel(item, meta, variant);
	}

	public static void registerModel(Item item, int meta, String variant) {
		ModelResourceLocation location = new ModelResourceLocation(item.getRegistryName(), variant);
		ModelLoader.setCustomModelResourceLocation(item, meta, location);
	}

	public static void registerModel(Block block, int meta) {
		Item item = Item.getItemFromBlock(block);
		if(item == Items.AIR) throw new UnsupportedOperationException("This block has no Item!");
		registerModel(item, meta);
	}

	public static void registerModel(Item item, int meta) {
		ModelResourceLocation location = new ModelResourceLocation(item.getRegistryName(), "inventory");
		ModelLoader.setCustomModelResourceLocation(item, meta, location);
	}

	public static <T extends Enum<T> & IStringSerializable> void registerModel(Block block, Class<T> clazz) {
		Item item = Item.getItemFromBlock(block);
		if(item == Items.AIR) throw new UnsupportedOperationException("This block has no Item!");
		registerModel(item, clazz);
	}

	public static <T extends Enum<T> & IStringSerializable> void registerModel(Item item, Class<T> clazz) {
		for(T t : clazz.getEnumConstants()) {
			ResourceLocation location = new ResourceLocation(item.getRegistryName() + "_" + t.getName());
			ModelResourceLocation modelResourceLocation = new ModelResourceLocation(location, "inventory");
			ModelLoader.setCustomModelResourceLocation(item, t.ordinal(), modelResourceLocation);
		}
	}
}
