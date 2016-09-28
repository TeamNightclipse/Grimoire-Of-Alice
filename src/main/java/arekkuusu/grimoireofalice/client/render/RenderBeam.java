package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.entity.EntityBeam;
import arekkuusu.grimoireofalice.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderBeam extends Render<EntityBeam> {

	private static final ResourceLocation TEXTURE_BEACON_BEAM = new ResourceLocation("textures/entity/beacon_beam.png");
	private final RenderItem renderItem;

	public RenderBeam(RenderManager renderManager) {
		super(renderManager);
		this.renderItem = Minecraft.getMinecraft().getRenderItem();
	}

	@Override
	public void doRender(EntityBeam entity, double x, double y, double z, float entityYaw, float partialTicks) {
		ItemStack stack = new ItemStack(ModItems.dragonJewel);

		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5D, y - 0.8D, z + 0.5D);
		GlStateManager.enableRescaleNormal();
		GlStateManager.scale(2F, 2F, 2F);
		GlStateManager.rotate(entity.getTicksAlive() * 32, 0.0F, 1.0F, 0.0F);
		renderItem.renderItem(stack, ItemCameraTransforms.TransformType.GROUND);
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();

		GlStateManager.alphaFunc(516, 0.1F);
		this.bindTexture(TEXTURE_BEACON_BEAM);
		GlStateManager.disableFog();
		int i = 0;
		int height = 0;
		for (int j = 0; j < 50; ++j) {
			int ij = i + height;
			double p_188205_17_ = 0.25D;
			double p_188205_15_ = 0.2D;
			double p_188205_10_ = entity.worldObj.getTotalWorldTime();
			GlStateManager.glTexParameteri(3553, 10242, 10497);
			GlStateManager.glTexParameteri(3553, 10243, 10497);
			GlStateManager.disableLighting();
			GlStateManager.disableCull();
			GlStateManager.disableBlend(); //TODO: Very ugly effect. Change for Light beams or something pretty. NOT A BEACON
			GlStateManager.depthMask(true);
			GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			Tessellator tessellator = Tessellator.getInstance();
			VertexBuffer vertexbuffer = tessellator.getBuffer();
			double d0 = p_188205_10_ + partialTicks;
			double d1 = height < 0 ? d0 : -d0;
			double d2 = MathHelper.frac(d1 * 0.2D - (double) MathHelper.floor_double(d1 * 0.1D));
			float f = 1;
			float f1 = 2;
			float f2 = 0;
			double d3 = d0 * 0.025D * -1.5D;
			double d4 = 0.5D + Math.cos(d3 + 2.356194490192345D) * p_188205_15_;
			double d5 = 0.5D + Math.sin(d3 + 2.356194490192345D) * p_188205_15_;
			double d6 = 0.5D + Math.cos(d3 + (Math.PI / 4D)) * p_188205_15_;
			double d7 = 0.5D + Math.sin(d3 + (Math.PI / 4D)) * p_188205_15_;
			double d8 = 0.5D + Math.cos(d3 + 3.9269908169872414D) * p_188205_15_;
			double d9 = 0.5D + Math.sin(d3 + 3.9269908169872414D) * p_188205_15_;
			double d10 = 0.5D + Math.cos(d3 + 5.497787143782138D) * p_188205_15_;
			double d11 = 0.5D + Math.sin(d3 + 5.497787143782138D) * p_188205_15_;
			double d12 = 0.0D;
			double d13 = 1.0D;
			double d14 = -1.0D + d2;
			double d15 = (double) height * d13 * (0.5D / p_188205_15_) + d14;
			vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
			vertexbuffer.pos(x + d4, y + (double) ij, z + d5).tex(1.0D, d15).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d4, y + (double) i, z + d5).tex(1.0D, d14).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d6, y + (double) i, z + d7).tex(0.0D, d14).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d6, y + (double) ij, z + d7).tex(0.0D, d15).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d10, y + (double) ij, z + d11).tex(1.0D, d15).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d10, y + (double) i, z + d11).tex(1.0D, d14).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d8, y + (double) i, z + d9).tex(0.0D, d14).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d8, y + (double) ij, z + d9).tex(0.0D, d15).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d6, y + (double) ij, z + d7).tex(1.0D, d15).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d6, y + (double) i, z + d7).tex(1.0D, d14).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d10, y + (double) i, z + d11).tex(0.0D, d14).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d10, y + (double) ij, z + d11).tex(0.0D, d15).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d8, y + (double) ij, z + d9).tex(1.0D, d15).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d8, y + (double) i, z + d9).tex(1.0D, d14).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d4, y + (double) i, z + d5).tex(0.0D, d14).color(f, f1, f2, 1.0F).endVertex();
			vertexbuffer.pos(x + d4, y + (double) ij, z + d5).tex(0.0D, d15).color(f, f1, f2, 1.0F).endVertex();
			tessellator.draw();
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			GlStateManager.depthMask(false);
			d3 = 0.5D - p_188205_17_;
			d4 = 0.5D - p_188205_17_;
			d5 = 0.5D + p_188205_17_;
			d6 = 0.5D - p_188205_17_;
			d7 = 0.5D - p_188205_17_;
			d8 = 0.5D + p_188205_17_;
			d9 = 0.5D + p_188205_17_;
			d10 = 0.5D + p_188205_17_;
			d12 = 1.0D;
			d13 = -1.0D + d2;
			d14 = (double) height * d13 + d12;
			vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
			vertexbuffer.pos(x + d3, y + (double) ij, z + d4).tex(1.0D, d14).color(f, f1, f2, 0.125F).endVertex();
			vertexbuffer.pos(x + d3, y + (double) i, z + d4).tex(1.0D, d13).color(f, f1, f2, 0.125F).endVertex();
			vertexbuffer.pos(x + d5, y + (double) i, z + d6).tex(0.0D, d13).color(f, f1, f2, 0.125F).endVertex();
			vertexbuffer.pos(x + d5, y + (double) ij, z + d6).tex(0.0D, d14).color(f, f1, f2, 0.125F).endVertex();
			vertexbuffer.pos(x + d9, y + (double) ij, z + d10).tex(1.0D, d13).color(f, f1, f2, 0.125F).endVertex();
			vertexbuffer.pos(x + d7, y + (double) i, z + d8).tex(0.0D, d13).color(f, f1, f2, 0.125F).endVertex();
			vertexbuffer.pos(x + d7, y + (double) ij, z + d8).tex(0.0D, d14).color(f, f1, f2, 0.125F).endVertex();
			vertexbuffer.pos(x + d5, y + (double) ij, z + d6).tex(1.0D, d14).color(f, f1, f2, 0.125F).endVertex();
			vertexbuffer.pos(x + d5, y + (double) i, z + d6).tex(1.0D, d13).color(f, f1, f2, 0.125F).endVertex();
			vertexbuffer.pos(x + d9, y + (double) i, z + d10).tex(0.0D, d13).color(f, f1, f2, 0.125F).endVertex();
			vertexbuffer.pos(x + d9, y + (double) ij, z + d10).tex(0.0D, d14).color(f, f1, f2, 0.125F).endVertex();
			vertexbuffer.pos(x + d7, y + (double) ij, z + d8).tex(1.0D, d14).color(f, f1, f2, 0.125F).endVertex();
			vertexbuffer.pos(x + d7, y + (double) i, z + d8).tex(1.0D, d13).color(f, f1, f2, 0.125F).endVertex();
			vertexbuffer.pos(x + d3, y + (double) i, z + d4).tex(0.0D, d13).color(f, f1, f2, 0.125F).endVertex();
			vertexbuffer.pos(x + d3, y + (double) ij, z + d4).tex(0.0D, d14).color(f, f1, f2, 0.125F).endVertex();
			tessellator.draw();
			GlStateManager.enableLighting();
			GlStateManager.enableTexture2D();
			GlStateManager.depthMask(true);
			++height;
		}
		GlStateManager.enableFog();
	}


	@Override
	protected ResourceLocation getEntityTexture(EntityBeam entity) {
		return TEXTURE_BEACON_BEAM;
	}
}
