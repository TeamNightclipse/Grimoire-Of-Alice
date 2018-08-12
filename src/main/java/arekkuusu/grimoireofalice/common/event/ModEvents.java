package arekkuusu.grimoireofalice.common.event;

import arekkuusu.grimoireofalice.common.core.handler.ConfigHandler;
import arekkuusu.grimoireofalice.common.core.handler.WorldGenLoot;
import net.minecraftforge.common.MinecraftForge;

public class ModEvents {

	public static void preInit() {
		MinecraftForge.EVENT_BUS.register(new GeneralEvents());
		MinecraftForge.EVENT_BUS.register(new CapabilityEvents());
		MinecraftForge.EVENT_BUS.register(new HouraiEvents());
		MinecraftForge.EVENT_BUS.register(new MalletServerEvent());
		if(ConfigHandler.grimoireOfAlice.worldGen.lootGen) {
			MinecraftForge.EVENT_BUS.register(new WorldGenLoot());
		}
	}
}
