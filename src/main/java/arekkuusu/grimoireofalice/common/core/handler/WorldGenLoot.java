package arekkuusu.grimoireofalice.common.core.handler;

import com.google.common.collect.ImmutableList;

import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class WorldGenLoot {

	private static final List<String> TABLES = ImmutableList.of(
			"chests/abandoned_mineshaft", "chests/desert_pyramid",
			"chests/jungle_temple", "chests/simple_dungeon",
			"chests/spawn_bonus_chest", "chests/stronghold_corridor",
			"chests/village_blacksmith", "gameplay/fishing"
	);

	public WorldGenLoot() {
		for(String table : TABLES) LootTableList.register(new ResourceLocation(LibMod.MODID, table));
	}

	@SubscribeEvent
	public void onLoot(LootTableLoadEvent event) {
		String chestLoot = "minecraft:chests/";
		String fishingLoot = "minecraft:gameplay/";

		String lootName = event.getName().toString();

		if(lootName.startsWith(chestLoot)) {
			String file = lootName.substring(lootName.indexOf(chestLoot) + chestLoot.length());
			switch(file) {
				case "abandoned_mineshaft":
				case "desert_pyramid":
				case "jungle_temple":
				case "simple_dungeon":
				case "spawn_bonus_chest":
				case "stronghold_corridor":
				case "village_blacksmith":
					event.getTable().addPool(getInjectPool("chests/" + file));
					break;
				default:
					break;
			}
		}
		else if(lootName.startsWith(fishingLoot)) {
			String file = lootName.substring(lootName.indexOf(fishingLoot) + fishingLoot.length());
			if(file.equals("fishing")) {
				event.getTable().addPool(getInjectPool("gameplay/" + file));
			}
		}
	}

	private static LootPool getInjectPool(String entryName) {
		return new LootPool(new LootEntry[]{getInjectEntry(entryName, 1)}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "grimoireofalice_pool");
	}

	private static LootEntryTable getInjectEntry(String name, int weight) {
		return new LootEntryTable(new ResourceLocation(LibMod.MODID, name), weight, 0, new LootCondition[0], "grimoireofalice_entry");
	}
}
