package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.util.ResourceLibrary;
import arekkuusu.grimoireofalice.common.entity.EntityNetherSoul;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderNetherSoul extends Render<EntityNetherSoul> {

	public RenderNetherSoul(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityNetherSoul entity, double x, double y, double z, float entityYaw, float partialTicks) {

	}

	@Override
	protected ResourceLocation getEntityTexture(EntityNetherSoul entity) {
		return ResourceLibrary.NETHER_SOUL_TEXTURE;
	}
}
