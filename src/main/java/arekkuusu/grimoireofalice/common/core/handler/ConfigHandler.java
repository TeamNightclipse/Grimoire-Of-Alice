/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.core.handler;

import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;

@SuppressWarnings("WeakerAccess")
@Config(modid = LibMod.MODID)
public class ConfigHandler {

	@Comment("Don't change this if you don't know what you are doing")
	public static GrimoireOfAlice grimoireOfAlice = new GrimoireOfAlice();

	public static class GrimoireOfAlice {

		@Comment("Here simply change to true or false if players can get certain Items")
		public Crafting crafting = new Crafting();
		@Comment("Here simply change to true or false if players can get certain items")
		public Food food = new Food();
		@Comment("Here simply change the spawning rate of worldgen features")
		public WorldGen worldGen = new WorldGen();
		@Comment("Here you can turn certain features or abilities on and off")
		public Features features = new Features();
		@Comment("Here you can turn mob spawning on and off")
		public Spawning spawning = new Spawning();

		public static class Crafting {

			@Comment("Here simply change to true or false if players can get certain items from the altar")
			public Altar altar = new Altar();

			@Comment("Can players craft the mask?")
			public boolean mask = true;
			@Comment("Can players craft the hihiirokane?")
			public boolean hihiirokane = false;
			@Comment("Can players craft hihiirokane blocks?")
			public boolean hihiirokaneBlock = true;
			@Comment("Can players craft the shimenawa rope?")
			public boolean shimenawaRope = true;
			@Comment("Can players craft the hakurouken?")
			public boolean hakurouken = true;
			@Comment("Can players craft the watermelon blade?")
			public boolean waterMelonBlade = true;
			@Comment("Can players craft the watermelon sword?")
			public boolean waterMelonSword = true;
			@Comment("Can players craft the popsicle stick?")
			public boolean popsicleStick = true;
			@Comment("Can players craft the cattail plant?")
			public boolean cattailPlant = true;
			@Comment("Can players craft the ghastly send off lantern?")
			public boolean ghastlySendOffLantern = true;
			@Comment("Can players craft the patchy book?")
			public boolean patchyBook = true;
			@Comment("Can players craft the syringe?")
			public boolean syringe = true;
			@Comment("Can players craft the simple ufos?")
			public boolean simpleUfo = true;
			@Comment("Can players get Kokoro's Masks?")
			public boolean kokoroMask = false;
			@Comment("Can players craft the spiritual strike talisman?")
			public boolean talisman = true;
			@Comment("Can players craft the tenshi hat?")
			public boolean tenshiHat = true;

			public static class Altar {

				public Masks masks = new Masks();
				@Comment("Here are all recipes for items that depend on DanmakuCore")
				public DanmakuCore danmakuCore = new DanmakuCore();

				@Comment("Can players craft the mask?")
				public boolean mask = false;
				@Comment("Can players craft the hihiirokane?")
				public boolean hihiirokane = true;
				@Comment("Can players craft the patchy book?")
				public boolean patchyBook = true;
				@Comment("Can players craft the syringe?")
				public boolean syringe = true;
				@Comment("Can players craft the simple ufos?")
				public boolean simpleUfo = false;
				@Comment("Can players craft the third eye?")
				public boolean thirdEye = true;
				@Comment("Can players craft the kanako's shimenawa?")
				public boolean kakanoShimenawa = true;
				@Comment("Can players craft the amenonuhoko?")
				public boolean amenonuhoko = true;
				@Comment("Can players craft the blood thirsty orb?")
				public boolean bloodThirstyOrb = true;
				@Comment("Can players craft the budah boul?")
				public boolean budahBoul = true;
				@Comment("Can players craft the cursed decoy doll?")
				public boolean cursedDecoyDoll = true;
				@Comment("Can players craft the death scythe?")
				public boolean deathScythe = true;
				@Comment("Can players craft the dragon jewel?")
				public boolean dragonJewel = true;
				@Comment("Can players craft the dragon stone?")
				public boolean dragonStone = true;
				@Comment("Can players craft the fake miracle mallet?")
				public boolean fakeMiracleMallet = true;
				@Comment("Can players craft the fire robe?")
				public boolean fireRobe = true;
				@Comment("Can players craft the gap folding umbrella?")
				public boolean gapFoldingUmbrella = true;
				@Comment("Can players craft the ghost dipper?")
				public boolean ghostDipper = true;
				@Comment("Can players craft the grimoire book?")
				public boolean grimoireBook = false;
				@Comment("Can players craft the hakurei gohei?")
				public boolean hakureiGohei = true;
				@Comment("Can players craft the ibaraki box?")
				public boolean ibarakiBox = true;
				@Comment("Can players craft the ichirin unzan?")
				public boolean ichirinUnzan = true;
				@Comment("Can players craft the ichirin ring?")
				public boolean ichirinRing = true;
				@Comment("Can players craft the kappa hat?")
				public boolean kappaHat = true;
				@Comment("Can players craft the maple leaf shield?")
				public boolean mapleLeafShield = true;
				@Comment("Can players craft the marisa hat?")
				public boolean marisaHat = true;
				@Comment("Can players craft the miko cloak?")
				public boolean mikoCloak = true;
				@Comment("Can players craft the miko stick?")
				public boolean mikoStick = true;
				@Comment("Can players craft the mochi hammer?")
				public boolean mochiHammer = true;
				@Comment("Can players craft the momiji's scimitar sword?")
				public boolean momijisScimitarSword = true;
				@Comment("Can players craft the nazin pendulum?")
				public boolean nazrinPendulum = true;
				@Comment("Can players craft the nazrin sticks?")
				public boolean narzinStick = true;
				@Comment("Can players craft the needle?")
				public boolean needle = true;
				@Comment("Can players craft the nimble fabric?")
				public boolean nimbleFabric = true;
				@Comment("Can players craft the nue trident?")
				public boolean nueTrident = true;
				@Comment("Can players craft the ibuki gourd?")
				public boolean ibukiGourd = true;
				@Comment("Can players craft the icicle sword?")
				public boolean icicleSword = true;
				@Comment("Can players craft the rod of remorse?")
				public boolean rodOfRemorse = true;
				@Comment("Can players craft the rumia sword?")
				public boolean rumiaSword = true;
				@Comment("Can players craft the sacred toyosatomimi sword?")
				public boolean sacredToyosatomimiSword = true;
				@Comment("Can players craft the sanae gohei?")
				public boolean sanaeGohei = true;
				@Comment("Can players craft the sariel wand?")
				public boolean sarielWand = false;
				@Comment("Can players craft the sichi seiken?")
				public boolean sichiSeiken = true;
				@Comment("Can players craft the kanako's onbashira?")
				public boolean kanakoOnbashira = true;
				@Comment("Can players craft the skull?")
				public boolean skull = false;
				@Comment("Can players craft the stop watch?")
				public boolean stopWatch = true;
				@Comment("Can players craft the substitute jizo?")
				public boolean substituteJizo = true;
				@Comment("Can players craft the suwako hat?")
				public boolean suwakoHat = true;
				@Comment("Can players craft the swallow cowrie shell?")
				public boolean swallowCowrieShell = true;
				@Comment("Can players craft the shinmyoumaru bowl?")
				public boolean shinmyoumaruBowl = true;
				@Comment("Can players craft the roukanken?")
				public boolean roukanken = true;
				@Comment("Can players craft the simpleUfo?")
				public boolean ufo = true;
				@Comment("Can players craft the wall passing chisel?")
				public boolean wallPassingChisel = true;
				@Comment("Can players craft the youkai book?")
				public boolean youkaiBook = false;
				@Comment("Can players craft the toyosatomimi hat?")
				public boolean toyosatomimiHat = true;
				@Comment("Can players craft the utsuho wings?")
				public boolean utsuhoWings = true;
				@Comment("Can players craft the nether shard?")
				public boolean netherShard = true;
				@Comment("Can players craft the gap?")
				public boolean gap = true;
				@Comment("Can players craft the murasa ghost anchor?")
				public boolean ghostAnchor = true;
				@Comment("Can players craft the charm of healing?")
				public boolean healingCharm = true;
				@Comment("Can players craft the Seamless Ceiling of Kinkaku-ji?")
				public boolean kinkakuCeiling = true;
				@Comment("Can players craft the Red Stone of Aja?")
				public boolean redstoneAja = true;

				@Comment("Can players craft holy stones?")
				public boolean holyStone = true;
				@Comment("Can players craft holy key stone?")
				public boolean holyKeyStone = true;
				@Comment("Can players craft onbashiras?")
				public boolean onbashira = true;
				@Comment("Can players craft end crystals?")
				public boolean endCrystal = true;
				@Comment("Can player craft nuclear boots?")
				public boolean nuclearBoots = true;

				public static class Masks {

					@Comment("Can players get Kokoro's Masks from the Altar?")
					public boolean kokoroMask = true;
				}

				public static class DanmakuCore {

					@Comment("Can players craft the elly scythe?")
					public boolean ellyScythe = true;
					@Comment("Can players craft the jeweled hourai?")
					public boolean jeweledHourai = true;
					@Comment("Can players craft the laevatein?")
					public boolean laevatein = true;
					@Comment("Can players craft the leaf?")
					public boolean leaf = true;
					@Comment("Can players craft the miracle mallet?")
					public boolean miracleMallet = true;
					@Comment("Can players craft the shou lamp?")
					public boolean shouLamp = true;
					@Comment("Can players craft the spell card pouch?")
					public boolean spellCardPuch = true;
					@Comment("Can players craft the sword of hisou?")
					public boolean swordOfHisou = true;
					@Comment("Can players craft the tengu fan?")
					public boolean tenguFan = true;
					@Comment("Can players craft the trumpet?")
					public boolean trumpet = true;
					@Comment("Can players craft the violin?")
					public boolean violin = true;
					@Comment("Can players craft the piano?")
					public boolean piano = true;
					@Comment("Can players craft the nuclear rod?")
					public boolean nuclearRod = true;
					@Comment("Can players craft the tengu camera?")
					public boolean tenguCamera = true;
				}
			}
		}

		public static class Food {

			@Comment("Here simply change to true or false if players can get certain items from the altar")
			public Altar altar = new Altar();

			@Comment("Can players craft grilled lamprey?")
			public boolean grilledLamprey = true;
			@Comment("Can players get the heavely peach?")
			public boolean heavelyPeach = true;
			@Comment("Can players craft shroom powder?")
			public boolean shroomPowder = true;

			public static class Altar {
				@Comment("Can players craft the ultramarine orb elixir?")
				public boolean ultramarineOrbElixir = true;
				@Comment("Can players craft the yuugi sake?")
				public boolean yuugiSake = true;
				@Comment("Can players craft the hourai elixir?")
				public boolean houraiElixir = true;
				@Comment("Can players craft the kappa nostrum?")
				public boolean kappaNostrum = true;
			}
		}

		public static class WorldGen {

			@Comment("Can players find Loot in Chests?")
			public boolean lootGen = true;
			@Comment("How many shroom spawn tries per chunk?")
			public int shroomSpawnRate = 25;
			@Comment("How many kyoumarubotan spawn tries per chunk?")
			public int kyoumarubotanSpawnRate = 5;
			@Comment("How many impure stone spawn tries per chunk?")
			public int impureStoneSpawnRate = 5;
			@Comment("How many impure stone per chunk?")
			public int impureStoneQuantity = 3;
		}

		public static class Features {

			@Comment("Use vanilla block models for the onbashira and pillar?")
			public boolean vanillaBlockModels = true;
			@Comment("Can the ender dragon drop the sword of kusanagi?")
			public boolean swordOfKusanagi = true;
			@Comment("Allow shichi seiken drop goods?")
			public boolean allowGoodDrops = true;
			@Comment("Dragon Scale rarity?")
			public int dragonScaleRarity = 5;
			@Comment("Allow flying Items?")
			public boolean allowFly = true;
			@Comment("Allow items and potions that allow the player to revive")
			public boolean allowRevive = true;
			@Comment("Allow time stop effect of sakuya's stop watch")
			public boolean timeStopEffect = true;
			@Comment("Allow time stop render effect //Not yet implemented")
			public boolean timeStopRenderEffect = true;
			@Comment("Max range between Gaps")
			public int gapRange = 100;
		}

		public static class Spawning {

			@Comment("Set this to false to prevent books from spawning naturally")
			public boolean bookSpawning = true;
			@Comment("Put here all biome types where the book can spawn and the spawn weight (Use BIOME:weight, where BIOME is the Biome type, and weight is the spawning weight)")
			public String[] bookBiomeTypes = new String[]{"MAGICAL:25", "SPOOKY:20", "MOUNTAIN:20", "PLAINS:20", "FOREST:20", "SNOWY:20"};
		}
	}
}
