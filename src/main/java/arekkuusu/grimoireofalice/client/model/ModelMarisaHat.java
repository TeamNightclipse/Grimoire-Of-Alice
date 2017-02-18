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
public class ModelMarisaHat extends ModelBiped {

	private final ModelRenderer hat;
	private final ModelRenderer ribbon_base;
	private final ModelRenderer hat_0;
	private final ModelRenderer hat_1;
	private final ModelRenderer hat_2;
	private final ModelRenderer hat_top;
	private final ModelRenderer hat_west;
	private final ModelRenderer hat_east;
	private final ModelRenderer hat_north;
	private final ModelRenderer hat_south;
	private final ModelRenderer ribbon_golden;
	private final ModelRenderer ribbon_white_right;
	private final ModelRenderer ribbon_white_left;

	public ModelMarisaHat() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.hat_0 = new ModelRenderer(this, 0, 42);
		this.hat_0.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat_0.addBox(-4.0F, -12.0F, -4.0F, 8, 3, 8, 0.0F);
		this.setRotateAngle(hat_0, -0.091106186954104F, 0.0F, 0.0F);
		this.hat_west = new ModelRenderer(this, 34, 33);
		this.hat_west.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat_west.addBox(6.5F, -8.0F, -7.0F, 1, 1, 14, 0.0F);
		this.hat = new ModelRenderer(this, 0, 16);
		this.hat.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat.addBox(-7.0F, -9.0F, -7.0F, 14, 1, 14, 0.0F);
		this.hat_east = new ModelRenderer(this, 34, 0);
		this.hat_east.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat_east.addBox(-7.5F, -8.0F, -7.0F, 1, 1, 14, 0.0F);
		this.hat_top = new ModelRenderer(this, 0, 61);
		this.hat_top.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat_top.addBox(-0.5F, -15.9F, -1.3F, 2, 1, 2, 0.0F);
		this.setRotateAngle(hat_top, -0.22759093446006054F, -0.12217304763960307F, 0.0F);
		this.hat_south = new ModelRenderer(this, 34, 52);
		this.hat_south.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat_south.addBox(-7.0F, -8.0F, 6.5F, 14, 1, 1, 0.0F);
		this.ribbon_golden = new ModelRenderer(this, 0, 0);
		this.ribbon_golden.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ribbon_golden.addBox(-1.0F, -10.0F, -5.5F, 2, 1, 1, 0.0F);
		this.ribbon_base = new ModelRenderer(this, 0, 31);
		this.ribbon_base.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ribbon_base.addBox(-5.0F, -10.0F, -4.5F, 10, 1, 10, 0.0F);
		this.setRotateAngle(ribbon_base, 0.0F, 0.05759586531581287F, 0.0F);
		this.hat_1 = new ModelRenderer(this, 0, 52);
		this.hat_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat_1.addBox(-3.0F, -13.8F, -3.0F, 6, 2, 6, 0.0F);
		this.setRotateAngle(hat_1, -0.136659280431156F, 0.06981317007977318F, 0.0F);
		this.ribbon_white_right = new ModelRenderer(this, 0, 4);
		this.ribbon_white_right.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ribbon_white_right.addBox(-5.3F, -12.3F, -5.4F, 4, 3, 1, 0.0F);
		this.setRotateAngle(ribbon_white_right, 0.0F, 0.0F, 0.091106186954104F);
		this.hat_2 = new ModelRenderer(this, 21, 58);
		this.hat_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat_2.addBox(-1.5F, -15.0F, -2.4F, 4, 2, 4, 0.0F);
		this.setRotateAngle(hat_2, -0.18203784098300857F, -0.08552113334772216F, 0.0F);
		this.ribbon_white_left = new ModelRenderer(this, 0, 4);
		this.ribbon_white_left.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ribbon_white_left.addBox(1.3F, -12.3F, -5.4F, 4, 3, 1, 0.0F);
		this.setRotateAngle(ribbon_white_left, 0.0F, 0.0F, -0.091106186954104F);
		this.hat_north = new ModelRenderer(this, 34, 49);
		this.hat_north.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat_north.addBox(-7.0F, -8.0F, -7.5F, 14, 1, 1, 0.0F);
		this.hat.addChild(this.hat_0);
		this.hat.addChild(this.hat_west);
		this.hat.addChild(this.hat_east);
		this.hat.addChild(this.hat_top);
		this.hat.addChild(this.hat_south);
		this.hat.addChild(this.ribbon_golden);
		this.hat.addChild(this.ribbon_base);
		this.hat.addChild(this.hat_1);
		this.hat.addChild(this.ribbon_white_right);
		this.hat.addChild(this.hat_2);
		this.hat.addChild(this.ribbon_white_left);
		this.hat.addChild(this.hat_north);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		bipedHead = hat;
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale * 1.25F, entity);
		GlStateManager.pushMatrix();

		if (this.isChild) {
			GlStateManager.scale(0.75F, 0.75F, 0.75F);
			GlStateManager.translate(0.0F, 16.0F * scale, 0.0F);
			this.bipedHead.render(scale * 1.25F);
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
			GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
		}
		else {
			GlStateManager.translate(0, 0.25, 0);
			if (entity.isSneaking()) {
				GlStateManager.translate(0.0F, 0.25F, 0.0F);
			}
			this.bipedHead.render(scale * 1.25F);
		}

		GlStateManager.popMatrix();
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
