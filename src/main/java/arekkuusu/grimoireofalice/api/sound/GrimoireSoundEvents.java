package arekkuusu.grimoireofalice.api.sound;

import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public final class GrimoireSoundEvents {

	private GrimoireSoundEvents() {}

	public static final SoundEvent CAMERA_BEEP = getRegisteredSound("cameraBeep");
	public static final SoundEvent CAMERA_SHOOT = getRegisteredSound("cameraShoot");
	public static final SoundEvent CAMERA_SHUTTER = getRegisteredSound("cameraShutter");

	private static SoundEvent getRegisteredSound(String name) {
		return SoundEvent.REGISTRY.getObject(new ResourceLocation(LibMod.MODID, name));
	}
}
