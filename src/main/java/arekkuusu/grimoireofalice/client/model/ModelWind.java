package arekkuusu.grimoireofalice.client.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * ModelWind - Arekkuusu
 * Created using Tabula 5.1.0
 */
public class ModelWind extends ModelBase {

	//TODO: Name these
	private final ModelRenderer shape1;
	private final ModelRenderer shape1_1;
	private final ModelRenderer shape1_2;
	private final ModelRenderer shape1_3;

	public ModelWind() {
		this.textureWidth = 128;
		this.textureHeight = 128;
		this.shape1 = new ModelRenderer(this, 0, 0);
		this.shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1.addBox(-25.0F, -10.0F, 0.0F, 50, 20, 0, 0.0F);
		this.shape1_2 = new ModelRenderer(this, 0, 46);
		this.shape1_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_2.addBox(-25.0F, -10.0F, 0.0F, 50, 20, 0, 0.0F);
		this.setRotateAngle(shape1_2, 0.0F, -1.5707963267948966F, 0.0F);
		this.shape1_3 = new ModelRenderer(this, 0, 69);
		this.shape1_3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_3.addBox(-25.0F, -10.0F, 0.0F, 50, 20, 0, 0.0F);
		this.setRotateAngle(shape1_3, 0.0F, -2.356194490192345F, 0.0F);
		this.shape1_1 = new ModelRenderer(this, 0, 23);
		this.shape1_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_1.addBox(-25.0F, -10.0F, 0.0F, 50, 20, 0, 0.0F);
		this.setRotateAngle(shape1_1, 0.0F, -0.7853981633974483F, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 0.8F);

		this.shape1.render(f5);
		this.shape1_2.render(f5);
		this.shape1_3.render(f5);
		this.shape1_1.render(f5);

		GlStateManager.disableBlend();
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
