package arekkuusu.grimoireofalice.client.render.tile;

import arekkuusu.grimoireofalice.common.block.tile.TilePillarAltar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TilePillarAltarRenderer extends TileEntitySpecialRenderer<TilePillarAltar> {

	@Override
	public void render(TilePillarAltar te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5F, y + te.getRenderHeight(), z + 0.5);
		GlStateManager.scale(1.5, 1.5, 1.5);
		GlStateManager.rotate(te.tickCount, 0F, 1F, 0F);
		ItemStack stack = te.getItemStack();
		Minecraft mc = Minecraft.getMinecraft();
		if(!stack.isEmpty()) {
			mc.getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.GROUND);
		}
		GlStateManager.popMatrix();
	}
}
