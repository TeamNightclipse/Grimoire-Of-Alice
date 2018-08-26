/*
 * This class was created by <Katrix>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.compat.danmakucore.form;

import arekkuusu.grimoireofalice.common.lib.LibDanmakuName;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.form.Form;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.form.FormDummy;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(LibMod.MOD_ID)
public class GOAForms {

	@ObjectHolder(LibDanmakuName.WIND)
	public static final Form WIND = FormDummy.instance();
	@ObjectHolder(LibDanmakuName.LEAF)
	public static final Form LEAF = FormDummy.instance();
	@ObjectHolder(LibDanmakuName.UFO)
	public static final Form UFO = FormDummy.instance();
}
