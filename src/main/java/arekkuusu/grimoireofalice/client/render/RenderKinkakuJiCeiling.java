package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.ResourceLocations;
import arekkuusu.grimoireofalice.client.model.ModelKinkakuJiCeiling;
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
		GlStateManager.rotate(90 - entity.rotationYaw, 0F, 1F, 0F);

		MODEL.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityKinkakuJiCeiling entity) {
		return ResourceLocations.KINKAKU_JI_CEILING;
	}
}
