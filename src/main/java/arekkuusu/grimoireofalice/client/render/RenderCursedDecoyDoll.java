package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.ResourceLocations;
import arekkuusu.grimoireofalice.client.model.ModelCursedDecoyDoll;
import arekkuusu.grimoireofalice.common.entity.EntityCursedDecoyDoll;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;


public class RenderCursedDecoyDoll extends Render<EntityCursedDecoyDoll> {

	private static final ModelCursedDecoyDoll MODEL = new ModelCursedDecoyDoll();

	public RenderCursedDecoyDoll(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityCursedDecoyDoll entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		bindEntityTexture(entity);
		GlStateManager.translate(x, y + 0.7, z);
		GlStateManager.scale(0.5, 0.5, 0.5);
		GlStateManager.rotate(180F, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(entity.rotationYaw, 0.0F, 1.0F, 0.0F);
		MODEL.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCursedDecoyDoll entity) {
		return ResourceLocations.DOLL_TEXTURE;
	}
}
