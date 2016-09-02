/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice;

import java.util.List;
import arekkuusu.grimoireofalice.item.ModItems;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GOACreativeTab extends CreativeTabs {

	private List<ItemStack> list;
	
	GOACreativeTab() {
		super(LibMod.MODID);
		setNoTitle();
	}

	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(ModItems.uFOs);
	}
	
	@Override
	public Item getTabIconItem() {
		return getIconItemStack().getItem();
	}

	@Override
	public boolean hasSearchBar() {
		return false;
	}
	
	@Override
	public void displayAllRelevantItems(List<ItemStack> list) {
		this.list = list;

		addItem(ModItems.thirdEye);
		addItem(ModItems.amenonuhoko);
		addItem(ModItems.gloriousNipponSteel);
		addItem(ModItems.hihiirokane);
		addItem(ModItems.laevatein);
		addItem(ModItems.mapleLeafShield);
		addItem(ModItems.mikoStick);
		addItem(ModItems.mochiHammer);
		addItem(ModItems.momijisScimitarSword);
		addItem(ModItems.nazrinStick);
		addItem(ModItems.nueTrident);
		addItem(ModItems.shimenawaRope);
		addItem(ModItems.swordOfKusanagi);
		addItem(ModItems.timeOrb);
		addItem(ModItems.uFOs);
	}
	
	private void addItem(Item item) {
		item.getSubItems(item, this, list);
	}

	private void addBlock(Block block) {
		ItemStack stack = new ItemStack(block);
		block.getSubBlocks(stack.getItem(), this, list);
	}
	
}