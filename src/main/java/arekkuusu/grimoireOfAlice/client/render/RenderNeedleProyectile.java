package arekkuusu.grimoireOfAlice.client.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import arekkuusu.grimoireOfAlice.client.model.ModelNeedle;
import arekkuusu.grimoireOfAlice.entity.EntityNeedle;
import arekkuusu.grimoireOfAlice.lib.LibMod;

public class RenderNeedleProyectile extends Render {

	private static final ResourceLocation TEXTURE = new ResourceLocation(LibMod.MODID, "textures/models/Needle.png");
	private static final ModelNeedle model = new ModelNeedle();
	private int d;
	private float rotate;
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TEXTURE;
	}
	
	@Override
	public void doRender(Entity needle, double bdo, double ub, double le, float o, float f1) {
		GL11.glPushMatrix();
		bindEntityTexture(needle);
		GL11.glTranslatef((float) bdo, (float) ub, (float) le);
		GL11.glRotatef(needle.prevRotationPitch + (needle.rotationPitch - needle.prevRotationPitch) * f1, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef((needle.prevRotationYaw + (needle.rotationYaw - needle.prevRotationYaw) * f1) - 90F, 0.0F, 1.0F, 0.0F);
        model.render(needle, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
    }
}