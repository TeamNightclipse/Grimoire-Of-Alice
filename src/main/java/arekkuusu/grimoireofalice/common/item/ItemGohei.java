package arekkuusu.grimoireofalice.common.item;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemGohei<E extends Enum<E>> extends ItemBase {

	private E[] modes;

	public ItemGohei(String id, E[] modes) {
		super(id);
		this.modes = modes;
	}

	protected void increaseType(ItemStack itemStack) {
		int nextIndex = getType(itemStack).ordinal() + 1;
		E next;
		if(nextIndex >= modes.length) {
			next = modes[0];
		}
		else {
			next = modes[nextIndex];
		}

		setType(itemStack, next);
	}

	protected void setType(ItemStack itemStack, E mode) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		if(nbt == null) {
			nbt = new NBTTagCompound();
			itemStack.setTagCompound(nbt);
		}
		nbt.setByte("GoheiMode", (byte) mode.ordinal());
	}

	protected E getType(ItemStack itemStack) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		return nbt == null ? modes[0] : modes[nbt.getByte("GoheiMode")];
	}
}
