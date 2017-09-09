package arekkuusu.grimoireofalice.client.render.model;

import arekkuusu.grimoireofalice.common.entity.EntityYoukaiBook;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * ModelBook - Arekkuusu
 * Created using Tabula 5.1.0
 */

@SideOnly(Side.CLIENT)
public class ModelBook extends ModelBase {

	private final ModelRenderer bigPaperNorth;
	private final ModelRenderer smallPaperNorth;
	private final ModelRenderer smallPaperNorthMiddle;
	private final ModelRenderer smallPaperNorthBottom;
	private final ModelRenderer bookBase;
	private final ModelRenderer bigPaperSouth;
	private final ModelRenderer smallPaperSouth;
	private final ModelRenderer smallPaperSouthMiddle;
	private final ModelRenderer smallPaperSouthBottom;
	private final ModelRenderer bookCoverNorth;
	private final ModelRenderer bookCoverSouth;

	public ModelBook() {
		this.textureWidth = 64;
		this.textureHeight = 32;

		this.smallPaperSouth = new ModelRenderer(this, 17, 16);
		this.smallPaperSouth.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.smallPaperSouth.addBox(-4.0F, -5.0F, 0.0F, 8, 5, 0, 0.0F);
		this.bookCoverNorth = new ModelRenderer(this, 0, 0);
		this.bookCoverNorth.setRotationPoint(0.0F, 0.0F, -1.0F);
		this.bookCoverNorth.addBox(-5.0F, -6.0F, 0.0F, 10, 6, 0, 0.0F);
		this.setRotateAngle(bookCoverNorth, 0.0F, -3.141592653589793F, 0.0F);
		this.smallPaperNorth = new ModelRenderer(this, 0, 16);
		this.smallPaperNorth.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.smallPaperNorth.addBox(-4.0F, -5.0F, 0.0F, 8, 5, 0, 0.0F);
		this.bigPaperSouth = new ModelRenderer(this, 19, 8);
		this.bigPaperSouth.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bigPaperSouth.addBox(-4.0F, -5.0F, -0.01F, 8, 5, 1, 0.0F);
		this.bookBase = new ModelRenderer(this, 0, 22);
		this.bookBase.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bookBase.addBox(-1.0F, 0.01F, -5.0F, 2, 0, 10, 0.0F);
		this.setRotateAngle(bookBase, 0.0F, 1.5707963267948966F, 0.0F);
		this.bookCoverSouth = new ModelRenderer(this, 21, 0);
		this.bookCoverSouth.setRotationPoint(0.0F, 0.0F, 1.0F);
		this.bookCoverSouth.addBox(-5.0F, -6.0F, 0.0F, 10, 6, 0, 0.0F);
		this.bigPaperNorth = new ModelRenderer(this, 0, 8);
		this.bigPaperNorth.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bigPaperNorth.addBox(-4.0F, -5.0F, -0.99F, 8, 5, 1, 0.0F);

		this.smallPaperSouthMiddle = new ModelRenderer(this, 0, 16);
		this.smallPaperSouthMiddle.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.smallPaperSouthMiddle.addBox(-4.0F, -5.0F, 0.0F, 8, 5, 0, 0.0F);

		this.smallPaperSouthBottom = new ModelRenderer(this, 0, 16);
		this.smallPaperSouthBottom.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.smallPaperSouthBottom.addBox(-4.0F, -5.0F, 0.0F, 8, 5, 0, 0.0F);

		this.smallPaperNorthMiddle = new ModelRenderer(this, 17, 16);
		this.smallPaperNorthMiddle.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.smallPaperNorthMiddle.addBox(-4.0F, -5.0F, 0.0F, 8, 5, 0, 0.0F);

		this.smallPaperNorthBottom = new ModelRenderer(this, 17, 16);
		this.smallPaperNorthBottom.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.smallPaperNorthBottom.addBox(-4.0F, -5.0F, 0.0F, 8, 5, 0, 0.0F);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		setRotationAngles(entityIn, ageInTicks);
		GlStateManager.enableCull();
		this.smallPaperSouth.render(scale);
		this.smallPaperSouthMiddle.render(scale);
		this.smallPaperSouthBottom.render(scale);
		this.bigPaperSouth.render(scale);
		this.bookCoverSouth.render(scale);
		this.bookBase.render(scale);
		this.bookCoverNorth.render(scale);
		this.bigPaperNorth.render(scale);
		this.smallPaperNorth.render(scale);
		this.smallPaperNorthMiddle.render(scale);
		this.smallPaperNorthBottom.render(scale);
		GlStateManager.disableCull();
	}

	public void setRotationAngles(Entity entity, float ageInTicks) {
		if(!((EntityYoukaiBook) entity).getIsBookLaying()) {
			float angle = MathHelper.cos(ageInTicks * 0.8F) * (float) Math.PI * 0.25F;
			bookCoverNorth.rotateAngleX = (16 - angle) * 0.5F;
			bookCoverSouth.rotateAngleX = bookCoverNorth.rotateAngleX;

			bigPaperNorth.rotateAngleX = (15 - angle) * 0.5F;
			bigPaperSouth.rotateAngleX = -bigPaperNorth.rotateAngleX;

			smallPaperNorth.rotateAngleX = (12 - angle) * 0.5F;
			smallPaperSouth.rotateAngleX = -smallPaperNorth.rotateAngleX;

			smallPaperNorthMiddle.rotateAngleX = (13 - angle) * 0.5F;
			smallPaperSouthMiddle.rotateAngleX = -smallPaperNorthMiddle.rotateAngleX;

			smallPaperNorthBottom.rotateAngleX = (14 - angle) * 0.5F;
			smallPaperSouthBottom.rotateAngleX = -smallPaperNorthBottom.rotateAngleX;
		}
		else {
			bookCoverNorth.rotateAngleX = 0;
			bookCoverSouth.rotateAngleX = 0;

			bigPaperNorth.rotateAngleX = 0;
			bigPaperSouth.rotateAngleX = 0;

			smallPaperNorth.rotateAngleX = 0;
			smallPaperSouth.rotateAngleX = 0;

			smallPaperNorthMiddle.rotateAngleX = 0;
			smallPaperSouthMiddle.rotateAngleX = 0;

			smallPaperNorthBottom.rotateAngleX = 0;
			smallPaperSouthBottom.rotateAngleX = 0;
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
