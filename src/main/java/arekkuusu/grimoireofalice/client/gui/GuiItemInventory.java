package arekkuusu.grimoireofalice.client.gui;

import arekkuusu.grimoireofalice.lib.LibMod;
import arekkuusu.grimoireofalice.plugin.touhou.InventoryPouch;
import arekkuusu.grimoireofalice.plugin.touhou.SpellCardContainer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiItemInventory extends GuiContainer {

	private static final ResourceLocation iconLocation = new ResourceLocation(LibMod.MODID.toLowerCase(), "textures/gui/Pouch.png");

	public GuiItemInventory(InventoryPlayer playerInv, InventoryPouch flowerBagInv) {
		super(new SpellCardContainer(playerInv, flowerBagInv));
	}

	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		String s = "§fSpell Card Pouch";
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 0, 4210752);
	}

	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(iconLocation);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
}
