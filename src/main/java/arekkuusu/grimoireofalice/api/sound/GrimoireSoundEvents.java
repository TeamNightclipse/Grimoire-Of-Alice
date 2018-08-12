package arekkuusu.grimoireofalice.api.sound;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public final class GrimoireSoundEvents {

	public static final SoundEvent CAMERA_BEEP = getRegisteredSound("camera_beep");
	public static final SoundEvent CAMERA_SHOOT = getRegisteredSound("camera_shoot");
	public static final SoundEvent CAMERA_SHUTTER = getRegisteredSound("camera_shutter");
	public static final SoundEvent NEEDLE_SWEEP = getRegisteredSound("needle_sweep");
	public static final SoundEvent SIMPLE_BELL = getRegisteredSound("simple_bell");
	public static final SoundEvent CRAFTING_SPELL = getRegisteredSound("crafting_spell");
	public static final SoundEvent ORIN_NYAA = getRegisteredSound("orin_nyaa");
	public static final SoundEvent POWER_UP = getRegisteredSound("power_up");
	public static final SoundEvent ATTTACK_LONG = getRegisteredSound("attack_long");
	public static final SoundEvent HORN = getRegisteredSound("horn");
	public static final SoundEvent WARP = getRegisteredSound("warp");
	public static final SoundEvent UFO_IDDLE = getRegisteredSound("ufo_iddle");
	public static final SoundEvent UFO_SPAWN = getRegisteredSound("ufo_spawn");
	public static final SoundEvent CAUTION = getRegisteredSound("caution");
	public static final SoundEvent WAVE = getRegisteredSound("wave");
	public static final SoundEvent WIND = getRegisteredSound("wind");
	public static final SoundEvent PAGE_TURN = getRegisteredSound("page_turn");
	public static final SoundEvent WING_FLAP = getRegisteredSound("wing_flap");
	public static final SoundEvent ORA = getRegisteredSound("ora");

	private GrimoireSoundEvents() {
	}

	private static SoundEvent getRegisteredSound(String name) {
		return SoundEvent.REGISTRY.getObject(new ResourceLocation("grimoireofalice", name));
	}
}
