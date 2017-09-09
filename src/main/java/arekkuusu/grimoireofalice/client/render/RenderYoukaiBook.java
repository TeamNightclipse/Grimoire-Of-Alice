package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.render.model.ModelBook;
import arekkuusu.grimoireofalice.client.util.ResourceLibrary;
import arekkuusu.grimoireofalice.common.entity.EntityYoukaiBook;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderYoukaiBook extends RenderLiving<EntityYoukaiBook> {

	public RenderYoukaiBook(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelBook(), 0.25F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityYoukaiBook entity) {
		return ResourceLibrary.YOUKAI_BOOK;
	}

	@Override
	protected void preRenderCallback(EntityYoukaiBook entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(0.8F, 0.8F, 0.8F);
	}

	@Override
	protected void applyRotations(EntityYoukaiBook entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
		GlStateManager.translate(0.0F, -1F, 0.0F);
		if(entityLiving.getIsBookLaying()) {
			GlStateManager.translate(0.0F, -0.1F, 0.0F);
		}
		else {
			GlStateManager.translate(0.0F, MathHelper.cos(p_77043_2_ * 0.3F) * 0.1F, 0.0F);
		}

		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}
}
