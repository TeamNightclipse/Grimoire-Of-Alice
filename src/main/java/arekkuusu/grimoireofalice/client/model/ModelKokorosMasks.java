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
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelKokorosMasks extends ModelBiped {

	//fields
	private final ModelRenderer mask1;
	private final ModelRenderer mask2;
	private final ModelRenderer mask3;
	private final ModelRenderer mask4;
	private final ModelRenderer mask5;
	private final ModelRenderer mask6;
	private final ModelRenderer mask7;
	private final ModelRenderer mask8;
	private final ModelRenderer mask9;

	public ModelKokorosMasks() {
		textureWidth = 256;
		textureHeight = 256;

		mask1 = new ModelRenderer(this, 0, 0);
		mask1.addBox(-10.5F, -5F, -31F, 21, 21, 1);
		mask1.setRotationPoint(0F, 0F, 0F);
		setRotation(mask1, 0F, 0F, 0F);
		mask2 = new ModelRenderer(this, 0, 24);
		mask2.addBox(-10.5F, -5F, 31F, 21, 21, 1);
		mask2.setRotationPoint(0F, 0F, 0F);
		setRotation(mask2, 0F, 0F, 0F);
		mask3 = new ModelRenderer(this, 48, 0);
		mask3.addBox(31F, -5F, -10.5F, 1, 21, 21);
		mask3.setRotationPoint(0F, 0F, 0F);
		setRotation(mask3, 0F, 0F, 0F);
		mask4 = new ModelRenderer(this, 95, 0);
		mask4.addBox(-31F, -5F, -10.5F, 1, 21, 21);
		mask4.setRotationPoint(0F, 0F, 0F);
		setRotation(mask4, 0F, 0F, 0F);
		mask5 = new ModelRenderer(this, 96, 88);
		mask5.addBox(30.5F, -5F, -9.5F, 1, 21, 21);
		mask5.setRotationPoint(0F, 0F, 0F);
		setRotation(mask5, 0F, 0.7941248F, 0F);
		mask6 = new ModelRenderer(this, 48, 88);
		mask6.addBox(-31.5F, -5F, -10F, 1, 21, 21);
		mask6.setRotationPoint(0F, 0F, 0F);
		setRotation(mask6, 0F, 0.7853982F, 0F);
		mask7 = new ModelRenderer(this, 96, 44);
		mask7.addBox(-31F, -5F, -10.5F, 1, 21, 21);
		mask7.setRotationPoint(0F, 0F, 0F);
		setRotation(mask7, 0F, -0.7853982F, 0F);
		mask8 = new ModelRenderer(this, 48, 44);
		mask8.addBox(31F, -5F, -10.5F, 1, 21, 21);
		mask8.setRotationPoint(0F, 0F, 0F);
		setRotation(mask8, 0F, -0.7853982F, 0F);
		mask9 = new ModelRenderer(this, 142, 0);
		mask9.addBox(12F, -15F, -5F, 1, 21, 21);
		mask9.setRotationPoint(0F, -16F, 0F);
		setRotation(mask9, 0F, 0.3490659F, 0F);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float age, float headYaw, float headPitch, float scale) {
		float maxUpAndDown = 0.05F;
		float speed = 2;
		float angle = 0;

		float toDegrees = (float) Math.PI / 180F;
		angle += speed * age;
		if(angle > 360) {
			angle -= 360;
		}

		GlStateManager.pushMatrix();
		GlStateManager.scale(0.7F, 0.7F, 0.7F);
		GlStateManager.translate(0, maxUpAndDown * Math.sin(angle * toDegrees), 0);
		GlStateManager.pushMatrix();
		GlStateManager.rotate(age * 5, 0.0F, 1.0F, 0.0F);
		mask1.render(scale);
		mask2.render(scale);
		mask3.render(scale);
		mask4.render(scale);
		mask5.render(scale);
		mask6.render(scale);
		mask7.render(scale);
		mask8.render(scale);
		GlStateManager.popMatrix();
		mask9.render(scale);
		GlStateManager.popMatrix();
	}

	private static void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}

