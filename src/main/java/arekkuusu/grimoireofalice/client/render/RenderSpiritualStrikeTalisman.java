package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.ResourceLocations;
import arekkuusu.grimoireofalice.client.model.ModelFlat;
import arekkuusu.grimoireofalice.common.entity.EntitySpiritualStrikeTalisman;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSpiritualStrikeTalisman extends Render<EntitySpiritualStrikeTalisman> {

	private static final ModelBase MODEL = new ModelFlat();

	public RenderSpiritualStrikeTalisman(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntitySpiritualStrikeTalisman entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		GlStateManager.rotate(90F, 1F, 0F, 0F);
		GlStateManager.rotate(15F, 0F, 0F, 1F);
		GlStateManager.rotate(entity.ticksExisted * 2F, 0F, 1F, 0F);
		GlStateManager.rotate(entity.ticksExisted * 8F, 0F, 0F, 1F);
		bindEntityTexture(entity);
		MODEL.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySpiritualStrikeTalisman entity) {
		return ResourceLocations.TALISMAN;
	}
}
