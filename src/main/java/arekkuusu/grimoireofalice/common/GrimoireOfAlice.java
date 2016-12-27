/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common;

import arekkuusu.grimoireofalice.common.core.handler.*;
import arekkuusu.grimoireofalice.common.entity.ModEntity;
import arekkuusu.grimoireofalice.common.event.ModEvents;
import arekkuusu.grimoireofalice.common.core.helper.LogHelper;
import arekkuusu.grimoireofalice.common.item.ModItems;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import arekkuusu.grimoireofalice.common.core.ISidedProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = LibMod.MODID, name = LibMod.MODNAME, version = LibMod.MODVER, dependencies = LibMod.DEPENDENCIES)
public class GrimoireOfAlice {

	public static final GOACreativeTab CREATIVE_TAB = new GOACreativeTab();
	public static boolean danmakuCoreInstalled;

	@Instance(LibMod.MODID)
	public static GrimoireOfAlice instance;

	@SidedProxy(serverSide = LibMod.PROXYCOMMON, clientSide = LibMod.PROXYCLIENT)
	public static ISidedProxy proxy;

	@EventHandler
	public void construct(FMLConstructionEvent event) {
		danmakuCoreInstalled = Loader.isModLoaded("danmakucore");
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ModEvents.preInit();
		ModEntity.preInit();
		ModSounds.preInit();
		MinecraftForge.EVENT_BUS.register(new StopWatchHandler());
		if (ConfigHandler.grimoireOfAlice.worldGen.pointItemsGen)
			MinecraftForge.TERRAIN_GEN_BUS.register(new WorldGenLoot());
		MinecraftForge.TERRAIN_GEN_BUS.register(new WorldGenPlants());
		proxy.preInit(event);
		if (danmakuCoreInstalled) {
			LogHelper.info("Deep Voice, do you have an answer? Doesn't matter, we must know");
		}
		else {
			LogHelper.info("Oh Deep Voice, we want the Answer to the ultimate question of life the universe and everything");
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
		GameRegistry.registerWorldGenerator(new WorldGenOre(), 0);
		ModItems.init();
		ModItems.initOreDictionary();
		ModItems.initFlyingItems();
		if (danmakuCoreInstalled) {
			LogHelper.info("Alright...");
			LogHelper.info("The answer to the ultimate question, of life, the universe and everything, is...");
			LogHelper.info(42);
		}
		else {
			LogHelper.info("Hm, I'd like to think about this, return to this place in exactly seven and a half million years");
		}
	}
}
