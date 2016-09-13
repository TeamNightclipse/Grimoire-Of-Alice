package arekkuusu.grimoireofalice.client.render;

import org.lwjgl.opengl.GL11;

import arekkuusu.grimoireofalice.client.model.ModelEllyScythe;
import arekkuusu.grimoireofalice.client.model.ModelLeaf;
import arekkuusu.grimoireofalice.entity.EntityLeaf;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderLeaf extends Render<EntityLeaf> {
	
	private static final ResourceLocation TEXTURE = new ResourceLocation(LibMod.MODID, "textures/models/entities/leaf.png");
	private static final ModelBase MODEL = new ModelLeaf();

	public RenderLeaf(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
    public void doRender(EntityLeaf circle, double x, double y, double z, float yaw, float pitch) {
		GL11.glPushMatrix();
		bindEntityTexture(circle);
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		GL11.glRotatef(circle.getTime()*32, 0.0F, 1.0F, 0.0F);
		MODEL.render(circle, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityLeaf entity) {
		return TEXTURE;
	}

}
