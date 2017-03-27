package arekkuusu.grimoireofalice.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * ModelSatoriEye - Either Mojang or a mod author
 * Created using Tabula 5.1.0
 */

@SideOnly(Side.CLIENT)
public class ModelSatoriEye extends ModelBiped { //TODO: Add a different model

    private final ModelRenderer eye;
	private final ModelRenderer right_0;
	private final ModelRenderer left_0;
	private final ModelRenderer shape31;
	private final ModelRenderer shape31_1;
	private final ModelRenderer shape31_2;
	private final ModelRenderer shape31_3;
	private final ModelRenderer right_1;
	private final ModelRenderer right_2;
	private final ModelRenderer right_3;
	private final ModelRenderer right_4;
	private final ModelRenderer right_5;
	private final ModelRenderer right_6;
	private final ModelRenderer right_5_0;
	private final ModelRenderer right_5_1;
	private final ModelRenderer right_5_2;
	private final ModelRenderer right_7;
	private final ModelRenderer right_8;
	private final ModelRenderer right_9;
	private final ModelRenderer left_1;
	private final ModelRenderer left_2;
	private final ModelRenderer left_3;
	private final ModelRenderer left_4;
	private final ModelRenderer left_5;
	private final ModelRenderer left_6;
	private final ModelRenderer left_7;
	private final ModelRenderer left_8;
	private final ModelRenderer left_9;

    public ModelSatoriEye() {
		this.textureWidth = 64;
		this.textureHeight = 16;
        this.right_5 = new ModelRenderer(this, 0, 14);
        this.right_5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.right_5.addBox(-9.0F, 8.2F, -0.5F, 6, 1, 1, 0.0F);
        this.setRotateAngle(right_5, 0.0F, -1.5009831567151235F, -0.09529497715889039F);
        this.left_0 = new ModelRenderer(this, 17, 0);
        this.left_0.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.left_0.addBox(-6.1F, 5.2F, -6.5F, 10, 1, 1, 0.0F);
        this.setRotateAngle(left_0, 0.0F, 0.18203784098300857F, 0.22759093446006054F);
        this.right_5_1 = new ModelRenderer(this, 1, 12);
        this.right_5_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.right_5_1.addBox(-10.0F, 8.2F, -3.1F, 1, 1, 3, 0.0F);
        this.setRotateAngle(right_5_1, 0.07330382858376185F, 0.0F, 0.0F);
        this.right_4 = new ModelRenderer(this, 0, 12);
        this.right_4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.right_4.addBox(-2.7F, 8.2F, -6.7F, 6, 1, 1, 0.0F);
        this.setRotateAngle(right_4, 0.0F, -0.45378560551852565F, -0.09529497715889039F);
        this.left_2 = new ModelRenderer(this, 0, 8);
        this.left_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.left_2.addBox(-4.2F, 9.5F, -6.9F, 6, 1, 1, 0.0F);
        this.setRotateAngle(left_2, 0.0F, 0.5462880558742251F, 0.5009094953223726F);
        this.left_7 = new ModelRenderer(this, 0, 10);
        this.left_7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.left_7.addBox(3.3F, 9.0F, -1.9F, 4, 1, 1, 0.0F);
        this.setRotateAngle(left_7, 0.0F, 0.31869712141416456F, 0.40980330836826856F);
        this.left_9 = new ModelRenderer(this, 19, 11);
        this.left_9.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.left_9.addBox(-7.5F, 6.6F, 0.3F, 11, 1, 1, 0.0F);
        this.setRotateAngle(left_9, 0.0F, 0.0F, 0.045553093477052F);
        this.shape31 = new ModelRenderer(this, 19, 0);
        this.shape31.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape31.addBox(-5.5F, 8.9F, -3.0F, 9, 1, 1, 0.0F);
        this.setRotateAngle(shape31, 0.0F, 0.0F, -0.05235987755982988F);
        this.shape31_3 = new ModelRenderer(this, 1, 6);
        this.shape31_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape31_3.addBox(-5.0F, 9.3F, -1.7F, 1, 1, 5, 0.0F);
        this.setRotateAngle(shape31_3, -0.03490658503988659F, 0.0F, 0.0F);
        this.shape31_1 = new ModelRenderer(this, 1, 6);
        this.shape31_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape31_1.addBox(-8.3F, 1.6F, 6.4F, 5, 1, 1, 0.0F);
        this.setRotateAngle(shape31_1, -0.8203047484373349F, -0.0767944870877505F, -0.4991641660703782F);
        this.left_3 = new ModelRenderer(this, 14, 8);
        this.left_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.left_3.addBox(-5.2F, 10.3F, -6.9F, 6, 1, 1, 0.0F);
        this.setRotateAngle(left_3, 0.0F, 0.22759093446006054F, 0.31869712141416456F);
        this.eye = new ModelRenderer(this, 0, 0);
        this.eye.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.eye.addBox(1.2F, 4.5F, -8.0F, 3, 3, 3, 0.0F);
        this.right_3 = new ModelRenderer(this, 24, 14);
        this.right_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.right_3.addBox(-1.7F, 8.1F, -7.3F, 3, 1, 1, 0.0F);
        this.setRotateAngle(right_3, 0.0F, -0.136659280431156F, -0.31869712141416456F);
        this.right_5_2 = new ModelRenderer(this, 0, 14);
        this.right_5_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.right_5_2.addBox(-9.0F, 8.4F, -2.9F, 1, 1, 1, 0.0F);
        this.left_8 = new ModelRenderer(this, 10, 10);
        this.left_8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.left_8.addBox(3.1F, 6.8F, 0.3F, 4, 1, 1, 0.0F);
        this.setRotateAngle(left_8, 0.0F, 0.31869712141416456F, 0.40980330836826856F);
        this.right_2 = new ModelRenderer(this, 2, 7);
        this.right_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.right_2.addBox(-1.0F, 7.9F, -7.5F, 3, 1, 1, 0.0F);
        this.setRotateAngle(right_2, 0.0F, -0.136659280431156F, -0.31869712141416456F);
        this.right_7 = new ModelRenderer(this, 9, 0);
        this.right_7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.right_7.addBox(-4.3F, 6.5F, 0.2F, 3, 1, 1, 0.0F);
        this.setRotateAngle(right_7, 0.0F, -0.06981317007977318F, -0.296705972839036F);
        this.right_0 = new ModelRenderer(this, 12, 4);
        this.right_0.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.right_0.addBox(1.1F, 6.5F, -7.3F, 3, 1, 1, 0.0F);
        this.setRotateAngle(right_0, 0.0F, -0.136659280431156F, -0.31869712141416456F);
        this.right_8 = new ModelRenderer(this, 0, 6);
        this.right_8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.right_8.addBox(-3.4F, 5.7F, 0.6F, 3, 1, 1, 0.0F);
        this.setRotateAngle(right_8, 0.0F, -0.10471975511965977F, -0.3141592653589793F);
        this.left_4 = new ModelRenderer(this, 28, 8);
        this.left_4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.left_4.addBox(-3.3F, 11.4F, -6.7F, 5, 1, 1, 0.0F);
        this.setRotateAngle(left_4, 0.0F, 0.31869712141416456F, 0.40980330836826856F);
        this.left_6 = new ModelRenderer(this, 49, 9);
        this.left_6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.left_6.addBox(2.1F, 10.8F, -4.1F, 4, 1, 1, 0.0F);
        this.setRotateAngle(left_6, 0.0F, 0.31869712141416456F, 0.40980330836826856F);
        this.right_1 = new ModelRenderer(this, 0, 8);
        this.right_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.right_1.addBox(-0.3F, 7.5F, -7.5F, 3, 1, 1, 0.0F);
        this.setRotateAngle(right_1, 0.0F, -0.136659280431156F, -0.4363323129985824F);
        this.right_6 = new ModelRenderer(this, 15, 14);
        this.right_6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.right_6.addBox(-4.9F, 7.4F, -0.1F, 3, 1, 1, 0.0F);
        this.setRotateAngle(right_6, 0.0F, -0.06981317007977318F, -0.20943951023931953F);
        this.right_9 = new ModelRenderer(this, 12, 2);
        this.right_9.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.right_9.addBox(-0.3F, 5.7F, 0.6F, 5, 1, 1, 0.0F);
        this.setRotateAngle(right_9, 0.0F, -0.1954768762233649F, 0.0F);
        this.shape31_2 = new ModelRenderer(this, 8, 0);
        this.shape31_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape31_2.addBox(4.0F, 8.5F, -3.5F, 1, 1, 5, 0.0F);
        this.setRotateAngle(shape31_2, 0.06981317007977318F, 0.0F, 0.0F);
        this.left_1 = new ModelRenderer(this, 42, 7);
        this.left_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.left_1.addBox(-6.8F, 7.4F, -6.8F, 6, 1, 1, 0.0F);
        this.setRotateAngle(left_1, 0.0F, 0.18203784098300857F, 0.5462880558742251F);
        this.left_5 = new ModelRenderer(this, 39, 9);
        this.left_5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.left_5.addBox(-0.3F, 11.7F, -5.8F, 4, 1, 1, 0.0F);
        this.setRotateAngle(left_5, 0.0F, 0.31869712141416456F, 0.40980330836826856F);
        this.right_5_0 = new ModelRenderer(this, 0, 14);
        this.right_5_0.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.right_5_0.addBox(-8.5F, 8.5F, -3.9F, 3, 1, 1, 0.0F);
        this.right_4.addChild(this.right_5);
        this.eye.addChild(this.left_0);
        this.right_5.addChild(this.right_5_1);
        this.right_3.addChild(this.right_4);
        this.left_1.addChild(this.left_2);
        this.left_6.addChild(this.left_7);
        this.left_8.addChild(this.left_9);
        this.eye.addChild(this.shape31);
        this.eye.addChild(this.shape31_3);
        this.eye.addChild(this.shape31_1);
        this.left_2.addChild(this.left_3);
        this.right_2.addChild(this.right_3);
        this.right_5.addChild(this.right_5_2);
        this.left_7.addChild(this.left_8);
        this.right_1.addChild(this.right_2);
        this.right_6.addChild(this.right_7);
        this.eye.addChild(this.right_0);
        this.right_7.addChild(this.right_8);
        this.left_3.addChild(this.left_4);
        this.left_5.addChild(this.left_6);
        this.right_0.addChild(this.right_1);
        this.right_5.addChild(this.right_6);
        this.right_8.addChild(this.right_9);
        this.eye.addChild(this.shape31_2);
        this.left_0.addChild(this.left_1);
        this.left_4.addChild(this.left_5);
        this.right_5.addChild(this.right_5_0);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float age, float netHeadYaw, float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, age, netHeadYaw, headPitch, scale, entity);

		GlStateManager.pushMatrix();
		scale *= 1.5;
		float maxUpAndDown = 0.05F;
		float speed = 2;
		float angle = 0;

		float toDegrees = (float) Math.PI / 180F;
		angle += speed * age;
		if (angle > 360) angle -= 360;

		GlStateManager.translate(0, maxUpAndDown * Math.sin(angle * toDegrees), 0);
		GlStateManager.scale(0.8F, 0.8F, 0.8F);

		if (this.isChild) {
			GlStateManager.scale(0.75F, 0.75F, 0.75F);
			GlStateManager.translate(0.0F, 16.0F * scale, 0.0F);
			GlStateManager.popMatrix();

			GlStateManager.pushMatrix();
			GlStateManager.scale(0.5F, 0.5F, 0.5F);

			GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
			this.eye.render(scale);
		}
		else {
			if (entity.isSneaking()) {
				GlStateManager.translate(0.0F, 0.2F, 0.0F);
			}

			this.eye.render(scale);
		}

		GlStateManager.popMatrix();
	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		this.eye.rotateAngleY = 0.0F;

		if (this.swingProgress > 0.0F) {
			EnumHandSide enumhandside = this.getMainHand(entityIn);
			float f1 = this.swingProgress;
			this.eye.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float) Math.PI * 2F)) * 0.2F;

			if (enumhandside == EnumHandSide.LEFT) {
				this.eye.rotateAngleY *= -1.0F;
			}
		}

		if (this.isSneak) {
			this.eye.rotateAngleX = 0.5F;
		}
		else {
			this.eye.rotateAngleX = 0.0F;
		}
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
