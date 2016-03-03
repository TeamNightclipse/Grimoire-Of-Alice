/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimore Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package Arekkuusu.GrimoireOfAlice;

import Arekkuusu.GrimoireOfAlice.handler.ChestGenHandler;
import Arekkuusu.GrimoireOfAlice.handler.ConfigHandler;
import Arekkuusu.GrimoireOfAlice.item.GOAItem;
import Arekkuusu.GrimoireOfAlice.item.crafting.CraftingVanilla;
import Arekkuusu.GrimoireOfAlice.lib.LibMod;
import Arekkuusu.GrimoireOfAlice.util.LogHelper;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = LibMod.MODID, name = LibMod.NAME, version = LibMod.VERSION, guiFactory = LibMod.GUI_FACTORY_CLASS) // dependencies = "required-after:THKaguyaMod;")
public class GrimoireOfAlice {

	public static boolean THKaguyaModInstalled;

	@Instance(LibMod.MODID)
	public static GrimoireOfAlice instance;

	@SidedProxy(clientSide = LibMod.CLIENT_PROXY_CLASS, serverSide = LibMod.SERVER_PROXY_CLASS)
	public static CommonProxy Proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		//Config handlingBAKA
		ConfigHandler.setConfig(event.getSuggestedConfigurationFile());

		THKaguyaModInstalled = Loader.isModLoaded("THKaguyaMod");

		GOAItem.preInit();
		//*GOABlock.preInit();
		//*GOATH.preInit();
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {

		Proxy.registerRenderers();

		CraftingVanilla.misc();
		CraftingVanilla.Table();

		if(ConfigHandler.BookEnabled) {
			LogHelper.warn("Books enabled, not yet supported");
			ChestGenHandler.init();
		}
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		//
	}
}
