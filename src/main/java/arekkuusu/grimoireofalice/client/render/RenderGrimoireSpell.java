/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.ResourceLocations;
import arekkuusu.grimoireofalice.client.model.ModelFlat;
import arekkuusu.grimoireofalice.common.entity.EntityGrimoireSpell;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBook;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderGrimoireSpell extends Render<EntityGrimoireSpell> {

	private final ModelBook modelBook = new ModelBook();
	private static final ModelBase MODEL = new ModelFlat();

	public RenderGrimoireSpell(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityGrimoireSpell circle, double x, double y, double z, float yaw, float pitch) {

		GlStateManager.pushMatrix();
		bindEntityTexture(circle);
		GlStateManager.translate(x, y, z);
		GlStateManager.disableLighting();
		float size = 6.0F - circle.getTickCount() * 0.01F;
		GlStateManager.scale(size, size, size);
		GlStateManager.rotate(circle.getTickCount() * 16F, 0.0F, 1.0F, 0.0F);
		MODEL.render(circle, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();

		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5F, y + 0.75F, z + 0.5F);
		float ticks = circle.getTickCount() + 5;
		GlStateManager.translate(0.0F, 0.1F + MathHelper.sin(ticks * 0.1F) * 0.01F, 0.0F);

		float bookRotation = circle.getBookRotation() - circle.getBookRotationPrev();

		while(bookRotation >= Math.PI) {
			bookRotation -= Math.PI * 2F;
		}
		while(bookRotation < -Math.PI) {
			bookRotation += Math.PI * 2F;
		}

		float rotateAngle = circle.getBookRotationPrev() + bookRotation * 5;
		GlStateManager.rotate(-rotateAngle * (180F / (float) Math.PI), 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(80.0F, 0.0F, 0.0F, 1.0F);
		bindTexture(ResourceLocations.GRIMOIRE_BOOK);
		float pageFlipLow = circle.getPageFlipPrev() + (circle.getPageFlip() - circle.getPageFlipPrev()) * 5 + 0.25F;
		float pageFlipHigh = circle.getPageFlipPrev() + (circle.getPageFlip() - circle.getPageFlipPrev()) * 5 + 0.75F;
		pageFlipLow = (pageFlipLow - MathHelper.fastFloor(pageFlipLow)) * 1.6F - 0.3F;
		pageFlipHigh = (pageFlipHigh - MathHelper.fastFloor(pageFlipHigh)) * 1.6F - 0.3F;

		if(pageFlipLow < 0.0F) {
			pageFlipLow = 0.0F;
		}

		if(pageFlipHigh < 0.0F) {
			pageFlipHigh = 0.0F;
		}

		if(pageFlipLow > 1.0F) {
			pageFlipLow = 1.0F;
		}

		if(pageFlipHigh > 1.0F) {
			pageFlipHigh = 1.0F;
		}

		float pageSpread = circle.getBookSpreadPrev() + (circle.getBookSpread() - circle.getBookSpreadPrev()) * 5;
		GlStateManager.enableCull();
		modelBook.render(null, ticks, pageFlipLow, pageFlipHigh, pageSpread, 0.0F, 0.0625F);
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityGrimoireSpell circle) {
		return ResourceLocations.GRIMOIRE_CIRCLE_TEXTURE;
	}
}
