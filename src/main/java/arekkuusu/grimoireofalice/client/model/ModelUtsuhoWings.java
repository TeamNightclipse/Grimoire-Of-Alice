package arekkuusu.grimoireofalice.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelUtsuhoWings - Arekkuusu Created using Tabula 5.1.0
 */
public class ModelUtsuhoWings extends ModelBiped {

	private final ModelRenderer wingLeft;
	private final ModelRenderer wingRight;
	private final ModelRenderer cape;
	private final ModelRenderer chest;

    public ModelUtsuhoWings() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.wingLeft = new ModelRenderer(this, 0, 0);
        this.wingLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wingLeft.addBox(-20.0F, 0.5F, 2.5F, 40, 19, 0, 0.0F);
        this.setRotateAngle(wingLeft, 0.24434609527920614F, 0.0F, 0.0F);
        this.wingRight = new ModelRenderer(this, 0, 20);
        this.wingRight.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wingRight.addBox(0.0F, -6.0F, 2.0F, 30, 21, 0, 0.0F);
        this.setRotateAngle(wingRight, 0.17453292519943295F, 0.0F, 0.12217304763960307F);
        this.cape = new ModelRenderer(this, 0, 42);
        this.cape.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.cape.addBox(-30.0F, -6.0F, 2.0F, 30, 21, 0, 0.0F);
        this.setRotateAngle(cape, 0.17453292519943295F, 0.0F, -0.12217304763960307F);
		this.chest = new ModelRenderer(this, 104, 0);
		this.chest.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.chest.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.25F);
    }

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		wingLeft.render(scale);
		wingRight.render(scale);
		cape.render(scale);
		bipedBody = chest;

		bipedRightArm.showModel = false;
		bipedLeftArm.showModel = false;
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
