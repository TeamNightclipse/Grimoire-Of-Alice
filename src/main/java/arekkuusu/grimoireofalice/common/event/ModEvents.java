package arekkuusu.grimoireofalice.common.event;

import net.minecraftforge.common.MinecraftForge;

public class ModEvents {

	public static void preInit() {
		MinecraftForge.EVENT_BUS.register(new YukkuriEvent());
		MinecraftForge.EVENT_BUS.register(new CapabilitiesEvent());
	}
}
