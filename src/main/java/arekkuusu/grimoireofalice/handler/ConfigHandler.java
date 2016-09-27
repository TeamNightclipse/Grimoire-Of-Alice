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

import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigHandler {

	private static Configuration config;
	
	public static boolean maskRecipes;
	public static boolean pointItemRecipes;
	public static int mushroomSpawningRate;
	public static int kyoumarubotanSpawningRate;

	public static void setConfig(File configFile) {
		config = new Configuration(configFile);
		loadConfig();
		MinecraftForge.EVENT_BUS.register(ChangeListener.class);
	}

	private static void loadConfig() {

		config.addCustomCategoryComment("Grimoire of Alice", "Don't change this if you don't know what you are doing");
		maskRecipes = config.get("Masks", "Masks Recipes", true, "Can players get Kokoro's Masks?").getBoolean();
		pointItemRecipes = config.get("Points", "Point Item Recipes", true, "Can players get Point Items?").getBoolean();
		mushroomSpawningRate = config.get("Mushrooms", "Mushroom Spawning Rate", 10, "How many Spawn tries per chunks?").getInt();
		kyoumarubotanSpawningRate = config.get("Kyoumatubotan", "Kyoumatubotan Spawning Rate", 10, "How many Spawn tries per chunks?").getInt();
		if(config.hasChanged()) {
			config.save();
		}
	}

	public static class ChangeListener {

		@SubscribeEvent
		public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
			if(event.getModID().equalsIgnoreCase(LibMod.MODID)) {
				loadConfig();
			}
		}
	}
}
