/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelKokorosMasks extends ModelBiped {

	//fields
	private final ModelRenderer Mask1;
	private final ModelRenderer Mask2;
	private final ModelRenderer Mask3;
	private final ModelRenderer Mask4;
	private final ModelRenderer Mask5;
	private final ModelRenderer Mask6;
	private final ModelRenderer Mask7;
	private final ModelRenderer Mask8;
	private final ModelRenderer Mask9;

	public ModelKokorosMasks() {
		textureWidth = 256;
		textureHeight = 256;

		Mask1 = new ModelRenderer(this, 0, 0);
		Mask1.addBox(-10.5F, -5F, -31F, 21, 21, 1);
		Mask1.setRotationPoint(0F, 0F, 0F);
		setRotation(Mask1, 0F, 0F, 0F);
		Mask2 = new ModelRenderer(this, 0, 24);
		Mask2.addBox(-10.5F, -5F, 31F, 21, 21, 1);
		Mask2.setRotationPoint(0F, 0F, 0F);
		setRotation(Mask2, 0F, 0F, 0F);
		Mask3 = new ModelRenderer(this, 48, 0);
		Mask3.addBox(31F, -5F, -10.5F, 1, 21, 21);
		Mask3.setRotationPoint(0F, 0F, 0F);
		setRotation(Mask3, 0F, 0F, 0F);
		Mask4 = new ModelRenderer(this, 95, 0);
		Mask4.addBox(-31F, -5F, -10.5F, 1, 21, 21);
		Mask4.setRotationPoint(0F, 0F, 0F);
		setRotation(Mask4, 0F, 0F, 0F);
		Mask5 = new ModelRenderer(this, 96, 88);
		Mask5.addBox(30.5F, -5F, -9.5F, 1, 21, 21);
		Mask5.setRotationPoint(0F, 0F, 0F);
		setRotation(Mask5, 0F, 0.7941248F, 0F);
		Mask6 = new ModelRenderer(this, 48, 88);
		Mask6.addBox(-31.5F, -5F, -10F, 1, 21, 21);
		Mask6.setRotationPoint(0F, 0F, 0F);
		setRotation(Mask6, 0F, 0.7853982F, 0F);
		Mask7 = new ModelRenderer(this, 96, 44);
		Mask7.addBox(-31F, -5F, -10.5F, 1, 21, 21);
		Mask7.setRotationPoint(0F, 0F, 0F);
		setRotation(Mask7, 0F, -0.7853982F, 0F);
		Mask8 = new ModelRenderer(this, 48, 44);
		Mask8.addBox(31F, -5F, -10.5F, 1, 21, 21);
		Mask8.setRotationPoint(0F, 0F, 0F);
		setRotation(Mask8, 0F, -0.7853982F, 0F);
		Mask9 = new ModelRenderer(this, 142, 0);
		Mask9.addBox(12F, -15F, -5F, 1, 21, 21);
		Mask9.setRotationPoint(0F, -16F, 0F);
		setRotation(Mask9, 0F, 0.3490659F, 0F);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float age, float headYaw, float headPitch, float scale) {
		this.Mask1.render(scale);
		this.Mask2.render(scale);
		this.Mask3.render(scale);
		this.Mask4.render(scale);
		this.Mask5.render(scale);
		this.Mask6.render(scale);
		this.Mask7.render(scale);
		this.Mask8.render(scale);
		this.Mask9.render(scale);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}

