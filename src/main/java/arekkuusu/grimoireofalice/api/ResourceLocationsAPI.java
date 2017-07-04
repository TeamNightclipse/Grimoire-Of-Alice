package arekkuusu.grimoireofalice.api;

import net.minecraft.util.ResourceLocation;

public class ResourceLocationsAPI {

	//Sounds
	public static final ResourceLocation CAMERA_BEEP = getLocation("camera_beep");
	public static final ResourceLocation CAMERA_SHOOT = getLocation("camera_shoot");
	public static final ResourceLocation CAMERA_SHUTTER = getLocation("camera_shutter");
	public static final ResourceLocation NEEDLE_SWEEP = getLocation("needle_sweep");
	public static final ResourceLocation SIMPLE_BELL = getLocation("simple_bell");
	public static final ResourceLocation CRAFTING_SPELL = getLocation("crafting_spell");
	public static final ResourceLocation ORIN_NYAA = getLocation("orin_nyaa");
	public static final ResourceLocation POWER_UP = getLocation("power_up");
	public static final ResourceLocation ATTTACK_LONG = getLocation("attack_long");
	public static final ResourceLocation HORN = getLocation("horn");
	public static final ResourceLocation WARP = getLocation("warp");
	public static final ResourceLocation UFO_IDDLE = getLocation("ufo_iddle");
	public static final ResourceLocation UFO_SPAWN = getLocation("ufo_spawn");
	public static final ResourceLocation CAUTION = getLocation("caution");
	public static final ResourceLocation WAVE = getLocation("wave");
	public static final ResourceLocation WIND = getLocation("wind");
	public static final ResourceLocation PAGE_TURN = getLocation("page_turn");
	public static final ResourceLocation WING_FLAP = getLocation("wing_flap");
	public static final ResourceLocation ORA = getLocation("ora");

	private static ResourceLocation getLocation(String name) {
		return new ResourceLocation("grimoireofalice", name);
	}
}
