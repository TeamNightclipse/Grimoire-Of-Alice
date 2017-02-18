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
 * ModelUnzanFist - Arekkuusu Created using Tabula 5.1.0
 */

@SideOnly(Side.CLIENT)
public class ModelUnzanFist extends ModelBase {

	private final ModelRenderer fist;
	private final ModelRenderer finger1;
	private final ModelRenderer finger2;
	private final ModelRenderer finger3;
	private final ModelRenderer finger4;
	private final ModelRenderer finger5;
	private final ModelRenderer finger6;
	private final ModelRenderer finger7;

	public ModelUnzanFist() {
		textureWidth = 128;
		textureHeight = 64;
		finger3 = new ModelRenderer(this, 90, 35);
		finger3.setRotationPoint(0.0F, 0.0F, 0.0F);
		finger3.addBox(-3.0F, -10.0F, -11.0F, 6, 22, 6, 0.0F);
		fist = new ModelRenderer(this, 0, 0);
		fist.setRotationPoint(0.0F, 0.0F, 0.0F);
		fist.addBox(-10.0F, -8.0F, -10.0F, 20, 18, 20, 0.0F);
		finger6 = new ModelRenderer(this, 81, 0);
		finger6.setRotationPoint(0.0F, 0.0F, 0.0F);
		finger6.addBox(-10.0F, 10.0F, -11.0F, 6, 2, 17, 0.0F);
		finger1 = new ModelRenderer(this, 90, 35);
		finger1.setRotationPoint(0.0F, 0.0F, 0.0F);
		finger1.addBox(4.0F, -10.0F, -11.0F, 6, 22, 6, 0.0F);
		finger5 = new ModelRenderer(this, 90, 35);
		finger5.setRotationPoint(0.0F, 0.0F, 0.0F);
		finger5.addBox(-10.0F, -10.0F, -11.0F, 6, 22, 6, 0.0F);
		finger7 = new ModelRenderer(this, 0, 40);
		finger7.setRotationPoint(0.0F, 0.0F, 0.0F);
		finger7.addBox(-11.0F, 4.0F, -8.0F, 6, 6, 18, 0.0F);
		finger2 = new ModelRenderer(this, 81, 0);
		finger2.setRotationPoint(0.0F, 0.0F, 0.0F);
		finger2.addBox(4.0F, 10.0F, -11.0F, 6, 2, 17, 0.0F);
		finger4 = new ModelRenderer(this, 81, 0);
		finger4.setRotationPoint(0.0F, 0.0F, 0.0F);
		finger4.addBox(-3.0F, 10.0F, -11.0F, 6, 2, 17, 0.0F);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 0.90F);
		finger3.render(f5);
		fist.render(f5);
		finger6.render(f5);
		finger1.render(f5);
		finger5.render(f5);
		finger7.render(f5);
		finger2.render(f5);
		finger4.render(f5);
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
