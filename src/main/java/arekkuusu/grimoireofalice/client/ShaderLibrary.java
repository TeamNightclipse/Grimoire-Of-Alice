/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client;

import arekkuusu.grimoireofalice.client.util.ResourceLibrary;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.katsstuff.teamnightclipse.mirror.client.shaders.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Map;

@SideOnly(Side.CLIENT)
public final class ShaderLibrary {

	public static final MirrorShaderProgram BRIGHT = loadProgram(
			ResourceLibrary.BRIGHT_SHADER,
			ImmutableList.of(ShaderType.fragment(), ShaderType.vertex()),
			ImmutableMap.of("brightness", UniformType.unFloat())
	);

	private static MirrorShaderProgram loadProgram(ResourceLocation location, List<ShaderType> shaders, Map<String, UniformType> types) {
		Map<String, UniformBase<? extends UniformType>> uniforms = Maps.newHashMap();
		types.forEach((k, v) -> uniforms.put(k, new UniformBase<>(v, 1)));
		return ShaderManager.createProgram(location, shaders, uniforms, false);
	}

	public static void init() {
	}
}
