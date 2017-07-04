/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.event;

import arekkuusu.grimoireofalice.api.GrimoireOfAliceAPI;
import arekkuusu.grimoireofalice.common.GrimoireOfAlice;
import arekkuusu.grimoireofalice.common.block.ModBlocks;
import arekkuusu.grimoireofalice.common.core.helper.LogHelper;
import arekkuusu.grimoireofalice.common.item.ModItems;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class AchievementEvents {

	public static final Achievement ALTAR = new Achievement("achievement.craftAltar", "craftAltar", 0, 0, ModBlocks.ALTAR, AchievementList.OPEN_INVENTORY);
	public static final Achievement BUDDHA_BOWL = new Achievement("achievement.craftBuddhaBowl", "craftBuddhaBowl", 0, -5, ModItems.BUDAH_BOUL, ALTAR);
	public static final Achievement JEWELED_HOURAI = new Achievement("achievement.craftJeweledHourai", "craftJeweledHourai", 0, 3, ModItems.JEWELED_HOURAI, ALTAR);
	public static final Achievement FIRE_ROBE = new Achievement("achievement.craftFireRobe", "craftFireRobe", -4, 0, ModItems.FIRE_ROBE, ALTAR);
	public static final Achievement DRAGON_JEWEL = new Achievement("achievement.craftDragonJewel", "craftDragonJewel", 2, 1, ModItems.DRAGON_JEWEL, ALTAR);
	public static final Achievement COWRIE_SHELL = new Achievement("achievement.craftCowrieShell", "craftCowrieShell", -3, -2, ModItems.SWALLOW_COWRIE_SHELL, ALTAR);
	public static final Achievement AJA_STONE = new Achievement("achievement.craftAyaStone", "craftAyaStone", -2, 2, ModItems.RED_STONE_OF_AJA, ALTAR);
	public static final Achievement GOLDEN_CEILING = new Achievement("achievement.craftGoldenCeiling", "craftGoldenCeiling", 1, -1, ModItems.SEAMLESS_CEILING_KINKAKU_JI, ALTAR);
	public static final Achievement ELIXIR = new Achievement("achievement.craftElixir", "craftElixir", 2, -3, ModItems.HOURAI_ELIXIR, ALTAR);

	public static void init() {
		GrimoireOfAliceAPI.registerAchievement(ALTAR);

		GrimoireOfAliceAPI.registerAltarAchievement(ModItems.BUDAH_BOUL, BUDDHA_BOWL.setSpecial());
		GrimoireOfAliceAPI.registerAltarAchievement(ModItems.FIRE_ROBE, FIRE_ROBE.setSpecial());
		GrimoireOfAliceAPI.registerAltarAchievement(ModItems.DRAGON_JEWEL, DRAGON_JEWEL.setSpecial());
		GrimoireOfAliceAPI.registerAltarAchievement(ModItems.SWALLOW_COWRIE_SHELL, COWRIE_SHELL.setSpecial());
		GrimoireOfAliceAPI.registerAltarAchievement(ModItems.SEAMLESS_CEILING_KINKAKU_JI, GOLDEN_CEILING.setSpecial());
		GrimoireOfAliceAPI.registerAltarAchievement(ModItems.HOURAI_ELIXIR, ELIXIR.setSpecial());
		if(GrimoireOfAlice.danmakuCoreInstalled) {
			GrimoireOfAliceAPI.registerAltarAchievement(ModItems.JEWELED_HOURAI, JEWELED_HOURAI.setSpecial());
			GrimoireOfAliceAPI.registerAltarAchievement(ModItems.RED_STONE_OF_AJA, AJA_STONE.setSpecial());
		}

		List<Achievement> achievementList = new ArrayList<>();
		achievementList.addAll(GrimoireOfAliceAPI.getAchievementAltarMap().values());
		achievementList.addAll(GrimoireOfAliceAPI.getAchievementList());

		AchievementPage.registerAchievementPage(new AchievementPage(LibMod.MODNAME, achievementList.toArray(new Achievement[]{})));
		LogHelper.info("Registered Achievements");
	}

	public static void onAltarCraft(@Nullable EntityPlayer player, Item stack) {
		Map<Item, Achievement> map = GrimoireOfAliceAPI.getAchievementAltarMap();
		if(player != null && map.containsKey(stack)) {
			Achievement achievement = map.get(stack);
			player.addStat(achievement);
		}
	}

	@SubscribeEvent
	public void onCraft(PlayerEvent.ItemCraftedEvent event) {
		if(event.crafting.getItem() == ALTAR.icon.getItem()) {
			event.player.addStat(ALTAR);
		}
	}

	@SubscribeEvent
	public void onPickup(PlayerEvent.ItemPickupEvent event) {
		Map<Item, Achievement> map = GrimoireOfAliceAPI.getAchievementAltarMap();
		Item item = event.pickedUp.getItem().getItem();
		if(map.containsKey(item)) {
			Achievement achievement = map.get(item);
			event.player.addStat(achievement);
		}
		else if(item == ALTAR.icon.getItem()) {
			event.player.addStat(ALTAR);
		}
	}
}
