/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.handler;

import java.io.File;

import arekkuusu.grimoireOfAlice.lib.LibMod;
import arekkuusu.grimoireOfAlice.tmp.CleanupDone;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

@CleanupDone
public class ConfigHandler {

	private static Configuration config;
	
	public static boolean maskRecipes;
	public static boolean pointItemRecipes;

	public static void setConfig(File configFile) {
		config = new Configuration(configFile);
		loadConfig();
		FMLCommonHandler.instance().bus().register(new ChangeListener());
	}

	private static void loadConfig() {

		config.addCustomCategoryComment("Grimoire of Alice", "Don't change this if you don't know what you are doing");
		maskRecipes = config.get("Masks", "Masks Recipes", true, "Can players get Kokoro's Masks?").getBoolean();
		pointItemRecipes = config.get("Points", "Point Item Recipes (Only works if thKaguyaMod installed)", true, "Can players get Point Items?").getBoolean();
		if(config.hasChanged()) {
			config.save();
		}
	}

	@CleanupDone
	public static class ChangeListener {

		@SubscribeEvent
		public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
			if(event.modID.equalsIgnoreCase(LibMod.MODID)) {
				loadConfig();
			}
		}
	}
}
