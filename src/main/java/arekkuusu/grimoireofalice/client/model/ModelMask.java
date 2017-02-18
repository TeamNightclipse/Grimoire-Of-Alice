package arekkuusu.grimoireofalice.client.model;

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
 * ModelMask - Arekkuusu Created using Tabula 5.1.0
 */

@SideOnly(Side.CLIENT)
public class ModelMask extends ModelBiped {

	private final ModelRenderer mask;
	private final ModelRenderer mask_left;
	private final ModelRenderer mask_right;
	private final ModelRenderer mask_left_1;

	public ModelMask() {
		this.textureWidth = 32;
		this.textureHeight = 32;
		this.mask_right = new ModelRenderer(this, 0, -8);
		this.mask_right.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.mask_right.addBox(-7.3F, -8.0F, -5.2F, 0, 8, 8, 0.0F);
		this.setRotateAngle(mask_right, 0.0F, -0.4904375198104066F, 0.0F);
		this.mask_left_1 = new ModelRenderer(this, 0, 16);
		this.mask_left_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.mask_left_1.addBox(6.4F, -8.0F, -4.48F, 0, 8, 8, 0.0F);
		this.setRotateAngle(mask_left_1, 0.0F, -0.9754645189396307F, 0.0F);
		this.mask_left = new ModelRenderer(this, 0, 8);
		this.mask_left.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.mask_left.addBox(7.3F, -8.0F, -5.2F, 0, 8, 8, 0.0F);
		this.setRotateAngle(mask_left, 0.0F, 0.4904375198104066F, 0.0F);
		this.mask = new ModelRenderer(this, 0, 8);
		this.mask.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.mask.addBox(-4.0F, -8.0F, -8.0F, 8, 8, 0, 0.0F);
		this.setRotateAngle(mask, -0.18203784098300857F, 0.5918411493512771F, 0.0F);
		this.mask.addChild(this.mask_right);
		this.mask_left.addChild(this.mask_left_1);
		this.mask.addChild(this.mask_left);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float age, float netHeadYaw, float headPitch, float scale) {
		this.setRotationAngles(netHeadYaw, entity);
		GlStateManager.pushMatrix();
		GlStateManager.enableCull();

		scale *= 1.5;
		float maxUpAndDown = 0.05F;
		float speed = 2;
		float angle = 0;

		float toDegrees = (float) Math.PI / 180F;
		angle += speed * age;
		if (angle > 360) angle -= 360;

		GlStateManager.translate(0, maxUpAndDown * Math.sin(angle * toDegrees), 0);
		GlStateManager.translate(0, 0, maxUpAndDown * Math.cos(angle * toDegrees));
		GlStateManager.rotate(45, 0.0F, 1.0F, 0.0F);
		if (this.isChild) {
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
			GlStateManager.translate(0.0F, 16.0F * scale, 0.0F);
			this.mask.render(scale);
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.75F, 0.75F, 0.75F);
			GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
		}
		else {
			if (entity.isSneaking()) {
				GlStateManager.translate(0.0F, 0.2F, 0.0F);
			}

			this.mask.render(scale);
		}
		GlStateManager.disableCull();
		GlStateManager.popMatrix();
	}

	private void setRotationAngles(float netHeadYaw, Entity entityIn) {
		boolean flag = entityIn instanceof EntityLivingBase && ((EntityLivingBase) entityIn).getTicksElytraFlying() > 4;
		this.mask.rotateAngleY = netHeadYaw * 0.017453292F;

		if (flag) {
			this.mask.rotateAngleX = -((float) Math.PI / 4F);
		}

		if (this.isSneak) {
			this.mask.rotationPointY = 1.0F;
		}
		else {
			this.mask.rotationPointY = 0.0F;
		}
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
