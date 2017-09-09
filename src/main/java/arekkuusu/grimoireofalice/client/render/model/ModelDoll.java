package arekkuusu.grimoireofalice.client.render.model;

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
	private final ModelRenderer ribbon0Bottom;
	private final ModelRenderer ribbon1Top;
	private final ModelRenderer ribbon0Top;
	private final ModelRenderer ribbon1Middle;
	private final ModelRenderer ribbon1Bottom;
	private final ModelRenderer headTop;
	private final ModelRenderer headBottom;
	private final ModelRenderer neckRightTop;
	private final ModelRenderer neckLeftTop;
	private final ModelRenderer neckRightBottom;
	private final ModelRenderer neckLeftBottom;
	private final ModelRenderer feetRight;
	private final ModelRenderer feetLeft;
	private final ModelRenderer bodyRight;
	private final ModelRenderer bodyLeft;
	private final ModelRenderer handRight;
	private final ModelRenderer handLeft;

	public ModelDoll() {
		this.textureWidth = 32;
		this.textureHeight = 48;
		this.neckRightBottom = new ModelRenderer(this, 16, 3);
		this.neckRightBottom.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.neckRightBottom.addBox(-2.5F, 2.0F, -1.0F, 1, 1, 2, 0.0F);
		this.feetRight = new ModelRenderer(this, 12, 6);
		this.feetRight.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.feetRight.addBox(-3.5F, 10.5F, -1.5F, 2, 1, 3, 0.0F);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-2.5F, -1.5F, -1.5F, 5, 3, 3, 0.0F);
		this.neckLeftBottom = new ModelRenderer(this, 22, 3);
		this.neckLeftBottom.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.neckLeftBottom.addBox(1.5F, 2.0F, -1.0F, 1, 1, 2, 0.0F);
		this.bodyLeft = new ModelRenderer(this, 0, 25);
		this.bodyLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bodyLeft.addBox(3.5F, 3.5F, -1.5F, 1, 7, 3, 0.0F);
		this.ribbon0Top = new ModelRenderer(this, 9, 36);
		this.ribbon0Top.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ribbon0Top.addBox(-2.5F, -4.5F, -0.5F, 2, 1, 1, 0.0F);
		this.headBottom = new ModelRenderer(this, 0, 10);
		this.headBottom.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.headBottom.addBox(-1.5F, 1.5F, -1.5F, 3, 1, 3, 0.0F);
		this.body = new ModelRenderer(this, 0, 14);
		this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body.addBox(-3.5F, 2.5F, -1.5F, 7, 8, 3, 0.0F);
		this.ribbon0Bottom = new ModelRenderer(this, 0, 36);
		this.ribbon0Bottom.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ribbon0Bottom.addBox(-2.5F, -3.5F, -0.5F, 3, 1, 1, 0.0F);
		this.feetLeft = new ModelRenderer(this, 22, 6);
		this.feetLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.feetLeft.addBox(1.5F, 10.5F, -1.5F, 2, 1, 3, 0.0F);
		this.handLeft = new ModelRenderer(this, 16, 25);
		this.handLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.handLeft.addBox(4.5F, 4.5F, -1.5F, 1, 2, 3, 0.0F);
		this.ribbon1Top = new ModelRenderer(this, 0, 41);
		this.ribbon1Top.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ribbon1Top.addBox(-4.5F, -2.5F, -0.5F, 3, 1, 1, 0.0F);
		this.neckRightTop = new ModelRenderer(this, 16, 0);
		this.neckRightTop.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.neckRightTop.addBox(-2.0F, 1.5F, -1.0F, 1, 1, 2, 0.0F);
		this.ribbon1Middle = new ModelRenderer(this, 9, 41);
		this.ribbon1Middle.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ribbon1Middle.addBox(-4.5F, -1.5F, -0.5F, 2, 1, 1, 0.0F);
		this.handRight = new ModelRenderer(this, 8, 25);
		this.handRight.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.handRight.addBox(-5.5F, 4.5F, -1.5F, 1, 2, 3, 0.0F);
		this.neckLeftTop = new ModelRenderer(this, 22, 0);
		this.neckLeftTop.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.neckLeftTop.addBox(1.0F, 1.5F, -1.0F, 1, 1, 2, 0.0F);
		this.ribbon1Bottom = new ModelRenderer(this, 16, 41);
		this.ribbon1Bottom.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ribbon1Bottom.addBox(-3.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
		this.bodyRight = new ModelRenderer(this, 22, 15);
		this.bodyRight.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bodyRight.addBox(-4.5F, 3.5F, -1.5F, 1, 7, 3, 0.0F);
		this.headTop = new ModelRenderer(this, 0, 6);
		this.headTop.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.headTop.addBox(-1.5F, -2.5F, -1.5F, 3, 1, 3, 0.0F);
		this.headBottom.addChild(this.neckRightBottom);
		this.body.addChild(this.feetRight);
		this.headBottom.addChild(this.neckLeftBottom);
		this.body.addChild(this.bodyLeft);
		this.head.addChild(this.headBottom);
		this.body.addChild(this.feetLeft);
		this.body.addChild(this.handLeft);
		this.headBottom.addChild(this.neckRightTop);
		this.body.addChild(this.handRight);
		this.headBottom.addChild(this.neckLeftTop);
		this.body.addChild(this.bodyRight);
		this.head.addChild(this.headTop);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.head.render(f5);
		this.ribbon0Top.render(f5);
		this.body.render(f5);
		this.ribbon0Bottom.render(f5);
		this.ribbon1Top.render(f5);
		this.ribbon1Middle.render(f5);
		this.ribbon1Bottom.render(f5);
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
