package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.entity.EntityLeaf;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderLeaf extends Render<EntityLeaf> {
	
	private static final ResourceLocation TEXTURE = new ResourceLocation(LibMod.MODID, "textures/models/entities/leaf.png");

	public RenderLeaf(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
    public void doRender(EntityLeaf circle, double x, double y, double z, float yaw, float pitch) {
		GlStateManager.pushMatrix();
		bindEntityTexture(circle);
		//TODO: Render stuff here
		GlStateManager.popMatrix();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityLeaf entity) {
		return TEXTURE;
	}

}
