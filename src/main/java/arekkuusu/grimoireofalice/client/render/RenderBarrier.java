package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.ResourceLocations;
import arekkuusu.grimoireofalice.client.model.ModelFlat;
import arekkuusu.grimoireofalice.entity.EntityBarrier;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBarrier extends Render<EntityBarrier> {

	private static final ModelBase MODEL = new ModelFlat();

	public RenderBarrier(RenderManager p_i46179_1_) {
		super(p_i46179_1_);
	}

	@Override
	public void doRender(EntityBarrier entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		bindEntityTexture(entity);
		GlStateManager.translate(x, y, z);
		GlStateManager.scale(2F, 2F, 2F);

		GlStateManager.rotate(90 - entity.rotationYaw, 0F, 1F, 0F);
		GlStateManager.rotate(entity.rotationPitch + 90F, 0F, 0F, 1F);

		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.disableLighting();
		GlStateManager.rotate(entity.ticksExisted * 4, 0.0F, 1.0F, 0.0F);

		MODEL.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

		GlStateManager.enableLighting();
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();

		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.disableLighting();
		GlStateManager.rotate(-entity.ticksExisted * 4, 0.0F, 1.0F, 0.0F);

		MODEL.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

		GlStateManager.enableLighting();
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();

		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityBarrier entityBarrier) {
		return ResourceLocations.BARRIER_TEXTURE;
	}
}
