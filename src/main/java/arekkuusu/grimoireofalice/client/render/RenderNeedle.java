package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.entity.EntityNeedle;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderNeedle extends RenderArrow<EntityNeedle> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(LibMod.MODID, "textures/models/entities/needle.png");

	public RenderNeedle(RenderManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityNeedle entity) {
		return TEXTURE;
	}
}
