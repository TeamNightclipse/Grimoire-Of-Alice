/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.util.resource;

import arekkuusu.grimoireofalice.client.util.ResourceLibrary;
import arekkuusu.grimoireofalice.common.core.helper.LogHelper;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class SpriteLoader implements IResourceManagerReloadListener {

	private static final Map<ResourceLocation, SpriteResource> SPRITE_RESOURCE_MAP = new HashMap<>();
	public static final SpriteLoader INSTANCE = new SpriteLoader();
	static boolean reloading;

	public static SpriteResource load(Location location, String name) {
		ResourceLocation resource = ResourceLibrary.getTexture(location, name);
		SpriteResource bind = new SpriteResource(resource);
		SPRITE_RESOURCE_MAP.put(resource, bind);
		return bind;
	}

	public static FrameSpriteResource load(Location location, String name, int rows, int columns) {
		if(rows <= 0 || columns <= 0) {
			LogHelper.fatal("[SpriteLoader] Your sprite can't have 0 rows or columns" + location.toString());
		}

		ResourceLocation resource = ResourceLibrary.getTexture(location, name);
		FrameSpriteResource bind = new FrameSpriteResource(resource, rows, columns);
		SPRITE_RESOURCE_MAP.put(resource, bind);
		return bind;
	}

	@Override
	public void onResourceManagerReload(@Nullable IResourceManager resourceManager) {
		if(reloading) return;
		reloading = true;
		for(Map.Entry<ResourceLocation, SpriteResource> entry : SPRITE_RESOURCE_MAP.entrySet()) {
			SpriteResource bind = entry.getValue();
			bind.reload();
		}
		reloading = false;
	}
}
