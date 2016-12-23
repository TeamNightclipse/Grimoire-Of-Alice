/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * NazrinPendulum - Arekkuusu Created using Tabula 5.1.0
 */
public class ModelNazrinPendulum extends ModelBase {

	private final ModelRenderer prism1;
	private final ModelRenderer prism2;
	private final ModelRenderer prism3;
	private final ModelRenderer prism4;
	private final ModelRenderer prism5;
	private final ModelRenderer prism6;
	private final ModelRenderer prism7;
	private final ModelRenderer prism8;
	private final ModelRenderer top;

	public ModelNazrinPendulum() {
		textureWidth = 128;
		textureHeight = 32;
		prism6 = new ModelRenderer(this, 49, 0);
		prism6.setRotationPoint(0.0F, 0.0F, 0.0F);
		prism6.addBox(-12.0F, -17.5F, -14.2F, 24, 20, 0, 0.0F);
		setRotateAngle(prism6, -0.767944870877505F, 1.5707963267948966F, 0.0F);
		prism3 = new ModelRenderer(this, 0, 0);
		prism3.setRotationPoint(0.0F, 0.0F, 0.0F);
		prism3.addBox(-12.0F, -12.0F, -8.0F, 24, 32, 0, 0.0F);
		setRotateAngle(prism3, 0.3839724354387525F, 3.141592653589793F, 0.0F);
		prism8 = new ModelRenderer(this, 49, 0);
		prism8.setRotationPoint(0.0F, 0.0F, 0.0F);
		prism8.addBox(-12.0F, -17.5F, -14.2F, 24, 20, 0, 0.0F);
		setRotateAngle(prism8, -0.767944870877505F, -1.5707963267948966F, 0.0F);
		prism1 = new ModelRenderer(this, 0, 0);
		prism1.setRotationPoint(0.0F, 0.0F, 0.0F);
		prism1.addBox(-12.0F, -12.0F, -8.0F, 24, 32, 0, 0.0F);
		setRotateAngle(prism1, 0.3839724354387525F, 0.0F, 0.0F);
		top = new ModelRenderer(this, 100, 0);
		top.setRotationPoint(0.0F, 0.0F, 0.0F);
		top.addBox(-5.0F, -30.0F, 0.0F, 10, 10, 0, 0.0F);
		prism5 = new ModelRenderer(this, 49, 0);
		prism5.setRotationPoint(0.0F, 0.0F, 0.0F);
		prism5.addBox(-12.0F, -17.5F, -14.2F, 24, 20, 0, 0.0F);
		setRotateAngle(prism5, -0.767944870877505F, 0.0F, 0.0F);
		prism7 = new ModelRenderer(this, 49, 0);
		prism7.setRotationPoint(0.0F, 0.0F, 0.0F);
		prism7.addBox(-12.0F, -17.5F, -14.2F, 24, 20, 0, 0.0F);
		setRotateAngle(prism7, -0.767944870877505F, 3.141592653589793F, 0.0F);
		prism2 = new ModelRenderer(this, 0, 0);
		prism2.setRotationPoint(0.0F, 0.0F, 0.0F);
		prism2.addBox(-12.0F, -12.0F, -8.0F, 24, 32, 0, 0.0F);
		setRotateAngle(prism2, 0.3839724354387525F, 1.5707963267948966F, 0.0F);
		prism4 = new ModelRenderer(this, 0, 0);
		prism4.setRotationPoint(0.0F, 0.0F, 0.0F);
		prism4.addBox(-12.0F, -12.0F, -8.0F, 24, 32, 0, 0.0F);
		setRotateAngle(prism4, 0.3839724354387525F, -1.5707963267948966F, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		prism6.render(f5);
		prism3.render(f5);
		prism8.render(f5);
		prism1.render(f5);
		top.render(f5);
		prism5.render(f5);
		prism7.render(f5);
		prism2.render(f5);
		prism4.render(f5);
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
