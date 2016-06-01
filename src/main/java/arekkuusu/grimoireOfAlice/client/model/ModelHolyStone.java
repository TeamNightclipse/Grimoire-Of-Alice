/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHolyStone extends ModelBase {

	private ModelRenderer Cube;
	private ModelRenderer Side0;
	private ModelRenderer Side1;
	private ModelRenderer Side2;
	private ModelRenderer Side3;
	private ModelRenderer Paper0;
	private ModelRenderer Paper1;
	private ModelRenderer Paper2;
	private ModelRenderer Paper3;
	private ModelRenderer Paper4;
	private ModelRenderer Paper5;
	private ModelRenderer Paper6;
	private ModelRenderer Paper7;

	public ModelHolyStone() {
		textureWidth = 32;
		textureHeight = 32;

		Cube = new ModelRenderer(this, 0, 0);
		Cube.addBox(-4F, -4F, -4F, 8, 8, 8);
		Cube.setRotationPoint(0F, 16F, 0F);
		Cube.setTextureSize(64, 32);
		Cube.mirror = true;
		setRotation(Cube, 0F, 0F, 0F);
		Side0 = new ModelRenderer(this, 0, 26);
		Side0.addBox(-5F, -2F, -5F, 10, 1, 1);
		Side0.setRotationPoint(0F, 17F, 0F);
		Side0.setTextureSize(64, 32);
		Side0.mirror = true;
		setRotation(Side0, 0F, 0F, 0F);
		Side1 = new ModelRenderer(this, 0, 20);
		Side1.addBox(-5F, -2F, -5F, 10, 1, 1);
		Side1.setRotationPoint(0F, 17F, 0F);
		Side1.setTextureSize(64, 32);
		Side1.mirror = true;
		setRotation(Side1, 0F, 1.570796F, 0F);
		Side2 = new ModelRenderer(this, 0, 29);
		Side2.addBox(-5F, -2F, -5F, 10, 1, 1);
		Side2.setRotationPoint(0F, 17F, 0F);
		Side2.setTextureSize(64, 32);
		Side2.mirror = true;
		setRotation(Side2, 0F, 3.141593F, 0F);
		Side3 = new ModelRenderer(this, 0, 23);
		Side3.addBox(-5F, -2F, -5F, 10, 1, 1);
		Side3.setRotationPoint(0F, 17F, 0F);
		Side3.setTextureSize(64, 32);
		Side3.mirror = true;
		setRotation(Side3, 0F, 4.712389F, 0F);
		Paper0 = new ModelRenderer(this, 25, 23);
		Paper0.addBox(-3F, -1.5F, -5.15F, 2, 4, 0);
		Paper0.setRotationPoint(0F, 17F, 0F);
		Paper0.setTextureSize(64, 32);
		Paper0.mirror = true;
		setRotation(Paper0, -0.0698132F, 4.712389F, 0F);
		Paper1 = new ModelRenderer(this, 25, 23);
		Paper1.addBox(1F, -1.5F, -5.15F, 2, 4, 0);
		Paper1.setRotationPoint(0F, 17F, 0F);
		Paper1.setTextureSize(64, 32);
		Paper1.mirror = true;
		setRotation(Paper1, -0.0698132F, 4.712389F, 0F);
		Paper2 = new ModelRenderer(this, 25, 23);
		Paper2.addBox(-3F, -1.5F, -5.15F, 2, 4, 0);
		Paper2.setRotationPoint(0F, 17F, 0F);
		Paper2.setTextureSize(64, 32);
		Paper2.mirror = true;
		setRotation(Paper2, -0.0698132F, 3.152065F, 0F);
		Paper3 = new ModelRenderer(this, 25, 23);
		Paper3.addBox(1F, -1.5F, -5.15F, 2, 4, 0);
		Paper3.setRotationPoint(0F, 17F, 0F);
		Paper3.setTextureSize(64, 32);
		Paper3.mirror = true;
		setRotation(Paper3, -0.0698132F, 3.141593F, 0F);
		Paper4 = new ModelRenderer(this, 25, 23);
		Paper4.addBox(-3F, -1.5F, -5.15F, 2, 4, 0);
		Paper4.setRotationPoint(0F, 17F, 0F);
		Paper4.setTextureSize(64, 32);
		Paper4.mirror = true;
		setRotation(Paper4, -0.0698132F, 1.570796F, 0F);
		Paper5 = new ModelRenderer(this, 25, 23);
		Paper5.addBox(1F, -1.5F, -5.15F, 2, 4, 0);
		Paper5.setRotationPoint(0F, 17F, 0F);
		Paper5.setTextureSize(64, 32);
		Paper5.mirror = true;
		setRotation(Paper5, -0.0698132F, 1.570796F, 0F);
		Paper6 = new ModelRenderer(this, 25, 23);
		Paper6.addBox(-3F, -1.5F, -5.15F, 2, 4, 0);
		Paper6.setRotationPoint(0F, 17F, 0F);
		Paper6.setTextureSize(64, 32);
		Paper6.mirror = true;
		setRotation(Paper6, -0.0698132F, 0F, 0F);
		Paper7 = new ModelRenderer(this, 25, 23);
		Paper7.addBox(1F, -1.5F, -5.15F, 2, 4, 0);
		Paper7.setRotationPoint(0F, 17F, 0F);
		Paper7.setTextureSize(64, 32);
		Paper7.mirror = true;
		setRotation(Paper7, -0.0698132F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		renderModel(f5);
	}

	public void renderModel(float f5) {
		Cube.render(f5);
		Side0.render(f5);
		Side1.render(f5);
		Side2.render(f5);
		Side3.render(f5);
		Paper0.render(f5);
		Paper1.render(f5);
		Paper2.render(f5);
		Paper3.render(f5);
		Paper4.render(f5);
		Paper5.render(f5);
		Paper6.render(f5);
		Paper7.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
