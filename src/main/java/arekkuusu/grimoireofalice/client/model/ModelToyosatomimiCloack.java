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
 * ModelPlayer - Arekkuusu Created using Tabula 5.1.0
 */

@SideOnly(Side.CLIENT)
public class ModelToyosatomimiCloack extends ModelBiped {

	public ModelRenderer capeBase;
	public ModelRenderer cape0;
	public ModelRenderer cape1;
	public ModelRenderer cape2;
	public ModelRenderer cape3;
	public ModelRenderer cape4;
	public ModelRenderer cape5;
	public ModelRenderer cape6;
	public ModelRenderer cape7;
	public ModelRenderer cape8;
	public ModelRenderer cape9;
	public ModelRenderer cape10;

	public ModelToyosatomimiCloack() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.cape0 = new ModelRenderer(this, 0, 2);
		this.cape0.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.cape0.addBox(-10.0F, 0.4F, 2.5F, 11, 20, 0, 0.0F);
		this.setRotateAngle(cape0, 0.08726646259971647F, 0.22689280275926282F, 0.17453292519943295F);
		this.cape9 = new ModelRenderer(this, 19, 35);
		this.cape9.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.cape9.addBox(5.5F, 0.4F, 1.0F, 0, 20, 9, 0.0F);
		this.setRotateAngle(cape9, 0.0F, -3.141592653589793F, 0.0F);
		this.cape6 = new ModelRenderer(this, 32, 41);
		this.cape6.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.cape6.addBox(-7.0F, 2.9F, -5.7F, 5, 0, 8, 0.0F);
		this.setRotateAngle(cape6, 0.0F, 0.0F, 0.8651597102135892F);
		this.cape2 = new ModelRenderer(this, 46, 6);
		this.cape2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.cape2.addBox(-3.0F, 0.7F, -5.0F, 6, 1, 1, 0.0F);
		this.cape10 = new ModelRenderer(this, 43, 41);
		this.cape10.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.cape10.addBox(2.0F, 2.9F, -5.7F, 5, 0, 8, 0.0F);
		this.setRotateAngle(cape10, 0.0F, 0.0F, -0.8651597102135892F);
		this.cape8 = new ModelRenderer(this, 27, 32);
		this.cape8.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.cape8.addBox(3.0F, 0.4F, -5.6F, 7, 0, 8, 0.0F);
		this.cape4 = new ModelRenderer(this, 27, 23);
		this.cape4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.cape4.addBox(-10.0F, 0.4F, -5.5F, 7, 0, 8, 0.0F);
		this.cape5 = new ModelRenderer(this, 0, 35);
		this.cape5.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.cape5.addBox(5.5F, 0.4F, -10.0F, 0, 20, 9, 0.0F);
		this.setRotateAngle(cape5, 0.0F, -3.141592653589793F, 0.0F);
		this.cape7 = new ModelRenderer(this, 17, 23);
		this.cape7.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.cape7.addBox(-5.5F, 0.4F, -10.0F, 8, 20, 0, 0.0F);
		this.setRotateAngle(cape7, 0.08726646259971647F, -1.5707963267948966F, -0.08726646259971647F);
		this.cape1 = new ModelRenderer(this, 23, 2);
		this.cape1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.cape1.addBox(-1.0F, 0.4F, 2.5F, 11, 20, 0, 0.0F);
		this.setRotateAngle(cape1, 0.08726646259971647F, -0.22689280275926282F, -0.17453292519943295F);
		this.cape3 = new ModelRenderer(this, 0, 23);
		this.cape3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.cape3.addBox(-5.5F, 0.4F, 10.0F, 8, 20, 0, 0.0F);
		this.setRotateAngle(cape3, 0.08726646259971647F, -1.5707963267948966F, -0.08726646259971647F);
		this.capeBase = new ModelRenderer(this, 46, 0);
		this.capeBase.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.capeBase.addBox(-2.0F, 0.0F, 3.0F, 4, 2, 0, 0.0F);
		this.capeBase.addChild(this.cape0);
		this.cape7.addChild(this.cape9);
		this.cape4.addChild(this.cape6);
		this.capeBase.addChild(this.cape2);
		this.cape8.addChild(this.cape10);
		this.cape1.addChild(this.cape8);
		this.cape0.addChild(this.cape4);
		this.cape3.addChild(this.cape5);
		this.cape1.addChild(this.cape7);
		this.capeBase.addChild(this.cape1);
		this.cape0.addChild(this.cape3);
	}

    @SideOnly(Side.CLIENT)
    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		bipedBody = capeBase;
		bipedRightArm.showModel = false;
		bipedLeftArm.showModel = false;
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
