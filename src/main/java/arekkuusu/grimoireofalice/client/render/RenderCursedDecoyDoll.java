package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.ResourceLocations;
import arekkuusu.grimoireofalice.client.model.ModelDoll;
import arekkuusu.grimoireofalice.common.entity.EntityCursedDecoyDoll;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCursedDecoyDoll extends Render<EntityCursedDecoyDoll> {

	private static final ModelDoll MODEL = new ModelDoll();

	public RenderCursedDecoyDoll(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityCursedDecoyDoll entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		bindEntityTexture(entity);
		GlStateManager.translate(x - 0.1, y + 0.7, z - 0.1);
		GlStateManager.rotate(180F, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(entity.rotationYaw - 180, 0.0F, 1.0F, 0.0F);
		MODEL.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCursedDecoyDoll entity) {
		return ResourceLocations.DOLL_TEXTURE;
	}
}
