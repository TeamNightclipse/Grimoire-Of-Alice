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
import arekkuusu.grimoireofalice.entity.EntityEllyScytheThrowable;
import arekkuusu.grimoireofalice.item.ModItems;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		ModBlocks.init();
		ModItems.init();
	}
	
	public void init(FMLInitializationEvent event) {
		int modEntityID = 0;
		EntityRegistry.registerModEntity(EntityEllyScytheThrowable.class, "PotatoArrow", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
	}
	
	public void postInit(FMLPostInitializationEvent event) {
		
	}

	public void serverAboutToStart(FMLServerAboutToStartEvent event) {

	}

	public void serverStarting(FMLServerStartingEvent event) {
		
	}
	
}
