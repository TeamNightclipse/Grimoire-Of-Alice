package arekkuusu.grimoireofalice.api.sound;

import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public final class GrimoireSoundEvents {

	private GrimoireSoundEvents() {}

	public static final SoundEvent CAMERA_BEEP = getRegisteredSound("cameraBeep");
	public static final SoundEvent CAMERA_SHOOT = getRegisteredSound("cameraShoot");
	public static final SoundEvent CAMERA_SHUTTER = getRegisteredSound("cameraShutter");
	public static final SoundEvent NEEDLE_SWEEP = getRegisteredSound("needleSweep");
	public static final SoundEvent SIMPLE_BELL = getRegisteredSound("simpleBell");
	public static final SoundEvent CRAFTING_SPELL = getRegisteredSound("chainLightning");

	private static SoundEvent getRegisteredSound(String name) {
		return SoundEvent.REGISTRY.getObject(new ResourceLocation(LibMod.MODID, name));
	}
}
