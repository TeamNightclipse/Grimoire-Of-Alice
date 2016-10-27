/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.handler;

import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;

@Config(modid = LibMod.MODID)
public class ConfigHandler {

	@Comment("Don't change this if you don't know what you are doing")
	public static GrimoireOfAlice grimoireOfAlice = new GrimoireOfAlice();

	public static class GrimoireOfAlice {

		@Comment("Here simply change to true or false if players can get certain Items")
		public Crafting crafting = new Crafting();
		@Comment("Here simply change the spawning rate of worldgen features")
		public WorldGen worldGen = new WorldGen();

		public static class Crafting {

			public Masks masks = new Masks();
			@Comment("Can players get Point Items?")
			public boolean pointsItemRecipes = true;

			public static class Masks {

				@Comment("Can players get Kokoro's Masks from Vanilla Crafting table?")
				public boolean vanillaMaskRecipes = false;
				@Comment("Can players get Kokoro's Masks from the Altar?")
				public boolean altarMaskRecipes = true;
			}
		}

		public static class WorldGen {

			@Comment("How many Spawn tries per chunks?")
			public int mushromSpawnRate = 10;
			@Comment("How many Spawn tries per chunks?")
			public int kyoumarubotanSpawnRate = 10;
		}
	}
}
