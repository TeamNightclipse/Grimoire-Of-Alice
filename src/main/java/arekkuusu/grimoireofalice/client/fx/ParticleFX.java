package arekkuusu.grimoireofalice.client.fx;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum ParticleFX implements IStringSerializable {
	SHINMYOUMARU_SPARKLE,
	RED_MIST;

	@Override
	public String getName() {
		return name().toLowerCase(Locale.ROOT);
	}
}
