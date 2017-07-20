/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client;

import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.minecraft.util.ResourceLocation;

public final class ResourceLocations {

	//Entity
	public static final ResourceLocation BARRIER_TEXTURE = getLocation("textures/models/entities/barrier.png");
	public static final ResourceLocation CAMERA_SQUARE_TEXTURE = getLocation("textures/models/entities/camera_square.png");
	public static final ResourceLocation DOLL_TEXTURE = getLocation("textures/models/entities/Doll.png");
	public static final ResourceLocation DRAGON_JEWEL_TEXTURE = getLocation("textures/models/entities/magic_circle_0.png");
	public static final ResourceLocation WHITE = getLocation("textures/models/entities/white.png");
	public static final ResourceLocation GRIMOIRE_CIRCLE_TEXTURE = getLocation("textures/models/entities/magic_circle_1.png");
	public static final ResourceLocation GRIMOIRE_BOOK = getLocation("textures/models/entities/magic_book.png");
	public static final ResourceLocation ORB_TEXTURE = getLocation("textures/models/entities/orb.png");
	public static final ResourceLocation NETHER_SOUL_TEXTURE = getLocation("textures/models/entities/leaf.png");

	public static final ResourceLocation CIRCLE_TEXTURE[] = {
			getLocation("textures/models/entities/magic_circle_0.png"),
			getLocation("textures/models/entities/magic_circle_1.png"),
			getLocation("textures/models/entities/magic_circle_2.png"),
			getLocation("textures/models/entities/magic_circle_3.png"),
			getLocation("textures/models/entities/magic_circle_4.png"),
			getLocation("textures/models/entities/magic_circle_5.png")
	};

	public static final ResourceLocation NAZRIN_TEXTURE = getLocation("textures/models/entities/nazrin_pendulum.png");
	public static final ResourceLocation WATCH_TEXTURE = getLocation("textures/models/entities/stop_watch.png");
	public static final ResourceLocation UNZAN_FIST_TEXTURE = getLocation("textures/models/entities/unzan_fist.png");
	public static final ResourceLocation MIRACLE_LANTERN = getLocation("textures/models/entities/miracle_lantern.png");
	public static final ResourceLocation FIERY_SWORD_TEXTURE = getLocation("textures/models/entities/fiery_sword.png");
	public static final ResourceLocation MIRACLE_CIRCLE = getLocation("textures/models/entities/miracle_circle.png");
	public static final ResourceLocation TALISMAN = getLocation("textures/models/entities/talisman.png");
	public static final ResourceLocation GAP = getLocation("textures/models/entities/gap.png");
	public static final ResourceLocation GAP_COLOR = getLocation("textures/models/entities/gap_color.png");
	public static final ResourceLocation KINKAKU_JI_CEILING = getLocation("textures/models/entities/kinkaku_ji_ceiling.png");
	public static final ResourceLocation YOUKAI_BOOK = getLocation("textures/models/entities/youkai_book.png");

	//Armor
	public static final ResourceLocation SUWAKO_HAT = getLocation("textures/models/armor/suwakohat.png");
	public static final ResourceLocation KAPPA_HAT = getLocation("textures/models/armor/kappahat.png");
	public static final ResourceLocation MARISA_HAT = getLocation("textures/models/armor/marisahat.png");
	public static final ResourceLocation SHINMYOUMARU_HAT = getLocation("textures/models/armor/shinmyoumaru_hat.png");
	public static final ResourceLocation FOX_MASK = getLocation("textures/models/armor/foxmask.png");
	public static final ResourceLocation FUKU_NO_KAMI_MASK = getLocation("textures/models/armor/fukunokamimask.png");
	public static final ResourceLocation HANNYA_MASK = getLocation("textures/models/armor/hannyamask.png");
	public static final ResourceLocation HYOTTOKO_MASK = getLocation("textures/models/armor/hyottokomask.png");
	public static final ResourceLocation KOKOROS_MASKS = getLocation("textures/models/armor/kokorosmasks_layer_1.png");
	public static final ResourceLocation KOOMOTE_MASK = getLocation("textures/models/armor/koomotemask.png");
	public static final ResourceLocation MASK_OF_HOPE = getLocation("textures/models/armor/maskofhope.png");
	public static final ResourceLocation MONKEY_MASK = getLocation("textures/models/armor/monkeymask.png");
	public static final ResourceLocation RAIDEN_MASK = getLocation("textures/models/armor/raidenMask.png");
	public static final ResourceLocation UBA_MASK = getLocation("textures/models/armor/ubamask.png");
	public static final ResourceLocation ICHIRIN_UNZAN = getLocation("textures/models/armor/ichirin_aura.png");
	public static final ResourceLocation FIRE_ROBE = getLocation("textures/models/armor/firerobe.png");
	public static final ResourceLocation KANAKO_SHIMENAWA = getLocation("textures/models/armor/kanako_aura.png");
	public static final ResourceLocation TOYOSATOMIMI_HAT = getLocation("textures/models/armor/toyosatomimi_hat.png");
	public static final ResourceLocation TOYOSATOMIMI_CLOAK = getLocation("textures/models/armor/toyosatomimi_cloack.png");
	public static final ResourceLocation UTSUHO_WINGS = getLocation("textures/models/armor/utsuho_wings.png");
	public static final ResourceLocation NUCLEAR_BOOTS = getLocation("textures/models/armor/nuclearboots.png");
	public static final ResourceLocation KOISHI_EYE = getLocation("textures/models/armor/koishi_eye.png");
	public static final ResourceLocation SATORI_EYE = getLocation("textures/models/armor/satori_eye.png");
	public static final ResourceLocation TENSHI_HAT = getLocation("textures/models/armor/tenshihat.png");

	public static final ResourceLocation AURA_MOKOU = getLocation("textures/models/armor/mokou_aura.png");
	public static final ResourceLocation AURA_BYAKUREN = getLocation("textures/models/armor/byakuren_aura.png");

	//Particle
	public static final ResourceLocation SHINMYOUMARU_SPARKLE = getLocation("particle/shinmyoumaru");
	public static final ResourceLocation RED_MIST = getLocation("particle/red_mist");
	public static final ResourceLocation NEEDLE_SWING = getLocation("textures/particle/needle_swing.png");
	public static final ResourceLocation RED_GAS = getLocation("particle/red_gas");
	public static final ResourceLocation NETHER_FIRE = getLocation("particle/nether_fire");

	//GUI
	public static final ResourceLocation POTION_TEXTURES = getLocation("textures/gui/potions.png");
	public static final ResourceLocation BOOK_GUI_TEXTURES[] = {
			getLocation("textures/gui/guide/guide0.png"),
			getLocation("textures/gui/guide/guide1.png"),
			getLocation("textures/gui/guide/guide2.png"),
			getLocation("textures/gui/guide/guide3.png"),
			getLocation("textures/gui/guide/guide4.png"),
			getLocation("textures/gui/guide/guide5.png"),
			getLocation("textures/gui/guide/guide6.png"),
			getLocation("textures/gui/guide/guide7.png"),
			getLocation("textures/gui/guide/guide8.png"),
			getLocation("textures/gui/guide/guide9.png")
	};

	//Structures
	public static final ResourceLocation STRUCTURE_BOULDER = getLocation("boulder");

	private static ResourceLocation getLocation(String name) {
		return new ResourceLocation(LibMod.MODID, name);
	}
}
