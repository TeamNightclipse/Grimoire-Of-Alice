package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.ResourceLocations;
import arekkuusu.grimoireofalice.client.model.ModelFlat;
import arekkuusu.grimoireofalice.common.entity.EntityMiracleCircle;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderMiracleCircle extends Render<EntityMiracleCircle> {

	private static final ModelBase MODEL = new ModelFlat();

	public RenderMiracleCircle(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityMiracleCircle entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
		bindEntityTexture(entity);

		GlStateManager.translate(x, y, z);
		GlStateManager.rotate(180 - renderManager.playerViewY, 0.0F, 1.0F, 0.0F);

		if(renderManager.options.thirdPersonView == 2) {
			GlStateManager.rotate(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		}
		else {
			GlStateManager.rotate(-renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		}
		GL11.glRotatef(entity.turnAngle, 0.0F, 0.0F, 1.0F);

		GlStateManager.rotate(90 - entity.rotationYaw, 1F, 0F, 0F);
		MODEL.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

		GlStateManager.disableBlend();
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();

	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMiracleCircle entity) {
		return ResourceLocations.MIRACLE_CIRCLE;
	}
}
