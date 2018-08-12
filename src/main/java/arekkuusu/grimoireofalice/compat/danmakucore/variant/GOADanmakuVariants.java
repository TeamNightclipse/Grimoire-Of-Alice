/*
 * This class was created by <Katrix>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.compat.danmakucore.variant;

import arekkuusu.grimoireofalice.common.lib.LibDanmakuName;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.DanmakuVariant;
import net.katsstuff.teamnightclipse.danmakucore.registry.DanmakuRegistry;
import net.minecraft.util.ResourceLocation;

public class GOADanmakuVariants {

	public static final DanmakuVariant UFO = DanmakuRegistry.DanmakuVariant().getValue(resource(LibDanmakuName.UFO));

	private static ResourceLocation resource(String name) {
		return new ResourceLocation(LibMod.MOD_ID, name);
	}
}
