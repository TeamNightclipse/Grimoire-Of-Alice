package arekkuusu.grimoireofalice.client.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * ModelKinkakuJiCeiling - Arekkuusu
 * Created using Tabula 5.1.0
 */

@SideOnly(Side.CLIENT)
public class ModelKinkakuJiCeiling extends ModelBase {

	private final ModelRenderer ceiling;

	public ModelKinkakuJiCeiling() {
		this.textureWidth = 128;
		this.textureHeight = 48;
		this.ceiling = new ModelRenderer(this, 0, 0);
		this.ceiling.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ceiling.addBox(-16.0F, 0.0F, -16.0F, 32, 1, 32, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.ceiling.render(f5);
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
