/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice;

import arekkuusu.grimoireOfAlice.block.GOABlock;
import arekkuusu.grimoireOfAlice.entity.EntityEllyScytheThrowable;
import arekkuusu.grimoireOfAlice.entity.EntityNeedle;
import arekkuusu.grimoireOfAlice.handler.ConfigHandler;
import arekkuusu.grimoireOfAlice.handler.GuiHandler;
import arekkuusu.grimoireOfAlice.helper.LogHelper;
import arekkuusu.grimoireOfAlice.item.GOAItem;
import arekkuusu.grimoireOfAlice.item.crafting.THCrafting;
import arekkuusu.grimoireOfAlice.item.crafting.VanillaCrafting;
import arekkuusu.grimoireOfAlice.lib.LibMod;
import arekkuusu.grimoireOfAlice.tmp.CleanupDone;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;

@CleanupDone
@Mod(modid = LibMod.MODID, name = LibMod.MODNAME, version = LibMod.MODVER, dependencies = "after:THKaguyaMod;")
public class GrimoireOfAlice {

	public static final GOACreativeTab CREATIVE_TAB = new GOACreativeTab(LibMod.MODID);
	public static boolean tohouInstalled; 

	@SidedProxy(clientSide = LibMod.PROXYCLIENT, serverSide = LibMod.PROXYSERVER)
	private static ProxyServer proxy;

	@Instance(LibMod.MODID)
	public static GrimoireOfAlice instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		tohouInstalled = Loader.isModLoaded("THKaguyaMod");
		GOAItem.preInit();
		GOABlock.preInit();
		LogHelper.info("Answer to the ultimate question of life the universe and everything");
		ConfigHandler.setConfig(event.getSuggestedConfigurationFile());
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		int modEntityID = 0;
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		EntityRegistry.registerModEntity(EntityEllyScytheThrowable.class, "PotatoArrow", ++modEntityID, this, 64, 10, true);
		EntityRegistry.registerModEntity(EntityNeedle.class, "PotatoNeedle", ++modEntityID, this, 64, 10, true);
		proxy.init(event);
		proxy.registerRenders();

		VanillaCrafting.booksAndStrings();
		VanillaCrafting.masks();
		if(tohouInstalled){
			LogHelper.info("is 42");
			THCrafting.pointsAndItems();
		}
	}

	public ProxyServer getProxy() {
		return proxy;
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {}

}
