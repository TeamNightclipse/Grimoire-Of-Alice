package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.ResourceLocations;
import arekkuusu.grimoireofalice.client.model.ModelUnzanFist;
import arekkuusu.grimoireofalice.common.entity.EntityUnzanFist;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderUnzanFist extends Render<EntityUnzanFist> {

	private static final ModelBase MODEL = new ModelUnzanFist();

	public RenderUnzanFist(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityUnzanFist entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		bindEntityTexture(entity);
		GlStateManager.translate(x, y, z);
		GlStateManager.scale(1.5, 1.5, 1.5);
		GlStateManager.rotate(90 - entity.rotationYaw, 0F, 1F, 0F);
		GlStateManager.rotate(entity.rotationPitch + 90F, 0F, 0F, 1F);
		MODEL.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityUnzanFist entity) {
		return ResourceLocations.UNZAN_FIST_TEXTURE;
	}
}
