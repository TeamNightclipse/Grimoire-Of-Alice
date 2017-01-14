package arekkuusu.grimoireofalice.common.item;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemGohei extends ItemMod {

	private final byte modes;

	public ItemGohei(String id, byte modes) {
		super(id);
		this.modes = modes;
	}

	public void setType(ItemStack itemStack, byte mode) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		if (nbt == null) {
			nbt = new NBTTagCompound();
			itemStack.setTagCompound(nbt);
		}
		nbt.setByte("GoheiMode", mode);
	}

	public byte getType(ItemStack itemStack) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		return nbt == null ? modes : nbt.getByte("GoheiMode");
	}
}
