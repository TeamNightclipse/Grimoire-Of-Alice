package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.model.ModelUnzanFist;
import arekkuusu.grimoireofalice.entity.EntityUnzanFist;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderUnzanFist extends Render<EntityUnzanFist> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(LibMod.MODID, "textures/models/entities/UnzanFist.png");
	private static final ModelBase MODEL = new ModelUnzanFist();

	public RenderUnzanFist(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityUnzanFist entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		bindEntityTexture(entity);
		GlStateManager.translate(x, y, z);
		GlStateManager.disableLighting();
		GlStateManager.enableBlend();
		GlStateManager.scale(1.5, 1.5, 1.5);
		GlStateManager.rotate(90 - entity.rotationYaw,0.0F,1.0F,0.0F);
		GlStateManager.rotate(entity.rotationPitch + 90,0.0F,0.0F,1.0F);
		MODEL.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.disableBlend();
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityUnzanFist entity) {
		return TEXTURE;
	}
}
