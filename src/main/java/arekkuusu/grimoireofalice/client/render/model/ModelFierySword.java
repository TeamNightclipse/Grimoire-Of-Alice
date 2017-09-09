package arekkuusu.grimoireofalice.client.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

/**
 * ModelFierySword - Arekkuusu
 * Created using Tabula 5.1.0
 */

@SideOnly(Side.CLIENT)
public class ModelFierySword extends ModelBase {

	private final ModelRenderer base;
	private final ModelRenderer baseTrs;
	private final ModelRenderer big;
	private final ModelRenderer longer;
	private final ModelRenderer flat;
	private final ModelRenderer bigTrs;
	private final ModelRenderer longTrs;
	private final ModelRenderer flatTrs;

	public ModelFierySword() {
		this.textureWidth = 192;
		this.textureHeight = 128;
		this.bigTrs = new ModelRenderer(this, 0, 0);
		this.bigTrs.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bigTrs.addBox(-28.5F, -10.5F, 41.5F, 57, 21, 21, 0.0F);
		this.big = new ModelRenderer(this, 0, 0);
		this.big.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.big.addBox(-30.0F, -12.0F, 40.0F, 60, 24, 24, 0.0F);
		this.base = new ModelRenderer(this, 51, 12);
		this.base.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.base.addBox(-10.0F, -10.0F, 28.0F, 20, 20, 12, 0.0F);
		this.longer = new ModelRenderer(this, 0, 38);
		this.longer.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.longer.addBox(-10.0F, -10.0F, 64.0F, 20, 20, 70, 0.0F);
		this.flat = new ModelRenderer(this, 0, 0);
		this.flat.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.flat.addBox(-25.0F, -5.0F, 80.0F, 50, 10, 24, 0.0F);
		this.baseTrs = new ModelRenderer(this, 51, 12);
		this.baseTrs.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.baseTrs.addBox(-8.5F, -8.5F, 29.5F, 17, 17, 9, 0.0F);
		this.flatTrs = new ModelRenderer(this, 0, 0);
		this.flatTrs.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.flatTrs.addBox(-23.5F, -3.5F, 81.0F, 47, 7, 21, 0.0F);
		this.longTrs = new ModelRenderer(this, 7, 43);
		this.longTrs.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.longTrs.addBox(-8.5F, -8.5F, 65.5F, 17, 17, 67, 0.0F);
		this.baseTrs.addChild(this.bigTrs);
		this.base.addChild(this.big);
		this.base.addChild(this.longer);
		this.base.addChild(this.flat);
		this.baseTrs.addChild(this.flatTrs);
		this.baseTrs.addChild(this.longTrs);
	}

	@SideOnly(Side.CLIENT)
	public void renderInsideForm(float f5) {
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1, 1, 1, 1F);
		this.baseTrs.render(f5);
		GlStateManager.disableBlend();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		GlStateManager.depthMask(false);
		GlStateManager.color(f, f1, f2, 1F);
		this.base.render(f5);
		GlStateManager.depthMask(true);
		GlStateManager.disableBlend();
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
