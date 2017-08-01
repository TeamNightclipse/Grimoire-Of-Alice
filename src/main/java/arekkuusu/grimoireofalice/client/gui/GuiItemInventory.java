/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.gui;

import java.util.Locale;

import arekkuusu.grimoireofalice.common.lib.LibMod;
import arekkuusu.grimoireofalice.common.plugin.danmakucore.item.SpellCardContainer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiItemInventory extends AbstractGuiContainer {

	private static final ResourceLocation ICON_LOCATION = new ResourceLocation(LibMod.MODID.toLowerCase(Locale.ROOT), "textures/gui/Pouch.png");

	public GuiItemInventory(InventoryPlayer playerInv, ItemStack stack) {
		super(new SpellCardContainer(playerInv, stack), ICON_LOCATION, "grimoire.gui.pouch.name");
	}
}
