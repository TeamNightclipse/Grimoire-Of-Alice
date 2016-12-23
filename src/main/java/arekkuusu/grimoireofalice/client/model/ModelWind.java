/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.model;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * ModelWind - Arekkuusu Created using Tabula 5.1.0
 */
public class ModelWind extends ModelBase {

	private final ModelRenderer north;
	private final ModelRenderer east;
	private final ModelRenderer south;
	private final ModelRenderer west;

	public ModelWind() {
		textureWidth = 128;
		textureHeight = 128;
		north = new ModelRenderer(this, 0, 0);
		north.setRotationPoint(0.0F, 0.0F, 0.0F);
		north.addBox(-25.0F, -10.0F, 0.0F, 50, 20, 0, 0.0F);
		south = new ModelRenderer(this, 0, 46);
		south.setRotationPoint(0.0F, 0.0F, 0.0F);
		south.addBox(-25.0F, -10.0F, 0.0F, 50, 20, 0, 0.0F);
		setRotateAngle(south, 0.0F, -1.5707963267948966F, 0.0F);
		west = new ModelRenderer(this, 0, 69);
		west.setRotationPoint(0.0F, 0.0F, 0.0F);
		west.addBox(-25.0F, -10.0F, 0.0F, 50, 20, 0, 0.0F);
		setRotateAngle(west, 0.0F, -2.356194490192345F, 0.0F);
		east = new ModelRenderer(this, 0, 23);
		east.setRotationPoint(0.0F, 0.0F, 0.0F);
		east.addBox(-25.0F, -10.0F, 0.0F, 50, 20, 0, 0.0F);
		setRotateAngle(east, 0.0F, -0.7853981633974483F, 0.0F);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(f, f1, f2, 0.8F);

		north.render(f5);
		south.render(f5);
		west.render(f5);
		east.render(f5);

		GlStateManager.disableBlend();
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
