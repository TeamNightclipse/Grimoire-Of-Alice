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
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

public class GOADanmakuVariants {

	private static final IForgeRegistry<DanmakuVariant> REGISTRY = GameRegistry.findRegistry(DanmakuVariant.class);

	public static final DanmakuVariant UFO = REGISTRY.getValue(resource(LibDanmakuVariantName.LEAF));
	public static final DanmakuVariant LEAF = REGISTRY.getValue(resource(LibDanmakuVariantName.UFO));

	private static ResourceLocation resource(String name) {
		return new ResourceLocation(LibMod.MODID, name);
	}
}
