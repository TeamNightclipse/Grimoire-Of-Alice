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
 * ModelKappaHat - Arekkuusu Created using Tabula 5.1.0
 */
public class ModelKappaHat extends ModelBiped {

	private final ModelRenderer hat;
	public ModelRenderer hat0;
	private final ModelRenderer cherry0;
	private final ModelRenderer cherry1;
	private final ModelRenderer cherry2;
	private final ModelRenderer cherry3;

	public ModelKappaHat() {
		this.textureWidth = 32;
		this.textureHeight = 32;
		this.hat = new ModelRenderer(this, 0, 0);
		this.hat.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
		this.cherry3 = new ModelRenderer(this, 0, 0);
		this.cherry3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.cherry3.addBox(4.5F, -3.5F, -0.6F, 1, 1, 1, 0.0F);
		this.hat0 = new ModelRenderer(this, 0, 17);
		this.hat0.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat0.addBox(-4.5F, -4.5F, -9.5F, 9, 1, 5, 0.0F);
		this.cherry1 = new ModelRenderer(this, 0, 0);
		this.cherry1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.cherry1.addBox(-5.5F, -5.5F, -0.8F, 1, 1, 1, 0.0F);
		this.cherry0 = new ModelRenderer(this, 0, 0);
		this.cherry0.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.cherry0.addBox(-5.5F, -4.5F, 0.4F, 1, 1, 1, 0.0F);
		this.cherry2 = new ModelRenderer(this, 0, 0);
		this.cherry2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.cherry2.addBox(4.5F, -4.5F, 0.5F, 1, 1, 1, 0.0F);
		this.hat.addChild(this.cherry3);
		this.hat.addChild(this.hat0);
		this.hat.addChild(this.cherry1);
		this.hat.addChild(this.cherry0);
		this.hat.addChild(this.cherry2);
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
