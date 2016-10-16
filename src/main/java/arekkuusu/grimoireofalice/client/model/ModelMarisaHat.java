package arekkuusu.grimoireofalice.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMarisaHat extends ModelBiped {

	private final ModelRenderer hatBase;
	private final ModelRenderer hat1;
	private final ModelRenderer hat2;
	private final ModelRenderer hat3;
	private final ModelRenderer hat4;
	private final ModelRenderer hat5;
	private final ModelRenderer hat6;
	private final ModelRenderer hatSide1;
	private final ModelRenderer hatSide2;
	private final ModelRenderer hatSide3;
	private final ModelRenderer ribbonBase;
	private final ModelRenderer ribbon1;
	private final ModelRenderer ribbon2;

	public ModelMarisaHat() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.ribbon1 = new ModelRenderer(this, 37, 56);
		this.ribbon1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ribbon1.addBox(-5.4F, -11.9F, -5.3F, 4, 3, 0, 0.0F);
		this.setRotateAngle(ribbon1, 0.0F, 0.05759586531581287F, 0.07330382858376185F);
		this.hat6 = new ModelRenderer(this, 34, 33);
		this.hat6.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat6.addBox(6.5F, -8.0F, -7.0F, 1, 1, 14, 0.0F);
		this.hatSide1 = new ModelRenderer(this, 34, 0);
		this.hatSide1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hatSide1.addBox(-7.5F, -8.0F, -7.0F, 1, 1, 14, 0.0F);
		this.hatSide2 = new ModelRenderer(this, 34, 49);
		this.hatSide2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hatSide2.addBox(-7.0F, -8.0F, -7.5F, 14, 1, 1, 0.0F);
		this.hat5 = new ModelRenderer(this, 0, 61);
		this.hat5.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat5.addBox(-1.0F, -16.0F, -1.0F, 2, 1, 2, 0.0F);
		this.setRotateAngle(hat5, 0.0F, -0.12217304763960307F, 0.0F);
		this.hatSide3 = new ModelRenderer(this, 34, 52);
		this.hatSide3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hatSide3.addBox(-7.0F, -8.0F, 6.5F, 14, 1, 1, 0.0F);
		this.hat1 = new ModelRenderer(this, 0, 31);
		this.hat1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat1.addBox(-5.0F, -10.0F, -5.0F, 10, 1, 10, 0.0F);
		this.setRotateAngle(hat1, 0.0F, 0.05759586531581287F, 0.0F);
		this.hat2 = new ModelRenderer(this, 0, 42);
		this.hat2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat2.addBox(-4.0F, -12.0F, -4.0F, 8, 2, 8, 0.0F);
		this.setRotateAngle(hat2, 0.0F, -0.03490658503988659F, 0.0F);
		this.hat3 = new ModelRenderer(this, 0, 52);
		this.hat3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat3.addBox(-3.0F, -13.0F, -3.0F, 6, 1, 6, 0.0F);
		this.setRotateAngle(hat3, 0.0F, 0.06981317007977318F, 0.0F);
		this.hat4 = new ModelRenderer(this, 21, 58);
		this.hat4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat4.addBox(-2.0F, -15.0F, -2.0F, 4, 2, 4, 0.0F);
		this.setRotateAngle(hat4, 0.0F, -0.08552113334772216F, 0.0F);
		this.ribbonBase = new ModelRenderer(this, 47, 56);
		this.ribbonBase.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ribbonBase.addBox(-1.0F, -11.0F, -5.1F, 2, 2, 0, 0.0F);
		this.setRotateAngle(ribbonBase, 0.0F, 0.05759586531581287F, 0.0F);
		this.hatBase = new ModelRenderer(this, 0, 16);
		this.hatBase.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hatBase.addBox(-7.0F, -9.0F, -7.0F, 14, 1, 14, 0.0F);
		this.ribbon2 = new ModelRenderer(this, 53, 56);
		this.ribbon2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ribbon2.addBox(1.4F, -11.9F, -5.1F, 4, 3, 0, 0.0F);
		this.setRotateAngle(ribbon2, 0.0F, 0.05759586531581287F, -0.07330382858376185F);
		this.ribbonBase.addChild(this.ribbon1);
		this.hatBase.addChild(this.hat6);
		this.hatBase.addChild(this.hatSide1);
		this.hatBase.addChild(this.hatSide2);
		this.hatBase.addChild(this.hat5);
		this.hatBase.addChild(this.hatSide3);
		this.hatBase.addChild(this.hat1);
		this.hatBase.addChild(this.hat2);
		this.hatBase.addChild(this.hat3);
		this.hatBase.addChild(this.hat4);
		this.hatBase.addChild(this.ribbonBase);
		this.ribbonBase.addChild(this.ribbon2);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		bipedHeadwear.showModel = false;
		bipedHead = this.hatBase;

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
