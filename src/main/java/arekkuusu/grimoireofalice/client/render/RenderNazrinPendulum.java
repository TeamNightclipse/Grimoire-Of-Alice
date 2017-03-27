/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.render;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import arekkuusu.grimoireofalice.client.ResourceLocations;
import arekkuusu.grimoireofalice.client.model.ModelNazrinPendulum;
import arekkuusu.grimoireofalice.common.entity.EntityNazrinPendulum;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderNazrinPendulum extends Render<EntityNazrinPendulum> {

	private static final ModelBase MODEL = new ModelNazrinPendulum();

	public RenderNazrinPendulum(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityNazrinPendulum pendulum, double x, double y, double z, float yaw, float pitch) {
		GlStateManager.pushMatrix();
		bindEntityTexture(pendulum);
		GlStateManager.translate(x, y, z);

		float maxUpAndDown = 0.05F;
		float speed = 2;
		float angle = 0;

		float toDegrees = (float) Math.PI / 180F;
		angle += speed * pendulum.ticksExisted;
		if (angle > 360) angle -= 360;

		GlStateManager.translate(0, maxUpAndDown * Math.sin(angle * toDegrees), 0);

		GlStateManager.scale(0.15, 0.15, 0.15);
		GlStateManager.rotate(180F, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(pendulum.ticksExisted * 8, 0.0F, 1.0F, 0.0F);
		GlStateManager.disableLighting();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
		MODEL.render(pendulum, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.enableLighting();
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityNazrinPendulum entity) {
		return ResourceLocations.NAZRIN_TEXTURE;
	}
}
