/*
 * This class was created by <Katrix>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.plugin.danmakucore.subentity;

import arekkuusu.grimoireofalice.common.lib.LibMod;
import arekkuusu.grimoireofalice.common.lib.LibSubEntityName;
import net.katsstuff.danmakucore.entity.danmaku.subentity.SubEntityType;
import net.katsstuff.danmakucore.registry.DanmakuRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

public class GOASubEntities {

	private static final IForgeRegistry<SubEntityType> REGISTRY = DanmakuRegistry.SUB_ENTITY;

	public static final SubEntityType WIND = REGISTRY.getValue(resource(LibSubEntityName.WIND));
	public static final SubEntityType LEAF = REGISTRY.getValue(resource(LibSubEntityName.LEAF));
	public static final SubEntityType UFO = REGISTRY.getValue(resource(LibSubEntityName.UFO));
	public static final SubEntityType SUN = REGISTRY.getValue(resource(LibSubEntityName.SUN));

	private static ResourceLocation resource(String name) {
		return new ResourceLocation(LibMod.MODID, name);
	}
}
