/*
 * This class was created by <Katrix>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.plugin.danmakucore;

import arekkuusu.grimoireofalice.plugin.danmakucore.form.FormWind;
import arekkuusu.grimoireofalice.plugin.danmakucore.subentity.SubEntityNote;
import arekkuusu.grimoireofalice.plugin.danmakucore.subentity.SubEntityWind;
import net.katsstuff.danmakucore.entity.danmaku.form.Form;
import net.katsstuff.danmakucore.entity.danmaku.subentity.SubEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class Initializer {

	@SubscribeEvent
	public static void registerForms(RegistryEvent.Register<Form> event) {
		event.getRegistry().registerAll(
				new FormWind()
		);
	}

	@SubscribeEvent
	public static void registerSubEntities(RegistryEvent.Register<SubEntityType> event) {
		event.getRegistry().registerAll(
				new SubEntityWind(),
				new SubEntityNote()
		);
	}
}