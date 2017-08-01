/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.gui;

import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import arekkuusu.grimoireofalice.client.ResourceLocations;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiScreenGuide extends GuiScreen {

	private static final ImmutableList<List<String>> TEXTS = ImmutableList.of(
			Collections.emptyList(),
			getText("grimoire.gui.book_1"),
			getText("grimoire.gui.book_2"),
			getText("grimoire.gui.book_3"),
			getText("grimoire.gui.book_4"),
			getText("grimoire.gui.book_5"),
			getText("grimoire.gui.book_6"),
			getText("grimoire.gui.book_7"),
			getText("grimoire.gui.book_8"),
			getText("grimoire.gui.book_9")
	);

	private static final int BOOK_TOTAL_PAGES = 10;
	private int currPage = 0;
	private GuiButton buttonDone;
	private NextPageButton buttonNextPage;
	private NextPageButton buttonPreviousPage;

	private static List<String> getText(String text) {
		List<String> lines = new ArrayList<>();
		String formatted = I18n.format(text);

		Collections.addAll(lines, formatted.split("\\|"));

		return lines;
	}

	@Override
	public void initGui() {
		buttonList.clear();
		Keyboard.enableRepeatEvents(true);

		buttonDone = new GuiButton(0, width / 2 + 2, 256, 98, 20, I18n.format("gui.done"));
		buttonList.add(buttonDone);

		int i = (width - 180) / 2;
		buttonNextPage = this.addButton(new NextPageButton(1, i + 120, 256, true));
		buttonPreviousPage = this.addButton(new NextPageButton(2, i + 38, 256, false));

		buttonDone.visible = false;
		buttonPreviousPage.visible = false;
		buttonNextPage.visible = true;
	}

	@Override
	public void updateScreen() {
		buttonDone.visible = currPage == BOOK_TOTAL_PAGES - 1;
		buttonNextPage.visible = currPage < BOOK_TOTAL_PAGES - 1;
		buttonPreviousPage.visible = currPage > 0;
	}

	@Override
	protected void actionPerformed(GuiButton parButton) {
		if(parButton == buttonDone) {
			mc.displayGuiScreen(null);
		}
		else if(parButton == buttonNextPage) {
			if(currPage < BOOK_TOTAL_PAGES - 1) {
				++currPage;
			}
		}
		else if(parButton == buttonPreviousPage && currPage > 0) {
			--currPage;
		}
		playTurnPage();
	}

	@Override
	public void drawScreen(int parWidth, int parHeight, float partialTicks) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(ResourceLocations.BOOK_GUI_TEXTURES[currPage]);
		drawTexturedModalRect((width - 250) / 2, 56, 0, 0, 256, 192);

		List<String> get = TEXTS.get(MathHelper.clamp(currPage, 0, 9));
		for(int i = 0; i < get.size(); i++) {
			String s = get.get(i);
			fontRenderer.drawString(s, (width - 245) / 2, 70 + i * 12, 0);
		}

		super.drawScreen(parWidth, parHeight, partialTicks);
	}

	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		player.playSound(GrimoireSoundEvents.PAGE_TURN, 1F, 1F);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	private static class NextPageButton extends GuiButton {

		private final boolean isForward;

		public NextPageButton(int buttonId, int widthIn, int heightIn, boolean isForward) {
			super(buttonId, widthIn, heightIn, 23, 13, "");
			this.isForward = isForward;
		}

		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY) {
			if(visible) {
				boolean flag = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				mc.getTextureManager().bindTexture(ResourceLocations.BOOK_GUI_TEXTURES[0]);
				int i = 0;
				int j = 192;

				if(flag) {
					i += 23;
				}

				if(!isForward) {
					j += 13;
				}

				this.drawTexturedModalRect(x, y, i, j, 23, 13);
			}
		}
	}

	private static void playTurnPage() {
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		player.playSound(GrimoireSoundEvents.PAGE_TURN, 0.5F, 1.25F);
	}
}
