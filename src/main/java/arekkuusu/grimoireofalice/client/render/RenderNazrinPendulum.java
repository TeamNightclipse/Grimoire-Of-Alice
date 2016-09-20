package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.model.ModelNazrinPendulum;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderNazrinPendulum extends Render {

    private static final ResourceLocation TEXTURE = new ResourceLocation(LibMod.MODID, "textures/models/entities/nazrinpendulum.png");
    private static final ModelBase MODEL = new ModelNazrinPendulum();

    public RenderNazrinPendulum(RenderManager renderManager) {
        super(renderManager);
    }
    @Override
    public void doRender(Entity pendulum, double x, double y, double z, float yaw, float pitch) {
        GlStateManager.pushMatrix();
        this.bindEntityTexture(pendulum);
        GlStateManager.translate(x, y, z);
        GlStateManager.scale(0.5, 0.5, 0.5);
        GlStateManager.rotate(180F - yaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.enableLighting();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
        MODEL.render(pendulum, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GlStateManager.disableLighting();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return TEXTURE;
    }
}
