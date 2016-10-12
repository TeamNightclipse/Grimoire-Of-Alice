package arekkuusu.grimoireofalice.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * ModelCursedDecoyDoll - Arekkuusu
 * Created using Tabula 5.1.0
 */
public class ModelCursedDecoyDoll extends ModelBase {

	//TODO: Name these
	private final ModelRenderer shape1;
	private final ModelRenderer shape1_1;
	private final ModelRenderer shape1_2;
	private final ModelRenderer shape1_3;
	private final ModelRenderer shape1_4;
	private final ModelRenderer shape1_5;
	private final ModelRenderer shape1_6;
	private final ModelRenderer shape1_7;
	private final ModelRenderer shape1_8;
	private final ModelRenderer shape1_9;
	private final ModelRenderer shape1_10;
	private final ModelRenderer shape1_11;
	private final ModelRenderer shape1_12;
	private final ModelRenderer shape1_13;
	private final ModelRenderer shape1_14;
	private final ModelRenderer shape1_15;
	private final ModelRenderer shape25;
	private final ModelRenderer shape25_1;
	private final ModelRenderer shape1_16;
	private final ModelRenderer shape1_17;
	private final ModelRenderer shape1_18;
	private final ModelRenderer shape1_19;
	private final ModelRenderer shape1_20;
	private final ModelRenderer shape1_21;
	private final ModelRenderer shape1_22;
	private final ModelRenderer shape1_23;
	private final ModelRenderer shape25_2;
	private final ModelRenderer shape36;
	private final ModelRenderer shape36_1;
	private final ModelRenderer shape25_3;

	public ModelCursedDecoyDoll() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.shape36_1 = new ModelRenderer(this, 25, 50);
		this.shape36_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape36_1.addBox(2.5F, 8.1F, -3.0F, 2, 9, 5, 0.0F);
		this.setRotateAngle(shape36_1, 0.0F, 0.0F, -0.3141592653589793F);
		this.shape1_10 = new ModelRenderer(this, 48, 19);
		this.shape1_10.setRotationPoint(0.0F, 14.0F, 0.0F);
		this.shape1_10.addBox(5.7F, 6.3F, -4.0F, 0, 3, 7, 0.0F);
		this.setRotateAngle(shape1_10, 0.0F, 0.0F, 0.9599310885968813F);
		this.shape1_17 = new ModelRenderer(this, 33, 8);
		this.shape1_17.setRotationPoint(0.0F, 14.0F, 0.0F);
		this.shape1_17.addBox(4.7F, -13.4F, -4.0F, 0, 2, 7, 0.0F);
		this.shape1_8 = new ModelRenderer(this, 48, 11);
		this.shape1_8.setRotationPoint(0.0F, 14.0F, 0.0F);
		this.shape1_8.addBox(10.7F, -0.4F, -4.0F, 0, 3, 7, 0.0F);
		this.setRotateAngle(shape1_8, 0.0F, 0.0F, 0.9599310885968813F);
		this.shape1_20 = new ModelRenderer(this, 33, 18);
		this.shape1_20.setRotationPoint(0.0F, 14.0F, 0.0F);
		this.shape1_20.addBox(1.9F, -12.2F, -4.0F, 0, 3, 7, 0.0F);
		this.setRotateAngle(shape1_20, 0.0F, 0.0F, -0.5462880558742251F);
		this.shape1_12 = new ModelRenderer(this, 48, 27);
		this.shape1_12.setRotationPoint(0.0F, 14.0F, 0.0F);
		this.shape1_12.addBox(-5.7F, 6.3F, -4.0F, 0, 3, 7, 0.0F);
		this.setRotateAngle(shape1_12, 0.0F, 0.0F, -0.9599310885968813F);
		this.shape1_23 = new ModelRenderer(this, 33, 29);
		this.shape1_23.setRotationPoint(0.0F, 14.0F, 0.0F);
		this.shape1_23.addBox(-14.7F, -10.2F, -4.0F, 0, 2, 7, 0.0F);
		this.setRotateAngle(shape1_23, 0.0F, 0.0F, 0.8196066167365371F);
		this.shape1_2 = new ModelRenderer(this, 33, -7);
		this.shape1_2.setRotationPoint(0.0F, 14.0F, 0.0F);
		this.shape1_2.addBox(7.3F, -3.2F, -4.0F, 0, 10, 7, 0.0F);
		this.setRotateAngle(shape1_2, 0.0F, 0.0F, -0.10471975511965977F);
		this.shape1_19 = new ModelRenderer(this, 33, 15);
		this.shape1_19.setRotationPoint(0.0F, 14.0F, 0.0F);
		this.shape1_19.addBox(14.7F, -10.2F, -4.0F, 0, 2, 7, 0.0F);
		this.setRotateAngle(shape1_19, 0.0F, 0.0F, -0.8196066167365371F);
		this.shape25_3 = new ModelRenderer(this, 20, 25);
		this.shape25_3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape25_3.addBox(-2.0F, -5.4F, 3.5F, 6, 3, 0, 0.0F);
		this.setRotateAngle(shape25_3, 0.0F, 0.0F, 0.8651597102135892F);
		this.shape1_21 = new ModelRenderer(this, 33, 22);
		this.shape1_21.setRotationPoint(0.0F, 14.0F, 0.0F);
		this.shape1_21.addBox(-4.7F, -13.4F, -4.0F, 0, 2, 7, 0.0F);
		this.shape25_2 = new ModelRenderer(this, -6, 56);
		this.shape25_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape25_2.addBox(-3.0F, -3.7F, -4.0F, 6, 0, 7, 0.0F);
		this.shape1_16 = new ModelRenderer(this, 33, 4);
		this.shape1_16.setRotationPoint(0.0F, 14.0F, 0.0F);
		this.shape1_16.addBox(-1.9F, -12.2F, -4.0F, 0, 3, 7, 0.0F);
		this.setRotateAngle(shape1_16, 0.0F, 0.0F, 0.5462880558742251F);
		this.shape1_18 = new ModelRenderer(this, 33, 11);
		this.shape1_18.setRotationPoint(0.0F, 14.0F, 0.0F);
		this.shape1_18.addBox(7.6F, -15.0F, -4.0F, 0, 3, 7, 0.0F);
		this.setRotateAngle(shape1_18, 0.0F, 0.0F, -0.22759093446006054F);
		this.shape1_1 = new ModelRenderer(this, 0, 0);
		this.shape1_1.setRotationPoint(0.0F, 14.0F, 0.0F);
		this.shape1_1.addBox(-8.0F, -10.0F, 3.0F, 16, 20, 0, 0.0F);
		this.shape1_22 = new ModelRenderer(this, 33, 25);
		this.shape1_22.setRotationPoint(0.0F, 14.0F, 0.0F);
		this.shape1_22.addBox(-7.6F, -15.0F, -4.0F, 0, 3, 7, 0.0F);
		this.setRotateAngle(shape1_22, 0.0F, 0.0F, 0.22759093446006054F);
		this.shape1_14 = new ModelRenderer(this, 48, 37);
		this.shape1_14.setRotationPoint(0.0F, 14.0F, 0.0F);
		this.shape1_14.addBox(7.8F, -5.3F, -4.0F, 0, 4, 7, 0.0F);
		this.setRotateAngle(shape1_14, 0.0F, 0.0F, -0.3490658503988659F);
		this.shape25_1 = new ModelRenderer(this, 0, 44);
		this.shape25_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape25_1.addBox(-5.0F, -4.0F, -4.0F, 10, 11, 0, 0.0F);
		this.shape1 = new ModelRenderer(this, 0, 0);
		this.shape1.setRotationPoint(0.0F, 14.0F, 0.0F);
		this.shape1.addBox(-8.0F, -10.0F, -4.0F, 16, 20, 0, 0.0F);
		this.shape36 = new ModelRenderer(this, 25, 50);
		this.shape36.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape36.addBox(-4.4F, 8.1F, -3.0F, 2, 9, 5, 0.0F);
		this.setRotateAngle(shape36, 0.0F, 0.0F, 0.3141592653589793F);
		this.shape1_13 = new ModelRenderer(this, 48, 31);
		this.shape1_13.setRotationPoint(0.0F, 14.0F, 0.0F);
		this.shape1_13.addBox(9.3F, -3.1F, -4.0F, 0, 5, 7, 0.0F);
		this.setRotateAngle(shape1_13, 0.0F, 0.0F, -1.1344640137963142F);
		this.shape25 = new ModelRenderer(this, 0, 32);
		this.shape25.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape25.addBox(-5.0F, -4.0F, 3.0F, 10, 11, 0, 0.0F);
		this.shape1_15 = new ModelRenderer(this, 48, 42);
		this.shape1_15.setRotationPoint(0.0F, 14.0F, 0.0F);
		this.shape1_15.addBox(-9.3F, -3.1F, -4.0F, 0, 5, 7, 0.0F);
		this.setRotateAngle(shape1_15, 0.0F, 0.0F, 1.1344640137963142F);
		this.shape1_5 = new ModelRenderer(this, 48, 3);
		this.shape1_5.setRotationPoint(0.0F, 14.0F, 0.0F);
		this.shape1_5.addBox(-10.7F, -0.4F, -4.0F, 0, 3, 7, 0.0F);
		this.setRotateAngle(shape1_5, 0.0F, 0.0F, -0.9599310885968813F);
		this.shape1_4 = new ModelRenderer(this, 48, -1);
		this.shape1_4.setRotationPoint(0.0F, 14.0F, 0.0F);
		this.shape1_4.addBox(-9.9F, 1.2F, -4.0F, 0, 3, 7, 0.0F);
		this.setRotateAngle(shape1_4, 0.0F, 0.0F, -0.5235987755982988F);
		this.shape1_7 = new ModelRenderer(this, 48, 7);
		this.shape1_7.setRotationPoint(0.0F, 14.0F, 0.0F);
		this.shape1_7.addBox(9.9F, 1.2F, -4.0F, 0, 3, 7, 0.0F);
		this.setRotateAngle(shape1_7, 0.0F, 0.0F, 0.5235987755982988F);
		this.shape1_3 = new ModelRenderer(this, 48, -6);
		this.shape1_3.setRotationPoint(0.0F, 14.0F, 0.0F);
		this.shape1_3.addBox(-7.8F, -5.3F, -4.0F, 0, 4, 7, 0.0F);
		this.setRotateAngle(shape1_3, 0.0F, 0.0F, 0.3490658503988659F);
		this.shape1_6 = new ModelRenderer(this, 0, 14);
		this.shape1_6.setRotationPoint(0.0F, 14.0F, 0.0F);
		this.shape1_6.addBox(-7.3F, -3.2F, -4.0F, 0, 10, 7, 0.0F);
		this.setRotateAngle(shape1_6, 0.0F, 0.0F, 0.10471975511965977F);
		this.shape1_11 = new ModelRenderer(this, 48, 23);
		this.shape1_11.setRotationPoint(0.0F, 14.0F, 0.0F);
		this.shape1_11.addBox(-3.9F, 4.6F, -4.0F, 0, 3, 7, 0.0F);
		this.setRotateAngle(shape1_11, 0.0F, 0.0F, -0.6981317007977318F);
		this.shape1_9 = new ModelRenderer(this, 48, 15);
		this.shape1_9.setRotationPoint(0.0F, 14.0F, 0.0F);
		this.shape1_9.addBox(3.9F, 4.7F, -4.0F, 0, 3, 7, 0.0F);
		this.setRotateAngle(shape1_9, 0.0F, 0.0F, 0.6981317007977318F);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

		GlStateManager.pushMatrix();
		//TODO: Cleanup this mess
		GlStateManager.translate(this.shape36_1.offsetX, this.shape36_1.offsetY, this.shape36_1.offsetZ);
		GlStateManager.translate(this.shape36_1.rotationPointX * scale, this.shape36_1.rotationPointY * scale, this.shape36_1.rotationPointZ * scale);
		GlStateManager.scale(0.9D, 0.9D, 0.9D);
		GlStateManager.translate(-this.shape36_1.offsetX, -this.shape36_1.offsetY, -this.shape36_1.offsetZ);
		GlStateManager.translate(-this.shape36_1.rotationPointX * scale, -this.shape36_1.rotationPointY * scale, -this.shape36_1.rotationPointZ * scale);

		this.shape36_1.render(scale);

		GlStateManager.popMatrix();

		this.shape1_10.render(scale);
		this.shape1_17.render(scale);
		this.shape1_8.render(scale);
		this.shape1_20.render(scale);
		this.shape1_12.render(scale);
		this.shape1_23.render(scale);
		this.shape1_2.render(scale);
		this.shape1_19.render(scale);
		this.shape1_21.render(scale);
		this.shape25_2.render(scale);
		this.shape1_16.render(scale);
		this.shape1_18.render(scale);
		this.shape1_1.render(scale);
		this.shape1_22.render(scale);
		this.shape1_14.render(scale);
		this.shape25_1.render(scale);
		this.shape1.render(scale);

		GlStateManager.pushMatrix();
		GlStateManager.translate(this.shape36.offsetX, this.shape36.offsetY, this.shape36.offsetZ);
		GlStateManager.translate(this.shape36.rotationPointX * scale, this.shape36.rotationPointY * scale, this.shape36.rotationPointZ * scale);
		GlStateManager.scale(0.9D, 0.9D, 0.9D);
		GlStateManager.translate(-this.shape36.offsetX, -this.shape36.offsetY, -this.shape36.offsetZ);
		GlStateManager.translate(-this.shape36.rotationPointX * scale, -this.shape36.rotationPointY * scale, -this.shape36.rotationPointZ * scale);

		this.shape36.render(scale);

		GlStateManager.popMatrix();

		this.shape1_13.render(scale);
		this.shape25.render(scale);
		this.shape1_15.render(scale);
		this.shape1_5.render(scale);
		this.shape1_4.render(scale);
		this.shape1_7.render(scale);
		this.shape1_3.render(scale);
		this.shape1_6.render(scale);
		this.shape1_11.render(scale);
		this.shape1_9.render(scale);
		this.shape25_3.render(scale);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
