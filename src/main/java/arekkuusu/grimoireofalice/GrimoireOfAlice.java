package arekkuusu.grimoireofalice;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import arekkuusu.grimoireofalice.lib.LibMod;

@Mod(modid = LibMod.MODID, name = LibMod.MODNAME, version = LibMod.MODVER)
public class GrimoireOfAlice {

	public static final GOACreativeTab CREATIVE_TAB = new GOACreativeTab();
	
	@Instance(LibMod.MODID)
	public static GrimoireOfAlice instance;
	
	@SidedProxy(serverSide = LibMod.PROXYCOMMON, clientSide = LibMod.PROXYCLIENT)
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
	
	@EventHandler
	public void serverStarting(FMLServerAboutToStartEvent event) {
		proxy.serverAboutToStart(event);
	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		proxy.serverStarting(event);
	}

	@EventHandler
	public void serverStopping(FMLServerStoppingEvent event) {
	}

	@EventHandler
	public void handleIMC(FMLInterModComms.IMCEvent event) {
	}
	
}
