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
 * ModelPlayer - Either Mojang or a mod author
 * Created using Tabula 5.1.0
 */
public class ModelShinmyoumaruHat extends ModelBiped {

    public ModelRenderer hatBase;
    public ModelRenderer hatBase0;
    public ModelRenderer hatBase1;
    public ModelRenderer hatBase2;
    public ModelRenderer hatBase3;
    public ModelRenderer hatBottom;
    public ModelRenderer hatMid;
    public ModelRenderer hatTop;

    public ModelShinmyoumaruHat() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.hatBase1 = new ModelRenderer(this, 0, 15);
        this.hatBase1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.hatBase1.addBox(-7.6F, -8.6F, -8.0F, 1, 4, 15, 0.0F);
        this.hatMid = new ModelRenderer(this, 0, 51);
        this.hatMid.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.hatMid.addBox(-5.0F, -11.0F, -5.5F, 10, 1, 10, 0.0F);
        this.hatTop = new ModelRenderer(this, 39, 37);
        this.hatTop.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.hatTop.addBox(-3.0F, -12.5F, -3.5F, 6, 2, 6, 0.0F);
        this.hatBase = new ModelRenderer(this, -14, 0);
        this.hatBase.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.hatBase.addBox(-7.0F, -8.0F, -7.5F, 14, 0, 14, 0.5F);
        this.setRotateAngle(hatBase, -0.27314402793711257F, 0.0F, 0.0F);
        this.hatBase2 = new ModelRenderer(this, 32, 15);
        this.hatBase2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.hatBase2.addBox(6.6F, -8.6F, -8.0F, 1, 4, 15, 0.0F);
        this.hatBase3 = new ModelRenderer(this, 32, 6);
        this.hatBase3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.hatBase3.addBox(-7.5F, -8.6F, 6.1F, 15, 4, 1, 0.0F);
        this.hatBottom = new ModelRenderer(this, 0, 35);
        this.hatBottom.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.hatBottom.addBox(-6.0F, -10.0F, -6.5F, 12, 2, 12, 0.0F);
        this.hatBase0 = new ModelRenderer(this, 32, 0);
        this.hatBase0.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.hatBase0.addBox(-7.5F, -8.6F, -8.1F, 15, 4, 1, 0.0F);
        this.hatBase.addChild(this.hatBase1);
        this.hatBase.addChild(this.hatMid);
        this.hatBase.addChild(this.hatTop);
        this.hatBase.addChild(this.hatBase2);
        this.hatBase.addChild(this.hatBase3);
        this.hatBase.addChild(this.hatBottom);
        this.hatBase.addChild(this.hatBase0);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		bipedHeadwear.showModel = false;
		bipedHead = hatBase;
		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch - 25, scale);
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
