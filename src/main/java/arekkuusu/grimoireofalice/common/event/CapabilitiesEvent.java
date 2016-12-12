package arekkuusu.grimoireofalice.common.event;

import java.util.ArrayList;
import java.util.List;

import arekkuusu.grimoireofalice.api.GrimoireOfAliceAPI;
import arekkuusu.grimoireofalice.api.items.IItemData;
import com.google.common.collect.ImmutableList;

import arekkuusu.grimoireofalice.common.core.handler.ConfigHandler;
import arekkuusu.grimoireofalice.common.item.ModItems;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class CapabilitiesEvent {

	private final ArrayList<EntityPlayer> playersFlying = new ArrayList<>();
	private final List<ItemStack> flyItems = GrimoireOfAliceAPI.getFlyItems();
	private final List<ItemStack> flyArmor = GrimoireOfAliceAPI.getFlyArmor();

	//onItemDrop and onItemToss handle player dropping stuff on death and drag n' drop from inventory.
	@SubscribeEvent
	public void onItemDrop(PlayerDropsEvent event) {
		if(ConfigHandler.grimoireOfAlice.features.allowFly) {
			EntityPlayer player = event.getEntityPlayer();
			List<EntityItem> drop = event.getDrops();
			for(EntityItem item : drop) {
				Item i = item.getEntityItem().getItem();
				if(isFlyItem(i)) {
					if(!player.capabilities.isCreativeMode) {
						player.capabilities.allowFlying = false;
						player.capabilities.isFlying = false;
						if(!player.worldObj.isRemote) {
							player.sendPlayerAbilities();
						}
						playersFlying.remove(player);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onItemToss(ItemTossEvent event) {
		if(ConfigHandler.grimoireOfAlice.features.allowFly) {
			EntityPlayer player = event.getPlayer();
			Item item = event.getEntityItem().getEntityItem().getItem();
			if(isFlyItem(item)) {
				if(!player.capabilities.isCreativeMode) {
					player.capabilities.allowFlying = false;
					player.capabilities.isFlying = false;
					if(!player.worldObj.isRemote) {
						player.sendPlayerAbilities();
					}
					playersFlying.remove(player);
				}
			}
		}
	}

	/**
	 * Tested on:
	 * - Chests
	 * - Crafting Pillars
	 * - Item Frames
	 * - Dragging the Item in the Inventory
	 * - Tiles from another mods
	 * - Crashes: Not tested yet
	**/
	@SubscribeEvent
	public void updatePlayerFlyStatus(LivingEvent.LivingUpdateEvent event) {
		if (ConfigHandler.grimoireOfAlice.features.allowFly && event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			//Flying
			if (playersFlying.contains(player)) {
				if (canFlyAndMove(player)) {
					player.capabilities.allowFlying = true;
					if (!player.worldObj.isRemote) {
						player.sendPlayerAbilities();
					}
					return;
				}
				else if (!player.isSpectator() && !player.capabilities.isCreativeMode) {
					player.capabilities.allowFlying = false;
					player.capabilities.isFlying = false;
					if (!player.worldObj.isRemote) {
						player.sendPlayerAbilities();
					}
				}
				playersFlying.remove(player);
			}
			else if (canFlyAndMove(player)) {
				playersFlying.add(player);
			}
		}
	}

	private boolean canFlyAndMove(EntityPlayer player) {
		return flyItems.stream().anyMatch(stack -> player.inventory.hasItemStack(stack)) || player.inventory.armorInventory[2] != null && (flyArmor.stream().anyMatch(stack -> stack.getItem() == player.inventory.armorInventory[2].getItem()));
	}

	private boolean isFlyItem(Item item) {
		return (!flyItems.isEmpty() && flyItems.stream().anyMatch(stack -> item == stack.getItem())) || (!flyArmor.isEmpty() && flyArmor.stream().anyMatch(stack -> item == stack.getItem()));
	}
}
