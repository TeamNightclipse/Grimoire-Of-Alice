package arekkuusu.grimoireofalice.common.event;

import java.util.ArrayList;
import java.util.List;

import arekkuusu.grimoireofalice.api.AliceAPI;
import arekkuusu.grimoireofalice.common.core.handler.ConfigHandler;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilitiesEvent {

	private final ArrayList<EntityPlayer> playersFlying = new ArrayList<>();

	//onItemDrop and onItemToss handle player dropping stuff on death and drag n' drop from inventory.
	@SubscribeEvent
	public void onItemDrop(PlayerDropsEvent event) {
		if(ConfigHandler.grimoireOfAlice.features.allowFly) {
			EntityPlayer player = event.getEntityPlayer();
			List<EntityItem> drop = event.getDrops();
			for(EntityItem item : drop) {
				Item i = item.getItem().getItem();
				processItemRemoveFlyer(player, i);
			}
		}
	}

	@SubscribeEvent
	public void onItemToss(ItemTossEvent event) {
		if(ConfigHandler.grimoireOfAlice.features.allowFly) {
			EntityPlayer player = event.getPlayer();
			Item item = event.getEntityItem().getItem().getItem();
			processItemRemoveFlyer(player, item);
		}
	}

	@SubscribeEvent
	public void updatePlayerFlyStatus(LivingEvent.LivingUpdateEvent event) {
		if(ConfigHandler.grimoireOfAlice.features.allowFly && event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			//Flying
			if(playersFlying.contains(player)) {
				if(canFly(player)) {
					player.capabilities.allowFlying = true;
					if(!player.world.isRemote) {
						player.sendPlayerAbilities();
					}
				}
				else if(!player.isSpectator() && !player.capabilities.isCreativeMode) {
					player.capabilities.allowFlying = false;
					player.capabilities.isFlying = false;
					if(!player.world.isRemote) {
						player.sendPlayerAbilities();
					}
					playersFlying.remove(player);
				}
			}
			else if(canFly(player)) {
				playersFlying.add(player);
			}
		}
	}

	private void processItemRemoveFlyer(EntityPlayer player, Item item) {
		if(isFlyItem(item) && !player.capabilities.isCreativeMode) {
			player.capabilities.allowFlying = false;
			player.capabilities.isFlying = false;
			if(!player.world.isRemote) {
				player.sendPlayerAbilities();
			}
			playersFlying.remove(player);
		}
	}

	private static boolean canFly(EntityPlayer player) {
		return AliceAPI.getFlyingItems().stream().anyMatch(stack -> player.inventory.hasItemStack(stack))
				|| AliceAPI.getFlyingArmor().stream().anyMatch(
				stack -> player.inventory.armorInventory.stream().anyMatch(stack::isItemEqual));
	}

	private static boolean isFlyItem(Item item) {
		List<ItemStack> flyItems = AliceAPI.getFlyingItems();
		List<ItemStack> flyArmor = AliceAPI.getFlyingArmor();

		return (!flyItems.isEmpty() && flyItems.stream().anyMatch(stack -> item == stack.getItem())) ||
				(!flyArmor.isEmpty() && flyArmor.stream().anyMatch(stack -> item == stack.getItem()));
	}
}
