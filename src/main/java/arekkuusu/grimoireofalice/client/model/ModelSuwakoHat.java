package arekkuusu.grimoireofalice.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * SuwakoHat - Arekkuusu
 * Created using Tabula 5.1.0
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
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.shape15_6 = new ModelRenderer(this, 32, 32);
		this.shape15_6.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape15_6.addBox(-4.0F, -11.0F, -7.0F, 8, 3, 0, 0.0F);
		this.shape15 = new ModelRenderer(this, 0, 0);
		this.shape15.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape15.addBox(-4.0F, -13.0F, -4.0F, 8, 5, 8, 0.0F);
		this.shape15_1 = new ModelRenderer(this, 19, 0);
		this.shape15_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape15_1.addBox(-7.0F, -8.0F, -7.0F, 14, 0, 14, 0.0F);
		this.shape15_4 = new ModelRenderer(this, 0, 32);
		this.shape15_4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape15_4.addBox(-7.0F, -8.0F, 6.0F, 14, 1, 1, 0.0F);
		this.shape15_2 = new ModelRenderer(this, 0, 14);
		this.shape15_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape15_2.addBox(-7.0F, -8.0F, -7.0F, 1, 1, 14, 0.0F);
		this.shape15_5 = new ModelRenderer(this, 0, 35);
		this.shape15_5.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape15_5.addBox(6.0F, -8.0F, -7.0F, 1, 1, 14, 0.0F);
		this.shape15_3 = new ModelRenderer(this, 0, 32);
		this.shape15_3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape15_3.addBox(-7.0F, -8.0F, -7.0F, 14, 1, 1, 0.0F);
		this.shape15.addChild(this.shape15_4);
		this.shape15.addChild(this.shape15_6);
		this.shape15.addChild(this.shape15_2);
		this.shape15.addChild(this.shape15_1);
		this.shape15.addChild(this.shape15_5);
		this.shape15.addChild(this.shape15_3);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		bipedHeadwear.showModel = false;
		bipedHead = this.shape15;
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
