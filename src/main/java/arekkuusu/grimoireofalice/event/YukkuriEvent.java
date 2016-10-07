package arekkuusu.grimoireofalice.event;

import arekkuusu.grimoireofalice.entity.EntityMagicCircle;
import arekkuusu.grimoireofalice.handler.EnumTextures;
import arekkuusu.grimoireofalice.item.ModItems;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class YukkuriEvent {

	@SubscribeEvent
	public void LivingDeathEvent(LivingDeathEvent event) {
		EntityLivingBase living = event.getEntityLiving();
		World world = living.worldObj;
		boolean doSound = false;
			if (living instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) living;
				if (!world.isRemote) {
					if (player.getEntityData().hasKey("Eternal") && player.getEntityData().getBoolean("Eternal")) {
						doSound = true;

						player.hurtResistantTime = 50;

						EntityMagicCircle circle = new EntityMagicCircle(world, player, EnumTextures.RED_PENTAGRAM, player.hurtResistantTime);
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

				if(doSound){
					world.playSound(player, new BlockPos(player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D), //Sounds don't play
							SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE, SoundCategory.HOSTILE, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
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
					return;
				}
			}
			if (player.inventory.hasItemStack(new ItemStack(ModItems.substituteJizo))) {
				for (int i = 0; i < player.inventory.mainInventory.length; i++) {
					if (player.inventory.mainInventory[i] != null &&
							player.inventory.mainInventory[i].getItem() == ModItems.substituteJizo) {
						if (player.inventory.mainInventory[i].stackSize > 1) {
							player.inventory.mainInventory[i].stackSize--;
						} else {
							player.inventory.mainInventory[i] = null;
						}
						player.worldObj.playSound(player, new BlockPos(player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D), //Sounds don't play
								SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.HOSTILE, 0.5F, player.worldObj.rand.nextFloat() * 0.1F + 0.9F);
						event.setCanceled(true);
						break;
					}
				}
			}
		}
	}
}
