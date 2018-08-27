/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.core.handler;

import arekkuusu.grimoireofalice.client.gui.GuiItemInventory;
import arekkuusu.grimoireofalice.client.gui.GuiMarisaHat;
import arekkuusu.grimoireofalice.common.item.HatContainer;
import arekkuusu.grimoireofalice.common.item.ModItems;
import arekkuusu.grimoireofalice.common.item.SpellCardContainer;
import arekkuusu.grimoireofalice.common.lib.LibGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		switch(id) {
			case LibGui.POUCH_BAG:
				EnumHand hand = EnumHand.MAIN_HAND.ordinal() == x ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
				ItemStack heldItem = player.getHeldItem(hand);
				if(heldItem.isEmpty() || heldItem.getItem() != ModItems.SPELLCARD_POUCH) {
					return null;
				}
				return new SpellCardContainer(player.inventory, heldItem);
			case LibGui.HAT:
				EnumHand hand0 = EnumHand.MAIN_HAND.ordinal() == x ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
				ItemStack heldItem0 = player.getHeldItem(hand0);
				if(heldItem0.isEmpty() || heldItem0.getItem() != ModItems.MARISA_HAT) {
					return null;
				}
				return new HatContainer(player.inventory, heldItem0);
			default:
				return null;
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		switch(id) {
			case LibGui.POUCH_BAG:
				EnumHand hand0 = EnumHand.MAIN_HAND.ordinal() == x ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
				ItemStack heldItem0 = player.getHeldItem(hand0);
				if(heldItem0.isEmpty() || heldItem0.getItem() != ModItems.SPELLCARD_POUCH) {
					return null;
				}
				return new GuiItemInventory(player.inventory, heldItem0);
			case LibGui.HAT:
				EnumHand hand1 = EnumHand.MAIN_HAND.ordinal() == x ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
				ItemStack heldItem1 = player.getHeldItem(hand1);
				if(heldItem1.isEmpty() || heldItem1.getItem() != ModItems.MARISA_HAT) {
					return null;
				}
				return new GuiMarisaHat(player.inventory, heldItem1);
			default:
				return null;
		}
	}
}
