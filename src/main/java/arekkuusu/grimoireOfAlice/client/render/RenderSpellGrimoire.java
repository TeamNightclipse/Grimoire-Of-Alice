package arekkuusu.grimoireOfAlice.client.render;

import org.lwjgl.opengl.GL11;

import arekkuusu.grimoireOfAlice.client.model.ModelSpell;
import arekkuusu.grimoireOfAlice.lib.LibMod;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderSpellGrimoire extends Render{

	private static final ResourceLocation TEXTURE = new ResourceLocation(LibMod.MODID, "textures/models/Spell.png");
	private static final ModelBase model = new ModelSpell();
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TEXTURE;
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch) {
		GL11.glPushMatrix();
		bindEntityTexture(entity);
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glRotatef((entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * pitch) - 90F, 0.0F, 1.0F, 0.0F);
		model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}
}
