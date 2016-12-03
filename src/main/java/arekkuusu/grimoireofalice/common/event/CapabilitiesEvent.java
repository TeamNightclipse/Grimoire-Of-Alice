package arekkuusu.grimoireofalice.common.event;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

import arekkuusu.grimoireofalice.common.handler.ConfigHandler;
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
	private final List<ItemStack> flyItems = ImmutableList.of(
			new ItemStack(ModItems.HAKUREI_GOHEI),
			new ItemStack(ModItems.UTSUHO_WINGS)
	);

	//onItemDrop and onItemToss handle player dropping stuff on death and drag n' drop from inventory.
	@SubscribeEvent
	public void onItemDrop(PlayerDropsEvent event) {
		if(ConfigHandler.grimoireOfAlice.features.allowFly) {
			EntityPlayer player = event.getEntityPlayer();
			List<EntityItem> drop = event.getDrops();
			for(EntityItem item : drop) {
				Item i = item.getEntityItem().getItem();
				if(i == ModItems.HAKUREI_GOHEI || i == ModItems.UTSUHO_WINGS) {
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
			if(item == ModItems.HAKUREI_GOHEI || item == ModItems.UTSUHO_WINGS) {
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

	/*Tested on:
	* - Chests
	* - Crafting Pillars
	* - Item Frames
	* - Dragging the Item in the Inventory
	* */
	@SubscribeEvent
	public void updatePlayerFlyStatus(LivingEvent.LivingUpdateEvent event) {
		if(ConfigHandler.grimoireOfAlice.features.allowFly && event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.getEntityLiving();
			//Flying
			if(playersFlying.contains(player)) {
				if(canFlyAndMove(player)) {
					player.capabilities.allowFlying = true;
				}
				else if(!player.isSpectator() && !player.capabilities.isCreativeMode) {
					player.capabilities.allowFlying = false;
					player.capabilities.isFlying = false;
					if(!player.worldObj.isRemote) {
						player.sendPlayerAbilities();
					}
				}
				playersFlying.remove(player);
			}
			else if(canFlyAndMove(player)) {
				playersFlying.add(player);
				player.capabilities.allowFlying = true;
				if(!player.worldObj.isRemote) {
					player.sendPlayerAbilities();
				}
			}
		}
	}

	private boolean canFlyAndMove(EntityPlayer player) {
		if(flyItems.stream().anyMatch(stack -> player.inventory.hasItemStack(stack))) {

			@SuppressWarnings("ConstantConditions") //Liar
			IItemHandler capability = player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

			for(int i = 0; i < capability.getSlots(); i++) {
				ItemStack stack = capability.getStackInSlot(i);
				if(stack != null && stack.getItem() == ModItems.HAKUREI_GOHEI) {

					//TODO: This should NOT be here
					if(getGoheiMode(stack) == 0 && !player.isSneaking()) {
						Vec3d vec = player.getLookVec();
						if(player.motionX < 0.5 && player.motionX > -0.5) {
							player.motionX = 0.5 * vec.xCoord;
						}
						if(player.motionY < 0.5 && player.motionY > -0.5) {
							player.motionY = 0.5 * vec.yCoord;
						}
						if(player.motionZ < 0.5 && player.motionZ > -0.5) {
							player.motionZ = 0.5 * vec.zCoord;
						}
					}

					break;
				}
			}

			return true;
		}
		else return player.inventory.armorInventory[2] != null && player.inventory.armorInventory[2].getItem() == ModItems.UTSUHO_WINGS;
	}

	private int getGoheiMode(ItemStack itemStack) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		return nbt == null ? 0 : nbt.getByte("GoheiMode");
	}
}
