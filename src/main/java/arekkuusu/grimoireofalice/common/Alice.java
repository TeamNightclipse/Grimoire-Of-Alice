/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common;

import arekkuusu.grimoireofalice.common.core.ISidedProxy;
import arekkuusu.grimoireofalice.common.core.capability.CapabilityHourai;
import arekkuusu.grimoireofalice.common.core.capability.CapabilityMallet;
import arekkuusu.grimoireofalice.common.core.handler.ModSounds;
import arekkuusu.grimoireofalice.common.core.handler.WorldGenOre;
import arekkuusu.grimoireofalice.common.core.handler.WorldGenPlants;
import arekkuusu.grimoireofalice.common.core.helper.LogHelper;
import arekkuusu.grimoireofalice.common.core.network.PacketHandler;
import arekkuusu.grimoireofalice.common.entity.ModEntity;
import arekkuusu.grimoireofalice.common.event.AchievementEvents;
import arekkuusu.grimoireofalice.common.event.Mappings;
import arekkuusu.grimoireofalice.common.event.ModEvents;
import arekkuusu.grimoireofalice.common.item.ModItems;
import arekkuusu.grimoireofalice.common.item.crafting.AltarRecipes;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLMissingMappingsEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = LibMod.MOD_ID, name = LibMod.MOD_NAME, version = LibMod.MOD_VERSION, acceptedMinecraftVersions = "[1.11.2]")
public class Alice {

	public static final AliceCreativeTab CREATIVE_TAB = new AliceCreativeTab();

	@SidedProxy(clientSide = LibMod.CLIENT_PROXY, serverSide = LibMod.SERVER_PROXY)
	public static ISidedProxy proxy;
	@Instance(LibMod.MOD_ID)
	public static Alice instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.TERRAIN_GEN_BUS.register(new WorldGenPlants());
		proxy.preInit(event);
		ModEvents.preInit();
		ModEntity.preInit();
		ModSounds.preInit();
		PacketHandler.init();
		CapabilityHourai.init();
		CapabilityMallet.init();

		LogHelper.info("Oh Deep Voice, we want the Answer to the ultimate question of life the universe and everything");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		GameRegistry.registerWorldGenerator(new WorldGenOre(), 0);
		proxy.init(event);
		AchievementEvents.init();
		AltarRecipes.init();
		ModItems.init();
		Mappings.init();

		LogHelper.info("Hm, I'd like to think about this, return to this place in exactly seven and a half million years");
	}

	@EventHandler
	public void serverClosing(FMLServerStoppedEvent event) {
		LogHelper.info("Deep Voice, do you have an answer...?");
		LogHelper.info("Yes, but you're not gonna like it...");
		LogHelper.info("Doesn't matter! We must know!");
		LogHelper.info("Alright...");
		LogHelper.info("The answer to the ultimate question, of life, the universe and everything, is...");
		LogHelper.info(42);
	}

	@EventHandler
	public void remapping(FMLMissingMappingsEvent event) {
		Mappings.remap(event.get());
	}
}
