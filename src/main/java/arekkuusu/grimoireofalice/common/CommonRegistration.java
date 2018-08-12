/*
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common;

import arekkuusu.grimoireofalice.client.effect.SoundBase;
import arekkuusu.grimoireofalice.common.block.ModBlocks;
import arekkuusu.grimoireofalice.common.item.ModItems;
import arekkuusu.grimoireofalice.common.potion.PotionElixir;
import arekkuusu.grimoireofalice.common.potion.PotionRadiationPoisoning;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public class CommonRegistration {

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		ModItems.register(event.getRegistry());
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		ModBlocks.register(event.getRegistry());
	}

	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
		IForgeRegistry<SoundEvent> registry = event.getRegistry();
		registry.register(new SoundBase("camera_beep"));
		registry.register(new SoundBase("camera_shoot"));
		registry.register(new SoundBase("camera_shutter"));
		registry.register(new SoundBase("needle_sweep"));
		registry.register(new SoundBase("simple_bell"));
		registry.register(new SoundBase("crafting_spell"));
		registry.register(new SoundBase("orin_nyaa"));
		registry.register(new SoundBase("power_up"));
		registry.register(new SoundBase("attack_long"));
		registry.register(new SoundBase("horn"));
		registry.register(new SoundBase("warp"));
		registry.register(new SoundBase("ufo_iddle"));
		registry.register(new SoundBase("ufo_spawn"));
		registry.register(new SoundBase("caution"));
		registry.register(new SoundBase("wave"));
		registry.register(new SoundBase("wind"));
		registry.register(new SoundBase("page_turn"));
		registry.register(new SoundBase("wing_flap"));
		registry.register(new SoundBase("ora"));
	}

	@SubscribeEvent
	public static void registerPotions(RegistryEvent.Register<Potion> event) {
		event.getRegistry().registerAll(
				new PotionElixir(),
				new PotionRadiationPoisoning()
		);
	}
}
