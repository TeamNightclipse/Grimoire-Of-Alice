/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.render.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * AuraIchirin - Arekkuusu Created using Tabula 5.1.0
 */

@SideOnly(Side.CLIENT)
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
		if(angle > 360) {
			angle -= 360;
		}

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

		boolean flag = entity instanceof EntityLivingBase && ((EntityLivingBase) entity).getTicksElytraFlying() > 4;
		this.bipedHead.rotateAngleY = netHeadYaw * 0.017453292F;

		if(flag) {
			this.bipedHead.rotateAngleX = -((float) Math.PI / 4F);
		}
		else {
			this.bipedHead.rotateAngleX = headPitch * 0.017453292F;
		}

		this.jewel.rotateAngleY = 0.0F;
		this.fistRight.rotationPointZ = 0.0F;
		this.fistRight.rotationPointX = -5.0F;
		this.fistLeft.rotationPointZ = 0.0F;
		this.fistLeft.rotationPointX = 5.0F;
		float f = 1.0F;

		if(flag) {
			f = (float) (entity.motionX * entity.motionX + entity.motionY * entity.motionY + entity.motionZ * entity.motionZ);
			f = f / 0.2F;
			f = f * f * f;
		}

		if(f < 1.0F) {
			f = 1.0F;
		}

		this.fistRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
		this.fistLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
		this.fistRight.rotateAngleZ = 0.0F;
		this.fistLeft.rotateAngleZ = 0.0F;

		if(this.isRiding) {
			this.fistRight.rotateAngleX += -((float) Math.PI / 5F);
			this.fistLeft.rotateAngleX += -((float) Math.PI / 5F);
		}

		this.fistRight.rotateAngleY = 0.0F;
		this.fistRight.rotateAngleZ = 0.0F;

		switch(this.leftArmPose) {
			case EMPTY:
				this.fistLeft.rotateAngleY = 0.0F;
				break;
			case BLOCK:
				this.fistLeft.rotateAngleX = this.fistLeft.rotateAngleX * 0.5F - 0.9424779F;
				this.fistLeft.rotateAngleY = 0.5235988F;
				break;
			case ITEM:
				this.fistLeft.rotateAngleX = this.fistLeft.rotateAngleX * 0.5F - ((float) Math.PI / 10F);
				this.fistLeft.rotateAngleY = 0.0F;
		}

		switch(this.rightArmPose) {
			case EMPTY:
				this.fistRight.rotateAngleY = 0.0F;
				break;
			case BLOCK:
				this.fistRight.rotateAngleX = this.fistRight.rotateAngleX * 0.5F - 0.9424779F;
				this.fistRight.rotateAngleY = -0.5235988F;
				break;
			case ITEM:
				this.fistRight.rotateAngleX = this.fistRight.rotateAngleX * 0.5F - ((float) Math.PI / 10F);
				this.fistRight.rotateAngleY = 0.0F;
		}

		if(this.swingProgress > 0.0F) {
			EnumHandSide enumhandside = this.getMainHand(entity);
			ModelRenderer modelrenderer = this.getArmForSide(enumhandside);
			float f1 = this.swingProgress;
			this.jewel.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float) Math.PI * 2F)) * 0.2F;

			if(enumhandside == EnumHandSide.LEFT) {
				this.jewel.rotateAngleY *= -1.0F;
			}

			this.fistRight.rotationPointZ = MathHelper.sin(this.jewel.rotateAngleY) * 5.0F;
			this.fistRight.rotationPointX = -MathHelper.cos(this.jewel.rotateAngleY) * 5.0F;
			this.fistLeft.rotationPointZ = -MathHelper.sin(this.jewel.rotateAngleY) * 5.0F;
			this.fistLeft.rotationPointX = MathHelper.cos(this.jewel.rotateAngleY) * 5.0F;
			this.fistRight.rotateAngleY += this.jewel.rotateAngleY;
			this.fistLeft.rotateAngleY += this.jewel.rotateAngleY;
			this.fistLeft.rotateAngleX += this.jewel.rotateAngleY;

			f1 = 20F - this.swingProgress;
			f1 *= f1;
			f1 *= f1;
			f1 = 1.0F - f1;
			float f2 = MathHelper.sin(f1 * (float) Math.PI);
			float f3 = MathHelper.sin(this.swingProgress * (float) Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
			modelrenderer.rotateAngleX = (float) ((double) modelrenderer.rotateAngleX - ((double) f2 * 1.2D + (double) f3));
			modelrenderer.rotateAngleY += this.jewel.rotateAngleY * 4.0F;
			modelrenderer.rotateAngleZ += MathHelper.sin(this.swingProgress * (float) Math.PI) * -0.4F;
		}

		if(this.isSneak) {
			this.jewel.rotateAngleX = 0.5F;
			this.fistRight.rotateAngleX += 0.4F;
			this.fistLeft.rotateAngleX += 0.4F;
		}
		else {
			this.jewel.rotateAngleX = 0.0F;
		}

		this.fistRight.rotateAngleZ += MathHelper.cos(age * 0.09F) * 0.05F + 0.05F;
		this.fistLeft.rotateAngleZ -= MathHelper.cos(age * 0.09F) * 0.05F + 0.05F;
		this.fistRight.rotateAngleX += MathHelper.sin(age * 0.067F) * 0.05F;
		this.fistLeft.rotateAngleX -= MathHelper.sin(age * 0.067F) * 0.05F;

		GlStateManager.pushMatrix();

		if(this.isChild) {
			GlStateManager.scale(0.75F, 0.75F, 0.75F);
			GlStateManager.translate(0.0F, 16.0F * scale, 0.0F);
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
			GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
			this.jewel.render(scale);

			renderFists(scale);
		}
		else {
			if(entity.isSneaking()) {
				GlStateManager.translate(0.0F, 0.2F, 0.0F);
			}

			this.jewel.render(scale);

			renderFists(scale);
		}

		GlStateManager.popMatrix();
	}

	private void renderFists(float scale) {
		if(renderRight) {
			renderFist(this.fistRight, scale);
		}

		if(renderLeft) {
			renderFist(this.fistLeft, scale);
		}
	}

	private static void renderFist(ModelRenderer fist, float scale) {
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 0.95F);
		GlStateManager.translate(fist.offsetX, fist.offsetY, fist.offsetZ);
		GlStateManager.translate(fist.rotationPointX * scale, fist.rotationPointY * scale, fist.rotationPointZ * scale);
		GlStateManager.scale(2.5D, 2.5D, 2.5D);
		GlStateManager.translate(-fist.offsetX, -fist.offsetY, -fist.offsetZ);
		GlStateManager.translate(-fist.rotationPointX * scale, -fist.rotationPointY * scale, -fist.rotationPointZ * scale);
		fist.render(scale);
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
	}

	public void setRenderRight(boolean renderRight) {
		this.renderRight = renderRight;
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

