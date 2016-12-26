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
public class ModelFierySword extends ModelBase {
    public ModelRenderer bigBox_Base;
	public ModelRenderer bigBox;
	public ModelRenderer smallBox_Base;
    public ModelRenderer smallBox;

    public ModelFierySword() {
        this.textureWidth = 192;
        this.textureHeight = 64;
        this.smallBox = new ModelRenderer(this, 0, 0);
        this.smallBox.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.smallBox.addBox(-28.5F, -10.5F, 41.5F, 57, 21, 21, 0.0F);
        this.bigBox_Base = new ModelRenderer(this, 51, 12);
        this.bigBox_Base.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bigBox_Base.addBox(-10.0F, -10.0F, 28.0F, 20, 20, 12, 0.0F);
        this.bigBox = new ModelRenderer(this, 0, 0);
        this.bigBox.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bigBox.addBox(-30.0F, -12.0F, 40.0F, 60, 24, 24, 0.0F);
        this.smallBox_Base = new ModelRenderer(this, 51, 12);
        this.smallBox_Base.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.smallBox_Base.addBox(-8.5F, -8.5F, 29.5F, 17, 17, 9, 0.0F);
        this.smallBox_Base.addChild(this.smallBox);
        this.bigBox_Base.addChild(this.bigBox);
    }

	@SideOnly(Side.CLIENT)
	public void renderInsideForm(float f5) {
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1, 1, 1, 1F);
		this.smallBox_Base.render(f5);
		GlStateManager.disableBlend();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		GlStateManager.depthMask(false);
		GlStateManager.color(f, f1, f2, 1F);
		this.bigBox_Base.render(f5);
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
