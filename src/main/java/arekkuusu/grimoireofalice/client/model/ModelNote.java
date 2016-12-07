package arekkuusu.grimoireofalice.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

/**
 * ModelNote - Arekkuusu
 * Created using Tabula 5.1.0
 */
public class ModelNote extends ModelBase {

	private final ModelRenderer noteSmall;
	private final ModelRenderer noteBig;
	private final ModelRenderer noteSmallSquare;
	private final ModelRenderer noteSmallDot;
	private final ModelRenderer noteBig0;
	private final ModelRenderer noteBig1;
	private final ModelRenderer noteBig2;
	private final ModelRenderer noteBig3;

    public ModelNote() {
        this.textureWidth = 32;
        this.textureHeight = 16;
        this.noteSmall = new ModelRenderer(this, 0, 0);
        this.noteSmall.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.noteSmall.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 1, 0.0F);
        this.noteBig0 = new ModelRenderer(this, 11, 5);
        this.noteBig0.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.noteBig0.addBox(-2.0F, -6.0F, 0.0F, 1, 4, 1, 0.0F);
        this.noteSmallDot = new ModelRenderer(this, 4, 5);
        this.noteSmallDot.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.noteSmallDot.addBox(0.0F, -7.0F, 0.0F, 1, 1, 1, 0.0F);
        this.noteSmallSquare = new ModelRenderer(this, 0, 5);
        this.noteSmallSquare.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.noteSmallSquare.addBox(1.0F, -6.0F, 0.0F, 1, 4, 1, 0.0F);
        this.noteBig1 = new ModelRenderer(this, 11, 5);
        this.noteBig1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.noteBig1.addBox(3.0F, -6.0F, 0.0F, 1, 4, 1, 0.0F);
        this.noteBig2 = new ModelRenderer(this, 11, 0);
        this.noteBig2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.noteBig2.addBox(-5.0F, -2.0F, 0.0F, 4, 4, 1, 0.0F);
        this.noteBig3 = new ModelRenderer(this, 11, 0);
        this.noteBig3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.noteBig3.addBox(0.0F, -2.0F, 0.0F, 4, 4, 1, 0.0F);
        this.noteBig = new ModelRenderer(this, 11, 5);
        this.noteBig.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.noteBig.addBox(-2.0F, -6.0F, 0.0F, 5, 1, 1, 0.0F);
        this.noteBig.addChild(this.noteBig0);
        this.noteSmall.addChild(this.noteSmallDot);
        this.noteSmall.addChild(this.noteSmallSquare);
        this.noteBig.addChild(this.noteBig1);
        this.noteBig0.addChild(this.noteBig2);
        this.noteBig1.addChild(this.noteBig3);
    }

	@SideOnly(Side.CLIENT)
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(f, f1, f2, 1F);
		this.noteSmall.render(f5);
		GlStateManager.disableBlend();
	}

    /**
     * This is a helper function from Tabula to set the rotation of small parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
