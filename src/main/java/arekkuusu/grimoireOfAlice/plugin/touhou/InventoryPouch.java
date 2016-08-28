package arekkuusu.grimoireOfAlice.plugin.touhou;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

public class InventoryPouch implements IInventory{

	private final ItemStack invItem;
	
	public static final int INV_SIZE = 8;
	
	private ItemStack[] inventory = new ItemStack[INV_SIZE];
	
	public InventoryPouch(ItemStack stack) {
		invItem = stack;

		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}
		
		readFromNBT(stack.getTagCompound());
	}

	
	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack stack = getStackInSlot(slot);
		if(stack != null) {
			if(stack.stackSize > amount) {
				stack = stack.splitStack(amount);
				markDirty();
			}
			else {
				setInventorySlotContents(slot, null);
			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		setInventorySlotContents(slot, null);
		return stack;
	}

	@Override
	public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
		this.inventory[p_70299_1_] = p_70299_2_;
        if (p_70299_2_ != null && p_70299_2_.stackSize > this.getInventoryStackLimit()) {
            p_70299_2_.stackSize = this.getInventoryStackLimit();
        }
        this.markDirty();
	}

	@Override
	public String getInventoryName() {
		return "§fSpell Card Pouch";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public void markDirty() {
		for (int i = 0; i < getSizeInventory(); ++i) {
			if (getStackInSlot(i) != null && getStackInSlot(i).stackSize == 0) {
				inventory[i] = null;
			}
		}
		writeToNBT(invItem.getTagCompound());
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return true;
	}

	@Override
	public void openInventory() {
		
	}

	@Override
	public void closeInventory() {
		
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack item) {
		return !(item.getItem() instanceof ItemSpellCardPouch);
	}

	public void readFromNBT(NBTTagCompound compound) {
		NBTTagList items = compound.getTagList("ItemInventory", Constants.NBT.TAG_COMPOUND);
		for (int i = 0; i < items.tagCount(); ++i) {
			NBTTagCompound item = items.getCompoundTagAt(i);
			int slot = item.getInteger("Slot");
			if (slot >= 0 && slot < getSizeInventory()) {
				inventory[slot] = ItemStack.loadItemStackFromNBT(item);
			}
		}
	}
	
	public void writeToNBT(NBTTagCompound tagcompound) {
		NBTTagList items = new NBTTagList();
		for (int i = 0; i < getSizeInventory(); ++i) {
			if (getStackInSlot(i) != null) {
				NBTTagCompound item = new NBTTagCompound();
				item.setInteger("Slot", i);
				getStackInSlot(i).writeToNBT(item);
				items.appendTag(item);
			}
		}
		tagcompound.setTag("ItemInventory", items);
	}
	
}
