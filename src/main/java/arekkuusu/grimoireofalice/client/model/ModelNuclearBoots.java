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

/**
 * ModelPlayer - Arekkuusu Created using Tabula 5.1.0
 */
public class ModelNuclearBoots extends ModelBiped {

	public ModelRenderer bootRight;
	public ModelRenderer bootLeft;
	public ModelRenderer atomRing0;
	public ModelRenderer atomRing1;

	public ModelNuclearBoots() {
		this.textureWidth = 48;
		this.textureHeight = 32;
		this.atomRing1 = new ModelRenderer(this, 8, 16);
		this.atomRing1.setRotationPoint(1.9F, 12.0F, 0.1F);
		this.atomRing1.addBox(-9.4F, -1.5F, -4.5F, 9, 0, 9, 0.0F);
		this.setRotateAngle(atomRing1, -0.0F, 0.0F, 0.8726646259971648F);
		this.bootRight = new ModelRenderer(this, 0, 0);
		this.bootRight.setRotationPoint(-1.9F, 12.0F, 0.1F);
		this.bootRight.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
		this.bootLeft = new ModelRenderer(this, 0, 16);
		this.bootLeft.setRotationPoint(1.9F, 12.0F, 0.1F);
		this.bootLeft.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
		this.atomRing0 = new ModelRenderer(this, 8, 0);
		this.atomRing0.setRotationPoint(1.9F, 12.0F, 0.1F);
		this.atomRing0.addBox(-2.0F, -4.6F, -4.5F, 9, 0, 9, 0.0F);
		this.setRotateAngle(atomRing0, -0.0F, 0.0F, -0.8726646259971648F);
		this.bootLeft.addChild(this.atomRing1);
		this.bootLeft.addChild(this.atomRing0);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		bipedLeftLeg = bootLeft;
		bipedRightLeg = bootRight;
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
