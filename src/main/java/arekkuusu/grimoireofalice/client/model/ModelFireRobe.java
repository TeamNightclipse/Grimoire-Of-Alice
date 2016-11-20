package arekkuusu.grimoireofalice.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelPlayer - Either Mojang or a mod author
 * Created using Tabula 5.1.0
 */
public class ModelFireRobe extends ModelBiped {

	public ModelRenderer leftArm;
	public ModelRenderer rightArm;
	public ModelRenderer chest;
	public ModelRenderer backRobe;
	public ModelRenderer leftRobe;
	public ModelRenderer rightRobe;
	public ModelRenderer frontRobe;

	public ModelFireRobe() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.frontRobe = new ModelRenderer(this, 4, 27);
		this.frontRobe.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.frontRobe.addBox(-4.0F, 10.0F, 0.6F, 8, 3, 0, 0.25F);
		this.setRotateAngle(frontRobe, -0.2617993877991494F, 0.0F, 0.0F);
		this.rightRobe = new ModelRenderer(this, 11, 9);
		this.rightRobe.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.rightRobe.addBox(4.0F, 11.0F, -5.8F, 0, 14, 4, 0.25F);
		this.setRotateAngle(rightRobe, 0.2617993877991494F, 0.0F, 0.0F);
		this.backRobe = new ModelRenderer(this, 24, 18);
		this.backRobe.setRotationPoint(0.0F, 12.5F, 4.0F);
		this.backRobe.addBox(-4.0F, -0.5F, -1.9F, 8, 12, 0, 0.25F);
		this.setRotateAngle(backRobe, 0.2617993877991494F, 0.0F, 0.0F);
		this.chest = new ModelRenderer(this, 0, 0);
		this.chest.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.chest.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.25F);
		this.leftArm = new ModelRenderer(this, 24, 0);
		this.leftArm.setRotationPoint(5.0F, 2.0F, -0.0F);
		this.leftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 14, 4, 0.25F);
		this.setRotateAngle(leftArm, 0.0F, 0.0F, -0.10000736613927509F);
		this.leftRobe = new ModelRenderer(this, 11, 9);
		this.leftRobe.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leftRobe.addBox(-4.0F, 11.0F, -5.8F, 0, 14, 4, 0.25F);
		this.setRotateAngle(leftRobe, 0.2617993877991494F, 0.0F, 0.0F);
		this.rightArm = new ModelRenderer(this, 24, 0);
		this.rightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		this.rightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 14, 4, 0.25F);
		this.setRotateAngle(rightArm, 0.0F, 0.0F, 0.10000736613927509F);
	}

    @Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		bipedRightArm = rightArm;
		bipedBody = chest;
		bipedLeftArm = leftArm;
		backRobe.render(scale);
		frontRobe.render(scale);
		rightRobe.render(scale);
		leftRobe.render(scale);
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
