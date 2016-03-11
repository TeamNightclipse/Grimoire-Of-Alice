/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.client.render;

import org.lwjgl.opengl.GL11;

import arekkuusu.grimoireOfAlice.client.model.ModelDoll;
import arekkuusu.grimoireOfAlice.lib.LibMod;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderExplosiveDoll extends Render {

	private static final ResourceLocation TEXTURE = new ResourceLocation(LibMod.MODID.toLowerCase(), "textures/entity/yourtexture.png");
	private static final ModelBase MODEL = new ModelDoll();

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TEXTURE;
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTick) {
		GL11.glPushMatrix();

		bindEntityTexture(entity);

		GL11.glTranslated(x, y, z);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		MODEL.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

		GL11.glPopMatrix();
	}
}

