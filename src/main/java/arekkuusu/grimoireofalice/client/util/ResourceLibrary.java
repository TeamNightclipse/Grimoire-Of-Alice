/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.util;

import arekkuusu.grimoireofalice.client.util.resource.ResourceBuilder;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.katsstuff.teamnightclipse.mirror.client.helper.*;
import net.minecraft.util.ResourceLocation;

public final class ResourceLibrary {

	//Entity
	public static final ResourceLocation BARRIER_TEXTURE = getTexture(TextureLocation.Model(), "entity/barrier");
	public static final ResourceLocation CAMERA_SQUARE_TEXTURE = getTexture(TextureLocation.Model(), "entity/camera_square");
	public static final ResourceLocation DOLL_TEXTURE = getTexture(TextureLocation.Model(), "entity/doll");
	public static final ResourceLocation DRAGON_JEWEL_TEXTURE = getTexture(TextureLocation.Model(), "entity/magic_circle_0");
	public static final ResourceLocation WHITE = getTexture(TextureLocation.Model(), "entity/white");
	public static final ResourceLocation ORB_TEXTURE = getTexture(TextureLocation.Model(), "entity/orb");
	public static final ResourceLocation NETHER_SOUL_TEXTURE = getTexture(TextureLocation.Model(), "entity/leaf");
	public static final ResourceLocation[] CIRCLE_TEXTURE = ResourceBuilder.toArray(5, "entity/magic_circle_", name ->
			getTexture(TextureLocation.Model(), name)
	);
	public static final ResourceLocation NAZRIN_TEXTURE = getTexture(TextureLocation.Model(), "entity/nazrin_pendulum");
	public static final ResourceLocation WATCH_TEXTURE = getTexture(TextureLocation.Model(), "entity/stop_watch");
	public static final ResourceLocation UNZAN_FIST_TEXTURE = getTexture(TextureLocation.Model(), "entity/unzan_fist");
	public static final ResourceLocation MIRACLE_LANTERN = getTexture(TextureLocation.Model(), "entity/miracle_lantern");
	public static final ResourceLocation FIERY_SWORD_TEXTURE = getTexture(TextureLocation.Model(), "entity/fiery_sword");
	public static final ResourceLocation MIRACLE_CIRCLE = getTexture(TextureLocation.Model(), "entity/miracle_circle");
	public static final ResourceLocation TALISMAN = getTexture(TextureLocation.Model(), "entity/talisman");
	public static final ResourceLocation GAP = getTexture(TextureLocation.Model(), "entity/gap");
	public static final ResourceLocation GAP_COLOR = getTexture(TextureLocation.Model(), "entity/gap_color");
	public static final ResourceLocation KINKAKU_JI_CEILING = getTexture(TextureLocation.Model(), "entity/kinkaku_ji_ceiling");
	public static final ResourceLocation YOUKAI_BOOK = getTexture(TextureLocation.Model(), "entity/youkai_book");
	//Armor
	public static final ResourceLocation SUWAKO_HAT = getTexture(TextureLocation.Model(), "armor/suwako_hat");
	public static final ResourceLocation KAPPA_HAT = getTexture(TextureLocation.Model(), "armor/kappa_hat");
	public static final ResourceLocation MARISA_HAT = getTexture(TextureLocation.Model(), "armor/marisa_hat");
	public static final ResourceLocation SHINMYOUMARU_HAT = getTexture(TextureLocation.Model(), "armor/shinmyoumaru_hat");
	public static final ResourceLocation FOX_MASK = getTexture(TextureLocation.Model(), "armor/fox_mask");
	public static final ResourceLocation FUKU_NO_KAMI_MASK = getTexture(TextureLocation.Model(), "armor/fuku_no_kami_mask");
	public static final ResourceLocation HANNYA_MASK = getTexture(TextureLocation.Model(), "armor/hannya_mask");
	public static final ResourceLocation HYOTTOKO_MASK = getTexture(TextureLocation.Model(), "armor/hyottoko_mask");
	public static final ResourceLocation KOKOROS_MASKS = getTexture(TextureLocation.Model(), "armor/kokoro_masks");
	public static final ResourceLocation KOOMOTE_MASK = getTexture(TextureLocation.Model(), "armor/koomote_mask");
	public static final ResourceLocation MASK_OF_HOPE = getTexture(TextureLocation.Model(), "armor/mask_of_hope");
	public static final ResourceLocation MONKEY_MASK = getTexture(TextureLocation.Model(), "armor/monkey_mask");
	public static final ResourceLocation RAIDEN_MASK = getTexture(TextureLocation.Model(), "armor/raiden_mask");
	public static final ResourceLocation UBA_MASK = getTexture(TextureLocation.Model(), "armor/uba_mask");
	public static final ResourceLocation ICHIRIN_UNZAN = getTexture(TextureLocation.Model(), "armor/ichirin_unzan");
	public static final ResourceLocation FIRE_ROBE = getTexture(TextureLocation.Model(), "armor/fire_robe");
	public static final ResourceLocation KANAKO_SHIMENAWA = getTexture(TextureLocation.Model(), "armor/kanako_shimenawa");
	public static final ResourceLocation TOYOSATOMIMI_HAT = getTexture(TextureLocation.Model(), "armor/toyosatomimi_hat");
	public static final ResourceLocation TOYOSATOMIMI_CLOAK = getTexture(TextureLocation.Model(), "armor/toyosatomimi_cloak");
	public static final ResourceLocation UTSUHO_WINGS = getTexture(TextureLocation.Model(), "armor/utsuho_wings");
	public static final ResourceLocation NUCLEAR_BOOTS = getTexture(TextureLocation.Model(), "armor/nuclear_boots");
	public static final ResourceLocation KOISHI_EYE = getTexture(TextureLocation.Model(), "armor/koishi_eye");
	public static final ResourceLocation SATORI_EYE = getTexture(TextureLocation.Model(), "armor/satori_eye");
	public static final ResourceLocation TENSHI_HAT = getTexture(TextureLocation.Model(), "armor/tenshi_hat");
	//Shader
	public static final ResourceLocation BRIGHT_SHADER = getShader(ShaderLocation.Program(), "bright");
	//GUI
	public static final ResourceLocation POTION_TEXTURES = getTexture(TextureLocation.Gui(), "potions");
	public static final ResourceLocation[] BOOK_GUI_TEXTURES = ResourceBuilder.toArray(10, "guide/guide_", name ->
			getTexture(TextureLocation.Gui(), name)
	);

	public static ResourceLocation getEffect(String name) {
		return ResourceHelperStatic.getAtlas(LibMod.MOD_ID, TextureLocation.Effect(), name);
	}

	private static ResourceLocation getTexture(Location location, String name) {
		return ResourceHelperStatic.getTexture(LibMod.MOD_ID, location, name);
	}

	private static ResourceLocation getShader(Location location, String name) {
		return ResourceHelperStatic.getLocation(LibMod.MOD_ID, AssetLocation.Shaders(), location, name, "");
	}
}
