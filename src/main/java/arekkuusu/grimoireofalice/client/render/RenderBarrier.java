package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.model.ModelFlatTexture;
import arekkuusu.grimoireofalice.entity.EntityBarrier;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBarrier extends Render<EntityBarrier> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(LibMod.MODID, "textures/models/entities/Barrier.png");
	private static final ModelBase MODEL = new ModelFlatTexture();

	public RenderBarrier(RenderManager p_i46179_1_) {
		super(p_i46179_1_);
	}

	@Override
	public void doRender(EntityBarrier entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		bindEntityTexture(entity);
		GlStateManager.translate(x,y,z);
		GlStateManager.scale(2,2,2);

		GlStateManager.rotate(90 - entity.rotationYaw,0.0F,1.0F,0.0F);
		GlStateManager.rotate(entity.rotationPitch + 90,0.0F,0.0F,1.0F);

		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.rotate(entity.getTicksAlive() * 4, 0.0F, 1.0F, 0.0F);
		MODEL.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();

		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.rotate(-entity.getTicksAlive() * 4, 0.0F, 1.0F, 0.0F);
		MODEL.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();

		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityBarrier entityBarrier) {
		return TEXTURE;
	}
}
