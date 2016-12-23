package arekkuusu.grimoireofalice.common.core.handler;

import arekkuusu.grimoireofalice.client.ResourceLocations;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.util.SoundEvent;

public class ModSounds {

	public static void preInit() {
		registerSound(ResourceLocations.CAMERA_BEEP);
		registerSound(ResourceLocations.CAMERA_SHOOT);
		registerSound(ResourceLocations.CAMERA_SHUTTER);
		registerSound(ResourceLocations.NEEDLE_SWEEP);
		registerSound(ResourceLocations.SIMPLE_BELL);
		registerSound(ResourceLocations.CRAFTING_SPELL);
		registerSound(ResourceLocations.ORIN_NYAA);
		registerSound(ResourceLocations.POWER_UP);
		registerSound(ResourceLocations.ATTTACK_LONG);
		registerSound(ResourceLocations.HORN);
		registerSound(ResourceLocations.WARP);
		registerSound(ResourceLocations.UFO_IDDLE);
		registerSound(ResourceLocations.UFO_SPAWN);
		registerSound(ResourceLocations.CAUTION);
		registerSound(ResourceLocations.WAVE);
	}

	private static void registerSound(ResourceLocation soundNameIn) {
		GameRegistry.register(new SoundEvent(soundNameIn), soundNameIn);
	}
}
