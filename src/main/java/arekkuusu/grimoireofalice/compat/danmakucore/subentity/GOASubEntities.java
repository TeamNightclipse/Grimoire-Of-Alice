/*
 * This class was created by <Katrix>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.compat.danmakucore.subentity;

import arekkuusu.grimoireofalice.common.lib.LibDanmakuName;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.subentity.SubEntityType;
import net.katsstuff.teamnightclipse.danmakucore.registry.DanmakuRegistry;
import net.minecraft.util.ResourceLocation;

public class GOASubEntities {

	public static final SubEntityType WIND = DanmakuRegistry.SubEntity().getValue(resource(LibDanmakuName.WIND));
	public static final SubEntityType LEAF = DanmakuRegistry.SubEntity().getValue(resource(LibDanmakuName.LEAF));
	public static final SubEntityType UFO = DanmakuRegistry.SubEntity().getValue(resource(LibDanmakuName.UFO));
	public static final SubEntityType SUN = DanmakuRegistry.SubEntity().getValue(resource(LibDanmakuName.SUN));

	private static ResourceLocation resource(String name) {
		return new ResourceLocation(LibMod.MOD_ID, name);
	}
}
