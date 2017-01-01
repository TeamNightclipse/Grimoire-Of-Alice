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
	public static final SoundEvent CRAFTING_SPELL = getRegisteredSound("craftingSpell");
	public static final SoundEvent ORIN_NYAA = getRegisteredSound("orinNyaa");
	public static final SoundEvent POWER_UP = getRegisteredSound("powerUp");
	public static final SoundEvent ATTTACK_LONG = getRegisteredSound("attackLong");
	public static final SoundEvent HORN = getRegisteredSound("horn");
	public static final SoundEvent WARP = getRegisteredSound("warp");
	public static final SoundEvent UFO_IDDLE = getRegisteredSound("ufoIddle");
	public static final SoundEvent UFO_SPAWN = getRegisteredSound("ufoSpawn");
	public static final SoundEvent CAUTION = getRegisteredSound("caution");
	public static final SoundEvent WAVE = getRegisteredSound("wave");

	private static SoundEvent getRegisteredSound(String name) {
		return SoundEvent.REGISTRY.getObject(new ResourceLocation("grimoireofalice", name));
	}
}
