package arekkuusu.grimoireofalice.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

/**
 * AuraIchirin - Arekkuusu
 * Created using Tabula 5.1.0
 */
public class ModelAuraIchirin extends ModelBiped {
    public ModelRenderer shape1;
    public ModelRenderer shape1_1;
    public ModelRenderer shape1_2;
    public ModelRenderer shape1_3;
    public ModelRenderer shape1_4;
    public ModelRenderer shape1_5;
    public ModelRenderer shape1_6;
    public ModelRenderer shape1_7;
    public ModelRenderer shape1_8;
    public ModelRenderer shape1_9;
    public ModelRenderer shape1_10;

    private float size;

    public ModelAuraIchirin(float size) {
        this.size = size;
        this.textureWidth = 256;
        this.textureHeight = 384;
        this.shape1_5 = new ModelRenderer(this, 181, 25);
        this.shape1_5.setRotationPoint(0.0F, -30.0F, 7.0F);
        this.shape1_5.addBox(23.0F, -33.0F, -3.0F, 16, 16, 16, 0.0F);
        this.shape1_7 = new ModelRenderer(this, 222, 0);
        this.shape1_7.setRotationPoint(0.0F, -30.0F, 7.0F);
        this.shape1_7.addBox(40.0F, -25.0F, -14.0F, 8, 8, 8, 0.0F);
        this.shape1_3 = new ModelRenderer(this, 181, 4);
        this.shape1_3.setRotationPoint(0.0F, -30.0F, 7.0F);
        this.shape1_3.addBox(-60.0F, -5.0F, 22.0F, 10, 10, 10, 0.0F);
        this.shape1_4 = new ModelRenderer(this, 0, 200);
        this.shape1_4.setRotationPoint(0.0F, -35.0F, 7.0F);
        this.shape1_4.addBox(-35.0F, -29.0F, 33.0F, 30, 30, 30, 0.0F);
        this.shape1_8 = new ModelRenderer(this, 130, 200);
        this.shape1_8.setRotationPoint(0.0F, -35.0F, 7.0F);
        this.shape1_8.addBox(15.0F, -35.0F, 20.0F, 30, 30, 30, 0.0F);
        this.shape1_9 = new ModelRenderer(this, 0, 265);
        this.shape1_9.setRotationPoint(0.0F, -35.0F, 7.0F);
        this.shape1_9.addBox(15.0F, 20.0F, 40.0F, 30, 30, 30, 0.0F);
        this.shape1_10 = new ModelRenderer(this, 0, 330);
        this.shape1_10.setRotationPoint(0.0F, -35.0F, -15.0F);
        this.shape1_10.addBox(15.0F, 20.0F, 40.0F, 25, 25, 25, 0.0F);
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(0.0F, -30.0F, 7.0F);
        this.shape1.addBox(-30.0F, -30.0F, 0.0F, 60, 60, 60, 0.0F);
        this.shape1_1 = new ModelRenderer(this, 0, 122);
        this.shape1_1.setRotationPoint(0.0F, -30.0F, 7.0F);
        this.shape1_1.addBox(-49.0F, 9.1F, 15.0F, 38, 38, 38, 0.0F);
        this.shape1_2 = new ModelRenderer(this, 125, 270);
        this.shape1_2.setRotationPoint(0.0F, -30.0F, 7.0F);
        this.shape1_2.addBox(-60.0F, 0.0F, 25.0F, 20, 20, 20, 0.0F);
        this.shape1_6 = new ModelRenderer(this, 204, 130);
        this.shape1_6.setRotationPoint(0.0F, -30.0F, 7.0F);
        this.shape1_6.addBox(33.0F, -36.0F, -9.0F, 12, 12, 12, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.8F);
        this.shape1_5.render(size);
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.8F);
        this.shape1_7.render(size);
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.8F);
        this.shape1_3.render(size);
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.8F);
        this.shape1_4.render(size);
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.9F);
        this.shape1_8.render(size);
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.8F);
        this.shape1_9.render(size);
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.8F);
        this.shape1_10.render(size);
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.95F);
        this.shape1.render(size);
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.9F);
        this.shape1_1.render(size);
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.8F);
        this.shape1_2.render(size);
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.8F);
        this.shape1_6.render(size);
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
