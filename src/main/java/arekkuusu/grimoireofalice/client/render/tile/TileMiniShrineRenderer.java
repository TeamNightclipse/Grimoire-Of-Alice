package arekkuusu.grimoireofalice.client.render.tile;

import arekkuusu.grimoireofalice.client.util.helper.BlendHelper;
import arekkuusu.grimoireofalice.common.block.tile.TileMiniShrine;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.Random;

@SideOnly(Side.CLIENT)
public class TileMiniShrineRenderer extends TileItemStackRenderer<TileMiniShrine> {

	private static final Random BEAM_RAND = new Random();

	@Override
	public void render(TileMiniShrine te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		ItemStack stack = te.getItemStack();
		if(!stack.isEmpty()) {
			switch(MinecraftForgeClient.getRenderPass()) {
				case 0:
					renderStack(stack, x, y + 1, z, partialTicks);
					break;
				case 1:
					float prevX = OpenGlHelper.lastBrightnessX;
					float prevY = OpenGlHelper.lastBrightnessY;
					OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 255, 255);
					GlStateManager.pushMatrix();
					GlStateManager.enableBlend();
					GlStateManager.disableCull();
					BlendHelper.BLEND_NORMAL.blend();
					GlStateManager.translate(x + 0.5, y + 1.5, z + 0.5);
					renderBeams( (Minecraft.getSystemTime() + partialTicks) / 20F * 0.005F, 10, 0xFF0000, 0xffffff, 0.35F);
					GlStateManager.enableCull();
					GlStateManager.disableBlend();
					GlStateManager.popMatrix();
					OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, prevX, prevY);
					break;
			}
		}
	}

	private static void renderBeams(float age, int number, int startRBG, int endRGB, float size) {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glDisable(GL11.GL_ALPHA_TEST);

		BEAM_RAND.setSeed(432L);
		float r_ = (startRBG >>> 16 & 0xFF) / 256F;
		float g_ = (startRBG >>> 8 & 0xFF) / 256F;
		float b_ = (startRBG & 0xFF) / 256F;

		float r = (endRGB >>> 16 & 0xFF) / 256F;
		float g = (endRGB >>> 8 & 0xFF) / 256F;
		float b = (endRGB & 0xFF) / 256F;

		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		float rotation = age % 500;

		for(int i = 0; (float) i < number; ++i) {
			GlStateManager.rotate(BEAM_RAND.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(BEAM_RAND.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(BEAM_RAND.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
			GlStateManager.rotate(BEAM_RAND.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(BEAM_RAND.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(BEAM_RAND.nextFloat() * 360.0F + rotation * 90.0F, 0.0F, 0.0F, 1.0F);
			float min = (size * 0.5F);
			float resized = BEAM_RAND.nextFloat() * size + min;
			float sizeMulti = BEAM_RAND.nextFloat() * min + (min * 0.5F);
			bufferbuilder.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION_COLOR);
			bufferbuilder.pos(0.0D, 0.0D, 0.0D).color(r_, g_, b_, 1F).endVertex();
			bufferbuilder.pos(-0.866D * sizeMulti, resized, (-0.5F * sizeMulti)).color(r, g, b, 0F).endVertex();
			bufferbuilder.pos(0.866D * sizeMulti, resized, (-0.5F * sizeMulti)).color(r, g, b, 0F).endVertex();
			bufferbuilder.pos(0.0D, resized, (1.0F * sizeMulti)).color(r, g, b, 0F).endVertex();
			bufferbuilder.pos(-0.866D * sizeMulti, resized, (-0.5F * sizeMulti)).color(r, g, b, 0F).endVertex();
			tessellator.draw();
		}

		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glShadeModel(GL11.GL_FLAT);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
}