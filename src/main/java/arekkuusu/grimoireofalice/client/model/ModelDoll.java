package arekkuusu.grimoireofalice.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * ModelDoll - Arekkuusu
 * Created using Tabula 5.1.0
 */

@SideOnly(Side.CLIENT)
public class ModelDoll extends ModelBase {

    private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer ribbon0_bottom;
	private final ModelRenderer ribbon1_top;
	private final ModelRenderer ribbon0_top;
	private final ModelRenderer ribbon1_middle;
	private final ModelRenderer ribbon1_bottom;
	private final ModelRenderer head_top;
	private final ModelRenderer head_bottom;
	private final ModelRenderer neckRight_top;
	private final ModelRenderer neckLeft_top;
	private final ModelRenderer neckRight_bottom;
	private final ModelRenderer neckLeft_bottom;
	private final ModelRenderer feetRight;
	private final ModelRenderer feetLeft;
	private final ModelRenderer bodyRight;
	private final ModelRenderer bodyLeft;
	private final ModelRenderer handRight;
	private final ModelRenderer handLeft;

    public ModelDoll() {
        this.textureWidth = 32;
        this.textureHeight = 48;
        this.neckRight_bottom = new ModelRenderer(this, 16, 3);
        this.neckRight_bottom.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.neckRight_bottom.addBox(-2.5F, 2.0F, -1.0F, 1, 1, 2, 0.0F);
        this.feetRight = new ModelRenderer(this, 12, 6);
        this.feetRight.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.feetRight.addBox(-3.5F, 10.5F, -1.5F, 2, 1, 3, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-2.5F, -1.5F, -1.5F, 5, 3, 3, 0.0F);
        this.neckLeft_bottom = new ModelRenderer(this, 22, 3);
        this.neckLeft_bottom.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.neckLeft_bottom.addBox(1.5F, 2.0F, -1.0F, 1, 1, 2, 0.0F);
        this.bodyLeft = new ModelRenderer(this, 0, 25);
        this.bodyLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bodyLeft.addBox(3.5F, 3.5F, -1.5F, 1, 7, 3, 0.0F);
        this.ribbon0_top = new ModelRenderer(this, 9, 36);
        this.ribbon0_top.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ribbon0_top.addBox(-2.5F, -4.5F, -0.5F, 2, 1, 1, 0.0F);
        this.head_bottom = new ModelRenderer(this, 0, 10);
        this.head_bottom.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head_bottom.addBox(-1.5F, 1.5F, -1.5F, 3, 1, 3, 0.0F);
        this.body = new ModelRenderer(this, 0, 14);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-3.5F, 2.5F, -1.5F, 7, 8, 3, 0.0F);
        this.ribbon0_bottom = new ModelRenderer(this, 0, 36);
        this.ribbon0_bottom.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ribbon0_bottom.addBox(-2.5F, -3.5F, -0.5F, 3, 1, 1, 0.0F);
        this.feetLeft = new ModelRenderer(this, 22, 6);
        this.feetLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.feetLeft.addBox(1.5F, 10.5F, -1.5F, 2, 1, 3, 0.0F);
        this.handLeft = new ModelRenderer(this, 16, 25);
        this.handLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.handLeft.addBox(4.5F, 4.5F, -1.5F, 1, 2, 3, 0.0F);
        this.ribbon1_top = new ModelRenderer(this, 0, 41);
        this.ribbon1_top.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ribbon1_top.addBox(-4.5F, -2.5F, -0.5F, 3, 1, 1, 0.0F);
        this.neckRight_top = new ModelRenderer(this, 16, 0);
        this.neckRight_top.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.neckRight_top.addBox(-2.0F, 1.5F, -1.0F, 1, 1, 2, 0.0F);
        this.ribbon1_middle = new ModelRenderer(this, 9, 41);
        this.ribbon1_middle.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ribbon1_middle.addBox(-4.5F, -1.5F, -0.5F, 2, 1, 1, 0.0F);
        this.handRight = new ModelRenderer(this, 8, 25);
        this.handRight.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.handRight.addBox(-5.5F, 4.5F, -1.5F, 1, 2, 3, 0.0F);
        this.neckLeft_top = new ModelRenderer(this, 22, 0);
        this.neckLeft_top.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.neckLeft_top.addBox(1.0F, 1.5F, -1.0F, 1, 1, 2, 0.0F);
        this.ribbon1_bottom = new ModelRenderer(this, 16, 41);
        this.ribbon1_bottom.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ribbon1_bottom.addBox(-3.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.bodyRight = new ModelRenderer(this, 22, 15);
        this.bodyRight.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bodyRight.addBox(-4.5F, 3.5F, -1.5F, 1, 7, 3, 0.0F);
        this.head_top = new ModelRenderer(this, 0, 6);
        this.head_top.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head_top.addBox(-1.5F, -2.5F, -1.5F, 3, 1, 3, 0.0F);
        this.head_bottom.addChild(this.neckRight_bottom);
        this.body.addChild(this.feetRight);
        this.head_bottom.addChild(this.neckLeft_bottom);
        this.body.addChild(this.bodyLeft);
        this.head.addChild(this.head_bottom);
        this.body.addChild(this.feetLeft);
        this.body.addChild(this.handLeft);
        this.head_bottom.addChild(this.neckRight_top);
        this.body.addChild(this.handRight);
        this.head_bottom.addChild(this.neckLeft_top);
        this.body.addChild(this.bodyRight);
        this.head.addChild(this.head_top);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.head.render(f5);
        this.ribbon0_top.render(f5);
        this.body.render(f5);
        this.ribbon0_bottom.render(f5);
        this.ribbon1_top.render(f5);
        this.ribbon1_middle.render(f5);
        this.ribbon1_bottom.render(f5);
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
