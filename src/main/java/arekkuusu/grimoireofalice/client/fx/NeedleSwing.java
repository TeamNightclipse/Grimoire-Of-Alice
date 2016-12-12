package arekkuusu.grimoireofalice.client.fx;

import arekkuusu.grimoireofalice.client.ResourceLocations;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class NeedleSwing extends Particle {

	private static final VertexFormat VERTEX_FORMAT = (new VertexFormat()).addElement(DefaultVertexFormats.POSITION_3F).addElement(DefaultVertexFormats.TEX_2F).addElement(DefaultVertexFormats.COLOR_4UB).addElement(DefaultVertexFormats.TEX_2S).addElement(DefaultVertexFormats.NORMAL_3B).addElement(DefaultVertexFormats.PADDING_1B);
	private final TextureManager textureManager;
	private final int lifeTime;
	private final float size;
	private int life;

	public NeedleSwing(TextureManager textureManagerIn, World worldIn, double x, double y, double z, double modifier) {
		super(worldIn, x, y, z);
		this.textureManager = textureManagerIn;
		this.size = 2.0F + (float) modifier * 0.5F;
		this.lifeTime = 4;
	}

	@Override
	public void renderParticle(VertexBuffer worldRendererIn, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		int i = (int) (((float) this.life + partialTicks) * 3.0F / (float) this.lifeTime);

		if (i <= 7) {
			GlStateManager.pushMatrix();
			this.textureManager.bindTexture(ResourceLocations.NEEDLE_SWING);
			float f = (float) (i % 4) / 4.0F;
			float f1 = f + 0.24975F;
			float f2 = (float) (i / 2) / 2.0F;
			float f3 = f2 + 0.4995F;
			float f4 = 1.0F * this.size;
			float f5 = (float) (this.prevPosX + (this.posX - this.prevPosX) * (double) partialTicks - interpPosX);
			float f6 = (float) (this.prevPosY + (this.posY - this.prevPosY) * (double) partialTicks - interpPosY);
			float f7 = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * (double) partialTicks - interpPosZ);
			worldRendererIn.begin(7, VERTEX_FORMAT);
			worldRendererIn.pos((double) (f5 - rotationX * f4 - rotationXY * f4), (double) (f6 - rotationZ * f4 * 0.5F), (double) (f7 - rotationYZ * f4 - rotationXZ * f4)).tex((double) f1, (double) f3).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
			worldRendererIn.pos((double) (f5 - rotationX * f4 + rotationXY * f4), (double) (f6 + rotationZ * f4 * 0.5F), (double) (f7 - rotationYZ * f4 + rotationXZ * f4)).tex((double) f1, (double) f2).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
			worldRendererIn.pos((double) (f5 + rotationX * f4 + rotationXY * f4), (double) (f6 + rotationZ * f4 * 0.5F), (double) (f7 + rotationYZ * f4 + rotationXZ * f4)).tex((double) f, (double) f2).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
			worldRendererIn.pos((double) (f5 + rotationX * f4 - rotationXY * f4), (double) (f6 - rotationZ * f4 * 0.5F), (double) (f7 + rotationYZ * f4 - rotationXZ * f4)).tex((double) f, (double) f3).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
			Tessellator.getInstance().draw();
			GlStateManager.popMatrix();
		}
	}

	@Override
	public int getBrightnessForRender(float i) {
		return 61680;
	}

	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		++this.life;

		if (this.life == this.lifeTime) {
			this.setExpired();
		}
	}

	@Override
	public int getFXLayer() {
		return 3;
	}
}
