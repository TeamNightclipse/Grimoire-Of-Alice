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
import arekkuusu.grimoireOfAlice.client.entity.proyectile.EntityThrowingExplosiveDoll;
import arekkuusu.grimoireOfAlice.client.entity.proyectile.EntityThrowingNeedleDoll;
import arekkuusu.grimoireOfAlice.helper.LogHelper;
import arekkuusu.grimoireOfAlice.item.GOAItem;
import arekkuusu.grimoireOfAlice.item.crafting.VanillaCrafting;
import arekkuusu.grimoireOfAlice.lib.LibMod;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;

@Mod
(modid = LibMod.MODID,  name = LibMod.MODNAME, version = LibMod.MODVER, dependencies = "required-after:THKaguyaMod;")

public class GrimoireOfAlice {
	
	@SidedProxy (clientSide = LibMod.PROXYCLIENT, serverSide = LibMod.PROXYSERVER)
	
	public static ProxyServer proxy;
	
	@Instance ("goa")
	public static GrimoireOfAlice instance;
	
	public static boolean THKaguyaModDetected;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		
		GOAItem.preInit();
		GOABlock.preInit();
	
		//All Entity Items Go Here
		int modEntityID = 0;
		
		EntityRegistry.registerModEntity(EntityThrowingExplosiveDoll.class, "ThrowingExplosiveDoll", ++modEntityID, this, 64, 10, true);
		EntityRegistry.registerModEntity(EntityThrowingNeedleDoll.class, "ThrowingNeedleDoll", ++modEntityID, this, 64, 10, true);
		
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event){
		
		proxy.RegisterRenders();
		
		VanillaCrafting.BooksAndStrings();
		VanillaCrafting.Blocks();
		VanillaCrafting.ThrowEntity();
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		THKaguyaModDetected = Loader.isModLoaded("THKaguyaMod");
		if (THKaguyaModDetected){
			LogHelper.info("THKaguyaMod Detected" );
			
			
		}
		
	}
	
}
