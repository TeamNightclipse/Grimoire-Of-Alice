package arekkuusu.grimoireofalice.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * ModelTenshiHat - Arekkuusu Created using Tabula 5.1.0
 */

@SideOnly(Side.CLIENT)
public class ModelTenshiHat extends ModelBiped {

	private final ModelRenderer hat;
	private final ModelRenderer hat_top;
	private final ModelRenderer fruit_big;
	private final ModelRenderer fruit_small;
	private final ModelRenderer leave_0;
	private final ModelRenderer leave_0_1;
	private final ModelRenderer leave_1;
	private final ModelRenderer leave_1_1;

	public ModelTenshiHat() {
		this.textureWidth = 64;
		this.textureHeight = 16;
		this.leave_1 = new ModelRenderer(this, 45, 6);
		this.leave_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leave_1.addBox(-2.6F, -7.2F, 8.4F, 0, 2, 4, 0.0F);
		this.setRotateAngle(leave_1, 0.3490658503988659F, -0.20943951023931953F, 0.0F);
		this.fruit_small = new ModelRenderer(this, 0, 6);
		this.fruit_small.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.fruit_small.addBox(-6.7F, -10.5F, -2.0F, 2, 2, 2, 0.0F);
		this.leave_0_1 = new ModelRenderer(this, 45, 2);
		this.leave_0_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leave_0_1.addBox(5.2F, -10.2F, -0.7F, 0, 2, 4, 0.0F);
		this.setRotateAngle(leave_0_1, 0.20943951023931953F, 0.20943951023931953F, 0.0F);
		this.leave_1_1 = new ModelRenderer(this, 45, 5);
		this.leave_1_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leave_1_1.addBox(5.2F, -9.3F, 3.2F, 0, 2, 3, 0.0F);
		this.setRotateAngle(leave_1_1, 0.3490658503988659F, 0.20943951023931953F, 0.0F);
		this.hat = new ModelRenderer(this, 0, 0);
		this.hat.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat.addBox(-7.0F, -8.0F, -7.0F, 14, 0, 14, 0.5F);
		this.hat_top = new ModelRenderer(this, 9, 5);
		this.hat_top.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat_top.addBox(-4.5F, -10.5F, -4.5F, 9, 2, 9, 0.0F);
		this.leave_0 = new ModelRenderer(this, 45, 0);
		this.leave_0.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leave_0.addBox(-3.8F, -9.4F, 5.4F, 0, 2, 4, 0.0F);
		this.setRotateAngle(leave_0, 0.20943951023931953F, -0.20943951023931953F, 0.0F);
		this.fruit_big = new ModelRenderer(this, 0, 0);
		this.fruit_big.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.fruit_big.addBox(-7.0F, -11.3F, -0.1F, 3, 3, 3, 0.0F);
		this.leave_0.addChild(this.leave_1);
		this.hat.addChild(this.fruit_small);
		this.hat.addChild(this.leave_0_1);
		this.leave_0_1.addChild(this.leave_1_1);
		this.hat.addChild(this.hat_top);
		this.hat.addChild(this.leave_0);
		this.hat.addChild(this.fruit_big);
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
