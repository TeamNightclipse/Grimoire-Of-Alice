/*******************************************************************************
 * Arekkuusu / Solar 2017
 *
 * This project is licensed under the MIT.
 * The source code is available on github:
 * https://github.com/ArekkuusuJerii/Solar#solar
 ******************************************************************************/
package arekkuusu.grimoireofalice.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;

/**
 * Created by <Arekkuusu> on 08/10/2017.
 * It's distributed as part of Solar.
 */
@SideOnly(Side.CLIENT)
public final class ShaderLibrary {

	public static final ShaderGroup PENCIL = loadShader("minecraft", "pencil");
	public static final ShaderGroup INVERT = loadShader("minecraft", "invert");
	public static final ShaderGroup ART = loadShader("minecraft", "art");
	public static final ShaderGroup COLOR = loadShader("minecraft", "color_convolve");

	private static ShaderGroup loadShader(String mod, String name) {
		ResourceLocation location = new ResourceLocation(mod, "shaders/post/" + name + ".json");
		Minecraft mc = Minecraft.getMinecraft();
		ShaderGroup shader = null;
		try {
			shader = new ShaderGroup(mc.renderEngine, mc.getResourceManager(), mc.getFramebuffer(), location);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return shader;
	}

	public static void init() {
	}
}
