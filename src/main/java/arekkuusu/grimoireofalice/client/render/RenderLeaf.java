package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderLeaf extends Render {
	
	private static final ResourceLocation TEXTURE = new ResourceLocation(LibMod.MODID, "textures/models/entities/leaf.png");

	public RenderLeaf(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
    public void doRender(Entity circle, double x, double y, double z, float yaw, float pitch) {
		Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE);
		VertexBuffer wr = Tessellator.getInstance().getBuffer();
		wr.begin(7, DefaultVertexFormats.ITEM);
		Tessellator.getInstance().draw();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TEXTURE;
	}

}
