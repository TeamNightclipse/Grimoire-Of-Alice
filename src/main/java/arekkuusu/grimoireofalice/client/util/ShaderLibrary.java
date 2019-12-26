/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.util;

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
	public static final ShaderType fragment = ShaderType.fragment();
	public static final ShaderType vertex = ShaderType.vertex();
	public static final UniformType floatUniformType = UniformType.unFloat();
	public static final MirrorShaderProgram BRIGHT = loadProgram(
			ResourceLibrary.BRIGHT_SHADER,
			ImmutableList.of(fragment, vertex),
			ImmutableMap.of("brightness", floatUniformType)
	);
	public static final ResourceLocation INVERT = getShader("minecraft", "invert");

	public static ResourceLocation getShader(String mod, String name) {
		return new ResourceLocation(mod, "shaders/post/" + name + ".json");
	}

	public static MirrorShaderProgram loadProgram(ResourceLocation location, List<ShaderType> shaders, Map<String, UniformType> types) throws RuntimeException {
		Map<String, UniformBase<? extends UniformType>> uniforms = Maps.newHashMap();
		types.forEach((k, v) -> uniforms.put(k, new UniformBase<>(v, 1)));
		return ShaderManager.createProgram(location, shaders, uniforms, false);
	}

	public static void init() {
	}
}
