/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.event;

import arekkuusu.grimoireofalice.api.AliceAPI;
import arekkuusu.grimoireofalice.common.block.ModBlocks;
import arekkuusu.grimoireofalice.common.core.helper.LogHelper;
import arekkuusu.grimoireofalice.common.item.ModItems;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

	public static final Achievement ALTAR = new Achievement("achievement.craftAltar", "craftAltar", 0, 0, ModBlocks.crafting_altar, AchievementList.OPEN_INVENTORY);
	public static final Achievement BUDDHA_BOWL = new Achievement("achievement.craftBuddhaBowl", "craftBuddhaBowl", 0, -5, ModItems.budah_bowl, ALTAR);
	public static final Achievement JEWELED_HOURAI = new Achievement("achievement.craftJeweledHourai", "craftJeweledHourai", 0, 3, ModItems.jeweled_hourai, ALTAR);
	public static final Achievement FIRE_ROBE = new Achievement("achievement.craftFireRobe", "craftFireRobe", -4, 0, ModItems.fire_robe, ALTAR);
	public static final Achievement DRAGON_JEWEL = new Achievement("achievement.craftDragonJewel", "craftDragonJewel", 2, 1, ModItems.dragon_jewel, ALTAR);
	public static final Achievement COWRIE_SHELL = new Achievement("achievement.craftCowrieShell", "craftCowrieShell", -3, -2, ModItems.swallow_cowrie_shell, ALTAR);
	public static final Achievement AJA_STONE = new Achievement("achievement.craftAyaStone", "craftAyaStone", -2, 2, ModItems.red_stone_of_aja, ALTAR);
	public static final Achievement GOLDEN_CEILING = new Achievement("achievement.craftGoldenCeiling", "craftGoldenCeiling", 1, -1, ModItems.seamless_ceiling_of_kinkakuji, ALTAR);
	public static final Achievement ELIXIR = new Achievement("achievement.craftElixir", "craftElixir", 2, -3, ModItems.hourai_elixir, ALTAR);

	public static void init() {
		AliceAPI.addVanillaAchievement(ALTAR);

		AliceAPI.addAltarAchievement(ModItems.budah_bowl, BUDDHA_BOWL.setSpecial());
		AliceAPI.addAltarAchievement(ModItems.fire_robe, FIRE_ROBE.setSpecial());
		AliceAPI.addAltarAchievement(ModItems.dragon_jewel, DRAGON_JEWEL.setSpecial());
		AliceAPI.addAltarAchievement(ModItems.swallow_cowrie_shell, COWRIE_SHELL.setSpecial());
		AliceAPI.addAltarAchievement(ModItems.seamless_ceiling_of_kinkakuji, GOLDEN_CEILING.setSpecial());
		AliceAPI.addAltarAchievement(ModItems.hourai_elixir, ELIXIR.setSpecial());
		AliceAPI.addAltarAchievement(ModItems.jeweled_hourai, JEWELED_HOURAI.setSpecial());
		AliceAPI.addAltarAchievement(ModItems.red_stone_of_aja, AJA_STONE.setSpecial());

		List<Achievement> achievementList = new ArrayList<>();
		achievementList.addAll(AliceAPI.getAltarAchievements().values());
		achievementList.addAll(AliceAPI.getVanillaAchievements());

		AchievementPage.registerAchievementPage(new AchievementPage(LibMod.MOD_NAME, achievementList.toArray(new Achievement[]{})));
		LogHelper.info("Registered Achievements");
	}

	public static void onAltarCraft(@Nullable EntityPlayer player, ItemStack stack) {
		Map<Item, Achievement> map = AliceAPI.getAltarAchievements();
		if(player != null && map.containsKey(stack.getItem())) {
			Achievement achievement = map.get(stack.getItem());
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
		Map<Item, Achievement> map = AliceAPI.getAltarAchievements();
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
