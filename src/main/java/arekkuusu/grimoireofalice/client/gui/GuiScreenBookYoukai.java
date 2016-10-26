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
import net.minecraft.client.Minecraft;
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
		this.editingPlayer = player;
		this.bookObj = book;
		this.bookIsUnsigned = isUnsigned;

		if (book.hasTagCompound()) {
			NBTTagCompound nbttagcompound = book.getTagCompound();
			//noinspection ConstantConditions
			this.bookPages = nbttagcompound.getTagList("pages", 8);

			this.bookPages = this.bookPages.copy();
			this.bookTotalPages = this.bookPages.tagCount();

			if (this.bookTotalPages < 1) {
				this.bookTotalPages = 1;
			}
		}

		if (this.bookPages == null && isUnsigned) {
			this.bookPages = new NBTTagList();
			this.bookPages.appendTag(new NBTTagString(""));
			this.bookTotalPages = 1;
		}
	}

	/**
	 * Called from the main game loop to update the screen.
	 */
	@Override
	public void updateScreen() {
		super.updateScreen();
		++this.updateCount;
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
	 * window resizes, the buttonList is cleared beforehand.
	 */
	@Override
	public void initGui() {
		this.buttonList.clear();
		Keyboard.enableRepeatEvents(true);
		if (this.bookIsUnsigned) {
			this.buttonSign = this.addButton(new GuiButton(3, this.width / 2 - 100, 196, 98, 20, I18n.format("book.signButton")));
			this.buttonDone = this.addButton(new GuiButton(0, this.width / 2 + 2, 196, 98, 20, I18n.format("gui.done")));
			this.buttonFinalize = this.addButton(
					new GuiButton(5, this.width / 2 - 100, 196, 98, 20, I18n.format("book.finalizeButton")));
			this.buttonCancel = this.addButton(new GuiButton(4, this.width / 2 + 2, 196, 98, 20, I18n.format("gui.cancel")));
		} else {
			this.buttonDone = this.addButton(new GuiButton(0, this.width / 2 - 100, 196, 200, 20, I18n.format("gui.done")));
		}

		this.updateButtons();
	}

	/**
	 * Called when the screen is unloaded. Used to disable keyboard repeat events
	 */
	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	private void updateButtons() {
		this.buttonDone.visible = !this.bookIsUnsigned || !this.bookGettingSigned;

		if(this.bookIsUnsigned) {
			this.buttonSign.visible = !this.bookGettingSigned;
			this.buttonCancel.visible = this.bookGettingSigned;
			this.buttonFinalize.visible = this.bookGettingSigned;
			this.buttonFinalize.enabled = bookTitle.trim().length() > 6;
		}
	}

	private void sendBookToServer(boolean publish) throws IOException {
		if(this.bookIsUnsigned && this.bookIsModified) {
			if(this.bookPages != null) {
				while(this.bookPages.tagCount() > 1) {
					String s = this.bookPages.getStringTagAt(this.bookPages.tagCount() - 1);

					if(!s.isEmpty()) {
						break;
					}

					this.bookPages.removeTag(this.bookPages.tagCount() - 1);
				}

				if(this.bookObj.hasTagCompound()) {
					NBTTagCompound nbttagcompound = this.bookObj.getTagCompound();
					//noinspection ConstantConditions
					nbttagcompound.setTag("pages", this.bookPages);
				}
				else {
					this.bookObj.setTagInfo("pages", this.bookPages);
				}

				String s1 = "MC|BEdit";

				if(publish) {
					s1 = "MC|BSign";
					this.bookObj.setTagInfo("author", new NBTTagString(this.editingPlayer.getName()));
					this.bookObj.setTagInfo("title", new NBTTagString(bookTitle.trim().replace("§k", "").substring(2)));
				}

				//TODO: Need to handle updates separately
				PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
				packetbuffer.writeItemStackToBuffer(this.bookObj);
				this.mc.getConnection().sendPacket(new CPacketCustomPayload(s1, packetbuffer));
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
					this.mc.displayGuiScreen(null);
					this.sendBookToServer(false);
					break;
				case 1:
					if(this.currPage < this.bookTotalPages - 1) {
						++this.currPage;
					}
					else if(this.bookIsUnsigned) {
						this.addNewPage();

						if(this.currPage < this.bookTotalPages - 1) {
							++this.currPage;
						}
					}
					break;
				case 2:
					if(this.currPage > 0) {
						--this.currPage;
					}
					break;
				case 3:
					if(bookIsUnsigned) {
						this.bookGettingSigned = true;
					}
					break;
				case 4:
					if(bookGettingSigned) {
						this.bookGettingSigned = false;
					}
					break;
				case 5:
					if(bookGettingSigned) {
						this.sendBookToServer(true);
						this.mc.displayGuiScreen(null);
					}
					break;
				default:
					break;
			}

			this.updateButtons();
		}
	}

	private void addNewPage() {
		if(this.bookPages != null && this.bookPages.tagCount() < 50) {
			this.bookPages.appendTag(new NBTTagString(""));
			++this.bookTotalPages;
			this.bookIsModified = true;
		}
	}

	/**
	 * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of
	 * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
	 */
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);

		if(this.bookIsUnsigned) {
			if(this.bookGettingSigned) {
				this.keyTypedInTitle(typedChar, keyCode);
			}
			else {
				this.keyTypedInBook(typedChar, keyCode);
			}
		}
	}

	/**
	 * Processes keystrokes when editing the text of a book
	 */
	private void keyTypedInBook(char typedChar, int keyCode) {
		if(GuiScreen.isKeyComboCtrlV(keyCode)) {
			this.pageInsertIntoCurrent(GuiScreen.getClipboardString());
		}
		else {
			switch(keyCode) {
				case 14:
					String s = this.pageGetCurrent();

					if(!s.isEmpty()) {
						this.pageSetCurrent(s.substring(0, s.length() - 3));
					}

					return;
				case 28:
				case 156:
					this.pageInsertIntoCurrent("\n");
					return;
				default:

					if(ChatAllowedCharacters.isAllowedCharacter(typedChar)) {
						this.pageInsertIntoCurrent("§k" + Character.toString(typedChar));
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

				if(!this.bookTitle.isEmpty()) {
					this.bookTitle = this.bookTitle.substring(0, this.bookTitle.length() - 3);
					this.updateButtons();
				}

				return;
			case 28:
			case 156:

				if(!this.bookTitle.isEmpty()) {
					this.sendBookToServer(true);
					this.mc.displayGuiScreen(null);
				}

				return;
			default:

				if(this.bookTitle.length() < 36 && ChatAllowedCharacters.isAllowedCharacter(p_146460_1_)) {
					this.bookTitle = this.bookTitle + "§k" + Character.toString(p_146460_1_);
					this.updateButtons();
					this.bookIsModified = true;
				}
		}
	}

	/**
	 * Returns the entire text of the current page as determined by currPage
	 */
	private String pageGetCurrent() {
		return this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.tagCount() ? this.bookPages.getStringTagAt(
				this.currPage) : "";
	}

	/**
	 * Sets the text of the current page as determined by currPage
	 */
	private void pageSetCurrent(String p_146457_1_) {
		if(this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.tagCount()) {
			this.bookPages.set(this.currPage, new NBTTagString(p_146457_1_));
			this.bookIsModified = true;
		}
	}

	/**
	 * Processes any text getting inserted into the current page, enforcing the page size limit
	 */
	private void pageInsertIntoCurrent(String p_146459_1_) {
		String s = this.pageGetCurrent();
		String s1 = s + p_146459_1_;
		int i = this.fontRendererObj.splitStringWidth(s1 + "" + TextFormatting.BLACK + "_", 118);

		if(i <= 128 && s1.length() < 256) {
			this.pageSetCurrent(s1);
		}
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(BOOK_GUI_TEXTURES);
		int i = (this.width - 192) / 2;
		this.drawTexturedModalRect(i, 2, 0, 0, 192, 192);

		if(this.bookGettingSigned) {
			String s = this.bookTitle;

			if(this.bookIsUnsigned) {
				if(this.updateCount / 6 % 2 == 0) {
					s = s + "" + TextFormatting.DARK_PURPLE + "_";
				}
				else {
					s = s + "" + TextFormatting.LIGHT_PURPLE + "_";
				}
			}

			String s1 = I18n.format("book.editTitle");
			int k = this.fontRendererObj.getStringWidth(s1);
			this.fontRendererObj.drawString(s1, i + 36 + (116 - k) / 2, 34, 0);
			int l = this.fontRendererObj.getStringWidth(s);
			this.fontRendererObj.drawString(s, i + 36 + (116 - l) / 2, 50, 0);
			String s2 = I18n.format("Grimoire of ", this.editingPlayer.getName());
			int i1 = this.fontRendererObj.getStringWidth(s2);
			this.fontRendererObj.drawString(TextFormatting.DARK_RED + s2, i + 36 + (116 - i1) / 2, 60, 0);
			String s3 = I18n.format("This book must be sealed to prevent someone else from reading the scriptures"); //TODO: Add to lang file
			this.fontRendererObj.drawSplitString(s3, i + 36, 82, 116, 0);
		}
		else {
			String s4 = I18n.format("book.pageIndicator", this.currPage + 1, this.bookTotalPages);
			String s5 = "";

			if(this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.tagCount()) {
				s5 = this.bookPages.getStringTagAt(this.currPage);
			}

			if(this.bookIsUnsigned) {
				if(this.fontRendererObj.getBidiFlag()) {
					s5 = s5 + "_";
				}
				else if(this.updateCount / 6 % 2 == 0) {
					s5 = s5 + "" + TextFormatting.DARK_PURPLE + "_";
				}
				else {
					s5 = s5 + "" + TextFormatting.LIGHT_PURPLE + "_";
				}
			}
			else if(this.cachedPage != this.currPage) {
				if(ItemWrittenBook.validBookTagContents(this.bookObj.getTagCompound())) {
					try {
						ITextComponent itextcomponent = ITextComponent.Serializer.jsonToComponent(s5);
						this.cachedComponents = itextcomponent != null ? GuiUtilRenderComponents.splitText(itextcomponent, 116, this.fontRendererObj,
								true, true) : null;
					}
					catch(JsonParseException var13) {
						this.cachedComponents = null;
					}
				}
				else {
					TextComponentString textcomponentstring = new TextComponentString(TextFormatting.DARK_RED + "* Invalid book tag *");
					this.cachedComponents = Lists.newArrayList(textcomponentstring);
				}

				this.cachedPage = this.currPage;
			}

			int j1 = this.fontRendererObj.getStringWidth(s4);
			this.fontRendererObj.drawString(s4, i - j1 + 192 - 44, 18, 0);

			if(this.cachedComponents == null) {
				this.fontRendererObj.drawSplitString(s5, i + 36, 34, 116, 0);
			}
			else {
				int k1 = Math.min(128 / this.fontRendererObj.FONT_HEIGHT, this.cachedComponents.size());

				for(int l1 = 0; l1 < k1; ++l1) {
					ITextComponent itextcomponent2 = this.cachedComponents.get(l1);
					this.fontRendererObj.drawString(itextcomponent2.getUnformattedText(), i + 36, 34 + l1 * this.fontRendererObj.FONT_HEIGHT, 0);
				}

				ITextComponent itextcomponent1 = this.getClickedComponentAt(mouseX, mouseY);

				if(itextcomponent1 != null) {
					this.handleComponentHover(itextcomponent1, mouseX, mouseY);
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
			ITextComponent itextcomponent = this.getClickedComponentAt(mouseX, mouseY);

			if(itextcomponent != null && this.handleComponentClick(itextcomponent)) {
				return;
			}
		}

		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	/**
	 * Executes the click event specified by the given chat component
	 */
	@Override
	protected boolean handleComponentClick(ITextComponent component) {
		ClickEvent clickevent = component.getStyle().getClickEvent();

		if(clickevent == null) {
			return false;
		}
		else if(clickevent.getAction() == ClickEvent.Action.CHANGE_PAGE) {
			String s = clickevent.getValue();

			try {
				int i = Integer.parseInt(s) - 1;

				if(i >= 0 && i < this.bookTotalPages && i != this.currPage) {
					this.currPage = i;
					this.updateButtons();
					return true;
				}
			}
			catch(Throwable ignored) {}

			return false;
		}
		else {
			boolean flag = super.handleComponentClick(component);

			if(flag && clickevent.getAction() == ClickEvent.Action.RUN_COMMAND) {
				this.mc.displayGuiScreen(null);
			}

			return flag;
		}
	}

	@Nullable
	public ITextComponent getClickedComponentAt(int p_175385_1_, int p_175385_2_) {
		if(this.cachedComponents == null) {
			return null;
		}
		else {
			int i = p_175385_1_ - (this.width - 192) / 2 - 36;
			int j = p_175385_2_ - 2 - 16 - 16;

			if(i >= 0 && j >= 0) {
				int k = Math.min(128 / this.fontRendererObj.FONT_HEIGHT, this.cachedComponents.size());

				if(i <= 116 && j < this.mc.fontRendererObj.FONT_HEIGHT * k + k) {
					int l = j / this.mc.fontRendererObj.FONT_HEIGHT;

					if(l >= 0 && l < this.cachedComponents.size()) {
						ITextComponent itextcomponent = this.cachedComponents.get(l);
						int i1 = 0;

						for(ITextComponent itextcomponent1 : itextcomponent) {
							if(itextcomponent1 instanceof TextComponentString) {
								i1 += this.mc.fontRendererObj.getStringWidth(((TextComponentString)itextcomponent1).getText());

								if(i1 > i) {
									return itextcomponent1;
								}
							}
						}
					}

					return null;
				}
				else {
					return null;
				}
			}
			else {
				return null;
			}
		}
	}

	private static class NextPageButton extends GuiButton {

		private final boolean isForward;

		NextPageButton(int p_i46316_1_, int p_i46316_2_, int p_i46316_3_, boolean p_i46316_4_) {
			super(p_i46316_1_, p_i46316_2_, p_i46316_3_, 23, 13, "");
			this.isForward = p_i46316_4_;
		}

		/**
		 * Draws this button to the screen.
		 */
		public void drawButton(Minecraft mc, int mouseX, int mouseY) {
			if(this.visible) {
				boolean flag = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width
						&& mouseY < this.yPosition + this.height;
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				mc.getTextureManager().bindTexture(GuiScreenBookYoukai.BOOK_GUI_TEXTURES);
				int i = 0;
				int j = 192;

				if(flag) {
					i += 23;
				}

				if(!this.isForward) {
					j += 13;
				}

				this.drawTexturedModalRect(this.xPosition, this.yPosition, i, j, 23, 13);
			}
		}
	}
}
