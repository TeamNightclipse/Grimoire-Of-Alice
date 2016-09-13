package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.model.ModelEllyScythe;
import arekkuusu.grimoireofalice.entity.EntityMagicCircle;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderMagicCircle extends Render<EntityMagicCircle> {

	private static final ResourceLocation circleTexture = new ResourceLocation(LibMod.MODID, "textures/models/entities/ellyscythe.png");
	private static final ModelBase MODEL = new ModelEllyScythe();
	
	public RenderMagicCircle(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
    public void doRender(EntityMagicCircle circle, double x, double y, double z, float yaw, float pitch) {
		GlStateManager.pushMatrix();
		bindEntityTexture(circle);
		GlStateManager.translate(x, y, z);
		GlStateManager.disableLighting();
		float size = circle.getCircleSize() * 6.0F;
		GlStateManager.scale(size, size, size);
		float angle = MathHelper.sin((float)(Math.toRadians(circle.getAnimationCount()) * 2F)) * 40F;
		GlStateManager.rotate(180F - renderManager.playerViewY + angle, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(-renderManager.playerViewX - angle, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(circle.getAnimationCount() * 5F, 0.0F, 0.0F, 1.0F);
		MODEL.render(circle, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
		System.out.println("Tajdad"); //Never prints
    }
	
	@Override
	protected ResourceLocation getEntityTexture(EntityMagicCircle circle) {
		return circleTexture;
	}

}
