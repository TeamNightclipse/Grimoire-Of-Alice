/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.effect.ParticleBase;
import arekkuusu.grimoireofalice.common.core.helper.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class ParticleRenderer {

	private final List<ParticleBase> particles = new ArrayList<>();

	public void update() {
		for(int i = 0; i < particles.size(); i++) {
			Particle particle = particles.get(i);
			if(!particle.isAlive()) {
				particles.remove(i);
			} else {
				particle.onUpdate();
			}
		}
	}

	public void renderAll(float partial) {
		Entity entity = Minecraft.getMinecraft().player;
		if(entity != null) {
			Particle.interpPosX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partial;
			Particle.interpPosY = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partial;
			Particle.interpPosZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partial;
			Particle.cameraViewDir = entity.getLook(partial);

			float f = ActiveRenderInfo.getRotationX();
			float f1 = ActiveRenderInfo.getRotationZ();
			float f2 = ActiveRenderInfo.getRotationYZ();
			float f3 = ActiveRenderInfo.getRotationXY();
			float f4 = ActiveRenderInfo.getRotationXZ();

			GlStateManager.enableAlpha();
			GlStateManager.enableBlend();
			GlStateManager.alphaFunc(516, 0.003921569F);
			GlStateManager.disableCull();
			GlStateManager.depthMask(false);

			Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			Tessellator tess = Tessellator.getInstance();
			VertexBuffer buffer = tess.getBuffer();

			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);

			buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
			for(ParticleBase particle : particles) {
				if(!particle.shouldDisableDepth()) {
					particle.renderParticle(buffer, entity, partial, f, f4, f1, f2, f3);
				}
			}
			tess.draw();

			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);

			buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
			for(ParticleBase particle : particles) {
				if(particle.shouldDisableDepth()) {
					particle.renderParticle(buffer, entity, partial, f, f4, f1, f2, f3);
				}
			}
			tess.draw();

			GlStateManager.enableDepth();

			GlStateManager.depthMask(true);
			GlStateManager.enableCull();
			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			GlStateManager.disableBlend();
			GlStateManager.alphaFunc(516, 0.1F);
		}
	}

	public void add(ParticleBase particle) {
		particles.add(particle);
	}
}
