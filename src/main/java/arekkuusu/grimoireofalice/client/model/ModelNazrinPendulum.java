package arekkuusu.grimoireofalice.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelNazrinPendulum extends ModelBase {

	public ModelRenderer shape1;
	public ModelRenderer shape1_1;
	public ModelRenderer shape1_2;
	public ModelRenderer shape1_3;
	public ModelRenderer shape5;
	public ModelRenderer shape5_1;
	public ModelRenderer shape5_2;
	public ModelRenderer shape5_3;
	public ModelRenderer shape10;

	public ModelNazrinPendulum() {
		this.textureWidth = 128;
		this.textureHeight = 32;
		this.shape5_1 = new ModelRenderer(this, 49, 0);
		this.shape5_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape5_1.addBox(-12.0F, -17.5F, -14.2F, 24, 20, 0, 0.0F);
		this.setRotateAngle(shape5_1, -0.767944870877505F, 1.5707963267948966F, 0.0F);
		this.shape1_2 = new ModelRenderer(this, 0, 0);
		this.shape1_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_2.addBox(-12.0F, -12.0F, -8.0F, 24, 32, 0, 0.0F);
		this.setRotateAngle(shape1_2, 0.3839724354387525F, 3.141592653589793F, 0.0F);
		this.shape5_3 = new ModelRenderer(this, 49, 0);
		this.shape5_3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape5_3.addBox(-12.0F, -17.5F, -14.2F, 24, 20, 0, 0.0F);
		this.setRotateAngle(shape5_3, -0.767944870877505F, -1.5707963267948966F, 0.0F);
		this.shape1 = new ModelRenderer(this, 0, 0);
		this.shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1.addBox(-12.0F, -12.0F, -8.0F, 24, 32, 0, 0.0F);
		this.setRotateAngle(shape1, 0.3839724354387525F, 0.0F, 0.0F);
		this.shape10 = new ModelRenderer(this, 100, 0);
		this.shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape10.addBox(-5.0F, -30.0F, 0.0F, 10, 10, 0, 0.0F);
		this.shape5 = new ModelRenderer(this, 49, 0);
		this.shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape5.addBox(-12.0F, -17.5F, -14.2F, 24, 20, 0, 0.0F);
		this.setRotateAngle(shape5, -0.767944870877505F, 0.0F, 0.0F);
		this.shape5_2 = new ModelRenderer(this, 49, 0);
		this.shape5_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape5_2.addBox(-12.0F, -17.5F, -14.2F, 24, 20, 0, 0.0F);
		this.setRotateAngle(shape5_2, -0.767944870877505F, 3.141592653589793F, 0.0F);
		this.shape1_1 = new ModelRenderer(this, 0, 0);
		this.shape1_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_1.addBox(-12.0F, -12.0F, -8.0F, 24, 32, 0, 0.0F);
		this.setRotateAngle(shape1_1, 0.3839724354387525F, 1.5707963267948966F, 0.0F);
		this.shape1_3 = new ModelRenderer(this, 0, 0);
		this.shape1_3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_3.addBox(-12.0F, -12.0F, -8.0F, 24, 32, 0, 0.0F);
		this.setRotateAngle(shape1_3, 0.3839724354387525F, -1.5707963267948966F, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.shape5_1.render(f5);
		this.shape1_2.render(f5);
		this.shape5_3.render(f5);
		this.shape1.render(f5);
		this.shape10.render(f5);
		this.shape5.render(f5);
		this.shape5_2.render(f5);
		this.shape1_1.render(f5);
		this.shape1_3.render(f5);
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
