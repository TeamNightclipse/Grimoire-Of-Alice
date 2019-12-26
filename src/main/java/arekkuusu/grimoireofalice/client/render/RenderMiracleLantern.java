package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.util.ShaderLibrary;
import arekkuusu.grimoireofalice.client.render.model.ModelMiracleLantern;
import arekkuusu.grimoireofalice.client.util.ResourceLibrary;
import arekkuusu.grimoireofalice.common.entity.EntityMiracleLantern;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMiracleLantern extends Render<EntityMiracleLantern> {

	private static final ModelMiracleLantern MODEL_MIRACLE_LANTERN = new ModelMiracleLantern();

	public RenderMiracleLantern(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityMiracleLantern entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		ShaderLibrary.BRIGHT.begin();
		ShaderLibrary.BRIGHT.getUniformJ("brightness").ifPresent(b -> {
			b.set(0F);
			b.upload();
		});
		bindEntityTexture(entity);
		GlStateManager.translate(x, y, z);
		GlStateManager.rotate(entityYaw, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(entity.ticksExisted * 8F, 1.0F, 0.0F, 0.0F);
		MODEL_MIRACLE_LANTERN.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		MODEL_MIRACLE_LANTERN.renderMore(0.0625F);
		ShaderLibrary.BRIGHT.end();
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMiracleLantern entity) {
		return ResourceLibrary.MIRACLE_LANTERN;
	}
}
