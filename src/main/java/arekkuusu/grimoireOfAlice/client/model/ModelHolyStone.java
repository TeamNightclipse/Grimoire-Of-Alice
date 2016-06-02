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

	private ModelRenderer cube;
	private ModelRenderer side0;
	private ModelRenderer side1;
	private ModelRenderer side2;
	private ModelRenderer side3;
	private ModelRenderer paper0;
	private ModelRenderer paper1;
	private ModelRenderer paper2;
	private ModelRenderer paper3;
	private ModelRenderer paper4;
	private ModelRenderer paper5;
	private ModelRenderer paper6;
	private ModelRenderer paper7;

	public ModelHolyStone() {
		textureWidth = 32;
		textureHeight = 32;

		cube = new ModelRenderer(this, 0, 0);
		cube.addBox(-4F, -4F, -4F, 8, 8, 8);
		cube.setRotationPoint(0F, 16F, 0F);
		cube.setTextureSize(64, 32);
		cube.mirror = true;
		setRotation(cube, 0F, 0F, 0F);
		side0 = new ModelRenderer(this, 0, 26);
		side0.addBox(-5F, -2F, -5F, 10, 1, 1);
		side0.setRotationPoint(0F, 17F, 0F);
		side0.setTextureSize(64, 32);
		side0.mirror = true;
		setRotation(side0, 0F, 0F, 0F);
		side1 = new ModelRenderer(this, 0, 20);
		side1.addBox(-5F, -2F, -5F, 10, 1, 1);
		side1.setRotationPoint(0F, 17F, 0F);
		side1.setTextureSize(64, 32);
		side1.mirror = true;
		setRotation(side1, 0F, 1.570796F, 0F);
		side2 = new ModelRenderer(this, 0, 29);
		side2.addBox(-5F, -2F, -5F, 10, 1, 1);
		side2.setRotationPoint(0F, 17F, 0F);
		side2.setTextureSize(64, 32);
		side2.mirror = true;
		setRotation(side2, 0F, 3.141593F, 0F);
		side3 = new ModelRenderer(this, 0, 23);
		side3.addBox(-5F, -2F, -5F, 10, 1, 1);
		side3.setRotationPoint(0F, 17F, 0F);
		side3.setTextureSize(64, 32);
		side3.mirror = true;
		setRotation(side3, 0F, 4.712389F, 0F);
		paper0 = new ModelRenderer(this, 25, 23);
		paper0.addBox(-3F, -1.5F, -5.15F, 2, 4, 0);
		paper0.setRotationPoint(0F, 17F, 0F);
		paper0.setTextureSize(64, 32);
		paper0.mirror = true;
		setRotation(paper0, -0.0698132F, 4.712389F, 0F);
		paper1 = new ModelRenderer(this, 25, 23);
		paper1.addBox(1F, -1.5F, -5.15F, 2, 4, 0);
		paper1.setRotationPoint(0F, 17F, 0F);
		paper1.setTextureSize(64, 32);
		paper1.mirror = true;
		setRotation(paper1, -0.0698132F, 4.712389F, 0F);
		paper2 = new ModelRenderer(this, 25, 23);
		paper2.addBox(-3F, -1.5F, -5.15F, 2, 4, 0);
		paper2.setRotationPoint(0F, 17F, 0F);
		paper2.setTextureSize(64, 32);
		paper2.mirror = true;
		setRotation(paper2, -0.0698132F, 3.152065F, 0F);
		paper3 = new ModelRenderer(this, 25, 23);
		paper3.addBox(1F, -1.5F, -5.15F, 2, 4, 0);
		paper3.setRotationPoint(0F, 17F, 0F);
		paper3.setTextureSize(64, 32);
		paper3.mirror = true;
		setRotation(paper3, -0.0698132F, 3.141593F, 0F);
		paper4 = new ModelRenderer(this, 25, 23);
		paper4.addBox(-3F, -1.5F, -5.15F, 2, 4, 0);
		paper4.setRotationPoint(0F, 17F, 0F);
		paper4.setTextureSize(64, 32);
		paper4.mirror = true;
		setRotation(paper4, -0.0698132F, 1.570796F, 0F);
		paper5 = new ModelRenderer(this, 25, 23);
		paper5.addBox(1F, -1.5F, -5.15F, 2, 4, 0);
		paper5.setRotationPoint(0F, 17F, 0F);
		paper5.setTextureSize(64, 32);
		paper5.mirror = true;
		setRotation(paper5, -0.0698132F, 1.570796F, 0F);
		paper6 = new ModelRenderer(this, 25, 23);
		paper6.addBox(-3F, -1.5F, -5.15F, 2, 4, 0);
		paper6.setRotationPoint(0F, 17F, 0F);
		paper6.setTextureSize(64, 32);
		paper6.mirror = true;
		setRotation(paper6, -0.0698132F, 0F, 0F);
		paper7 = new ModelRenderer(this, 25, 23);
		paper7.addBox(1F, -1.5F, -5.15F, 2, 4, 0);
		paper7.setRotationPoint(0F, 17F, 0F);
		paper7.setTextureSize(64, 32);
		paper7.mirror = true;
		setRotation(paper7, -0.0698132F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float age, float headYaw, float headPitch, float scale) {
		super.render(entity, limbSwing, limbSwingAmount, age, headYaw, headPitch, scale);
		setRotationAngles(limbSwing, limbSwingAmount, age, headYaw, headPitch, scale, entity);
		cube.render(scale);
		side0.render(scale);
		side1.render(scale);
		side2.render(scale);
		side3.render(scale);
		paper0.render(scale);
		paper1.render(scale);
		paper2.render(scale);
		paper3.render(scale);
		paper4.render(scale);
		paper5.render(scale);
		paper6.render(scale);
		paper7.render(scale);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
