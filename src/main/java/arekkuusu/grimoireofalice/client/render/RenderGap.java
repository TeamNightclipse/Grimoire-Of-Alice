package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.ResourceLocations;
import arekkuusu.grimoireofalice.client.model.ModelFlat;
import arekkuusu.grimoireofalice.common.entity.EntityGap;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.nio.FloatBuffer;

@SideOnly(Side.CLIENT)
//Code taken from Sheep rendering
public class RenderGap extends Render<EntityGap> {

	private static final ModelBase MODEL = new ModelFlat();
	private static final DynamicTexture TEXTURE_BRIGHTNESS = new DynamicTexture(16, 16);
	private FloatBuffer brightnessBuffer = GLAllocation.createDirectFloatBuffer(4);
	private final RenderLayerGap layerGap;

	public RenderGap(RenderManager renderManager) {
		super(renderManager);
		this.layerGap = new RenderLayerGap(this);
	}

	@Override
	public void doRender(EntityGap entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.enableCull();
		GlStateManager.translate(x, y, z);
		GlStateManager.rotate(180F, 1.0F, 0.0F, 0.0F);

		float maxUpAndDown = 0.05F;
		float speed = 2;
		float angle = 0;

		float toDegrees = (float) Math.PI / 180F;
		angle += speed * entity.ticksExisted;
		if (angle > 360) angle -= 360;

		GlStateManager.translate(0, maxUpAndDown * MathHelper.sin(angle * toDegrees), 0);
		GlStateManager.rotate(entity.rotationYaw + 90, 0F, 1F, 0F);
		GlStateManager.rotate(entity.rotationPitch + 90F, 0F, 0F, 1F);

		if (this.renderOutlines) {
			setScoreTeamColor();
			GlStateManager.enableColorMaterial();
			GlStateManager.enableOutlineMode(getTeamColor(entity));

			bindEntityTexture(entity);
			MODEL.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

			setBrightness();
			layerGap.doRenderLayer(entity);
			unsetBrightness();

			GlStateManager.disableOutlineMode();
			GlStateManager.disableColorMaterial();
			unsetScoreTeamColor();
		}
		else {
			boolean flag = setBrightness();

			bindEntityTexture(entity);
			MODEL.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

			if (flag) {
				unsetBrightness();
			}

			setBrightness();
			layerGap.doRenderLayer(entity);
			unsetBrightness();

			GlStateManager.depthMask(true);
		}

		GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GlStateManager.enableTexture2D();
		GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
		GlStateManager.disableCull();
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	private boolean setBrightness() {
		int i = 0;
		boolean flag = (i >> 24 & 255) > 0;

		if (!flag) {
			return false;
		} else {
			GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
			GlStateManager.enableTexture2D();
			GlStateManager.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, 8448);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.defaultTexUnit);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PRIMARY_COLOR);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 7681);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.defaultTexUnit);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
			GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
			GlStateManager.enableTexture2D();
			GlStateManager.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, OpenGlHelper.GL_INTERPOLATE);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.GL_CONSTANT);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PREVIOUS);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE2_RGB, OpenGlHelper.GL_CONSTANT);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND2_RGB, 770);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 7681);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.GL_PREVIOUS);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
			this.brightnessBuffer.position(0);

			float f1 = (float) (i >> 24 & 255) / 255.0F;
			float f2 = (float) (i >> 16 & 255) / 255.0F;
			float f3 = (float) (i >> 8 & 255) / 255.0F;
			float f4 = (float) (i & 255) / 255.0F;
			this.brightnessBuffer.put(f2);
			this.brightnessBuffer.put(f3);
			this.brightnessBuffer.put(f4);
			this.brightnessBuffer.put(1.0F - f1);

			this.brightnessBuffer.flip();
			GlStateManager.glTexEnv(8960, 8705, this.brightnessBuffer);
			GlStateManager.setActiveTexture(OpenGlHelper.GL_TEXTURE2);
			GlStateManager.enableTexture2D();
			GlStateManager.bindTexture(TEXTURE_BRIGHTNESS.getGlTextureId());
			GlStateManager.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, 8448);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.GL_PREVIOUS);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.lightmapTexUnit);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 7681);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.GL_PREVIOUS);
			GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
			GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
			return true;
		}
	}

	private void unsetBrightness() {
		GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
		GlStateManager.enableTexture2D();
		GlStateManager.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
		GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, 8448);
		GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.defaultTexUnit);
		GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PRIMARY_COLOR);
		GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
		GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
		GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 8448);
		GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.defaultTexUnit);
		GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_ALPHA, OpenGlHelper.GL_PRIMARY_COLOR);
		GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
		GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_ALPHA, 770);
		GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GlStateManager.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
		GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, 8448);
		GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
		GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
		GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, 5890);
		GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PREVIOUS);
		GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 8448);
		GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
		GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, 5890);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.setActiveTexture(OpenGlHelper.GL_TEXTURE2);
		GlStateManager.disableTexture2D();
		GlStateManager.bindTexture(0);
		GlStateManager.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
		GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, 8448);
		GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
		GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
		GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, 5890);
		GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PREVIOUS);
		GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 8448);
		GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
		GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, 5890);
		GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}

	private void setScoreTeamColor() {
		GlStateManager.disableLighting();
		GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GlStateManager.disableTexture2D();
		GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}

	private void unsetScoreTeamColor() {
		GlStateManager.enableLighting();
		GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GlStateManager.enableTexture2D();
		GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityGap entity) {
		return ResourceLocations.GAP;
	}
}
