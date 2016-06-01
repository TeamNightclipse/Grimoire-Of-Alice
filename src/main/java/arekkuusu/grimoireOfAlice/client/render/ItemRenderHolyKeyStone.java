/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.client.render;

import org.lwjgl.opengl.GL11;

import arekkuusu.grimoireOfAlice.client.model.ModelHolyKeyStone;
import arekkuusu.grimoireOfAlice.client.tile.TileEntityHolyKeyStone;
import arekkuusu.grimoireOfAlice.lib.LibMod;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class ItemRenderHolyKeyStone implements IItemRenderer {

	private ModelBase MODEL;
	private static final ResourceLocation TEXTURE = new ResourceLocation(LibMod.MODID, "textures/models/HolyKeyStone.png");

	public ItemRenderHolyKeyStone() {
		MODEL = new ModelHolyKeyStone();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch(type) {
			case EQUIPPED:
			case EQUIPPED_FIRST_PERSON:
			case ENTITY:
			case INVENTORY:
				return true;
			default:
				return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		switch(type) {
			case ENTITY: {
				return false;
			}
			case EQUIPPED: {
				return false;
			}
			case EQUIPPED_FIRST_PERSON: {
				return false;
			}
			case INVENTORY: {
				return helper == ItemRendererHelper.INVENTORY_BLOCK;
			}
			default: {
				return false;
			}
		}
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glPushMatrix();
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		float s = 1F;
		GL11.glScalef(s, s, s);
		GL11.glRotatef(-5F, 1F, 0F, 0F);
		TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityHolyKeyStone(), 0.0D, 0.0D, 0.0D, 0.0F);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}
}
