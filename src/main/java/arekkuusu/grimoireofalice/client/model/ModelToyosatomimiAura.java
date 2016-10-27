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
import net.minecraft.inventory.EntityEquipmentSlot;

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

	private final EntityEquipmentSlot slot;

	public ModelToyosatomimiAura(EntityEquipmentSlot slot) {
		this.slot = slot;

		textureWidth = 1024;
		textureHeight = 512;

		aura = new ModelRenderer(this, 1024, 512);
		aura.setRotationPoint(0F, -16F, 10F);
		aura.addBox(-254F, -248F, 0F, 511, 404, 1);
		cape4 = new ModelRenderer(this, 33, 445);
		cape4.setRotationPoint(0.0F, 0.0F, 0.0F);
		cape4.addBox(9.0F, -0.5F, -3.0F, 0, 22, 7, 0.25F);
		cape5 = new ModelRenderer(this, 0, 472);
		cape5.setRotationPoint(0.0F, 0.0F, 0.0F);
		cape5.addBox(-11.0F, 0.8F, -3.0F, 7, 0, 6, 0.25F);
		this.setRotationAngles(cape5, 0.0F, 0.0F, 0.4363323129985824F);
		head = new ModelRenderer(this, 0, 418);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addBox(-8.0F, -16.0F, -8.0F, 16, 16, 16, 0.5F);
		earLeft = new ModelRenderer(this, 20, 451);
		earLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
		earLeft.addBox(1.7F, -13.7F, 0.0F, 2, 6, 0, 0.5F);
		this.setRotationAngles(earLeft, 0.0F, 0.0F, -0.4553564018453205F);
		cape6 = new ModelRenderer(this, 0, 479);
		cape6.setRotationPoint(0.0F, 0.0F, 0.0F);
		cape6.addBox(4.0F, 0.8F, -3.0F, 7, 0, 6, 0.25F);
		this.setRotationAngles(cape6, 0.0F, 0.0F, -0.4363323129985824F);
		cape2 = new ModelRenderer(this, 27, 475);
		cape2.setRotationPoint(0.0F, 0.0F, 0.0F);
		cape2.addBox(-9.0F, -0.5F, 4.0F, 18, 22, 0, 0.25F);
		capeBase = new ModelRenderer(this, 0, 451);
		capeBase.setRotationPoint(0.0F, 0.0F, 0.0F);
		capeBase.addBox(-9.0F, -1.5F, -2.2F, 5, 0, 6, 0.25F);
		this.setRotationAngles(capeBase, 0.0F, 0.0F, -0.08726646259971647F);
		cape = new ModelRenderer(this, 0, 458);
		cape.setRotationPoint(0.0F, 0.0F, 0.0F);
		cape.addBox(4.0F, -1.5F, -2.0F, 5, 0, 6, 0.25F);
		this.setRotationAngles(cape, 0.0F, 0.0F, 0.08726646259971647F);
		earRight = new ModelRenderer(this, 25, 451);
		earRight.setRotationPoint(0.0F, 0.0F, 0.0F);
		earRight.addBox(-4.0F, -13.7F, 0.0F, 2, 6, 0, 0.5F);
		this.setRotationAngles(earRight, 0.0F, 0.0F, 0.4553564018453205F);
		cape1 = new ModelRenderer(this, 0, 465);
		cape1.setRotationPoint(0.0F, 0.0F, 0.0F);
		cape1.addBox(-4.0F, -0.5F, -2.0F, 8, 0, 6, 0.25F);
		cape3 = new ModelRenderer(this, 48, 445);
		cape3.setRotationPoint(0.0F, 0.0F, 0.0F);
		cape3.addBox(-9.0F, -0.5F, -3.0F, 0, 22, 7, 0.25F);
		bipedHeadwear.addChild(earLeft);
		bipedHeadwear.addChild(earRight);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		bipedHeadwear.render(scale);

		aura.showModel = slot == EntityEquipmentSlot.HEAD && entity.isSneaking();

		boolean renderCape = slot == EntityEquipmentSlot.CHEST;
		capeBase.showModel = renderCape;
		cape1.showModel = renderCape;
		cape2.showModel = renderCape;
		cape3.showModel = renderCape;
		cape4.showModel = renderCape;
		cape5.showModel = renderCape;
		cape6.showModel = renderCape;

		bipedHeadwear.showModel = slot == EntityEquipmentSlot.HEAD;
		bipedHead.showModel = slot == EntityEquipmentSlot.HEAD;

		aura.render(0.05F);
		capeBase.render(scale);
		cape1.render(scale);
		cape2.render(scale);
		cape3.render(scale);
		cape4.render(scale);
		cape5.render(scale);
		cape6.render(scale);

		GlStateManager.pushMatrix();
		GlStateManager.translate(head.offsetX, head.offsetY, head.offsetZ);
		GlStateManager.translate(head.rotationPointX * scale, head.rotationPointY * scale, head.rotationPointZ * scale);
		GlStateManager.scale(0.5D, 0.5D, 0.5D);
		GlStateManager.translate(-head.offsetX, -head.offsetY, -head.offsetZ);
		GlStateManager.translate(-head.rotationPointX * scale, -head.rotationPointY * scale, -head.rotationPointZ * scale);
		bipedHead = head;
		GlStateManager.popMatrix();

		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, 0.035F);
	}

	public void setRotationAngles(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
