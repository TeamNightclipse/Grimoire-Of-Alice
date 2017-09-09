/*
 * This class was created by <Katrix>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.danmakucore;

import arekkuusu.grimoireofalice.common.danmakucore.form.FormLeaf;
import arekkuusu.grimoireofalice.common.danmakucore.form.FormUfo;
import arekkuusu.grimoireofalice.common.danmakucore.form.FormWind;
import arekkuusu.grimoireofalice.common.danmakucore.subentity.SubEntityLeaf;
import arekkuusu.grimoireofalice.common.danmakucore.subentity.SubEntitySunBullet;
import arekkuusu.grimoireofalice.common.danmakucore.subentity.SubEntityUfo;
import arekkuusu.grimoireofalice.common.danmakucore.subentity.SubEntityWind;
import arekkuusu.grimoireofalice.common.lib.LibDanmakuVariantName;
import net.katsstuff.danmakucore.data.MovementData;
import net.katsstuff.danmakucore.entity.danmaku.DanmakuVariant;
import net.katsstuff.danmakucore.entity.danmaku.form.Form;
import net.katsstuff.danmakucore.entity.danmaku.subentity.SubEntityType;
import net.katsstuff.danmakucore.impl.danmakuvariant.DanmakuVariantGeneric;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class Initializer {

	private static final String DANMAKU_CORE = "danmakucore";

	@SubscribeEvent
	@Optional.Method(modid = DANMAKU_CORE)
	public static void registerForms(RegistryEvent.Register<Form> event) {
		event.getRegistry().registerAll(
				new FormWind(),
				new FormUfo(),
				new FormLeaf()
		);
	}

	@SubscribeEvent
	@Optional.Method(modid = DANMAKU_CORE)
	public static void registerSubEntities(RegistryEvent.Register<SubEntityType> event) {
		event.getRegistry().registerAll(
				new SubEntityWind(),
				new SubEntityUfo(),
				new SubEntityLeaf(),
				new SubEntitySunBullet()
		);
	}

	@SubscribeEvent
	@Optional.Method(modid = DANMAKU_CORE)
	public static void registerVariants(RegistryEvent.Register<DanmakuVariant> event) {
		event.getRegistry().registerAll(
				new DanmakuVariantGeneric(LibDanmakuVariantName.UFO, () -> LibGOAShotData.UFO, MovementData.constant(0.4D)),
				new DanmakuVariantGeneric(LibDanmakuVariantName.SUN, () -> LibGOAShotData.SUN, MovementData.constant(0.2D))
		);
	}
}