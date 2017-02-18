package arekkuusu.grimoireofalice.client.model;

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
	private final ModelRenderer base_trs;
	private final ModelRenderer big;
	private final ModelRenderer longer;
	private final ModelRenderer flat;
	private final ModelRenderer big_trs;
	private final ModelRenderer long_trs;
	private final ModelRenderer flat_trs;

	public ModelFierySword() {
		this.textureWidth = 192;
		this.textureHeight = 128;
		this.big_trs = new ModelRenderer(this, 0, 0);
		this.big_trs.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.big_trs.addBox(-28.5F, -10.5F, 41.5F, 57, 21, 21, 0.0F);
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
		this.base_trs = new ModelRenderer(this, 51, 12);
		this.base_trs.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.base_trs.addBox(-8.5F, -8.5F, 29.5F, 17, 17, 9, 0.0F);
		this.flat_trs = new ModelRenderer(this, 0, 0);
		this.flat_trs.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.flat_trs.addBox(-23.5F, -3.5F, 81.0F, 47, 7, 21, 0.0F);
		this.long_trs = new ModelRenderer(this, 7, 43);
		this.long_trs.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.long_trs.addBox(-8.5F, -8.5F, 65.5F, 17, 17, 67, 0.0F);
		this.base_trs.addChild(this.big_trs);
		this.base.addChild(this.big);
		this.base.addChild(this.longer);
		this.base.addChild(this.flat);
		this.base_trs.addChild(this.flat_trs);
		this.base_trs.addChild(this.long_trs);
    }

	@SideOnly(Side.CLIENT)
	public void renderInsideForm(float f5) {
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1, 1, 1, 1F);
		this.base_trs.render(f5);
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
