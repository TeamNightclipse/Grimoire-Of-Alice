/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.model.ModelEllyScythe;
import arekkuusu.grimoireofalice.entity.EntityThrow;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderEllyScytheProyectile extends Render<EntityThrow> { //TODO: Change me to EntityEllyProjectile when that comes back

	protected RenderEllyScytheProyectile(RenderManager renderManager) {
		super(renderManager);
	}

	private static final ResourceLocation TEXTURE = new ResourceLocation(LibMod.MODID, "textures/models/entities/ellyscythe.png");
	private static final ModelBase MODEL = new ModelEllyScythe();
	
	@Override
	protected ResourceLocation getEntityTexture(EntityThrow entity) {
		return TEXTURE;
	}
	
	@Override
	public void doRender(EntityThrow ellyScythe, double x, double y, double z, float yaw, float pitch) {
		GlStateManager.pushMatrix();
		bindEntityTexture(ellyScythe);
		GlStateManager.translate(x, y, z);
		GlStateManager.rotate(ellyScythe.prevRotationPitch + (ellyScythe.rotationPitch - ellyScythe.prevRotationPitch) * pitch, 0.0F, 0.0F, 1.0F);
		GlStateManager.rotate((ellyScythe.prevRotationYaw + (ellyScythe.rotationYaw - ellyScythe.prevRotationYaw) * pitch) - 90F, 0.0F, 1.0F, 0.0F);
		MODEL.render(ellyScythe, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.popMatrix();
	}
	
}