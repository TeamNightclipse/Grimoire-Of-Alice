package arekkuusu.grimoireofalice.common.item;

import net.katsstuff.danmakucore.helper.ShortNBTProperty;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemJeweled extends ItemBase {

	public static final ShortNBTProperty<ItemStack> JEWELS = ShortNBTProperty.ofStack("Jewels");

	public ItemJeweled(String name) {
		super(name);
		addPropertyOverride(new ResourceLocation("jewels"),
				(stack, world, entity) -> stack.hasTagCompound() ? (float) JEWELS.get(stack) : 0F);
	}

	protected void addJewels(ItemStack stack, short charge) {
		JEWELS.set((short)(JEWELS.get(stack) + charge), stack);
	}
}