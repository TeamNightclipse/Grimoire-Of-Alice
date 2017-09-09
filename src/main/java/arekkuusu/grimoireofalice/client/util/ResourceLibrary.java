/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.util;

import arekkuusu.grimoireofalice.client.util.resource.Location;
import arekkuusu.grimoireofalice.client.util.resource.ResourceBuilder;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static arekkuusu.grimoireofalice.client.util.ResourceLibrary.TextureLocation.GUI;
import static arekkuusu.grimoireofalice.client.util.ResourceLibrary.TextureLocation.MODEL;

public final class ResourceLibrary {

	public static final ResourceLocation BARRIER_TEXTURE = getTexture(MODEL, "entity/barrier");
	public static final ResourceLocation CAMERA_SQUARE_TEXTURE = getTexture(MODEL, "entity/camera_square");
	public static final ResourceLocation DOLL_TEXTURE = getTexture(MODEL, "entity/Doll");
	public static final ResourceLocation DRAGON_JEWEL_TEXTURE = getTexture(MODEL, "entity/magic_circle_0");
	public static final ResourceLocation WHITE = getTexture(MODEL, "entity/white");
	public static final ResourceLocation GRIMOIRE_CIRCLE_TEXTURE = getTexture(MODEL, "entity/magic_circle_1");
	public static final ResourceLocation GRIMOIRE_BOOK = getTexture(MODEL, "entity/magic_book");
	public static final ResourceLocation ORB_TEXTURE = getTexture(MODEL, "entity/orb");
	public static final ResourceLocation NETHER_SOUL_TEXTURE = getTexture(MODEL, "entity/leaf");

	public static final ResourceLocation[] CIRCLE_TEXTURE = ResourceBuilder.toArray(5, "entity/magic_circle_", name ->
		getTexture(MODEL, name)
	);

	public static final ResourceLocation NAZRIN_TEXTURE = getTexture(MODEL, "entity/nazrin_pendulum");
	public static final ResourceLocation WATCH_TEXTURE = getTexture(MODEL, "entity/stop_watch");
	public static final ResourceLocation UNZAN_FIST_TEXTURE = getTexture(MODEL, "entity/unzan_fist");
	public static final ResourceLocation MIRACLE_LANTERN = getTexture(MODEL, "entity/miracle_lantern");
	public static final ResourceLocation FIERY_SWORD_TEXTURE = getTexture(MODEL, "entity/fiery_sword");
	public static final ResourceLocation MIRACLE_CIRCLE = getTexture(MODEL, "entity/miracle_circle");
	public static final ResourceLocation TALISMAN = getTexture(MODEL, "entity/talisman");
	public static final ResourceLocation GAP = getTexture(MODEL, "entity/gap");
	public static final ResourceLocation GAP_COLOR = getTexture(MODEL, "entity/gap_color");
	public static final ResourceLocation KINKAKU_JI_CEILING = getTexture(MODEL, "entity/kinkaku_ji_ceiling");
	public static final ResourceLocation YOUKAI_BOOK = getTexture(MODEL, "entity/youkai_book");

	//Armor
	public static final ResourceLocation SUWAKO_HAT = getTexture(MODEL, "armor/suwako_hat");
	public static final ResourceLocation KAPPA_HAT = getTexture(MODEL, "armor/kappa_hat");
	public static final ResourceLocation MARISA_HAT = getTexture(MODEL, "armor/marisa_hat");
	public static final ResourceLocation SHINMYOUMARU_HAT = getTexture(MODEL, "armor/shinmyoumaru_hat");
	public static final ResourceLocation FOX_MASK = getTexture(MODEL, "armor/fox_mask");
	public static final ResourceLocation FUKU_NO_KAMI_MASK = getTexture(MODEL, "armor/fuku_no_kami_mask");
	public static final ResourceLocation HANNYA_MASK = getTexture(MODEL, "armor/hannya_mask");
	public static final ResourceLocation HYOTTOKO_MASK = getTexture(MODEL, "armor/hyottoko_mask");
	public static final ResourceLocation KOKOROS_MASKS = getTexture(MODEL, "armor/kokoro_masks_layer_1");
	public static final ResourceLocation KOOMOTE_MASK = getTexture(MODEL, "armor/koomote_mask");
	public static final ResourceLocation MASK_OF_HOPE = getTexture(MODEL, "armor/mask_of_hope");
	public static final ResourceLocation MONKEY_MASK = getTexture(MODEL, "armor/monkey_mask");
	public static final ResourceLocation RAIDEN_MASK = getTexture(MODEL, "armor/raiden_mask");
	public static final ResourceLocation UBA_MASK = getTexture(MODEL, "armor/uba_mask");
	public static final ResourceLocation ICHIRIN_UNZAN = getTexture(MODEL, "armor/unzan_model");
	public static final ResourceLocation FIRE_ROBE = getTexture(MODEL, "armor/fire_robe");
	public static final ResourceLocation KANAKO_SHIMENAWA = getTexture(MODEL, "armor/kanako_aura");
	public static final ResourceLocation TOYOSATOMIMI_HAT = getTexture(MODEL, "armor/toyosatomimi_hat");
	public static final ResourceLocation TOYOSATOMIMI_CLOAK = getTexture(MODEL, "armor/toyosatomimi_cloak");
	public static final ResourceLocation UTSUHO_WINGS = getTexture(MODEL, "armor/utsuho_wings");
	public static final ResourceLocation NUCLEAR_BOOTS = getTexture(MODEL, "armor/nuclear_boots");
	public static final ResourceLocation KOISHI_EYE = getTexture(MODEL, "armor/koishi_eye");
	public static final ResourceLocation SATORI_EYE = getTexture(MODEL, "armor/satori_eye");
	public static final ResourceLocation TENSHI_HAT = getTexture(MODEL, "armor/tenshi_hat");

	//GUI
	public static final ResourceLocation POTION_TEXTURES = getTexture(GUI,"potions");
	public static final ResourceLocation[] BOOK_GUI_TEXTURES = ResourceBuilder.toArray(9, "guide_", name ->
			getTexture(GUI, name)
	);

	public static ResourceLocation getLocation(AssetLocation asset, Location location, String name, String suffix) {
		StringBuilder builder = new StringBuilder();
		if(asset != null) builder.append(asset.getPath());
		if(location != null) builder.append(location.getPath());
		builder.append(name).append(suffix);
		return new ResourceLocation(LibMod.MOD_ID, builder.toString());
	}

	public static ModelResourceLocation getModel(String name, String variant) {
		ResourceLocation atlas = getAtlas(null, name);
		return new ModelResourceLocation(atlas, variant);
	}

	public static ResourceLocation getAtlas(Location location, String name) {
		return getLocation(null, location, name, "");
	}

	public static ResourceLocation getTexture(Location location, String name) {
		return getLocation(AssetLocation.TEXTURES, location, name, ".png");
	}

	public enum ModelLocation implements Location {
		BLOCK("block"),
		ITEM("item"),
		OBJ("obj");

		private final String path;

		ModelLocation(String path) {
			this.path = path;
		}

		@Override
		public String getPath() {
			return path + "/";
		}
	}

	public enum TextureLocation implements Location {
		BLOCKS("blocks"),
		ITEMS("items"),
		EFFECT("effect"),
		GUI("gui"),
		MODEL("model");

		private final String path;

		TextureLocation(String path) {
			this.path = path;
		}

		@Override
		public String getPath() {
			return path + "/";
		}
	}

	public enum AssetLocation {
		MODELS("models"),
		TEXTURES("textures");

		private final String path;

		AssetLocation(String path) {
			this.path = path;
		}

		public String getPath() {
			return path + "/";
		}
	}
}
