/*
 * This class was created by <Katrix>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.plugin.danmakucore.subentity;

import arekkuusu.grimoireofalice.lib.LibMod;
import arekkuusu.grimoireofalice.lib.LibSubEntityName;
import net.katsstuff.danmakucore.entity.danmaku.subentity.SubEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

public class GOASubEntities {

	private static final IForgeRegistry<SubEntityType> REGISTRY = GameRegistry.findRegistry(SubEntityType.class);

	public static final SubEntityType WIND = REGISTRY.getValue(resource(LibSubEntityName.WIND));
	public static final SubEntityType NOTE = REGISTRY.getValue(resource(LibSubEntityName.NOTE));
	public static final SubEntityType UFO = REGISTRY.getValue(resource(LibSubEntityName.UFO));
	public static final SubEntityType LEAF = REGISTRY.getValue(resource(LibSubEntityName.LEAF));

	private static ResourceLocation resource(String name) {
		return new ResourceLocation(LibMod.MODID, name);
	}
}
