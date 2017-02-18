/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

/**
 * ModelMiracleLantern - Arekkuusu
 * Created using Tabula 5.1.0
 */

@SideOnly(Side.CLIENT)
public class ModelMiracleLantern extends ModelBase {

	private final ModelRenderer inside;
	private final ModelRenderer outside;

	public ModelMiracleLantern() {
		this.textureWidth = 32;
		this.textureHeight = 32;
		this.outside = new ModelRenderer(this, 0, 14);
		this.outside.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.outside.addBox(-3.5F, -5.0F, -3.5F, 7, 10, 7, 0.0F);
		this.inside = new ModelRenderer(this, 0, 0);
		this.inside.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.inside.addBox(-2.5F, -4.0F, -2.5F, 5, 8, 5, 0.0F);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.inside.render(f5);
	}

	@SideOnly(Side.CLIENT)
	public void renderMore(float f5) {
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 0.15F);
		this.outside.render(f5);
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
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
