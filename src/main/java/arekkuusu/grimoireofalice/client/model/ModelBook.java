package arekkuusu.grimoireofalice.client.model;

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

	private final ModelRenderer big_paper_north;
	private final ModelRenderer small_paper_north;
	private final ModelRenderer small_paper_north_middle;
	private final ModelRenderer small_paper_north_bottom;
	private final ModelRenderer book_base;
	private final ModelRenderer big_paper_south;
	private final ModelRenderer small_paper_south;
	private final ModelRenderer small_paper_south_middle;
	private final ModelRenderer small_paper_south_bottom;
	private final ModelRenderer book_cover_north;
	private final ModelRenderer book_cover_south;

	public ModelBook() {
		this.textureWidth = 64;
		this.textureHeight = 32;

		this.small_paper_south = new ModelRenderer(this, 17, 16);
		this.small_paper_south.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.small_paper_south.addBox(-4.0F, -5.0F, 0.0F, 8, 5, 0, 0.0F);
		this.book_cover_north = new ModelRenderer(this, 0, 0);
		this.book_cover_north.setRotationPoint(0.0F, 0.0F, -1.0F);
		this.book_cover_north.addBox(-5.0F, -6.0F, 0.0F, 10, 6, 0, 0.0F);
		this.setRotateAngle(book_cover_north, 0.0F, -3.141592653589793F, 0.0F);
		this.small_paper_north = new ModelRenderer(this, 0, 16);
		this.small_paper_north.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.small_paper_north.addBox(-4.0F, -5.0F, 0.0F, 8, 5, 0, 0.0F);
		this.big_paper_south = new ModelRenderer(this, 19, 8);
		this.big_paper_south.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.big_paper_south.addBox(-4.0F, -5.0F, -0.01F, 8, 5, 1, 0.0F);
		this.book_base = new ModelRenderer(this, 0, 22);
		this.book_base.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.book_base.addBox(-1.0F, 0.01F, -5.0F, 2, 0, 10, 0.0F);
		this.setRotateAngle(book_base, 0.0F, 1.5707963267948966F, 0.0F);
		this.book_cover_south = new ModelRenderer(this, 21, 0);
		this.book_cover_south.setRotationPoint(0.0F, 0.0F, 1.0F);
		this.book_cover_south.addBox(-5.0F, -6.0F, 0.0F, 10, 6, 0, 0.0F);
		this.big_paper_north = new ModelRenderer(this, 0, 8);
		this.big_paper_north.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.big_paper_north.addBox(-4.0F, -5.0F, -0.99F, 8, 5, 1, 0.0F);

		this.small_paper_south_middle = new ModelRenderer(this, 0, 16);
		this.small_paper_south_middle.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.small_paper_south_middle.addBox(-4.0F, -5.0F, 0.0F, 8, 5, 0, 0.0F);

		this.small_paper_south_bottom = new ModelRenderer(this, 0, 16);
		this.small_paper_south_bottom.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.small_paper_south_bottom.addBox(-4.0F, -5.0F, 0.0F, 8, 5, 0, 0.0F);

		this.small_paper_north_middle = new ModelRenderer(this, 17, 16);
		this.small_paper_north_middle.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.small_paper_north_middle.addBox(-4.0F, -5.0F, 0.0F, 8, 5, 0, 0.0F);

		this.small_paper_north_bottom = new ModelRenderer(this, 17, 16);
		this.small_paper_north_bottom.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.small_paper_north_bottom.addBox(-4.0F, -5.0F, 0.0F, 8, 5, 0, 0.0F);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		setRotationAngles(entityIn, ageInTicks);
		GlStateManager.enableCull();
		this.small_paper_south.render(scale);
		this.small_paper_south_middle.render(scale);
		this.small_paper_south_bottom.render(scale);
		this.big_paper_south.render(scale);
		this.book_cover_south.render(scale);
		this.book_base.render(scale);
		this.book_cover_north.render(scale);
		this.big_paper_north.render(scale);
		this.small_paper_north.render(scale);
		this.small_paper_north_middle.render(scale);
		this.small_paper_north_bottom.render(scale);
		GlStateManager.disableCull();
	}

	public void setRotationAngles(Entity entity, float ageInTicks) {
		if (!((EntityYoukaiBook) entity).getIsBookLaying()) {
			float angle = MathHelper.cos(ageInTicks * 0.8F) * (float) Math.PI * 0.25F;
			book_cover_north.rotateAngleX = (16 - angle) * 0.5F;
			book_cover_south.rotateAngleX = book_cover_north.rotateAngleX;

			big_paper_north.rotateAngleX = (15 - angle) * 0.5F;
			big_paper_south.rotateAngleX = -big_paper_north.rotateAngleX;

			small_paper_north.rotateAngleX = (12 - angle) * 0.5F;
			small_paper_south.rotateAngleX = -small_paper_north.rotateAngleX;

			small_paper_north_middle.rotateAngleX = (13 - angle) * 0.5F;
			small_paper_south_middle.rotateAngleX = -small_paper_north_middle.rotateAngleX;

			small_paper_north_bottom.rotateAngleX = (14 - angle) * 0.5F;
			small_paper_south_bottom.rotateAngleX = -small_paper_north_bottom.rotateAngleX;
		}
		else {
			book_cover_north.rotateAngleX = 0;
			book_cover_south.rotateAngleX = 0;

			big_paper_north.rotateAngleX = 0;
			big_paper_south.rotateAngleX = 0;

			small_paper_north.rotateAngleX = 0;
			small_paper_south.rotateAngleX = 0;

			small_paper_north_middle.rotateAngleX = 0;
			small_paper_south_middle.rotateAngleX = 0;

			small_paper_north_bottom.rotateAngleX = 0;
			small_paper_south_bottom.rotateAngleX = 0;
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
