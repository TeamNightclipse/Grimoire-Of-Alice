/*
 * This class was created by <Katrix>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.plugin.danmakucore.form;

import arekkuusu.grimoireofalice.lib.LibFormName;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.katsstuff.danmakucore.entity.danmaku.form.Form;
import net.katsstuff.danmakucore.entity.danmaku.form.FormDummy;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(LibMod.MODID)
public class GOAForms {

	@ObjectHolder(LibFormName.WIND)
	public static final Form WIND = new FormDummy();
	@ObjectHolder(LibFormName.UFO)
	public static final Form UFO = new FormDummy();
}
