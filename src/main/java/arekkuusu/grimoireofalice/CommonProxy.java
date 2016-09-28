/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice;

import arekkuusu.grimoireofalice.block.ModBlocks;
import arekkuusu.grimoireofalice.entity.*;
//import arekkuusu.grimoireofalice.entity.EntityEllyScytheThrowable;
import arekkuusu.grimoireofalice.handler.WorldGenPlants;
import arekkuusu.grimoireofalice.item.ModItems;
import arekkuusu.grimoireofalice.item.crafting.VanillaCrafting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class CommonProxy {

	@SuppressWarnings("UnusedAssignment")
	public void preInit(FMLPreInitializationEvent event) {
		ModBlocks.init();
		ModItems.init();

		int modEntityID = 0;
		EntityRegistry.registerModEntity(EntityLeaf.class, "Leaf", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityAnimalShot.class, "Shot", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityNote.class, "Shot", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityNazrinPendulum.class, "Pendulum", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityWind.class, "Shot", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityMagicCircle.class, "Spell", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityNeedle.class, "Spell", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityDragonJewel.class, "Beam", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		//EntityRegistry.registerModEntity(EntityEllyScytheThrowable.class, "ellyScythe", modEntityID++, GrimoireOfAlice.instance, 64, 10, true);
		MinecraftForge.TERRAIN_GEN_BUS.register(new WorldGenPlants());
	}
	
	public void init(FMLInitializationEvent event) {
		VanillaCrafting.booksAndStrings();
		VanillaCrafting.masks();
	}
	
	public void postInit(FMLPostInitializationEvent event) {
		
	}

	public void serverAboutToStart(FMLServerAboutToStartEvent event) {

	}

	public void serverStarting(FMLServerStartingEvent event) {
		
	}
	
}
