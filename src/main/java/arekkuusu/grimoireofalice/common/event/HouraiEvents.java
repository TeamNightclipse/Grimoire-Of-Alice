package arekkuusu.grimoireofalice.common.event;

import arekkuusu.grimoireofalice.common.core.capability.HouraiProvider;
import arekkuusu.grimoireofalice.common.core.capability.IHouraiCapability;
import arekkuusu.grimoireofalice.common.core.handler.ConfigHandler;
import arekkuusu.grimoireofalice.common.entity.EntityMagicCircle;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import arekkuusu.grimoireofalice.common.potion.ModPotions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.List;
import java.util.stream.Collectors;

public class HouraiEvents {

	@CapabilityInject(IHouraiCapability.class)
	public static final Capability<IHouraiCapability> HOURAI_CAPABILITY = null;

	//@SubscribeEvent
	//public void onLogin(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event) {
	//	if (HouraiProvider.get(event.player) != null) {
	//		HouraiProvider.get(event.player).dataChanged(event.player);
	//	}
	//}

	//@SubscribeEvent
	//public void onPlayerChangedDimension(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent event) {
	//	if (HouraiProvider.get(event.player) != null) {
	//		HouraiProvider.get(event.player).dataChanged(event.player);
	//	}
	//}

	@SubscribeEvent
	public void attachPlayer(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof EntityPlayer) {
			event.addCapability(new ResourceLocation(LibMod.MODID, "GrimoireData"), new HouraiProvider());
		}
	}

	@SuppressWarnings("ConstantConditions")
	@SubscribeEvent
	public void onDeath(LivingDeathEvent event) {
		if (ConfigHandler.grimoireOfAlice.features.allowRevive) {
			EntityLivingBase victim = event.getEntityLiving();
			World world = victim.worldObj;

			if (!world.isRemote && victim instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) victim;
				if (player.hasCapability(HOURAI_CAPABILITY, null) && player.getCapability(HOURAI_CAPABILITY, null).getHouraiLevel() == 3) {
					player.hurtResistantTime = 50;

					EntityMagicCircle circle = new EntityMagicCircle(world, player, EntityMagicCircle.EnumTextures.BLUE_NORMAL,
							player.hurtResistantTime);
					world.spawnEntityInWorld(circle);

					player.isDead = false;
					player.setHealth(player.getMaxHealth());
					player.getFoodStats().addStats(100, 100);
					event.setCanceled(true);
				} else {
					@SuppressWarnings("ConstantConditions")
					boolean potion = player.isPotionActive(ModPotions.ELIXIR);
					if (potion) {
						player.hurtResistantTime = 100;
						player.isDead = false;
						player.setHealth(player.getMaxHealth());
						event.setCanceled(true);
					}
				}
			}
		}
	}

	@SuppressWarnings("ConstantConditions")
	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.player.hasCapability(HOURAI_CAPABILITY, null) && event.player.getCapability(HOURAI_CAPABILITY, null).getHouraiLevel() > 1) {
			List<PotionEffect> badPotions = event.player.getActivePotionEffects().stream()
					.filter(potionEffect -> potionEffect.getPotion().isBadEffect()).collect(Collectors.toList());
			if (!badPotions.isEmpty()) {
				badPotions.forEach(potionEffect -> event.player.removePotionEffect(potionEffect.getPotion()));
			}
		}
	}

	@SubscribeEvent
	public void onPlayerClone(net.minecraftforge.event.entity.player.PlayerEvent.Clone event) {
		if (event.isWasDeath()) {
			EntityPlayer oldPlayer = event.getOriginal();
			EntityPlayer newPlayer = event.getEntityPlayer();

			IHouraiCapability oldCap = HouraiProvider.get(oldPlayer);
			IHouraiCapability newCap = HouraiProvider.get(newPlayer);
			if (oldCap != null && newCap != null) {
				newCap.setHouraiLevel(oldCap.getHouraiLevel());
			}
		}
	}
}
