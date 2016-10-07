/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice;

import arekkuusu.grimoireofalice.handler.ConfigHandler;
import arekkuusu.grimoireofalice.helper.LogHelper;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = LibMod.MODID, name = LibMod.MODNAME, version = LibMod.MODVER, dependencies = "after:danmakucore")
public class GrimoireOfAlice {

	public static final GOACreativeTab CREATIVE_TAB = new GOACreativeTab();
	public static boolean danmakuCoreInstalled;
	
	@Instance(LibMod.MODID)
	public static GrimoireOfAlice instance;
	
	@SidedProxy(serverSide = LibMod.PROXYCOMMON, clientSide = LibMod.PROXYCLIENT)
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		danmakuCoreInstalled = Loader.isModLoaded("danamkucore");

		proxy.preInit(event);
		LogHelper.info("Answer to the ultimate question of life the universe and everything");
		ConfigHandler.setConfig(event.getSuggestedConfigurationFile());
	}


	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
		if(danmakuCoreInstalled){
			LogHelper.info("is 42");
		} else {
			LogHelper.info("is her");
		}
	}
}
