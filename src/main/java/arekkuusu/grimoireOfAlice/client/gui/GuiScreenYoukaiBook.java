/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.client.gui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import arekkuusu.grimoireOfAlice.item.GOAItem;
import arekkuusu.grimoireOfAlice.lib.LibMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GuiScreenYoukaiBook extends GuiScreen {

	private static final Logger LOGGER = LogManager.getLogger();
	private static final ResourceLocation BOOK_GUI_TEXTURES = new ResourceLocation(LibMod.MODID.toLowerCase(), "textures/gui/YoukaiBook.png");
	private static final int BOOK_IMAGE_WIDTH = 192;
	private static final int BOOK_IMAGE_HEIGHT = 192;

	private final EntityPlayer editingPlayer;
	private final ItemStack bookObj;
	private final boolean bookIsUnsigned;
	private boolean field_146481_r;
	private boolean field_146480_s;
	private int updateCount;
	private int bookTotalPages = 1;
	private int currPage;
	private NBTTagList bookPages;
	private String bookTitle = "§kS§kp";
	private GuiScreenYoukaiBook.NextPageButton buttonNextPage;
	private GuiScreenYoukaiBook.NextPageButton buttonPreviousPage;
	private GuiButton buttonDone;
	private GuiButton buttonSign;
	private GuiButton buttonFinalize;
	private GuiButton buttonCancel;

	public GuiScreenYoukaiBook(EntityPlayer player, ItemStack stack, boolean isSigned) {
		editingPlayer = player;
		bookObj = stack;
		bookIsUnsigned = isSigned;

		if(stack.hasTagCompound()) {
			NBTTagCompound nbttagcompound = stack.getTagCompound();
			bookPages = nbttagcompound.getTagList("pages", 8);

			if(bookPages != null) {
				bookPages = (NBTTagList)bookPages.copy();
				bookTotalPages = bookPages.tagCount();

				if(bookTotalPages < 1) {
					bookTotalPages = 1;
				}
			}
		}

		if(bookPages == null && isSigned) {
			bookPages = new NBTTagList();
			bookPages.appendTag(new NBTTagString(""));
			bookTotalPages = 1;
		}
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		++updateCount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		buttonList.clear();
		Keyboard.enableRepeatEvents(true);

		if(bookIsUnsigned) {
			buttonList.add(buttonSign = new GuiButton(3, width / 2 - 100, 4 + BOOK_IMAGE_HEIGHT, 98, 20, I18n.format("book.signButton")));
			buttonList.add(buttonDone = new GuiButton(0, width / 2 + 2, 4 + BOOK_IMAGE_HEIGHT, 98, 20, I18n.format("gui.done")));
			buttonList.add(buttonFinalize = new GuiButton(5, width / 2 - 100, 4 + BOOK_IMAGE_HEIGHT, 98, 20, I18n.format("book.finalizeButton")));
			buttonList.add(buttonCancel = new GuiButton(4, width / 2 + 2, 4 + BOOK_IMAGE_HEIGHT, 98, 20, I18n.format("gui.cancel")));
		}
		else {
			buttonList.add(buttonDone = new GuiButton(0, width / 2 - 100, 4 + BOOK_IMAGE_HEIGHT, 200, 20, I18n.format("gui.done")));
		}

		int i = (width - BOOK_IMAGE_WIDTH) / 2;
		byte b0 = 2;
		buttonList.add(buttonNextPage = new GuiScreenYoukaiBook.NextPageButton(1, i + 120, b0 + 154, true));
		buttonList.add(buttonPreviousPage = new GuiScreenYoukaiBook.NextPageButton(2, i + 38, b0 + 154, false));
		updateButtons();
	}

	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	private void updateButtons() {
		buttonNextPage.visible = !field_146480_s && (currPage < bookTotalPages - 1 || bookIsUnsigned);
		buttonPreviousPage.visible = !field_146480_s && currPage > 0;
		buttonDone.visible = !bookIsUnsigned || !field_146480_s;

		if(bookIsUnsigned) {
			buttonSign.visible = !field_146480_s;
			buttonCancel.visible = field_146480_s;
			buttonFinalize.visible = field_146480_s;
			buttonFinalize.enabled = bookTitle.trim().length() > 6;
		}
	}

	private void sendBookToServer(boolean p_146462_1_) {
		if(bookIsUnsigned && field_146481_r) {
			if(bookPages != null) {
				String s;

				while(bookPages.tagCount() > 1) {
					s = bookPages.getStringTagAt(bookPages.tagCount() - 1);

					if(s.length() != 0) {
						break;
					}

					bookPages.removeTag(bookPages.tagCount() - 1);
				}

				if(bookObj.hasTagCompound()) {
					NBTTagCompound nbttagcompound = bookObj.getTagCompound();
					nbttagcompound.setTag("pages", bookPages);
				}
				else {
					bookObj.setTagInfo("pages", bookPages);
				}

				s = "MC|BEdit";

				if(p_146462_1_) {
					s = "MC|BSign";
					bookObj.setTagInfo("author", new NBTTagString(editingPlayer.getCommandSenderName()));
					bookObj.setTagInfo("title", new NBTTagString(bookTitle.trim().replace("§k", "").substring(2)));
					bookObj.func_150996_a(GOAItem.enchantedBook);
				}

				ByteBuf bytebuf = Unpooled.buffer();

				try {
					new PacketBuffer(bytebuf).writeItemStackToBuffer(bookObj);
					mc.getNetHandler().addToSendQueue(new C17PacketCustomPayload(s, bytebuf));
				}
				catch(Exception exception) {
					LOGGER.error("Couldn\'t send book info", exception);
				}
				finally {
					bytebuf.release();
				}
			}
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		if(button.enabled) {
			if(button.id == 0) {
				mc.displayGuiScreen(null);
				sendBookToServer(false);
			}
			else if(button.id == 3 && bookIsUnsigned) {
				field_146480_s = true;
			}
			else if(button.id == 1) {
				if(currPage < bookTotalPages - 1) {
					++currPage;
				}
				else if(bookIsUnsigned) {
					addNewPage();

					if(currPage < bookTotalPages - 1) {
						++currPage;
					}
				}
			}
			else if(button.id == 2) {
				if(currPage > 0) {
					--currPage;
				}
			}
			else if(button.id == 5 && field_146480_s) {
				sendBookToServer(true);
				mc.displayGuiScreen(null);
			}
			else if(button.id == 4 && field_146480_s) {
				field_146480_s = false;
			}

			updateButtons();
		}
	}

	private void addNewPage() {
		if(bookPages != null && bookPages.tagCount() < 50) {
			bookPages.appendTag(new NBTTagString(""));
			++bookTotalPages;
			field_146481_r = true;
		}
	}

	@Override
	protected void keyTyped(char p_73869_1_, int p_73869_2_) {
		super.keyTyped(p_73869_1_, p_73869_2_);

		if(bookIsUnsigned) {
			if(field_146480_s) {
				func_146460_c(p_73869_1_, p_73869_2_);
			}
			else {
				keyTypedInBook(p_73869_1_, p_73869_2_);
			}
		}
	}

	private void keyTypedInBook(char p_146463_1_, int p_146463_2_) {
		switch(p_146463_1_) {
			case 22:
				func_146459_b(GuiScreen.getClipboardString());
				return;
			default:
				switch(p_146463_2_) {
					case 14:
						String s = func_146456_p();

						if(s.length() > 0) {
							func_146457_a(s.substring(0, s.length() - 3));
						}

						return;
					case 28:
					case 156:
						func_146459_b("\n");
						return;
					default:
						if(ChatAllowedCharacters.isAllowedCharacter(p_146463_1_)) {
							func_146459_b("§k"+Character.toString(p_146463_1_));
						}
				}
		}
	}

	private void func_146460_c(char p_146460_1_, int p_146460_2_) {
		switch(p_146460_2_) {
			case 14:
				if(!bookTitle.isEmpty()) {
					bookTitle = bookTitle.substring(0, bookTitle.length() - 3);
					updateButtons();
				}

				return;
			case 28:
			case 156:
				if(!bookTitle.isEmpty()) {
					sendBookToServer(true);
					mc.displayGuiScreen(null);
				}

				return;
			default:
				if(bookTitle.length() < 30 && ChatAllowedCharacters.isAllowedCharacter(p_146460_1_)) {
					bookTitle = bookTitle + "§k" +Character.toString(p_146460_1_);
					updateButtons();
					field_146481_r = true;
				}
		}
	}

	private String func_146456_p() {
		return bookPages != null && currPage >= 0 && currPage < bookPages.tagCount() ? bookPages.getStringTagAt(currPage) : "";
	}

	private void func_146457_a(String p_146457_1_) {
		if(bookPages != null && currPage >= 0 && currPage < bookPages.tagCount()) {
			bookPages.func_150304_a(currPage, new NBTTagString(p_146457_1_));
			field_146481_r = true;
		}
	}

	private void func_146459_b(String p_146459_1_) {
		String s1 = func_146456_p();
		String s2 = s1 + p_146459_1_;
		int i = fontRendererObj.splitStringWidth(s2 + "" + EnumChatFormatting.BLACK + "_", 118);

		if(i <= 118 && s2.length() < 256) {
			func_146457_a(s2);
		}
	}

	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(BOOK_GUI_TEXTURES);
		int k = (width - BOOK_IMAGE_WIDTH) / 2;
		byte b0 = 2;
		drawTexturedModalRect(k, b0, 0, 0, BOOK_IMAGE_WIDTH, BOOK_IMAGE_HEIGHT);
		String s;
		String s1 = "Secret spell for Grimoire";
		int l;

		if(field_146480_s) {
			s = bookTitle;

			if(bookIsUnsigned) {
				if(updateCount / 6 % 2 == 0) {
					s = s + "" + EnumChatFormatting.DARK_PURPLE + "_";
				}
				else {
					s = s + "" + EnumChatFormatting.DARK_PURPLE + "_";
				}
			}
			l = fontRendererObj.getStringWidth(s1);
			fontRendererObj.drawString(s1, k + 36 + (116 - l) / 2, b0 + 16 + 16, 0);
			int i1 = fontRendererObj.getStringWidth(s);
			fontRendererObj.drawString(s, k + 36 + (116 - i1) / 2, b0 + 48, 0);
			String s2 = I18n.format("Grimoire of " + editingPlayer.getCommandSenderName());
			int j1 = fontRendererObj.getStringWidth(s2);
			fontRendererObj.drawString(EnumChatFormatting.DARK_RED + s2, k + 36 + (116 - j1) / 2, b0 + 48 + 10, 0);
			String s3 = I18n.format("This book must be sealed to prevent someone else from reading the scriptures");
			fontRendererObj.drawSplitString(s3, k + 36, b0 + 80, 116, 0);
		}
		else {
			s = I18n.format("book.pageIndicator", currPage + 1, bookTotalPages);
			s1 = "";

			if(bookPages != null && currPage >= 0 && currPage < bookPages.tagCount()) {
				s1 = bookPages.getStringTagAt(currPage);
			}

			if(bookIsUnsigned) {
				if(fontRendererObj.getBidiFlag()) {
					s1 = s1 + "_";
				}
				else if(updateCount / 6 % 2 == 0) {
					s1 = s1 + "" + EnumChatFormatting.DARK_PURPLE + "_";
				}
				else {
					s1 = s1 + "" + EnumChatFormatting.DARK_PURPLE + "_";
				}
			}

			l = fontRendererObj.getStringWidth(s);
			fontRendererObj.drawString(s, k - l + BOOK_IMAGE_WIDTH - 44, b0 + 16, 0);
			fontRendererObj.drawSplitString(s1, k + 36, b0 + 16 + 16, 116, 0);
		}

		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
	}

	@SideOnly(Side.CLIENT)
	private static class NextPageButton extends GuiButton {

		private final boolean field_146151_o;

		public NextPageButton(int id, int xPos, int yPos, boolean p_i1079_4_) {
			super(id, xPos, yPos, 23, 13, "");
			field_146151_o = p_i1079_4_;
		}

		@Override
		public void drawButton(Minecraft minecraft, int x, int y) {
			if(visible) {
				boolean flag = x >= xPosition && y >= yPosition && x < xPosition + width
						&& y < yPosition + height;
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				minecraft.getTextureManager().bindTexture(GuiScreenYoukaiBook.BOOK_GUI_TEXTURES);
				int k = 0;
				int l = 192;

				if(flag) {
					k += 23;
				}

				if(!field_146151_o) {
					l += 13;
				}

				drawTexturedModalRect(xPosition, yPosition, k, l, 23, 13);
			}
		}
	}
}
