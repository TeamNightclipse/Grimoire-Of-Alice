package arekkuusu.grimoireofalice.common.core.capability;

import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityHourai {

	public static void init() {
		CapabilityManager.INSTANCE.register(IHouraiCapability.class, new HouraiCapabilityStorage(), DefaultHouraiCapability::new);
	}
}
