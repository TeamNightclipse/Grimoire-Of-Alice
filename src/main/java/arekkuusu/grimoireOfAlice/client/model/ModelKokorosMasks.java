/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelKokorosMasks extends ModelBiped {

	//fields
	ModelRenderer Mask1;
	ModelRenderer Mask2;
	ModelRenderer Mask3;
	ModelRenderer Maks4;
	ModelRenderer Mask5;
	ModelRenderer Mask6;
	ModelRenderer Mask7;
	ModelRenderer Mask8;
	ModelRenderer Mask9;

	public ModelKokorosMasks() {
		textureWidth = 256;
		textureHeight = 256;

		Mask1 = new ModelRenderer(this, 0, 0);
		Mask1.addBox(-10.5F, -5F, -31F, 21, 21, 1);
		Mask1.setRotationPoint(0F, 0F, 0F);
		Mask1.setTextureSize(256, 256);
		Mask1.mirror = true;
		setRotation(Mask1, 0F, 0F, 0F);
		Mask2 = new ModelRenderer(this, 0, 24);
		Mask2.addBox(-10.5F, -5F, 31F, 21, 21, 1);
		Mask2.setRotationPoint(0F, 0F, 0F);
		Mask2.setTextureSize(256, 256);
		Mask2.mirror = true;
		setRotation(Mask2, 0F, 0F, 0F);
		Mask3 = new ModelRenderer(this, 48, 0);
		Mask3.addBox(31F, -5F, -10.5F, 1, 21, 21);
		Mask3.setRotationPoint(0F, 0F, 0F);
		Mask3.setTextureSize(256, 256);
		Mask3.mirror = true;
		setRotation(Mask3, 0F, 0F, 0F);
		Maks4 = new ModelRenderer(this, 95, 0);
		Maks4.addBox(-31F, -5F, -10.5F, 1, 21, 21);
		Maks4.setRotationPoint(0F, 0F, 0F);
		Maks4.setTextureSize(256, 256);
		Maks4.mirror = true;
		setRotation(Maks4, 0F, 0F, 0F);
		Mask5 = new ModelRenderer(this, 96, 88);
		Mask5.addBox(30.5F, -5F, -9.5F, 1, 21, 21);
		Mask5.setRotationPoint(0F, 0F, 0F);
		Mask5.setTextureSize(256, 256);
		Mask5.mirror = true;
		setRotation(Mask5, 0F, 0.7941248F, 0F);
		Mask6 = new ModelRenderer(this, 48, 88);
		Mask6.addBox(-31.5F, -5F, -10F, 1, 21, 21);
		Mask6.setRotationPoint(0F, 0F, 0F);
		Mask6.setTextureSize(256, 256);
		Mask6.mirror = true;
		setRotation(Mask6, 0F, 0.7853982F, 0F);
		Mask7 = new ModelRenderer(this, 96, 44);
		Mask7.addBox(-31F, -5F, -10.5F, 1, 21, 21);
		Mask7.setRotationPoint(0F, 0F, 0F);
		Mask7.setTextureSize(256, 256);
		Mask7.mirror = true;
		setRotation(Mask7, 0F, -0.7853982F, 0F);
		Mask8 = new ModelRenderer(this, 48, 44);
		Mask8.addBox(31F, -5F, -10.5F, 1, 21, 21);
		Mask8.setRotationPoint(0F, 0F, 0F);
		Mask8.setTextureSize(256, 256);
		Mask8.mirror = true;
		setRotation(Mask8, 0F, -0.7853982F, 0F);
		Mask9 = new ModelRenderer(this, 142, 0);
		Mask9.addBox(12F, -15F, -5F, 1, 21, 21);
		Mask9.setRotationPoint(0F, -16F, 0F);
		Mask9.setTextureSize(256, 256);
		Mask9.mirror = true;
		setRotation(Mask9, 0F, 0.3490659F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Mask1.render(f5);
		Mask2.render(f5);
		Mask3.render(f5);
		Maks4.render(f5);
		Mask5.render(f5);
		Mask6.render(f5);
		Mask7.render(f5);
		Mask8.render(f5);
		Mask9.render(f5);
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
