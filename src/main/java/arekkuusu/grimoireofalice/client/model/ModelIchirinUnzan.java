/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
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

	private boolean renderRight;
	private boolean renderLeft;

	private final float size;
	private final ModelRenderer jewel;
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

	private final ModelRenderer fistLeft;
	private final ModelRenderer fistRight;

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
		jewel = new ModelRenderer(this, 137, 359);
		jewel.setRotationPoint(0.0F, 0.0F, 0.0F);
		jewel.addBox(-1.0F, 1.5F, -4.0F, 2, 2, 2, 0.25F);
		square9 = new ModelRenderer(this, 0, 265);
		square9.setRotationPoint(0.0F, -35.0F, 7.0F);
		square9.addBox(15.0F, 20.0F, 40.0F, 30, 30, 30, 0.0F);
		square4 = new ModelRenderer(this, 0, 200);
		square4.setRotationPoint(0.0F, -35.0F, 7.0F);
		square4.addBox(-35.0F, -29.0F, 33.0F, 30, 30, 30, 0.0F);
		square2 = new ModelRenderer(this, 125, 270);
		square2.setRotationPoint(0.0F, -30.0F, 7.0F);
		square2.addBox(-60.0F, 0.0F, 25.0F, 20, 20, 20, 0.0F);
		square3 = new ModelRenderer(this, 181, 4);
		square3.setRotationPoint(0.0F, -30.0F, 7.0F);
		square3.addBox(-60.0F, -5.0F, 22.0F, 10, 10, 10, 0.0F);
		square10 = new ModelRenderer(this, 0, 330);
		square10.setRotationPoint(0.0F, -35.0F, -15.0F);
		square10.addBox(15.0F, 20.0F, 40.0F, 25, 25, 25, 0.0F);
		square8 = new ModelRenderer(this, 130, 200);
		square8.setRotationPoint(0.0F, -35.0F, 7.0F);
		square8.addBox(15.0F, -35.0F, 20.0F, 30, 30, 30, 0.0F);
		square7 = new ModelRenderer(this, 222, 0);
		square7.setRotationPoint(0.0F, -30.0F, 7.0F);
		square7.addBox(40.0F, -25.0F, -14.0F, 8, 8, 8, 0.0F);
		square5 = new ModelRenderer(this, 181, 25);
		square5.setRotationPoint(0.0F, -30.0F, 7.0F);
		square5.addBox(23.0F, -33.0F, -3.0F, 16, 16, 16, 0.0F);

		fistLeft = new ModelRenderer(this, 0, 0);
		fistLeft.setRotationPoint(5.0F, 2.0F, -0.0F);
		fistLeft.addBox(9.0F, 0.0F, -7.0F, 10, 10, 10, 0.25F);
		setRotateAngle(fistLeft, 0.3490658503988659F, 0.0F, -0.10000736613927509F);
		fistRight = new ModelRenderer(this, 0, 21);
		fistRight.setRotationPoint(-5.0F, 2.0F, 0.0F);
		fistRight.addBox(-19.0F, 0.0F, -7.0F, 10, 10, 10, 0.25F);
		setRotateAngle(fistRight, 0.3490658503988659F, 0.0F, 0.10000736613927509F);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float age, float netHeadYaw, float headPitch, float scale) {
		float maxUpAndDown = 0.05F;
		float speed = 2;
		float angle = 0;

		float toDegrees = (float) Math.PI / 180F;
		angle += speed * age;
		if (angle > 360) angle -= 360;

		GlStateManager.pushMatrix();
		GlStateManager.translate(0, maxUpAndDown * Math.sin(angle * toDegrees), 0);
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
		GlStateManager.popMatrix();

		bipedHeadwear.showModel = false;

		bipedRightArm = fistRight;
		bipedLeftArm = fistLeft;

		bipedBody = jewel;

		this.setRotationAngles(limbSwing, limbSwingAmount, age, netHeadYaw, headPitch, scale, entity);
		GlStateManager.pushMatrix();

		if (this.isChild) {
			GlStateManager.scale(0.75F, 0.75F, 0.75F);
			GlStateManager.translate(0.0F, 16.0F * scale, 0.0F);
			this.bipedHead.render(scale);
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
			GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
			this.bipedBody.render(scale);

			renderFists(scale);

			this.bipedRightLeg.render(scale);
			this.bipedLeftLeg.render(scale);
			this.bipedHeadwear.render(scale);
		}
		else {
			if (entity.isSneaking()) {
				GlStateManager.translate(0.0F, 0.2F, 0.0F);
			}

			this.bipedHead.render(scale);
			this.bipedBody.render(scale);

			renderFists(scale);

			this.bipedRightLeg.render(scale);
			this.bipedLeftLeg.render(scale);
			this.bipedHeadwear.render(scale);
		}

		GlStateManager.popMatrix();
	}

	private void renderFists(float scale){
		if(renderRight) {
			GlStateManager.pushMatrix();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 0.95F);
			GlStateManager.translate(this.bipedRightArm.offsetX, this.bipedRightArm.offsetY, this.bipedRightArm.offsetZ);
			GlStateManager.translate(this.bipedRightArm.rotationPointX * scale, this.bipedRightArm.rotationPointY * scale, this.bipedRightArm.rotationPointZ * scale);
			GlStateManager.scale(1.5D, 1.5D, 1.5D);
			GlStateManager.translate(-this.bipedRightArm.offsetX, -this.bipedRightArm.offsetY, -this.bipedRightArm.offsetZ);
			GlStateManager.translate(-this.bipedRightArm.rotationPointX * scale, -this.bipedRightArm.rotationPointY * scale, -this.bipedRightArm.rotationPointZ * scale);
			this.bipedRightArm.render(scale);
			GlStateManager.disableBlend();
			GlStateManager.popMatrix();
		}

		if(renderLeft) {
			GlStateManager.pushMatrix();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 0.95F);
			GlStateManager.translate(this.bipedLeftArm.offsetX, this.bipedLeftArm.offsetY, this.bipedLeftArm.offsetZ);
			GlStateManager.translate(this.bipedLeftArm.rotationPointX * scale, this.bipedLeftArm.rotationPointY * scale, this.bipedLeftArm.rotationPointZ * scale);
			GlStateManager.scale(1.5D, 1.5D, 1.5D);
			GlStateManager.translate(-this.bipedLeftArm.offsetX, -this.bipedLeftArm.offsetY, -this.bipedLeftArm.offsetZ);
			GlStateManager.translate(-this.bipedLeftArm.rotationPointX * scale, -this.bipedLeftArm.rotationPointY * scale, -this.bipedLeftArm.rotationPointZ * scale);
			this.bipedLeftArm.render(scale);
			GlStateManager.disableBlend();
			GlStateManager.popMatrix();
		}
	}

	public boolean isRenderRight() {
		return renderRight;
	}

	public void setRenderRight(boolean renderRight) {
		this.renderRight = renderRight;
	}

	public boolean isRenderLeft() {
		return renderLeft;
	}

	public void setRenderLeft(boolean renderLeft) {
		this.renderLeft = renderLeft;
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

