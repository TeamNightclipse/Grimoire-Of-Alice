package arekkuusu.grimoireofalice.api.sound;

import arekkuusu.grimoireofalice.api.ResourceLocationsAPI;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public final class GrimoireSoundEvents {

	private GrimoireSoundEvents() {}

	public static final SoundEvent CAMERA_BEEP = getRegisteredSound(ResourceLocationsAPI.CAMERA_BEEP);
	public static final SoundEvent CAMERA_SHOOT = getRegisteredSound(ResourceLocationsAPI.CAMERA_SHOOT);
	public static final SoundEvent CAMERA_SHUTTER = getRegisteredSound(ResourceLocationsAPI.CAMERA_SHUTTER);
	public static final SoundEvent NEEDLE_SWEEP = getRegisteredSound(ResourceLocationsAPI.NEEDLE_SWEEP);
	public static final SoundEvent SIMPLE_BELL = getRegisteredSound(ResourceLocationsAPI.SIMPLE_BELL);
	public static final SoundEvent CRAFTING_SPELL = getRegisteredSound(ResourceLocationsAPI.CRAFTING_SPELL);
	public static final SoundEvent ORIN_NYAA = getRegisteredSound(ResourceLocationsAPI.ORIN_NYAA);
	public static final SoundEvent POWER_UP = getRegisteredSound(ResourceLocationsAPI.POWER_UP);
	public static final SoundEvent ATTTACK_LONG = getRegisteredSound(ResourceLocationsAPI.ATTTACK_LONG);
	public static final SoundEvent HORN = getRegisteredSound(ResourceLocationsAPI.HORN);
	public static final SoundEvent WARP = getRegisteredSound(ResourceLocationsAPI.WARP);
	public static final SoundEvent UFO_IDDLE = getRegisteredSound(ResourceLocationsAPI.UFO_IDDLE);
	public static final SoundEvent UFO_SPAWN = getRegisteredSound(ResourceLocationsAPI.UFO_SPAWN);
	public static final SoundEvent CAUTION = getRegisteredSound(ResourceLocationsAPI.CAUTION);
	public static final SoundEvent WAVE = getRegisteredSound(ResourceLocationsAPI.WAVE);

	private static SoundEvent getRegisteredSound(ResourceLocation name) {
		return SoundEvent.REGISTRY.getObject(name);
	}
}
