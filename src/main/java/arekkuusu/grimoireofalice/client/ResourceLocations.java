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
	public static final ResourceLocation BARRIER_TEXTURE = getLocation("textures/models/entities/Barrier.png");
	public static final ResourceLocation CAMERA_SQUARE_TEXTURE = getLocation("textures/models/entities/CameraSquare.png");
	public static final ResourceLocation DOLL_TEXTURE = getLocation("textures/models/entities/Doll.png");
	public static final ResourceLocation DRAGON_JEWEL_TEXTURE = getLocation("textures/models/entities/MagicCircle_0.png");
	public static final ResourceLocation ELLY_SCYTHE_TEXTURE = getLocation("textures/models/entities/Leaf.png");
	public static final ResourceLocation GRIMOIRE_CIRCLE_TEXTURE = getLocation("textures/models/entities/MagicCircle_1.png");
	public static final ResourceLocation GRIMOIRE_BOOK = getLocation("textures/models/entities/MagicBook.png");
	public static final ResourceLocation ORB_TEXTURE = getLocation("textures/models/entities/Orb.png");
	public static final ResourceLocation NETHER_SOUL_TEXTURE = getLocation("textures/models/entities/Leaf.png");

	public static final ResourceLocation CIRCLE_TEXTURE[] = {
			getLocation("textures/models/entities/MagicCircle_0.png"),
			getLocation("textures/models/entities/MagicCircle_1.png"),
			getLocation("textures/models/entities/MagicCircle_2.png"),
			getLocation("textures/models/entities/MagicCircle_3.png"),
			getLocation("textures/models/entities/MagicCircle_4.png"),
			getLocation("textures/models/entities/MagicCircle_5.png")
	};

	public static final ResourceLocation NAZRIN_TEXTURE = getLocation("textures/models/entities/NazrinPendulum.png");
	public static final ResourceLocation WATCH_TEXTURE = getLocation("textures/models/entities/StopWatch.png");
	public static final ResourceLocation UNZAN_FIST_TEXTURE = getLocation("textures/models/entities/UnzanFist.png");
	public static final ResourceLocation MIRACLE_LANTERN = getLocation("textures/models/entities/MiracleLantern.png");
	public static final ResourceLocation FIERY_SWORD_TEXTURE = getLocation("textures/models/entities/FierySword.png");

	//Armor
	public static final ResourceLocation SUWAKO_HAT = getLocation("textures/models/armor/suwakohat.png");
	public static final ResourceLocation KAPPA_HAT = getLocation("textures/models/armor/kappahat.png");
	public static final ResourceLocation MARISA_HAT = getLocation("textures/models/armor/marisahat.png");
	public static final ResourceLocation SHINMYOUMARU_HAT = getLocation("textures/models/armor/shinmyoumaruHat.png");
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

	public static final ResourceLocation AURA_MOKOU = getLocation("textures/models/armor/mokou_aura.png");
	public static final ResourceLocation AURA_BYAKUREN = getLocation("textures/models/armor/byakuren_aura.png");

	//Particle
	public static final ResourceLocation SHINMYOUMARU_SPARKLE = getLocation("particle/Shinmyoumaru");
	public static final ResourceLocation RED_MIST = getLocation("particle/RedMist");
	public static final ResourceLocation NEEDLE_SWING = getLocation("textures/particle/NeedleSwing.png");
	public static final ResourceLocation RED_GAS = getLocation("particle/RedGas");
	public static final ResourceLocation NETHER_FIRE = getLocation("particle/NetherFire");

	//Structures
	public static final ResourceLocation STRUCTURE_BOULDER = getLocation("boulder");

	private static ResourceLocation getLocation(String name){
		return new ResourceLocation(LibMod.MODID, name);
	}
}
