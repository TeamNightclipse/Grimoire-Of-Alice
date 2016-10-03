package arekkuusu.grimoireofalice.event;

import arekkuusu.grimoireofalice.entity.EntityMagicCircle;
import arekkuusu.grimoireofalice.handler.EnumTextures;
import arekkuusu.grimoireofalice.item.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class YukkuriEvent {

	@SubscribeEvent
	public void LivingDeathEvent(LivingDeathEvent event) {
		EntityLivingBase living = event.getEntityLiving();
		World world = living.worldObj;
		if (!world.isRemote) {
			if (living instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) living;
				if (player.getEntityData().hasKey("Eternal") && player.getEntityData().getBoolean("Eternal")) {
					world.playSound(player, new BlockPos(player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D),
							SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE, SoundCategory.HOSTILE, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);

					player.hurtResistantTime = 50;

					EntityMagicCircle circle = new EntityMagicCircle(world, player, EnumTextures.RED_NORMAL, player.hurtResistantTime);
					world.spawnEntityInWorld(circle);

					player.isDead = false;
					player.setHealth(player.getMaxHealth());
					event.setCanceled(true);
				} else {
					boolean potion = player.isPotionActive(ModItems.elixir);
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

	@SubscribeEvent
	public void LivingHurtEvent(LivingHurtEvent event) {
		if (event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			if(player.inventory.hasItemStack(new ItemStack(ModItems.ghastlySendOffLantern))){
				if(player.getCooldownTracker().hasCooldown(ModItems.ghastlySendOffLantern)){
					event.setCanceled(true);
				}
			} else
			for (int i = 0; i < player.inventory.mainInventory.length; i++) {
				if (player.inventory.mainInventory[i] != null &&
						player.inventory.mainInventory[i].getItem() == ModItems.substituteJizo) {
					if(player.inventory.mainInventory[i].stackSize > 1) {
						player.inventory.mainInventory[i].stackSize--;
					} else {
						player.inventory.mainInventory[i] = null;
					}
					event.setCanceled(true);
					break;
				}
			}
		}
	}
}
