package arekkuusu.grimoireofalice.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * NazrinPendulum - Arekkuusu Created using Tabula 5.1.0
 */
public class ModelNazrinPendulum extends ModelBase {

	//TODO: Name these
	private final ModelRenderer shape1;
	private final ModelRenderer shape1_1;
	private final ModelRenderer shape1_2;
	private final ModelRenderer shape1_3;
	private final ModelRenderer shape5;
	private final ModelRenderer shape5_1;
	private final ModelRenderer shape5_2;
	private final ModelRenderer shape5_3;
	private final ModelRenderer shape10;

	public ModelNazrinPendulum() {
		textureWidth = 128;
		textureHeight = 32;
		shape5_1 = new ModelRenderer(this, 49, 0);
		shape5_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		shape5_1.addBox(-12.0F, -17.5F, -14.2F, 24, 20, 0, 0.0F);
		setRotateAngle(shape5_1, -0.767944870877505F, 1.5707963267948966F, 0.0F);
		shape1_2 = new ModelRenderer(this, 0, 0);
		shape1_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		shape1_2.addBox(-12.0F, -12.0F, -8.0F, 24, 32, 0, 0.0F);
		setRotateAngle(shape1_2, 0.3839724354387525F, 3.141592653589793F, 0.0F);
		shape5_3 = new ModelRenderer(this, 49, 0);
		shape5_3.setRotationPoint(0.0F, 0.0F, 0.0F);
		shape5_3.addBox(-12.0F, -17.5F, -14.2F, 24, 20, 0, 0.0F);
		setRotateAngle(shape5_3, -0.767944870877505F, -1.5707963267948966F, 0.0F);
		shape1 = new ModelRenderer(this, 0, 0);
		shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
		shape1.addBox(-12.0F, -12.0F, -8.0F, 24, 32, 0, 0.0F);
		setRotateAngle(shape1, 0.3839724354387525F, 0.0F, 0.0F);
		shape10 = new ModelRenderer(this, 100, 0);
		shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
		shape10.addBox(-5.0F, -30.0F, 0.0F, 10, 10, 0, 0.0F);
		shape5 = new ModelRenderer(this, 49, 0);
		shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
		shape5.addBox(-12.0F, -17.5F, -14.2F, 24, 20, 0, 0.0F);
		setRotateAngle(shape5, -0.767944870877505F, 0.0F, 0.0F);
		shape5_2 = new ModelRenderer(this, 49, 0);
		shape5_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		shape5_2.addBox(-12.0F, -17.5F, -14.2F, 24, 20, 0, 0.0F);
		setRotateAngle(shape5_2, -0.767944870877505F, 3.141592653589793F, 0.0F);
		shape1_1 = new ModelRenderer(this, 0, 0);
		shape1_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		shape1_1.addBox(-12.0F, -12.0F, -8.0F, 24, 32, 0, 0.0F);
		setRotateAngle(shape1_1, 0.3839724354387525F, 1.5707963267948966F, 0.0F);
		shape1_3 = new ModelRenderer(this, 0, 0);
		shape1_3.setRotationPoint(0.0F, 0.0F, 0.0F);
		shape1_3.addBox(-12.0F, -12.0F, -8.0F, 24, 32, 0, 0.0F);
		setRotateAngle(shape1_3, 0.3839724354387525F, -1.5707963267948966F, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		shape5_1.render(f5);
		shape1_2.render(f5);
		shape5_3.render(f5);
		shape1.render(f5);
		shape10.render(f5);
		shape5.render(f5);
		shape5_2.render(f5);
		shape1_1.render(f5);
		shape1_3.render(f5);
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
