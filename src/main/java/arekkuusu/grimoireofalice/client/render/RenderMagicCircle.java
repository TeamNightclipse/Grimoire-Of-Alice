package arekkuusu.grimoireofalice.client.render;

import org.lwjgl.opengl.GL11;

import arekkuusu.grimoireofalice.client.model.ModelEllyScythe;
import arekkuusu.grimoireofalice.entity.EntityMagicCircle;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderMagicCircle extends Render {

	private static final ResourceLocation circleTexture = new ResourceLocation(LibMod.MODID, "textures/models/entities/ellyscythe.png");
	private static final ModelBase MODEL = new ModelEllyScythe();
	
	public RenderMagicCircle(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
    public void doRender(Entity circle, double x, double y, double z, float yaw, float pitch) {
		doRender((EntityMagicCircle)circle, z, z, z, pitch, pitch);
    }
	
    public void doRender(EntityMagicCircle circle, double x, double y, double z, float yaw, float pitch) {
		GL11.glPushMatrix();
		bindEntityTexture(circle);
        GL11.glTranslatef((float)x, (float)y, (float)z);
    	GL11.glDisable(GL11.GL_LIGHTING);
    	float size = circle.getCircleSize() * 6.0F;
        GL11.glScalef(size, size, size);
        float angle = MathHelper.sin(2F * (float)circle.getAnimationCount() / 180F * (float)Math.PI) * 40F;
		GL11.glRotatef(180F - renderManager.playerViewY + angle, 0.0F, 1.0F, 0.0F);
    	GL11.glRotatef(-renderManager.playerViewX - angle, 1.0F, 0.0F, 0.0F);
    	GL11.glRotatef(circle.getAnimationCount() * 5F, 0.0F, 0.0F, 1.0F);
        MODEL.render(circle, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
		System.out.println("Tajdad"); //Never prints
    }
	
	@Override
	protected ResourceLocation getEntityTexture(Entity circle) {
		return circleTexture;
	}

}
