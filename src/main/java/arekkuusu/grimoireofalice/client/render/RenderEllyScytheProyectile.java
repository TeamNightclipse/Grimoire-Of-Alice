/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.util.ResourceLibrary;
import arekkuusu.grimoireofalice.common.entity.EntityEllyScythe;
import arekkuusu.grimoireofalice.common.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEllyScytheProyectile extends Render<EntityEllyScythe> {

	private final RenderItem renderItem;

	public RenderEllyScytheProyectile(RenderManager renderManager) {
		super(renderManager);
		renderItem = Minecraft.getMinecraft().getRenderItem();
	}

	@Override
	public void doRender(EntityEllyScythe ellyScythe, double x, double y, double z, float yaw, float pitch) {
		ItemStack stack = new ItemStack(ModItems.ELLY_SCYTHE);

		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		GlStateManager.enableRescaleNormal();
		GlStateManager.scale(4F, 4F, 4F);
		GlStateManager.rotate(180, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(90, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(ellyScythe.ticksExisted * 16F, 0.0F, 0.0F, 1.0F);
		renderItem.renderItem(stack, ItemCameraTransforms.TransformType.GROUND);
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityEllyScythe entity) {
		return ResourceLibrary.WHITE;
	}
}