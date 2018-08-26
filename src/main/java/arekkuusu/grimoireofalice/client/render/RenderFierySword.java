package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.ShaderLibrary;
import arekkuusu.grimoireofalice.client.render.model.ModelFierySword;
import arekkuusu.grimoireofalice.client.util.ResourceLibrary;
import arekkuusu.grimoireofalice.client.util.helper.BlendHelper;
import arekkuusu.grimoireofalice.common.entity.EntityFierySword;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFierySword extends Render<EntityFierySword> {

	private static final ModelFierySword MODEL = new ModelFierySword();

	public RenderFierySword(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityFierySword entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		ShaderLibrary.BRIGHT.begin();
		ShaderLibrary.BRIGHT.getUniformJ("brightness").ifPresent(b -> {
			b.set(0F);
			b.upload();
		});
		GlStateManager.translate(x, y, z);
		GlStateManager.rotate(-entity.rotationYaw - 180F, 0F, 1F, 0F);
		GlStateManager.rotate(-entity.rotationPitch - 180F, 1F, 0F, 0F);
		bindEntityTexture(entity);
		MODEL.renderInsideForm(0.0625F);
		MODEL.render(entity, 1, 0, 0, 0.0F, 0.0F, 0.0625F);
		ShaderLibrary.BRIGHT.end();
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityFierySword entity) {
		return ResourceLibrary.FIERY_SWORD_TEXTURE;
	}
}
