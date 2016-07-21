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
import arekkuusu.grimoireOfAlice.handler.ConfigHandler;
import arekkuusu.grimoireOfAlice.handler.GuiHandler;
import arekkuusu.grimoireOfAlice.item.GOAItem;
import arekkuusu.grimoireOfAlice.item.crafting.VanillaCrafting;
import arekkuusu.grimoireOfAlice.lib.LibMod;
import arekkuusu.grimoireOfAlice.tmp.CleanupDone;
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
@Mod(modid = LibMod.MODID, name = LibMod.MODNAME, version = LibMod.MODVER)
public class GrimoireOfAlice {

	public static final GOACreativeTab CREATIVE_TAB = new GOACreativeTab(LibMod.MODID);

	@SidedProxy(clientSide = LibMod.PROXYCLIENT, serverSide = LibMod.PROXYSERVER)
	private static ProxyServer proxy;

	@Instance(LibMod.MODID)
	public static GrimoireOfAlice instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		GOAItem.preInit();
		GOABlock.preInit();
		ConfigHandler.setConfig(event.getSuggestedConfigurationFile());
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		int modEntityID = 0;
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		EntityRegistry.registerModEntity(EntityEllyScytheThrowable.class, "PotatoArrow", ++modEntityID, this, 64, 10, true);
		proxy.init(event);
		proxy.registerRenders();

		VanillaCrafting.booksAndStrings();
		VanillaCrafting.masks();
	}

	public ProxyServer getProxy() {
		return proxy;
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {}

}
