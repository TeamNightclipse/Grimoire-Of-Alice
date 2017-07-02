/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
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

	public NeedleSwing(TextureManager textureManagerIn, World world, double x, double y, double z, double modifier) {
		super(world, x, y, z);
		this.textureManager = textureManagerIn;
		this.size = (float)(2.0F +  modifier * 0.5F);
		this.lifeTime = 4;
	}

	@Override
	public void renderParticle(VertexBuffer worldRendererIn, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ,
			float rotationXY, float rotationXZ) {
		int i = (int)((life + partialTicks) * 3.0F / lifeTime);

		if (i <= 7) {
			GlStateManager.pushMatrix();
			this.textureManager.bindTexture(ResourceLocations.NEEDLE_SWING);
			float u1 = (i % 4) / 4.0F;
			float u2 = u1 + 0.24975F;
			float v1 = (i / 2) / 2.0F;
			float v2 = v1 + 0.4995F;
			float x = (float)(prevPosX + (posX - prevPosX) * partialTicks - interpPosX);
			float y = (float)(prevPosY + (posY - prevPosY) * partialTicks - interpPosY);
			float z = (float)(prevPosZ + (posZ - prevPosZ) * partialTicks - interpPosZ);
			worldRendererIn.begin(7, VERTEX_FORMAT);
			worldRendererIn.pos(x - rotationX * size - rotationXY * size, y - rotationZ * size * 0.5F, z - rotationYZ * size - rotationXZ * size)
					.tex(u2, v2).color(particleRed, particleGreen, particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
			worldRendererIn.pos(x - rotationX * size + rotationXY * size, y + rotationZ * size * 0.5F, z - rotationYZ * size + rotationXZ * size)
					.tex(u2, v1).color(particleRed, particleGreen, particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
			worldRendererIn.pos(x + rotationX * size + rotationXY * size, y + rotationZ * size * 0.5F, z + rotationYZ * size + rotationXZ * size)
					.tex(u1, v1).color(particleRed, particleGreen, particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
			worldRendererIn.pos(x + rotationX * size - rotationXY * size, y - rotationZ * size * 0.5F, z + rotationYZ * size - rotationXZ * size)
					.tex(u1, v2).color(particleRed, particleGreen, particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
			Tessellator.getInstance().draw();
			GlStateManager.popMatrix();
		}
	}

	@Override
	public int getBrightnessForRender(float p_189214_1_) {
		return 244;
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
