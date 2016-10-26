/*
 * This class was created by <Katrix>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.plugin.danmakucore.variant;

import arekkuusu.grimoireofalice.lib.LibDanmakuVariantName;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.katsstuff.danmakucore.entity.danmaku.DanmakuVariant;
import net.katsstuff.danmakucore.entity.danmaku.DanmakuVariantDummy;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(LibMod.MODID)
public class GOADanmakuVariants {

	@ObjectHolder(LibDanmakuVariantName.WIND)
	public static final DanmakuVariant WIND = new DanmakuVariantDummy();
	@ObjectHolder(LibDanmakuVariantName.UFO)
	public static final DanmakuVariant UFO = new DanmakuVariantDummy();
}
