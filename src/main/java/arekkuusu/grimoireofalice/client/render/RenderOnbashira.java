/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.render;

import org.lwjgl.opengl.GL11;

import arekkuusu.grimoireofalice.client.model.ModelOnbashira;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderOnbashira extends TileEntitySpecialRenderer {

	private static final ResourceLocation TEXTURE = new ResourceLocation(LibMod.MODID, "textures/models/Onbashira.png");
	private static final ModelOnbashira MODEL = new ModelOnbashira();

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
		GL11.glRotatef(180, 0F, 0f, 1f);
		bindTexture(TEXTURE);
		MODEL.render(null, 0F, 0F, 0F, 0F, 0F, 0.0625F);
		GL11.glPopMatrix();
	}
}
