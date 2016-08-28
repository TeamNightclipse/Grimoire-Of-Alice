package arekkuusu.grimoireOfAlice.handler;

import arekkuusu.grimoireOfAlice.item.GOAItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class ChestGenerator {

	public static WeightedRandomChestContent grilledLamprey = new WeightedRandomChestContent(new ItemStack(GOAItem.grilledLamprey), 1, 2, 9);
	public static WeightedRandomChestContent volatileString = new WeightedRandomChestContent(new ItemStack(GOAItem.volatileString), 1, 2, 9);
	public static WeightedRandomChestContent kappasNostrum = new WeightedRandomChestContent(new ItemStack(GOAItem.kappasNostrum), 1, 2, 9);

	public static void init() {

		ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(grilledLamprey);
		ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(volatileString);
		ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(kappasNostrum);
	}
	
}
