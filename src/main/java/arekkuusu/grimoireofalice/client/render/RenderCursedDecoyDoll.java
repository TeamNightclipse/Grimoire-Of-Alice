package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.model.ModelCursedDecoyDoll;
import arekkuusu.grimoireofalice.entity.EntityCursedDecoyDoll;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;


public class RenderCursedDecoyDoll extends Render<EntityCursedDecoyDoll> {

	private static final ResourceLocation DOLL_TEXTURE = new ResourceLocation(LibMod.MODID, "textures/models/entities/ModelCursedDecoyDoll-texturemap.png");
	private static final ModelCursedDecoyDoll MODEL = new ModelCursedDecoyDoll();

	public RenderCursedDecoyDoll(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityCursedDecoyDoll entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.disableLighting();
		bindEntityTexture(entity);
		GlStateManager.translate(x,y + 1,z);
		GlStateManager.scale(0.5,0.5,0.5);
		GlStateManager.rotate(180,1.0F,0.0F,0.0F);
		GlStateManager.rotate(entity.rotationYaw,0.0F,1.0F,0.0F);
		MODEL.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.enableLighting();
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCursedDecoyDoll entity) {
		return DOLL_TEXTURE;
	}
}
