package arekkuusu.grimoireofalice.handler;

import arekkuusu.grimoireofalice.lib.LibMod;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class WorldGenLoot {

	private static final List<String> TABLES = ImmutableList.of(
			"inject/abandoned_mineshaft", "inject/desert_pyramid",
			"inject/jungle_temple", "inject/simple_dungeon",
			"inject/spawn_bonus_chest", "inject/stronghold_corridor",
			"inject/village_blacksmith"
	);

	public WorldGenLoot() {
		for (String table : TABLES) LootTableList.register(new ResourceLocation(LibMod.MODID, table));
	}

	@SubscribeEvent
	public void onLoot(LootTableLoadEvent event) {
		String prefix = "minecraft:chests/";
		String namu = event.getName().toString();

		if (namu.startsWith(prefix)) {
			String file = namu.substring(namu.indexOf(prefix) + prefix.length());
			event.getTable().addPool(getInjectPool(file));
		}
	}

	private LootPool getInjectPool(String entryName) {
		return new LootPool(new LootEntry[]{getInjectEntry(entryName, 1)}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "grimoireofalice_pool");
	}

	private LootEntryTable getInjectEntry(String name, int weight) {
		return new LootEntryTable(new ResourceLocation(LibMod.MODID, "inject/" + name), weight, 0, new LootCondition[0], "grimoireofalice_entry");
	}
}