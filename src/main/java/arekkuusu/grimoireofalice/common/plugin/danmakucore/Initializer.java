/*
 * This class was created by <Katrix>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.plugin.danmakucore;

import arekkuusu.grimoireofalice.common.lib.LibDanmakuVariantName;
import arekkuusu.grimoireofalice.common.plugin.danmakucore.form.*;
import arekkuusu.grimoireofalice.common.plugin.danmakucore.subentity.*;
import net.katsstuff.danmakucore.data.MovementData;
import net.katsstuff.danmakucore.data.Vector3;
import net.katsstuff.danmakucore.entity.danmaku.DanmakuVariant;
import net.katsstuff.danmakucore.entity.danmaku.form.Form;
import net.katsstuff.danmakucore.entity.danmaku.subentity.SubEntityType;
import net.katsstuff.danmakucore.impl.danmakuvariant.DanmakuVariantGeneric;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class Initializer {

	private static final String DANMAKU_CORE = "danmakucore";

	@SubscribeEvent
	@Optional.Method(modid = DANMAKU_CORE)
	@SideOnly(Side.CLIENT)
	public static void registerForms(RegistryEvent.Register<Form> event) {
		event.getRegistry().registerAll(
				new FormWind(),
				new FormUfo(),
				new FormLeaf(),
				new FormNote()
		);
	}

	@SubscribeEvent
	@Optional.Method(modid = DANMAKU_CORE)
	public static void registerSubEntities(RegistryEvent.Register<SubEntityType> event) {
		event.getRegistry().registerAll(
				new SubEntityWind(),
				new SubEntityNote(),
				new SubEntityUfo(),
				new SubEntityLeaf(),
				new SubEntitySunBullet()
		);
	}

	@SubscribeEvent
	@Optional.Method(modid = DANMAKU_CORE)
	public static void registerVariants(RegistryEvent.Register<DanmakuVariant> event) {
		event.getRegistry().registerAll(
				new DanmakuVariantGeneric(LibDanmakuVariantName.UFO, () -> LibGOAShotData.UFO,
				new MovementData(0.4D, 0.4D, 0D, Vector3.gravity(0))),
				new DanmakuVariantGeneric(LibDanmakuVariantName.NOTE, () -> LibGOAShotData.NOTE,
						new MovementData(0.4D, 0.4D, 0D, Vector3.gravity(0)))
		);
	}
}