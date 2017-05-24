/*
 * This class was created by <Katrix>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.plugin.danmakucore.variant;

import arekkuusu.grimoireofalice.common.lib.LibDanmakuVariantName;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.katsstuff.danmakucore.entity.danmaku.DanmakuVariant;
import net.katsstuff.danmakucore.registry.DanmakuRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

public class GOADanmakuVariants {

	private static final IForgeRegistry<DanmakuVariant> REGISTRY = DanmakuRegistry.DANMAKU_VARIANT;

	public static final DanmakuVariant UFO = REGISTRY.getValue(resource(LibDanmakuVariantName.UFO));

	private static ResourceLocation resource(String name) {
		return new ResourceLocation(LibMod.MODID, name);
	}
}
