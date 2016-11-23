package arekkuusu.grimoireofalice.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * ModelUnzanFist - Arekkuusu Created using Tabula 5.1.0
 */
public class ModelUnzanFist extends ModelBase {

	//TODO: Name these
	private final ModelRenderer fist;
	private final ModelRenderer shape2;
	private final ModelRenderer shape2_1;
	private final ModelRenderer shape2_2;
	private final ModelRenderer shape2_3;
	private final ModelRenderer shape2_4;
	private final ModelRenderer shape2_5;
	private final ModelRenderer shape2_6;

	public ModelUnzanFist() {
		textureWidth = 128;
		textureHeight = 64;
		shape2_2 = new ModelRenderer(this, 90, 35);
		shape2_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		shape2_2.addBox(-3.0F, -10.0F, -11.0F, 6, 22, 6, 0.0F);
		fist = new ModelRenderer(this, 0, 0);
		fist.setRotationPoint(0.0F, 0.0F, 0.0F);
		fist.addBox(-10.0F, -8.0F, -10.0F, 20, 18, 20, 0.0F);
		shape2_5 = new ModelRenderer(this, 81, 0);
		shape2_5.setRotationPoint(0.0F, 0.0F, 0.0F);
		shape2_5.addBox(-10.0F, 10.0F, -11.0F, 6, 2, 17, 0.0F);
		shape2 = new ModelRenderer(this, 90, 35);
		shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
		shape2.addBox(4.0F, -10.0F, -11.0F, 6, 22, 6, 0.0F);
		shape2_4 = new ModelRenderer(this, 90, 35);
		shape2_4.setRotationPoint(0.0F, 0.0F, 0.0F);
		shape2_4.addBox(-10.0F, -10.0F, -11.0F, 6, 22, 6, 0.0F);
		shape2_6 = new ModelRenderer(this, 0, 40);
		shape2_6.setRotationPoint(0.0F, 0.0F, 0.0F);
		shape2_6.addBox(-11.0F, 4.0F, -8.0F, 6, 6, 18, 0.0F);
		shape2_1 = new ModelRenderer(this, 81, 0);
		shape2_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		shape2_1.addBox(4.0F, 10.0F, -11.0F, 6, 2, 17, 0.0F);
		shape2_3 = new ModelRenderer(this, 81, 0);
		shape2_3.setRotationPoint(0.0F, 0.0F, 0.0F);
		shape2_3.addBox(-3.0F, 10.0F, -11.0F, 6, 2, 17, 0.0F);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		shape2_2.render(f5);
		fist.render(f5);
		shape2_5.render(f5);
		shape2.render(f5);
		shape2_4.render(f5);
		shape2_6.render(f5);
		shape2_1.render(f5);
		shape2_3.render(f5);
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
