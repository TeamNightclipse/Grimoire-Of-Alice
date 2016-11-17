package arekkuusu.grimoireofalice.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * SuwakoHat - Arekkuusu Created using Tabula 5.1.0
 */
public class ModelSuwakoHat extends ModelBiped {

	private final ModelRenderer hat;
	private final ModelRenderer base;
	private final ModelRenderer side0;
	private final ModelRenderer side1;
	private final ModelRenderer side2;
	private final ModelRenderer side3;
	private final ModelRenderer eyes;

	public ModelSuwakoHat() {
		textureWidth = 64;
		textureHeight = 64;
		eyes = new ModelRenderer(this, 32, 32);
		eyes.setRotationPoint(0.0F, 0.0F, 0.0F);
		eyes.addBox(-4.0F, -11.0F, -7.0F, 8, 3, 0, 0.0F);
		hat = new ModelRenderer(this, 0, 0);
		hat.setRotationPoint(0.0F, 0.0F, 0.0F);
		hat.addBox(-4.0F, -13.0F, -4.0F, 8, 5, 8, 0.0F);
		base = new ModelRenderer(this, 19, 0);
		base.setRotationPoint(0.0F, 0.0F, 0.0F);
		base.addBox(-7.0F, -8.0F, -7.0F, 14, 0, 14, 0.0F);
		side2 = new ModelRenderer(this, 0, 32);
		side2.setRotationPoint(0.0F, 0.0F, 0.0F);
		side2.addBox(-7.0F, -8.0F, 6.0F, 14, 1, 1, 0.0F);
		side0 = new ModelRenderer(this, 0, 14);
		side0.setRotationPoint(0.0F, 0.0F, 0.0F);
		side0.addBox(-7.0F, -8.0F, -7.0F, 1, 1, 14, 0.0F);
		side3 = new ModelRenderer(this, 0, 35);
		side3.setRotationPoint(0.0F, 0.0F, 0.0F);
		side3.addBox(6.0F, -8.0F, -7.0F, 1, 1, 14, 0.0F);
		side1 = new ModelRenderer(this, 0, 32);
		side1.setRotationPoint(0.0F, 0.0F, 0.0F);
		side1.addBox(-7.0F, -8.0F, -7.0F, 14, 1, 1, 0.0F);
		hat.addChild(side2);
		hat.addChild(eyes);
		hat.addChild(side0);
		hat.addChild(base);
		hat.addChild(side3);
		hat.addChild(side1);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		bipedHeadwear.showModel = false;
		bipedHead = hat;
		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
