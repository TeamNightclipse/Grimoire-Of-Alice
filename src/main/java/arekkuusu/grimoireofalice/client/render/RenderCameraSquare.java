package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.ResourceLocations;
import arekkuusu.grimoireofalice.client.model.ModelFlat;
import arekkuusu.grimoireofalice.entity.EntityCameraSquare;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderCameraSquare extends Render<EntityCameraSquare> {

	private static final ModelBase MODEL = new ModelFlat();

	public RenderCameraSquare(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityCameraSquare entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		bindEntityTexture(entity);
		GlStateManager.translate(x, y - 0.4, z);
		GlStateManager.disableLighting();
		float size = 2.0F;
		GlStateManager.scale(size, size, size);
		float angle = 90;
		GlStateManager.rotate(180F - renderManager.playerViewY + angle, 0.0F, 1.0F, 0.0F);
		//TODO: Combine these
		GlStateManager.rotate(180F - renderManager.playerViewX, 0.0F, 0.0F, 1.0F);
		GlStateManager.rotate(angle * 5F, 0.0F, 0.0F, 1.0F);
		MODEL.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCameraSquare entity) {
		return ResourceLocations.CAMERA_SQUARE_TEXTURE;
	}
}
