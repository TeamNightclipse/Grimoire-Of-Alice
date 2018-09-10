/*
 * This class was created by <Katrix>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.compat.danmakucore;

import arekkuusu.grimoireofalice.compat.danmakucore.form.FormLeaf;
import arekkuusu.grimoireofalice.compat.danmakucore.form.FormUfo;
import arekkuusu.grimoireofalice.compat.danmakucore.form.FormWind;
import arekkuusu.grimoireofalice.compat.danmakucore.subentity.SubEntityLeaf;
import arekkuusu.grimoireofalice.compat.danmakucore.subentity.SubEntitySunBullet;
import arekkuusu.grimoireofalice.compat.danmakucore.subentity.SubEntityUfo;
import arekkuusu.grimoireofalice.compat.danmakucore.subentity.SubEntityWind;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.form.Form;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.subentity.SubEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class Initializer {

	@SubscribeEvent
	public static void registerForms(RegistryEvent.Register<Form> event) {
		event.getRegistry().registerAll(
				new FormWind(),
				new FormUfo(),
				new FormLeaf()
		);
	}

	@SubscribeEvent
	public static void registerSubEntities(RegistryEvent.Register<SubEntityType> event) {
		event.getRegistry().registerAll(
				new SubEntityWind(),
				new SubEntityUfo(),
				new SubEntityLeaf(),
				new SubEntitySunBullet()
		);
	}
}