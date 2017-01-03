package arekkuusu.grimoireofalice.common.plugin.danmakucore.item;

import arekkuusu.grimoireofalice.common.item.ItemMod;
import net.katsstuff.danmakucore.helper.ItemNBTHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemJeweled extends ItemMod {

	public ItemJeweled(String name) {
		super(name);
		addPropertyOverride(new ResourceLocation("jewels"),
				(stack, world, entity) -> stack.hasTagCompound() ? (float)getJewels(stack) : 0F);
	}

	protected void addJewels(ItemStack itemStack, short charge) {
		short jewels = ItemNBTHelper.getShort(itemStack, "Jewels", (short)0);
		ItemNBTHelper.setShort(itemStack, "Jewels", (short)(jewels + charge));
	}

	protected void setJewels(ItemStack itemStack, short charge) {
		ItemNBTHelper.setShort(itemStack, "Jewels", charge);
	}

	protected short getJewels(ItemStack itemStack) {
		return ItemNBTHelper.getShort(itemStack, "Jewels", (short)0);
	}
}