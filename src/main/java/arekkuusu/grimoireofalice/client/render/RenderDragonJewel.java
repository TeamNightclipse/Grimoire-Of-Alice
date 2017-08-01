package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.ResourceLocations;
import arekkuusu.grimoireofalice.client.model.ModelFlat;
import arekkuusu.grimoireofalice.common.entity.EntityDragonJewel;
import arekkuusu.grimoireofalice.common.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
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
public class RenderDragonJewel extends Render<EntityDragonJewel> {

	private final RenderItem renderItem;
	private static final ModelBase MODEL = new ModelFlat();

	public RenderDragonJewel(RenderManager renderManager) {
		super(renderManager);
		renderItem = Minecraft.getMinecraft().getRenderItem();
	}

	@Override
	public void doRender(EntityDragonJewel entity, double x, double y, double z, float entityYaw, float partialTicks) {
		ItemStack stack = new ItemStack(ModItems.DRAGON_JEWEL);

		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		GlStateManager.enableRescaleNormal();
		GlStateManager.scale(2F, 2F, 2F);
		GlStateManager.rotate(entity.getTicksAlive() * 32F, 0.0F, 1.0F, 0.0F);
		renderItem.renderItem(stack, ItemCameraTransforms.TransformType.GROUND);
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();

		GlStateManager.pushMatrix();
		bindEntityTexture(entity);
		GlStateManager.translate(x, y, z);
		GlStateManager.disableLighting();
		GlStateManager.enableBlend();
		float size = 2.0F;
		GlStateManager.scale(size, size, size);
		float angle = 90;
		GlStateManager.rotate(180F - renderManager.playerViewY + angle, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(180F - renderManager.playerViewX, 0.0F, 0.0F, 1.0F);
		GlStateManager.rotate(-renderManager.playerViewX - entity.getTicksAlive() * 16, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(angle * 5F, 0.0F, 0.0F, 1.0F);
		MODEL.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.disableBlend();
		GlStateManager.enableLighting();
		GlStateManager.enableRescaleNormal();
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityDragonJewel entity) {
		return ResourceLocations.DRAGON_JEWEL_TEXTURE;
	}
}
