package arekkuusu.grimoireOfAlice.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import arekkuusu.grimoireOfAlice.client.model.ModelEllyScythe;
import arekkuusu.grimoireOfAlice.entity.EntityEllyScytheThrowable;
import arekkuusu.grimoireOfAlice.entity.EntityThrow;
import arekkuusu.grimoireOfAlice.lib.LibMod;

public class RenderEllyScytheProyectile extends Render {

	private static final ResourceLocation TEXTURE = new ResourceLocation(LibMod.MODID, "textures/models/EllyScythe.png");
	private static final ModelBase model = new ModelEllyScythe();
	
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
		model.render(ellyScythe, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}
	
}