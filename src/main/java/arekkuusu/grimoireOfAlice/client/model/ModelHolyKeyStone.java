/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.client.model;

import arekkuusu.grimoireOfAlice.tmp.CleanupDone;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@CleanupDone
public class ModelHolyKeyStone extends ModelBase {

	private ModelRenderer stone1;
	private ModelRenderer stone2;
	private ModelRenderer stone3;
	private ModelRenderer stone4;
	private ModelRenderer stone5;
	private ModelRenderer stone6;
	private ModelRenderer stone7;
	private ModelRenderer string1;
	private ModelRenderer string2;
	private ModelRenderer string3;
	private ModelRenderer string4;
	private ModelRenderer string5;
	private ModelRenderer string6;
	private ModelRenderer string7;
	private ModelRenderer string8;
	private ModelRenderer string9;
	private ModelRenderer string10;
	private ModelRenderer string11;
	private ModelRenderer string12;

	public ModelHolyKeyStone() {
		textureWidth = 64;
		textureHeight = 64;
		float piHalf = (float)(Math.PI / 2);

		stone1 = new ModelRenderer(this, 7, 46);
		stone1.addBox(-7F, 2F, -7F, 14, 4, 14);
		stone1.setRotationPoint(0F, 5F, 0F);
		stone1.setTextureSize(64, 64);
		stone1.mirror = true;
		setRotation(stone1, 0F, 0F, 0F);
		stone2 = new ModelRenderer(this, 0, 10);
		stone2.addBox(-6F, 5F, -6F, 12, 2, 12);
		stone2.setRotationPoint(0F, 6F, 0F);
		stone2.setTextureSize(64, 64);
		stone2.mirror = true;
		setRotation(stone2, 0F, 0F, 0F);
		stone3 = new ModelRenderer(this, 24, 24);
		stone3.addBox(-5F, 6F, -5F, 10, 1, 10);
		stone3.setRotationPoint(0F, 7F, 0F);
		stone3.setTextureSize(64, 64);
		stone3.mirror = true;
		setRotation(stone3, 0F, 0F, 0F);
		stone4 = new ModelRenderer(this, 32, 1);
		stone4.addBox(-4F, 7F, -4F, 8, 1, 8);
		stone4.setRotationPoint(0F, 7F, 0F);
		stone4.setTextureSize(64, 64);
		stone4.mirror = true;
		setRotation(stone4, 0F, 0F, 0F);
		stone5 = new ModelRenderer(this, 0, 28);
		stone5.addBox(-3.5F, 8F, -3.5F, 7, 1, 7);
		stone5.setRotationPoint(0F, 7F, 0F);
		stone5.setTextureSize(64, 64);
		stone5.mirror = true;
		setRotation(stone5, 0F, 0F, 0F);
		stone6 = new ModelRenderer(this, 0, 37);
		stone6.addBox(-3F, 9F, -3F, 6, 1, 6);
		stone6.setRotationPoint(0F, 7F, 0F);
		stone6.setTextureSize(64, 64);
		stone6.mirror = true;
		setRotation(stone6, 0F, 0F, 0F);
		stone7 = new ModelRenderer(this, 18, 37);
		stone7.addBox(-2F, 11F, -2F, 4, 1, 4);
		stone7.setRotationPoint(0F, 6F, 0F);
		stone7.setTextureSize(64, 64);
		stone7.mirror = true;
		setRotation(stone7, 0F, 0F, 0F);
		string1 = new ModelRenderer(this, 28, 0);
		string1.addBox(3F, 1.8F, -6.9F, 2, 8, 0);
		string1.setRotationPoint(0F, 9F, 0F);
		string1.setTextureSize(64, 64);
		string1.mirror = true;
		setRotation(string1, -0.1047198F, 0F, 0F);
		string2 = new ModelRenderer(this, 16, 0);
		string2.addBox(-1F, 1.8F, -6.9F, 2, 8, 0);
		string2.setRotationPoint(0F, 9F, 0F);
		string2.setTextureSize(64, 64);
		string2.mirror = true;
		setRotation(string2, -0.1047198F, 0F, 0F);
		string3 = new ModelRenderer(this, 4, 0);
		string3.addBox(-5F, 1.8F, -6.9F, 2, 8, 0);
		string3.setRotationPoint(0F, 9F, 0F);
		string3.setTextureSize(64, 64);
		string3.mirror = true;
		setRotation(string3, -0.1047198F, 0F, 0F);
		string4 = new ModelRenderer(this, 4, 0);
		string4.addBox(3F, 1.8F, -6.9F, 2, 8, 0);
		string4.setRotationPoint(0F, 9F, 0F);
		string4.setTextureSize(64, 64);
		string4.mirror = true;
		setRotation(string4, -0.1047198F, piHalf, 0F);
		string5 = new ModelRenderer(this, 16, 0);
		string5.addBox(-1F, 1.8F, -6.9F, 2, 8, 0);
		string5.setRotationPoint(0F, 9F, 0F);
		string5.setTextureSize(64, 64);
		string5.mirror = true;
		setRotation(string5, -0.1047198F, piHalf, 0F);
		string6 = new ModelRenderer(this, 28, 0);
		string6.addBox(-5F, 1.8F, -6.9F, 2, 8, 0);
		string6.setRotationPoint(0F, 9F, 0F);
		string6.setTextureSize(64, 64);
		string6.mirror = true;
		setRotation(string6, -0.1047198F, piHalf, 0F);
		string7 = new ModelRenderer(this, 16, 0);
		string7.addBox(-5F, 1.8F, 6.9F, 2, 8, 0);
		string7.setRotationPoint(0F, 9F, 0F);
		string7.setTextureSize(64, 64);
		string7.mirror = true;
		setRotation(string7, 0.1047198F, 0F, 0F);
		string8 = new ModelRenderer(this, 28, 0);
		string8.addBox(-1F, 1.8F, 6.9F, 2, 8, 0);
		string8.setRotationPoint(0F, 9F, 0F);
		string8.setTextureSize(64, 64);
		string8.mirror = true;
		setRotation(string8, 0.1047198F, 0F, 0F);
		string9 = new ModelRenderer(this, 4, 0);
		string9.addBox(3F, 1.8F, 6.9F, 2, 8, 0);
		string9.setRotationPoint(0F, 9F, 0F);
		string9.setTextureSize(64, 64);
		string9.mirror = true;
		setRotation(string9, 0.1047198F, 0F, 0F);
		string10 = new ModelRenderer(this, 16, 0);
		string10.addBox(-5F, 1.8F, 6.9F, 2, 8, 0);
		string10.setRotationPoint(0F, 9F, 0F);
		string10.setTextureSize(64, 64);
		string10.mirror = true;
		setRotation(string10, 0.1047198F, piHalf, 0F);
		string11 = new ModelRenderer(this, 4, 0);
		string11.addBox(-1F, 1.8F, 6.9F, 2, 8, 0);
		string11.setRotationPoint(0F, 9F, 0F);
		string11.setTextureSize(64, 64);
		string11.mirror = true;
		setRotation(string11, 0.1047198F, piHalf, 0F);
		string12 = new ModelRenderer(this, 28, 0);
		string12.addBox(3F, 1.8F, 6.9F, 2, 8, 0);
		string12.setRotationPoint(0F, 9F, 0F);
		string12.setTextureSize(64, 64);
		string12.mirror = true;
		setRotation(string12, 0.1047198F, piHalf, 0F);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float age, float headYaw, float headPitch, float scale) {
		super.render(entity, limbSwing, limbSwingAmount, age, headYaw, headPitch, scale);
		setRotationAngles(limbSwing, limbSwingAmount, age, headYaw, headPitch, scale, entity);
		stone1.render(scale);
		stone2.render(scale);
		stone3.render(scale);
		stone4.render(scale);
		stone5.render(scale);
		stone6.render(scale);
		stone7.render(scale);
		string1.render(scale);
		string2.render(scale);
		string3.render(scale);
		string4.render(scale);
		string5.render(scale);
		string6.render(scale);
		string7.render(scale);
		string8.render(scale);
		string9.render(scale);
		string10.render(scale);
		string11.render(scale);
		string12.render(scale);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
