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
import arekkuusu.grimoireOfAlice.item.GOAItem;
import arekkuusu.grimoireOfAlice.item.crafting.VanillaCrafting;
import arekkuusu.grimoireOfAlice.lib.LibMod;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = LibMod.MODID, name = LibMod.MODNAME, version = LibMod.MODVER)
public class GrimoireOfAlice {

	@SidedProxy(clientSide = LibMod.PROXYCLIENT, serverSide = LibMod.PROXYSERVER)
	public static ProxyServer proxy;

	@Instance("grimoireofalice")
	public static GrimoireOfAlice instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		GOAItem.preInit();
		GOABlock.preInit();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
		proxy.registerRenders();

		VanillaCrafting.booksAndStrings();
		VanillaCrafting.masks();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {}
}
