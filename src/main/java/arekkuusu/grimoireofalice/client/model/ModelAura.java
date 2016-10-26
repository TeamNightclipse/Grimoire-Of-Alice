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
import net.minecraft.inventory.EntityEquipmentSlot;

public class ModelAura extends ModelBiped {

	//fields
	private final ModelRenderer aura;
	private final ModelRenderer chest;
	private final ModelRenderer ribbon;
	private final ModelRenderer ribbon1;
	private final ModelRenderer ribbon2;
	private final ModelRenderer ribbon3;
	private final ModelRenderer ribbon4;
	private final ModelRenderer ribbon5;
	private final ModelRenderer ribbonBase;

	private final EntityEquipmentSlot slot;

	public ModelAura(EntityEquipmentSlot slot) {
		this.slot = slot;

		textureWidth = 1024;
		textureHeight = 512;

		aura = new ModelRenderer(this, 1024, 512);
		aura.setRotationPoint(0F, -16F, 10F);
		aura.addBox(-254F, -200F, 0F, 511, 432, 1);
		chest = new ModelRenderer(this, 16, 445);
		chest.setRotationPoint(0.0F, 0.0F, 0.0F);
		chest.addBox(-16.0F, 0.0F, -8.0F, 32, 48, 16, 0.25F);
		this.ribbon2 = new ModelRenderer(this, 30, 498);
		this.ribbon2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ribbon2.addBox(-5.3F, -11.5F, 0.0F, 5, 3, 0, 0.0F);
		this.setRotationAngles(ribbon2, 0.0F, 0.08552113334772216F, 0.022689280275926284F);
		this.ribbon1 = new ModelRenderer(this, 15, 498);
		this.ribbon1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ribbon1.addBox(-7.5F, -11.5F, 0.0F, 6, 3, 0, 0.0F);
		this.setRotationAngles(ribbon1, 0.0F, 0.0F, 0.1308996938995747F);
		this.ribbon = new ModelRenderer(this, 0, 498);
		this.ribbon.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ribbon.addBox(1.5F, -11.5F, 0.0F, 6, 3, 0, 0.0F);
		this.setRotationAngles(ribbon, 0.0F, 0.0F, -0.1308996938995747F);
		this.ribbon4 = new ModelRenderer(this, 60, 498);
		this.ribbon4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ribbon4.addBox(-9.9F, -11.2F, 0.0F, 6, 3, 0, 0.0F);
		this.setRotationAngles(ribbon4, 0.0F, 0.0715584993317675F, 0.3490658503988659F);
		this.ribbonBase = new ModelRenderer(this, 90, 498);
		this.ribbonBase.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ribbonBase.addBox(-1.0F, -12.2F, 0.0F, 2, 4, 0, 0.0F);
		this.ribbon5 = new ModelRenderer(this, 75, 498);
		this.ribbon5.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ribbon5.addBox(4.1F, -11.2F, 0.0F, 6, 3, 0, 0.0F);
		this.setRotationAngles(ribbon5, 0.0F, -0.0715584993317675F, -0.3490658503988659F);
		this.ribbon3 = new ModelRenderer(this, 45, 498);
		this.ribbon3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ribbon3.addBox(0.5F, -11.4F, 0.0F, 5, 3, 0, 0.0F);
		this.setRotationAngles(ribbon3, 0.0F, 0.0890117918517108F, -0.04537856055185257F);
		ribbonBase.addChild(ribbon);
		ribbonBase.addChild(ribbon1);
		ribbonBase.addChild(ribbon2);
		ribbonBase.addChild(ribbon3);
		ribbonBase.addChild(ribbon4);
		ribbonBase.addChild(ribbon5);
	}

	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		bipedBody = this.chest;
		bipedHead = this.ribbonBase;

		bipedHeadwear.showModel = false;
		bipedHead.showModel = slot == EntityEquipmentSlot.HEAD;
		bipedBody.showModel = slot == EntityEquipmentSlot.CHEST;
		aura.showModel = slot != EntityEquipmentSlot.HEAD || entity.isSneaking();
		float size = bipedBody.showModel ? 0.025F : scale;

		this.aura.render(0.05F);

		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, size);
	}

	public void setRotationAngles(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
