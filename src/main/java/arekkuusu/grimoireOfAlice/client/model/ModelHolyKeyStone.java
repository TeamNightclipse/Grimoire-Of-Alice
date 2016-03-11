/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHolyKeyStone extends ModelBase {

	//fields
	ModelRenderer stone1;
	ModelRenderer stone2;
	ModelRenderer stone3;
	ModelRenderer stone4;
	ModelRenderer stone5;
	ModelRenderer stone6;
	ModelRenderer string1;
	ModelRenderer string2;
	ModelRenderer string3;
	ModelRenderer string4;
	ModelRenderer string5;
	ModelRenderer string6;
	ModelRenderer string7;
	ModelRenderer string8;
	ModelRenderer string9;
	ModelRenderer string10;
	ModelRenderer string11;
	ModelRenderer string12;

	public ModelHolyKeyStone() {
		textureWidth = 64;
		textureHeight = 32;

		stone1 = new ModelRenderer(this, 16, 17);
		stone1.addBox(-6F, 2F, -6F, 12, 3, 12);
		stone1.setRotationPoint(0F, 10F, 0F);
		stone1.setTextureSize(64, 32);
		stone1.mirror = true;
		setRotation(stone1, 0F, 0F, 0F);
		stone2 = new ModelRenderer(this, 0, 6);
		stone2.addBox(-5F, 5F, -5F, 10, 1, 10);
		stone2.setRotationPoint(0F, 10F, 0F);
		stone2.setTextureSize(64, 32);
		stone2.mirror = true;
		setRotation(stone2, 0F, 0F, 0F);
		stone3 = new ModelRenderer(this, 32, 7);
		stone3.addBox(-4F, 6F, -4F, 8, 1, 8);
		stone3.setRotationPoint(0F, 10F, 0F);
		stone3.setTextureSize(64, 32);
		stone3.mirror = true;
		setRotation(stone3, 0F, 0F, 0F);
		stone4 = new ModelRenderer(this, 40, 0);
		stone4.addBox(-3F, 7F, -3F, 6, 1, 6);
		stone4.setRotationPoint(0F, 10F, 0F);
		stone4.setTextureSize(64, 32);
		stone4.mirror = true;
		setRotation(stone4, 0F, 0F, 0F);
		stone5 = new ModelRenderer(this, 0, 21);
		stone5.addBox(-2.5F, 8F, -2.5F, 5, 1, 5);
		stone5.setRotationPoint(0F, 10F, 0F);
		stone5.setTextureSize(64, 32);
		stone5.mirror = true;
		setRotation(stone5, 0F, 0F, 0F);
		stone6 = new ModelRenderer(this, 0, 27);
		stone6.addBox(-2F, 9F, -2F, 4, 1, 4);
		stone6.setRotationPoint(0F, 10F, 0F);
		stone6.setTextureSize(64, 32);
		stone6.mirror = true;
		setRotation(stone6, 0F, 0F, 0F);
		string1 = new ModelRenderer(this, 28, 0);
		string1.addBox(3F, 3F, -5.8F, 2, 6, 0);
		string1.setRotationPoint(0F, 10F, 0F);
		string1.setTextureSize(64, 32);
		string1.mirror = true;
		setRotation(string1, -0.1047198F, 0F, 0F);
		string2 = new ModelRenderer(this, 16, 0);
		string2.addBox(-1F, 3F, -5.8F, 2, 6, 0);
		string2.setRotationPoint(0F, 10F, 0F);
		string2.setTextureSize(64, 32);
		string2.mirror = true;
		setRotation(string2, -0.1047198F, 0F, 0F);
		string3 = new ModelRenderer(this, 4, 0);
		string3.addBox(-5F, 3F, -5.8F, 2, 6, 0);
		string3.setRotationPoint(0F, 10F, 0F);
		string3.setTextureSize(64, 32);
		string3.mirror = true;
		setRotation(string3, -0.1047198F, 0F, 0F);
		string4 = new ModelRenderer(this, 4, 0);
		string4.addBox(3F, 3F, -5.8F, 2, 6, 0);
		string4.setRotationPoint(0F, 10F, 0F);
		string4.setTextureSize(64, 32);
		string4.mirror = true;
		setRotation(string4, -0.1047198F, 1.570796F, 0F);
		string5 = new ModelRenderer(this, 16, 0);
		string5.addBox(-1F, 3F, -5.8F, 2, 6, 0);
		string5.setRotationPoint(0F, 10F, 0F);
		string5.setTextureSize(64, 32);
		string5.mirror = true;
		setRotation(string5, -0.1047198F, 1.570796F, 0F);
		string6 = new ModelRenderer(this, 28, 0);
		string6.addBox(-5F, 3F, -5.8F, 2, 6, 0);
		string6.setRotationPoint(0F, 10F, 0F);
		string6.setTextureSize(64, 32);
		string6.mirror = true;
		setRotation(string6, -0.1047198F, 1.570796F, 0F);
		string7 = new ModelRenderer(this, 16, 0);
		string7.addBox(-5F, 3F, 5.8F, 2, 6, 0);
		string7.setRotationPoint(0F, 10F, 0F);
		string7.setTextureSize(64, 32);
		string7.mirror = true;
		setRotation(string7, 0.1047198F, 0F, 0F);
		string8 = new ModelRenderer(this, 28, 0);
		string8.addBox(-1F, 3F, 5.8F, 2, 6, 0);
		string8.setRotationPoint(0F, 10F, 0F);
		string8.setTextureSize(64, 32);
		string8.mirror = true;
		setRotation(string8, 0.1047198F, 0F, 0F);
		string9 = new ModelRenderer(this, 4, 0);
		string9.addBox(3F, 3F, 5.8F, 2, 6, 0);
		string9.setRotationPoint(0F, 10F, 0F);
		string9.setTextureSize(64, 32);
		string9.mirror = true;
		setRotation(string9, 0.1047198F, 0F, 0F);
		string10 = new ModelRenderer(this, 16, 0);
		string10.addBox(-5F, 3F, 5.8F, 2, 6, 0);
		string10.setRotationPoint(0F, 10F, 0F);
		string10.setTextureSize(64, 32);
		string10.mirror = true;
		setRotation(string10, 0.1047198F, 1.570796F, 0F);
		string11 = new ModelRenderer(this, 4, 0);
		string11.addBox(-1F, 3F, 5.8F, 2, 6, 0);
		string11.setRotationPoint(0F, 10F, 0F);
		string11.setTextureSize(64, 32);
		string11.mirror = true;
		setRotation(string11, 0.1047198F, 1.570796F, 0F);
		string12 = new ModelRenderer(this, 28, 0);
		string12.addBox(3F, 3F, 5.8F, 2, 6, 0);
		string12.setRotationPoint(0F, 10F, 0F);
		string12.setTextureSize(64, 32);
		string12.mirror = true;
		setRotation(string12, 0.1047198F, 1.570796F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		stone1.render(f5);
		stone2.render(f5);
		stone3.render(f5);
		stone4.render(f5);
		stone5.render(f5);
		stone6.render(f5);
		string1.render(f5);
		string2.render(f5);
		string3.render(f5);
		string4.render(f5);
		string5.render(f5);
		string6.render(f5);
		string7.render(f5);
		string8.render(f5);
		string9.render(f5);
		string10.render(f5);
		string11.render(f5);
		string12.render(f5);
	}

	public void renderModel(float f5) {

		stone1.render(f5);
		stone2.render(f5);
		stone3.render(f5);
		stone4.render(f5);
		stone5.render(f5);
		stone6.render(f5);
		string1.render(f5);
		string2.render(f5);
		string3.render(f5);
		string4.render(f5);
		string5.render(f5);
		string6.render(f5);
		string7.render(f5);
		string8.render(f5);
		string9.render(f5);
		string10.render(f5);
		string11.render(f5);
		string12.render(f5);

	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
