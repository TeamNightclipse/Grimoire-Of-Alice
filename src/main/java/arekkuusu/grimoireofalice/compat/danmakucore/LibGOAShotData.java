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

	public static final ShotData WIND = new ShotData(
			GOAForms.WIND,
			null,
			COLOR_SATURATED_CYAN,
			COLOR_VANILLA_WHITE,
			0.4F,
			0.5F,
			0.5F,
			0.5F,
			0,
			50,
			GOASubEntities.WIND
	);
	public static final ShotData UFO = new ShotData(
			GOAForms.UFO,
			null,
			COLOR_SATURATED_CYAN,
			COLOR_VANILLA_WHITE,
			1.0F,
			0.5F,
			0.5F,
			0.5F,
			0,
			50,
			GOASubEntities.UFO
	);
	public static final ShotData LEAF = new ShotData(
			GOAForms.LEAF,
			null,
			COLOR_SATURATED_GREEN,
			COLOR_VANILLA_WHITE,
			0.4F,
			0.3F,
			0.3F,
			0.3F,
			0,
			10,
			GOASubEntities.LEAF
	);
	public static final ShotData SUN = new ShotData(
			LibForms.SPHERE,
			null,
			COLOR_SATURATED_RED,
			COLOR_VANILLA_WHITE,
			0.5F,
			1.25F,
			1.25F,
			1.25F,
			0,
			200,
			GOASubEntities.SUN
	);
}
