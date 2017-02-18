package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.ResourceLocations;
import arekkuusu.grimoireofalice.client.model.ModelFlat;
import arekkuusu.grimoireofalice.common.entity.EntityGap;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderLayerGap {

	private static final ModelBase MODEL = new ModelFlat();
	private final RenderGap renderGap;

	public RenderLayerGap(RenderGap renderGap){
		this.renderGap = renderGap;
	}

	public void doRenderLayer(EntityGap entity) {
		renderGap.bindTexture(ResourceLocations.GAP_COLOR);

		if(entity.getColor() != EnumDyeColor.WHITE) {
			float[] afloat = EntitySheep.getDyeRgb(entity.getColor());
			GlStateManager.color(afloat[0], afloat[1], afloat[2]);
		}

		MODEL.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
	}
}
