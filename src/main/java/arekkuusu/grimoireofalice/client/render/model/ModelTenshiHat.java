package arekkuusu.grimoireofalice.client.render.model;

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
	private final ModelRenderer hatTop;
	private final ModelRenderer fruitBig;
	private final ModelRenderer fruitSmall;
	private final ModelRenderer leave0;
	private final ModelRenderer leave01;
	private final ModelRenderer leave1;
	private final ModelRenderer leave11;

	public ModelTenshiHat() {
		this.textureWidth = 64;
		this.textureHeight = 16;
		this.leave1 = new ModelRenderer(this, 45, 6);
		this.leave1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leave1.addBox(-2.6F, -7.2F, 8.4F, 0, 2, 4, 0.0F);
		this.setRotateAngle(leave1, 0.3490658503988659F, -0.20943951023931953F, 0.0F);
		this.fruitSmall = new ModelRenderer(this, 0, 6);
		this.fruitSmall.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.fruitSmall.addBox(-6.7F, -10.5F, -2.0F, 2, 2, 2, 0.0F);
		this.leave01 = new ModelRenderer(this, 45, 2);
		this.leave01.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leave01.addBox(5.2F, -10.2F, -0.7F, 0, 2, 4, 0.0F);
		this.setRotateAngle(leave01, 0.20943951023931953F, 0.20943951023931953F, 0.0F);
		this.leave11 = new ModelRenderer(this, 45, 5);
		this.leave11.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leave11.addBox(5.2F, -9.3F, 3.2F, 0, 2, 3, 0.0F);
		this.setRotateAngle(leave11, 0.3490658503988659F, 0.20943951023931953F, 0.0F);
		this.hat = new ModelRenderer(this, 0, 0);
		this.hat.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat.addBox(-7.0F, -8.0F, -7.0F, 14, 0, 14, 0.5F);
		this.hatTop = new ModelRenderer(this, 9, 5);
		this.hatTop.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hatTop.addBox(-4.5F, -10.5F, -4.5F, 9, 2, 9, 0.0F);
		this.leave0 = new ModelRenderer(this, 45, 0);
		this.leave0.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leave0.addBox(-3.8F, -9.4F, 5.4F, 0, 2, 4, 0.0F);
		this.setRotateAngle(leave0, 0.20943951023931953F, -0.20943951023931953F, 0.0F);
		this.fruitBig = new ModelRenderer(this, 0, 0);
		this.fruitBig.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.fruitBig.addBox(-7.0F, -11.3F, -0.1F, 3, 3, 3, 0.0F);
		this.leave0.addChild(this.leave1);
		this.hat.addChild(this.fruitSmall);
		this.hat.addChild(this.leave01);
		this.leave01.addChild(this.leave11);
		this.hat.addChild(this.hatTop);
		this.hat.addChild(this.leave0);
		this.hat.addChild(this.fruitBig);
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
