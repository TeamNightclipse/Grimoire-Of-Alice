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
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

public class GuiScreenGuide extends GuiScreen {
    private static final ResourceLocation BOOK_GUI_TEXTURES[] = {new ResourceLocation(LibMod.MODID, "textures/gui/guide/Guide0.png"),
            new ResourceLocation(LibMod.MODID, "textures/gui/guide/Guide1.png"),
            new ResourceLocation(LibMod.MODID, "textures/gui/guide/Guide2.png"),
            new ResourceLocation(LibMod.MODID, "textures/gui/guide/Guide3.png"),
            new ResourceLocation(LibMod.MODID, "textures/gui/guide/Guide4.png"),
            new ResourceLocation(LibMod.MODID, "textures/gui/guide/Guide5.png"),
            new ResourceLocation(LibMod.MODID, "textures/gui/guide/Guide6.png"),
            new ResourceLocation(LibMod.MODID, "textures/gui/guide/Guide7.png"),
            new ResourceLocation(LibMod.MODID, "textures/gui/guide/Guide8.png"),
            new ResourceLocation(LibMod.MODID, "textures/gui/guide/Guide9.png")};
    private static final int bookTotalPages = 19;
    private int currPage = 0;
    private GuiButton buttonDone;
    private NextPageButton buttonNextPage;
    private NextPageButton buttonPreviousPage;

	@Override
	public void initGui() {
		buttonList.clear();
		Keyboard.enableRepeatEvents(true);

		buttonDone = new GuiButton(0, this.width / 2 + 2, 196, 98, 20, I18n.format("gui.done"));

		buttonList.add(buttonDone);
		int i = (this.width - 192) / 2;
		buttonList.add(buttonNextPage = this.addButton(new NextPageButton(1, i + 120, 156, true)));
		buttonList.add(buttonPreviousPage = this.addButton(new NextPageButton(2, i + 38, 156, false)));

	}

    @Override
    public void updateScreen() {
		buttonDone.visible = (currPage == bookTotalPages - 1);
		buttonNextPage.visible = (currPage < bookTotalPages - 1);
		buttonPreviousPage.visible = currPage > 0;
	}

    @Override
    protected void actionPerformed(GuiButton parButton) { //For some reason this goes 2 4 6 8 10 12 16 18...
		if (parButton == buttonDone) {
			mc.displayGuiScreen(null);
		} else if (parButton == buttonNextPage) {
			if (currPage < bookTotalPages - 1) {
				++currPage;
			}
		} else if (parButton == buttonPreviousPage) {
			if (currPage > 0) {
				--currPage;
			}
		}
	}

    @Override
    public void drawScreen(int parWidth, int parHeight, float p_73863_3_) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		ResourceLocation TEXTURE;
		switch (currPage) {
			case 2:
				TEXTURE = BOOK_GUI_TEXTURES[1];
				break;
			case 4:
				TEXTURE = BOOK_GUI_TEXTURES[2];
				break;
			case 6:
				TEXTURE = BOOK_GUI_TEXTURES[3];
				break;
			case 8:
				TEXTURE = BOOK_GUI_TEXTURES[4];
				break;
			case 10:
				TEXTURE = BOOK_GUI_TEXTURES[5];
				break;
			case 12:
				TEXTURE = BOOK_GUI_TEXTURES[6];
				break;
			case 14:
				TEXTURE = BOOK_GUI_TEXTURES[7];
				break;
			case 16:
				TEXTURE = BOOK_GUI_TEXTURES[8];
				break;
			case 18:
				TEXTURE = BOOK_GUI_TEXTURES[9];
				break;
			default:
				TEXTURE = BOOK_GUI_TEXTURES[0];
		}
		mc.getTextureManager().bindTexture(TEXTURE);
		int i = (this.width - 192) / 2;
		this.drawTexturedModalRect(i, 2, 0, 0, 192, 192);
		super.drawScreen(parWidth, parHeight, p_73863_3_);
	}

    @Override
    protected void mouseClickMove(int parMouseX, int parMouseY, int parLastButtonClicked, long parTimeSinceMouseClick) {

    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    static class NextPageButton extends GuiButton {
        private final boolean isForward;

        public NextPageButton(int p_i46316_1_, int p_i46316_2_, int p_i46316_3_, boolean p_i46316_4_) {
            super(p_i46316_1_, p_i46316_2_, p_i46316_3_, 23, 13, "");
            this.isForward = p_i46316_4_;
        }

        public void drawButton(Minecraft mc, int mouseX, int mouseY) {
            if (this.visible) {
                boolean flag = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                mc.getTextureManager().bindTexture(BOOK_GUI_TEXTURES[0]);
                int i = 0;
                int j = 192;

                if (flag) {
                    i += 23;
                }

                if (!this.isForward) {
                    j += 13;
                }

                this.drawTexturedModalRect(this.xPosition, this.yPosition, i, j, 23, 13);
            }
        }
    }

	private <T extends GuiButton> T addButton(T p_189646_1_) {
		this.buttonList.add(p_189646_1_);
		return (T)p_189646_1_;
	}
}
