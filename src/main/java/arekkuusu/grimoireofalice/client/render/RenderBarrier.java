package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.util.ShaderLibrary;
import arekkuusu.grimoireofalice.client.render.model.ModelFlat;
import arekkuusu.grimoireofalice.client.util.ResourceLibrary;
import arekkuusu.grimoireofalice.common.entity.EntityBarrier;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
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

	public RenderBarrier(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityBarrier entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.disableLighting();
		ShaderLibrary.BRIGHT.begin();
		ShaderLibrary.BRIGHT.getUniformJ("brightness").ifPresent(b -> {
			b.set(0F);
			b.upload();
		});
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		float colorRev = 3.141593F * 5.6125F / 90F;
		float color = entity.ticksExisted * colorRev;
		GlStateManager.color(0.2F + 0.8F * MathHelper.sin(color), 0, 0, 0.90F);
		bindEntityTexture(entity);
		GlStateManager.translate(x, y, z);
		GlStateManager.scale(2F, 2F, 2F);
		GlStateManager.rotate(90 - entity.rotationYaw, 0F, 1F, 0F);
		GlStateManager.rotate(entity.rotationPitch + 90F, 0F, 0F, 1F);
		GlStateManager.rotate(entity.ticksExisted * 4F, 0.0F, 1.0F, 0.0F);
		MODEL.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		ShaderLibrary.BRIGHT.end();
		GlStateManager.enableLighting();
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityBarrier entityBarrier) {
		return ResourceLibrary.BARRIER_TEXTURE;
	}
}
