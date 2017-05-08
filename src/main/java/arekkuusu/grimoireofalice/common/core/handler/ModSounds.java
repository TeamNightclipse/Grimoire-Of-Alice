package arekkuusu.grimoireofalice.common.core.handler;

import arekkuusu.grimoireofalice.api.ResourceLocationsAPI;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModSounds {

	public static void preInit() {
		registerSound(ResourceLocationsAPI.CAMERA_BEEP);
		registerSound(ResourceLocationsAPI.CAMERA_SHOOT);
		registerSound(ResourceLocationsAPI.CAMERA_SHUTTER);
		registerSound(ResourceLocationsAPI.NEEDLE_SWEEP);
		registerSound(ResourceLocationsAPI.SIMPLE_BELL);
		registerSound(ResourceLocationsAPI.CRAFTING_SPELL);
		registerSound(ResourceLocationsAPI.ORIN_NYAA);
		registerSound(ResourceLocationsAPI.POWER_UP);
		registerSound(ResourceLocationsAPI.ATTTACK_LONG);
		registerSound(ResourceLocationsAPI.HORN);
		registerSound(ResourceLocationsAPI.WARP);
		registerSound(ResourceLocationsAPI.UFO_IDDLE);
		registerSound(ResourceLocationsAPI.UFO_SPAWN);
		registerSound(ResourceLocationsAPI.CAUTION);
		registerSound(ResourceLocationsAPI.WAVE);
		registerSound(ResourceLocationsAPI.PAGE_TURN);
		registerSound(ResourceLocationsAPI.WING_FLAP);
        registerSound(ResourceLocationsAPI.ORA);
	}

	private static void registerSound(ResourceLocation soundNameIn) {
		GameRegistry.register(new SoundEvent(soundNameIn), soundNameIn);
	}
}
