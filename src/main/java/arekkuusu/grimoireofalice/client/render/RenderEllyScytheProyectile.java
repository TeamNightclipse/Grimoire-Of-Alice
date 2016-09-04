/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import arekkuusu.grimoireofalice.client.model.ModelEllyScythe;
import arekkuusu.grimoireofalice.lib.LibMod;

public class RenderEllyScytheProyectile extends Render {

	protected RenderEllyScytheProyectile(RenderManager renderManager) {
		super(renderManager);
	}

	private static final ResourceLocation TEXTURE = new ResourceLocation(LibMod.MODID, "textures/models/EllyScythe.png");
	private static final ModelBase MODEL = new ModelEllyScythe();
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TEXTURE;
	}
	
	@Override
	public void doRender(Entity ellyScythe, double x, double y, double z, float yaw, float pitch) {
		GL11.glPushMatrix();
		bindEntityTexture(ellyScythe);
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glRotatef(ellyScythe.prevRotationPitch + (ellyScythe.rotationPitch - ellyScythe.prevRotationPitch) * pitch, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef((ellyScythe.prevRotationYaw + (ellyScythe.rotationYaw - ellyScythe.prevRotationYaw) * pitch) - 90F, 0.0F, 1.0F, 0.0F);
		MODEL.render(ellyScythe, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}
	
}