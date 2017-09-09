/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.util.resource;

import arekkuusu.grimoireofalice.common.core.helper.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class SpriteResource {

	private final ResourceLocation location;
	private ITextureObject texture;

	public SpriteResource(ResourceLocation location) {
		this.location = location;
		load();
	}

	public ResourceLocation getLocation() {
		return location;
	}

	public void bind() {
		if(texture == null || SpriteLoader.reloading) return;
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getGlTextureId());
	}

	public void bindManager() {
		if(texture == null || SpriteLoader.reloading) return;
		GlStateManager.bindTexture(texture.getGlTextureId());
	}

	public void reload() {
		if(!SpriteLoader.reloading) return;
		unLoad();
		load();
	}

	private void load() {
		if(texture != null) return;
		texture = new SimpleTexture(location);
		try {
			texture.loadTexture(Minecraft.getMinecraft().getResourceManager());
		} catch(Exception e) {
			LogHelper.warn("[Sprite Resource] Failed to load texture " + location.toString());
			e.printStackTrace();
			texture = TextureUtil.MISSING_TEXTURE;
		}
	}

	private void unLoad() {
		if(texture != null)
			GL11.glDeleteTextures(texture.getGlTextureId());
		texture = null;
	}
}
