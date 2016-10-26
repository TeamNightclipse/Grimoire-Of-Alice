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
import net.katsstuff.danmakucore.entity.danmaku.subentity.SubEntityTypeDummy;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(LibMod.MODID)
public class GOASubEntities {

	@ObjectHolder(LibSubEntityName.WIND)
	public static final SubEntityType WIND = new SubEntityTypeDummy();
	@ObjectHolder(LibSubEntityName.NOTE)
	public static final SubEntityType NOTE = new SubEntityTypeDummy();
	@ObjectHolder(LibSubEntityName.UFO)
	public static final SubEntityType UFO = new SubEntityTypeDummy();
}
