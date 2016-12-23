/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMarisaHat extends ModelBiped {

	private final ModelRenderer hatBase;
	private final ModelRenderer hat0;
	private final ModelRenderer hat1;
	private final ModelRenderer hat2;
	private final ModelRenderer hat3;
	private final ModelRenderer hat4;
	private final ModelRenderer hat5;
	private final ModelRenderer hat6;
	private final ModelRenderer hat7;
	private final ModelRenderer hat8;
	private final ModelRenderer ribbonMiddle;
	private final ModelRenderer ribbonRight;
	private final ModelRenderer ribbonLeft;

	public ModelMarisaHat() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.hat6 = new ModelRenderer(this, 34, 0);
		this.hat6.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat6.addBox(-7.5F, -8.0F, -7.0F, 1, 1, 14, 0.0F);
		this.ribbonRight = new ModelRenderer(this, 0, 4);
		this.ribbonRight.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ribbonRight.addBox(-5.3F, -12.3F, -5.4F, 4, 3, 1, 0.0F);
		this.setRotateAngle(ribbonRight, 0.0F, 0.0F, 0.091106186954104F);
		this.hatBase = new ModelRenderer(this, 0, 16);
		this.hatBase.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hatBase.addBox(-7.0F, -9.0F, -7.0F, 14, 1, 14, 0.0F);
		this.hat0 = new ModelRenderer(this, 0, 31);
		this.hat0.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat0.addBox(-5.0F, -10.0F, -4.5F, 10, 1, 10, 0.0F);
		this.setRotateAngle(hat0, 0.0F, 0.05759586531581287F, 0.0F);
		this.hat1 = new ModelRenderer(this, 0, 42);
		this.hat1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat1.addBox(-4.0F, -12.0F, -4.0F, 8, 3, 8, 0.0F);
		this.setRotateAngle(hat1, -0.091106186954104F, 0.0F, 0.0F);
		this.ribbonLeft = new ModelRenderer(this, 0, 4);
		this.ribbonLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ribbonLeft.addBox(1.3F, -12.3F, -5.4F, 4, 3, 1, 0.0F);
		this.setRotateAngle(ribbonLeft, 0.0F, 0.0F, -0.091106186954104F);
		this.hat5 = new ModelRenderer(this, 34, 33);
		this.hat5.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat5.addBox(6.5F, -8.0F, -7.0F, 1, 1, 14, 0.0F);
		this.hat3 = new ModelRenderer(this, 21, 58);
		this.hat3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat3.addBox(-1.5F, -15.0F, -2.4F, 4, 2, 4, 0.0F);
		this.setRotateAngle(hat3, -0.18203784098300857F, -0.08552113334772216F, 0.0F);
		this.hat2 = new ModelRenderer(this, 0, 52);
		this.hat2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat2.addBox(-3.0F, -14.0F, -3.0F, 6, 2, 6, 0.0F);
		this.setRotateAngle(hat2, -0.136659280431156F, 0.06981317007977318F, 0.0F);
		this.hat8 = new ModelRenderer(this, 34, 52);
		this.hat8.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat8.addBox(-7.0F, -8.0F, 6.5F, 14, 1, 1, 0.0F);
		this.hat4 = new ModelRenderer(this, 0, 61);
		this.hat4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat4.addBox(-0.5F, -16.0F, -1.3F, 2, 1, 2, 0.0F);
		this.setRotateAngle(hat4, -0.22759093446006054F, -0.12217304763960307F, 0.0F);
		this.hat7 = new ModelRenderer(this, 34, 49);
		this.hat7.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat7.addBox(-7.0F, -8.0F, -7.5F, 14, 1, 1, 0.0F);
		this.ribbonMiddle = new ModelRenderer(this, 0, 0);
		this.ribbonMiddle.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ribbonMiddle.addBox(-1.0F, -10.0F, -5.5F, 2, 1, 1, 0.0F);
		this.hatBase.addChild(this.hat6);
		this.hatBase.addChild(this.ribbonRight);
		this.hatBase.addChild(this.hat0);
		this.hatBase.addChild(this.hat1);
		this.hatBase.addChild(this.ribbonLeft);
		this.hatBase.addChild(this.hat5);
		this.hatBase.addChild(this.hat3);
		this.hatBase.addChild(this.hat2);
		this.hatBase.addChild(this.hat8);
		this.hatBase.addChild(this.hat4);
		this.hatBase.addChild(this.hat7);
		this.hatBase.addChild(this.ribbonMiddle);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		bipedHeadwear.showModel = false;
		bipedHead = hatBase;

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
