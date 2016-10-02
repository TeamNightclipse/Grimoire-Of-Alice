/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.entity.EntityNazrinPendulum;
import org.lwjgl.opengl.GL11;

import arekkuusu.grimoireofalice.client.model.ModelNazrinPendulum;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderNazrinPendulum extends Render<EntityNazrinPendulum> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(LibMod.MODID, "textures/models/entities/nazrinpendulum.png");
	private static final ModelBase MODEL = new ModelNazrinPendulum();

	public RenderNazrinPendulum(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityNazrinPendulum pendulum, double x, double y, double z, float yaw, float pitch) {
		GlStateManager.pushMatrix();
		bindEntityTexture(pendulum);
		GlStateManager.translate(x, y, z);
		GlStateManager.scale(0.4, 0.4, 0.4);
		GlStateManager.rotate(180F, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(pendulum.getTicksAlive() * 8, 0.0F, 1.0F, 0.0F);
		GlStateManager.enableLighting();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
		MODEL.render(pendulum, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.disableLighting();
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityNazrinPendulum entity) {
		return TEXTURE;
	}
}
