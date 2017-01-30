package arekkuusu.grimoireofalice.common.event;

import arekkuusu.grimoireofalice.common.core.handler.ConfigHandler;
import arekkuusu.grimoireofalice.common.core.handler.StopWatchHandler;
import arekkuusu.grimoireofalice.common.core.handler.WorldGenLoot;
import net.minecraftforge.common.MinecraftForge;

public class ModEvents {

	public static void preInit() {
		MinecraftForge.EVENT_BUS.register(new YukkuriEvent());
		MinecraftForge.EVENT_BUS.register(new CapabilitiesEvent());
		if (ConfigHandler.grimoireOfAlice.features.timeStopEffect) {
			MinecraftForge.EVENT_BUS.register(new StopWatchHandler());
		}
		MinecraftForge.EVENT_BUS.register(new HouraiEvents());
		if (ConfigHandler.grimoireOfAlice.worldGen.lootGen)
			MinecraftForge.EVENT_BUS.register(new WorldGenLoot());
	}
}
