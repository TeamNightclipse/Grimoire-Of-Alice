/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimore Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package Arekkuusu.GrimoireOfAlice.handler;

import java.io.File;

import Arekkuusu.GrimoireOfAlice.lib.LibMod;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {

	public static Configuration cfg;

	public static final String THO = "THO";
	public static final String MOBS = "Mobs";
	public static final String MISC = "Misc";

	public static boolean THOInsane;
	public static boolean BookEnabled;

	public static void setConfig(File configFile) {

		cfg = new Configuration(configFile);
		loadConfig();
		FMLCommonHandler.instance().bus().register(new ChangeListener());

	}

	public static void loadConfig() {

		cfg.addCustomCategoryComment(THO, "Configuration File is not meant to be changed!");

		THOInsane = cfg.getBoolean(THO, "THO Insane Mode", false, "For Aliens and or Gods looking for a chalenge");
		BookEnabled = cfg.get(MISC, "change for easy modo. EHHH EASY MODO?!?", false, "Enable Chest Loot for Books").getBoolean();
		if(cfg.hasChanged()) {
			cfg.save();
		}
	}

	public static class ChangeListener {

		@SubscribeEvent
		public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {

			if(event.modID.equalsIgnoreCase(LibMod.MODID)) {
				loadConfig();
			}
		}
	}
}
