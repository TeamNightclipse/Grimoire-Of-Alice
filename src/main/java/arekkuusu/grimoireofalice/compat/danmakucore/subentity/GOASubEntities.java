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
import net.katsstuff.teamnightclipse.danmakucore.danmaku.subentity.SubEntityTypeDummy;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(LibMod.MOD_ID)
public class GOASubEntities {

	@ObjectHolder(LibDanmakuName.WIND)
	public static final SubEntityType WIND = SubEntityTypeDummy.instance();
	@ObjectHolder(LibDanmakuName.SUN)
	public static final SubEntityType SUN = SubEntityTypeDummy.instance();
}
