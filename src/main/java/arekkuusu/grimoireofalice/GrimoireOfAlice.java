/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice;

import arekkuusu.grimoireofalice.helper.LogHelper;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraftforge.fml.common.Loader;
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

@Mod(modid = LibMod.MODID, name = LibMod.MODNAME, version = LibMod.MODVER)
public class GrimoireOfAlice {

	public static final GOACreativeTab CREATIVE_TAB = new GOACreativeTab();
	public static boolean tohouInstalled; 
	
	@Instance(LibMod.MODID)
	public static GrimoireOfAlice instance;
	
	@SidedProxy(serverSide = LibMod.PROXYCOMMON, clientSide = LibMod.PROXYCLIENT)
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		tohouInstalled = Loader.isModLoaded("KatrixTouhouMod");
		proxy.preInit(event);
		LogHelper.info("Answer to the ultimate question of life the universe and everything");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
		if(tohouInstalled){
			LogHelper.info("is 42");
		} else {
			LogHelper.info("is her");
		}
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
