package arekkuusu.grimoireofalice.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * SuwakoHat - Arekkuusu Created using Tabula 5.1.0
 */
public class ModelSuwakoHat extends ModelBiped {

	//TODO: Name these
	private final ModelRenderer shape15;
	private final ModelRenderer shape15_1;
	private final ModelRenderer shape15_2;
	private final ModelRenderer shape15_3;
	private final ModelRenderer shape15_4;
	private final ModelRenderer shape15_5;
	private final ModelRenderer shape15_6;

	public ModelSuwakoHat() {
		textureWidth = 64;
		textureHeight = 64;
		shape15_6 = new ModelRenderer(this, 32, 32);
		shape15_6.setRotationPoint(0.0F, 0.0F, 0.0F);
		shape15_6.addBox(-4.0F, -11.0F, -7.0F, 8, 3, 0, 0.0F);
		shape15 = new ModelRenderer(this, 0, 0);
		shape15.setRotationPoint(0.0F, 0.0F, 0.0F);
		shape15.addBox(-4.0F, -13.0F, -4.0F, 8, 5, 8, 0.0F);
		shape15_1 = new ModelRenderer(this, 19, 0);
		shape15_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		shape15_1.addBox(-7.0F, -8.0F, -7.0F, 14, 0, 14, 0.0F);
		shape15_4 = new ModelRenderer(this, 0, 32);
		shape15_4.setRotationPoint(0.0F, 0.0F, 0.0F);
		shape15_4.addBox(-7.0F, -8.0F, 6.0F, 14, 1, 1, 0.0F);
		shape15_2 = new ModelRenderer(this, 0, 14);
		shape15_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		shape15_2.addBox(-7.0F, -8.0F, -7.0F, 1, 1, 14, 0.0F);
		shape15_5 = new ModelRenderer(this, 0, 35);
		shape15_5.setRotationPoint(0.0F, 0.0F, 0.0F);
		shape15_5.addBox(6.0F, -8.0F, -7.0F, 1, 1, 14, 0.0F);
		shape15_3 = new ModelRenderer(this, 0, 32);
		shape15_3.setRotationPoint(0.0F, 0.0F, 0.0F);
		shape15_3.addBox(-7.0F, -8.0F, -7.0F, 14, 1, 1, 0.0F);
		shape15.addChild(shape15_4);
		shape15.addChild(shape15_6);
		shape15.addChild(shape15_2);
		shape15.addChild(shape15_1);
		shape15.addChild(shape15_5);
		shape15.addChild(shape15_3);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		bipedHeadwear.showModel = false;
		bipedHead = shape15;
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
