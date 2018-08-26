package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.ShaderLibrary;
import arekkuusu.grimoireofalice.client.render.model.ModelKinkakuJiCeiling;
import arekkuusu.grimoireofalice.client.util.ResourceLibrary;
import arekkuusu.grimoireofalice.common.entity.EntityKinkakuJiCeiling;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderKinkakuJiCeiling extends Render<EntityKinkakuJiCeiling> {

	private static final ModelBase MODEL = new ModelKinkakuJiCeiling();

	public RenderKinkakuJiCeiling(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityKinkakuJiCeiling entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		bindEntityTexture(entity);
		GlStateManager.scale(2F, 1F, 2F);
		ShaderLibrary.BRIGHT.begin();
		ShaderLibrary.BRIGHT.getUniformJ("brightness").ifPresent(b -> {
			b.set(0F);
			b.upload();
		});
		MODEL.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		ShaderLibrary.BRIGHT.end();
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityKinkakuJiCeiling entity) {
		return ResourceLibrary.KINKAKU_JI_CEILING;
	}
}
