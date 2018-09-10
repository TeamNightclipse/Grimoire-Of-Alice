/*
 * This class was created by <Katrix>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.compat.danmakucore;

import arekkuusu.grimoireofalice.compat.danmakucore.form.GOAForms;
import arekkuusu.grimoireofalice.compat.danmakucore.subentity.GOASubEntities;
import net.katsstuff.teamnightclipse.danmakucore.data.ShotData;
import net.katsstuff.teamnightclipse.danmakucore.lib.data.LibForms;

import static net.katsstuff.teamnightclipse.danmakucore.lib.LibColor.*;

public class LibGOAShotData {

	public static final ShotData WIND = ShotData.DefaultShotData()
			.setSubEntity(GOASubEntities.WIND)
			.setForm(GOAForms.WIND)
			.setMainColor(COLOR_VANILLA_WHITE)
			.setDamage(0F)
			.setSize(1F)
			.setDelay(0)
			.setEnd(50);

	public static final ShotData UFO = ShotData.DefaultShotData()
			.setSubEntity(GOASubEntities.UFO)
			.setForm(GOAForms.UFO)
			.setMainColor(COLOR_VANILLA_WHITE)
			.setDamage(0.5F)
			.setSize(0.5F)
			.setDelay(0)
			.setEnd(50);

	public static final ShotData LEAF = ShotData.DefaultShotData()
			.setSubEntity(GOASubEntities.LEAF)
			.setForm(GOAForms.LEAF)
			.setMainColor(COLOR_SATURATED_GREEN)
			.setDamage(0.4F)
			.setSize(0.5F)
			.setDelay(0)
			.setEnd(50);

	public static final ShotData SUN = ShotData.DefaultShotData()
			.setSubEntity(GOASubEntities.SUN)
			.setForm(LibForms.BUBBLE)
			.setCoreColor(COLOR_VANILLA_RED)
			.setEdgeColor(COLOR_WHITE)
			.setDamage(0.5F)
			.setSize(1.25F)
			.setDelay(0)
			.setEnd(200);

}
