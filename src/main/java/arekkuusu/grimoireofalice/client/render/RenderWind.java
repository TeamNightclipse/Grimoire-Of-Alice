package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.model.ModelWind;
import arekkuusu.grimoireofalice.entity.EntityWind;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderWind extends Render<EntityWind> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(LibMod.MODID, "textures/models/entities/Wind.png");
    private static final ModelBase MODEL = new ModelWind();

    public RenderWind(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    public void doRender(EntityWind wind, double x, double y, double z, float yaw, float pitch) {
        GlStateManager.pushMatrix();
        bindEntityTexture(wind);
        GlStateManager.translate(x, y, z);
        GlStateManager.rotate(wind.getTime() * 64, 0.0F, 1.0F, 0.0F);
        MODEL.render(wind, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GlStateManager.popMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityWind entity) {
        return TEXTURE;
    }
}
