package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.entity.EntityStopWatch;
import arekkuusu.grimoireofalice.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderStopWatch extends Render<EntityStopWatch> {

	private final RenderItem renderItem;

	public RenderStopWatch(RenderManager renderManager) {
		super(renderManager);
		this.renderItem = Minecraft.getMinecraft().getRenderItem();
	}

	@Override
	public void doRender(EntityStopWatch entity, double x, double y, double z, float entityYaw, float partialTicks) {
		ItemStack stack = new ItemStack(ModItems.stopWatch);

		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5D, y, z + 0.5D);
		GlStateManager.enableRescaleNormal();
		GlStateManager.scale(2F, 2F, 2F);
		GlStateManager.rotate(entity.getTicksAlive() * 32, 0.0F, 1.0F, 0.0F);
		renderItem.renderItem(stack, ItemCameraTransforms.TransformType.GROUND);
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityStopWatch entity) {
		return null;
	}
}
