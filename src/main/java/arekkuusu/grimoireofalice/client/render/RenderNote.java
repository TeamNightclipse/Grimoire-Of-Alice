/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.entity.EntityNote;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderNote extends Render<EntityNote> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(LibMod.MODID, "textures/models/entities/note.png");
	
	public RenderNote(RenderManager renderManager) {
		super(renderManager);
	}
	
	@Override
    public void doRender(EntityNote circle, double x, double y, double z, float yaw, float pitch) {
		//Will probably keep this empty, but would be cool to have a Note Entity that changes color overtime
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityNote entity) {
		return TEXTURE;
	}

}
