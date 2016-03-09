/**
 * This class was created by <Katrix>. It's distributed as
 * part of the Journey To Gensokyo Mod. Get the Source Code in github:
 * https://github.com/Katrix-/JTG
 *
 * Journey To Gensokyo is Open Source and distributed under the
 * a modifed Botania license: https://github.com/Katrix-/JTG/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.client.model;

import net.minecraft.client.model.ModelRenderer;

public class ModelMarisa extends ModelBipedDanmaku {

	public ModelRenderer cape;
	public ModelRenderer ribbonBackRight;
	public ModelRenderer ribbonBackLeft;
	public ModelRenderer hatBase;
	public ModelRenderer rightBraid;
	public ModelRenderer leftBraid;
	public ModelRenderer hatBase2;
	public ModelRenderer hatBase3;
	public ModelRenderer hatBase4;
	public ModelRenderer hatRim;
	public ModelRenderer hatRibbonLeft;
	public ModelRenderer hatRibbonRight;
	public ModelRenderer bipedRightLeg2;
	public ModelRenderer bipedLeftLeg2;
	public ModelRenderer bipedLeftArm2;
	public ModelRenderer bipedRightArm2;

	public ModelMarisa() {
		textureWidth = 128;
		textureHeight = 64;
		hatBase3 = new ModelRenderer(this, 112, 16);
		hatBase3.setRotationPoint(0.0F, -9.0F, 0.0F);
		hatBase3.addBox(-2.0F, -3.0F, -2.0F, 4, 3, 4, 0.0F);
		setRotateAngle(hatBase3, -0.5410520681182421F, 0.0F, 0.0F);
		hatRim = new ModelRenderer(this, 64, 16);
		hatRim.setRotationPoint(0.0F, -6.0F, 0.0F);
		hatRim.addBox(-6.0F, 0.0F, -6.0F, 12, 1, 12, 0.0F);
		bipedHead = new ModelRenderer(this, 0, 0);
		bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		bipedLeftArm2 = new ModelRenderer(this, 56, 0);
		bipedLeftArm2.setRotationPoint(0.0F, 3.0F, 0.0F);
		bipedLeftArm2.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
		bipedLeftLeg = new ModelRenderer(this, 36, 22);
		bipedLeftLeg.setRotationPoint(1.5F, 9.0F, 0.0F);
		bipedLeftLeg.addBox(-1.5F, 0.0F, -2.0F, 3, 6, 4, 0.0F);
		rightBraid = new ModelRenderer(this, 0, 32);
		rightBraid.setRotationPoint(-3.0F, -2.0F, -3.0F);
		rightBraid.addBox(-1.0F, 0.0F, -1.0F, 1, 4, 1, 0.0F);
		setRotateAngle(rightBraid, -0.22759093446006054F, 0.0F, 0.0F);
		bipedRightArm2 = new ModelRenderer(this, 56, 0);
		bipedRightArm2.setRotationPoint(0.0F, 3.0F, 0.0F);
		bipedRightArm2.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
		bipedBody = new ModelRenderer(this, 32, 8);
		bipedBody.setRotationPoint(0.0F, 3.0F, 0.0F);
		bipedBody.addBox(-3.0F, 0.0F, -2.0F, 6, 9, 4, 0.0F);
		hatRibbonLeft = new ModelRenderer(this, 108, 12);
		hatRibbonLeft.setRotationPoint(0.5F, -6.0F, -4.0F);
		hatRibbonLeft.addBox(0.0F, -2.0F, -1.0F, 4, 3, 1, 0.0F);
		setRotateAngle(hatRibbonLeft, 0.0F, 0.0F, -0.3490658503988659F);
		skirtBottom = new ModelRenderer(this, 16, 32);
		skirtBottom.setRotationPoint(0.0F, 4.0F, 0.0F);
		skirtBottom.addBox(-5.0F, 0.0F, -5.0F, 10, 7, 10, 0.0F);
		bipedRightLeg = new ModelRenderer(this, 36, 22);
		bipedRightLeg.setRotationPoint(-1.5F, 9.0F, 0.0F);
		bipedRightLeg.addBox(-1.5F, 0.0F, -2.0F, 3, 6, 4, 0.0F);
		bipedRightArm = new ModelRenderer(this, 48, 0);
		bipedRightArm.setRotationPoint(-4.0F, 2.0F, 0.0F);
		bipedRightArm.addBox(-1.0F, -1.0F, -1.0F, 2, 4, 2, 0.0F);
		setRotateAngle(bipedRightArm, -0.7000515629749255F, 0.0F, -0.6457718232379019F);
		skirtTop = new ModelRenderer(this, 0, 16);
		skirtTop.setRotationPoint(0.0F, 6.0F, 0.0F);
		skirtTop.addBox(-4.0F, 0.0F, -4.0F, 8, 4, 8, 0.0F);
		hatRibbonRight = new ModelRenderer(this, 96, 12);
		hatRibbonRight.setRotationPoint(-0.5F, -6.0F, -4.0F);
		hatRibbonRight.addBox(-4.0F, -2.0F, -1.0F, 4, 3, 1, 0.0F);
		setRotateAngle(hatRibbonRight, 0.0F, 0.0F, 0.3490658503988659F);
		longHair = new ModelRenderer(this, 0, 50);
		longHair.setRotationPoint(0.0F, -1.0F, 4.0F);
		longHair.addBox(-4.0F, 0.0F, -3.0F, 8, 5, 3, 0.0F);
		setRotateAngle(longHair, 0.17453292519943295F, 0.0F, 0.0F);
		hatBase = new ModelRenderer(this, 64, 0);
		hatBase.setRotationPoint(0.0F, -2.0F, 0.0F);
		hatBase.addBox(-4.0F, -8.0F, -4.0F, 8, 3, 8, 0.0F);
		bipedLeftLeg2 = new ModelRenderer(this, 50, 22);
		bipedLeftLeg2.setRotationPoint(0.0F, 6.0F, 0.0F);
		bipedLeftLeg2.addBox(-1.5F, 0.0F, -2.0F, 3, 6, 4, 0.0F);
		bipedLeftArm = new ModelRenderer(this, 48, 0);
		bipedLeftArm.setRotationPoint(4.0F, 2.0F, 0.0F);
		bipedLeftArm.addBox(-1.0F, -1.0F, -1.0F, 2, 4, 2, 0.0F);
		setRotateAngle(bipedLeftArm, -0.7000515629749255F, 0.0F, 0.6457718232379019F);
		hatBase4 = new ModelRenderer(this, 112, 24);
		hatBase4.setRotationPoint(0.0F, -11.0F, 1.0F);
		hatBase4.addBox(-1.0F, -3.0F, -1.0F, 2, 3, 2, 0.0F);
		setRotateAngle(hatBase4, -0.8203047484373349F, 0.0F, 0.0F);
		bipedRightLeg2 = new ModelRenderer(this, 50, 22);
		bipedRightLeg2.setRotationPoint(0.0F, 6.0F, 0.0F);
		bipedRightLeg2.addBox(-1.5F, 0.0F, -2.0F, 3, 6, 4, 0.0F);
		leftBraid = new ModelRenderer(this, 0, 32);
		leftBraid.setRotationPoint(3.0F, -2.0F, -3.0F);
		leftBraid.addBox(0.0F, 0.0F, -1.0F, 1, 4, 1, 0.0F);
		setRotateAngle(leftBraid, -0.22759093446006054F, 0.0F, 0.0F);
		cape = new ModelRenderer(this, 32, 56);
		cape.setRotationPoint(0.0F, 0.0F, 0.0F);
		cape.addBox(-5.0F, 0.0F, -2.5F, 10, 3, 5, 0.0F);
		ribbonBackRight = new ModelRenderer(this, 64, 36);
		ribbonBackRight.setRotationPoint(0.0F, 3.5F, 2.0F);
		ribbonBackRight.addBox(-3.5F, 0.0F, 0.0F, 4, 3, 1, 0.0F);
		setRotateAngle(ribbonBackRight, 0.0F, 0.0F, 0.3490658503988659F);
		ribbonBackLeft = new ModelRenderer(this, 64, 32);
		ribbonBackLeft.setRotationPoint(0.0F, 3.5F, 2.0F);
		ribbonBackLeft.addBox(-0.5F, 0.0F, 0.0F, 4, 3, 1, 0.0F);
		setRotateAngle(ribbonBackLeft, 0.0F, 0.0F, -0.3490658503988659F);
		hatBase2 = new ModelRenderer(this, 96, 2);
		hatBase2.setRotationPoint(0.0F, -7.0F, 0.0F);
		hatBase2.addBox(-3.0F, -3.0F, -3.0F, 6, 3, 6, 0.0F);
		setRotateAngle(hatBase2, -0.2792526803190927F, 0.0F, 0.0F);
		setChild();
	}

	@Override
	public void setChild() {
		super.setChild();
		bipedHead.addChild(leftBraid);
		bipedHead.addChild(rightBraid);
		bipedHead.addChild(hatBase);
		hatBase.addChild(hatBase2);
		hatBase.addChild(hatBase3);
		hatBase.addChild(hatBase4);
		hatBase.addChild(hatRim);
		hatBase.addChild(hatRibbonLeft);
		hatBase.addChild(hatRibbonRight);
		bipedLeftArm.addChild(bipedLeftArm2);
		bipedRightArm.addChild(bipedRightArm2);
		bipedLeftLeg.addChild(bipedLeftLeg2);
		bipedRightLeg.addChild(bipedRightLeg2);
		bipedBody.addChild(cape);
		bipedBody.addChild(ribbonBackRight);
		bipedBody.addChild(ribbonBackLeft);
	}
}
