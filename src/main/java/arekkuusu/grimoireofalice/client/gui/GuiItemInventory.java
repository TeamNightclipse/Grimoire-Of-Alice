/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.gui;

import arekkuusu.grimoireofalice.lib.LibMod;
import arekkuusu.grimoireofalice.plugin.danmakucore.item.SpellCardContainer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiItemInventory extends GuiContainer {

	private static final ResourceLocation iconLocation = new ResourceLocation(LibMod.MODID.toLowerCase(), "textures/gui/Pouch.png");

	public GuiItemInventory(InventoryPlayer playerInv, ItemStack flowerBagInv) {
		super(new SpellCardContainer(playerInv, flowerBagInv));
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		String s = "§fSpell Card Pouch"; //TODO: Localize
		fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 0, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(iconLocation);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
	}
}
