package arekkuusu.grimoireofalice.client.render.tile;

import arekkuusu.grimoireofalice.common.block.tile.TileInventoryBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileItemStackRenderer<T extends TileInventoryBase> extends TileEntitySpecialRenderer<T> {

	@Override
	public void render(T te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		ItemStack stack = te.getItemStack();
		if(!stack.isEmpty()) {
			renderStack(stack, x, y, z, partialTicks);
		}
	}

	public void renderStack(ItemStack stack, double x, double y, double z, float partialTicks) {
			GlStateManager.pushMatrix();
			GlStateManager.translate(x + 0.5, y + 0.38, z + 0.5);
			GlStateManager.rotate(partialTicks + (Minecraft.getSystemTime() + partialTicks) / 20F * 0.5F % 360F, 0F, 1F, 0F);
			//Fix stack 'y' center
			if(stack.getItem() instanceof ItemBlock) {
				GlStateManager.translate(0F, -0.1F, 0F);
			}
			GlStateManager.alphaFunc(516, 0.1F);
			GlStateManager.enableRescaleNormal();
			Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			Minecraft.getMinecraft().getRenderManager().renderEngine.getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);
			GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			RenderItem render = Minecraft.getMinecraft().getRenderItem();
			IBakedModel model = render.getItemModelWithOverrides(stack, null, null);
			IBakedModel transformedModel = net.minecraftforge.client.ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.GROUND, false);
			render.renderItem(stack, transformedModel);
			Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			Minecraft.getMinecraft().getRenderManager().renderEngine.getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();
			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();
	}
}