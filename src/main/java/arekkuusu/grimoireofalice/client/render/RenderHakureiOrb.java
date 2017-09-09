package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.render.model.ModelFlat;
import arekkuusu.grimoireofalice.client.util.ResourceLibrary;
import arekkuusu.grimoireofalice.common.entity.EntityHakureiOrb;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderHakureiOrb extends Render<EntityHakureiOrb> {

	private static final ModelBase MODEL = new ModelFlat();

	public RenderHakureiOrb(RenderManager render) {
		super(render);
	}

	@Override
	public void doRender(EntityHakureiOrb entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
		bindEntityTexture(entity);
		GlStateManager.translate(x, y, z);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
		float size = entity.getSize();
		GlStateManager.scale(size, size, size);
		GlStateManager.rotate(270F - renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(180F - renderManager.playerViewX, 0.0F, 0.0F, 1.0F);
		GlStateManager.rotate(entity.getTicksAlive() * 8, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(90 * 5F, 0.0F, 0.0F, 1.0F);
		MODEL.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.disableBlend();
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityHakureiOrb entityHakureiOrb) {
		return ResourceLibrary.ORB_TEXTURE;
	}
}
