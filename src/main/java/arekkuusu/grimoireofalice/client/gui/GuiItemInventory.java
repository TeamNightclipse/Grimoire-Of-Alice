package arekkuusu.grimoireofalice.client.gui;

import arekkuusu.grimoireofalice.lib.LibMod;
import arekkuusu.grimoireofalice.plugin.touhou.InventoryPouch;
import arekkuusu.grimoireofalice.plugin.touhou.SpellCardContainer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiItemInventory extends GuiContainer {

	private static final ResourceLocation iconLocation = new ResourceLocation(LibMod.MODID.toLowerCase(), "textures/gui/Pouch.png");

	private final InventoryPouch inventory;

	public GuiItemInventory(SpellCardContainer containerItem) {
		super(containerItem);
		this.inventory = containerItem.inventory;
	}

	public void drawScreen(int par1, int par2, float par3) {
		super.drawScreen(par1, par2, par3);
		float xSize_lo = (float) par1;
		float ySize_lo = (float) par2;
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
