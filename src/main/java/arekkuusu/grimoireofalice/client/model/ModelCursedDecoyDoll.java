package arekkuusu.grimoireofalice.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * ModelCursedDecoyDoll - Arekkuusu Created using Tabula 5.1.0
 */
public class ModelCursedDecoyDoll extends ModelBase {

	private final ModelRenderer doll;
	private final ModelRenderer doll1;
	private final ModelRenderer doll2;
	private final ModelRenderer doll3;
	private final ModelRenderer doll4;
	private final ModelRenderer doll5;
	private final ModelRenderer doll6;
	private final ModelRenderer doll7;
	private final ModelRenderer doll8;
	private final ModelRenderer doll9;
	private final ModelRenderer doll10;
	private final ModelRenderer doll11;
	private final ModelRenderer doll12;
	private final ModelRenderer doll13;
	private final ModelRenderer doll14;
	private final ModelRenderer doll15;
	private final ModelRenderer doll16;
	private final ModelRenderer doll17;
	private final ModelRenderer doll18;
	private final ModelRenderer doll19;
	private final ModelRenderer doll20;
	private final ModelRenderer doll21;
	private final ModelRenderer doll22;
	private final ModelRenderer doll23;
	private final ModelRenderer doll24;
	private final ModelRenderer doll25;
	private final ModelRenderer doll26;
	private final ModelRenderer doll27;
	private final ModelRenderer doll28;
	private final ModelRenderer doll29;

	public ModelCursedDecoyDoll() {
		textureWidth = 64;
		textureHeight = 64;
		doll28 = new ModelRenderer(this, 25, 50);
		doll28.setRotationPoint(0.0F, 0.0F, 0.0F);
		doll28.addBox(2.5F, 8.1F, -3.0F, 2, 9, 5, 0.0F);
		setRotateAngle(doll28, 0.0F, 0.0F, -0.3141592653589793F);
		doll10 = new ModelRenderer(this, 48, 19);
		doll10.setRotationPoint(0.0F, 14.0F, 0.0F);
		doll10.addBox(5.7F, 6.3F, -4.0F, 0, 3, 7, 0.0F);
		setRotateAngle(doll10, 0.0F, 0.0F, 0.9599310885968813F);
		doll19 = new ModelRenderer(this, 33, 8);
		doll19.setRotationPoint(0.0F, 14.0F, 0.0F);
		doll19.addBox(4.7F, -13.4F, -4.0F, 0, 2, 7, 0.0F);
		doll8 = new ModelRenderer(this, 48, 11);
		doll8.setRotationPoint(0.0F, 14.0F, 0.0F);
		doll8.addBox(10.7F, -0.4F, -4.0F, 0, 3, 7, 0.0F);
		setRotateAngle(doll8, 0.0F, 0.0F, 0.9599310885968813F);
		doll22 = new ModelRenderer(this, 33, 18);
		doll22.setRotationPoint(0.0F, 14.0F, 0.0F);
		doll22.addBox(1.9F, -12.2F, -4.0F, 0, 3, 7, 0.0F);
		setRotateAngle(doll22, 0.0F, 0.0F, -0.5462880558742251F);
		doll12 = new ModelRenderer(this, 48, 27);
		doll12.setRotationPoint(0.0F, 14.0F, 0.0F);
		doll12.addBox(-5.7F, 6.3F, -4.0F, 0, 3, 7, 0.0F);
		setRotateAngle(doll12, 0.0F, 0.0F, -0.9599310885968813F);
		doll25 = new ModelRenderer(this, 33, 29);
		doll25.setRotationPoint(0.0F, 14.0F, 0.0F);
		doll25.addBox(-14.7F, -10.2F, -4.0F, 0, 2, 7, 0.0F);
		setRotateAngle(doll25, 0.0F, 0.0F, 0.8196066167365371F);
		doll2 = new ModelRenderer(this, 33, -7);
		doll2.setRotationPoint(0.0F, 14.0F, 0.0F);
		doll2.addBox(7.3F, -3.2F, -4.0F, 0, 10, 7, 0.0F);
		setRotateAngle(doll2, 0.0F, 0.0F, -0.10471975511965977F);
		doll21 = new ModelRenderer(this, 33, 15);
		doll21.setRotationPoint(0.0F, 14.0F, 0.0F);
		doll21.addBox(14.7F, -10.2F, -4.0F, 0, 2, 7, 0.0F);
		setRotateAngle(doll21, 0.0F, 0.0F, -0.8196066167365371F);
		doll29 = new ModelRenderer(this, 20, 25);
		doll29.setRotationPoint(0.0F, 0.0F, 0.0F);
		doll29.addBox(-2.0F, -5.4F, 3.5F, 6, 3, 0, 0.0F);
		setRotateAngle(doll29, 0.0F, 0.0F, 0.8651597102135892F);
		doll23 = new ModelRenderer(this, 33, 22);
		doll23.setRotationPoint(0.0F, 14.0F, 0.0F);
		doll23.addBox(-4.7F, -13.4F, -4.0F, 0, 2, 7, 0.0F);
		doll26 = new ModelRenderer(this, -6, 56);
		doll26.setRotationPoint(0.0F, 0.0F, 0.0F);
		doll26.addBox(-3.0F, -3.7F, -4.0F, 6, 0, 7, 0.0F);
		doll18 = new ModelRenderer(this, 33, 4);
		doll18.setRotationPoint(0.0F, 14.0F, 0.0F);
		doll18.addBox(-1.9F, -12.2F, -4.0F, 0, 3, 7, 0.0F);
		setRotateAngle(doll18, 0.0F, 0.0F, 0.5462880558742251F);
		doll20 = new ModelRenderer(this, 33, 11);
		doll20.setRotationPoint(0.0F, 14.0F, 0.0F);
		doll20.addBox(7.6F, -15.0F, -4.0F, 0, 3, 7, 0.0F);
		setRotateAngle(doll20, 0.0F, 0.0F, -0.22759093446006054F);
		doll1 = new ModelRenderer(this, 0, 0);
		doll1.setRotationPoint(0.0F, 14.0F, 0.0F);
		doll1.addBox(-8.0F, -10.0F, 3.0F, 16, 20, 0, 0.0F);
		doll24 = new ModelRenderer(this, 33, 25);
		doll24.setRotationPoint(0.0F, 14.0F, 0.0F);
		doll24.addBox(-7.6F, -15.0F, -4.0F, 0, 3, 7, 0.0F);
		setRotateAngle(doll24, 0.0F, 0.0F, 0.22759093446006054F);
		doll14 = new ModelRenderer(this, 48, 37);
		doll14.setRotationPoint(0.0F, 14.0F, 0.0F);
		doll14.addBox(7.8F, -5.3F, -4.0F, 0, 4, 7, 0.0F);
		setRotateAngle(doll14, 0.0F, 0.0F, -0.3490658503988659F);
		doll17 = new ModelRenderer(this, 0, 44);
		doll17.setRotationPoint(0.0F, 0.0F, 0.0F);
		doll17.addBox(-5.0F, -4.0F, -4.0F, 10, 11, 0, 0.0F);
		doll = new ModelRenderer(this, 0, 0);
		doll.setRotationPoint(0.0F, 14.0F, 0.0F);
		doll.addBox(-8.0F, -10.0F, -4.0F, 16, 20, 0, 0.0F);
		doll27 = new ModelRenderer(this, 25, 50);
		doll27.setRotationPoint(0.0F, 0.0F, 0.0F);
		doll27.addBox(-4.4F, 8.1F, -3.0F, 2, 9, 5, 0.0F);
		setRotateAngle(doll27, 0.0F, 0.0F, 0.3141592653589793F);
		doll13 = new ModelRenderer(this, 48, 31);
		doll13.setRotationPoint(0.0F, 14.0F, 0.0F);
		doll13.addBox(9.3F, -3.1F, -4.0F, 0, 5, 7, 0.0F);
		setRotateAngle(doll13, 0.0F, 0.0F, -1.1344640137963142F);
		doll16 = new ModelRenderer(this, 0, 32);
		doll16.setRotationPoint(0.0F, 0.0F, 0.0F);
		doll16.addBox(-5.0F, -4.0F, 3.0F, 10, 11, 0, 0.0F);
		doll15 = new ModelRenderer(this, 48, 42);
		doll15.setRotationPoint(0.0F, 14.0F, 0.0F);
		doll15.addBox(-9.3F, -3.1F, -4.0F, 0, 5, 7, 0.0F);
		setRotateAngle(doll15, 0.0F, 0.0F, 1.1344640137963142F);
		doll5 = new ModelRenderer(this, 48, 3);
		doll5.setRotationPoint(0.0F, 14.0F, 0.0F);
		doll5.addBox(-10.7F, -0.4F, -4.0F, 0, 3, 7, 0.0F);
		setRotateAngle(doll5, 0.0F, 0.0F, -0.9599310885968813F);
		doll4 = new ModelRenderer(this, 48, -1);
		doll4.setRotationPoint(0.0F, 14.0F, 0.0F);
		doll4.addBox(-9.9F, 1.2F, -4.0F, 0, 3, 7, 0.0F);
		setRotateAngle(doll4, 0.0F, 0.0F, -0.5235987755982988F);
		doll7 = new ModelRenderer(this, 48, 7);
		doll7.setRotationPoint(0.0F, 14.0F, 0.0F);
		doll7.addBox(9.9F, 1.2F, -4.0F, 0, 3, 7, 0.0F);
		setRotateAngle(doll7, 0.0F, 0.0F, 0.5235987755982988F);
		doll3 = new ModelRenderer(this, 48, -6);
		doll3.setRotationPoint(0.0F, 14.0F, 0.0F);
		doll3.addBox(-7.8F, -5.3F, -4.0F, 0, 4, 7, 0.0F);
		setRotateAngle(doll3, 0.0F, 0.0F, 0.3490658503988659F);
		doll6 = new ModelRenderer(this, 0, 14);
		doll6.setRotationPoint(0.0F, 14.0F, 0.0F);
		doll6.addBox(-7.3F, -3.2F, -4.0F, 0, 10, 7, 0.0F);
		setRotateAngle(doll6, 0.0F, 0.0F, 0.10471975511965977F);
		doll11 = new ModelRenderer(this, 48, 23);
		doll11.setRotationPoint(0.0F, 14.0F, 0.0F);
		doll11.addBox(-3.9F, 4.6F, -4.0F, 0, 3, 7, 0.0F);
		setRotateAngle(doll11, 0.0F, 0.0F, -0.6981317007977318F);
		doll9 = new ModelRenderer(this, 48, 15);
		doll9.setRotationPoint(0.0F, 14.0F, 0.0F);
		doll9.addBox(3.9F, 4.7F, -4.0F, 0, 3, 7, 0.0F);
		setRotateAngle(doll9, 0.0F, 0.0F, 0.6981317007977318F);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

		GlStateManager.pushMatrix();
		//TODO: Cleanup this mess
		GlStateManager.translate(doll28.offsetX, doll28.offsetY, doll28.offsetZ);
		GlStateManager.translate(doll28.rotationPointX * scale, doll28.rotationPointY * scale, doll28.rotationPointZ * scale);
		GlStateManager.scale(0.9D, 0.9D, 0.9D);
		GlStateManager.translate(-doll28.offsetX, -doll28.offsetY, -doll28.offsetZ);
		GlStateManager.translate(-doll28.rotationPointX * scale, -doll28.rotationPointY * scale, -doll28.rotationPointZ * scale);

		doll28.render(scale);

		GlStateManager.popMatrix();

		doll10.render(scale);
		doll19.render(scale);
		doll8.render(scale);
		doll22.render(scale);
		doll12.render(scale);
		doll25.render(scale);
		doll2.render(scale);
		doll21.render(scale);
		doll23.render(scale);
		doll26.render(scale);
		doll18.render(scale);
		doll20.render(scale);
		doll1.render(scale);
		doll24.render(scale);
		doll14.render(scale);
		doll17.render(scale);
		doll.render(scale);

		GlStateManager.pushMatrix();
		GlStateManager.translate(doll27.offsetX, doll27.offsetY, doll27.offsetZ);
		GlStateManager.translate(doll27.rotationPointX * scale, doll27.rotationPointY * scale, doll27.rotationPointZ * scale);
		GlStateManager.scale(0.9D, 0.9D, 0.9D);
		GlStateManager.translate(-doll27.offsetX, -doll27.offsetY, -doll27.offsetZ);
		GlStateManager.translate(-doll27.rotationPointX * scale, -doll27.rotationPointY * scale, -doll27.rotationPointZ * scale);

		doll27.render(scale);

		GlStateManager.popMatrix();

		doll13.render(scale);
		doll16.render(scale);
		doll15.render(scale);
		doll5.render(scale);
		doll4.render(scale);
		doll7.render(scale);
		doll3.render(scale);
		doll6.render(scale);
		doll11.render(scale);
		doll9.render(scale);
		doll29.render(scale);
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
