package arekkuusu.grimoireofalice.api;

import net.minecraft.util.ResourceLocation;

public class ResourceLocationsAPI {

	//Sounds
	public static final ResourceLocation CAMERA_BEEP = getLocation("cameraBeep");
	public static final ResourceLocation CAMERA_SHOOT = getLocation("cameraShoot");
	public static final ResourceLocation CAMERA_SHUTTER = getLocation("cameraShutter");
	public static final ResourceLocation NEEDLE_SWEEP = getLocation("needleSweep");
	public static final ResourceLocation SIMPLE_BELL = getLocation("simpleBell");
	public static final ResourceLocation CRAFTING_SPELL = getLocation("craftingSpell");
	public static final ResourceLocation ORIN_NYAA = getLocation("orinNyaa");
	public static final ResourceLocation POWER_UP = getLocation("powerUp");
	public static final ResourceLocation ATTTACK_LONG = getLocation("attackLong");
	public static final ResourceLocation HORN = getLocation("horn");
	public static final ResourceLocation WARP = getLocation("warp");
	public static final ResourceLocation UFO_IDDLE = getLocation("ufoIddle");
	public static final ResourceLocation UFO_SPAWN = getLocation("ufoSpawn");
	public static final ResourceLocation CAUTION = getLocation("caution");
	public static final ResourceLocation WAVE = getLocation("wave");

	private static ResourceLocation getLocation(String name){
		return new ResourceLocation("grimoireofalice", name);
	}
}
