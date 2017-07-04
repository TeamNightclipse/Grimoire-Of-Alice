package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.ResourceLocations;
import arekkuusu.grimoireofalice.client.model.ModelFlat;
import arekkuusu.grimoireofalice.common.entity.EntityBarrier;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderBarrier extends Render<EntityBarrier> {

	private static final ModelBase MODEL = new ModelFlat();

	public RenderBarrier(RenderManager p_i46179_1_) {
		super(p_i46179_1_);
	}

	@Override
	public void doRender(EntityBarrier entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
		GlStateManager.disableLighting();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		float colorRev = 3.141593F * 5.6125F / 180F;
		float color = entity.ticksExisted * colorRev;

		GlStateManager.color(1 + MathHelper.sin(color), 3 + MathHelper.cos(color), 5 + MathHelper.sin(color), 0.90F);

		bindEntityTexture(entity);
		GlStateManager.translate(x, y, z);
		GlStateManager.scale(2F, 2F, 2F);
		GlStateManager.rotate(90 - entity.rotationYaw, 0F, 1F, 0F);
		GlStateManager.rotate(entity.rotationPitch + 90F, 0F, 0F, 1F);
		GlStateManager.rotate(entity.ticksExisted * 4, 0.0F, 1.0F, 0.0F);
		MODEL.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

		GlStateManager.disableBlend();
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityBarrier entityBarrier) {
		return ResourceLocations.BARRIER_TEXTURE;
	}
}
