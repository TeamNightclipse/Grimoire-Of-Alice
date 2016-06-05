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

import arekkuusu.grimoireOfAlice.tile.TileEntityOnbashira;
import arekkuusu.grimoireOfAlice.tmp.CleanupDone;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

@CleanupDone
public class ItemRenderOnbashira implements IItemRenderer {

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch(type) {
			case INVENTORY:
				return true;
			default:
				return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		switch(type) {
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
		GL11.glTranslatef(0F, -0.7F, -0.5F);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		float s = 0.5F;
		GL11.glScalef(s, s, s);
		GL11.glRotatef(0F, 0F, 0F, 0F);
		TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityOnbashira(), 0.0D, 0.0D, 0.0D, 0.0F);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}
}
