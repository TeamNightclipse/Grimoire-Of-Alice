package arekkuusu.grimoireofalice.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ByakurenAura - Arekkuusu
 * Created using Tabula 5.1.0
 */
public class ModelByakurenAura extends ModelBiped {

	//fields
	private final ModelRenderer aura;

	public ModelByakurenAura() {
		textureWidth = 1024;
		textureHeight = 512;

		aura = new ModelRenderer(this, 1024, 512);
		aura.setRotationPoint(0F, -16F, 10F);
		aura.addBox(-254F, -153F, 0F, 511, 315, 1);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.aura.render(0.05F);
	}

	public void setRotationAngles(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

}
