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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * SuwakoHat - Arekkuusu Created using Tabula 5.1.0
 */

@SideOnly(Side.CLIENT)
public class ModelSuwakoHat extends ModelBiped {

	private final ModelRenderer hat;
	private final ModelRenderer hatbase;
	private final ModelRenderer side0;
	private final ModelRenderer side1;
	private final ModelRenderer side2;
	private final ModelRenderer side3;
	private final ModelRenderer eyes;

	public ModelSuwakoHat() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.eyes = new ModelRenderer(this, 32, 32);
		this.eyes.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.eyes.addBox(-4.0F, -16.0F, -4.0F, 8, 3, 0, 0.0F);
		this.side3 = new ModelRenderer(this, 0, 35);
		this.side3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.side3.addBox(6.0F, -8.1F, -7.0F, 1, 1, 14, 0.0F);
		this.side2 = new ModelRenderer(this, 0, 32);
		this.side2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.side2.addBox(-7.0F, -8.1F, 6.0F, 14, 1, 1, 0.0F);
		this.hatbase = new ModelRenderer(this, 19, 0);
		this.hatbase.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hatbase.addBox(-7.0F, -8.0F, -7.0F, 14, 0, 14, 0.0F);
		this.hat = new ModelRenderer(this, 0, 0);
		this.hat.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat.addBox(-4.0F, -13.0F, -4.0F, 8, 5, 8, 0.0F);
		this.side1 = new ModelRenderer(this, 0, 32);
		this.side1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.side1.addBox(-7.0F, -8.1F, -7.0F, 14, 1, 1, 0.0F);
		this.side0 = new ModelRenderer(this, 0, 14);
		this.side0.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.side0.addBox(-7.0F, -8.1F, -7.0F, 1, 1, 14, 0.0F);
		this.hat.addChild(this.eyes);
		this.hat.addChild(this.side3);
		this.hat.addChild(this.side2);
		this.hat.addChild(this.hatbase);
		this.hat.addChild(this.side1);
		this.hat.addChild(this.side0);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		bipedHeadwear.showModel = false;
		bipedHead = hat;
		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
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
