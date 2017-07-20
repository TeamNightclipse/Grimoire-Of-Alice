package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.common.entity.EntityIceBlock;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderIceBlock extends Render<EntityIceBlock> {

	private static final ResourceLocation TEXTURE = new ResourceLocation("textures/blocks/ice.png");

	public RenderIceBlock(RenderManager renderManager) {
		super(renderManager);
	}

	private static final int[][] p = {
			{0, 1, 2, 3},
			{3, 2, 5, 4},
			{2, 1, 6, 5},
			{1, 0, 7, 6},
			{0, 3, 4, 7},
			{4, 5, 6, 7}
	};

	private static final int[][] t = {
			{0, 1},
			{1, 1},
			{1, 0},
			{0, 0}
	};

	@Override
	public void doRender(EntityIceBlock box, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		GlStateManager.disableLighting();
		GlStateManager.disableCull();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
		bindEntityTexture(box);

		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer vex = tessellator.getBuffer();
		float width = box.getIceBlockWidth() / 2.0F + 0.4F;
		float height = box.getIceBlockHeight();

		float[] px = {-width, width, width, -width, -width, width, width, -width};
		float[] py = {height, height, height, height, 0.0F, 0.0F, 0.0F, 0.0F};
		float[] pz = {width, width, -width, -width, -width, -width, width, width};

		for(int i = 0; i < 6; i++) {
			vex.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
			for(int j = 0; j < 4; j++) {
				tessellator.getBuffer().pos(px[p[i][j]], py[p[i][j]], pz[p[i][j]]).tex(t[j][0], t[j][1]).endVertex();
			}
			tessellator.draw();
		}

		GlStateManager.disableBlend();
		GlStateManager.enableCull();
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityIceBlock entity) {
		return TEXTURE;
	}
}
