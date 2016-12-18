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
		bipedRightArm.showModel = false;
		bipedLeftArm.showModel = false;

		bipedBody = jewel;

		super.render(entity, limbSwing, limbSwingAmount, age, netHeadYaw, headPitch, scale);
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

