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
	}

	private static void registerSound(ResourceLocation soundNameIn) {
		GameRegistry.register(new SoundEvent(soundNameIn), soundNameIn);
	}
}
