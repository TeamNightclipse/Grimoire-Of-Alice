package arekkuusu.grimoireofalice.client.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * AuraIchirin - Arekkuusu Created using Tabula 5.1.0
 */
public class ModelIchirinUnzan extends ModelBiped {

	private final float size;
	//private final ModelRenderer chest;
	//private final ModelRenderer leftArm;
	//private final ModelRenderer hat;
	//private final ModelRenderer rightArm;
	//private final ModelRenderer rightArmSuper;
	//private final ModelRenderer leftArmSuper;
	private final ModelRenderer jewel;
	//private final ModelRenderer frontLegs;
	//private final ModelRenderer backLegs;
	//private final ModelRenderer rightLegs;
	//private final ModelRenderer leftLegs;
	private final ModelRenderer head;
	private final ModelRenderer square1;
	private final ModelRenderer square2;
	private final ModelRenderer square3;
	private final ModelRenderer square4;
	private final ModelRenderer square5;
	private final ModelRenderer square6;
	private final ModelRenderer square7;
	private final ModelRenderer square8;
	private final ModelRenderer square9;
	private final ModelRenderer square10;

	public ModelIchirinUnzan(float size) {
		this.size = size;
		textureWidth = 256;
		textureHeight = 384;
		head = new ModelRenderer(this, 0, 0);
		head.setRotationPoint(0.0F, -30.0F, 7.0F);
		head.addBox(-30.0F, -30.0F, 0.0F, 60, 60, 60, 0.0F);
		square1 = new ModelRenderer(this, 0, 122);
		square1.setRotationPoint(0.0F, -30.0F, 7.0F);
		square1.addBox(-49.0F, 9.1F, 15.0F, 38, 38, 38, 0.0F);
		square6 = new ModelRenderer(this, 204, 130);
		square6.setRotationPoint(0.0F, -30.0F, 7.0F);
		square6.addBox(33.0F, -36.0F, -9.0F, 12, 12, 12, 0.0F);
		//this.rightArm = new ModelRenderer(this, 172, 359);
		//this.rightArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		//this.rightArm.addBox(-4.0F, 0.0F, -2.0F, 8, 6, 4, 0.25F);
		//this.chest = new ModelRenderer(this, 227, 359);
		//this.chest.setRotationPoint(5.0F, 2.0F, -0.0F);
		//this.chest.addBox(-1.5F, -2.0F, -2.5F, 5, 12, 5, 0.25F);
		//this.setRotateAngle(chest, 0.0F, 0.0F, -0.10000736613927509F);
		jewel = new ModelRenderer(this, 137, 359);
		jewel.setRotationPoint(0.0F, 0.0F, 0.0F);
		jewel.addBox(-1.0F, 1.5F, -4.0F, 2, 2, 2, 0.25F);
		square9 = new ModelRenderer(this, 0, 265);
		square9.setRotationPoint(0.0F, -35.0F, 7.0F);
		square9.addBox(15.0F, 20.0F, 40.0F, 30, 30, 30, 0.0F);
		square4 = new ModelRenderer(this, 0, 200);
		square4.setRotationPoint(0.0F, -35.0F, 7.0F);
		square4.addBox(-35.0F, -29.0F, 33.0F, 30, 30, 30, 0.0F);
		//this.rightLegs = new ModelRenderer(this, 235, 284);
		//this.rightLegs.setRotationPoint(0.0F, 0.0F, 0.0F);
		//this.rightLegs.addBox(-4.0F, 6.4F, -3.0F, 0, 15, 6, 0.25F);
		//this.leftArmSuper = new ModelRenderer(this, 157, 329);
		//this.leftArmSuper.setRotationPoint(5.0F, 2.0F, -0.0F);
		//this.leftArmSuper.addBox(-4.0F, -2.1F, -3.0F, 8, 5, 6, 0.25F);
		//this.setRotateAngle(leftArmSuper, 0.0F, 0.0F, -0.10000736613927509F);
		square2 = new ModelRenderer(this, 125, 270);
		square2.setRotationPoint(0.0F, -30.0F, 7.0F);
		square2.addBox(-60.0F, 0.0F, 25.0F, 20, 20, 20, 0.0F);
		square3 = new ModelRenderer(this, 181, 4);
		square3.setRotationPoint(0.0F, -30.0F, 7.0F);
		square3.addBox(-60.0F, -5.0F, 22.0F, 10, 10, 10, 0.0F);
		square10 = new ModelRenderer(this, 0, 330);
		square10.setRotationPoint(0.0F, -35.0F, -15.0F);
		square10.addBox(15.0F, 20.0F, 40.0F, 25, 25, 25, 0.0F);
		//this.frontLegs = new ModelRenderer(this, 235, 309);
		//this.frontLegs.setRotationPoint(0.0F, 0.0F, 0.0F);
		//this.frontLegs.addBox(-4.0F, 6.4F, -1.4F, 8, 15, 0, 0.25F);
		//this.setRotateAngle(frontLegs, -0.10821041362364843F, 0.0F, 0.0F);
		//this.leftLegs = new ModelRenderer(this, 211, 284);
		//this.leftLegs.setRotationPoint(0.0F, 0.0F, 0.0F);
		//this.leftLegs.addBox(4.0F, 6.4F, -3.0F, 0, 15, 6, 0.25F);
		//this.hat = new ModelRenderer(this, 205, 335);
		//this.hat.setRotationPoint(0.0F, 0.0F, 0.0F);
		//this.hat.addBox(-4.5F, -8.0F, -4.5F, 9, 8, 9, 0.5F);
		//this.rightArmSuper = new ModelRenderer(this, 125, 329);
		//this.rightArmSuper.setRotationPoint(-5.0F, 1.9F, 0.0F);
		//this.rightArmSuper.addBox(-4.0F, -2.0F, -3.0F, 8, 5, 6, 0.25F);
		//this.setRotateAngle(rightArmSuper, 0.0F, 0.0F, 0.10000736613927509F);
		square8 = new ModelRenderer(this, 130, 200);
		square8.setRotationPoint(0.0F, -35.0F, 7.0F);
		square8.addBox(15.0F, -35.0F, 20.0F, 30, 30, 30, 0.0F);
		square7 = new ModelRenderer(this, 222, 0);
		square7.setRotationPoint(0.0F, -30.0F, 7.0F);
		square7.addBox(40.0F, -25.0F, -14.0F, 8, 8, 8, 0.0F);
		//this.backLegs = new ModelRenderer(this, 211, 309);
		//this.backLegs.setRotationPoint(0.0F, 0.0F, 0.0F);
		//this.backLegs.addBox(-4.0F, 6.4F, 1.3F, 8, 15, 0, 0.25F);
		///this.setRotateAngle(backLegs, 0.10821041362364843F, 0.0F, 0.0F);
		square5 = new ModelRenderer(this, 181, 25);
		square5.setRotationPoint(0.0F, -30.0F, 7.0F);
		square5.addBox(23.0F, -33.0F, -3.0F, 16, 16, 16, 0.0F);
		//this.leftArm = new ModelRenderer(this, 200, 359);
		//this.leftArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		//this.leftArm.addBox(-3.5F, -2.0F, -2.5F, 5, 12, 5, 0.25F);
		//this.setRotateAngle(leftArm, 0.0F, 0.0F, 0.10000736613927509F);
		//rightArm.addChild(rightArmSuper);
		//rightArm.addChild(leftArmSuper);
		//rightArm.addChild(frontLegs);
		//rightArm.addChild(backLegs);
		//rightArm.addChild(rightLegs);
		//rightArm.addChild(leftLegs);
		//rightArm.addChild(jewel);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 0.95F);
		head.render(size);
		GlStateManager.disableBlend();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 0.9F);
		square1.render(size);
		square8.render(size);
		GlStateManager.disableBlend();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 0.8F);
		square2.render(size);
		square3.render(size);
		square4.render(size);
		square5.render(size);
		square6.render(size);
		square7.render(size);
		square9.render(size);
		square10.render(size);
		GlStateManager.disableBlend();

		bipedHeadwear.showModel = false;
		//bipedHead.showModel = true;
		bipedRightArm.showModel = false;
		bipedLeftArm.showModel = false;

		//bipedHead = this.hat;
		bipedBody = jewel;
		//bipedRightArm = this.leftArm;
		//bipedLeftArm = this.chest;

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
