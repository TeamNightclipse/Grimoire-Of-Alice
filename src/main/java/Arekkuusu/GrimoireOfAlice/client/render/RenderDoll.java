package arekkuusu.grimoireOfAlice.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderDoll extends RenderLiving {

	private ResourceLocation texture;

	public RenderDoll(ModelBase p_i1262_1_, ResourceLocation texture) {
		
		super(p_i1262_1_, 0.25F);
		this.texture = texture;

	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		
		return texture;
		
	}

}
