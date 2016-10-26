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
	private final ModelRenderer neck;

	public ModelByakurenAura() {
		textureWidth = 1024;
		textureHeight = 512;

		aura = new ModelRenderer(this, 1024, 512);
		aura.setRotationPoint(0F, -16F, 10F);
		aura.addBox(-254F, -153F, 0F, 511, 315, 1);
		neck = new ModelRenderer(this, 16, 445);
		neck.setRotationPoint(0.0F, 0.0F, 0.0F);
		neck.addBox(-16.0F, 0.0F, -8.0F, 32, 48, 16, 0.25F);
	}

	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		bipedHeadwear.showModel = false;
		bipedBody.showModel = true;

		bipedBody = this.neck;
		aura.showModel = entity.isSneaking();

		this.aura.render(0.05F);

		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, 0.025F);
	}

	public void setRotationAngles(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
