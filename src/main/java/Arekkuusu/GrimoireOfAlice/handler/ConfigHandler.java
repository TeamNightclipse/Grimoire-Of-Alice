/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.handler;

import java.io.File;

import arekkuusu.grimoireofalice.lib.libMod;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {

	public static Configuration config;
	
	public static final String RBooks = "RecipeBooks";
	
	public static boolean RecipesBook;

	public static void setConfig(File configFile) {

		config = new Configuration(configFile);
		loadConfig();
		FMLCommonHandler.instance().bus().register(new ChangeListener());
	}

	private static void loadConfig() {
		
		config.addCustomCategoryComment(RBooks, "Enable Easy Mode... EHHHH?!? EASY MODO?!?");
		
		RecipesBook = config.get(RBooks, "Enable Book Recipe", false, "Can players Craft GOA Books?").getBoolean();
		
		if (config.hasChanged()) {
			config.save();
		}
	}
	
	public static class ChangeListener {
		
		@SubscribeEvent
		public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {

			if (event.modID.equalsIgnoreCase(libMod.MODID)) {
				loadConfig();
			}
		}
	}
	
}
