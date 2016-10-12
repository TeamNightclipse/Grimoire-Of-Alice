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

public class ModelToyosatomimiAura extends ModelBiped {

	//fields
	private final ModelRenderer aura;
	private final ModelRenderer head;
	private final ModelRenderer capeBase;
	private final ModelRenderer cape;
	private final ModelRenderer cape1;
	private final ModelRenderer cape2;
	private final ModelRenderer cape3;
	private final ModelRenderer cape4;
	private final ModelRenderer cape5;
	private final ModelRenderer cape6;
	private final ModelRenderer earLeft;
	private final ModelRenderer earRight;

	public ModelToyosatomimiAura() {
		textureWidth = 1024;
		textureHeight = 512;

		aura = new ModelRenderer(this, 1024, 512);
		aura.setRotationPoint(0F, -16F, 10F);
		aura.addBox(-254F, -248F, 0F, 511, 404, 1);
		this.cape4 = new ModelRenderer(this, 33, 445);
		this.cape4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.cape4.addBox(9.0F, -0.5F, -3.0F, 0, 22, 7, 0.25F);
		this.cape5 = new ModelRenderer(this, 0, 472);
		this.cape5.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.cape5.addBox(-11.0F, 0.8F, -3.0F, 7, 0, 6, 0.25F);
		this.setRotationAngles(cape5, 0.0F, 0.0F, 0.4363323129985824F);
		this.head = new ModelRenderer(this, 0, 418);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-8.0F, -16.0F, -8.0F, 16, 16, 16, 0.5F);
		this.earLeft = new ModelRenderer(this, 20, 451);
		this.earLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earLeft.addBox(1.7F, -13.7F, 0.0F, 2, 6, 0, 0.5F);
		this.setRotationAngles(earLeft, 0.0F, 0.0F, -0.4553564018453205F);
		this.cape6 = new ModelRenderer(this, 0, 479);
		this.cape6.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.cape6.addBox(4.0F, 0.8F, -3.0F, 7, 0, 6, 0.25F);
		this.setRotationAngles(cape6, 0.0F, 0.0F, -0.4363323129985824F);
		this.cape2 = new ModelRenderer(this, 27, 475);
		this.cape2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.cape2.addBox(-9.0F, -0.5F, 4.0F, 18, 22, 0, 0.25F);
		this.capeBase = new ModelRenderer(this, 0, 451);
		this.capeBase.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.capeBase.addBox(-9.0F, -1.5F, -2.2F, 5, 0, 6, 0.25F);
		this.setRotationAngles(capeBase, 0.0F, 0.0F, -0.08726646259971647F);
		this.cape = new ModelRenderer(this, 0, 458);
		this.cape.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.cape.addBox(4.0F, -1.5F, -2.0F, 5, 0, 6, 0.25F);
		this.setRotationAngles(cape, 0.0F, 0.0F, 0.08726646259971647F);
		this.earRight = new ModelRenderer(this, 25, 451);
		this.earRight.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earRight.addBox(-4.0F, -13.7F, 0.0F, 2, 6, 0, 0.5F);
		this.setRotationAngles(earRight, 0.0F, 0.0F, 0.4553564018453205F);
		this.cape1 = new ModelRenderer(this, 0, 465);
		this.cape1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.cape1.addBox(-4.0F, -0.5F, -2.0F, 8, 0, 6, 0.25F);
		this.cape3 = new ModelRenderer(this, 48, 445);
		this.cape3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.cape3.addBox(-9.0F, -0.5F, -3.0F, 0, 22, 7, 0.25F);
		bipedHeadwear.addChild(earLeft);
		bipedHeadwear.addChild(earRight);
	}

	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.aura.render(0.05F);
		this.capeBase.render(scale);
		this.cape1.render(scale);
		this.cape2.render(scale);
		this.cape3.render(scale);
		this.cape4.render(scale);
		this.cape5.render(scale);
		this.cape6.render(scale);
		bipedHeadwear.render(scale);

		aura.showModel = entity.isSneaking();

		GlStateManager.pushMatrix();
		GlStateManager.translate(this.head.offsetX, this.head.offsetY, this.head.offsetZ);
		GlStateManager.translate(this.head.rotationPointX * scale, this.head.rotationPointY * scale, this.head.rotationPointZ * scale);
		GlStateManager.scale(0.5D, 0.5D, 0.5D);
		GlStateManager.translate(-this.head.offsetX, -this.head.offsetY, -this.head.offsetZ);
		GlStateManager.translate(-this.head.rotationPointX * scale, -this.head.rotationPointY * scale, -this.head.rotationPointZ * scale);
		bipedHead = this.head;
		GlStateManager.popMatrix();

		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, 0.035F);
	}

	public void setRotationAngles(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
