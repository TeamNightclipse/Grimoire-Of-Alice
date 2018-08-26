package arekkuusu.grimoireofalice.client.render.tile;

import arekkuusu.grimoireofalice.common.block.tile.TileCraftingAltar;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileCraftingAltarRenderer extends TileEntitySpecialRenderer<TileCraftingAltar> {

	@Override
	public void render(TileCraftingAltar te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();

		GlStateManager.popMatrix();
	}
}