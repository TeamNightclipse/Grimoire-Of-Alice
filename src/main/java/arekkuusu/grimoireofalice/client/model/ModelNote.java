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
 * ModelNote - Arekkuusu
 * Created using Tabula 5.1.0
 */

public class ModelNote extends ModelBase {

	private final ModelRenderer noteSmall;
	private final ModelRenderer note_small;
	private final ModelRenderer noteBig;
	private final ModelRenderer note_big;

	public ModelNote() {
		this.textureWidth = 32;
		this.textureHeight = 16;
		this.noteBig = new ModelRenderer(this, 11, 0);
		this.noteBig.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.noteBig.addBox(-2.0F, -0.5F, -2.0F, 4, 4, 3, 0.0F);
		this.noteSmall = new ModelRenderer(this, 0, 0);
		this.noteSmall.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.noteSmall.addBox(-1.5F, 0.0F, -1.5F, 3, 3, 2, 0.0F);
		this.note_small = new ModelRenderer(this, 0, 8);
		this.note_small.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.note_small.addBox(-1.5F, -4.0F, -1.5F, 1, 4, 2, 0.0F);
		this.note_big = new ModelRenderer(this, 13, 8);
		this.note_big.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.note_big.addBox(-2.0F, -4.5F, -2.0F, 2, 4, 3, 0.0F);
		this.noteSmall.addChild(this.note_small);
		this.noteBig.addChild(this.note_big);
	}

	@SideOnly(Side.CLIENT)
    public void renderInsideForm(float f5) {
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1, 1, 1, 1F);
		this.noteSmall.render(f5);
		GlStateManager.disableBlend();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		GlStateManager.depthMask(false);
		GlStateManager.color(f, f1, f2, 1F);
		this.noteBig.render(f5);
		GlStateManager.depthMask(true);
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
