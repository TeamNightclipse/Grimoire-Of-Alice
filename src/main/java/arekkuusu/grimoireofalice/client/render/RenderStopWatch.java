package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.ResourceLocations;
import arekkuusu.grimoireofalice.client.model.ModelStopWatch;
import arekkuusu.grimoireofalice.common.entity.EntityStopWatch;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderStopWatch extends Render<EntityStopWatch> {

	private static final ModelStopWatch MODEL = new ModelStopWatch();

	public RenderStopWatch(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityStopWatch entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		bindEntityTexture(entity);
		GlStateManager.translate(x, y + 0.4, z);
		GlStateManager.scale(0.25, 0.25, 0.25);
		GlStateManager.rotate(180F, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(entity.ticksExisted * 8, 0.0F, 1.0F, 0.0F);
		MODEL.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityStopWatch entity) {
		return ResourceLocations.WATCH_TEXTURE;
	}
}
