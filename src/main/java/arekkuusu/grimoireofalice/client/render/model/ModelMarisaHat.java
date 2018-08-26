/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.render.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMarisaHat extends ModelBiped {

	private final ModelRenderer hat;
	private final ModelRenderer ribbonBase;
	private final ModelRenderer hat0;
	private final ModelRenderer hat1;
	private final ModelRenderer hat2;
	private final ModelRenderer hatTop;
	private final ModelRenderer hatWest;
	private final ModelRenderer hatEast;
	private final ModelRenderer hatNorth;
	private final ModelRenderer hatSouth;
	private final ModelRenderer ribbonGolden;
	private final ModelRenderer ribbonWhiteRight;
	private final ModelRenderer ribbonWhiteLeft;

	public ModelMarisaHat() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.hat0 = new ModelRenderer(this, 0, 42);
		this.hat0.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat0.addBox(-4.0F, -12.0F, -4.0F, 8, 3, 8, 0.0F);
		this.setRotateAngle(hat0, -0.091106186954104F, 0.0F, 0.0F);
		this.hatWest = new ModelRenderer(this, 34, 33);
		this.hatWest.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hatWest.addBox(6.5F, -8.0F, -7.0F, 1, 1, 14, 0.0F);
		this.hat = new ModelRenderer(this, 0, 16);
		this.hat.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat.addBox(-7.0F, -9.0F, -7.0F, 14, 1, 14, 0.0F);
		this.hatEast = new ModelRenderer(this, 34, 0);
		this.hatEast.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hatEast.addBox(-7.5F, -8.0F, -7.0F, 1, 1, 14, 0.0F);
		this.hatTop = new ModelRenderer(this, 0, 61);
		this.hatTop.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hatTop.addBox(-0.5F, -15.9F, -1.3F, 2, 1, 2, 0.0F);
		this.setRotateAngle(hatTop, -0.22759093446006054F, -0.12217304763960307F, 0.0F);
		this.hatSouth = new ModelRenderer(this, 34, 52);
		this.hatSouth.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hatSouth.addBox(-7.0F, -8.0F, 6.5F, 14, 1, 1, 0.0F);
		this.ribbonGolden = new ModelRenderer(this, 0, 0);
		this.ribbonGolden.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ribbonGolden.addBox(-1.0F, -10.0F, -5.5F, 2, 1, 1, 0.0F);
		this.ribbonBase = new ModelRenderer(this, 0, 31);
		this.ribbonBase.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ribbonBase.addBox(-5.0F, -10.0F, -4.5F, 10, 1, 10, 0.0F);
		this.setRotateAngle(ribbonBase, 0.0F, 0.05759586531581287F, 0.0F);
		this.hat1 = new ModelRenderer(this, 0, 52);
		this.hat1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat1.addBox(-3.0F, -13.8F, -3.0F, 6, 2, 6, 0.0F);
		this.setRotateAngle(hat1, -0.136659280431156F, 0.06981317007977318F, 0.0F);
		this.ribbonWhiteRight = new ModelRenderer(this, 0, 4);
		this.ribbonWhiteRight.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ribbonWhiteRight.addBox(-5.3F, -12.3F, -5.4F, 4, 3, 1, 0.0F);
		this.setRotateAngle(ribbonWhiteRight, 0.0F, 0.0F, 0.091106186954104F);
		this.hat2 = new ModelRenderer(this, 21, 58);
		this.hat2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat2.addBox(-1.5F, -15.0F, -2.4F, 4, 2, 4, 0.0F);
		this.setRotateAngle(hat2, -0.18203784098300857F, -0.08552113334772216F, 0.0F);
		this.ribbonWhiteLeft = new ModelRenderer(this, 0, 4);
		this.ribbonWhiteLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ribbonWhiteLeft.addBox(1.3F, -12.3F, -5.4F, 4, 3, 1, 0.0F);
		this.setRotateAngle(ribbonWhiteLeft, 0.0F, 0.0F, -0.091106186954104F);
		this.hatNorth = new ModelRenderer(this, 34, 49);
		this.hatNorth.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hatNorth.addBox(-7.0F, -8.0F, -7.5F, 14, 1, 1, 0.0F);
		this.hat.addChild(this.hat0);
		this.hat.addChild(this.hatWest);
		this.hat.addChild(this.hatEast);
		this.hat.addChild(this.hatTop);
		this.hat.addChild(this.hatSouth);
		this.hat.addChild(this.ribbonGolden);
		this.hat.addChild(this.ribbonBase);
		this.hat.addChild(this.hat1);
		this.hat.addChild(this.ribbonWhiteRight);
		this.hat.addChild(this.hat2);
		this.hat.addChild(this.ribbonWhiteLeft);
		this.hat.addChild(this.hatNorth);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		bipedHead = hat;
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale * 1.25F, entity);
		GlStateManager.pushMatrix();
		if(this.isChild) {
			GlStateManager.scale(0.75F, 0.75F, 0.75F);
			GlStateManager.translate(0.0F, 16.0F * scale, 0.0F);
			this.bipedHead.render(scale);
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
			GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
		} else {
			GlStateManager.translate(0, 0.25, 0);
			if(entity.isSneaking()) {
				GlStateManager.translate(0.0F, 0.25F, 0.0F);
			}
			this.bipedHead.render(scale);
		}
		GlStateManager.popMatrix();
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
