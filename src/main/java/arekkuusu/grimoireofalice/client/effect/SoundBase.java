package arekkuusu.grimoireofalice.client.effect;

import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.katsstuff.teamnightclipse.mirror.client.helper.ResourceHelperStatic;
import net.minecraft.util.SoundEvent;

public class SoundBase extends SoundEvent {

	public SoundBase(String name) {
		super(ResourceHelperStatic.getSimple(LibMod.MOD_ID, name));
		setRegistryName(LibMod.MOD_ID, name);
	}
}
