package arekkuusu.grimoireofalice.common.core.handler;

import arekkuusu.grimoireofalice.client.ResourceLocations;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.util.SoundEvent;

public class ModSounds {

	public static void preInit() {
		GameRegistry.register(new SoundEvent(ResourceLocations.CAMERA_BEEP), ResourceLocations.CAMERA_BEEP);
		GameRegistry.register(new SoundEvent(ResourceLocations.CAMERA_SHOOT), ResourceLocations.CAMERA_SHOOT);
		GameRegistry.register(new SoundEvent(ResourceLocations.CAMERA_SHUTTER), ResourceLocations.CAMERA_SHUTTER);
	}
}
