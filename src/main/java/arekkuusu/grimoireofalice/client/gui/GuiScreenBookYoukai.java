/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.gui;

import java.io.IOException;
import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.Lists;
import com.google.gson.JsonParseException;

import arekkuusu.grimoireofalice.lib.LibMod;
import io.netty.buffer.Unpooled;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWrittenBook;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiScreenBookYoukai extends GuiScreen {

	private static final ResourceLocation BOOK_GUI_TEXTURES = new ResourceLocation(LibMod.MODID, "textures/gui/YoukaiBook.png");
	/** The player editing the book */
	private final EntityPlayer editingPlayer;
	private final ItemStack bookObj;
	/** Whether the book is signed or can still be edited */
	private final boolean bookIsUnsigned;
	/** Whether the book's title or contents has been modified since being opened */
	private boolean bookIsModified;
	/** Determines if the signing screen is open */
	private boolean bookGettingSigned;
	/** Update ticks since the gui was opened */
	private int updateCount;
	private int bookTotalPages = 1;
	private int currPage;
	private NBTTagList bookPages;
	private String bookTitle = "§kS§kp";
	private List<ITextComponent> cachedComponents;
	private int cachedPage = -1;
	private GuiButton buttonDone;
	/** The GuiButton to sign this book. */
	private GuiButton buttonSign;
	private GuiButton buttonFinalize;
	private GuiButton buttonCancel;

	public GuiScreenBookYoukai(EntityPlayer player, ItemStack book, boolean isUnsigned) {
		editingPlayer = player;
		bookObj = book;
		bookIsUnsigned = isUnsigned;

		if(book.hasTagCompound()) {
			NBTTagCompound nbttagcompound = book.getTagCompound();
			//noinspection ConstantConditions
			bookPages = nbttagcompound.getTagList("pages", 8);

			bookPages = bookPages.copy();
			bookTotalPages = bookPages.tagCount();

			if(bookTotalPages < 1) {
				bookTotalPages = 1;
			}
		}

		if(bookPages == null && isUnsigned) {
			bookPages = new NBTTagList();
			bookPages.appendTag(new NBTTagString(""));
			bookTotalPages = 1;
		}
	}

	/**
	 * Called from the main game loop to update the screen.
	 */
	@Override
	public void updateScreen() {
		super.updateScreen();
		++updateCount;
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question. Called when the GUI is
	 * displayed and when the window resizes, the buttonList is cleared beforehand.
	 */
	@Override
	public void initGui() {
		buttonList.clear();
		Keyboard.enableRepeatEvents(true);
		if(bookIsUnsigned) {
			buttonSign = this.addButton(new GuiButton(3, width / 2 - 100, 196, 98, 20, I18n.format("book.signButton")));
			buttonDone = this.addButton(new GuiButton(0, width / 2 + 2, 196, 98, 20, I18n.format("gui.done")));
			buttonFinalize = this.addButton(new GuiButton(5, width / 2 - 100, 196, 98, 20, I18n.format("book.finalizeButton")));
			buttonCancel = this.addButton(new GuiButton(4, width / 2 + 2, 196, 98, 20, I18n.format("gui.cancel")));
		}
		else {
			buttonDone = this.addButton(new GuiButton(0, width / 2 - 100, 196, 200, 20, I18n.format("gui.done")));
		}

		updateButtons();
	}

	/**
	 * Called when the screen is unloaded. Used to disable keyboard repeat events
	 */
	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	private void updateButtons() {
		buttonDone.visible = !bookIsUnsigned || !bookGettingSigned;

		if(bookIsUnsigned) {
			buttonSign.visible = !bookGettingSigned;
			buttonCancel.visible = bookGettingSigned;
			buttonFinalize.visible = bookGettingSigned;
			buttonFinalize.enabled = bookTitle.trim().length() > 6;
		}
	}

	private void sendBookToServer(boolean publish) throws IOException {
		if(bookIsUnsigned && bookIsModified) {
			if(bookPages != null) {
				while(bookPages.tagCount() > 1) {
					String s = bookPages.getStringTagAt(bookPages.tagCount() - 1);

					if(!s.isEmpty()) {
						break;
					}

					bookPages.removeTag(bookPages.tagCount() - 1);
				}

				if(bookObj.hasTagCompound()) {
					NBTTagCompound nbttagcompound = bookObj.getTagCompound();
					//noinspection ConstantConditions
					nbttagcompound.setTag("pages", bookPages);
				}
				else {
					bookObj.setTagInfo("pages", bookPages);
				}

				String s1 = "MC|BEdit";

				if(publish) {
					s1 = "MC|BSign";
					bookObj.setTagInfo("author", new NBTTagString(editingPlayer.getName()));
					bookObj.setTagInfo("title", new NBTTagString(bookTitle.trim().replace("§k", "").substring(2)));
				}

				//TODO: Need to handle updates separately
				PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
				packetbuffer.writeItemStackToBuffer(bookObj);
				mc.getConnection().sendPacket(new CPacketCustomPayload(s1, packetbuffer));
			}
		}
	}

	/**
	 * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
	 */
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if(button.enabled) {
			switch(button.id) {
				case 0:
					mc.displayGuiScreen(null);
					sendBookToServer(false);
					break;
				case 1:
					if(currPage < bookTotalPages - 1) {
						++currPage;
					}
					else if(bookIsUnsigned) {
						addNewPage();

						if(currPage < bookTotalPages - 1) {
							++currPage;
						}
					}
					break;
				case 2:
					if(currPage > 0) {
						--currPage;
					}
					break;
				case 3:
					if(bookIsUnsigned) {
						bookGettingSigned = true;
					}
					break;
				case 4:
					if(bookGettingSigned) {
						bookGettingSigned = false;
					}
					break;
				case 5:
					if(bookGettingSigned) {
						sendBookToServer(true);
						mc.displayGuiScreen(null);
					}
					break;
				default:
					break;
			}

			updateButtons();
		}
	}

	private void addNewPage() {
		if(bookPages != null && bookPages.tagCount() < 50) {
			bookPages.appendTag(new NBTTagString(""));
			++bookTotalPages;
			bookIsModified = true;
		}
	}

	/**
	 * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of
	 * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl
	 * Keyboard key code)
	 */
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);

		if(bookIsUnsigned) {
			if(bookGettingSigned) {
				keyTypedInTitle(typedChar, keyCode);
			}
			else {
				keyTypedInBook(typedChar, keyCode);
			}
		}
	}

	/**
	 * Processes keystrokes when editing the text of a book
	 */
	private void keyTypedInBook(char typedChar, int keyCode) {
		if(GuiScreen.isKeyComboCtrlV(keyCode)) {
			pageInsertIntoCurrent(GuiScreen.getClipboardString());
		}
		else {
			switch(keyCode) {
				case 14:
					String s = pageGetCurrent();

					if(!s.isEmpty()) {
						pageSetCurrent(s.substring(0, s.length() - 3));
					}

					return;
				case 28:
				case 156:
					pageInsertIntoCurrent("\n");
					return;
				default:

					if(ChatAllowedCharacters.isAllowedCharacter(typedChar)) {
						pageInsertIntoCurrent("§k" + Character.toString(typedChar));
					}
			}
		}
	}

	/**
	 * Processes keystrokes when editing the title of a book
	 */
	private void keyTypedInTitle(char p_146460_1_, int p_146460_2_) throws IOException {
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

				if(bookTitle.length() < 36 && ChatAllowedCharacters.isAllowedCharacter(p_146460_1_)) {
					bookTitle = bookTitle + "§k" + Character.toString(p_146460_1_);
					updateButtons();
					bookIsModified = true;
				}
		}
	}

	/**
	 * Returns the entire text of the current page as determined by currPage
	 */
	private String pageGetCurrent() {
		return bookPages != null && currPage >= 0 && currPage < bookPages.tagCount() ? bookPages.getStringTagAt(currPage) : "";
	}

	/**
	 * Sets the text of the current page as determined by currPage
	 */
	private void pageSetCurrent(String p_146457_1_) {
		if(bookPages != null && currPage >= 0 && currPage < bookPages.tagCount()) {
			bookPages.set(currPage, new NBTTagString(p_146457_1_));
			bookIsModified = true;
		}
	}

	/**
	 * Processes any text getting inserted into the current page, enforcing the page size limit
	 */
	private void pageInsertIntoCurrent(String p_146459_1_) {
		String s = pageGetCurrent();
		String s1 = s + p_146459_1_;
		int i = fontRendererObj.splitStringWidth(s1 + "" + TextFormatting.BLACK + "_", 118);

		if(i <= 128 && s1.length() < 256) {
			pageSetCurrent(s1);
		}
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(BOOK_GUI_TEXTURES);
		int i = (width - 192) / 2;
		this.drawTexturedModalRect(i, 2, 0, 0, 192, 192);

		if(bookGettingSigned) {
			String s = bookTitle;

			if(bookIsUnsigned) {
				if(updateCount / 6 % 2 == 0) {
					s = s + "" + TextFormatting.DARK_PURPLE + "_";
				}
				else {
					s = s + "" + TextFormatting.LIGHT_PURPLE + "_";
				}
			}

			String s1 = I18n.format("book.editTitle");
			int k = fontRendererObj.getStringWidth(s1);
			fontRendererObj.drawString(s1, i + 36 + (116 - k) / 2, 34, 0);
			int l = fontRendererObj.getStringWidth(s);
			fontRendererObj.drawString(s, i + 36 + (116 - l) / 2, 50, 0);
			String s2 = I18n.format("grimoire.gui.youkaibook_header.name", editingPlayer.getName());
			int i1 = fontRendererObj.getStringWidth(s2);
			fontRendererObj.drawString(TextFormatting.DARK_RED + s2, i + 36 + (116 - i1) / 2, 60, 0);
			String s3 = I18n.format("grimoire.gui.youkaibook_description.name");
			fontRendererObj.drawSplitString(s3, i + 36, 82, 116, 0);
		}
		else {
			String s4 = I18n.format("book.pageIndicator", currPage + 1, bookTotalPages);
			String s5 = "";

			if(bookPages != null && currPage >= 0 && currPage < bookPages.tagCount()) {
				s5 = bookPages.getStringTagAt(currPage);
			}

			if(bookIsUnsigned) {
				if(fontRendererObj.getBidiFlag()) {
					s5 = s5 + "_";
				}
				else if(updateCount / 6 % 2 == 0) {
					s5 = s5 + "" + TextFormatting.DARK_PURPLE + "_";
				}
				else {
					s5 = s5 + "" + TextFormatting.LIGHT_PURPLE + "_";
				}
			}
			else if(cachedPage != currPage) {
				if(ItemWrittenBook.validBookTagContents(bookObj.getTagCompound())) {
					try {
						ITextComponent itextcomponent = ITextComponent.Serializer.jsonToComponent(s5);
						cachedComponents = itextcomponent != null ? GuiUtilRenderComponents.splitText(itextcomponent, 116, fontRendererObj, true,
								true) : null;
					}
					catch(JsonParseException var13) {
						cachedComponents = null;
					}
				}
				else {
					TextComponentString textcomponentstring = new TextComponentString(TextFormatting.DARK_RED + "* Invalid book tag *");
					cachedComponents = Lists.newArrayList(textcomponentstring);
				}

				cachedPage = currPage;
			}

			int j1 = fontRendererObj.getStringWidth(s4);
			fontRendererObj.drawString(s4, i - j1 + 192 - 44, 18, 0);

			if(cachedComponents == null) {
				fontRendererObj.drawSplitString(s5, i + 36, 34, 116, 0);
			}
			else {
				int k1 = Math.min(128 / fontRendererObj.FONT_HEIGHT, cachedComponents.size());

				for(int l1 = 0; l1 < k1; ++l1) {
					ITextComponent itextcomponent2 = cachedComponents.get(l1);
					fontRendererObj.drawString(itextcomponent2.getUnformattedText(), i + 36, 34 + l1 * fontRendererObj.FONT_HEIGHT, 0);
				}

				ITextComponent itextcomponent1 = getClickedComponentAt(mouseX, mouseY);

				if(itextcomponent1 != null) {
					handleComponentHover(itextcomponent1, mouseX, mouseY);
				}
			}
		}

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	/**
	 * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
	 */
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		if(mouseButton == 0) {
			ITextComponent itextcomponent = getClickedComponentAt(mouseX, mouseY);

			if(itextcomponent != null && handleComponentClick(itextcomponent)) return;
		}

		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	/**
	 * Executes the click event specified by the given chat component
	 */
	@Override
	protected boolean handleComponentClick(ITextComponent component) {
		ClickEvent clickevent = component.getStyle().getClickEvent();

		if(clickevent == null) return false;
		else if(clickevent.getAction() == ClickEvent.Action.CHANGE_PAGE) {
			String s = clickevent.getValue();

			try {
				int i = Integer.parseInt(s) - 1;

				if(i >= 0 && i < bookTotalPages && i != currPage) {
					currPage = i;
					updateButtons();
					return true;
				}
			}
			catch(Throwable ignored) {}

			return false;
		}
		else {
			boolean flag = super.handleComponentClick(component);

			if(flag && clickevent.getAction() == ClickEvent.Action.RUN_COMMAND) {
				mc.displayGuiScreen(null);
			}

			return flag;
		}
	}

	@Nullable
	public ITextComponent getClickedComponentAt(int p_175385_1_, int p_175385_2_) {
		if(cachedComponents == null) return null;
		else {
			int i = p_175385_1_ - (width - 192) / 2 - 36;
			int j = p_175385_2_ - 2 - 16 - 16;

			if(i >= 0 && j >= 0) {
				int k = Math.min(128 / fontRendererObj.FONT_HEIGHT, cachedComponents.size());

				if(i <= 116 && j < mc.fontRendererObj.FONT_HEIGHT * k + k) {
					int l = j / mc.fontRendererObj.FONT_HEIGHT;

					if(l >= 0 && l < cachedComponents.size()) {
						ITextComponent itextcomponent = cachedComponents.get(l);
						int i1 = 0;

						for(ITextComponent itextcomponent1 : itextcomponent) {
							if(itextcomponent1 instanceof TextComponentString) {
								i1 += mc.fontRendererObj.getStringWidth(((TextComponentString)itextcomponent1).getText());

								if(i1 > i) return itextcomponent1;
							}
						}
					}

					return null;
				}
				else return null;
			}
			else return null;
		}
	}
}
